import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

/** Demonstrates using java.util.logging while handling an exception scenario. */
class LoggerExample {

  private static final Logger LOGGER =
      Logger.getLogger(LoggerExample.class.getName()); // Logger.getLogger("LoggerExample")

  /**
   * Entry point that runs two scenarios: one succeeds, the other triggers logging in the catch
   * block.
   */
  public static void main(String[] args) {
    BigDecimal bill = BigDecimal.valueOf(125.50);
    LOGGER.log(Level.INFO, "Starting bill-splitting demo for amount {0}", bill);

    computeEachBill(bill, 5);
    computeEachBill(bill, 0);

    LOGGER.info("Finished bill-splitting demo");
  }

  /**
   * Attempts to divide a bill among the provided number of people, logging each step along the way.
   *
   * @param bill total amount to split
   * @param people how many people the bill should be divided between
   */
  public static void computeEachBill(final BigDecimal bill, final int people) {
    LOGGER.log(Level.INFO, "Preparing to split {0} among {1} people", new Object[] {bill, people});

    BigDecimal individualBill = BigDecimal.ZERO;
    BigDecimal numPeople = BigDecimal.valueOf(people);
    try {
      LOGGER.fine("Performing bill division");
      individualBill = bill.divide(numPeople);
      LOGGER.log(Level.INFO, "Calculated individual bill: {0}", individualBill);
    } catch (ArithmeticException e) {
      LOGGER.log(
          Level.WARNING,
          "Invalid split request detected (people must be greater than zero): {0}",
          people);
      LOGGER.log(Level.SEVERE, "Unable to split bill for " + people + " people", e);

      numPeople = BigDecimal.valueOf(2);
      LOGGER.log(Level.INFO, "Falling back to {0} people", numPeople);
      individualBill = bill.divide(numPeople);
    } finally {
      LOGGER.log(
          Level.INFO,
          "Bill for each of {0} people is {1}",
          new Object[] {numPeople, individualBill});
    }
  }
}
