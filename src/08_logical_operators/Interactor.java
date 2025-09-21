/** Simple switch example for object interactions. */
public class Interactor {

  /** Entry point demonstrating a switch over possible objects. */
  public static void main(String[] args) {
    final String object = "Wall";

    switch (object) {
      case "Door":
        System.out.println("You have discovered a new room");
        break;
      case "Fridge":
        System.out.println("The food is all yours");
        break;
      case "Wall":
      case "Window":
        System.out.println("I can't go through that");
        break;
      default:
        System.out.println("This object cannot be interacted with");
        break;
    }
  }
}
