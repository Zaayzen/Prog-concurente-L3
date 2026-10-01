package Exo2;

public class CompteurV1 implements Runnable{
    private int compteur;

    public CompteurV1(int compteur){
        this.compteur = compteur;
    }

    public synchronized void incrementer(){
        compteur++;
    }

    @Override 
    public void run(){
        for(int i = 0; i < 10000; i++){
            incrementer();
        }
    }

    public static void main(String[] args) throws Exception{
        CompteurV1 c = new CompteurV1(0);
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Compteur final: " + c.compteur);
    }
}
