import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class ContactNet {

    HashMap<Conversation, Integer> conversations;
    int connectionThreshold = 5;

    public int setThreshold(int connectionThreshold) {
        return this.connectionThreshold = connectionThreshold;
    }

    public ContactNet() {
        conversations = new HashMap<>();
    }

    public void registerConversation(Person sender, Person receiver) {
        boolean alreadyExists = false;
        Conversation c = new Conversation(sender, receiver);
        if (conversations.containsKey(c)) {
            int amount = conversations.get(c);
            conversations.put(c, amount + 1);
        }
        else {
            conversations.put(c, 1);
            sender.neighbours.add(receiver);
        }
    }

    public boolean hasDirectConnection (Person p1, Person p2) {
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
            if (hasDirectConnection(suspect, neighbour)) {
                connections.add(neighbour);
            }
        }
        return connections;
    }

    /**
     *
     * @param suspect person we are finding connections for
     * @param connections the current connections we know about - for initial method call use an empty set or null
     * @return
     */
    public LinkedList<Person> findIndirectConnections(Person suspect, HashSet<Person> connections) {
        connections = connections == null ? new HashSet<Person>() : connections;
        //for all neighbours
        for (Person neighbour : suspect.neighbours) {
            //do they have direct connection (above threshold)
            if (hasDirectConnection(suspect, neighbour)) {
                //if the set doesn't already contain them
                if (!connections.contains(neighbour)) {
                    //add them
                    connections.add(neighbour);
                    //check their neighbours
                    connections.addAll(findIndirectConnections(neighbour, connections));

                    /*
                    //think this part is unnecessary if the recursive call is moved outside of loop
                    for (Person indirectNeighbour : neighbour.neighbours) {
                        if (hasDirectConnection(neighbour, indirectNeighbour)) {
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
