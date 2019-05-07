import java.util.HashSet;
import java.util.LinkedList;

public class ContactNetAlt {

    public ContactNetAlt() {
    }

    /**
     * A conversation takes place, adds the new amount value to the conversation,
     * puts conversation in sender's neighbour list
     * @param sender sender
     * @param receiver receiver
     */
    public void registerConversationAlt(Person sender, Person receiver) {
        Conversation c = new Conversation(sender, receiver);
        if (sender.conversations.containsKey(c)) {
            int amount = sender.conversations.get(c) + 1; //adds 1 to conversation amount
            sender.conversations.put(c, amount);
        }
        else {
            sender.conversations.put(c, 1);
        }
    }

    /**
     * returns whether person1 has close (direct) connection with a neighbour p2
     * @param p1 one person
     * @param p2 another person
     * @param threshold amount of conversation required for close connection
     * @return true if amount >= threshold, else false
     */
    public boolean hasCloseConnectionAlt(Person p1, Person p2, int threshold) {
        Conversation c1 = new Conversation(p1, p2);
        Conversation c2 = new Conversation(p2, p1);
        try {
            if (p1.conversations.get(c1) >= threshold
                    && p2.conversations.get(c2) >= threshold) {
                return true;
            }
        }
        catch (NullPointerException ex) {//one or both conversations was not found
            return false;
        }
        return false;
    }

    /**
     * Gets a person's close connections with their neighbours
     * @param suspect person we are finding close connections for
     * @param threshold amount of conversation required for close connection
     * @return list of all people the suspect has a close connection with
     */
    public LinkedList<Person> findDirectConnectionsAlt (Person suspect, int threshold) {
        LinkedList<Person> connections = new LinkedList<Person>();
        for (Conversation c : suspect.conversations.keySet()) {
            Person neighbour = c.receiver;
            if (hasCloseConnectionAlt(suspect, neighbour, threshold)) {
                connections.add(neighbour);
            }
        }
        return connections;
    }

    /**
     * Finds connections with neighbours
     * @param suspect person we are finding close connections for
     * @param threshold the amount of interacts required for having a close connection
     * @return list of all people the suspect has a close connection with
     */
    public LinkedList<Person> findIndirectConnectionsAlt(Person suspect, int threshold) {
        Person root = suspect;
        HashSet<Person> connections = new HashSet<Person>();
        return getIndirectConnectionsAlt(suspect, root, connections, threshold);
    }

    /**
     * Gets connections with neighbours, and recursively runs the function on the neighbours' neighbours
     * @param suspect person we are finding close connections for
     * @param root the person the method was initially called on
     * @param connections the current close connections we know about - for initial method call use an empty set or null
     * @param threshold amount of conversations required for close connection
     * @return list of all people the suspect has a close connection with
     */
    public LinkedList<Person> getIndirectConnectionsAlt(Person suspect, Person root, HashSet<Person> connections, int threshold) {
        //for all neighbours
        for (Conversation c : suspect.conversations.keySet()) {
            Person neighbour = c.receiver;
            if (neighbour.equals(root)) continue;
            //do they have direct connection (above threshold)
            if (hasCloseConnectionAlt(suspect, neighbour, threshold)) {
                //if the set doesn't already contain them
                if (!connections.contains(neighbour)) {
                    //add them
                    connections.add(neighbour);
                    //check their neighbours
                    connections.addAll(getIndirectConnectionsAlt(neighbour, root, connections, threshold));
                }
            }
        }
        return new LinkedList<Person>(connections);
    }

    public void printDirectAlt(Person suspect, int threshold) {
        System.out.println("Direct connections for \"" + suspect.name + "\"");
        System.out.println(findDirectConnectionsAlt(suspect, threshold));
    }

    public void printIndirectAlt(Person suspect, int threshold) {
        System.out.println("Indirect connections for \"" + suspect.name + "\"");
        System.out.println(findIndirectConnectionsAlt(suspect, threshold));
    }
}
