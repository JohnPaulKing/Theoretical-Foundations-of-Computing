import java.util.ArrayList;

public class NonTerminal extends Symbol {
    ArrayList<Compound> pathways; //all of the symbols that can be taken

    public NonTerminal(String text){
        super(text);
        pathways = new ArrayList<>(); //arraylist for paths
        super.isTerminal = false;
        super.isCompound = false;
    }


    public Compound selectPath() {
        Compound rvalue;
        rvalue = pathways.get(Util.rng(pathways.size()));
        //System.out.println("Symbol to be expanded: "+ this); //debug
        //for (Compound s : pathways) {//debug
        //    System.out.println("pathway option: "+ s); //debug
        //}
        //System.out.println("pathway selected: "+ rvalue);//debug
        //generates a random number, return symbol at that number
        return rvalue;
    }

    public void addPath(ArrayList<Symbol> list) {
        pathways.add(new Compound(list)); //instantiate compound, and add as a list
    }

}
