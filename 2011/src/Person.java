import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class Person {
    String name;
    HashSet<Person> neighbours;
    HashMap<Conversation, Conversation> conversations;

    public Person(String name) {
        this.name = name;
        neighbours = new HashSet<>();
        conversations = new HashMap<>();
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Person p = (Person) o;
        return p.toString().equals(this.toString());
    }
}
