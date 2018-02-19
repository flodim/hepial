public class TypeInteger extends Type {

    private static TypeInteger instance;

    private TypeInteger() {}

    public static TypeInteger getInstance() {
        if (instance == null)
        {
            instance = new TypeInteger();
        }
        return instance;
    }

    @Override
    public boolean isConform(Type other) {
        return other instanceof TypeInteger;
    }
}