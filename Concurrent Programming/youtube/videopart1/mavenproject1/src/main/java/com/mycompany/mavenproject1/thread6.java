/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sadiq
 */
public class thread6 implements Runnable {
    final Integer a= 2;
    
    
    public static void main(String[] args) {
        int numberOfThreads=10;
     Thread[] threads = new Thread[numberOfThreads];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new thread6(), "Thread-" + i);
            threads[i].start();
            System.out.println("DONE");
            
            
        }

    }

    @Override
    public void run() {synchronized (a){
 try {
            PrintWriter writer = new PrintWriter(new FileWriter("numbers.txt"),true);

            for (int i = 1; i <= 1_000; i++) {
                writer.println(Thread.currentThread().getName()+" :"+ i);
                Thread.sleep(100);
            }

            writer.close();
            
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException ex) {
            Logger.getLogger(thread6.class.getName()).log(Level.SEVERE, null, ex);
        }    }
    
    
    
    }
    
}
