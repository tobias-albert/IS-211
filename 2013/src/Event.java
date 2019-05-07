public class Event implements Comparable<Event> {
    int t; // tidspunktet når hendelsen skal skje
    Bus bus; // bussen som stopper
    int stopNo; //stoppested nr
    public Event(int t, Bus bus, int stop) {
        this.t = t;
        this.bus = bus;
        this.stopNo = stop;
    }
    public int getTime() {
        return t;
    }

    @Override
    public int compareTo(Event e) {
        return this.getTime() - e.getTime();
    }
    // I denne simuleringen har vi bare en type Event, en buss som stopper på
    // et stoppested. Det er lagt inn noen utskrifter her, så det er mulig å se
    // konsekvensen av endringer, f.eks. sette opp flere busser, større busse osv.
    public void happen() {
        BusSim sim = BusSim.instance; // få tak i BusSim objektet
        if (stopNo < sim.NUM_STOPS-1) {

            // ikke ved endestasjonen
            // bussen kan ta med minimum av de som venter på holdepl og ledige seter
            int n = Math.min(sim.stop[stopNo], sim.MAX_PASS - bus.numPass);
            System.out.format("Buss %d tok opp %d pass fra holdepl. %d.\n",
                    bus.id, n, stopNo);
            bus.numPass = bus.numPass + n;
            sim.stop[stopNo] = sim.stop[stopNo] - n;
            if (sim.stop[stopNo] > 0)
                System.out.println(sim.stop[stopNo]+" pass. står igjen\n");
            // når bussen kommer til neste stop
            int eta = t + sim.timeToNextStop();
            // lag ny hendelse for neste stop
            Event e = new Event(eta, bus, stopNo + 1);
            // og gi den til BusSim objektet.
            sim.addEvent(e);
        }
        else System.out.format("Buss %d kom til endestasj med %d pass\n",
                bus.id, bus.numPass);
    }
}