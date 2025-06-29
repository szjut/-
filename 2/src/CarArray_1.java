import java.util.Random;

public class CarArray_1 {
    public static void main(String[] args) {
        // Создание массива с годами выпуска 50 случайных машин
        int[] productionYears = new int[50];
        Random random = new Random();

        // Заполнение массива случайными годами от 2000 до 2025
        for (int i = 0; i < productionYears.length; i++) {
            productionYears[i] = 2000 + random.nextInt(26); // 2000-2025
        }

        // Вывод машин, выпущенных после 2015 года
        System.out.println("Машины, выпущенные после 2015 года:");
        for (int year : productionYears) {
            if (year > 2015) {
                System.out.println(year);
            }
        }

        // Подсчёт среднего возраста авто (2025 - средний год выпуска)
        int sum = 0;
        for (int year : productionYears) {
            sum += year;
        }
        double averageYear = (double) sum / productionYears.length;
        double averageAge = 2025 - averageYear;
        System.out.printf("Средний возраст авто: %.2f лет%n", averageAge);
    }
}
