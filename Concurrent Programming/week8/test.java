import java.lang.Runtime;

import java.util.concurrent.Executors;
import java.util.concurrent.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.List;
import java.util.ArrayList;


public class test {
    //  TODO: Bonus answer:
    public static void main(String[] args) throws Exception {
        //TODO: get logical processor count (minus one) and print it
System.out.println(Runtime.getRuntime().availableProcessors());
        //TODO: create a thread pool with this many threads
      int numberOfThreads=Runtime.getRuntime().availableProcessors();
        System.out.println(numberOfThreads-1);
          //BlockingQueue<long> bq = new ArrayBlockingQueue<>(256*numberOfThreads);

        //TODO: create a thread pool with this many threads
ExecutorService es = Executors.newFixedThreadPool( numberOfThreads);


          BlockingQueue<Long> bq = new ArrayBlockingQueue<>(256*numberOfThreads);

        //TODO: create a list to store Future<Long>
        List <Future> allFutures = new ArrayList<>();

    }
    
    
    
    }