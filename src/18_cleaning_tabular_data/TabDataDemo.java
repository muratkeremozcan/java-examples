import java.util.Locale;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;

/**
 * Tablesaw cheatsheet.
 *
 * <ul>
 *   <li>{@code Table.read().csv(path)} // load CSV into a typed table
 *   <li>{@code table.column(colName).countMissing()} // quick null counter per column
 *   <li>{@code table.countBy("Category")} // group + tally without writing SQL
 *   <li>{@code table.doubleColumn("Unit_Price").summary()} // numeric stats without pandas
 *   <li>{@code table.stringColumn("Product_Name").map(...)} // vectorised string cleanup
 * </ul>
 */
public class TabDataDemo {
  public static void main(String[] args) {
    dataQualityDemo();
    dataCleaningDemo();
  }

  private static void dataQualityDemo() {
    Table table = Table.read().csv("src/18_cleaning_tabular_data/grocery_inventory.csv");

    for (String colName : table.columnNames()) {
      // table.column(colName).countMissing() acts like COUNT(*) FILTER (WHERE value IS NULL)
      int missing = table.column(colName).countMissing();
      System.out.println(colName + " missing values: " + missing);
    }

    // Count the table by unique categories
    Table catCounts = table.countBy("Category");
    System.out.println("\nCategory distribution:");
    System.out.println(catCounts);

    // Extract a numeric column to unlock math helpers (mean, min, std dev, etc.).
    DoubleColumn price = table.doubleColumn("Unit_Price");
    System.out.println("Unit price statistics:");

    // Compute the min and max of unit price without hand-written loops.
    System.out.println("Min: " + price.min());
    System.out.println("Max: " + price.max());

    // Compute the mean and standard deviation of the unit price
    System.out.println("Mean: " + price.mean());
    System.out.println("Standard deviation: " + price.standardDeviation());
  }

  /** Walk column metadata and compute derived values for tabular cleanup. */
  private static void dataCleaningDemo() {
    Table table = Table.read().csv("src/18_cleaning_tabular_data/grocery_inventory.csv");

    System.out.println("Column data types:");
    for (String columnName : table.columnNames()) {
      // table.column(colName).type() tells you if you loaded StringColumn, DoubleColumn, etc.
      System.out.println(columnName + ": " + table.column(columnName).type());
    }

    // asDoubleColumn() promotes the int column so arithmetic with DoubleColumn stays safe.
    DoubleColumn quantity = table.intColumn("Stock_Quantity").asDoubleColumn();
    DoubleColumn unitPrice = table.doubleColumn("Unit_Price");

    // Vectorized multiply: quantity * price for every row → new column.
    DoubleColumn totalValue = quantity.multiply(unitPrice).setName("Total_Value");
    table.addColumns(totalValue);

    System.out.println("\nMultiplying two columns: Stock_Quantity * Unit_Price = Total_Value");
    System.out.println(
        table
            .selectColumns("Product_Name", "Stock_Quantity", "Unit_Price", "Total_Value")
            .first(4)
            .print());

    // Pull the string column so we can run normalization steps in one pass.
    StringColumn names = table.stringColumn("Product_Name");
    StringColumn standardizedNames =
        // map() is vectorized; each lambda run handles one cell.
        names
            .map(
                // Strip marketing fluff in parentheses, then trim + lowercase for easy joins.
                t ->
                    t.replaceAll("\\(.*\\)", "")
                        // Remove surrounding space
                        .trim()
                        // Convert to lowercase using a stable locale
                        .toLowerCase(Locale.ROOT))
            .setName("Standardized_Names");

    System.out.println("Example name before cleaning: " + names.get(0));
    System.out.println("Example name after cleaning: " + standardizedNames.get(0));
  }
}
