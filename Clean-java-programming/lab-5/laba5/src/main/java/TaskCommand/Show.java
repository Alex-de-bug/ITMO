package TaskCommand;

import Converters.SortHashTable;
import command.Command;
import data.Dragon;

import java.util.ArrayList;
import java.util.Enumeration;

import static main1.Loader.dragonHashtable;

/**
 * Класс выводит все объекты коллекции в строковом представлении
 */
public class Show implements Command {
    /**
     * Метод выводит объекты в строковом представлении
     * (печатается переопределённый toString)
     */
    @Override
    public void execute() {
        if (dragonHashtable.size() != 0) {
            ArrayList<Dragon> sortList= new SortHashTable().sort(dragonHashtable);
            for (int i = 0; i < sortList.size(); i++) {
                System.out.println(sortList.get(i).toString());
            }
//            // Creating  Enumeration interface
//            // and get keys() from Hashtable
//            Enumeration<Integer> e = dragonHashtable.keys();
//            // Iterating through the Hashtable
//            // object
//            // Checking for next element in Hashtable object
//            // with the help of hasMoreElements() method
//            while (e.hasMoreElements()) {
//                // Getting the key of a particular entry
//                int key = e.nextElement();
//                // Print and display the Key and Name
//                System.out.println("Key : " + key
//                        + "\t\t Dragon : "
//                        + dragonHashtable.get(key));
//            }
        } else {
            System.out.println("Коллекция пуста!");
        }
    }
}
