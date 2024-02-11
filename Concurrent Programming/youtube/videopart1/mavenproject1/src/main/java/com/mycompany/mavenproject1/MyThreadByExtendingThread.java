/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;
import java.io.*;

/**
 *
 * @author sadiq
 */
public class MyThreadByExtendingThread extends Thread{
    public String threadName="";
    public MyThreadByExtendingThread(String name){
    threadName=name;
    }
    
    @Override
    public void run(){
         try {
                         PrintWriter writer = new PrintWriter("output.txt", "UTF-8");

            for (int i = 0; i < threadName.length(); i++) {
                //System.out.print(threadName.charAt(i) );
                
                
                writer.print(threadName.charAt(i));
                // Sleep for a bit to simulate some work being done
                Thread.sleep(500);
            }
            writer.flush();
         writer.close();
         
        } catch (InterruptedException e) {
            System.out.println(threadName + " was interrupted.");
        }
         catch (IOException e) {
            e.printStackTrace();
        }
         
         
         
        System.out.println(threadName + " has finished executing.");
    }
        
    
public static void main(String[] args) {
        // Create two instances of the MyThread class
        MyThreadByExtendingThread thread1 = new MyThreadByExtendingThread("Hello");
        MyThreadByExtendingThread thread2 = new MyThreadByExtendingThread("World");

        // Start the threads
        thread1.start();
        thread2.start();
        
                System.out.println(" has finished executing.");

    }
    
    
}
