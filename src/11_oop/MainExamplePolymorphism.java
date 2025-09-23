// Takeaways (Polymorphism):
// - Base class exposes a shared API; overrides specialize behavior per model.
// - Call through the base type; runtime dispatch picks the right override.

// 2 kinds of polymorphism
// - Compile-time, method overloading polymorphism: same method name, different parameter lists
// - Runtime, subtype polymorphism: @Override

/** Mirrors {@code MainExampleAbs} but focuses on polymorphism. */
public class MainExamplePolymorphism {

  private static final String REMOTE_START_MODE = "remote";

  /** Entry point that walks through examples of overloading and overriding. */
  public static void main(String[] args) {
    final MyCar[] garage = {
      new Toyota("red", "Camry", 2019, 101_189), new Tesla("black", "Model 3", 2022, 201_489)
    };

    System.out.println("\n=== Shared base behavior (common implementation) ===");
    for (final MyCar car : garage) {
      System.out.printf("Vehicle number stays private -> %d%n", car.getVehicleNumber());
    }

    System.out.println("\n=== Compile-time, method overloading polymorphism ===");
    for (final MyCar car : garage) {
      car.turnEngineOn(REMOTE_START_MODE); // KEY: Compile-time overload picked by argument list
    }

    System.out.println("\n=== Runtime, subtype polymorphism ===");
    for (final MyCar car : garage) {
      System.out.printf("%s (%d) -> %s%n", car.model, car.year, car.getCarType());
      car.turnEngineOn(); // KEY: same call site, different override per car.
      System.out.println("MPG: " + car.calculateMpg(200, 10));
    }

    System.out.println("\n=== Subtype-only features (needs downcast) ===");
    for (final MyCar car : garage) {
      if (car instanceof Tesla tesla) {
        tesla.enableAutopilot(); // KEY: downcast only when you truly need subtype behavior.
      }
    }
  }

  // Same base shape as MainExampleAbs
  private abstract static class MyCar {
    public final String color;
    public final String model;
    public final int year;
    private final int vehicleNumber;

    protected MyCar(
        final String color, final String model, final int year, final int vehicleNumber) {
      this.color = color;
      this.model = model;
      this.year = year;
      this.vehicleNumber = vehicleNumber;
    }

    public void turnEngineOn() {
      System.out.println("engine is on");
    }

    // Compile-time, method overloading polymorphism: same method name, different parameter lists
    public void turnEngineOn(final String startMode) {
      if (REMOTE_START_MODE.equalsIgnoreCase(startMode)) {
        System.out.println("engine is on via remote starter");
      } else {
        turnEngineOn();
      }
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

  /**
   * Toyota tweaks only the behavior that differs from the base class. When a {@code MyCar} variable
   * actually holds a Toyota, these overrides are the ones the runtime dispatches to—polymorphism in
   * action.
   */
  public static final class Toyota extends MyCar {
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    // KEY: anything we override and change is polymorphism
    // Runtime, subtype polymorphism: @Override
    @Override
    public void turnEngineOn() {
      System.out.println("Toyota engine rumbling to life"); // runtime dispatch hits Toyota override
    }

    // This is the only abstract method that subclasses must implement
    @Override
    public String getCarType() {
      return "Sedan"; // polymorphism: same method, Toyota-specific result
    }
  }

  /** Tesla overrides more pieces to showcase different runtime behavior. */
  public static final class Tesla extends MyCar {
    private static final double KWH_PER_GALLON_EQUIVALENT = 33.7;
    private static final double MODEL3_KWH_PER_100_MILES = 26.0;

    public Tesla(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    // Runtime, subtype polymorphism: @Override
    @Override
    public void turnEngineOn() {
      System.out.println("Tesla engages electric motors");
    }

    // Runtime, subtype polymorphism: @Override
    @Override
    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      // Treat the MPG request as MPGe by estimating energy consumption for the drive.
      final double energyUsedKwh = milesDriven / 100.0 * MODEL3_KWH_PER_100_MILES;
      final double mpge = milesDriven / (energyUsedKwh / KWH_PER_GALLON_EQUIVALENT);
      return (int) Math.round(mpge);
    }

    // This is the only abstract method that subclasses must implement
    @Override
    public String getCarType() {
      return "Electric";
    }

    // Subtype-only feature: not in the base class
    public void enableAutopilot() {
      System.out.println("Autopilot enabled — keep your hands ready");
    }
  }
}

/* TS equivalent
// KEY: same base contract, but everything lives at module scope and `private` is compile-time only.

abstract class MyCar {
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

  abstract getCarType(): string;
}

class Toyota extends MyCar {
  override turnEngineOn(): void {
    console.log('Toyota engine rumbling to life');
  }

  override getCarType(): string {
    return 'Sedan';
  }
}

class Tesla extends MyCar {
  override turnEngineOn(): void {
    console.log('Tesla engages electric motors');
  }

  override calculateMpg(milesDriven: number, gallonsUsed: number): number {
    return Math.floor(milesDriven * 3.5);
  }

  override getCarType(): string {
    return 'Electric';
  }

  enableAutopilot(): void {
    console.log('Autopilot enabled — keep your hands ready');
  }
}

function main(): void {
  const garage: MyCar[] = [
    new Toyota('red', 'Camry', 2019, 101189),
    new Tesla('black', 'Model 3', 2022, 201489),
  ];

  console.log('=== Runtime overrides ===');
  for (const car of garage) {
    console.log(`${car.model} (${car.year}) -> ${car.getCarType()}`);
    car.turnEngineOn();
    console.log(`MPG: ${car.calculateMpg(200, 10)}`);
  }

  console.log('\n=== Subtype-only features ===');
  for (const car of garage) {
    if (car instanceof Tesla) {
      car.enableAutopilot();
    }
  }
}

main();
*/
