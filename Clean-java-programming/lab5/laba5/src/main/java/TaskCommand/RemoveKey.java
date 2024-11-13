package TaskCommand;

import command.CommandWithArgument;

import static main1.Loader.dragonHashtable;
/**
 * Класс удаляет элемент из коллекции по его ключу
 */

public class RemoveKey implements CommandWithArgument {

    private int keyId;
    /**
     * Метод удаляет элемент из коллекции по его ключу
     */
    @Override
    public void execute() {
        dragonHashtable.remove(keyId);
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
