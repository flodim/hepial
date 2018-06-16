package symbole_table;

public class TypeString extends Type {
    private static TypeString instance;

    private TypeString() {}

    public static TypeString getInstance() {
        if (instance == null) {
            instance = new TypeString();
        }
        return instance;
    }

    @Override
    public boolean isConform(Type other) {
        return other instanceof TypeString;
    }
}
