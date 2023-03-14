package net.alephdev.commands;

import net.alephdev.Main;
/**
 * Command outputs some info about collection
 * @author MixaDev
 */
public class InfoCommand implements Command {
    @Override
    public void execute(String[] args) {
        System.out.println("Тип элементов: "+(!Main.getStorageManager().getAll().isEmpty() ? Main.getStorageManager().getAll().get(0).getClass().getName() : "Неизвестно"));
        System.out.println("Дата инициализации: "+Main.getStorageManager().getInitializationDate());
        System.out.println("Количество элементов: "+Main.getStorageManager().getAll().size());
    }
    @Override
    public String getDesctiption() {
        return "информация о коллекции";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
