/**
 * Contrasts GC-heavy string concatenation with a StringBuilder approach so you can see how object
 * churn slows things down.
 */
public class GarbageCollectorExample {

  /**
   * Inefficient approach: rebuilds the buffer every iteration so the JVM keeps copying data and
   * freeing the old char arrays. Extra garbage builds up quickly and makes the GC work harder.
   */
  @SuppressWarnings("PMD.AvoidInstantiatingObjectsInLoops")
  public static String generateReportWithoutOptimization(final double[] dataPoints) {
    StringBuilder report = new StringBuilder();
    for (double value : dataPoints) {
      report.append(String.format("%.2f, ", value));
      // Inefficient: force a brand new buffer each time, similar GC churn to string concatenation.
      report = new StringBuilder(report.toString());
    }
    return report.toString();
  }

  /**
   * Efficient approach: sticks with one {@link StringBuilder} so the loop stays O(n) without extra
   * garbage.
   */
  public static String generateReportWithOptimization(final double[] dataPoints) {
    // Create a new string builder
    StringBuilder report = new StringBuilder(dataPoints.length * 8);
    for (double value : dataPoints) {
      // For every value, add the formatted value to the string builder
      report.append(String.format("%.2f, ", value));
    }
    // Convert to string before returning
    return report.toString();
  }

  /** Runs both report generators and prints timing plus sanity checks. */
  public static void main(String[] args) {
    double[] testData = new double[10_000];
    for (int i = 0; i < testData.length; i++) {
      testData[i] = Math.random() * 100;
    }

    // Key takeaway: both methods do the exact same job, but the GC-friendly version spends far less
    // time allocating temporary strings.
    long startWithoutOptimization = System.nanoTime();
    String reportWithoutOptimization = generateReportWithoutOptimization(testData);
    long endWithoutOptimization = System.nanoTime();

    long startWithOptimization = System.nanoTime();
    String reportWithOptimization = generateReportWithOptimization(testData);
    long endWithOptimization = System.nanoTime();

    System.out.println(
        "Time taken without optimization: "
            + (endWithoutOptimization - startWithoutOptimization) / 1_000_000
            + " ms");
    System.out.println(
        "Time taken with optimization: "
            + (endWithOptimization - startWithOptimization) / 1_000_000
            + " ms");
    System.out.println(
        "Reports have same length? "
            + (reportWithoutOptimization.length() == reportWithOptimization.length()));
  }
}
