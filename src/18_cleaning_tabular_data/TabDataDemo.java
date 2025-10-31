import tech.tablesaw.api.DoubleColumn;
import tech.tablesaw.api.Table;

/**
 * Tablesaw cheatsheet.
 *
 * <ul>
 *   <li>{@code Table.read().csv(path)} // load CSV into a typed table
 *   <li>{@code table.column(colName).countMissing()} // quick null counter per column
 *   <li>{@code table.countBy("Category")} // group + tally without writing SQL
 *   <li>{@code table.doubleColumn("Unit_Price").summary()} // numeric stats without pandas</li>
 * </ul>
 */
public class TabDataDemo {
  public static void main(String[] args) {
    dataQualityDemo();
  }

  private static void dataQualityDemo() {
    Table table = Table.read().csv("src/18_cleaning_tabular_data/grocery_table.csv");

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
}
