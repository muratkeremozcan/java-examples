import com.google.common.base.CharMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 *
 * <ul>
 *   <li>Define pattern d-MMM-VY, like 4-Jan-22: Pattern datePattern = Pattern.compile(regex)
 *   <li>Full pattern matching (does date match datePattern?) boolean matchesPattern =
 *       datePattern.matcher(date).matches()
 *   <li>Pattern finding (does date have a month substring?) boolean hasMonth =
 *       Pattern.compile(regex).matcher(date).find();
 *   <li>Character matching (does date have digits?) boolean hasDigits =
 *       CharMatcher.digit().matchesAny0f(date);
 *   <li>CharMatcher.digit().retainFrom(input) // grab only digits from messy strings
 * </ul>
 */
public class PatternValidation {
  /** Run the regex and CharMatcher demonstrations. */
  public static void main(String[] args) {
    validatePurchaseDates();
    extractPlatformVersions();
    vetQuantityCharacters();
  }

  /** Validate that dates follow an expected MM/DD/YYYY style. */
  private static void validatePurchaseDates() {
    List<String> dates = Arrays.asList("11/19/2006", "11/13/1985", "4/10/2008", "2008-04-10");
    // Compile once: Pattern caches the regex engine for reuse.
    Pattern slashDate = Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");

    for (String date : dates) {
      // matches() enforces a full-string match (no need for ^ and $ when using Matcher#matches).
      boolean isValid = slashDate.matcher(date).matches();
      System.out.println(date + " is valid: " + isValid);
    }
  }

  /** Find and report the console generation hinted in a platform title. */
  private static void extractPlatformVersions() {
    List<String> platforms = Arrays.asList("PlayStation 5", "PlayStation 4", "Xbox One", "Switch");
    // \d matches any digit; keep the pattern tiny when you only need one number token.
    Pattern singleDigit = Pattern.compile("\\d");

    for (String platform : platforms) {
      Matcher matcher = singleDigit.matcher(platform);
      // find() scans for the first occurrence without rejecting non-digit prefixes/suffixes.
      if (matcher.find()) {
        // jdk 21: digit() covers BMP digits; ASCII is enough for these console labels.
        String digitsOnly = CharMatcher.inRange('0', '9').retainFrom(platform);
        // group() surfaces the first match; digitsOnly keeps the full numeric suffix if present.
        System.out.println(platform + ": version " + digitsOnly);
      } else {
        System.out.println(platform + ": no version number");
      }
    }
  }

  /** Quick CharMatcher checks for numeric strings that allow decimals only. */
  private static void vetQuantityCharacters() {
    List<String> quantities = Arrays.asList("41.49", "29.08", "15.85", "12.5x");

    for (String quantity : quantities) {
      // inRange('0','9') ensures at least one digit shows up (filters blank/symbol-only input).
      boolean hasDigit = CharMatcher.inRange('0', '9').matchesAnyOf(quantity);
      // simple decimal guard; CharMatcher#is avoids tiny regexes when one character matters.
      boolean hasDecimalPoint = CharMatcher.is('.').matchesAnyOf(quantity);
      // Combine ranges with or() to flag any alphabetic noise in price fields.
      boolean hasAlpha =
          CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z')).matchesAnyOf(quantity);

      boolean isValid = hasDigit && hasDecimalPoint && !hasAlpha;
      System.out.println(quantity + " has valid characters: " + isValid);
    }
  }
}
