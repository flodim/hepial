package abstract_tree;

public class IntNumber extends Expression{
    private Integer value;

    public IntNumber(Integer val){
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
