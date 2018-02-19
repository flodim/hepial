import java.util.Objects;

public class Entry {
    private String name;

    public Entry(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Entry entry = (Entry) other;
        return Objects.equals(name, entry.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
