import com.google.common.base.CharMatcher;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex + CharMatcher cheatsheet.
 *
 * <ul>
 *   <li>{@code Pattern.compile("\\d{1,2}/\\d{1,2}/\\d{4}");} // MM/DD/YYYY matcher</li>
 *   <li>{@code matcher.matches();} // require the whole string to match</li>
 *   <li>{@code matcher.find(); matcher.group();} // pull the first matching substring</li>
 *   <li>{@code CharMatcher.inRange('0','9').retainFrom(input);} // keep only ASCII digits</li>
 *   <li>{@code CharMatcher.is('.').matchesAnyOf(input);} // cheap single-character check</li>
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
      // find() tells us whether any digit exists (PlayStation 5, etc.).
      if (matcher.find()) {
        // Retain only ASCII digits so the suffix is easy to log or compare.
        String digitsOnly = CharMatcher.inRange('0', '9').retainFrom(platform);
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
