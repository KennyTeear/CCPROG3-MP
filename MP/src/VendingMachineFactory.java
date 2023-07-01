import java.util.Scanner;
/**
 * Factory class for creating and testing vending machines.
 */
public class VendingMachineFactory {
    private VendingMachine vendingMachine;
    /**
     * Constructs a VendingMachineFactory object.
     */
    public VendingMachineFactory() {
        this.vendingMachine = null;
    }

    /**
     * Starts the vending machine factory menu.
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);

        int choice = 0;
        while (choice != 3) {
            System.out.println("----- Vending Machine Factory Menu -----");
            System.out.println("1. Create new vending machine");
            System.out.println("2. Test vending machine");
            System.out.println("3. Exit program");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    createVendingMachine();
                    break;
                case 2:
                    testVendingMachine();
                    break;
                case 3:
                    exitProgram();
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }

        scanner.close();
    }
    /**
     * Creates new vending machine
     */
    public void createVendingMachine() {
        // put the stuff inside the slots
        Slots[] slots = new Slots[8];
        slots[0] = new Slots(new Item("Hotdog           ", 35), 35, 10);
        slots[1] = new Slots(new Item("Ham              " , 45), 45, 10);
        slots[2] = new Slots(new Item("Beef Slices      ", 40), 50, 10);
        slots[3] = new Slots(new Item("Lettuce          ", 10), 15, 10);
        slots[4] = new Slots(new Item("Tomato           ", 10), 25, 10);
        slots[5] = new Slots(new Item("Egg              ", 20), 20, 10);
        slots[6] = new Slots(new Item("Whole wheat Bread", 10), 50, 10);
        slots[7] = new Slots(new Item("White Bread      ", 30), 30, 10);

        // Create the payment process
        PaymentProcess paymentProcess = new PaymentProcess(0);

        // create the vending machine
        this.vendingMachine = new VendingMachine(slots, paymentProcess);
        System.out.println("New vending machine created successfully.");
    }

    /**
     * Tests the vending machine.
     */
    public void testVendingMachine() {
        if (this.vendingMachine != null) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("----- Vending Machine Testing Menu -----");
                System.out.println("1. Display available items");
                System.out.println("2. Insert payment");
                System.out.println("3. Select slot and dispense item");
                System.out.println("4. Replenish item quantity");
                System.out.println("5. Set item price");
                System.out.println("6. Print transaction summary");
                System.out.println("7. Replenish change denominations");
                System.out.println("8. Exit testing");

                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        this.vendingMachine.displayItems();
                        break;

                    case 2:
                        System.out.print("Enter denomination: ");
                        String denomination = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int quantity = scanner.nextInt();
                        this.vendingMachine.receivePayment(denomination, quantity);
                        break;

                    case 3:
                        System.out.print("Enter slot number: ");
                        int slotNumber = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        Item item = this.vendingMachine.dispenseItem(slotNumber);
                        if (item != null) {
                            System.out.println("Dispensed item: " + item.getName());
                        }
                        break;

                    case 4:
                        System.out.print("Enter slot number: ");
                        int restockSlotNumber = scanner.nextInt();
                        System.out.print("Enter quantity: ");
                        int restockQty = scanner.nextInt();
                        if (restockQty >= 0) {
                            this.vendingMachine.restockItem(restockSlotNumber, restockQty);
                        } else {
                            System.out.println("Invalid quantity. Quantity must be non-negative.");
                        }
                        break;

                    case 5:
                        System.out.print("Enter slot number: ");
                        int setPriceSlotNumber = scanner.nextInt();
                        System.out.print("Enter price: ");
                        int setPrice = scanner.nextInt();
                        this.vendingMachine.setPrice(setPriceSlotNumber, setPrice);
                        break;

                    case 6:
                        this.vendingMachine.printTransactionSummary();
                        break;

                    case 7: // Add case for replenishing change denominations
                        System.out.print("Enter denomination to replenish: ");
                        String replenishDenomination = scanner.nextLine();
                        System.out.print("Enter quantity: ");
                        int replenishQuantity = scanner.nextInt();
                        this.vendingMachine.replenishChange(replenishDenomination, replenishQuantity);
                        break;

                    case 8: // Update the case number for exit
                        System.out.println("Exiting testing.");
                        return;

                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
            }
        } else {
            System.out.println("No vending machine created yet.");
        }
    }
    
    /**
     * Exit the Program
     */
    public void exitProgram() {
        System.out.println("Exiting program.");
        System.exit(0);
    }
}
