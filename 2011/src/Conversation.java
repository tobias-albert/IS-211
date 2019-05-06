public class Conversation {
    Person sender;
    Person receiver;
    int amount;

    Conversation(Person sender, Person receiver) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = 1;
    }

    @Override
    public String toString() {
        return "{s: " + this.sender + " | r: " + this. receiver + " }";
    }

    @Override
    public int hashCode() {
            /*
            int a = Math.abs(this.sender.hashCode()/997300);
            int b = Math.abs(this.receiver.hashCode()/997300);
            return Integer.valueOf("" + a + b);
            */
        return this.toString().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) return true;
        if (o.getClass() != this.getClass()) return false;
        Conversation c = (Conversation) o;
        return c.toString().equals((this.toString()));
    }
}
