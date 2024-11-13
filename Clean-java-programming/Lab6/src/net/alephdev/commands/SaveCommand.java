package net.alephdev.commands;

import java.io.File;
import java.io.IOException;
import net.alephdev.Main;
import net.alephdev.StorageManager;
/**
 * Command for saving data into a file
 * @author MixaDev
 */
public class SaveCommand implements Command {
    @Override
    public void execute(String[] args) {
        try {
            Main.getStorageManager().save(Main.getStorageManager().getFilename());
        } catch(IOException ex) {
            System.out.println("Не удалось сохранить коллекцию в заданный файл!");
            return;
        }
        new File(StorageManager.autosaveName).delete();
        System.out.println("Коллекция сохранена");
    }
    @Override
    public String getDesctiption() {
        return "сохранить коллекцию в файл";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
