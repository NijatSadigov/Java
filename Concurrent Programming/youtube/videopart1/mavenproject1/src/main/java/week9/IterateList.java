/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week9;
import java.util.*;
/**
 *
 * @author sadiq
 */




public class IterateList {
    
    public static void NonSyncIterate(Collection<Integer> elements, int number){

for (Integer e:elements){
    System.out.println(e+ " "+ number);
}



}
    public static void syncIterate (Collection<Integer> elements, int number){
       synchronized (elements){
NonSyncIterate(elements,  number);
        
    }}

    
     public static void main(String args[]) {
Thread threads[] = new Thread[2];
List <Integer> elements = new ArrayList<>();
//List <Integer> elements = new LinkedList<>();
//Collection<Integer> elements = new Vector<>();

Collections.synchronizedCollection(elements);
Collections.synchronizedList(elements);



for(int i=0;i<2;i++){
    int finali=0;
    threads[i]=new Thread(()->{
    syncIterate(elements, finali);
    
    });
}


for(int i=0;i<2;i++)threads[i].start();
for(int i=0;i<10000;i++)elements.add(i);
try{
for(int i=0;i<2;i++)threads[i].join();

}

catch(InterruptedException e){

}











    }
    
    
    
    
    
    
}
