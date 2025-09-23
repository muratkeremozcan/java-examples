// Access modifiers:
// public: exposes to all
// protected: package (same folder structure) +subclasses
// private: to the declaring type
// static binds state/behavior to the class rather than instances.
// final in Java mirrors `readonly` in TypeScript

// Core concepts of OOP
// Encapsulation: state + behavior bundled together (attributes + methods)
// Inheritance: Extending the functionality of existing code
// Polymorphism: Creating a unified interface that morphs child method behavior; call through a
// common shape, let the runtime pick the implementation

/** Demonstrates basic Java OOP concepts including nested classes and access modifiers. */
public class MainExample {

  /**
   * Main method to demonstrate the usage of the MyCar class.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final MyCar myCar = new MyCar("red", "camry", 2019, 101_189);
    // KEY:the class itself can access private members and methods
    System.out.println(myCar.color);
    System.out.println(myCar.model);
    System.out.println(myCar.year);
    System.out.println(myCar.vehicleNumber);
    myCar.turnEngineOn();
    System.out.println(myCar.calculateMpg(180, 20));
    myCar.deployAirbags();

    System.out.println("\nChild class\n");

    // KEY: The child class can only access public members and methods
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

  /** Lightweight subclass that inherits all behavior from {@link MyCar}. */
  public static final class Toyota extends MyCar {
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }
  }
}

/* TS equivalent
// KEY: the class itself can still touch its private members (same as Java);
// subclasses only see the public API.

// TypeScript differences: no nested classes
// `readonly` mirrors Java's `final` fields
// and no extra `static` keyword is needed because these classes sit at module scope
// In contrast, Java has to keep `static` on the entry-point method and on the nested class
// so we can call them without materializing a `MainExample` instance.

class MyCar {
  constructor(
    public readonly color: string,
    public readonly model: string,
    public readonly year: number,
    private readonly vehicleNumber: number,
  ) {}

  turnEngineOn(): void {
    console.log('engine is on');
  }

  getVehicleNumber(): number {
    return this.vehicleNumber;
  }

  calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return Math.floor(milesDriven / gallonsUsed);
  }

  private deployAirbags(): void {
    console.log('airbags deployed');
  }
}

// TS lets us keep this to one line: if you don't override anything, it reuses the parent's
// constructor and methods automatically.
class Toyota extends MyCar {}

function main(): void {
  const myCar = new MyCar('red', 'camry', 2019, 101189);
  myCar.turnEngineOn();
  console.log(myCar.getVehicleNumber()); // still allowed: within the class API

  const myToyota = new Toyota('black', 'yaris', 2014, 201489);
  console.log(myToyota.color);
  // myToyota.deployAirbags(); // compile error: private member
}

main();
*/
