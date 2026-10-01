package Exo4notSync;

public class Airport {
    private String name;
    private ControlTower controlTower;
    private int airplanesOnRunway;

    public Airport(String name, ControlTower controlTower) {
        this.name = name;
        this.controlTower = controlTower;
        controlTower.setAirport(this);
    }

    public String getName() {
        return name;
    }

    public ControlTower getControlTower() {
        return controlTower;
    }

    void useRunway(Airplane airplane, String operation) {
        airplanesOnRunway++;
        if (airplanesOnRunway > 1) {
            System.out.println("CRASH at " + name + ": " + airplane.getName()
                    + " is on the runway with another airplane!");
        }

        System.out.println(airplane.getName() + " starts " + operation + ".");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            airplanesOnRunway--;
        }
        System.out.println(airplane.getName() + " completed " + operation + ".");
    }

    public static void main(String[] args) throws InterruptedException {
        ControlTower tower = new ControlTower();
        new Airport("TM2 Airport", tower);
        Thread[] airplanes = new Thread[10];

        for (int i = 0; i < airplanes.length; i++) {
            airplanes[i] = new Thread(
                    new Airplane("Airplane-" + (i + 1), tower, i % 2 == 0));
        }

        for (Thread airplane : airplanes) {
            airplane.start();
        }

        for (Thread airplane : airplanes) {
            airplane.join();
        }
    }
}
