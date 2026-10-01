package Exo3;

import java.util.ArrayList;

public class CompteurV1 implements Runnable{
    private int compteur;

    public CompteurV1(int compteur){
        this.compteur = compteur;
    }

    public synchronized void incrementer(){
        compteur++;
        if(compteur % 1000 == 0) {
            IO.println("Thread " + Thread.currentThread().getName() + " incremented compteur to " + compteur);
        }
    }

    @Override 
    public void run(){
        for(int i = 0; i < 10000; i++){
            incrementer();
        }
    }

    public static void main(String[] args) throws Exception{
        CompteurV1 c = new CompteurV1(0);
        
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
