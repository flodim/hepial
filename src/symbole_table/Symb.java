package symbole_table;

public class Symb {
    private final Type type;
    private final boolean isConst;
    private final int line;

    public Symb(Type type, boolean isConst, int line) {
        this.type = type;
        this.isConst = isConst;
        this.line = line;
    }

    public Type getType() {
        return type;
    }

    public boolean isConst() {
        return isConst;
    }

    public int getLine() {
        return line;
    }
}
