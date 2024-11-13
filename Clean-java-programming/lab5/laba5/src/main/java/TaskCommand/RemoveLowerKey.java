package TaskCommand;

import Converters.SortHashTable;
import command.CommandWithArgument;
import data.Dragon;

import java.util.ArrayList;

import static main1.Loader.dragonHashtable;

/**
 * Класс удаляет из коллекции все элементы, ключ которых меньше, чем заданный
 */

public class RemoveLowerKey implements CommandWithArgument {

    private int keyId;

    /**
     * Метод удаляет из коллекции все элементы, ключ которых меньше, чем заданный
     */

    @Override
    public void execute() {
        ArrayList<Dragon> sortList= new SortHashTable().sort(dragonHashtable);
        for (int i = 0; i < sortList.size(); i++) {
            if(sortList.get(i).getId()<dragonHashtable.get(keyId).getId()){
                dragonHashtable.remove(sortList.get(i).getId());
            }
        }
    }

    /**
     * Метод обрабатывающий аргумент
     * @param argument
     */

    @Override
    public void getArgument(String argument) {
        System.out.println("Вы ввели ключ "+argument);
        try {
            if(dragonHashtable.containsKey(Integer.parseInt(argument))){
                System.out.println("Данный ключ подходит");
                keyId = Integer.parseInt(argument);
                execute();
            }else{
                System.out.println("Данный ключ не содержится в коллекции, либо меньшь или равен 0");
            }
        }catch (NumberFormatException e){
            System.err.println("Ключ введён неверно");
        }
    }
}
