import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Key takeaways.
 *
 * <ul>
 *   <li>Normalize noisy category strings into a canonical enum so the rest of the app sees
 *       consistent values.
 *   <li>Seed the lookup table with common typos and casing variations to absorb messy input data.
 *   <li>Use Map#getOrDefault to funnel unknown values into an explicit fallback bucket.
 *   <li>Wrap shared lookup tables with Collections.unmodifiableMap to guard them against changes.
 * </ul>
 */
public class CategoryMappingDemo {

  /** Canonical categories the import process should emit. */
  public enum ProductCategory {
    ELECTRONICS,
    HOME_APPLIANCES,
    UNCATEGORIZED;
  }

  /** Demo mapping messy category inputs into canonical enums. */
  public static void main(String[] args) {
    // Align real-world spellings with the enum our downstream code expects.
    Map<String, ProductCategory> categoryMap = new HashMap<>();
    categoryMap.put("Home Appliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("home appliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("HomeAppliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("Electronics", ProductCategory.ELECTRONICS);
    categoryMap.put("electronics", ProductCategory.ELECTRONICS);
    categoryMap.put("Electronic", ProductCategory.ELECTRONICS);

    System.out.println("Electronics category:");
    System.out.println(categoryMap.get("Electronics"));

    // Unknown input falls back to a safe catch-all for later manual review.
    ProductCategory unknownCategory =
        categoryMap.getOrDefault("Mystery category", ProductCategory.UNCATEGORIZED);

    System.out.println("\nMystery category:");
    System.out.println(unknownCategory);

    // Freeze the map so shared code cannot accidentally mutate the lookup table.
    Map<String, ProductCategory> immutableMap = Collections.unmodifiableMap(categoryMap);

    System.out.println("\nAttempting to modify immutable map:");
    try {
      immutableMap.put("new", ProductCategory.ELECTRONICS);
    } catch (UnsupportedOperationException e) {
      System.out.println("Cannot modify immutable map");
    }
  }
}
