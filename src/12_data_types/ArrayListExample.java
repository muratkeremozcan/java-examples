// import java.util.*; // would import all
import java.util.ArrayList;

// Arrays vs Collections
// Not resizable - Dynamically sized
// Stores primitives or objects - stores only objects
// elements must be of same type - can store different types of elements
// [] to access elements - uses methods to access elements
// {} syntax for initialization - constructor for initialization

/** Demonstrates basic differences between arrays and ArrayList. */
public class ArrayListExample {

  /** Entry point that showcases simple List operations. */
  public static void main(String[] args) {
    var list = new ArrayList<String>(); // array equivalent would be String[]
    list.add("hello");
    list.add("world");
    System.out.println(list);
  }
}
