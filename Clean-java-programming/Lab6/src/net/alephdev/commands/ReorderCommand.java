package net.alephdev.commands;

import java.io.IOException;
import net.alephdev.Main;
import net.alephdev.StorageManager;
/**
 * Command for reordering collection
 * @author MixaDev
 */
public class ReorderCommand implements Command {
    @Override
    public void execute(String[] args) {
        Main.getStorageManager().reverse();
        System.out.println("Объекты отсортированы в обратном порядке");
        try {
            Main.getStorageManager().save(StorageManager.autosaveName);
        } catch (IOException ex) {}
    }
    @Override
    public String getDesctiption() {
        return "отсортировать коллекцию в обратном порядке";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
