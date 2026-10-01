public class q1 extends Thread{
    private final char c;
    
    public q1(char c){
        this.c = c;
    }


    @Override 
    public void run(){
        for(int i = 0; i < 10; i++){
            IO.print(c);
            try {
                Thread.sleep((long) (Math.random() * 100));
            } catch (Exception e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public static void main(){
        for(int i = 0; i < 10; i++){
            Runnable task = new q1((char) ('A' + i));
            Thread t = new Thread(task);
            t.start();
        }
    }
}
