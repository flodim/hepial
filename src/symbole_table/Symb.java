package symbole_table;

public class Symb {
    private final Type type;
    private final boolean isConst;


    public Symb(Type type, boolean isConst) {
        this.type = type;
        this.isConst = isConst;
    }

    public Type getType() {
        return type;
    }

    public boolean isConst() {
        return isConst;
    }

}
