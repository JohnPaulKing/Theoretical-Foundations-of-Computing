//I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
//John-Paul King

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Jking22_DFA {

    /**
     * This method takes a filename and string as arguments
     * reads in the file and parses the information
     * prints out the results of the test
     * @param args filename, string of letters
     */
    public static void main(String[] args) {
        //for keeping track of valid letters
        Language lang;

        //We will use line to contain a temp array of strings
        String[] line;
        //we will use temp to contain a temp string
        String temp;

        //initialize file scanner
        Scanner in = getScanner(args[0]);

        //if file name wasnt correct
        if (in == null) {
            System.out.println("Failed to create scanner");
            return; //exit program
        }

        //first line of the file contains valid states
        //split them into array of names
        line = in.nextLine().split(",");
        //for every name
        for (String name : line) {
            //create a state
            //constructor will place it in list
            new State(name);
        }

        //second line of file contains alphabet
        line = in.nextLine().split(",");
        //now all of chars are strings in an array
        //need to turn it into one string (and then char array)
        temp = "";
        //for each string
        for (String s : line) {
            //concat string to temp
            temp = temp + s;
        }
        //create language with this alphabet
        lang = new Language(temp.toCharArray());

        //third line of file contains the start state
        temp = in.nextLine();
        //set as start state
        State.findState(temp).setAsStartState();

        //fourth line contains accept states
        line = in.nextLine().split(",");
        //for each name
        for (String s : line) {
            State.findState(s).setAsAcceptState();
        }

        //remove reference to line as it is no longer needed
        line = null;

        //the rest of the lines contain information about the function
        //until end of file
        while (in.hasNextLine()) {
            //reset these for each line
            int counter = 1;
            String stateName1 = "";
            char letter;
            String stateName2;

            //read in line
            temp = in.nextLine();
            //extract first state name
            while (temp.charAt(counter) != ',') {
                //add the next character to the string
                stateName1 = stateName1 + temp.charAt(counter);
                //increase counter
                counter++;
            }
            //increase counter to get past comma
            counter++;
            //extract next char as letter
            letter = temp.charAt(counter);
            //move counter forward 3 spaces
            counter += 4;
            //use substring to get second state name
            stateName2 = temp.substring(counter);

            //now we find the state
            //then add its connection
            State.findState(stateName1).createConnection(letter, State.findState(stateName2));

        }

        //first we have to check if user passed in empty string
        if (args.length < 2) {
            System.out.println(testEmptyString());
        } else {

            //now we check for the string passed in as arg and print result
            System.out.println(theoremTest(args[1], lang));
        }

    }

    /**
     * returns a scanner
     * if no file found, prints error message
     * @param fileName name of file
     * @return scanner object
     */
    public static Scanner getScanner(String fileName) {
        //create file obj
        try {
            return new Scanner(new File(fileName));
        }
        //if wrong username
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Tests the string in the language
     * @param string string of characters to test
     * @param lang language theorems are comprised of
     * @return string message to print to console
     */
    public static String theoremTest(String string, Language lang) {
        //find starter state
        State currentState = State.findStartState();

        //first see if valid string in the language
        if (lang.hasString(string)) {
            for (char letter : string.toCharArray()) {
                //move to the state to which the next letter brings it
                currentState = currentState.connect(letter);
            }
            //test if it ends in accept state
            if (currentState.isAcceptState()) {
                return "Accepted!";
            } else {
                return "Rejected.";
            }

        } else {
            return "Malformed";
        }
    }


    /** if the second argument is empty, this method is called
     * if start state is accept state, empty string passes
     * @return message to print to console
     */
    public static String testEmptyString() {
        if (State.findStartState().isAcceptState()) {
            return "Accepted!";
        } else {
            return "Rejected.";
        }
    }
}