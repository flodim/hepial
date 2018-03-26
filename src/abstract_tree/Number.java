package abstract_tree;

public class Number extends Expression{
    int value;

    public  Number (int val){
        this.value=val;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}
