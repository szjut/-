import java.util.*;

class Car implements Comparable<Car> {
    private String vin;
    private String model;
    private String manufacturer;
    private int year;
    private int mileage;
    private double price;

    public Car(String vin, String model, String manufacturer, int year, int mileage, double price) {
        this.vin = vin;
        this.model = model;
        this.manufacturer = manufacturer;
        this.year = year;
        this.mileage = mileage;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return vin.equals(car.vin);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vin);
    }

    @Override
    public int compareTo(Car other) {
        return Integer.compare(other.year, this.year); // Сортировка от новых к старым
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d) - %d km, $%.2f", manufacturer, model, year, mileage, price);
    }

    // Геттеры
    public String getVin() { return vin; }
    public String getModel() { return model; }
    public String getManufacturer() { return manufacturer; }
    public int getYear() { return year; }
    public int getMileage() { return mileage; }
    public double getPrice() { return price; }
}

public class CarEqualsHashCode_3 {
    public static void main(String[] args) {
        Set<Car> carSet = new HashSet<>();

        Car car1 = new Car("VIN123", "Camry", "Toyota", 2020, 30000, 25000);
        Car car2 = new Car("VIN456", "Model S", "Tesla", 2022, 15000, 80000);
        Car car3 = new Car("VIN123", "Corolla", "Toyota", 2018, 45000, 18000); // Дубликат VIN

        carSet.add(car1);
        carSet.add(car2);
        carSet.add(car3);

        System.out.println("Машины в HashSet (дубликаты по VIN не добавляются):");
        carSet.forEach(System.out::println);

        // Сортировка по году выпуска
        List<Car> sortedCars = new ArrayList<>(carSet);
        Collections.sort(sortedCars);
        System.out.println("\nМашины, отсортированные по году выпуска (от новых к старым):");
        sortedCars.forEach(System.out::println);
    }
}
