package abstract_tree;

import visitors.Visitor;

public abstract class AbstractTree {
    public abstract Object accept(Visitor visitor);
}
