//I hereby declare upon my word of honor that I have neither given nor received unauthorized help on this work.
//John-Paul King

import java.util.ArrayList;

/**
 * Represents a state of the DFA
 */
public class State {
    String name;
    boolean startState;
    boolean acceptState;
    //for every letter (weight) on chart, there will be a connection
    //two letters can lead to same state, then arraylist would contain multiple references
    ArrayList<Character> weights;
    ArrayList<State> connections;

   public static ArrayList<State> allStates = new ArrayList<State>(); //list of all states

    /**
     * searches list of states for name
     * @param name name to search in list
     * @return state with name that matches arg
     */
    public static State findState(String name) {
        //for states in master list
        for (State s : allStates) {
            //if names match
            if (name.equals(s.getName())) {
                //return its reference
                return s;
            }
        } return null;
    }

    /**
     * Finds and returns start state from list
     * @return start state
     */
    public static State findStartState() {
        for (State s : allStates) {
            if (s.isStartState()) {
                //return start state
                return s;
            }
        } return null;
    }

    /**
     * Constructor
     * @param name name of state
     */
    public State(String name) {
        this.name = name;
        //add to complete list of states
        allStates.add(this);
        //initialize arraylists
        weights = new ArrayList<Character>();
        connections = new ArrayList<State>();

        //Default to false for now
        startState = false;
        acceptState = false;

    }

    /**
     * Makes the state an accept state
     */
    public void setAsAcceptState() {
        acceptState = true;
    }

    /**
     * Makes the state a start state
     */
    public void setAsStartState() {
        startState = true;
    }

    /**
     *
     * @return true if start state
     */
    public boolean isStartState() {
        return startState;
    }

    /**
     *
     * @return true if accept state
     */
    public boolean isAcceptState() {
        return acceptState;
    }

    /**
     *
     * @return name of state
     */
    public String getName() {
        return name;
    }

    /**
     * this function does the same as above
     * Needed it for some debugging
     * @return name of state
     */
    public String toString() {
        return name;
    }

    /**
     * adds to arrays containing letters and the states they lead to
     * Basically keeps track of the "function" information
     * @param c letter (from alphabet)
     * @param x the state which that letter leads to
     */
    public void createConnection(char c, State x) {
        //add these to the end of an array
        weights.add(c);
        connections.add(x);
    }

    /**
     * Moves from one state to another, based on letter
     * @param c letter (from alphabet)
     * @return state after letter
     */
    public State connect(char c) {
        for (int i = 0; i < weights.size(); i++) {
            if (c == weights.get(i)) {
                return connections.get(i);
            }
        }
        return null;
    }
}
