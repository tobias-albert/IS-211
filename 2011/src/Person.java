import java.util.HashMap;
import java.util.HashSet;

public class Person {
    String name;
    HashSet<Person> neighbours; //used by ContactNet
    HashMap<Conversation, Integer> conversations; //used by ContactNetAlt

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
        if (o == this) return true; //compared to self
        if (o.getClass() != this.getClass()) return false; //compared to object of other class
        Person p = (Person) o; //casts Object to type Person
        return p.toString().equals(this.toString());
    }
}
