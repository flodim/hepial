package abstract_tree;

public abstract class Binary extends Expression{
   protected Expression left;
   protected Expression right;

    public Expression getLeft() {
        return left;
    }

    public Expression getRight() {
        return right;
    }

    public void setLeft(Expression left) {
        this.left = left;
    }

    public void setRight(Expression right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "("+left+Operator()+right+")";
    }

    public abstract String Operator();
}
