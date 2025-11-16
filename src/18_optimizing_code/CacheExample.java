import java.util.HashMap;
import java.util.Map;

/**
 * Tiny LRU (last recently used) cache sketch; shows the usual time-vs-memory trade-off: cached hits
 * are O(1) but you burn memory. Keeping a bounded capacity and evicting the least-recently-used
 * entry prevents the cache from growing without limit.
 *
 * <p>DataCamp lesson gist:
 *
 * <ul>
 *   <li>Caching = keep hot results on the “counter” for fast reuse, but counter space is limited.
 *   <li>In-memory caches (like this HashMap-based one) need eviction and expiration policies to
 *       avoid bloat or stale data; LRU is one common choice.
 *   <li>Distributed apps usually lean on Redis via Jedis or similar: you still call `get`/`put`,
 *       but Redis enforces TTLs and shares data across servers.
 * </ul>
 */
public class CacheExample {

  private final int capacity;
  private final Map<String, CacheEntry> cache = new HashMap<>();

  // constructor overloading: how you do optional parameters in Java
  /** Builds a cache with the default capacity of 100 entries. */
  public CacheExample() {
    this(100);
  }

  /** Allows callers to tweak capacity for experiments. */
  public CacheExample(final int capacity) {
    this.capacity = capacity;
  }

  // TypeScript equivalent using optional parameter
  // class CacheExample {
  //   constructor(private capacity: number = 100) {
  //   }
  // }

  /**
   * Retrieves a value and bumps its access timestamp (so it stays “hot” in the cache).
   *
   * @param key cache key
   * @return cached value or {@code null} when it is a miss
   */
  public String get(final String key) {
    String value = null;
    CacheEntry entry = cache.get(key);
    if (entry != null) {
      entry.updateAccessTime();
      value = entry.value;
    }
    return value;
  }

  /**
   * Stores a value and triggers eviction when we exceed the configured capacity.
   *
   * @param key cache key
   * @param value cached value
   */
  public void put(final String key, final String value) {
    cache.put(key, new CacheEntry(value));
    if (cache.size() > capacity) {
      removeLeastRecentlyUsed();
    }
  }

  private void removeLeastRecentlyUsed() {
    String lruKey = null;
    long oldest = Long.MAX_VALUE;
    for (Map.Entry<String, CacheEntry> entry : cache.entrySet()) {
      if (entry.getValue().lastAccessed < oldest) {
        oldest = entry.getValue().lastAccessed;
        lruKey = entry.getKey();
      }
    }
    if (lruKey != null) {
      cache.remove(lruKey);
    }
  }

  private static final class CacheEntry {
    private final String value;
    private long lastAccessed;

    private CacheEntry(final String value) {
      this.value = value;
      updateAccessTime();
    }

    private void updateAccessTime() {
      lastAccessed = System.currentTimeMillis();
    }
  }
}
