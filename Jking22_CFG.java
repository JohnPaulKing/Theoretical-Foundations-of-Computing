import java.util.Scanner;

public class Jking22_CFG {
    public static void main(String[] args) {

        /*variables*/
        Scanner in; //scanner for input
        int iterations; //number of strings parser must produce
        String temp; //point to temporary strings read from file before parse
        String line[]; //array of strings split on regex
        Parser parser; //generates strings based on rules

        /*
        Initiatlize variables
         */
        //init file scanner (using first arg)
        in = Util.getScanner(args[0]);
        //if second arg isn't present, defaults to 1
        iterations = (args[1] != null) ? Integer.parseInt(args[1]) : 0;
        parser = new Parser();
        //add empty terminal, regardless if used
        parser.addTerminal(new Terminal("_EMPTY_"));

        /*set up parser information
        read in from file
         */
        temp = in.nextLine(); //first line / non terminals
        line = temp.split(","); //array of non terminals
        for (String c : line) {
            parser.addNonTerminal(new NonTerminal(c));
        }
        temp = in.nextLine(); //next line / terminals
        line = temp.split(",");
        for (String c : line) {
            parser.addTerminal(new Terminal(c));
        }
        temp = in.nextLine(); //get next line first
        while (in.hasNextLine()) { //doesnt execute on last line
            int counter = 0; //position of string
            String nonTerminal = "";
            //read in next line
            //read non terminal
            while (temp.charAt(counter) != ' ') {
                nonTerminal = nonTerminal + temp.charAt(counter);
                counter++;
            } //starter non terminal complete
            counter+=4; //move past arrow and first space
            line = temp.substring(counter).split(" "); //split on space
            //line now contains each symbol
            //add path for non terminal
            System.out.println();
            parser.addPath(nonTerminal,line);
            temp = in.nextLine(); //next line of file
        }
        //last line has already been read

        parser.setStart(temp);
        temp = null; // garbage collection
        line = null; //garbage collection

        /* run parser n times where n = iterations*/
        for (int i = 0; i < iterations; i++) {
            String generated;
            while ((generated = parser.parse()) == null){ } //parse until not null
            System.out.println("(" + i + ") " + generated);
        }
    }

}
