import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class MinMaxMean {
  // Record is just a compact, immutable data bundle—think readonly TS object with auto getters.
  private record CoffeeSales(
      LocalDate date, String coffeeName, String paymentMethod, int quantity, double amount) {}

  public static void main(String[] args) {
    // Use a List so we get helpers like forEach; Java arrays stay low-level index/length only.
    List<CoffeeSales> sales =
        Arrays.asList(
            new CoffeeSales(LocalDate.of(2024, 3, 1), "Latte", "cash", 1, 6.26),
            new CoffeeSales(LocalDate.of(2024, 3, 5), "Hot Chocolate", "card", 3, 32.51),
            new CoffeeSales(LocalDate.of(2024, 3, 6), "Americano", "card", 2, 18.70));

    // Stats helper accumulates numbers so we can query mean/min/max without manual math.
    DescriptiveStatistics stats = new DescriptiveStatistics();
    // Feed each sale amount into the stats collector (mutates its internal running totals).
    sales.forEach(sale -> stats.addValue(sale.amount()));

    // printf placeholders: %n newline, %d integer, %.2f decimal with 2 digits—TS template-style.
    System.out.printf("%nSales analyzed: %d%n", sales.size());
    System.out.printf("Average amount: $%.2f%n", stats.getMean());
    System.out.printf("Amount range: $%.2f - $%.2f%n", stats.getMin(), stats.getMax());
    System.out.printf("Median amount: $%.2f%n", stats.getPercentile(50)); // median
    System.out.printf(
        "Normal range: $%.2f - $%.2f%n", stats.getPercentile(25), stats.getPercentile(75));
    System.out.printf(
        "Standard deviation: $%.2f%n",
        stats.getStandardDeviation()); // Standard deviation says how spread out the numbers are
    // around the average
  }
}
