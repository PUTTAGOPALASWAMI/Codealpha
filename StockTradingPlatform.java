import java.util.HashMap;
import java.util.Scanner;

public class StockTradingPlatform {
    private static HashMap<String, Double> stockMarket = new HashMap<>(); // Stores stock prices
    private static HashMap<String, Integer> portfolio = new HashMap<>(); // User's portfolio
    private static double balance = 10000.0; // Starting balance

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        initializeMarket();

        while (true) {
            System.out.println("\n--- Stock Trading Platform ---");
            System.out.println("1. View Market Prices");
            System.out.println("2. Buy Stocks");
            System.out.println("3. Sell Stocks");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: viewMarket(); break;
                case 2: buyStock(scanner); break;
                case 3: sellStock(scanner); break;
                case 4: viewPortfolio(); break;
                case 5: System.out.println("Exiting... Goodbye!"); return;
                default: System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void initializeMarket() {
        stockMarket.put("AAPL", 150.0);
        stockMarket.put("GOOGL", 2800.0);
        stockMarket.put("TSLA", 700.0);
        stockMarket.put("AMZN", 3400.0);
        stockMarket.put("MSFT", 290.0);
    }

    private static void viewMarket() {
        System.out.println("\n--- Market Prices ---");
        for (var entry : stockMarket.entrySet()) {
            System.out.println(entry.getKey() + " : $" + entry.getValue());
        }
    }

    private static void buyStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String stock = scanner.nextLine().toUpperCase();
        if (!stockMarket.containsKey(stock)) {
            System.out.println("Invalid stock symbol.");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        double cost = stockMarket.get(stock) * quantity;

        if (cost > balance) {
            System.out.println("Insufficient balance!");
        } else {
            balance -= cost;
            portfolio.put(stock, portfolio.getOrDefault(stock, 0) + quantity);
            System.out.println("Bought " + quantity + " shares of " + stock);
        }
    }

    private static void sellStock(Scanner scanner) {
        System.out.print("Enter stock symbol: ");
        String stock = scanner.nextLine().toUpperCase();
        if (!portfolio.containsKey(stock)) {
            System.out.println("You don't own this stock.");
            return;
        }
        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();

        if (quantity > portfolio.get(stock)) {
            System.out.println("Not enough shares to sell.");
        } else {
            double earnings = stockMarket.get(stock) * quantity;
            balance += earnings;
            portfolio.put(stock, portfolio.get(stock) - quantity);
            if (portfolio.get(stock) == 0) {
                portfolio.remove(stock);
            }
            System.out.println("Sold " + quantity + " shares of " + stock);
        }
    }

    private static void viewPortfolio() {
        System.out.println("\n--- Your Portfolio ---");
        System.out.println("Balance: $" + balance);
        for (var entry : portfolio.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue() + " shares");
        }
    }
}
