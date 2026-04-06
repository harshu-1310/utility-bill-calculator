import java.util.Scanner;

interface Billable {
    double calculateTotal();
}

public class UtilityBillGenerator implements Billable {
    private String customerName;
    private int previousReading;
    private int currentReading;
    private int unitsConsumed;
    private double taxAmount;
    private double totalAmount;

    public UtilityBillGenerator(String customerName, int previousReading, int currentReading) {
        this.customerName = customerName;
        this.previousReading = previousReading;
        this.currentReading = currentReading;
        this.unitsConsumed = currentReading - previousReading;
    }

    @Override
    public double calculateTotal() {
        double cost = 0;
        int units = unitsConsumed;
        if (units <= 100) {
            cost = units * 1.0;
        } else if (units <= 300) {
            cost = 100 * 1.0 + (units - 100) * 2.0;
        } else {
            cost = 100 * 1.0 + 200 * 2.0 + (units - 300) * 5.0;
        }
        // Example: 10% tax
        taxAmount = cost * 0.10;
        totalAmount = cost + taxAmount;
        return totalAmount;
    }

    public void printReceipt() {
        System.out.println("\n----- Digital Receipt -----");
        System.out.println("Customer Name: " + customerName);
        System.out.println("Units Consumed: " + unitsConsumed);
        System.out.printf("Tax Amount: $%.2f\n", taxAmount);
        System.out.printf("Final Total: $%.2f\n", totalAmount);
        System.out.println("--------------------------\n");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter Customer Name (or type 'Exit' to quit): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("Exit")) {
                System.out.println("Exiting Utility Bill Generator.");
                break;
            }
            System.out.print("Enter Previous Meter Reading: ");
            int prev;
            try {
                prev = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Previous Meter Reading.");
                continue;
            }
            System.out.print("Enter Current Meter Reading: ");
            int curr;
            try {
                curr = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer for Current Meter Reading.");
                continue;
            }
            if (prev > curr) {
                System.out.println("Error: Previous Meter Reading cannot be higher than Current Meter Reading.\n");
                continue;
            }
            UtilityBillGenerator bill = new UtilityBillGenerator(name, prev, curr);
            bill.calculateTotal();
            bill.printReceipt();
        }
        scanner.close();
    }
}
