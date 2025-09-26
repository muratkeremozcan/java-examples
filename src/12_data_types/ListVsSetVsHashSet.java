import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.concurrent.ArrayBlockingQueue;

/*
 * List vs Set vs HashSet vs ArrayBlockingQueue
 * List: accepts duplicates, keeps insertion order with index access, allows multiple nulls.
 * List: relies on a resizable array backing (ArrayList).
 * Set (LinkedHashSet): stores unique values, preserves insertion order, allows at most one null.
 * Set (LinkedHashSet): centers around membership operations like contains or remove.
 * HashSet: stores unique values, iteration order is unspecified, allows at most one null.
 * HashSet: uses hashing for fast membership checks.
 * ArrayBlockingQueue: enforces a fixed capacity, keeps first-in-first-out order, disallows nulls.
 * ArrayBlockingQueue: thread-safe queue backed by an array; offer/poll manage capacity.
 */
public class ListVsSetVsHashSet {

  public static void main(String[] args) {
    demoList();
    demoSet();
    demoHashSet();
    demoArrayBlockingQueue();
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

    System.out.println("\nList heights: " + heightsList);
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

    System.out.println("\nLinkedHashSet heights: " + heightsSet);
    System.out.println("Contains 72? " + heightsSet.contains(72));
  }

  private static void demoHashSet() {
    final var heightsHashSet = new HashSet<>();
    heightsHashSet.add(72);
    heightsHashSet.add(64);
    heightsHashSet.add(66);
    heightsHashSet.add(null); // allows a single null entry
    // heightsHashSet.remove(64); // removing by value

    System.out.println("\nHashSet heights (order may change): " + heightsHashSet);
    System.out.println("Contains 64? " + heightsHashSet.contains(64));
  }

  private static void demoArrayBlockingQueue() {
    final var heightsQueue = new ArrayBlockingQueue<>(3);
    heightsQueue.add(72);
    heightsQueue.add(64);
    heightsQueue.add(66); // queue now at capacity

    boolean offeredExtra = heightsQueue.offer(70); // capacity reached, offer returns false
    System.out.println("\nArrayBlockingQueue heights: " + heightsQueue);
    System.out.println("Offer extra height succeeded? " + offeredExtra);
    System.out.println("Peek keeps oldest entry: " + heightsQueue.peek());
    System.out.println("Poll removes oldest entry: " + heightsQueue.poll());
    System.out.println("Queue after poll: " + heightsQueue);
    // heightsQueue.add(null); // would throw NullPointerException because nulls are not allowed
  }
}
