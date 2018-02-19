
public class Symb {
    private Type type;
    private boolean isConst;

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
