import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Represents an Invoice Calculator.
 * @author Confidence Affang,Naude Vlok
 * @version 2.0
 * @see <a href="https://github.com/confidenceaffang/invoceapp.git">Github Repository</a>
 */
public class InvoiceApp {
    /**
     * Scanner for all user input
     **/
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Standard double dash line for display output
     */
    private static final String DOUBLE_DASH_LINE = String.format("%50s", "").replace(' ', '=');

    /**
     * Standard single dash line for display output
     */
    private static final String SINGLE_DASH_LINE = DOUBLE_DASH_LINE.replace('=', '-');

    /**
     * Prompt the user for a double value
     *
     * @param prompt Input label to display
     * @return User input as a double
     */
    private static double getDouble(String prompt) {
        String userInput = "?";
        double validInput = 0.0;

        // keep looping until valid input
        while (true) {
            try {
                System.out.print(prompt);
                userInput = InvoiceApp.sc.nextLine();
                validInput = Double.parseDouble(userInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return validInput;

    } // end of getDouble method

    /**
     * Prompt the user for a double value
     *
     * @param prompt Input label to display
     * @return User input as a double
     */
    private static int getInteger(String prompt) {
        String userInput = "?";
        int validInput = 0;

        // keep looping until valid input
        while (true) {
            try {
                System.out.print(prompt);
                userInput = InvoiceApp.sc.nextLine();
                validInput = Integer.parseInt(userInput);
                break;
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return validInput;

    } // end of getInteger method

    /**
     * Prompt the user for a string value
     *
     * @param prompt Input label to display
     * @return User input as a string
     */
    private static String getString(String prompt) {
        String userInput = "?";

        // keep looping until valid input
        while (true) {
            System.out.print(prompt);
            userInput = InvoiceApp.sc.nextLine();
            if (!userInput.isEmpty()) {
                break;
            } else {
                System.out.println("Invalid input. Please try again...");
            }
        } // end of validation while loop

        return userInput;
    } // end of getString method

    /**
     * Get user's input for y/n question
     *
     * @param prompt Y/N Prompt
     * @return true for y and false for n
     */
    private static boolean getYesNo(String prompt) {
        String userInput = "?";
        boolean ynAnswer = false;

        // keep looping until y or n input
        while (true) {
            System.out.print(prompt);
            userInput = InvoiceApp.sc.nextLine().toLowerCase();

            if (userInput.equals("n")) {
                break;
            } else if (userInput.equals("y")) {
                ynAnswer = true;
                break;
            } else {
                System.out.println("Invalid input. Please try again...");
            }
        } // end y/n validation

        return ynAnswer; // true = yes | false = no

    } // end of getYesNo method

    /**
     * Takes in the subtotal and uses the statements to get eh discount percentage and return it
     *
     * @param subtotal the subtotal of each item
     * @return this returns the discount percentage
     */
    private static double getDiscountPercent(double subtotal) {
        double discountPercent = 0;

        // using range to give the discount percentages
        if (subtotal >= 50.0) {
            discountPercent = 0.25;
        } else if (subtotal >= 40.0) {
            discountPercent = 0.20;
        } else if (subtotal >= 30.0) {
            discountPercent = 0.15;
        } else if (subtotal >= 20.0) {
            discountPercent = 0.10;
        } else if (subtotal >= 10.0) {
            discountPercent = 0.05;
        } else {
            discountPercent = 0.0;
        }

        return discountPercent;
    }

    /**
     * Print the user's receipt
     *
     * @param discountPercent the returned discount percent depending on the subtotal of each item
     * @param descriptionList All descriptions for items purchased
     * @param qtyList         All qualities for items purchased
     * @param priceList       All prices for items purchased
     */

    private static void printReceipt(double discountPercent, List<String> descriptionList, List<Integer> qtyList, List<Double> priceList) {
        String description = "?";
        String newDescription = "?";
        int qty = 0;
        double price = 0.0;


        double subTotal = 0.0;
        double receiptTotal = 0.0;
        double amountSaved = 0.0;
        double totalSaved = 0.0;

        System.out.println(InvoiceApp.DOUBLE_DASH_LINE);
        System.out.println("Customer Receipt");
        System.out.print(InvoiceApp.DOUBLE_DASH_LINE);

        for (int i = 0; i < descriptionList.size(); i++) {

            description = descriptionList.get(i);
            qty = qtyList.get(i);
            price = priceList.get(i);

            subTotal = qty * price;

            discountPercent = getDiscountPercent(subTotal);
            System.out.println();
            System.out.printf("%-20s  %3d  @  $%,6.2f = $%,8.2f\n", description, qty, price, subTotal);

            if (discountPercent > 0) {
                amountSaved = discountPercent * subTotal;
                subTotal = subTotal - amountSaved;
                newDescription = "Discount " + String.valueOf((int) (discountPercent * 100)) + "%";
                System.out.printf("%27s  -$%,6.2f = $%,8.2f\n", newDescription, amountSaved, subTotal);
                totalSaved += amountSaved;
            }


            receiptTotal += subTotal;

        } // end of for lop

        System.out.println(InvoiceApp.SINGLE_DASH_LINE);

        if (totalSaved > 0) {
            System.out.printf("Total Savings: $%,8.2f\n", totalSaved);
        }

        System.out.printf("Receipt Total: $%,8.2f\n", receiptTotal);
        System.out.println(InvoiceApp.SINGLE_DASH_LINE);

    } // end of printReceipt method

    /**
     * Prompt the user for the item description, quantity, and price. Keep looping until the user is done.
     *
     * @param args No runtime arguments are used for this program.
     */
    public static void main(String[] args) {

        List<String> descriptionList = new ArrayList<String>();
        List<Integer> qtyList = new ArrayList<Integer>();
        List<Double> priceList = new ArrayList<Double>();

        String description = "?";
        int qty = 0;
        double price = 0.0;
        double discountPercent = 0.0;

        boolean keepLooping = true;

        System.out.println(DOUBLE_DASH_LINE);
        System.out.println("Welcome to the Invoice Total Calculator");
        System.out.println(DOUBLE_DASH_LINE);
        System.out.println();

        while (keepLooping) {

            description = InvoiceApp.getString("Item Description: ");
            qty = InvoiceApp.getInteger("Item Quantity...: ");
            price = InvoiceApp.getDouble("Item Price......: ");

            descriptionList.add(description);
            qtyList.add(qty);
            priceList.add(price);

            System.out.println();
            System.out.println(InvoiceApp.SINGLE_DASH_LINE);

            keepLooping = InvoiceApp.getYesNo("Do you want to enter another line item (y/n): ");

            System.out.println(InvoiceApp.SINGLE_DASH_LINE);
            System.out.println();

        } // end keepLooping while loop

        InvoiceApp.printReceipt(discountPercent, descriptionList, qtyList, priceList);

        System.out.println("Thank you for shopping with us!");

    } // end of main method

} // end of InvoiceApp