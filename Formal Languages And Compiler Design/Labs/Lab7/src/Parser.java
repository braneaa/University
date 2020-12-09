

import java.util.*;


class Parser {

    private Grammar grammar;
    private Map<String, List<String>> first;
    private Map<String, Set<String>> follow;
    private Map<Pair<String, String>, Pair<List<String>, Integer>> parseTable;
    private Map<Pair<String, List<String>>, Integer> numberedProductions;
    private Stack<String> alpha;
    private Stack<String> beta;
    private Stack<String> pi;

    Parser(Grammar grammar) {
        this.grammar = grammar;
        this.first = new HashMap<>();
        this.follow = new HashMap<>();
        this.parseTable = new HashMap<>();
        this.numberedProductions = new HashMap<>();
        this.alpha = new Stack<>();
        this.beta = new Stack<>();
        this.pi = new Stack<>();
    }

    private List<String> generateFirst(String nonTerminal) {

        if (this.first.containsKey(nonTerminal)) {
            return this.first.get(nonTerminal);
        }

        List<String> terminals = grammar.getTerminals();
        Map<String, List<List<String>>> productions = grammar.getProductions();

        List<List<String>> all = productions.get(nonTerminal);

        if (all == null)
            return Collections.singletonList(nonTerminal);

        List<String> firsts = new ArrayList<>();
        for (List<String> list : all) {

            String firstOfList = list.get(0);
            if (terminals.contains(firstOfList)) {
                firsts.add(firstOfList);
            } else {
                firsts.addAll(generateFirst(firstOfList));
            }
        }
        return firsts;
    }

    void first() {

        List<String> nonTerminals = grammar.getNonTerminals();
        List<String> terminals = grammar.getTerminals();

        for (String terminal : terminals) {
            List<String> ft = Collections.singletonList(terminal);
            this.first.put(terminal, ft);
        }

        for (String nonTerminal : nonTerminals) {
            this.first.put(nonTerminal, generateFirst(nonTerminal));
        }
    }

    void generateFollow() {

        Map<String, Set<String>> previousIteration = new HashMap<>();
        Map<String, Set<String>> currentIteration;
        List<String> nonTerminals = grammar.getNonTerminals();
        for (String n : nonTerminals)
            previousIteration.put(n, new HashSet<>());
        previousIteration.get(grammar.getStart()).add("$");
        currentIteration = copy(previousIteration);


        while (true) {
            for (String n : nonTerminals) {
                //we get a dictionary with the productions that have n in the rhs
                Map<String, List<List<String>>> productions = grammar.getProductionsInWhichNonTerminalIsOnTheRight(n);
                for (Map.Entry<String, List<List<String>>> entry : productions.entrySet()) {
                    for (List<String> prod : entry.getValue()) {
                        //for each production that has n in rhs we go from n to the end
                        int index = prod.indexOf(n) + 1;
                        for (int i = index; i < prod.size(); i++) {
                            //we add all first of what is after n
                            List<String> first = generateFirst(prod.get(i));
                            currentIteration.get(n).addAll(first);
                            //if first contains ~ then we don't add ~ to current iteration, but add all elems from prev iteration of lhs
                            if (first.contains("eps")) {
                                currentIteration.get(n).remove("eps");
                                currentIteration.get(n).addAll(previousIteration.get(entry.getKey()));
                            }
                        }
                        //if nothing follows n then we add everything from prev iteration of the lhs
                        if (index == prod.size()) {
                            currentIteration.get(n).addAll(previousIteration.get(entry.getKey()));
                        }
                    }
                }
            }
            if (currentIteration.equals(previousIteration))
                break;
            else {
                previousIteration = copy(currentIteration);
            }
        }
        this.follow = currentIteration;
    }

    private static HashMap<String, Set<String>> copy(Map<String, Set<String>> original) {
        HashMap<String, Set<String>> copy = new HashMap<>();
        for (Map.Entry<String, Set<String>> entry : original.entrySet()) {
            Set<String> temp = new HashSet<>(entry.getValue());
            copy.put(entry.getKey(), temp);
        }
        return copy;
    }

    private void numberProductions() {
        int index = 1;
        for (Map.Entry<String, List<List<String>>> production : grammar.getProductions().entrySet()) {
            for (List<String> value : production.getValue())
                numberedProductions.put(new Pair<>(production.getKey(), value), index++);
        }
    }

    void generateParseTable() {
        numberProductions();

        List<String> columnSymbols = new LinkedList<>(grammar.getTerminals());
        columnSymbols.add("$");

        parseTable.put(new Pair<>("$", "$"), new Pair<>(Collections.singletonList("acc"), -1));
        for (String terminal: grammar.getTerminals())
            parseTable.put(new Pair<>(terminal, terminal), new Pair<>(Collections.singletonList("pop"), -1));

        numberedProductions.forEach((key, value) -> {
            String rowSymbol = key.getKey();
            List<String> rule = key.getValue();
            Pair<List<String>, Integer> parseTableValue = new Pair<>(rule, value);

            for (String columnSymbol : columnSymbols) {
                Pair<String, String> parseTableKey = new Pair<>(rowSymbol, columnSymbol);

                // if our column-terminal is exactly first of rule
                if (rule.get(0).equals(columnSymbol) && !columnSymbol.equals("eps")) {
                    parseTable.put(parseTableKey, parseTableValue);
                } else
                // if the first symbol is a non-terminal and it s first contain our column-terminal
                if (grammar.getNonTerminals().contains(rule.get(0)) && first.get(rule.get(0)).contains(columnSymbol)) {
                    if (!parseTable.containsKey(parseTableKey)) {
                        parseTable.put(parseTableKey, parseTableValue);
                    }
                }
                else {
                    // if the first symbol is eps then everything if follow(rowSymbol) will be in parse table
                    if (rule.get(0).equals("eps")) {
                        for (String b : follow.get(rowSymbol))
                            parseTable.put(new Pair<>(rowSymbol, b), parseTableValue);
                    }
                    // if eps is in FIRST(rule)
                    else {
                        Set<String> firsts = new HashSet<>();
                        for (String symbol : rule)
                            if (grammar.getNonTerminals().contains(symbol))
                                firsts.addAll(first.get(symbol));
                        if (firsts.contains("eps")) {
                            for (String b : first.get(rowSymbol)) {
                                if (b.equals("eps")) {
                                    b = "$";
                                }
                                parseTableKey = new Pair<>(rowSymbol, b);
                                if (!parseTable.containsKey(parseTableKey)) {
                                    parseTable.put(parseTableKey, parseTableValue);
                                }
                            }
                        }
                    }
                }
            }
        });
    }

    boolean parse(List<String> w) {
        alpha.clear();
        alpha.push("$");
        pushAll(w, alpha);

        beta.clear();
        beta.push("$");
        beta.push(grammar.getStart());

        pi.clear();
        pi.push("eps");

        boolean parse = true;
        boolean accepted = true;

        while (parse) {
            String betaHead = beta.peek();
            String alphaHead = alpha.peek();

            if (betaHead.equals("$") && alphaHead.equals("$")) {
                return true;
            }

            Pair<String, String> heads = new Pair<>(betaHead, alphaHead);
            Pair<List<String>, Integer> parseTableValue = parseTable.get(heads);

            if (parseTableValue == null) {
                heads = new Pair<>(betaHead, "eps");
                parseTableValue = parseTable.get(heads);
                if (parseTableValue != null) {
                    beta.pop();
                    continue;
                }
            }

            if (parseTableValue == null) {
                parse = false;
                accepted = false;
            } else {
                List<String> production = parseTableValue.getKey();
                Integer productionNb = parseTableValue.getValue();

                if (productionNb == -1 && production.get(0).equals("acc")) {
                    parse = false;
                } else if (productionNb == -1 && production.get(0).equals("pop")) {
                    beta.pop();
                    alpha.pop();
                } else {
                    beta.pop();
                    if (!production.get(0).equals("eps")) {
                        pushAll(production, beta);
                    }
                    pi.push(productionNb.toString());
                }
            }
        }

        return accepted;
    }

    private void pushAll(List<String> sequence, Stack<String> stack) {
        for (int i = sequence.size() - 1; i >= 0; i--) {
            stack.push(sequence.get(i));
        }
    }

    String getParseTable() {
        StringBuilder s = new StringBuilder();
        parseTable.forEach((k, v) -> s.append(k.toString()).append(" -> ").append(v.toString()).append('\n'));
        return s.toString();
    }

    Map<Pair<String, List<String>>, Integer> getProductionsNumbered() {
        return numberedProductions;
    }

    Stack<String> getPi() {
        return pi;
    }
}
