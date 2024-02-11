import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Pipeline0 {
    public static void main(String[] args) throws Exception {
        String NO_FURTHER_INPUT = "";

        BlockingQueue<String> bq = new ArrayBlockingQueue<String>(1024); // TODO create the queue

        ExecutorService pool = Executors.newCachedThreadPool();

        pool.submit(() -> {
            bq.addAll(List.of("a", "bb", "ccccccc", "ddd", "eeee", NO_FURTHER_INPUT));
        });

        pool.submit(() -> {
            try {
                while (true) {

                    String item= bq.take();
                    if(item.equals(NO_FURTHER_INPUT))break;

                    System.out.println(item);
                    // TODO queue #1 ====> txt ===> print it
                    // TODO also handle NO_FURTHER_INPUTs
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}
