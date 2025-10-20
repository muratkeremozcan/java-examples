import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.apache.commons.lang3.math.NumberUtils;

/** Quick demo: parse dates and dollar amounts from plain strings. */
public class ParseTextDemo {
  // Formatter matches inputs like 1/10/23 (month/day/two-digit-year).
  private static LocalDate parseDate(final String dateStr) {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("M/d/yy");
    return LocalDate.parse(dateStr, format);
  }

  // Strip the dollar sign then use Apache helpers to coerce into a double.
  private static double parseAmount(final String amount) {
    return NumberUtils.toDouble(amount.replace("$", "").trim());
  }

  /** Parse a sample receipt line and log the intermediate checks. */
  public static void main(String[] args) {
    LocalDate date = parseDate("1/10/23");
    System.out.println("Date: " + date);

    String amount = "$30.61";
    // Check if the amount looks like a number before we convert it.
    boolean isAmountParsable = NumberUtils.isParsable(amount);
    System.out.println("Is amount parsable? " + isAmountParsable);

    Double parsedAmount = parseAmount(amount);
    System.out.println("parsedAmount: " + parsedAmount);
  }
}
