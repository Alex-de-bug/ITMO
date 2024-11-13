package net.alephdev.commands;

import java.util.Arrays;
import net.alephdev.Main;
import net.alephdev.models.Organization;
import net.alephdev.models.OrganizationType;
/**
 * Command for filtering elements by type
 * @author MixaDev
 */
public class FilterTypeCommand implements Command {
    @Override
    public void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("Необходимо указать тип, использование: filter_by_type "+Arrays.toString(OrganizationType.values()));
            return;
        }
        OrganizationType type;
        try {
            type = OrganizationType.valueOf(args[0]);
        } catch(IllegalArgumentException ex) {
            System.out.println("Данного типа организации не существует, введите тип из предложенных: "+Arrays.toString(OrganizationType.values()));
            return;
        }
        for(Organization organization : Main.getStorageManager().getAll()) {
            if(organization.getType() != type)
                continue;
            System.out.println(organization);
        }
    }
    @Override
    public String getDesctiption() {
        return "вывести элементы, значение поля type которых равно заданному";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[]{"type"};
    }
}
