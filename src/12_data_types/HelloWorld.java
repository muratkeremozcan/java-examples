import java.math.BigDecimal;

/** Demonstrates BigDecimal operations and primitive arithmetic. */
public class HelloWorld {

  /**
   * Example entry point that mirrors the original POJO interaction sample.
   *
   * @param args command line arguments (unused)
   */
  public static void main(final String[] args) {
    double d = 9.123_456_789;
    // Create a BigDecimal with the value of 9.123456789
    BigDecimal bigDec = new BigDecimal("9.123456789");
    final BigDecimal ten = BigDecimal.TEN;
    final BigDecimal hundred = BigDecimal.valueOf(100);
    d = d * 10;
    d = d / 100;
    // Multiply bigDec by ten
    bigDec = bigDec.multiply(ten);
    // Divide bigDec by hundred
    bigDec = bigDec.divide(hundred);
    System.out.println(d);
    System.out.println(bigDec);
  }
}
