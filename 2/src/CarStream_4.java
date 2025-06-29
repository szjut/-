import java.util.*;
import java.util.stream.Collectors;

public class CarStream_4 {
    public static void main(String[] args) {
        List<Car> cars = Arrays.asList(
                new Car("VIN001", "Camry", "Toyota", 2020, 45000, 25000),
                new Car("VIN002", "Model 3", "Tesla", 2022, 12000, 45000),
                new Car("VIN003", "X5", "BMW", 2021, 30000, 60000),
                new Car("VIN004", "Civic", "Honda", 2019, 49000, 22000),
                new Car("VIN005", "Model S", "Tesla", 2023, 8000, 85000),
                new Car("VIN006", "Accord", "Honda", 2018, 55000, 20000)
        );

        // 1. Фильтрация машин с пробегом < 50.000 км
        List<Car> lowMileageCars = cars.stream()
                .filter(c -> c.getMileage() < 50000)
                .collect(Collectors.toList());
        System.out.println("Машины с пробегом < 50.000 км:");
        lowMileageCars.forEach(System.out::println);

        // 2. Сортировка по цене (по убыванию)
        List<Car> sortedByPrice = cars.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .collect(Collectors.toList());
        System.out.println("\nМашины, отсортированные по цене (по убыванию):");
        sortedByPrice.forEach(System.out::println);

        // 3. Топ-3 самые дорогие машины
        List<Car> top3Expensive = cars.stream()
                .sorted(Comparator.comparingDouble(Car::getPrice).reversed())
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("\nТоп-3 самые дорогие машины:");
        top3Expensive.forEach(System.out::println);

        // 4. Средний пробег всех машин
        double averageMileage = cars.stream()
                .mapToInt(Car::getMileage)
                .average()
                .orElse(0);
        System.out.printf("\nСредний пробег всех машин: %.2f км%n", averageMileage);

        // 5. Группировка по производителю
        Map<String, List<Car>> carsByManufacturer = cars.stream()
                .collect(Collectors.groupingBy(Car::getManufacturer));
        System.out.println("\nМашины, сгруппированные по производителю:");
        carsByManufacturer.forEach((manufacturer, carList) -> {
            System.out.println(manufacturer + ":");
            carList.forEach(c -> System.out.println("  " + c));
        });
    }
}
