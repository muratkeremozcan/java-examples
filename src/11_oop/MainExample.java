public class MainExample {

  // Java supports nested classes
  // The static means it doesn't have access to Main's instance members
  private static final class MyCar {
    private final String color;
    private final String model;
    private final int year;

    private MyCar() {
      this.model = "camry";
      this.color = "red";
      this.year = 2019;
    }
  }

  public static void main(String[] args) {
    final MyCar myCar = new MyCar();
    System.out.println(myCar.color);
    System.out.println(myCar.model);
    System.out.println(myCar.year);
  }
}

/* TS equivalent
// Main.ts

export class Car {
  constructor(
    public color: string,
    public model: string,
    public year: number
  ) {}
}

const myCar = new Car("blue", "corolla", 2022);

*/
