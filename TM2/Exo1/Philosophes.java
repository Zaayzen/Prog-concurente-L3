package Exo1;
public class Philosophes implements Runnable{
    
    private Object leftFork;
    private Object rightFork;

    public Philosophes(Object leftFork, Object rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    private void doAction(String action) throws InterruptedException{
        IO.println(Thread.currentThread().getName() + " " + action);
        Thread.sleep(((int) (Math.random() * 100)));
    }
    
    @Override 
    public void run() {
        try{
            while(true){
                doAction(System.nanoTime()+ ": Thinking");
                synchronized(leftFork){
                    doAction(System.nanoTime()+ ": Picked up left fork");
                    synchronized(rightFork){
                        doAction(System.nanoTime()+ ": Picked up right fork - eating"); 
                        doAction(System.nanoTime()+ ": Put down right fork");
                    }
                    doAction(System.nanoTime()+ ": Put down left fork. Back to thinking");
                }
            }
        }catch(InterruptedException e){
            Thread.currentThread().interrupt();
            return;
        }
    }

    public static void main() throws Exception{
        Philosophes[] philosophers = new Philosophes[5];
        Object[] forks = new Object[philosophers.length];
        for(int i = 0; i < forks.length; i++){
            forks[i] = new Object();
        }

        for (int i = 0; i < philosophers.length; i++){
            Object leftFork = forks[i];
            Object rightFork = forks[(i + 1) % forks.length];

            philosophers[i] = new Philosophes(leftFork, rightFork);
            Thread t = new Thread(philosophers[i], "Philosopher " + (i + 1));
            t.start();
        }
    }
}