package TaskCommand;

import command.Command;
import data.Dragon;

import java.util.Enumeration;

import static main1.Loader.dragonHashtable;

/**
 * Класс выводит любой объект из коллекции, значение поля head которого является минимальным.
 */

public class MinByHead implements Command {

    /**
     * Метод, который выводит любой объект из коллекции, значение поля head которого является минимальным.
     */
    @Override
    public void execute() {
        if(dragonHashtable.size()!=0){
            long minHeadDragon = Long.MAX_VALUE;
            Dragon minDragon = new Dragon();
            // Creating  Enumeration interface
            // and get keys() from Hashtable
            Enumeration<Integer> e = dragonHashtable.keys();
            // Iterating through the Hashtable
            // object
            // Checking for next element in Hashtable object
            // with the help of hasMoreElements() method
            while (e.hasMoreElements()) {
                // Getting the key of a particular entry
                int key = e.nextElement();
                // Print and display the Rank and Name
                if(dragonHashtable.get(key).getHead().getEyesCount()<=minHeadDragon){
                    minHeadDragon=dragonHashtable.get(key).getHead().getEyesCount();
                    minDragon = dragonHashtable.get(key);
                }
            }
            System.out.println(minDragon);
        }else{
            System.out.println("Коллекция пуста!");
        }
    }
}
