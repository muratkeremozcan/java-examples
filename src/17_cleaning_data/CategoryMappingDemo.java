import java.util.Collections;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

/**
 * Key takeaways.
 *
 * <ul>
 *   <li>Normalize UI strings into one enum so reports and rules work on real categories.</li>
 *   <li>Absorb typos/casing upfront; Map#getOrDefault gives a safe fallback bucket.</li>
 *   <li>Aggregate raw counts into canonical categories with EnumMap + Map#merge.</li>
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
    showCategoryLookup();
    showCategoryAggregation();
  }

  private static void showCategoryLookup() {
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

  /** Aggregate raw counts into per-category totals using the same canonical map. */
  private static void showCategoryAggregation() {
    // Reuse the normalization table so noisy strings land in the right bucket.
    Map<String, ProductCategory> categoryMap = new HashMap<>();
    categoryMap.put("Home Appliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("home appliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("HomeAppliances", ProductCategory.HOME_APPLIANCES);
    categoryMap.put("Electronics", ProductCategory.ELECTRONICS);
    categoryMap.put("electronics", ProductCategory.ELECTRONICS);
    categoryMap.put("Electronic", ProductCategory.ELECTRONICS);

    // Raw counts keyed by whatever string the upstream system emitted.
    Map<String, Integer> rawData =
        Map.of(
            "Home Appliances", 10,
            "home appliances", 20,
            "HomeAppliances", 30,
            "Electronics", 10,
            "electronics", 20,
            "Electronic", 10);

    // Use EnumMap so the result is keyed by the canonical enum and stays type-safe.
    Map<ProductCategory, Integer> stockByCategory = new EnumMap<>(ProductCategory.class);

    // Fold all the raw counts into category totals. merge() either inserts or sums dupes.
    rawData.forEach(
        (raw, quantity) -> stockByCategory.merge(categoryMap.get(raw), quantity, Integer::sum));

    System.out.println("Stock by category:" + stockByCategory);
  }
}
