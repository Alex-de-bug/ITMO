package net.alephdev.commands;

import net.alephdev.Main;
/**
 * Command for clearing the whole collection
 * @author MixaDev
 */
public class ClearCommand implements Command {
    @Override
    public void execute(String[] args) {
        Main.getStorageManager().clear();
        System.out.println("Коллекция очищена");
    }
    @Override
    public String getDesctiption() {
        return "очистить коллекцию";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
