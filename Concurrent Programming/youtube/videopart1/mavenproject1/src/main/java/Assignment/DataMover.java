/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Assignment;
import java.util.ArrayList;
import java.util.List;

public class DataMover {
    public static int[] data;
    public static List<Thread> movers;
    public static int moveTime;
    private static final Object lock = new Object();

    public static void main(String[] args) {
        int numThreads = args.length > 0 ? args.length - 1 : 4;
        moveTime = args.length > 0 ? Integer.parseInt(args[0]) : 123;
        data = new int[numThreads];
        movers = new ArrayList<>();

        for (int i = 0; i < numThreads; i++) {
            data[i] = i * 1000;
int defaultSleepTimes[] = new int[]{111, 256, 404};
int sleepTime = args.length > i + 1 ? Integer.parseInt(args[i + 1]) : defaultSleepTimes[Math.min(i, defaultSleepTimes.length - 1)];            Thread thread = new Thread(new DataMoverThread(i, sleepTime));
            movers.add(thread);
            thread.start();
        }

        for (Thread thread : movers) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Print final array values
        for (int value : data) {
            System.out.println(value);
        }
    }

    static class DataMoverThread implements Runnable {
        private final int index;
        private final int sleepTime;

        DataMoverThread(int index, int sleepTime) {
            this.index = index;
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(sleepTime);

                    synchronized (lock) {
                        data[index] -= index;
                        System.out.println("#" + index + ": data " + index + " == " + data[index]);
                    }

                    Thread.sleep(moveTime);

                    synchronized (lock) {
                        int nextIndex = (index + 1) % data.length;
                        data[nextIndex] += index;
                        System.out.println("#" + index + ": data " + nextIndex + " -> " + data[nextIndex]);
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
