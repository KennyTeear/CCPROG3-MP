import java.util.Map;

/**
 * Handles the payment process in a vending machine using PaymentUtils.
 */
public class PaymentProcess {
    private int balance;
    private Map<String, Integer> billDenominations;

    /**
     * Constructs a PaymentProcess with an initial balance.
     * @param balance the initial balance of the vending machine
     */
    public PaymentProcess(int balance) {
        this.balance = balance;
        this.billDenominations = PaymentUtils.initializeBillDenominations(); // Initialize denominations
    }

    /**
     * Receive payment in a specific denomination and update the balance.
     * @param denomination the denomination of the payment
     * @param quantity the quantity of the payment
     */
    public void receivePayment(String denomination, int quantity) {
        if (billDenominations.containsKey(denomination)) {
            int currentQuantity = billDenominations.get(denomination);
            billDenominations.put(denomination, currentQuantity + quantity);
            int amount = PaymentUtils.getDenominationAmount(denomination) * quantity;
            balance += amount; // Update balance
            System.out.println("Received payment of " + amount);
        } else {
            System.out.println("Invalid denomination");
        }
    }

    /**
     * Dispenses change to the user and updates the balance.
     * @param change the amount of change to dispense
     */
    public void giveChange(int change) {
        if (PaymentUtils.hasSufficientChange(change, balance, billDenominations)) {
            balance -= change; // Deduct from balance
            PaymentUtils.distributeChange(change, billDenominations); // Dispense change
            System.out.println("Dispensed change of " + change);
        } else {
            System.out.println("Insufficient change in the machine");
        }
    }

    /**
     * Replenishes the quantity of a specific bill denomination in the vending machine.
     * @param denomination the denomination of the bill to replenish
     * @param quantity the quantity of bills to replenish
     */
    public void replenishChange(String denomination, int quantity) {
        if (billDenominations.containsKey(denomination)) {
            int currentQuantity = billDenominations.get(denomination);
            billDenominations.put(denomination, currentQuantity + quantity);
            System.out.println("Replenished " + quantity + " units of " + denomination);
        } else {
            System.out.println("Invalid denomination");
        }
    }

    /**
     * Collect the payment and reset the balance to zero.
     * @return the collected payment amount
     */
    public int collectPayment() {
        int collectedAmount = balance;
        balance = 0; // Reset balance after collection
        return collectedAmount;
    }

    /**
     * Get the current balance.
     * @return the current balance
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Displays the available bills and their quantities in the vending machine.
     * @return A formatted string of available bills
     */
    public String displayAvailableBills() {
        StringBuilder sb = new StringBuilder();
        sb.append("Available Bills:\n");
        for (Map.Entry<String, Integer> entry : billDenominations.entrySet()) {
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        return sb.toString();
    }

        /**
     * Resets all bill denominations to zero.
     */
    public void zeroBillDenominations() {
        for (String denomination : billDenominations.keySet()) {
            billDenominations.put(denomination, 0);
        }
        System.out.println("All bill denominations have been reset to zero.");
    }

}
