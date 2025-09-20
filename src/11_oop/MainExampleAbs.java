// Takeaways (Abstract class):
// - Abstract classes can mix concrete + abstract methods; you cannot instantiate them.
// - Subclasses must implement abstract methods and may `@Override` concrete ones.
// - Use when you want shared state/behavior with extension points (single inheritance).

/**
 * Demonstrates the use of abstract classes in Java. This example shows how to define an abstract
 * class with both concrete and abstract methods, and how to create concrete subclasses that extend
 * it.
 */
public class MainExampleAbs {

  // Abstract class relaxes the inheritance, by having abstract methods
  // that the children have to implement
  // it also allows @Override to be used on methods that are already implemented
  private abstract static class MyCar {
    public final String color;
    public final String model;
    public final int year;
    private final int vehicleNumber;

    public MyCar(final String color, final String model, final int year, final int vehicleNumber) {
      this.color = color;
      this.model = model;
      this.year = year;
      this.vehicleNumber = vehicleNumber;
    }

    public void turnEngineOn() {
      System.out.println("engine is on");
    }

    public int getVehicleNumber() {
      return vehicleNumber;
    }

    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return milesDriven / gallonsUsed;
    }

    // This is the only abstract method that subclasses must implement
    public abstract String getCarType();
  }

  // Concrete implementation
  /** A concrete implementation of MyCar representing a Toyota vehicle. */
  public static final class Toyota extends MyCar {
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    // need to implement the abstract method
    @Override
    public String getCarType() {
      return "Sedan";
    }
  }

  // Another concrete implementation
  /** A concrete implementation of MyCar representing a Tesla electric vehicle. */
  public static final class Tesla extends MyCar {
    public Tesla(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    // need to implement the abstract method
    @Override
    public String getCarType() {
      return "Electric";
    }

    // optionally can override other concrete methods
    @Override
    public void turnEngineOn() {
      System.out.println("Starting electric motors...");
    }

    @Override
    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return (int) (milesDriven * 3.5);
    }
  }

  /**
   * Main method to demonstrate the usage of abstract classes and their implementations.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Can't instantiate abstract class directly
    // MyCar car = new MyCar("red", "generic", 2020, 100_000); // Compile error!

    // Using the Toyota implementation
    final Toyota toyota = new Toyota("red", "Camry", 2019, 101_189);
    System.out.println(toyota.color);
    System.out.println(toyota.model);
    System.out.println(toyota.year);
    System.out.println(toyota.getVehicleNumber()); // Can't access vehicleNumber directly (private)
    toyota.turnEngineOn();
    System.out.println("MPG: " + toyota.calculateMpg(200, 10));
    System.out.println("Car type: " + toyota.getCarType());

    // Using the Tesla implementation
    System.out.println("\nTesla:");
    final Tesla tesla = new Tesla("black", "Model 3", 2022, 201_489);
    System.out.println(tesla.color);
    System.out.println(tesla.model);
    System.out.println(tesla.year);
    System.out.println(tesla.getVehicleNumber());
    tesla.turnEngineOn(); // Different implementation
    System.out.println("MPGe: " + tesla.calculateMpg(200, 1)); // Different calculation
    System.out.println("Car type: " + tesla.getCarType());
  }
}
