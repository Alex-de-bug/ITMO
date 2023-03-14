package TaskCommand;

import command.Command;

import java.time.LocalDateTime;

import static main1.Loader.dragonHashtable;

/**
 * Класс выводит информацию о коллекции
 */

public class Info implements Command {
    /**
     * Метод для вывода информации о коллекции
     */
    @Override
    public void execute() {
        System.out.println("Тип колллекции: Hashtable");
        System.out.println("Дата и время инициализации коллекции: " + LocalDateTime.now());
        System.out.println("Колличество элементов в коллекции: " + dragonHashtable.size());
    }
}
