import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Random;

public class BusSim {
    // Sim parametere - disse kan endres på for å teste ut forskjellige scenarier
    public static final int NUM_STOPS = 10; // antall stoppesteder
    public static final int NUM_BUSES = 3; // antall busser
    public static final int MAX_PASS = 40;
    public static final int TIME_BETWEEN_STOPS = 10; // kjøretid mellom stops
    public static final int MAX_DELAY = 5; // max forsinkelse mellom to stop
    // global peker til det eneste BusSim objektet
    public static final BusSim instance = new BusSim();
    // antal passasjerer som venter på hver holdeplass
    int[] stop = new int[NUM_STOPS];
    public final Random random = new Random();
    //// Oppgave 1a datastruktur…
    public PriorityQueue<Event> events;
    private int t; // “Klokka”

    public static void main(String[] args) {
        instance.run();
    }

    // Dette er motoren i simuleringen den behandler Event-ojektene i rekkefølge
    // basert på tidspunkt. Når et Event blir utført kan det bli lagt til nye.
    public void run() {
        setup();
        while (!events.isEmpty() /* opg 1b er det flere ubehandle Events igjen */) {
            Event e = nextEvent();
            t = e.getTime(); // hopp fram I tid til hendelsen
            e.happen(); // og utfør den…
        }
    }

    private void setup() {
        /* opg 1b */
        // grovt forenklede eksempeldata – lag noen busser og sett 15 passasjerer
        // på hver holdeplass
        events = new PriorityQueue<>();//new EventComparator());
        for (int i = 0; i < NUM_BUSES; i++) {
            Bus b = new Bus(i);
            Event e = new Event(i * 30, b, 0);
            addEvent(e);
        }
        for (int i = 0; i < NUM_STOPS; i++) stop[i] = 15;
    }

    // Legg til et event-objekt på vent til det skal behandles
    public void addEvent(Event e) { // opg 1c
        events.add(e);
    }

    // ta ut neste Event I tidsrekkefølge
    public Event nextEvent() { // opg 1c
        return events.poll();
    }

    // Beregn hvor lang tid en buss bruker til neste stopp
    // Metoden legger en tilfeldig forsinkelse til kjøretiden for å simulere at noen
    // busser er forsinket.
    public int timeToNextStop() {
        return TIME_BETWEEN_STOPS + random.nextInt(MAX_DELAY);
    }
}