// Takeaways (Polymorphism):
// - Keep the same MyCar data model; subclasses use @Override to expose runtime polymorphism.
// - Method overloading resolves at compile time; overridden implementations still dispatch at
// runtime.
// - Treat objects as MyCar references so swapping Toyota/Tesla requires no caller changes.
// - Private members remain encapsulated; only the outer class can reach MyCar.deployAirbags().

/**
 * Builds on {@code MainExample} by highlighting polymorphism. Reuses the original {@code MyCar}
 * shape while demonstrating method overriding, method overloading, and working with the base type.
 */
public class MainExamplePolymorphism {

  /**
   * Abstract base car identical in shape to {@code MainExample}, matching the structure used in
   * {@code MainExampleAbs}. Provides two {@code turnEngineOn} overloads so subclasses can
   * specialize both forms.
   */
  private abstract static class MyCar {
    protected static final String START_MODE_REMOTE = "remote";
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

    public void turnEngineOn(final String startMode) {
      // Compile-time polymorphism: same method name, different signature.
      if (START_MODE_REMOTE.equalsIgnoreCase(startMode)) {
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

    private void deployAirbags() {
      System.out.println("airbags deployed");
    }

    public abstract String getCarType();
  }

  /** Toyota inherits data/state but tweaks only the parts that need different behavior. */
  public static final class Toyota extends MyCar {
    public Toyota(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    @Override
    public void turnEngineOn() {
      // Runtime polymorphism: concrete behavior determined by the object type.
      System.out.println("Toyota engine rumbling to life");
    }

    @Override
    public String getCarType() {
      return "Sedan";
    }
  }

  /** Tesla overrides multiple methods to showcase runtime dispatch on both overloads. */
  public static final class Tesla extends MyCar {
    public Tesla(final String color, final String model, final int year, final int vehicleNumber) {
      super(color, model, year, vehicleNumber);
    }

    @Override
    public void turnEngineOn() {
      System.out.println("Tesla engages electric motors");
    }

    @Override
    public void turnEngineOn(final String startMode) {
      // Even overloaded methods dispatch to Tesla's override at runtime.
      if (START_MODE_REMOTE.equalsIgnoreCase(startMode)) {
        System.out.println("Tesla starts silently via the phone app");
      } else {
        turnEngineOn();
      }
    }

    @Override
    public int calculateMpg(final int milesDriven, final int gallonsUsed) {
      // Electric vehicles use MPGe; keep it simple for demonstration purposes.
      return (int) (milesDriven * 3.5);
    }

    public void enableAutopilot() {
      System.out.println("Autopilot enabled — keep your hands ready");
    }

    @Override
    public String getCarType() {
      return "Electric";
    }
  }

  private static void demoTurnOnEngines(final MyCar car) {
    System.out.printf("%s (%d) -> ", car.model, car.year);
    car.turnEngineOn();
    car.turnEngineOn("remote");
  }

  /**
   * Main method demonstrates polymorphism while keeping the original data model intact.
   *
   * @param args command line arguments (not used)
   */
  public static void main(String[] args) {
    final MyCar baseCamry =
        new MyCar("red", "camry", 2019, 101_189) {
          @Override
          public String getCarType() {
            return "Generic";
          }
        };
    final MyCar tunedToyota = new Toyota("black", "yaris", 2014, 201_489);
    final MyCar futuristicTesla = new Tesla("white", "model 3", 2022, 301_777);

    final MyCar[] garage = {baseCamry, tunedToyota, futuristicTesla};

    System.out.println("=== Engine Start (overload + override) ===");
    for (final MyCar car : garage) {
      // Polymorphism: same method name, behavior depends on concrete class.
      demoTurnOnEngines(car);
    }

    System.out.println("\n=== MPG Calculations ===");
    System.out.println(baseCamry.calculateMpg(180, 20));
    System.out.println(tunedToyota.calculateMpg(220, 10));
    System.out.println(futuristicTesla.calculateMpg(200, 1));

    System.out.println("\n=== Car Types via Abstract Method ===");
    // Polymorphism: abstract method resolved by Toyota/Tesla overrides.
    System.out.println(tunedToyota.getCarType());
    System.out.println(futuristicTesla.getCarType());

    System.out.println("\n=== Accessing private member via outer class ===");
    // Only the outer class can call the private method; outside callers cannot.
    baseCamry.deployAirbags();

    System.out.println("\n=== Tesla-specific feature via downcast ===");
    for (final MyCar car : garage) {
      if (car instanceof Tesla tesla) {
        tesla.enableAutopilot();
      }
    }

    System.out.println("\n=== Vehicle numbers stay private ===");
    for (final MyCar car : garage) {
      System.out.printf("%s -> %d%n", car.model, car.getVehicleNumber());
    }
  }
}
