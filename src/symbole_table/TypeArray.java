package symbole_table;

public class TypeArray extends Type {
    @Override
    public boolean isConform(Type other) {
        return other instanceof TypeArray;
    }
}
