import java.util.*;
//Зазноба Антон
public class PhoneBook
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner (System.in);
        Map<String, List<String>> phoneBook = new HashMap<>(); // Создаем телефонную книгу, где ключ - имя, значение - список телефонных номеров

        while (true)
        {
            //Выводим меню
            System.out.println("Меню:");
            System.out.println("1. Добавить контакт");
            System.out.println("2. Вывести телефонную книгу");
            System.out.println("3. Выйти");

            int choice = scanner.nextInt(); //Получаем выбор пользователя
            scanner.nextLine(); //Очищаем буфер ввода

            switch (choice)
            {
                case 1:
                    addContact(scanner, phoneBook);// Вызываем метод для добавления контакта
                    break;
                case 2:
                    printPhoneBook(phoneBook);// Вызываем метод для вывода телефонной книги
                    break;
                case 3:
                    System.out.println("Выход из программы");// Выводим сообщение о выходе
                    return;
                default:
                    System.out.println("Некорректный ввод. Попробуйте еще раз."); // Выводим сообщение об ошибке при некорректном выборе
            }
        }
    }

    private static void addContact (Scanner scanner, Map<String, List<String>> phoneBook)
    {
        System.out.println("Введите имя: ");
        String name = scanner.nextLine(); //Получаем имя от пользователя
        System.out.println("Введите номер телефона: ");
        String phoneNumber = scanner.nextLine(); //Получаем телефон от пользователя

        List<String> numbers = phoneBook.getOrDefault(name, new ArrayList<>()); // Получаем список номеров для данного имени, если нет - создаем новый список
        numbers.add(phoneNumber); // Добавляем номер телефона в список
        phoneBook.put(name, numbers); // Записываем список в телефонную книгу по данному имени

        System.out.println("Контакт успешно добавлен.");
    }

    private static void printPhoneBook(Map<String, List<String>> phoneBook)
    {
        if (phoneBook.isEmpty()) // Проверяем, пуста ли телефонная книга
        {
            System.out.println("Телефонная книга пуста.");
            return;
        }
        // Создаем список записей из телефонной книги
        List<Map.Entry<String, List<String>>> entries = new ArrayList<>(phoneBook.entrySet());

        // Сортируем список записей по убыванию количества номеров телефонов
        entries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        System.out.println("Телефонная книга:");
        for (Map.Entry<String, List<String>> entry : entries)
        {
            System.out.println(entry.getKey() + ":");
            List<String> sortedNumbers = entry.getValue();
            Collections.sort(sortedNumbers, Collections.reverseOrder()); // Сортировка номеров телефонов в порядке убывания
            for (String number : sortedNumbers)
            {
                System.out.println("  " + number);
            }
        }
    }
}