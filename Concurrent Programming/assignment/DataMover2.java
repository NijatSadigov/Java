import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Random;

public class DataMover2 {
    public static AtomicInteger arrivalCount = new AtomicInteger(0);
    public static AtomicInteger totalSent = new AtomicInteger(0);
    public static AtomicInteger totalArrived = new AtomicInteger(0);
    public static ExecutorService pool;
    public static List<BlockingQueue<Integer>> queues;
    public static List<Future<DataMover2Result>> moverResults;
    public static List<Integer> discards = new ArrayList<>();

    public static class DataMover2Result {
        public int count = 0;
        public int data = 0;
        public int forwarded = 0;
    }

    public static void main(String[] args) {
        int threadCount = args.length > 0 ? args.length : 4;
        int[] threadDelays = new int[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threadDelays[i] = args.length > 0 ? Integer.parseInt(args[i]) : new int[]{123, 111, 256, 404}[i];
        }

        queues = new ArrayList<>();
        for (int i = 0; i < threadCount; i++) {
            queues.add(new LinkedBlockingQueue<>());
        }

        pool = Executors.newFixedThreadPool(100);
        moverResults = new ArrayList<>();
        
        for (int i = 0; i < threadCount; i++) {
            int finalI = i;
            moverResults.add(pool.submit(() -> processTask(finalI, threadDelays[finalI], threadCount)));
        }

        pool.shutdown();
        try {
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow();
            }
        } catch (InterruptedException e) {
            pool.shutdownNow();
        }

        processResults(threadCount);
    }

    private static DataMover2Result processTask(int taskIndex, int delay, int n) throws InterruptedException {
        Random random = new Random();
        DataMover2Result result = new DataMover2Result();
        BlockingQueue<Integer> outputQueue = queues.get(taskIndex);
        BlockingQueue<Integer> inputQueue = queues.get((taskIndex + 1) % n);
        
        while (totalSent.get() < 5 * n) {
            int x = random.nextInt(10001);
            outputQueue.put(x);
            totalSent.incrementAndGet();
            System.out.println("total " + totalSent.get() + "/20 | #" + taskIndex + " sends " + x);

            Integer received = inputQueue.poll(random.nextInt(701) + 300, TimeUnit.MILLISECONDS);
            if (received != null) {
                if (received % n == taskIndex) {
                    arrivalCount.incrementAndGet();
                    result.count++;
                    result.data += received;
                    System.out.println("total " + totalSent.get() + "/20 | #" + taskIndex + " got " + received);
                } else {
                    outputQueue.put(received - 1);
                    result.forwarded++;
                    System.out.println("total " + totalSent.get() + "/20 | #" + taskIndex + " forwards " + (received - 1) + " [" + (received % n) + "]");
                }
            } else {
                System.out.println("total " + totalSent.get() + "/20 | #" + taskIndex + " got nothing...");
            }

            Thread.sleep(delay);
        }
        return result;
    }

    private static void processResults(int n) {
        for (Future<DataMover2Result> future : moverResults) {
            try {
                DataMover2Result result = future.get();
                totalArrived.addAndGet(result.data + result.forwarded);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (BlockingQueue<Integer> queue : queues) {
            while (!queue.isEmpty()) {
                discards.add(queue.poll());
            }
        }

        int totalDiscards = discards.stream().mapToInt(Integer::intValue).sum();
        System.out.println("discarded " + discards + " = " + totalDiscards);
        System.out.println("sent " + totalSent.get() + " === got " + totalArrived.get() + " = " + arrivalCount.get() + " + discarded " + totalDiscards);

        if (totalSent.get() != totalArrived.get() + totalDiscards) {
            System.out.println("WRONG sent " + totalSent.get() + " !== got " + totalArrived.get() + " = " + arrivalCount.get() + " + discarded " + totalDiscards);
        }
    }
}
