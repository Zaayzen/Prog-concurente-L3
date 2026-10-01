package Exo4Sync;

public class ControlTower {
    private Airport airport;

    void setAirport(Airport airport) {
        this.airport = airport;
    }

    public synchronized void requestLanding(Airplane airplane) {
        System.out.println("Tower authorizes " + airplane.getName() + " to land.");
        airport.useRunway(airplane, "landing");
    }

    public synchronized void requestTakeoff(Airplane airplane) {
        System.out.println("Tower authorizes " + airplane.getName() + " to take off.");
        airport.useRunway(airplane, "takeoff");
    }
}
