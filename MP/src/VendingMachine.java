import java.util.ArrayList;

/**
 * Represents a vending machine that holds slots of items and handles transactions.
 */
public class VendingMachine {
    private static Slots[] slots;
    private ArrayList<Transaction> transactionLog;
    private PaymentProcess paymentProcess;

    /**
     * Constructs a VendingMachine object with the specified slots and payment process.
     *
     * @param slots          the array of slots containing items
     * @param paymentProcess the payment process for handling transactions
     */
    public VendingMachine(Slots[] slots, PaymentProcess paymentProcess) {
        this.slots = slots;
        this.paymentProcess = paymentProcess;
        this.transactionLog = new ArrayList<>();
    }

    /**
     * Displays the available items in the vending machine.
     */
    public void displayItems() {
        System.out.println("Available items:");
        for (int i = 0; i < slots.length; i++) {
            Slots slot = slots[i];
            Item item = slot.getItem();
            if (item != null) {
                System.out.print("Slot " + i + ": ");
                System.out.print(item.getName() + " - ");
                System.out.print("Price: " + slot.getPrice() + " - ");
                System.out.print("Quantity: " + slot.getQty() + " - ");
                System.out.println("Calories: " + item.getCalories());
            } else {
                System.out.println("Slot " + i + ": Empty");
            }
        }
    }

    /**
     * Receives payment in the specified denomination and quantity.
     *
     * @param denomination the denomination of the payment
     * @param quantity     the quantity of the payment
     */
    public void receivePayment(String denomination, int quantity) {
        paymentProcess.receivePayment(denomination, quantity);
    }


    /**
     * Replenishes the quantity of a specific bill denomination in the vending machine.
     *
     * @param denomination the denomination of the bill to replenish
     * @param quantity     the quantity of bills to replenish
     */
    public String replenishChange(String denomination, int quantity) {
        paymentProcess.replenishChange(denomination, quantity);
        return "Replenished change: " + quantity + " units of " + denomination;
    }

    /**
     * Dispenses an item from the specified slot number.
     *
     * @param slotNumber the slot number of the item to dispense
     * @return the dispensed item, or null if the slot is empty or the payment is insufficient
     */
    public Item dispenseItem(int slotNumber) {
        if (slotNumber >= 0 && slotNumber < slots.length) {
            Slots slot = slots[slotNumber];
            if (slot.getAvailability()) {
                int price = slot.getPrice();

                int hotDogSlotNumber = 0;
                int hamSlotNumber = 1;
                int beefSlotNumber = 2;
                int breadSlotNumber = 6;
                int lettuceSlotNumber = 3;
                int tomatoSlotNumber = 4;

                Slots hotDogSlot = slots[hotDogSlotNumber];
                Slots hamSlot = slots[hamSlotNumber];
                Slots beefSlot = slots[beefSlotNumber];
                Slots breadSlot = slots[breadSlotNumber];
                Slots lettuceSlot = slots[lettuceSlotNumber];
                Slots tomatoSlot = slots[tomatoSlotNumber];

                if (slotNumber == 9) { // Healthy Hotdog Sandwich
                    if (hotDogSlot.getQty() >= 1 && breadSlot.getQty() >= 1 &&
                            lettuceSlot.getQty() >= 1 && tomatoSlot.getQty() >= 1) {
                        hotDogSlot.setQty(hotDogSlot.getQty() - 1);
                        breadSlot.setQty(breadSlot.getQty() - 1);
                        lettuceSlot.setQty(lettuceSlot.getQty() - 1);
                        tomatoSlot.setQty(tomatoSlot.getQty() - 1);
                        slot.setQty(slot.getQty() - 1);
                    } else {
                        System.out.println("Insufficient quantity of associated items. Please restock the associated items.");
                        return null;
                    }
                } else if (slotNumber == 10) { // Healthy Ham Sandwich
                    if (hamSlot.getQty() >= 1 && breadSlot.getQty() >= 1 &&
                            lettuceSlot.getQty() >= 1 && tomatoSlot.getQty() >= 1) {
                        hamSlot.setQty(hamSlot.getQty() - 1);
                        breadSlot.setQty(breadSlot.getQty() - 1);
                        lettuceSlot.setQty(lettuceSlot.getQty() - 1);
                        tomatoSlot.setQty(tomatoSlot.getQty() - 1);
                        slot.setQty(slot.getQty() - 1);
                    } else {
                        System.out.println("Insufficient quantity of associated items. Please restock the associated items.");
                        return null;
                    }
                } else if (slotNumber == 11) { // Healthy Beef Sandwich
                    if (beefSlot.getQty() >= 1 && breadSlot.getQty() >= 1 &&
                            lettuceSlot.getQty() >= 1 && tomatoSlot.getQty() >= 1) {
                        beefSlot.setQty(beefSlot.getQty() - 1);
                        breadSlot.setQty(breadSlot.getQty() - 1);
                        lettuceSlot.setQty(lettuceSlot.getQty() - 1);
                        tomatoSlot.setQty(tomatoSlot.getQty() - 1);
                        slot.setQty(slot.getQty() - 1);
                    } else {
                        System.out.println("Insufficient quantity of associated items. Please restock the associated items.");
                        return null;
                    }
                } else if (slotNumber == 12) { // Vegetarian Sandwich
                    if (breadSlot.getQty() >= 1 &&
                            lettuceSlot.getQty() >= 1 && tomatoSlot.getQty() >= 1) {
                        breadSlot.setQty(breadSlot.getQty() - 1);
                        lettuceSlot.setQty(lettuceSlot.getQty() - 1);
                        tomatoSlot.setQty(tomatoSlot.getQty() - 1);
                        slot.setQty(slot.getQty() - 1);
                    } else {
                        System.out.println("Insufficient quantity of associated items. Please restock the associated items.");
                        return null;
                    }
                } else {
                    // For regular slots, just check the quantity of the selected item
                    if (slot.getQty() < 1) {
                        System.out.println("Insufficient quantity. Please select a lower quantity.");
                        return null;
                    }
                    slot.setQty(slot.getQty() - 1);
                }

                if (paymentProcess.getBalance() >= price) {
                    Item item = slot.getItem();
                    paymentProcess.giveChange(paymentProcess.getBalance() - price);
                    int collect = paymentProcess.collectPayment();
                    System.out.println("Collected payment of: " + collect);
                    transactionLog.add(new Transaction(item, price));

                    return item;
                } else {
                    System.out.println("Insufficient payment. Please insert more money.");
                }
            } else {
                System.out.println("Item not available in slot " + slotNumber);
            }
        } else {
            System.out.println("Invalid slot number");
        }
        return null;
    }

    /**
     * Restocks the item quantity in the specified slot.
     *
     * @param slotNumber the slot number to restock
     * @param qty        the quantity to add to the slot
     */
    public void restockItem(int slotNumber, int qty) {
        if (slotNumber >= 0 && slotNumber < slots.length) {
            Slots slot = slots[slotNumber];
            slot.setQty(qty);
            System.out.println("Restocked item in slot " + slotNumber + " with quantity " + qty);
        } else {
            System.out.println("Invalid slot number");
        }
    }

    /**
     * Sets the price of the item in the specified slot.
     *
     * @param slotNumber the slot number to set the price
     * @param price      the price to set for the item
     */
    public void setPrice(int slotNumber, int price) {
        if (slotNumber >= 0 && slotNumber < slots.length) {
            Slots slot = slots[slotNumber];
            slot.setPrice(price);
            System.out.println("Set price of item in slot " + slotNumber + " to " + price);
        } else {
            System.out.println("Invalid slot number");
        }
    }

    /**
     * Prints the transaction summary.
     */
    public void printTransactionSummary() {
        System.out.println("Transaction Summary:");
        for (Transaction transaction : transactionLog) {
            Item item = transaction.getItem();
            int payment = transaction.getPayment();
            System.out.println("Item: " + item.getName() + " - Payment: " + payment);
        }
    }

    /**
     * Empties the money from the vending machine, collecting the amount.
     *
     */
    public void emptyMoney() {
        paymentProcess.collectPayment();
        paymentProcess.ZeroBillDenominations();
    }

    /**
     * Displays the available bills and their quantities in the vending machine.
     */
    public void displayAvailableBills() {
        paymentProcess.displayAvailableBills();
    }

    /**
     * Returns the array of slots in the vending machine.
     *
     * @return the array of slots
     */
    public static Slots[] getSlots() {
        return slots;
    }

    /**
     * Returns the transactionLog
     *
     * @return the arraylist of transaction logs
     */
    public ArrayList<Transaction> getTransactionLog() {
        return transactionLog;
    }

    public String replenishItem(int slotNumber, int quantity) {
        if (slotNumber >= 0 && slotNumber < slots.length) {
            Slots slot = slots[slotNumber];
            slot.setQty(slot.getQty() + quantity);
            return "Restocked item in slot " + slotNumber + " with quantity " + quantity;
        } else {
            return "Invalid slot number";
        }
    }

    public String setItemPrice(int slotNumber, int price) {
        if (slotNumber >= 0 && slotNumber < slots.length) {
            Slots slot = slots[slotNumber];
            slot.setPrice(price);
            return "Set price of item in slot " + slotNumber + " to " + price;
        } else {
            return "Invalid slot number";
        }
    }


    public PaymentProcess getPaymentProcess() {
        return paymentProcess;
    }
}