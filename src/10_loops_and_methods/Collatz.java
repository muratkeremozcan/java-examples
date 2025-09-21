/**
 * A class that demonstrates the Collatz conjecture by generating the sequence starting from a given
 * number until it reaches 1. The conjecture states that for any positive integer n, this process
 * will always reach 1.
 */
public class Collatz {
  /**
   * Generates and prints the Collatz sequence starting from 1634. The sequence continues until
   * reaching 1, with each step printed. Finally, it prints the total number of steps taken.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    int n = 1634;
    int i = 0;

    System.out.println("Collatz sequence for " + n + ":");

    while (n != 1) {
      if (isEven(n)) {
        System.out.println(" is even");
        n /= 2;
      } else {
        System.out.println(" is odd");
        n = 3 * n + 1;
      }
      System.out.println(n);
      i++;
    }
    System.out.println("Number of steps: " + i);
  }

  /**
   * Checks if a number is even.
   *
   * @param n the number to check
   * @return true if the number is even, false otherwise
   */
  private static boolean isEven(final int n) {
    return n % 2 == 0;
  }
}
