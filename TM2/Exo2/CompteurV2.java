package Exo2;


public class CompteurV2 implements Runnable{
    
    private int compteur;
    private Object moniteur = new Object();
    
    public CompteurV2(int compteur){
        this.compteur = compteur;
    }

    @Override 
    public void run(){
        for(int i = 0; i < 10000; i++){
            synchronized(moniteur){
                compteur++;
            }
        }
    }

    public static void main() throws Exception{
        CompteurV2 c = new CompteurV2(0);
        Thread t1 = new Thread(c);
        Thread t2 = new Thread(c);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Compteur final: " + c.compteur);
    }
}
