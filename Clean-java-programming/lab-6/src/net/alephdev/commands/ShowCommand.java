package net.alephdev.commands;

import net.alephdev.Main;
import net.alephdev.models.Organization;
/**
 * Command for showing current objects in collection
 * @author MixaDev
 */
public class ShowCommand implements Command {
    @Override
    public void execute(String[] args) {
        for(Organization organization : Main.getStorageManager().getAll())
            System.out.println(organization);
    }
    @Override
    public String getDesctiption() {
        return "вывести все элементы коллекции";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
