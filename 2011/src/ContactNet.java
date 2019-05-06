import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ContactNet {

    HashMap<Conversation, Integer> conversations;
    int connectionThreshold = 5;

    public ContactNet() {
        conversations = new HashMap<>();
    }

    /**
     * A conversation takes place, adds the new amount value to the conversation,
     * adds the conversation to the conversations map
     * or adds it initially and also adds it to sender's neighbour list
     * @param sender sender
     * @param receiver receiver
     */
    public void registerConversation(Person sender, Person receiver) {
        Conversation c = new Conversation(sender, receiver);
        if (conversations.containsKey(c)) {
            int amount = conversations.get(c);
            conversations.put(c, amount + 1); //adds 1 to conversation amount
        }
        else {
            conversations.put(c, 1);
            sender.neighbours.add(receiver);
        }
    }

    /**
     * returns whether person1 has close (direct) connection with a neighbour p2
     * @param p1 one person
     * @param p2 another person
     * @return true if amount >= threshold, else false
     */
    public boolean hasCloseConnection(Person p1, Person p2) {
        Conversation c1 = new Conversation(p1, p2);
        Conversation c2 = new Conversation(p2, p1);
        try {
            if (conversations.get(c1) >= connectionThreshold && conversations.get(c2) >= connectionThreshold) {
                return true;
            }
        }
        catch (NullPointerException ex) { //one or both conversations was not found
            return false;
        }
        return false;
    }

    public LinkedList<Person> findDirectConnections (Person suspect) {
        LinkedList<Person> connections = new LinkedList<Person>();
        for (Person neighbour : suspect.neighbours) {
            if (hasCloseConnection(suspect, neighbour)) {
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
    public LinkedList<Person> findIndirectConnections(Person suspect, HashSet<Person> connections) {
        connections = connections == null ? new HashSet<Person>() : connections;
        //for all neighbours
        for (Person neighbour : suspect.neighbours) {
            //do they have direct connection (above threshold)
            if (hasCloseConnection(suspect, neighbour)) {
                //if the set doesn't already contain them
                if (!connections.contains(neighbour)) {
                    //add them
                    connections.add(neighbour);
                    //check their neighbours
                    connections.addAll(findIndirectConnections(neighbour, connections));

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

    public void printDirect(Person suspect) {
        System.out.println("Direct connections for \"" + suspect.name + "\"");
        System.out.println(findDirectConnections(suspect));
    }

    public void printIndirect(Person suspect) {
        System.out.println("Indirect connections for \"" + suspect.name + "\"");
        System.out.println(findIndirectConnections(suspect, new HashSet<Person>()));
    }




}
