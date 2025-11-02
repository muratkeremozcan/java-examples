import static tech.tablesaw.aggregate.AggregateFunctions.sum;

import java.util.Locale;
import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.StringColumn;
import tech.tablesaw.api.Table;
import tech.tablesaw.selection.Selection;

/**
 *
 *
 * <ul>
 *   <li>{@code Table.read().csv(path)} // load CSV into a typed table
 *   <li>{@code table.column(colName).countMissing()} // quick null counter per column
 *   <li>{@code table.countBy("Category")} // group + tally without writing SQL
 *   <li>{@code table.doubleColumn("Unit_Price").summary()} // numeric stats without pandas
 *   <li>{@code table.stringColumn("Product_Name").map(...)} // vectorized string cleanup
 *   <li>{@code table.where(selA.and(selB))} // merge predicates before filtering
 *   <li>{@code table.where(...).summarize(col, sum).by("Category")} // WHERE → SUM → GROUP BY chain
 * </ul>
 */
public class TabDataDemo {
  /** Run demos covering quality, cleaning, and filtering tasks. */
  public static void main(String[] args) {
    dataQualityDemo();
    dataCleaningDemo();
    dataFilteringDemo();
    dataAggregationDemo();
  }

  private static void dataQualityDemo() {
    Table table = Table.read().csv("src/17_cleaning_data/grocery_inventory.csv");

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
    Table table = Table.read().csv("src/17_cleaning_data/grocery_inventory.csv");

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

  /** Filter rows using Selection predicates and sort the results. */
  private static void dataFilteringDemo() {
    Table inventory = Table.read().csv("src/17_cleaning_data/grocery_inventory.csv");

    // Build a Selection for rows where quantity > 50.
    Selection highQuantity = inventory.intColumn("Stock_Quantity").isGreaterThan(50);
    // Chain a string match; Selection#and composes filters like WHERE a AND b.
    Selection fruitsAndVegetables =
        inventory.stringColumn("Category").isEqualTo("Fruits & Vegetables");

    Table highQuantityTable =
        inventory.where(highQuantity.and(fruitsAndVegetables)).sortDescendingOn("Stock_Quantity");

    System.out.println("High quantity fruits and vegetables:");
    System.out.println(highQuantityTable.selectColumns("Product_Name", "Stock_Quantity").first(10));
  }

  /** Aggregate stock quantities by category after applying a price filter. */
  private static void dataAggregationDemo() {
    Table inventory = Table.read().csv("src/17_cleaning_data/grocery_inventory.csv");

    Table quantityByCategory =
        inventory
            // where(...) narrows the table to expensive items before aggregating.
            .where(inventory.doubleColumn("Unit_Price").isGreaterThan(5.0))
            // summarize(col, sum) adds a SUM column to the running aggregation table.
            .summarize("Stock_Quantity", sum)
            // by("Category") finalizes the aggregation by grouping on category.
            .by("Category");

    System.out.println("Total quantity by category:");
    System.out.println(quantityByCategory.first(5));
  }
}
