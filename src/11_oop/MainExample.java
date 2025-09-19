// - A `static` nested class cannot access the outer instance.
// - The outer class *can* access the nested class’s private members; outsiders cannot.
// - `private` truly hides members across package/subclass boundaries (use a getter).
// - `final` fields are set once (like `readonly` in TS).

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

// Abstract class example (similar to MainExampleAbs.java)
abstract class Car {
  constructor(
    public readonly color: string,
    public readonly model: string,
    public readonly year: number,
    private readonly vehicleNumber: number
  ) {}

  // Concrete method with implementation
  public turnEngineOn(): void {
    console.log("engine is on");
  }

  // Abstract method that subclasses must implement
  public abstract getCarType(): string;

  // Another concrete method
  public calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return milesDriven / gallonsUsed;
  }
}

// Concrete implementation (Toyota)
class Toyota extends Car {
  constructor(color: string, model: string, year: number, vehicleNumber: number) {
    super(color, model, year, vehicleNumber);
  }

  public getCarType(): string {
    return "Sedan";
  }
}

// Interface example (similar to MainExampleInt.java)
interface ICar {
  readonly color: string;
  readonly model: string;
  readonly year: number;
  getVehicleNumber(): number;
  getCarType(): string;
  turnEngineOn(): void;
  calculateMpg(milesDriven: number, gallonsUsed: number): number;
}

// Class implementing the interface
class Tesla implements ICar {
  constructor(
    public readonly color: string,
    public readonly model: string,
    public readonly year: number,
    private readonly vehicleNumber: number
  ) {}

  public getVehicleNumber(): number {
    return this.vehicleNumber;
  }

  public getCarType(): string {
    return "Electric";
  }

  public turnEngineOn(): void {
    console.log("Starting electric motors...");
  }

  public calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return milesDriven * 3.5; // MPGe calculation
  }
}

// Usage examples
console.log("=== Abstract Class Example ===");
const camry = new Toyota("red", "Camry", 2019, 101189);
console.log(camry.color); // "red"
console.log(camry.getCarType()); // "Sedan"
camry.turnEngineOn(); // "engine is on"

console.log("\n=== Interface Example ===");
const model3 = new Tesla("black", "Model 3", 2022, 201489);
console.log(model3.color); // "black"
console.log(model3.getCarType()); // "Electric"
model3.turnEngineOn(); // "Starting electric motors..."

// TypeScript also supports structural typing
function printCarInfo(car: { color: string; model: string }) {
  console.log(`${car.color} ${car.model}`);
}

printCarInfo(camry); // "red Camry"
printCarInfo(model3); // "black Model 3"
*/
