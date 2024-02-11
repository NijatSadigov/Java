
/**
 * There is a Krubi concert happening and people desperately want to get tickets to the best sectors.
 * There are 4 sectors in total, A, B, C and D, all of them will have 5 tickets so 20 customers will
 * be able to buy a ticket
 * Each customer has a preferred sector and is only willing to buy ticket for that sector
 */
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;//java.util.concurrent;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class TicketSaleSimulator {
    private static final int SLEEP_TIME_MIN = 100;
    private static final int SLEEP_TIME_MAX = 500;
    private static final int SHUTDOWN_TIME = 15000;

    private static final List<String> TICKET_TYPES = List.of("SECTOR A", "SECTOR B", "SECTOR C", "SECTOR D");

    private static final int TICKET_COUNT_PER_SELLER = 5;
    private static final int SELLER_COUNT = TICKET_TYPES.size();
    private static final int CUSTOMER_COUNT = SELLER_COUNT * TICKET_COUNT_PER_SELLER;

    private static Map<String, Integer> TICKET_INVENTORY =new HashMap<String,Integer>(); /* TODO Create a map for inventory */;
    private static BlockingQueue<String> TICKET_QUEUE = new ArrayBlockingQueue<String>(1);/* TODO Create a queue with 1 capacity */;

    public static void main(String[] args) {
        // Setting up the inventory
        TICKET_TYPES.forEach(str -> TICKET_INVENTORY.put(str, TICKET_COUNT_PER_SELLER));
        ExecutorService es= Executors.newFixedThreadPool(SELLER_COUNT+CUSTOMER_COUNT);
        // TODO Start a sellerAction for each ticket type on a separate thread
        for(int i=0;i<SELLER_COUNT;i++){int finali=i;
es.submit(()->{sellerAction(TICKET_TYPES.get(finali));});
        }
        // TODO Start a customerAction for each customer for a ticket type on a separate thread
        // TODO - There must be exactly 5 customers who want the same type of ticket

for(int i=0;i<CUSTOMER_COUNT;i++){int finali=i;
es.submit(()->{customerAction(finali,TICKET_TYPES.get(finali%SELLER_COUNT));});

        }

        // TODO Make the simulation stop after 15 seconds (SHUTDOWN_TIME)
        try{
                System.out.println("!");

        es.shutdown();
        es.awaitTermination(SHUTDOWN_TIME, TimeUnit.MILLISECONDS);
        }
        catch(Exception e){
System.out.println("?");

        }
    }

    /**
     * The seller who submits the ticket
     * Each ticket type has a separate seller assigned to it
     * Sellers take a ticket from the inventory and finish functioning once the inventory for
     * their ticket type is empty
     * @param ticketType The type of ticket its selling
     */
    private static void sellerAction(String ticketType) {
        // TODO As long as the seller's ticket type is not sold out according to TICKET_INVENTORY, do this:
        while(TICKET_INVENTORY.get(ticketType)>0)
{
        // TODO - Decrease the ticket count in TICKET_INVENTORY
        TICKET_INVENTORY.put(ticketType,TICKET_INVENTORY.get(ticketType)-1);
// TODO - Insert the appropriate ticket into TICKET_QUEUE
 try {
                TICKET_QUEUE.put(ticketType);
            } catch (InterruptedException e) {}// TODO - Print the following:
        System.out.println("New ticket available for " + ticketType);
    
        // TODO - After the sale, wait a minimum of SLEEP_TIME_MIN and a maximum of SLEEP_TIME_MAX
        try{
Thread.sleep( ThreadLocalRandom.current().nextInt(SLEEP_TIME_MIN, SLEEP_TIME_MAX));
        }
        catch(Exception e){


        }
   
}
        
         // TODO After the inventory no longer has this type of ticket, print out this and finish running:
        System.out.printf("Tickets for %s sold out%n", ticketType);


    }

    /**
     * A customer will always check the queue in which the tickets are being published
     * When the queue has a ticket of their type, they take it and stop looking for tickets
     * @param customerId Unique id of the customer
     * @param ticketType The only kind of ticket the customer is willing to buy
     */
    private static void customerAction(final int customerId, final String ticketType) {
        // TODO As long as the customer has no ticket, do the following:
        while (true){
                synchronized(TICKET_QUEUE){
        // TODO - Check TICKET_QUEUE for the desired type of ticket (using the peek method)
        String t=TICKET_QUEUE.peek();
        if(t!=null&&ticketType.equals(t)){
        // TODO - If the ticket is of the desired type, take it and attend the concert (finish execution)
        TICKET_QUEUE.remove();
                System.out.printf("Customer %d got a ticket to %s%n", customerId, ticketType);

                break;
        }

                }
        // TODO - After the check, wait a minimum of SLEEP_TIME_MIN and a maximum of SLEEP_TIME_MAX

try{
Thread.sleep( ThreadLocalRandom.current().nextInt(SLEEP_TIME_MIN, SLEEP_TIME_MAX));
        }
        catch(Exception e){


        }
        }
        
    }
}
