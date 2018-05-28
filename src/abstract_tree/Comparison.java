package abstract_tree;

import symbole_table.Type;
import symbole_table.TypeBoolean;

public abstract class Comparison extends Binary {
    @Override
    public Type getType() {
        return TypeBoolean.getInstance();
    }
}
