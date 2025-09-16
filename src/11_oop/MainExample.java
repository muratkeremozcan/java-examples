/** Demonstrates basic Java OOP concepts including nested classes and access modifiers. */
public class MainExample {

  // Java supports nested classes
  // The static means it doesn't have access to Main's instance members
  // only the outer class can access the private members and methods, no others
  private static final class MyCar {
    public final String color;
    public final String model;
    public final int year;
    private final int vehicleNumber;

    public MyCar() {
      this.model = "camry";
      this.color = "red";
      this.year = 2019;
      this.vehicleNumber = 101_189;
    }

    public void turnEngineOn() {
      System.out.println("engine is on");
    }

    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      return milesDriven / gallonsUsed;
    }

    private void deployAirbags() {
      System.out.println("airbags deployed");
    }
  }

  /**
   * Main method to demonstrate the usage of the MyCar class.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final MyCar myCar = new MyCar();
    System.out.println(myCar.color);
    System.out.println(myCar.model);
    System.out.println(myCar.year);
    System.out.println(myCar.vehicleNumber);
    myCar.turnEngineOn();
    System.out.println(myCar.calculateMpg(180, 20));
    myCar.deployAirbags();
  }
}

/* TS equivalent
// Main.ts

class Car {
  // Shorthand constructor with parameter properties
  constructor(
    public readonly color: string = "red",
    public readonly model: string = "camry",
    public readonly year: number = 2019,
    private readonly vehicleNumber: number = 101_189
  ) {}

  // Public method
  public turnEngineOn(): void {
    console.log("engine is on");
  }

  // Public method with parameters and return type
  public calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return milesDriven / gallonsUsed;
  }

  // Private method (only accessible within the class)
  private deployAirbags(): void {
    console.log("airbags deployed");
  }
}

// Usage - now we can create with parameters or use defaults
const myCar = new Car(); // Uses all default values
console.log(myCar.color); // "red"
console.log(myCar.model); // "camry"

// Or specify values
const otherCar = new Car("blue", "corolla", 2023, 202_345);
console.log(otherCar.color); // "blue"

*/
