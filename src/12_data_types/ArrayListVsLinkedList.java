import java.util.ArrayList;
import java.util.LinkedList;

// Quick takeaway: ArrayList is a resizable array - fast for indexed reads, slower for middle ops.
// O(1) random access, O(1) amortized add at end, O(n) add/remove elsewhere
// LinkedList is a doubly-linked list - fast at head/tail ops, slower for random access.
// O(n) random access, O(1) add/remove anywhere
// LinkedList uses more memory per element (for node pointers)

/** Quick demo that prints basic ArrayList and LinkedList operations. */
public class ArrayListVsLinkedList {
  /** Entry point that contrasts both list flavors. */
  public static void main(String[] args) {
    final var shopArrayList = new ArrayList<String>();
    shopArrayList.add("milk");
    shopArrayList.add("eggs");
    shopArrayList.add("bread");
    shopArrayList.add("milk");
    System.out.println(shopArrayList);

    shopArrayList.set(2, "rye-bread");
    shopArrayList.remove("milk");
    System.out.println(shopArrayList);
    System.out.println(shopArrayList.size());

    //////
    final var shopLinkedList = new LinkedList<String>();
    shopLinkedList.addLast("milk");
    shopLinkedList.addLast("eggs");
    shopLinkedList.addLast("bread");
    shopLinkedList.addFirst("milk");

    System.out.println(shopLinkedList);

    shopLinkedList.set(3, "rye-bread");
    shopLinkedList.remove("milk");
    System.out.println(shopLinkedList);
    System.out.println(shopLinkedList.size());

    ////////
    final var prices = new LinkedList<Double>();
    prices.add(5.60);
    prices.add(3.40);
    prices.addLast(9.65);
    prices.addFirst(1.35);
    System.out.println(prices);
    Double total = 0.0;

    for (Double price : prices) {
      total += price;
    }
    System.out.println("Average price: " + total / prices.size());
  }
}
