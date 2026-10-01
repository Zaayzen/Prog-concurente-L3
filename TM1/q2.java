public class q2 implements Runnable{

    @Override 
    public void run(){
        for(int i = 0; i < 1000; i++)
            IO.println(Thread.currentThread().getName() + " : " + i);
    }

    public static void main(){
        Runnable task = new q2();
        new Thread(task, "T1").start();
        new Thread(task, "T2").start();
    }
}

