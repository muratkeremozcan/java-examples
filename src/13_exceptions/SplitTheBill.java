import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

class SplitTheBill {

  private static final Logger LOGGER = Logger.getLogger(SplitTheBill.class.getName());

  public static void main(String[] args) {
    BigDecimal bill = BigDecimal.valueOf(125.50);
    computeEachBill(bill, 5);
    computeEachBill(bill, 0);
  }

  public static void computeEachBill(final BigDecimal bill, final int people) {
    BigDecimal individualBill = BigDecimal.ZERO;
    BigDecimal numPeople = BigDecimal.valueOf(people);
    try {
      individualBill = bill.divide(numPeople);
    } catch (ArithmeticException e) {
      System.out.println(
          "You didn't provide a positive number of people to split the bill among. Assuming 2"
              + " people.");
      System.out.println("Type of exception: " + e.getClass());
      System.out.println("Error message: " + e.getMessage());
      if (LOGGER.isLoggable(Level.SEVERE)) {
        LOGGER.log(Level.SEVERE, "Unable to split bill for " + people + " people", e);
      }

      numPeople = BigDecimal.valueOf(2);
      individualBill = bill.divide(numPeople);
    } finally {
      System.out.println("Bill for each of " + numPeople + " persons is: " + individualBill);
    }
  }
}
