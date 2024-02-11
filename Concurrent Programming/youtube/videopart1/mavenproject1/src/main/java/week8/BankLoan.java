/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
//18 october-25
package week8;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;

/**
 *
 * @author sadiq
 */
public class BankLoan {
    //
    public static void main (String Args[]){
        final int NumberOfThreads =10;
            AtomicInteger TotalLoan = new AtomicInteger();
            int ClientLoans[] = new int [NumberOfThreads];
        ArrayList <Future<Integer>> futures=new ArrayList<>();
        
    ExecutorService es= Executors.newFixedThreadPool(NumberOfThreads);
      for (int i=0;i<NumberOfThreads;i++){
          int finali=i;
       for (int j=0;j<10_000;j++)   {
           
      es.submit(()->{
      int LoanAmount =ThreadLocalRandom.current().nextInt(100, 1000+1);
      ClientLoans[finali]+=LoanAmount;
      
      TotalLoan.getAndAdd(LoanAmount);
      
      
      });
      
      
          }
      
      }
                es.shutdown();

      try{
      es.awaitTermination(NumberOfThreads, TimeUnit.SECONDS);
      }
      catch(InterruptedException e){};

    
    System.out.println(TotalLoan);
    
    }
    
    
    
    
}
