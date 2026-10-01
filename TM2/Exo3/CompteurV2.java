package Exo3;

import java.util.ArrayList;
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
            if (compteur % 1000 == 0) {
                    IO.println("Thread " + Thread.currentThread().getName() + " incremented compteur to " + compteur);
                }
                
        }
    }

    public static void main() throws Exception{
        CompteurV2 c = new CompteurV2(0);

        ArrayList<Thread> threads = new ArrayList<>();

        for(int i = 0; i < 50; i++){
            Thread t = new Thread(c);
            threads.add(t);
        }

        for(Thread t : threads){
            t.start();
            
        }
        for(Thread t : threads){
            t.join();
        }
        
        System.out.println("Compteur final: " + c.compteur);
    }
}
