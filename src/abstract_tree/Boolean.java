package abstract_tree;

import org.omg.CORBA.PUBLIC_MEMBER;

public class Boolean {
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
