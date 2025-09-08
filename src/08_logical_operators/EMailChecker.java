public class EMailChecker {
  public static void main(String[] args) {
    final String address = "martin.doeb@datacamp.com";
    validateEmail(address);
  }

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

  private static boolean isValidEmailFormat(
      final String address, final int addLen, final boolean hasAt) {
    return (hasAt && address.charAt(addLen - 4) == '.')
        || (hasAt && (address.charAt(addLen - 3) == '.' || hasDotAfterAt(address)));
  }

  private static boolean hasDotAfterAt(final String address) {
    final int atPos = address.indexOf('@');
    final String subString = address.substring(atPos);
    return subString.contains(".");
  }
}
