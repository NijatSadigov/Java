import java.lang.Runtime;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
public class BitcoinMiner {



    public static void main(String[] args) throws Exception {
        
        ExecutorService es ;
        BlockingQueue<Long> bq ;
        List <Future<Long>> tasks;

        //TODO: get logical processor count (minus one) and print it
        int numberOfThreads=Runtime.getRuntime().availableProcessors()-1;
        System.out.println(numberOfThreads);

        //TODO: create a thread pool with this many threads
        es = Executors.newFixedThreadPool( numberOfThreads);
        //TODO: create BlockingQueue for 256 * logical processors
          bq = new ArrayBlockingQueue<>(256*numberOfThreads);


        //TODO: create a list to store Future<Long>
        tasks = new ArrayList<>();

        createHashCheckers(numberOfThreads,es,bq,tasks);
        sendNonces(bq,tasks);
        finishUp(numberOfThreads, es,bq);
    }

    static void createHashCheckers(int coreCount,ExecutorService es, BlockingQueue<Long> bq, List<Future<Long>> tasks) {
        for (int i = 0; i < coreCount; i++) {
            tasks.add(es.submit(() -> createHashChecker(bq)));
        }
    }

    private static Long createHashChecker( BlockingQueue<Long> bq) {
        //TODO: add a task to the pool that does the following
                    //TODO: each task makes a copy of BlockChainData.block815296
     BlockChainData data = BlockChainData.getDataForBlock815296();
        while (true) {
            try {
                Long nonce = bq.take();
                if (nonce==Utils.SENTINEL) return Utils.NOT_FOUND_SOLUTION;
                if (data.tryBitcoinHash(nonce)) return nonce;
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return Utils.NOT_FOUND_SOLUTION;
            }
        }

    }
           

           // return Utils.NOT_FOUND_SOLUTION;
    




    static void sendNonces(BlockingQueue<Long> bq,List<Future<Long>>tasks) throws Exception {
        //TODO: remember the starting time
                long StartTime = System.currentTimeMillis();

        for (long nonce = Utils.NONCE_RANGE_START; nonce < Utils.NONCE_RANGE_END; nonce++) {
            //TODO: send the current nonce in the queue for checking
                bq.put(nonce);
            // Note: after we've sent enough nonces...
            if ((nonce % Utils.STEPS_BETWEEN_CHECKS) == 0) {
                // ... we check if we've got the solution
               
                //     ... if we have, we're done
                            if (getSolution(tasks)){

                // ... and we print the hash rate
                Utils.printHashRate(nonce, System.currentTimeMillis()-StartTime);
                return;
                
                            }


            }
            /*
            if (getSolution(tasks)) break;
            queue.put(nonce);
            if ((nonce % Utils.STEPS_BETWEEN_CHECKS) == 0) {
                Utils.printHashRate(nonce, System.nanoTime() - startTime);
            }
            */
        }



        
    }

    private static void finishUp(int numberOfThreads,ExecutorService es, BlockingQueue<Long> bq) {

        sendSentinels(numberOfThreads,bq);
        finishUp( es);
    }

    static void sendSentinels(int numberOfThreads, BlockingQueue<Long> bq) {
        //TODO: empty the queue
         // int numberOfThreads=Runtime.getRuntime().availableProcessors()-1;

        bq.clear();
        //TODO: then send as many SENTINEL values into the queue as there are cores
        for (int i=0;i<numberOfThreads;i++)
            bq.add(Utils.SENTINEL);
    }

    static void finishUp(ExecutorService es) {
        //TODO: gracefully stop all tasks from working
        
            es.shutdown();
      
        try {
            if (!es.awaitTermination(60, TimeUnit.SECONDS)) {
                es.shutdownNow();
            }
        } catch (InterruptedException ex) {
            es.shutdownNow();
            Thread.currentThread().interrupt();
        }
       





    }
/*
    static boolean checkFoundSolution(List<Future<Long>> tasks) {
        try {
            long solution = getSolution(tasks);
            if (solution != Utils.NOT_FOUND_SOLUTION) {
                Utils.printSolution(solution);
                return true;
            }
        } catch (Exception e) {}

        return false;
    }
*/
    static boolean getSolution(List<Future<Long>> tasks) throws InterruptedException, ExecutionException {
        //TODO: if any of our tasks is done, we return the solution that it has produced for us
         for (Future<Long> task : tasks) {
            if (task.isDone()) {
                try {
                    Long solution = task.get();
                    if (solution != Utils.NOT_FOUND_SOLUTION) {
                        Utils.printSolution(solution);
                        return true;
                    }
                } catch (InterruptedException | ExecutionException e) {}
            }
        }
        //TODO: otherwise:
        return false;
        //TODO: otherwise:
        //return Utils.NOT_FOUND_SOLUTION;
    }
}
