public class q3 implements Runnable{
    public static int x = 0;

    @Override 
    public void run(){
        for(int i = 0; i < 1000; i++){
            x++;
        }
        IO.print(Thread.currentThread().getName() + " a fini x = " + x);
    }

    public static void main(){
        Runnable task = new q3();
        Thread t1 = new Thread(task, "T1");
        Thread t2 = new Thread(task, "T2");
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();  // bonne pratique : restaurer le flag d'interruption
            e.printStackTrace();
        }

        IO.println("Valeur finale de x = " + x );
    }
}