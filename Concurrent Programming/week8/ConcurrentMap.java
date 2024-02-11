
import java.util.concurrent.*;
public class ConcurrentMap{



public static void main(String Args[] ){
    java.util.concurrent.ConcurrentMap<Long, String> meetings = new ConcurrentHashMap<>();
  //Map<Long,String> meetings = Collections.synchronizedMap(new HashMap<Long,String>());

ExecutorService es =Executors.newFixedThreadPool(10+10+1);
long StartTime=System.currentTimeMillis();
for (int i = 0; i < 10; i++) {
    int finali = i;
    es.submit(() -> {
        for (int j = 0; j < 5000; j++) {
            Long r = ThreadLocalRandom.current().nextLong(StartTime, StartTime+5000*10+1);
            Long check = r;
            synchronized (meetings) {
                for (check = r; check < r + 1; check++) {
                    if (meetings.containsKey(check)) break;
                }
                if (check == r + 1)
                    meetings.put(r, "Meeting " + (finali*5000+j));
                else j--;
            } 
        }
    });
}
System.out.println("1st phase done");
for(int i=0;i<10;i++){

es.submit((()->{

for (long j=0;j<2500;j++){
    synchronized(meetings){
for (Long l : meetings.keySet()) {
    meetings.remove(l);
    break;
}}
//meetings.put(j,"Meeting id: "+j  );


}


}));





}


System.out.println("2nc phase done");



Future<?> f = es.submit(() -> {
    while (true) {
        Long currentTime = System.currentTimeMillis();
        if (currentTime > StartTime + 50*1000) break;
        for (Long k = currentTime; k < currentTime + 50*1000; k++) {
            if (meetings.containsKey(k)) {
                System.out.println("Next Meeting: " + meetings.get(k) + " @ " + k);
            }
        }
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {}
    }
});
System.out.println("3rd phase done");

try {
    f.get();
} catch (InterruptedException|ExecutionException  e) {
    System.out.println("problem");

    es.shutdownNow();
}
System.out.println("4rd phase done");

  try {
            es.awaitTermination(5000, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

        }

}









}