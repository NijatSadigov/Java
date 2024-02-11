/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author sadiq
 */
public class thread3 implements Runnable{

    @Override
    public void run() {
        System.out.println("Thread: " + Thread.currentThread().getName());
    }
    
    

      public static void main(String[] args) {
      ThreadGroup group = new ThreadGroup("My Thread Group");

        Thread t1 = new Thread(group, new thread3(), "Thread 1");
        Thread t2 = new Thread(group, new thread3(), "Thread 2");
        Thread t3 = new Thread(group, new thread3(), "Thread 3");

      t1.start();
            t2.start();
      t3.start();
      
      
      
      int activeCount = group.activeCount();
        System.out.println("Estimated active threads: " + activeCount);
        group.list();

      

      }
    
}
