import java.util.Comparator;

public class EventComparator implements Comparator<Event> {
    public EventComparator() {

    }

    @Override
    public int compare (Event e1, Event e2) {
        return e1.getTime() - e2.getTime();
    }
}
