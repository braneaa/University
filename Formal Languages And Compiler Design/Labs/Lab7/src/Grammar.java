import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class Grammar {

    private List<String> NonTerminals;
    private List<String> Terminals;
    private String Start;
    private Map<String, List<List<String>>> Productions;

    Grammar() {
        this.Productions = new HashMap<>();
    }

    void readFromFile() throws IOException {
        File file = new File("data/g1.in");

        BufferedReader reader = new BufferedReader(new FileReader(file));

        String q = reader.readLine();
        this.NonTerminals = Utils.trim(Utils.split(q));

        String e = reader.readLine();
        this.Terminals = Utils.trim(Utils.split(e));

        this.Start = reader.readLine();

        String p;
        while ((p = reader.readLine()) != null) {
            List<String> T = Arrays.asList(p.split("~"));
            String k = T.get(0).trim();
            List<String> all = Utils.trim(Arrays.asList(T.get(1).strip().split("\\|")));
            List<List<String>> splitted = new ArrayList<>();
            for (String s : all) {
                splitted.add(Utils.trim(Arrays.asList(s.split(" "))));
            }
            List<List<String>> values = this.Productions.getOrDefault(k, null);
            if (values != null) {
                values.addAll(splitted);
            } else {
                values = new ArrayList<>(splitted);
                this.Productions.put(k, values);
            }
        }
    }

    List<String> getNonTerminals() {
        return NonTerminals;
    }

    List<String> getTerminals() {
        return Terminals;
    }

    String getStart() {
        return Start;
    }

    Map<String, List<List<String>>> getProductions() {
        return Productions;
    }

    Map<String, List<List<String>>> getProductionsInWhichNonTerminalIsOnTheRight(String nt) {
        Map<String, List<List<String>>> result = new HashMap<>();
        Productions.forEach((k, v) -> {
            for(List<String> productionRule: v) {
                if(productionRule.contains(nt)) {
                    List<List<String>> tempResult = result.getOrDefault(k, new ArrayList<>());
                    tempResult.add(productionRule);
                    result.put(k, tempResult);
                }
            }
        });
        return result;
    }
}
