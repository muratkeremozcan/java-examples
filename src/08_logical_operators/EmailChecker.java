// Access modifiers:
// public: exposes to all
// protected: package (same folder structure) +subclasses
// private: to the declaring type
// static binds state/behavior to the class rather than instances.
// In these examples, Java has to keep `static` on the entry-point method and on the nested class
// so we can call them without materializing a `MainExample` instance.

// We need them here so only the entry point stays public while helper logic stays private.

/** A utility class for validating email addresses. */
public class EmailChecker {
  /**
   * Main method to demonstrate email validation.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final String address = "martin.doeb@datacamp.com";
    validateEmail(address);
  }

  /**
   * Validates an email address and prints the result.
   *
   * @param address the email address to validate
   */
  private static void validateEmail(final String address) {
    final int addLen = address.length();
    final boolean hasAt = address.contains("@");

    if (!hasAt) {
      System.out.println("Your email is missing the '@'");
      return;
    }

    if (isValidEmailFormat(address, addLen, hasAt)) {
      System.out.println("That's a correct email address");
    } else {
      System.out.println("That's not a valid email");
    }
  }

  /**
   * Checks if an email address has a valid format.
   *
   * @param address the email address to check
   * @param addLen the length of the email address
   * @param hasAt whether the email contains an '@' symbol
   * @return true if the email format is valid, false otherwise
   */
  private static boolean isValidEmailFormat(
      final String address, final int addLen, final boolean hasAt) {
    return (hasAt && address.charAt(addLen - 4) == '.')
        || (hasAt && (address.charAt(addLen - 3) == '.' || hasDotAfterAt(address)));
  }

  /**
   * Checks if there is a dot after the '@' in an email address.
   *
   * @param address the email address to check
   * @return true if there is a dot after the '@', false otherwise
   */
  private static boolean hasDotAfterAt(final String address) {
    final int atPos = address.indexOf('@');
    final String subString = address.substring(atPos);
    return subString.contains(".");
  }
}
