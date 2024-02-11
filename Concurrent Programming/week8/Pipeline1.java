import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Pipeline1 {
    public static void main(String[] args) throws Exception {
        String NO_FURTHER_INPUT1 = "";
        int NO_FURTHER_INPUT2 = -1;

        BlockingQueue<String> bq1 = new ArrayBlockingQueue<String>(1024);// TODO create the queue
        BlockingQueue<Integer>  bq2 =new ArrayBlockingQueue<Integer>(1024); // TODO create the queue

        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq1.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT1));
        });

        pool.submit(() -> {
            try {
                while (true) {
                    String item=bq1.take();
                    if(item.equals(NO_FURTHER_INPUT1)){
                        bq2.add(NO_FURTHER_INPUT2);
                        break;
                    }

                    bq2.add(item.length());
                    // TODO queue #1 ====> txt  len ===> queue #2
                    // TODO also handle NO_FURTHER_INPUTs
                }
                                    

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.submit(() -> {
            try {
                while (true) {
                    // TODO queue #2 ====> len ====> print it
                    // TODO also handle NO_FURTHER_INPUTs
                                        int item=bq2.take();
                                        if(item==NO_FURTHER_INPUT2)break;
                                        System.out.println(item);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
