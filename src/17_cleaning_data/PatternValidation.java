import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Regex cheatsheet for later copy/paste.
 *
 * <ul>
 *   <li>Define pattern d-MMM-VY, like 4-Jan-22: Pattern datePattern = Pattern.compile(regex)</li>
 *   <li>Full pattern matching (does date match datePattern?) 
 *     boolean matchesPattern = datePattern.matcher(date).matches()</li>
 *   <li>Pattern finding (does date have a month substring?) 
 *      boolean hasMonth = Pattern.compile(regex).matcher(date).find();</li>
 *   <li>Character matching (does date have digits?) 
 *      boolean hasDigits = CharMatcher.digit().matchesAny0f(date);</li>
 * </ul>
 */
public class PatternValidation {
  public static void main(String[] args) {
    validatePurchaseDates();
    extractPlatformVersions();
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
        // group() surfaces whatever substring satisfied the pattern (here, the console version).
        System.out.println(platform + ": version " + matcher.group());
      } else {
        System.out.println(platform + ": no version number");
      }
    }
  }
}
