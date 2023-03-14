package net.alephdev.commands;

import java.io.IOException;
import net.alephdev.Main;
import net.alephdev.StorageManager;
/**
 * Command for removing all elements with greater id
 * @author MixaDev
 */
public class RemoveGreaterCommand implements Command {
    @Override
    public void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("Необходимо указать id, использование: remove_greater [id]");
            return;
        }
        try {
            Main.getStorageManager().removeGreater(Integer.parseInt(args[0]));
        } catch(NumberFormatException ex) {
            System.out.println("Введено неверное число");
            return;
        }
        System.out.println("Объекты удалены");
        try {
            Main.getStorageManager().save(StorageManager.autosaveName);
        } catch (IOException ex) {}
    }
    @Override
    public String getDesctiption() {
        return "удалить из коллекции все элементы, превышающие заданный id";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[]{"id"};
    }
}
