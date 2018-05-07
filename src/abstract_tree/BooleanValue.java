package abstract_tree;


public class BooleanValue extends Expression{
    boolean value;
    public BooleanValue(boolean v){
        this.value=v;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value?"vrai":"faux";
    }
}
