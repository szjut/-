import java.util.*;

public class CarModels_2 {
    public static void main(String[] args) {
        // Создание списка с названиями моделей машин (с дубликатами)
        List<String> carModels = new ArrayList<>(Arrays.asList(
                "Toyota Camry", "BMW X5", "Tesla Model S", "Toyota Camry",
                "Audi A4", "Tesla Model 3", "BMW X5", "Honda Civic"
        ));

        // Удаление дубликатов
        Set<String> uniqueModels = new LinkedHashSet<>(carModels);
        List<String> uniqueList = new ArrayList<>(uniqueModels);

        // Сортировка в обратном алфавитном порядке
        uniqueList.sort(Collections.reverseOrder());
        System.out.println("Уникальные модели (обратный порядок):");
        System.out.println(uniqueList);

        // Сохранение в Set
        Set<String> sortedSet = new TreeSet<>(Collections.reverseOrder());
        sortedSet.addAll(uniqueList);

        // Замена модели с "Tesla" на "ELECTRO_CAR"
        List<String> electroList = new ArrayList<>();
        for (String model : uniqueList) {
            if (model.contains("Tesla")) {
                electroList.add("ELECTRO_CAR");
            } else {
                electroList.add(model);
            }
        }
        System.out.println("Модели после замены Tesla:");
        System.out.println(electroList);
    }
}
