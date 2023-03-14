package TaskCommand;

import Converters.SortHashTable;
import command.Command;
import data.Dragon;

import java.util.*;

import static main1.Loader.dragonHashtable;

/**
 * Класс выводит значения поля type всех элементов в порядке убывания
 */

public class PrintFieldDescendingType implements Command {

    /**
     * Метод, который выводит значения поля type всех элементов в порядке убывания.
     */
    @Override
    public void execute() {
        ArrayList<Dragon> sortList= new SortHashTable().sort(dragonHashtable);
        SortedMap<Long, Dragon> sortedMap = new TreeMap<>(java.util.Collections.reverseOrder());

        for (int i = 0; i < sortList.size(); i++) {
            sortedMap.put(sortList.get(i).getHead().getEyesCount(),sortList.get(i));
        }
        for(Map.Entry<Long ,Dragon> entry : sortedMap.entrySet()) {
            Long key = entry.getKey();
            Dragon value = entry.getValue();

            System.out.println(key + " => " + value.getName());
        }


    }
}
