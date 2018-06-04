package symbole_table;

import abstract_tree.Idf;

import java.util.Objects;

public class Entry {
    private Idf id;

    public Entry(Idf id) {
        this.id = id;
    }

    public Idf getId() {
        return id;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Entry entry = (Entry) other;
        return Objects.equals(this.id.getName(), entry.id.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id.getName());
    }
}
