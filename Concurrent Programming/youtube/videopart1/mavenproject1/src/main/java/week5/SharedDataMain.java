import java.util.ArrayList;
import java.util.List;

public class SharedDataMain {
    List<Integer> list = new ArrayList<>();
    static Thread threads[] = new Thread[2]; // Make the array static
    
    class MyThread implements Runnable {
        int start;
        public MyThread(int s) { start = s; }
        @Override
        public void run() {
            
            synchronized(list){
            
            for (int i = start; i < 1000; i++) {
                list.add(i);
            }
            
            }
            
        }
    }
    
    public static void main(String args[]) {
        SharedDataMain mainObj = new SharedDataMain(); // Instance of the outer class

        for (int k = 0; k < 2; k++) {
            threads[k] = new Thread(mainObj.new MyThread(k)); // Create Thread objects
        }
        
        // Optionally, start the threads
        for (int k = 0; k < 2; k++) {
            threads[k].start();
        }
      
        
         for (int k = 0; k < 2; k++) {
        try {
            threads[k].join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
        
        
        
    System.out.println(mainObj.list.size());
       
    }
}