package Exo4notSync;

public class Airport {
    private String name;
    private ControlTower controlTower;

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

    public void useRunway(Airplane airplane, String operation) {
        System.out.println(airplane.getName() + " starts " + operation + ".");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
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
