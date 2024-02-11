
import java.util.List;
import java.util.ArrayList;

public class SyncList {

    public static void main (String[] args) {

List <Integer> list = new ArrayList<>();
Thread threads[]= new Thread[2];

for(int k=0;k<2;k++){
    int finalk=k;
 threads[k] = new Thread(()->
{
for (int i=1-finalk;i<1_000_000;i+=2){
list.add(i);

}


});}
////

for (int k=0;k<2;k++)threads[k].start();
try {
   for (int k=0;k<2;k++)threads[k].join();

 
} catch (Exception e) {
    // TODO: handle exception
}

}
    
}