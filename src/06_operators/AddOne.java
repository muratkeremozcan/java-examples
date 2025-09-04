/** Demonstrates increment and decrement operations. */
public class AddOne {
  /**
   * Main method to demonstrate age and days count operations.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    int age = 30;
    int days = 20;
    System.out.println("I'm " + age + " today.");

    age++;
    days--;

    System.out.println("But I'll be " + age + " soon!");
    System.out.println("Only " + days + " to go!");
  }
}
