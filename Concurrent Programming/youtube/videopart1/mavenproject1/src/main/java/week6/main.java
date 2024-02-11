/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package week6;

import java.util.stream.IntStream;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;



class ClientTask implements Runnable {
    private int clientId;

    public ClientTask(int clientId) {
        this.clientId = clientId;
    }

    @Override
    public void run() {
        // Task code here, e.g., processing a request
        System.out.println("Client " + clientId + " is being processed by thread " + Thread.currentThread().getName());
    }
}



public class main {
    

}
