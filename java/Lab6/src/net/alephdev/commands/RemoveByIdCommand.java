package net.alephdev.commands;

import java.io.IOException;
import net.alephdev.Main;
import net.alephdev.StorageManager;
/**
 * Command for removing collection element by id
 * @author MixaDev
 */
public class RemoveByIdCommand implements Command{
    @Override
    public void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("Необходимо указать id, использование: remove_by_id [id]");
            return;
        }
        try {
            Main.getStorageManager().remove(Integer.parseInt(args[0]));
        } catch(NumberFormatException ex) {
            System.out.println("Введено неверное число");
            return;
        } catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Объект удален");
        try {
            Main.getStorageManager().save(StorageManager.autosaveName);
        } catch (IOException ex) {}
    }
    @Override
    public String getDesctiption() {
        return "удалить элемент из коллекции";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[]{"id"};
    }
}
