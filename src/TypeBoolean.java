public class TypeBoolean extends Type {

    private static TypeBoolean instance;

    private TypeBoolean() {}

    public static TypeBoolean getInstance() {
        if (instance == null)
        {
            instance = new TypeBoolean();
        }
        return instance;
    }

    @Override
    public boolean isConform(Type other) {
        return other instanceof TypeBoolean;
    }
}

