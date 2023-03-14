package Converters;

import data.Dragon;
import data.DragonHead;

import java.util.*;


/**
 * Класс, для сортировки коллекции
 */


public class SortHashTable {
    /**
     * Метод, сортирующий коллекцию HashTable
     * @param  list
     * @return "ArrayList<Dragon>"
     */
    public ArrayList<Dragon> sort(Hashtable<Integer, Dragon> list){
        ArrayList<Dragon> dragonArrayList = new ArrayList<>();
        // Creating  Enumeration interface
        // and get keys() from Hashtable
        Enumeration<Integer> e = list.keys();
        // Iterating through the Hashtable
        // object
        // Checking for next element in Hashtable object
        // with the help of hasMoreElements() method
        while (e.hasMoreElements()) {
            // Getting the key of a particular entry
            int key = e.nextElement();
            dragonArrayList.add(list.get(key));
        }
        Collections.sort(dragonArrayList);
        return dragonArrayList;
    }

    public ArrayList<Dragon> sortReverse(Hashtable<Integer, Dragon> list){
        ArrayList<Dragon> revDragon;

        revDragon = sort(list);

        Collections.reverse(revDragon);

        return revDragon;
    }

}
