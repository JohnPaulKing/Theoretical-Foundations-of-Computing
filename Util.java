import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

/**
 * Contains some static methods used in a few different classes.
 */
public class Util {
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
    public static int rng(int max) {
        return (new Random()).nextInt(max);
    }

}
