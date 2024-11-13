package TaskCommand;

import Converters.SortHashTable;
import command.Command;
import data.Dragon;

import java.util.ArrayList;

import static main1.Loader.dragonHashtable;

/**
 * Класс вывести элементы коллекции в порядке убывания
 */

public class PrintDescending implements Command {

    /**
     * Метод, который выводит элементы коллекции в порядке убывания
     */
    @Override
    public void execute() {
        ArrayList<Dragon> sortList= new SortHashTable().sortReverse(dragonHashtable);
        for (int i = 0; i < sortList.size(); i++) {
            System.out.println(sortList.get(i).toString());
        }
    }
}
