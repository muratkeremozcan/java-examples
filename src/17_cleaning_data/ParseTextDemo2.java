import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * Key takeaways.
 *
 * <ul>
 *   <li>Shape parsed rows into the CoffeeSales record so later code stays type-safe.
 *   <li>NumberUtils.isParsable() guards quantity and price fields before conversion.
 *   <li>Strip the $ before handing the cleaned value to Double.parseDouble().
 *   <li>Files.newBufferedReader(..., UTF_8) avoids default-encoding surprises.
 * </ul>
 */
public class ParseTextDemo2 {
  private record CoffeeSales(
      LocalDate date, String coffeeName, String paymentMethod, Integer quantity, Double amount) {}

  // DateTimeFormatter.ofPattern() matches inputs like 1/10/23
  private static LocalDate parseDate(final String dateStr) {
    return LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("M/d/yy"));
  }

  // NumberUtils.isParsable() fails fast if the CSV column is not numeric.
  private static String validateNumeric(final String value) {
    if (NumberUtils.isParsable(value)) {
      return value;
    }
    throw new IllegalArgumentException("Invalid: " + value);
  }

  /** Strip the currency symbol and convert the cleaned price text into a double. */
  public static double parsePrice(final String priceText) {
    if (NumberUtils.isParsable(priceText.replace("$", ""))) {
      // Double.parseDouble() runs once the currency symbol is gone.
      return Double.parseDouble(priceText.replace("$", ""));
    }
    throw new IllegalArgumentException("Invalid price: " + priceText);
  }

  /** Convert the raw CSV fields into a strongly typed record. */
  public static CoffeeSales parseSalesData(final String[] fields) {
    LocalDate publishDate = parseDate(fields[0]);
    String coffeeName = fields[1];
    String paymentMethod = fields[2];
    // Integer.parseInt() converts the cleaned quantity string
    Integer quantity = Integer.parseInt(validateNumeric(fields[3]));
    Double amount = parsePrice(fields[4]);
    return new CoffeeSales(publishDate, coffeeName, paymentMethod, quantity, amount);
  }

  /** Parse the CSV file, map each row into a CoffeeSales record, and print the results. */
  public static void main(String[] args) throws IOException, InterruptedException {
    List<CoffeeSales> sales = new ArrayList<>();
    String filename = "src/17_cleaning_data/coffee.csv";
    try (BufferedReader reader =
        Files.newBufferedReader(Path.of(filename), StandardCharsets.UTF_8)) {
      reader.readLine(); // Skip header row
      String line;
      while ((line = reader.readLine()) != null) {
        String[] fields = line.split(",");
        sales.add(parseSalesData(fields));
      }
    }
    sales.forEach(System.out::println);
  }
}
