public class Conversation {
    Person sender;
    Person receiver;
    //int amount; //used by neither implementation at the moment

    Conversation(Person sender, Person receiver) {
        this.sender = sender;
        this.receiver = receiver;
        //this.amount = 1;
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
        if (o == this) return true; //compared to self
        if (o.getClass() != this.getClass()) return false; //compared to object of other class
        Conversation c = (Conversation) o; //casts Object to type Conversation
        return c.toString().equals((this.toString()));
    }
}
