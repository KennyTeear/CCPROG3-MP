import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for payment-related operations.
 */
public class PaymentUtils {
    
    /**
     * Initializes bill denominations with default quantities set to zero.
     * @return A map containing denominations and their default values.
     */
    public static Map<String, Integer> initializeBillDenominations() {
        Map<String, Integer> billDenominations = new HashMap<>();
        billDenominations.put("20", 0);
        billDenominations.put("50", 0);
        billDenominations.put("100", 0);
        billDenominations.put("200", 0);
        billDenominations.put("500", 0);
        billDenominations.put("1000", 0);
        return billDenominations;
    }
    
    /**
     * Returns the integer amount corresponding to a denomination.
     * @param denomination The string representation of the denomination.
     * @return The integer value of the denomination.
     */
    public static int getDenominationAmount(String denomination) {
        switch (denomination) {
            case "20": return 20;
            case "50": return 50;
            case "100": return 100;
            case "200": return 200;
            case "500": return 500;
            case "1000": return 1000;
            default: return 0;
        }
    }
    
    /**
     * Calculates the total available change in the vending machine.
     * @param billDenominations A map of denominations and their respective quantities.
     * @return The total value of available change.
     */
    public static int getTotalChange(Map<String, Integer> billDenominations) {
        int totalChange = 0;
        for (Map.Entry<String, Integer> entry : billDenominations.entrySet()) {
            totalChange += getDenominationAmount(entry.getKey()) * entry.getValue();
        }
        return totalChange;
    }
    
    /**
     * Checks if there is enough change to dispense a given amount.
     * @param change The amount of change required.
     * @param balance The current balance in the vending machine.
     * @param billDenominations The map containing available denominations.
     * @return True if sufficient change is available, false otherwise.
     */
    public static boolean hasSufficientChange(int change, int balance, Map<String, Integer> billDenominations) {
        return balance >= change && change <= getTotalChange(billDenominations);
    }
    
    /**
     * Distributes the requested change using the available denominations.
     * @param change The amount of change to dispense.
     * @param billDenominations The map of available bill denominations.
     */
    public static void distributeChange(int change, Map<String, Integer> billDenominations) {
        for (Map.Entry<String, Integer> entry : billDenominations.entrySet()) {
            String denomination = entry.getKey();
            int quantity = entry.getValue();
            int denominationAmount = getDenominationAmount(denomination);
            while (change >= denominationAmount && quantity > 0) {
                change -= denominationAmount;
                quantity--;
                System.out.println("Dispensed " + denomination);
            }
            billDenominations.put(denomination, quantity);
            if (change == 0) break;
        }
    }
}
