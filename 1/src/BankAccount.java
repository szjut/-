import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BankAccount {

    private String ownerName;
    private int balance;
    private LocalDateTime openingDate;
    private boolean isLocked;

    public BankAccount(String ownerName) {
        this.ownerName = ownerName;
        this.balance = 0;
        this.openingDate = LocalDateTime.now();
        this.isLocked = false;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public int getBalance() {
        return balance;
    }

    public LocalDateTime getOpeningDate() {
        return openingDate;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean deposit(int amount) {
        if (amount > 0 && !isLocked) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(int amount) {
        if (amount > 0 && amount <= balance && !isLocked) {
            balance -= amount;
            return true;
        }
        return false;
    }

    public boolean transfer(BankAccount otherAccount, int amount) {
        if (otherAccount != null && amount > 0 && amount <= balance && !isLocked && !otherAccount.isLocked) {
            if(withdraw(amount)) {
                otherAccount.deposit(amount);
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
        return String.format("Счет [Владелец: %s, Баланс: %d руб., Дата открытия: %s, Статус: %s]",
                ownerName,
                balance,
                openingDate.format(formatter),
                isLocked ? "заблокирован" : "активен");
    }

    public static void main(String[] args) {
        // Создание тестовых счетов
        BankAccount account1 = new BankAccount("Иван Иванов");
        BankAccount account2 = new BankAccount("Мария Петрова");

        System.out.println("\nСозданы новые счета:");
        System.out.println("Счет 1: " + account1);
        System.out.println("Счет 2: " + account2);

        // Тестирование операций
        System.out.println("\n--- Тестирование операций ---");

        // Пополнение счета
        System.out.println("\nПополнение счета 1 на 1000 руб.: " +
                (account1.deposit(1000) ? "Успешно" : "Ошибка"));
        System.out.println("Текущий баланс счета 1: " + account1.getBalance() + " руб.");

        // Снятие средств
        System.out.println("\nСнятие со счета 1 500 руб.: " +
                (account1.withdraw(500) ? "Успешно" : "Ошибка"));
        System.out.println("Текущий баланс счета 1: " + account1.getBalance() + " руб.");

        // Перевод средств
        System.out.println("\nПеревод со счета 1 на счет 2 300 руб.: " +
                (account1.transfer(account2, 300) ? "Успешно" : "Ошибка"));
        System.out.println("Баланс счета 1: " + account1.getBalance() + " руб.");
        System.out.println("Баланс счета 2: " + account2.getBalance() + " руб.");

        // Попытка снять больше, чем есть на счете
        System.out.println("\nПопытка снять 1000 руб. со счета 1:");
        System.out.println("Результат: " +
                (account1.withdraw(1000) ? "Успешно" : "Недостаточно средств или счет заблокирован"));

        // Блокировка счета
        System.out.println("\nБлокируем счет 1");
        account1.setLocked(true);
        System.out.println("Текущий статус счета 1: " +
                (account1.isLocked() ? "заблокирован" : "активен"));

        // Попытка операции с заблокированным счетом
        System.out.println("\nПопытка пополнить заблокированный счет 1 на 200 руб.:");
        System.out.println("Результат: " +
                (account1.deposit(200) ? "Успешно" : "Операция невозможна - счет заблокирован"));

        // Разблокировка счета
        System.out.println("\nРазблокируем счет 1");
        account1.setLocked(false);

        // Финальное состояние счетов
        System.out.println("\nФинальное состояние счетов:");
        System.out.println("Счет 1: " + account1);
        System.out.println("Счет 2: " + account2);
    }
}

