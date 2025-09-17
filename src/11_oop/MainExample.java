/** Demonstrates basic Java OOP concepts including nested classes and access modifiers. */
public class MainExample {

  // Java supports nested classes
  // The static means it doesn't have access to Main's instance members
  // only the outer class can access the private members and methods, no others
  private static class MyCar {
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

    private void deployAirbags() {
      System.out.println("airbags deployed");
    }
  }

  public static final class Toyota extends MyCar {
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }
  }

  /**
   * Main method to demonstrate the usage of the MyCar class.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final MyCar myCar = new MyCar("red", "camry", 2019, 101_189);
    System.out.println(myCar.color);
    System.out.println(myCar.model);
    System.out.println(myCar.year);
    System.out.println(myCar.vehicleNumber);
    myCar.turnEngineOn();
    System.out.println(myCar.calculateMpg(180, 20));
    myCar.deployAirbags();

    System.out.println("\nChild class\n");
    final Toyota myToyota = new Toyota("black", "yaris", 2014, 201_489);
    System.out.println(myToyota.color);
    System.out.println(myToyota.model);
    System.out.println(myToyota.year);
    // System.out.println(myToyota.vehicleNumber); // can't access private member
    System.out.println(myToyota.getVehicleNumber());

    myToyota.turnEngineOn();
    System.out.println(myToyota.calculateMpg(180, 20));
    // myToyota.deployAirbags(); // can't access private method
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
