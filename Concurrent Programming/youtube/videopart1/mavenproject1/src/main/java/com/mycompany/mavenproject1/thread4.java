/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sadiq
 */
public class thread4 implements Runnable{
    public String threadName="";
    public thread4(String name){
    threadName=name;
    }
    
    @Override
    public void run() {
        try{
        for (int i=0;i<threadName.length();i++){
            System.out.print(threadName.charAt(i));
            Thread.sleep(200); // Sleep for 10 milliseconds
          // this.wait();
Thread.currentThread().interrupt();

        }}
        catch (InterruptedException e) {
            e.printStackTrace();
        }
                    System.out.println("\nready");

        
        }
public static void main(String[] args) {
Thread t1 = new Thread(new thread4("Hello"));
Thread t2 = new Thread(new thread4("World"));

t1.start();
    t2.start();
 
        try {
            t1.join();
           // t1.setPriority(Thread.);
            t2.join();

        } catch (InterruptedException ex) {
            Logger.getLogger(thread4.class.getName()).log(Level.SEVERE, null, ex);
        }

    System.out.println("Both threads have finished execution.");


    }
    
}
