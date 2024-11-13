package net.alephdev.commands;

import java.io.IOException;
import net.alephdev.Main;
import net.alephdev.StorageManager;
/**
 * Command for removing last element
 * @author MixaDev
 */
public class RemoveLastCommand implements Command {
    @Override
    public void execute(String[] args) {
        try {
            Main.getStorageManager().removeLast();
        } catch(NullPointerException ex) {
            System.out.println(ex.getMessage());
            return;
        }
        System.out.println("Последний элемент удален");
        try {
            Main.getStorageManager().save(StorageManager.autosaveName);
        } catch (IOException ex) {}
    }
    @Override
    public String getDesctiption() {
        return "удалить последний элемент из коллекции";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
