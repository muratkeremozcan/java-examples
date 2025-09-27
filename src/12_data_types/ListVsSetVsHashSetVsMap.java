import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * List vs Set vs HashSet vs ArrayBlockingQueue vs Map (HashMap)
 *
 * List (ArrayList): accepts duplicates, allows multiple nulls,keeps insertion order with index access.
 * Ordered, indexed collection of elements, performs better in adding and removing elements.
 *
 * Set (LinkedHashSet): stores unique values, allows at most one null, preserves insertion order.
 * Unordered, unique elements, performs better in searching for elements.
 *
 * HashSet: stores unique values, allows at most one null, iteration order is unspecified, fast access.
 *
 * ArrayBlockingQueue: fixed capacity, keeps FIFO order, disallows nulls.
 * thread-safe queue backed by an array; offer/poll manage capacity.
 *
 * Map (HashMap): stores key/value pairs, keys are unique, allows one null key and null values.
 * Key&Value pairs stored in a look-up table optimized for key lookups; putting an existing key overwrites its value.
 */

/** Compares List, Set, HashSet, ArrayBlockingQueue, and Map using the same heights example. */
public class ListVsSetVsHashSetVsMap {

  public static void main(String[] args) {
    demoList();
    demoSet();
    demoHashSet();
    demoArrayBlockingQueue();
    demoMap();
  }

  private static void demoList() {
    final var heightsList = new ArrayList<>();
    heightsList.add(72);
    heightsList.add(64);
    heightsList.add(66);
    heightsList.add(64); // duplicates are retained
    heightsList.add(null); // multiple nulls allowed
    // heightsList.remove(Integer.valueOf(72)); // Remove by value 72
    // heightsList.remove(0); // Remove first element (which is 72)

    System.out.println("\nList:" + heightsList);
    System.out.println(
        "accepts duplicates, allows multiple nulls,keeps insertion order with index access.");
    System.out.println("Index 1 holds: " + heightsList.get(1));
  }

  private static void demoSet() {
    final var heightsSet = new LinkedHashSet<>();
    heightsSet.add(72);
    heightsSet.add(64);
    heightsSet.add(66);
    heightsSet.add(64); // duplicate ignored silently
    heightsSet.remove(64); // removing by value
    heightsSet.add(null); // only a single null allowed

    System.out.println("\nLinkedHashSet:" + heightsSet);
    System.out.println("stores unique values, allows at most one null, preserves insertion order.");
    System.out.println("Contains 72? " + heightsSet.contains(72));
  }

  private static void demoHashSet() {
    final var heightsHashSet = new HashSet<>();
    heightsHashSet.add(72);
    heightsHashSet.add(64);
    heightsHashSet.add(66);
    heightsHashSet.add(null); // allows a single null entry
    // heightsHashSet.remove(64); // removing by value

    System.out.println("\nHashSet (order may change): " + heightsHashSet);
    System.out.println(
        "stores unique values, allows at most one null, iteration order is unspecified, fast"
            + " access.");
    System.out.println("Contains 64? " + heightsHashSet.contains(64));
  }

  private static void demoArrayBlockingQueue() {
    final var heightsQueue = new ArrayBlockingQueue<>(3);
    heightsQueue.add(72);
    heightsQueue.add(64);
    heightsQueue.add(66); // queue now at capacity

    boolean offeredExtra = heightsQueue.offer(70); // capacity reached, offer returns false
    System.out.println("\nArrayBlockingQueue:" + heightsQueue);
    System.out.println("fixed capacity, keeps FIFO order, disallows nulls.");
    System.out.println("Offer extra height succeeded? " + offeredExtra);
    System.out.println("Peek keeps oldest entry: " + heightsQueue.peek());
    System.out.println("Poll removes oldest entry: " + heightsQueue.poll());
    System.out.println("Queue after poll: " + heightsQueue);
    // heightsQueue.add(null); // would throw NullPointerException because nulls are not allowed
  }

  private static void demoMap() {
    final var heightsByName = new HashMap<String, Integer>();
    heightsByName.put("Alice", 72);
    heightsByName.put("Ben", 64);
    heightsByName.put("Clair", 66);
    heightsByName.put("Ben", 65); // overwrites existing key
    heightsByName.put(null, 70); // HashMap allows a single null key
    heightsByName.put("Dana", null); // and null values
    heightsByName.remove("Eve"); // remove by key

    System.out.println("\nHashMap:" + heightsByName);
    System.out.println(
        "stores key/value pairs, keys are unique, allows one null key and null values.");
    System.out.println("Ben's recorded height: " + heightsByName.get("Ben"));
    System.out.println("Contains Clair? " + heightsByName.containsKey("Clair"));
  }
}
