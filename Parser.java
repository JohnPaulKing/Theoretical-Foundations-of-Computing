import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.LinkedList;
import java.util.ListIterator;

//class essentially represents a CFG
//builds its paths, terminals, non terminals
//also generates strings
public class Parser {
    private ArrayList<NonTerminal> nonTerminals;
    private ArrayList<Terminal> terminals;
    private Symbol startState;
    private ArrayList<String> alreadyGenerated;
    public static int MAX_EXPANDS = 20; //After this many unexpanded non terminals, the parser will start over

    public Parser() {
        nonTerminals = new ArrayList<>();
        terminals = new ArrayList<>();
        alreadyGenerated = new ArrayList<>();
    }

    public void addNonTerminal(NonTerminal c){
        nonTerminals.add(c);
    }

    public void addTerminal(Terminal c){
        terminals.add(c);
    }

    private Symbol findSymbol(String s) {
        for (NonTerminal t : nonTerminals) {
            if (t.toString().equals(s)) {
                return t;
            }
        }for (Terminal t : terminals) {
            if (t.toString().equals(s)) {
                return t;
            }
        } return null; //none found
    }

    public void setStart(String s){
        startState = findSymbol(s);
        //System.out.println("Start Symbol " + startState);//DEBUG
    }

    public ListIterator<Symbol> findFirstNonTerminal(LinkedList<Symbol> str) {
        ListIterator<Symbol> node = str.listIterator(); //iterator for linked list
        for (int i = 0; i < str.size(); i++, node.next()) { //increase node
            //System.out.print("string of symbols consists of:" );//DEBUG
            //for (Symbol s : str) { //DEBUG
            //    System.out.print(s + " ");//DEBUG
            //} //DEBUG
            //System.out.println();//DEBUG
            if (!str.get(i).isTerminal()) {
            //    System.out.println("Left most expandable symbol: " + str.get(i));//DEBUG
            //    System.out.println("Left most expandable symbol at index: " + i);//DEBUG
                return node;
            }
        } //System.out.println("No leftmost symbol found: terminating string" );//DEBUG
        return null; //no existing non terminals
    }

    public void addPath(String nonT, String[] paths) {
        //before arrow
        NonTerminal start = (NonTerminal) findSymbol(nonT);
        //after arrow
        ArrayList<Symbol> parsedPath = new ArrayList<>();
        for (String s : paths) {
            //epsilon case
            //convert it to a symbol
            parsedPath.add(findSymbol(s));
        } start.addPath(parsedPath);
    }

    public String parse() {
        LinkedList<Symbol> str = new LinkedList<>(); //for easy insertion
        ListIterator<Symbol> node; //iterator for linked list
        str.add(startState);
        //while there are non terminals, replace the
        while ((node = findFirstNonTerminal(str)) != null) {
            //System.out.print("String: ");
            //for (Symbol S : str) {
            //    System.out.print(S);
            //} System.out.println();
            NonTerminal t = (NonTerminal) node.next(); //first non terminal
            ArrayList<Symbol> selectedPath = t.selectPath().contents(); // in compound format
            //remove thing to be replaced
            node.remove();
            //replace (or expand it)
            for (Symbol s : selectedPath) {
                node.add(s);
            }

            //count non terminals
            int ntCounter = 0; //number of non terminals
            for (Symbol s : str) {
                if (!s.isTerminal) {
                    ntCounter++;
                }
            }
            if (ntCounter > Parser.MAX_EXPANDS) {
                return null; //start over
            }
        }

        //build string
        String builtString = "";
        for (Symbol s : str) {
            //if its empty, just don't add it
            if (!s.toString().equals("_EMPTY_")) {
                builtString = builtString + s.toString() + " ";
            }
        }

        //check if already generated
        boolean scrap = false;
        for (String s : alreadyGenerated) {
            if (builtString.equals(s)) {
                scrap = true; //scrap this string, build another
            }
        }

        if (scrap) {
            return null; //main will call it again
        } else {
            alreadyGenerated.add(builtString); //mark as having been generated
            return builtString;
        }
    }
}
