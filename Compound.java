import java.util.ArrayList;

public class Compound extends Symbol {
    ArrayList<Symbol> expand;
    private int expanded; //how many times this non terminal has been expanded
    public Compound(ArrayList<Symbol> list){
        super("[compound]"); //no name needed, since never printed
        super.isTerminal = false;
        super.isCompound = true;
        expand = list;
    }

    public ArrayList<Symbol> contents() {
        return expand;
    }

}