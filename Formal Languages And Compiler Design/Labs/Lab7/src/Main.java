import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    private static Grammar grammar = new Grammar();
    private static Parser parser = new Parser(grammar);

    public static void main(String[] args) throws IOException {

        grammar.readFromFile();
        parser.first();
        parser.generateFollow();
        parser.generateParseTable();

        while (true) {
            printMenu();

            Scanner scanner = new Scanner(System.in);

            String option = scanner.nextLine();

            switch (option) {
                case "1": {
                    printNonTerminals();
                    break;
                }
                case "2": {
                    printTerminals();
                    break;
                }
                case "3": {
                    printProductions();
                    break;
                }
                case "4": {
                    String nonTerminal = scanner.nextLine();
                    printProductionsForANonTerminal(nonTerminal.trim());
                    break;
                }
                case "5": {
                    System.out.println(parser.getParseTable());
                    break;
                }
                case "6": {
                    Scanner inScanner = new Scanner(System.in);
                    List<String> w = Arrays.asList(inScanner.nextLine().replace("\n", "").split(" "));
                    parseSequence(w);
                    break;
                }
                case "0": {
                    return;
                }
                default: {
                    System.out.println("Option does not exist!");
                }
            }
        }
    }

    private static void parseSequence(List<String> w) {
        boolean result = parser.parse(w);
        if (result) {
            Stack<String> pi = parser.getPi();
            System.out.println(pi);
            System.out.println(displayProductions(pi));
            System.out.println("Sequence " + w + " accepted.");
        } else {
            Stack<String> pi = parser.getPi();
            System.out.println(pi);
            System.out.println(displayProductions(pi));
            System.out.println("Sequence " + w + " is not accepted.");
        }
    }

    private static String displayProductions(Stack<String> pi) {
        StringBuilder sb = new StringBuilder();

        for (String productionIndexString : pi) {
            if (productionIndexString.equals("eps")) {
                continue;
            }
            Integer productionIndex = Integer.parseInt(productionIndexString);
            parser.getProductionsNumbered().forEach((key, value) ->{
                if (productionIndex.equals(value))
                    sb.append(value).append(": ").append(key.getKey()).append(" -> ").append(key.getValue()).append("\n");
            });
        }

        return sb.toString();
    }

    private static void printMenu() {
        System.out.println("0. Exit");
        System.out.println("1. Show non-terminals");
        System.out.println("2. Show terminals");
        System.out.println("3. Show all productions");
        System.out.println("4. Show all productions for a non-terminal");
        System.out.println("5. Parse table");
        System.out.println("6. Parse sequence");
    }

    private static void printNonTerminals() {
        System.out.println( grammar.getNonTerminals());
    }

    private static void printTerminals() {
        System.out.println(grammar.getTerminals());
    }

    private static void printProductions() {
        for (String key : grammar.getProductions().keySet()) {
            System.out.println(buildString(key));
        }
    }

    private static void printProductionsForANonTerminal(String nonTerminal) {
        System.out.println(buildString(nonTerminal));
    }

    private static String buildString(String key) {
        List<List<String>> value = grammar.getProductions().get(key);
        if (value != null) {
            StringBuilder all = new StringBuilder();
            for (List<String> list : value) {
                for (String s : list) {
                    all.append(s);
                }
                all.append(" | ");
            }
            return "\t" + key + " ~ " + all.substring(0, all.length() - 2);
        } else return "NonTerminal not found\n";
    }
}
