//I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
//John-Paul King

/**
 * Represents a language
 * Contains an alphabet
 */
public class Language {
    //initialized in constructor
    //holds all the valid letters of an alphabet
    public char[] alphabet;


    /**
     * Constructor
     * @param letters all valid letters in alphabet
     */
    public Language(char[] letters){
        //copy array reference
        this.alphabet = letters;
    }

    /**
     * Checks if a string is a valid string in the language
     * @param s string to be tested
     * @return true if string is valid
     */
    public boolean hasString(String s) {
        //switches on if letter is found
        //defaults to false
        boolean foundLetter = false;
        //for every char in a string
        for (int i = 0; i < s.length(); i++) {
            //reset to false
            foundLetter = false;
            //loop through alphabet
            for (char letter : alphabet) {
                //if the passed strings  match
                if (letter == s.charAt(i)) {
                    foundLetter = true;
                    break;
                }
            }   //if letter is not in alpha
            if (!foundLetter) {
                return false; //string is malformed
            }
        } return true; //each letter is within the alphabet
    }

}
