// - Interfaces define a contract; classes `implements` it.
// - No fields and state here; keep data in the class.

// With interfaces, it is reversed from abstract classes
// default methods are like the concrete methods in abstract classes  (do not have to be implemented
// in the children)
// the rest of the interface methods are like the abstract methods in abstract classes (have to be
// implemented in the children)

// Why use one over the other?
// in short; you can implement multiple interfaces in a class
// but abstract methods have reusable state; interfaces cannot hold instance state; abstract classes
// can share fields/constructors.

/**
 * Demonstrates the use of interfaces in Java. This example shows how to define interfaces and
 * implement them in classes, and how it compares to using abstract classes.
 */
public class MainExampleInt {

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

  /**
   * Defines the contract for vehicle implementations. This interface specifies the common behaviors
   * and properties that all vehicle types must implement.
   */
  public interface Vehicle {

    // KEY: the default methods in interfaces do not have to be implemented
    // these are like concrete methods in abstract classes
    default void turnEngineOn() {
      System.out.println("engine is on");
    }

    default int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return milesDriven / gallonsUsed;
    }

    // All interface methods are public abstract by default
    // these are like abstract methods in abstract classes; they have to be implemented
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
    // KEY: interfaces do not inherit state; have to have a constructor and fields
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

    // KEY: interfaces do not inherit methods; have to implement them
    // we only didn't have to implement the default methods in the interface
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
    // KEY: interfaces do not inherit state; have to have a constructor and fields
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

    // KEY: interfaces do not inherit methods; have to implement them
    // we only didn't have to implement the default methods in the interface
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
}

/* TS equivalent
// KEY: in TS, interfaces are structural and can't ship default method bodies.
//  We either repeat the shared logic in each implementer
// or factor it into a helper/base class.
// `readonly` gives us Java's `final` fields.

interface Vehicle {
  readonly color: string;
  readonly model: string;
  readonly year: number;
  readonly vehicleNumber: number;
  turnEngineOn(): void;
  calculateMpg(milesDriven: number, gallonsUsed: number): number;
  getCarType(): string;
}

class Toyota implements Vehicle {
  constructor(
    public readonly color: string,
    public readonly model: string,
    public readonly year: number,
    public readonly vehicleNumber: number,
  ) {}

  turnEngineOn(): void {
    console.log('engine is on');
  }

  calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return Math.floor(milesDriven / gallonsUsed);
  }

  getCarType(): string {
    return 'Sedan';
  }
}

class Tesla implements Vehicle {
  constructor(
    public readonly color: string,
    public readonly model: string,
    public readonly year: number,
    public readonly vehicleNumber: number,
  ) {}

  turnEngineOn(): void {
    console.log('Starting electric motors...');
  }

  calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return Math.floor(milesDriven * 3.5);
  }

  getCarType(): string {
    return 'Electric';
  }
}

function main(): void {
  const toyota: Vehicle = new Toyota('red', 'Camry', 2019, 101189);
  console.log(toyota.getCarType());

  const tesla: Vehicle = new Tesla('black', 'Model 3', 2022, 201489);
  console.log(tesla.getCarType());
}

main();
*/
