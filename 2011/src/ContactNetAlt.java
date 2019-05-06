import java.util.HashSet;
import java.util.LinkedList;

public class ContactNetAlt {
    int connectionThreshold = 5;

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
            c.amount = sender.conversations.get(c).amount + 1; //adds 1 to conversation amount
            sender.conversations.put(c, c);
        }
        else {
            c.amount = 1; //not needed when constructor for Conversation sets it to 1
            sender.conversations.put(c, c);
        }
    }

    /**
     * returns whether person1 has close (direct) connection with a neighbour p2
     * @param p1 one person
     * @param p2 another person
     * @return true if amount >= threshold, else false
     */
    public boolean hasCloseConnectionAlt(Person p1, Person p2) {
        Conversation c1 = new Conversation(p1, p2);
        Conversation c2 = new Conversation(p2, p1);
        try {
            if (p1.conversations.get(c1).amount >= connectionThreshold
                    && p2.conversations.get(c2).amount >= connectionThreshold) {
                return true;
            }
        }
        catch (NullPointerException ex) {//one or both conversations was not found
            return false;
        }
        return false;
    }

    public LinkedList<Person> findDirectConnectionsAlt (Person suspect) {
        LinkedList<Person> connections = new LinkedList<Person>();
        for (Conversation c : suspect.conversations.keySet()) {
            Person neighbour = c.receiver;
            if (hasCloseConnectionAlt(suspect, neighbour)) {
                connections.add(neighbour);
            }
        }
        return connections;
    }

    /**
     * Finds connections with neighbours, and recursively runs the function on the neighbours' neighbours
     * @param suspect person we are finding close connections for
     * @param connections the current close connections we know about - for initial method call use an empty set or null
     * @return list of all people the suspect has a close connection with
     */
    public LinkedList<Person> findIndirectConnectionsAlt(Person suspect, HashSet<Person> connections) {
        connections = connections == null ? new HashSet<Person>() : connections;
        //for all neighbours
        for (Conversation c : suspect.conversations.keySet()) {
            Person neighbour = c.receiver;
            //do they have direct connection (above threshold)
            if (hasCloseConnectionAlt(suspect, neighbour)) {
                //if the set doesn't already contain them
                if (!connections.contains(neighbour)) {
                    //add them
                    connections.add(neighbour);
                    //check their neighbours
                    connections.addAll(findIndirectConnectionsAlt(neighbour, connections));

                    /*
                    //think this part is unnecessary if the recursive call is moved outside of loop
                    for (Person indirectNeighbour : neighbour.neighbours) {
                        if (hasCloseConnection(neighbour, indirectNeighbour)) {
                            //recursive call on their neighbours
                            if (!connections.contains(indirectNeighbour)) {
                                connections.add(indirectNeighbour);
                                connections.addAll(findIndirectConnections(indirectNeighbour, connections));
                            }
                        }
                    }
                    */
                }
            }
        }
        return new LinkedList<Person>(connections);
    }

    public void printDirectAlt(Person suspect) {
        System.out.println("Direct connections for \"" + suspect.name + "\"");
        System.out.println(findDirectConnectionsAlt(suspect));
    }

    public void printIndirectAlt(Person suspect) {
        System.out.println("Indirect connections for \"" + suspect.name + "\"");
        System.out.println(findIndirectConnectionsAlt(suspect, new HashSet<Person>()));
    }
}
