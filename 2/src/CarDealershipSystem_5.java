import java.util.*;
import java.util.stream.Collectors;

enum CarType {
    SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE, TRUCK
}

class DealershipCar extends Car {
    private CarType type;

    public DealershipCar(String vin, String model, String manufacturer, int year, int mileage, double price, CarType type) {
        super(vin, model, manufacturer, year, mileage, price);
        this.type = type;
    }

    public CarType getType() { return type; }

    @Override
    public String toString() {
        return super.toString() + ", Type: " + type;
    }
}

class CarDealership {
    private Set<DealershipCar> cars = new HashSet<>();

    // Добавить машину (проверка дубликатов по VIN)
    public boolean addCar(DealershipCar car) {
        return cars.add(car);
    }

    // Найти все машины указанного производителя
    public List<DealershipCar> findCarsByManufacturer(String manufacturer) {
        return cars.stream()
                .filter(c -> c.getManufacturer().equalsIgnoreCase(manufacturer))
                .collect(Collectors.toList());
    }

    // Средняя цена машин определённого типа
    public double getAveragePriceByType(CarType type) {
        return cars.stream()
                .filter(c -> c.getType() == type)
                .mapToDouble(DealershipCar::getPrice)
                .average()
                .orElse(0);
    }

    // Список машин, отсортированных по году выпуска (от новых к старым)
    public List<DealershipCar> getCarsSortedByYear() {
        return cars.stream()
                .sorted(Comparator.comparingInt(DealershipCar::getYear).reversed())
                .collect(Collectors.toList());
    }

    // Количество машин каждого типа
    public Map<CarType, Long> getCarTypeStatistics() {
        return cars.stream()
                .collect(Collectors.groupingBy(DealershipCar::getType, Collectors.counting()));
    }

    // Самая старая и самая новая машина
    public Map<String, DealershipCar> getOldestAndNewestCars() {
        Map<String, DealershipCar> result = new HashMap<>();
        cars.stream()
                .min(Comparator.comparingInt(DealershipCar::getYear))
                .ifPresent(c -> result.put("oldest", c));
        cars.stream()
                .max(Comparator.comparingInt(DealershipCar::getYear))
                .ifPresent(c -> result.put("newest", c));
        return result;
    }
}

public class CarDealershipSystem_5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CarDealership dealership = new CarDealership();

        // Добавим несколько машин для примера
        dealership.addCar(new DealershipCar("VIN001", "Camry", "Toyota", 2020, 45000, 25000, CarType.SEDAN));
        dealership.addCar(new DealershipCar("VIN002", "Model 3", "Tesla", 2022, 12000, 45000, CarType.ELECTRIC));
        dealership.addCar(new DealershipCar("VIN003", "X5", "BMW", 2021, 30000, 60000, CarType.SUV));

        while (true) {
            System.out.println("\nМеню автоцентра:");
            System.out.println("1. Добавить машину");
            System.out.println("2. Найти машины по производителю");
            System.out.println("3. Средняя цена машин определённого типа");
            System.out.println("4. Список машин по году выпуска (новые -> старые)");
            System.out.println("5. Статистика по типам машин");
            System.out.println("6. Самая старая и новая машина");
            System.out.println("0. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.println("Добавление новой машины:");
                    System.out.print("VIN: ");
                    String vin = scanner.nextLine();
                    System.out.print("Модель: ");
                    String model = scanner.nextLine();
                    System.out.print("Производитель: ");
                    String manufacturer = scanner.nextLine();
                    System.out.print("Год выпуска: ");
                    int year = scanner.nextInt();
                    System.out.print("Пробег: ");
                    int mileage = scanner.nextInt();
                    System.out.print("Цена: ");
                    double price = scanner.nextDouble();
                    scanner.nextLine(); // consume newline
                    System.out.print("Тип (SEDAN, SUV, ELECTRIC, HATCHBACK, COUPE, TRUCK): ");
                    CarType type = CarType.valueOf(scanner.nextLine().toUpperCase());

                    boolean added = dealership.addCar(
                            new DealershipCar(vin, model, manufacturer, year, mileage, price, type));
                    if (added) {
                        System.out.println("Машина успешно добавлена!");
                    } else {
                        System.out.println("Ошибка: машина с таким VIN уже существует!");
                    }
                    break;

                case 2:
                    System.out.print("Введите производителя: ");
                    String manuf = scanner.nextLine();
                    List<DealershipCar> foundCars = dealership.findCarsByManufacturer(manuf);
                    System.out.println("Найдено " + foundCars.size() + " машин:");
                    foundCars.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("Введите тип машины (SEDAN, SUV, ELECTRIC, ...): ");
                    CarType carType = CarType.valueOf(scanner.nextLine().toUpperCase());
                    double avgPrice = dealership.getAveragePriceByType(carType);
                    System.out.printf("Средняя цена машин типа %s: %.2f%n", carType, avgPrice);
                    break;

                case 4:
                    List<DealershipCar> sortedByYear = dealership.getCarsSortedByYear();
                    System.out.println("Машины по году выпуска (новые -> старые):");
                    sortedByYear.forEach(System.out::println);
                    break;

                case 5:
                    Map<CarType, Long> stats = dealership.getCarTypeStatistics();
                    System.out.println("Количество машин каждого типа:");
                    stats.forEach((t, c) -> System.out.println(t + ": " + c));
                    break;

                case 6:
                    Map<String, DealershipCar> oldestNewest = dealership.getOldestAndNewestCars();
                    System.out.println("Самая новая машина: " + oldestNewest.get("newest"));
                    System.out.println("Самая старая машина: " + oldestNewest.get("oldest"));
                    break;

                case 0:
                    System.out.println("Выход из системы...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}
