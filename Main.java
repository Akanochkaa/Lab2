import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static Department currentDept = null;

    public static void main(String[] args) {
        int choice = -1;
        while (choice != 0) {
            System.out.println("\n=== ЛАБОРАТОРНАЯ РАБОТА №2 (ВАРИАНТ 8) ===");
            System.out.println("1. Задание 1.3: Имена");
            System.out.println("2. Задание 1.5: Дома");
            System.out.println("3. Задание 2.4: Сотрудники и отделы (Создать)");
            System.out.println("4. Задание 3.4: Список сотрудников");
            System.out.println("5. Задание 4.5: Имена создание");
            System.out.println("6. Задание 5.5: Дроби");
            System.out.println("0. Выход");
            System.out.print("Выберите задачу: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Ошибка! Введите число от 0 до 6.");
                scanner.next();
                continue;
            }

            switch (choice) {
                case 1 -> task1_3();
                case 2 -> task1_5();
                case 3 -> task2_4();
                case 4 -> task3_4();
                case 5 -> task4_5();
                case 6 -> task5_5();
                case 0 -> System.out.println("Выход...");
                default -> System.out.println("Неверный пункт.");
            }
        }
    }

    // ЗАДАНИЕ 1.3
    private static void task1_3() {
        System.out.println("\n--- Задание 1.3: Ввод имен  ---");
        int count = readInt("Сколько имен вы хотите создать? ");
        Name[] names = new Name[count];

        for (int i = 0; i < count; i++) {
            System.out.println("\nВведите данные для имени №" + (i + 1) + ":");
            System.out.print("  Фамилия (Enter - пустая строка): ");
            String ln = scanner.nextLine();
            System.out.print("  Имя (Enter - пустая строка): ");
            String fn = scanner.nextLine();
            System.out.print("  Отчество (Enter - пустая строка): ");
            String mn = scanner.nextLine();

            names[i] = new Name(fn, ln, mn);
        }

        System.out.println("\n--- Созданные имена ---");
        for (Name name : names) System.out.println(name);
    }

    // ЗАДАНИЕ 1.5
    private static void task1_5() {
        System.out.println("\n--- Задание 1.5: Дома ---");
        int count = readInt("Сколько домов вы хотите создать? ");
        House[] houses = new House[count];

        for (int i = 0; i < count; i++) {
            int floors = readInt("  Введите количество этажей для Дома №" + (i + 1) + ": ");
            houses[i] = new House(floors);
        }

        System.out.println("\n--- Созданные дома ---");
        for (House house : houses) System.out.println(house);
    }

    // ЗАДАНИЕ 2.4
    private static void task2_4() {
        System.out.println("\n--- Задание 2.4: Сотрудники и отдел ---");
        System.out.print("Введите название отдела: ");
        currentDept = new Department(scanner.nextLine());

        int count = readInt("Сколько сотрудников создать? ");
        for (int i = 1; i <= count; i++) {
            System.out.print("Введите имя сотрудника №" + i + ": ");
            Employee emp = new Employee(scanner.nextLine());
            emp.setDepartment(currentDept);
        }

        System.out.print("Введите имя начальника отдела: ");
        String bossName = scanner.nextLine();
        boolean found = false;
        for (Employee e : currentDept.getEmployees()) {
            if (e.getName().equalsIgnoreCase(bossName)) {
                currentDept.setBoss(e);
                found = true;
                break;
            }
        }
        if (!found) System.out.println("Такого сотрудника нет.");

        System.out.println("\n--- Результат ---");
        for (Employee e : currentDept.getEmployees()) System.out.println(e);
    }

    // ЗАДАНИЕ 3.4
    private static void task3_4() {
        System.out.println("\n--- Задание 3.4: Поиск по сотруднику ---");

        if (currentDept == null || currentDept.getEmployees().isEmpty()) {
            System.out.println("Сначала создайте отдел и сотрудников в Задании 2.4!");
            return;
        }

        System.out.print("Введите имя сотрудника, чтобы узнать его отдел и коллег: ");
        String searchName = scanner.nextLine();

        Employee foundEmp = null;
        for (Employee e : currentDept.getEmployees()) {
            if (e.getName().equalsIgnoreCase(searchName)) {
                foundEmp = e;
                break;
            }
        }

        if (foundEmp != null) {
            System.out.println("\nСотрудник " + foundEmp.getName() + " работает в отделе " + foundEmp.getDepartment().getName());
            System.out.println("Список всех сотрудников этого отдела:");
            for (Employee e : foundEmp.getDepartment().getEmployees()) {
                System.out.println("- " + e.getName());
            }
        } else {
            System.out.println("\nОшибка: сотрудник с именем " + searchName + " не найден в отделе " + currentDept.getName() + "!");
        }
    }

    // ЗАДАНИЕ 4.5
    private static void task4_5() {
        System.out.println("\n--- Задание 4.5: Создаем имена ---");
        int count = readInt("Сколько имен вы хотите создать? ");

        for (int i = 1; i <= count; i++) {
            System.out.println("\nСоздание имени №" + i);
            System.out.println("Как вы хотите создать имя?");
            System.out.println("1. Только имя");
            System.out.println("2. Имя и Фамилия");
            System.out.println("3. Полное ФИО (Имя, Фамилия, Отчество)");
            int type = readInt("Ваш выбор: ");

            Name personName;

            if (type == 1) {
                System.out.print("Введите Имя: ");
                String fn = scanner.nextLine().trim();
                personName = new Name(fn);
            } else if (type == 2) {
                System.out.print("Введите Имя: ");
                String fn = scanner.nextLine().trim();
                System.out.print("Введите Фамилию: ");
                String ln = scanner.nextLine().trim();
                personName = new Name(fn, ln);
            } else if (type == 3) {
                System.out.print("Введите Имя: ");
                String fn = scanner.nextLine().trim();
                System.out.print("Введите Фамилию: ");
                String ln = scanner.nextLine().trim();
                System.out.print("Введите Отчество: ");
                String mn = scanner.nextLine().trim();
                personName = new Name(fn, ln, mn);
            } else {
                System.out.println("Неверный выбор! Имя не создано.");
                continue;
            }

            System.out.println("  -> Создано имя: " + personName);
        }
    }

    // ЗАДАНИЕ 5.5
    private static void task5_5() {
        System.out.println("\n--- Задание 5.5: Дроби ---");
        System.out.println("Введите 1-ю дробь:");
        Fraction f1 = inputFraction();
        System.out.println("Введите 2-ю дробь:");
        Fraction f2 = inputFraction();
        System.out.println("Введите 3-ю дробь:");
        Fraction f3 = inputFraction();

        System.out.println("\nРезультаты:");
        System.out.println(f1 + " + " + f2 + " = " + f1.sum(f2));
        System.out.println(f2 + " - " + f3 + " = " + f2.minus(f3));
        System.out.println(f1 + " * " + f2 + " = " + f1.mult(f2));
        System.out.println(f1 + " / " + f2 + " = " + f1.div(f2));

        System.out.println("\nВыражение: f1.sum(f2).div(f3).minus(5) = " + f1.sum(f2).div(f3).minus(5));
    }

    private static Fraction inputFraction() {
        int num = readInt("  Введите числитель: ");
        int den = 0;
        while (true) {
            den = readInt("  Введите знаменатель (не 0): ");
            if (den == 0) System.out.println("  Ошибка! Знаменатель не может быть 0.");
            else break;
        }
        return new Fraction(num, den);
    }

    private static int readInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                int val = scanner.nextInt();
                scanner.nextLine();
                return val;
            } else {
                System.out.println("Ошибка! Введите целое число.");
                scanner.next();
            }
        }
    }
}