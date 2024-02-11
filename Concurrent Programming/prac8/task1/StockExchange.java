package task1;import java.util.Collections;
import java.util.*;
import java.util.Random;

public class StockExchange {
    private final Map<String, Stock> stocks = Collections.synchronizedMap(new HashMap<>());

    public StockExchange() {
        stocks.put("ABC", new Stock("ABC", 100.0));
        stocks.put("XYZ", new Stock("XYZ", 100.0));
        stocks.put("DEF", new Stock("DEF", 100.0));
    }

    public synchronized void updateStockPrice(String stockSymbol, boolean isBuying) {
        Stock stock = stocks.get(stockSymbol);
        double priceChangeFactor = isBuying ? 1.01 : 0.99;
        stock.setPrice(stock.getPrice() * priceChangeFactor);
    }

    public void displayStockPrices() {
        stocks.forEach((symbol, stock) ->
            System.out.printf("%s: %.2f\n", symbol, stock.getPrice()));
        System.out.println();
    }

    public static void main(String[] args) {
        StockExchange exchange = new StockExchange();
        for (int i = 0; i < 100; i++) {
            new Broker(exchange).start();
        }
        new PricePrinter(exchange).start();
    }
}

class Stock {
    private final String symbol;
    private double price;

    public Stock(String symbol, double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}

class Broker extends Thread {
    private final StockExchange exchange;
    private final Random random = new Random();

    public Broker(StockExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void run() {
        String[] symbols = {"ABC", "XYZ", "DEF"};
        for (int i = 0; i < 10000; i++) {
            String selectedStock = symbols[random.nextInt(symbols.length)];
            exchange.updateStockPrice(selectedStock, random.nextBoolean());
        }
    }
}

class PricePrinter extends Thread {
    private final StockExchange exchange;

    public PricePrinter(StockExchange exchange) {
        this.exchange = exchange;
    }

    @Override
    public void run() {
        while (!interrupted()) {
            exchange.displayStockPrices();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                interrupt();
            }
        }
    }
}
