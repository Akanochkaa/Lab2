# Lab2.java
# Лабораторная работа №2. Корионова Анастасия ИТ-5. Вариант 8.

> Вариант 8. Задачи: 1.3, 1.5, 2.4, 3.4, 4.5, 5.5.

---

## Задание 1.3. Имена

### Текст задачи

Создайте сущность Имя, которая описывается тремя параметрами: Фамилия, Личное имя, Отчество. Имя может быть приведено к строковому виду, включающему традиционное представление всех трех параметров: Фамилия Имя Отчество (например “Иванов Иван Иванович”). Необходимо предусмотреть возможность того, что какой-либо из параметров может быть не задан, и в этом случае он не учитывается при приведении к текстовому виду.

Необходимо создать следующие имена:
- Клеопатра
- Пушкин Александр Сергеевич
- Маяковский Владимир

### Алгоритм решения

1. Создать класс `Name` с тремя строковыми полями для хранения фамилии, имени и отчества.
2. Реализовать конструктор, который принимает эти три параметра. Если какой-то параметр не передан (равен `null`), заменить его на пустую строку `""`.
3. Переопределить метод `toString()`. В нём проверять каждое поле: если строка не пустая — добавлять её к результату и ставить пробел. Пустые поля пропускать.
4. В главном методе `main` реализовать ввод данных с клавиатуры (в цикле запрашивать Фамилию, Имя, Отчество). 
5. Создать объекты `Name` на основе введённых данных и вывести их на экран с помощью `System.out.println()`.

### Код класса Name.java

```java
public class Name {
    private String lastName;
    private String firstName;
    private String middleName;

    public Name(String firstName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = "";
        this.middleName = "";
    }

    public Name(String firstName, String lastName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = "";
    }

    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = middleName != null ? middleName.trim() : "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!lastName.isEmpty()) sb.append(lastName).append(" ");
        if (!firstName.isEmpty()) sb.append(firstName).append(" ");
        if (!middleName.isEmpty()) sb.append(middleName);
        return sb.toString().trim();
    }
}
```
---

## Задание 1.5. Дома

### Текст задачи

Создайте сущность Дом, которая описывается количеством этажей в виде числа. У Дома можно запросить текстовую форму, которое имеет представление вида “дом с N этажами”, где N это число. Гарантировать правильное окончание фразы, в зависимости от количества этажей. Создать и вывести на экран дома с 1, 5, 23 этажами.

### Алгоритм решения

1. Создать класс `House` с целочисленным полем `floors` (количество этажей).
2. В классе реализовать конструктор, который принимает количество этажей.
3. В методе `toString()` реализовать логику склонения слова «этаж»:
   - Если последние две цифры от 11 до 14 — пишем «этажами».
   - Иначе, если последняя цифра 1 — пишем «этажом».
   - Иначе (2, 3, 4, 5 и т.д.) — пишем «этажами».
4. В главном методе `main` запросить у пользователя количество домов и их этажность с клавиатуры.
5. Вывести созданные объекты на экран.

### Код класса House.java

```java
public class House {
    private int floors;

    public House(int floors) {
        this.floors = floors;
    }

    @Override
    public String toString() {
        String ending;
        int lastDigit = floors % 10;
        int lastTwoDigits = floors % 100;

        if (lastTwoDigits >= 11 && lastTwoDigits <= 14) ending = "этажами";
        else if (lastDigit == 1) ending = "этажом";
        else if (lastDigit >= 2 && lastDigit <= 4) ending = "этажами";
        else ending = "этажами";

        return "дом с " + floors + " " + ending;
    }
}
```
## Задание 2.4: Сотрудники и отделы

### Текст задачи

Создайте сущность Сотрудник, которая описывается именем (в строковой форме) и отделом, в котором сотрудник работает, причем у каждого отдела есть название и начальник, который также является Сотрудником. Сотрудник может быть приведен к текстовой форме вида: “Имя работает в отделе Название, начальник которого Имя”. В случае если сотрудник является руководителем отдела, то текстовая форма должна быть “Имя начальник отдела Название”.

Необходимо выполнить следующие задачи:
1. Создать Сотрудников Петрова, Козлова, Сидорова работающих в отделе IT.
2. Сделать Козлова начальником IT отдела.
3. Вывести на экран текстовое представление всех трех Сотрудников (у всех троих должен оказаться один и тот же отдел и начальник).

### Алгоритм решения

Для реализации задачи создаются два взаимосвязанных класса: `Department` (Отдел) и `Employee` (Сотрудник).

1. **Класс `Department` (Отдел):**
   * Хранит название отдела (`name`).
   * Хранит ссылку на начальника (`boss`), который является объектом класса `Employee`.
   * Хранит список всех сотрудников отдела (`List<Employee> employees`).
   * Имеет методы для назначения начальника (`setBoss`) и добавления сотрудников в список (`addEmployee`).

2. **Класс `Employee` (Сотрудник):**
   * Хранит имя сотрудника (`name`).
   * Хранит ссылку на отдел, в котором он работает (`department`).
   * При привязке сотрудника к отделу (через метод `setDepartment`), сотрудник автоматически добавляется в список сотрудников этого отдела.
   * **Логика `toString()`**: Метод проверяет, совпадает ли текущий сотрудник (`this`) со ссылкой на начальника своего отдела (`department.getBoss() == this`). Если да — выводится строка для начальника. Если нет — берется имя начальника и выводится строка для обычного сотрудника.

### Код класса Employee.java

```java
public class Employee {
    private String name;
    private Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
        department.addEmployee(this);
    }

    public Department getDepartment() { return department; }
    public String getName() { return name; }

    @Override
    public String toString() {
        if (department == null) return name + " (без отдела)";

        if (department.getBoss() == this) {
            return name + " начальник отдела " + department.getName();
        }
        String bossName = (department.getBoss() != null) ? department.getBoss().getName() : "нет";
        return name + " работает в отделе " + department.getName() + ", начальник которого " + bossName;
    }
}

```
---

## Задание 3.4. Список сотрудников отдела

### Текст задачи

Измените решение, полученное в задаче 2.4 таким образом, чтобы имея ссылку на сотрудника, можно было бы узнать список всех сотрудников этого отдела.

### Алгоритм решения

1. В задаче 2.4 между классами `Employee` (Сотрудник) и `Department` (Отдел) уже установлена двусторонняя связь. Сотрудник знает свой отдел, а отдел хранит список своих сотрудников.
2. Чтобы получить список всех сотрудников отдела, имея ссылку на конкретного сотрудника, нужно:
   - Обратиться к сотруднику и получить его отдел через метод `getDepartment()`.
   - Обратиться к полученному отделу и запросить список всех его сотрудников через метод `getEmployees()`.
3. В главном методе `main` (в классе `Main`) реализован ввод имени сотрудника с клавиатуры. Программа находит этого сотрудника в списке, получает его отдел и выводит имена всех сотрудников этого отдела.

### Код класса Department.java 

```java
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String name;
    private Employee boss;
    private List<Employee> employees = new ArrayList<>();

    public Department(String name) {
        this.name = name;
    }

    public void setBoss(Employee boss) {
        this.boss = boss;
    }

    public void addEmployee(Employee emp) {
        employees.add(emp);
    }

    public List<Employee> getEmployees() { 
        return employees; 
    }

    public String getName() { 
        return name; 
    }

    public Employee getBoss() { 
        return boss; 
    }
}
```
```java
public class Employee {
    private String name;
    private Department department;

    public Employee(String name) {
        this.name = name;
    }

    public void setDepartment(Department department) {
        this.department = department;
        department.addEmployee(this);
    }

    public Department getDepartment() { return department; }
    public String getName() { return name; }

    @Override
    public String toString() {
        if (department == null) return name + " (без отдела)";

        if (department.getBoss() == this) {
            return name + " начальник отдела " + department.getName();
        }
        String bossName = (department.getBoss() != null) ? department.getBoss().getName() : "нет";
        return name + " работает в отделе " + department.getName() + ", начальник которого " + bossName;
    }
}
```
---

## Задание 4.5. Создаем Имена 

### Текст задачи

Измените сущность Имя из задачи 1.3. Новые требования включают:
- Имя можно создать указав только Личное имя
- Имя можно создать указав Личное имя и Фамилию
- Имя можно создать указав все три параметра: Личное имя, Фамилию, Отчество

Необходимо создать следующие имена:
1. Клеопатра
2. Александр Сергеевич Пушкин
3. Владимир Маяковский
4. Христофор Бонифатьевич (здесь Христофор это имя, а Бонифатьевич - фамилия)

### Алгоритм решения

1. В классе `Name` реализовать **три конструктора** (перегрузка):
   - Конструктор, принимающий только личное имя.
   - Конструктор, принимающий личное имя и фамилию.
   - Конструктор, принимающий личное имя, фамилию и отчество.
2. В каждом конструкторе инициализировать поля класса. Если параметр не передан, присвоить пустую строку `""`.
3. Переопределить метод `toString()` для вывода имени в формате «Фамилия Имя Отчество» (пропуская пустые поля).

### Код класса Name.java 

```java
public class Name {
    private String firstName;
    private String lastName;
    private String middleName;

    // 1. Только Личное имя
    public Name(String firstName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = "";
        this.middleName = "";
    }

    // 2. Личное имя и Фамилия
    public Name(String firstName, String lastName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = "";
    }

    // 3. Все три параметра
    public Name(String firstName, String lastName, String middleName) {
        this.firstName = firstName != null ? firstName.trim() : "";
        this.lastName = lastName != null ? lastName.trim() : "";
        this.middleName = middleName != null ? middleName.trim() : "";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (!lastName.isEmpty()) sb.append(lastName).append(" ");
        if (!firstName.isEmpty()) sb.append(firstName).append(" ");
        if (!middleName.isEmpty()) sb.append(middleName);
        return sb.toString().trim();
    }
}
```
---

## Задание 5.5. Дроби

### Текст задачи

Создайте сущность Дробь со следующими особенностями:
- Имеет числитель: целое число
- Имеет знаменатель: целое число
- Дробь может быть создана с указанием числителя и знаменателя
- Может вернуть строковое представление вида “числитель/знаменатель”
- Может выполнять операции сложения, вычитания, умножения и деления с другой Дробью или целым числом. Результатом операции должна быть новая Дробь (таким образом, обе исходные дроби не изменяются).

Затем необходимо выполнить следующие задачи:
1. Создать несколько экземпляров дробей.
2. Написать по одному примеру использования каждого метода.
3. Вывести на экран примеры и результаты их выполнения в формате «1/3 * 2/3 = 2/9».
4. Посчитать f1.sum(f2).div(f3).minus(5).

### Алгоритм решения

1. Создать класс `Fraction` с полями `numerator` (числитель) и `denominator` (знаменатель).
2. В конструкторе проверять, что знаменатель не равен нулю (иначе выбрасывать исключение).
3. Реализовать математические операции:
   - **Сложение:** `(a/b) + (c/d) = (a*d + c*b) / (b*d)`.
   - **Вычитание:** `(a/b) - (c/d) = (a*d - c*b) / (b*d)`.
   - **Умножение:** `(a/b) * (c/d) = (a*c) / (b*d)`.
   - **Деление:** `(a/b) / (c/d) = (a*d) / (b*c)`.
4. Каждая операция должна возвращать **новый объект** `Fraction` (исходные дроби не меняются).
5. Переопределить метод `toString()` для вывода в формате «числитель/знаменатель».
6. Для операций с целыми числами реализовать перегрузку методов (создавая дробь вида `integer/1`).

### Код класса Fraction.java

```java
public class Fraction {
    private int numerator;
    private int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю!");
        }
        this.numerator = numerator;
        this.denominator = denominator;
    }

    private Fraction simplify() {
        int gcd = gcd(Math.abs(this.numerator), Math.abs(this.denominator));
        int newNum = this.numerator / gcd;
        int newDen = this.denominator / gcd;

        if (newDen < 0) {
            newNum = -newNum;
            newDen = -newDen;
        }
        return new Fraction(newNum, newDen);
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public Fraction sum(Fraction other) {
        return new Fraction(
            this.numerator * other.denominator + other.numerator * this.denominator,
            this.denominator * other.denominator
        ).simplify();
    }

    public Fraction minus(Fraction other) {
        return new Fraction(
            this.numerator * other.denominator - other.numerator * this.denominator,
            this.denominator * other.denominator
        ).simplify();
    }

    public Fraction mult(Fraction other) {
        return new Fraction(
            this.numerator * other.numerator,
            this.denominator * other.denominator
        ).simplify();
    }

    public Fraction div(Fraction other) {
        if (other.numerator == 0) throw new ArithmeticException("Деление на ноль!");
        return new Fraction(
            this.numerator * other.denominator,
            this.denominator * other.numerator
        ).simplify();
    }

    public Fraction sum(int integer) { return this.sum(new Fraction(integer, 1)); }
    public Fraction minus(int integer) { return this.minus(new Fraction(integer, 1)); }
    public Fraction mult(int integer) { return this.mult(new Fraction(integer, 1)); }
    public Fraction div(int integer) { return this.div(new Fraction(integer, 1)); }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        return numerator + "/" + denominator;
    }
}
```
