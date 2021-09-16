public class Symbol {
    protected String text; //what the symbol "is" such
    protected boolean isTerminal;
    protected boolean isCompound; //it turns out I didn't need to have compound be a symbol child

    public Symbol(String text) {
        this.text = text;
    }

    public boolean isTerminal() {
        return isTerminal;
    }

    public String toString() {
        return text; //protected member
    }
}
