public class Terminal extends Symbol {
    public Terminal(String text){
        super(text);
        super.isTerminal = true;
        super.isCompound = false;
    }
}
