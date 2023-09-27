package russian.gb.Exeption;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество ДатаРождения НомерТелефона Пол):");
        String input = scanner.nextLine();
        scanner.close();

        try {
            String[] data = input.split(" ");

            if (data.length != 6) {
                System.out.println("Ошибка: Неправильное количество данных.");
                return;
            }

            String lastName = data[0];
            String firstName = data[1];
            String middleName = data[2];
            String birthDate = data[3];
            String phoneNumber = data[4];
            char gender = data[5].charAt(0);

            if (birthDate.matches("\\d{2}.\\d{2}.\\d{4}") && phoneNumber.matches("\\d+") && (gender == 'ж' || gender == 'м')) {
                Person person = new Person(lastName, firstName, middleName, birthDate, phoneNumber, gender);
                saveToFile(person);
                System.out.println("Данные успешно сохранены.");
            } else {
                System.out.println("Ошибка: Неправильный формат данных.");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    private static void saveToFile(Person person) throws IOException {
        String fileName = person.getLastName() + ".txt";

        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName, true))) {
            writer.println(person.toString());
        }
    }
}