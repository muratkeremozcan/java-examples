// - Interfaces define a contract; classes `implements` it (Java is nominal, not structural).
// - Java 8+ allows `default` methods with bodies; classes can override them.
// - Prefer interfaces for capabilities; you can implement many interfaces.
// - No fields with state here (only constants); keep data in the class.

/**
 * Demonstrates the use of interfaces in Java. This example shows how to define interfaces and
 * implement them in classes, and how it compares to using abstract classes.
 */
public class MainExampleInt {

  /**
   * Defines the contract for vehicle implementations. This interface specifies the common behaviors
   * and properties that all vehicle types must implement.
   */
  public interface Vehicle {

    // Default methods in interfaces (Java 8+)
    // the default methods in interfaces do not have to be implemented
    default void turnEngineOn() {
      System.out.println("engine is on");
    }

    default int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return milesDriven / gallonsUsed;
    }

    // All interface methods are public abstract by default
    int getVehicleNumber();

    String getColor();

    String getModel();

    int getYear();

    String getCarType();
  }

  // Concrete implementation of Car interface
  /**
   * Represents a Toyota vehicle implementation. This class provides a concrete implementation of
   * the Vehicle interface for Toyota cars.
   */
  @SuppressWarnings("PMD.DataClass")
  public static final class Toyota implements Vehicle {
    private final String color;
    private final String model;
    private final int year;
    private final int vehicleNumber;

    /**
     * Constructs a new Toyota instance with the specified properties.
     *
     * @param color the color of the vehicle
     * @param model the model of the vehicle
     * @param year the manufacturing year of the vehicle
     * @param vehicleNumber the unique identification number of the vehicle
     */
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      this.color = color;
      this.model = model;
      this.year = year;
      this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getColor() {
      return color;
    }

    @Override
    public String getModel() {
      return model;
    }

    @Override
    public int getYear() {
      return year;
    }

    @Override
    public int getVehicleNumber() {
      return vehicleNumber;
    }

    @Override
    public String getCarType() {
      return "Sedan";
    }
  }

  /**
   * Represents a Tesla electric vehicle implementation. This class provides a concrete
   * implementation of the Vehicle interface for Tesla cars, including electric vehicle specific
   * behaviors.
   */
  public static final class Tesla implements Vehicle {
    private final String color;
    private final String model;
    private final int year;
    private final int vehicleNumber;

    /**
     * Constructs a new Tesla instance with the specified properties.
     *
     * @param color the color of the vehicle
     * @param model the model of the vehicle
     * @param year the manufacturing year of the vehicle
     * @param vehicleNumber the unique identification number of the vehicle
     */
    public Tesla(final String color, final String model, final int year, final int vehicleNumber) {
      this.color = color;
      this.model = model;
      this.year = year;
      this.vehicleNumber = vehicleNumber;
    }

    @Override
    public String getColor() {
      return color;
    }

    @Override
    public String getModel() {
      return model;
    }

    @Override
    public int getYear() {
      return year;
    }

    @Override
    public int getVehicleNumber() {
      return vehicleNumber;
    }

    @Override
    public String getCarType() {
      return "Electric";
    }

    @Override
    public void turnEngineOn() {
      System.out.println("Starting electric motors...");
    }

    @Override
    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return (int) (milesDriven * 3.5); // MPGe calculation
    }
  }

  /**
   * Main method to demonstrate the usage of Vehicle interface and its implementations. Creates
   * instances of different vehicle types and demonstrates their behaviors.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    // Using the Toyota implementation
    System.out.println("=== Toyota Car ===");
    final Vehicle toyota = new Toyota("red", "Camry", 2019, 101_189);
    System.out.println(toyota.getColor());
    System.out.println(toyota.getModel());
    System.out.println(toyota.getYear());
    System.out.println(toyota.getVehicleNumber());
    toyota.turnEngineOn();
    System.out.println("MPG: " + toyota.calculateMpg(200, 10));
    System.out.println("Car type: " + toyota.getCarType());

    // Using the Tesla implementation
    System.out.println("\n=== Tesla Car ===");
    final Vehicle tesla = new Tesla("black", "Model 3", 2022, 201_489);
    System.out.println(tesla.getColor());
    System.out.println(tesla.getModel());
    System.out.println(tesla.getYear());
    System.out.println(tesla.getVehicleNumber());
    tesla.turnEngineOn(); // Different implementation
    System.out.println("MPGe: " + tesla.calculateMpg(200, 1)); // Different calculation
    System.out.println("Car type: " + tesla.getCarType());
  }
}
