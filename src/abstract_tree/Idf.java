package abstract_tree;

public class Idf extends Expression {
    private String name;

    public Idf(String n){
        name=n;
    }

    public String getName() {
        return name;
    }
}
