import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;

/**
 * Walks through simple JVM memory measurements to show how allocations affect the heap in real
 * time.
 */
public class PerformanceMetrics {

  public static void main(String[] args) {
    memoryUsageDemo();
    cpuTimeDemo();
  }

  // Takeaway: allocations that stay referenced keep the heap busy; freeing references lets the GC
  // reclaim that space. Measuring before/after highlights the true footprint of a structure.
  private static void memoryUsageDemo() {
    double memoryBefore = getUsedMemoryMegabytes();
    System.out.println("Memory before array creation: " + memoryBefore + " MB");

    int size = 10_000_000;
    double[] largeArray = new double[size];
    for (int i = 0; i < size; i++) {
      largeArray[i] = Math.sqrt(i);
    }

    // Get the currently used memory in megabytes
    double memoryAfter = getUsedMemoryMegabytes();
    System.out.println("Memory after array creation: " + memoryAfter + " MB");
    System.out.println("Memory used by array creation: " + (memoryAfter - memoryBefore) + " MB");
    // Prevent SpotBugs from treating the populated array as unused.
    if (largeArray[size - 1] == -1) {
      System.out.println("Unreachable branch just to use largeArray: " + largeArray[0]);
    }
  }

  // Takeaway: `totalMemory - freeMemory` approximates live heap usage. Converting to MB keeps the
  // output readable when experimenting.
  private static double getUsedMemoryMegabytes() {
    // Runtime.getRuntime() to retrieve the JVM runtime instance
    Runtime runtime = Runtime.getRuntime();
    // runtime.totalMemory() - runtime.freeMemory() to calculate the current used memory
    long usedMemoryBytes = runtime.totalMemory() - runtime.freeMemory();
    return usedMemoryBytes / (1024.0 * 1024.0);
  }

  // Takeaway: wall-clock timings can fluctuate, but CPU time isolates how much work the thread
  // actually performed—perfect for comparing tight loops or algorithm variants.
  private static void cpuTimeDemo() {
    long iterations = 100_000_000L; // 100 million

    long startSum = getCpuTimeNano();
    long sum = calculateSum(iterations);
    long endSum = getCpuTimeNano();
    long sumDuration = endSum - startSum;
    System.out.printf(
        "Sum 1..%d = %d (CPU time %.3f ms)%n", iterations, sum, sumDuration / 1_000_000.0);

    long startSquareSum = getCpuTimeNano();
    long squareSum = calculateSquareSum(iterations);
    long endSquareSum = getCpuTimeNano();
    long squareSumDuration = endSquareSum - startSquareSum;
    System.out.printf(
        "Square sum 1..%d = %d (CPU time %.3f ms)%n",
        iterations, squareSum, squareSumDuration / 1_000_000.0);

    if (sumDuration == 0) {
      System.out.println("Sum completed too quickly to compute a ratio accurately.");
      return;
    }

    double ratio = squareSumDuration / (double) sumDuration;
    System.out.printf(
        "Nested multiplication makes the square sum %.2fx more CPU intensive.%n", ratio);
  }

  /** Returns the CPU time (in nanoseconds) consumed by the current thread so far. */
  public static long getCpuTimeNano() {
    // ManagementFactory.getThreadMXBean() to Retrieve the ThreadMXBean instance
    ThreadMXBean threadMxBean = ManagementFactory.getThreadMXBean();
    long cpuTimeNanos = 0L;
    if (threadMxBean.isThreadCpuTimeSupported()) {
      threadMxBean.setThreadCpuTimeEnabled(true);
      // Report CPU ticks used by the current thread (ignoring time spent blocked or waiting).
      cpuTimeNanos = threadMxBean.getThreadCpuTime(Thread.currentThread().threadId());
    } else {
      System.out.println("CPU time measurement not supported");
    }
    return cpuTimeNanos;
  }

  /**
   * O(n) loop doing simple addition; fewer operations keep the constant factor low.
   *
   * @param n upper bound for the summation
   * @return sum of the first n integers
   */
  public static long calculateSum(final long n) {
    long sum = 0;
    for (long i = 1; i <= n; i++) {
      sum += i;
    }
    return sum;
  }

  /**
   * Still O(n), but the extra multiplication per iteration adds CPU cost—mirrors real workloads
   * where heavier math per element matters even with identical Big-O growth.
   *
   * @param n upper bound for the summation
   * @return sum of squared integers up to n
   */
  public static long calculateSquareSum(final long n) {
    long sum = 0;
    for (long i = 1; i <= n; i++) {
      sum += i * i;
    }
    return sum;
  }
}
