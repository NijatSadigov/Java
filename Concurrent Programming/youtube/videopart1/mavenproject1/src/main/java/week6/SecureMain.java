/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week6;

import java.util.stream.IntStream;

/**
 *
 * @author sadiq
 */
public class SecureMain {
    //atomic mini thread
    public int safeInt;
    public int get(){return safeInt;}
    public void set(int safeInt){this.safeInt=safeInt;}
    public SecureMain(){set(0);}
    public SecureMain(int safeInt){set(safeInt);}
    public int getAndIncrement(){
        int oldValue;
        synchronized(this){
    oldValue=get();
    set(get()+1);
    return oldValue;
    
    }
    }
       public int getAndDecrement(){
        int oldValue;
        synchronized(this){
    oldValue=get();
    set(get()-1);
    return oldValue;
    
    }
    }
    public int getAndAdd(int a){
        int oldValue;
        synchronized(this){
    oldValue=get();
    set(get()+a);
    return oldValue;
    
    }
    }
  
    
    
    
    public static void main(String args[]) throws InterruptedException {
    //System.out.print(3);
    Thread threads[]= new Thread [10];
    SecureMain tsmi = new SecureMain();
    for (int i=0;i<10;i++){
        
        
        threads[i]= new Thread(()->{
        IntStream.range(0, 10_000_000).forEach(x -> {tsmi.getAndIncrement();});
        
        
        
        }); }
    
    
        for (int i=0;  i<10;i++)      threads[i].start();
        for (int i=0;  i<10;i++)      threads[i].join();
        
        System.out.println(tsmi.get());
        
        
        
        
        

        
   
    
    }
    
    

    
}
