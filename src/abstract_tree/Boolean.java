package abstract_tree;


public class Boolean extends Expression{
    boolean value;
    public Boolean(boolean v){
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
