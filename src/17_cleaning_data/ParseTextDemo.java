import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Key takeaways:
 *
 * <ul>
 *   <li>Define a DateTimeFormatter when you need to coerce loosely formatted dates.
 *   <li>Lean on Apache Commons' NumberUtils to sanity-check strings before parsing.
 *   <li>Strip noisy characters (like $) before converting text into numeric types.
 *   <li>NumberUtils.isParsable() checks if a string can be parsed into a number.
 * </ul>
 */
public class ParseTextDemo {
  private static LocalDate parseDate(final String dateStr) {
    // DateTimeFormatter.ofPattern() defines the expected format
    DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yy");
    return LocalDate.parse(dateStr, format);
  }

  // Strip the dollar sign then use Apache helpers to coerce into a double.
  private static double parseAmount(final String amount) {
    // NumberUtils.toDouble() to convert a string to a double
    return NumberUtils.toDouble(amount.replace("$", "").trim());
  }

  /** Parse a sample receipt line and log the intermediate checks. */
  public static void main(String[] args) {
    LocalDate date = parseDate("1/10/23");
    System.out.println("Date: " + date);

    String amount = "$30.61";
    // Check if the amount looks like a number before we convert it.
    // NumberUtils.isParsable() checks if a string can be parsed into a number
    boolean isAmountParsable = NumberUtils.isParsable(amount);
    System.out.println("Is amount parsable? " + isAmountParsable);

    Double parsedAmount = parseAmount(amount);
    System.out.println("parsedAmount: " + parsedAmount);
  }
}
