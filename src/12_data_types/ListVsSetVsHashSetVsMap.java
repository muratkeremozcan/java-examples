import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Compares List, Set, HashSet, ArrayBlockingQueue, and Map using the same height measurements.
 *
 * <p>Focuses on how each structure handles ordering, duplicates, null handling, and capacity.
 */
public class ListVsSetVsHashSetVsMap {

  /** Runs the demonstrations for each collection type. */
  public static void main(String[] args) {
    demoList();
    demoSet();
    demoHashSet();
    demoArrayBlockingQueue();
    demoMap();
  }

  /** Shows how ArrayList keeps insertion order and accepts duplicates. */
  @SuppressWarnings("PMD.LooseCoupling")
  private static void demoList() {
    ArrayList<Integer> heightsList = new ArrayList<>(); // NOPMD - concrete type for illustration
    heightsList.add(72);
    heightsList.add(64);
    heightsList.add(66);
    heightsList.add(64); // duplicates are retained
    heightsList.add(null); // multiple nulls allowed

    System.out.println("\nList: " + heightsList);
    System.out.println("Index 1 holds: " + heightsList.get(1));
  }

  /** Demonstrates LinkedHashSet uniqueness with predictable iteration order. */
  @SuppressWarnings("PMD.LooseCoupling")
  private static void demoSet() {
    LinkedHashSet<Integer> heightsSet = new LinkedHashSet<>(); // NOPMD - concrete type for lesson
    heightsSet.add(72);
    heightsSet.add(64);
    heightsSet.add(66);
    heightsSet.add(64); // duplicate ignored silently
    heightsSet.remove(64); // remove by value
    heightsSet.add(null); // only a single null allowed

    System.out.println("\nLinkedHashSet: " + heightsSet);
    System.out.println("Contains 72? " + heightsSet.contains(72));
  }

  /** Illustrates hash-based uniqueness without guaranteed ordering. */
  @SuppressWarnings("PMD.LooseCoupling")
  private static void demoHashSet() {
    HashSet<Integer> heightsHashSet = new HashSet<>(); // NOPMD - concrete type for lesson
    heightsHashSet.add(72);
    heightsHashSet.add(64);
    heightsHashSet.add(66);
    heightsHashSet.add(null); // allows a single null entry

    System.out.println("\nHashSet: " + heightsHashSet);
    System.out.println("Contains 64? " + heightsHashSet.contains(64));
  }

  /** Highlights fixed-capacity FIFO behaviour with ArrayBlockingQueue. */
  @SuppressWarnings("PMD.LooseCoupling")
  private static void demoArrayBlockingQueue() {
    ArrayBlockingQueue<Integer> heightsQueue =
        new ArrayBlockingQueue<>(3); // NOPMD - concrete type keeps message clear
    heightsQueue.add(72);
    heightsQueue.add(64);
    heightsQueue.add(66); // queue now at capacity

    boolean offeredExtra = heightsQueue.offer(70); // capacity reached, offer returns false
    System.out.println("\nArrayBlockingQueue: " + heightsQueue);
    System.out.println("Offer extra height succeeded? " + offeredExtra);
    System.out.println("Peek keeps oldest entry: " + heightsQueue.peek());
    System.out.println("Poll removes oldest entry: " + heightsQueue.poll());
    System.out.println("Queue after poll: " + heightsQueue);
  }

  /** Demonstrates key/value lookups and overwrites in HashMap. */
  @SuppressWarnings({"PMD.LooseCoupling", "PMD.UseConcurrentHashMap"})
  private static void demoMap() {
    HashMap<String, Integer> heightsByName = new HashMap<>(); // NOPMD - concrete type for lesson
    heightsByName.put("Alice", 72);
    heightsByName.put("Ben", 64);
    heightsByName.put("Clair", 66);
    heightsByName.put("Ben", 65); // overwrites existing key
    heightsByName.put(null, 70); // HashMap allows a single null key
    heightsByName.put("Dana", null); // and null values

    System.out.println("\nHashMap: " + heightsByName);
    System.out.println("Ben's recorded height: " + heightsByName.get("Ben"));
    System.out.println("Contains Clair? " + heightsByName.containsKey("Clair"));
  }
}
