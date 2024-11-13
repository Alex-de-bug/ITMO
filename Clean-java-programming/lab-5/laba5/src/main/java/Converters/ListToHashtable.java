package Converters;


import data.Dragon;
import java.util.Hashtable;
import java.util.List;

/**
 * Класс, переводящий List в Hashtable
 */


public class ListToHashtable {
    private Hashtable<Integer, Dragon> dragonHashtable;

    /**
     * Метод, преобразующий List в Hashtable
     * @param  list
     * @return "Hashtable<Integer, Dragon>"
     */
    public Hashtable<Integer, Dragon> converter(List<Dragon> list){
        dragonHashtable = new Hashtable<>();

        for (int i = 0; i < list.size(); i++) {
            dragonHashtable.put(list.get(i).getId(), list.get(i));
        }
        return dragonHashtable;
    }
}
