package net.alephdev.commands;

import java.util.HashMap;
import java.util.Map;
import net.alephdev.Main;
import net.alephdev.models.Organization;
import net.alephdev.models.OrganizationType;
/**
 * Command for grouping elements by type
 * @author MixaDev
 */
public class GroupCountingByTypeCommand implements Command {
    @Override
    public void execute(String[] args) {
        Map<OrganizationType, Integer> result = new HashMap<>();
        for(Organization organization : Main.getStorageManager().getAll()) {
            if(result.containsKey(organization.getType()))
                result.put(organization.getType(), result.get(organization.getType())+1);
            else
                result.put(organization.getType(), 1);
        }
        for(OrganizationType type : OrganizationType.values()) {
            if(!result.containsKey(type))
                System.out.println(type+": 0 использований");
            else
                System.out.println(type+": "+result.get(type)+" использований");
        }
    }
    @Override
    public String getDesctiption() {
        return "вывод количества элементов в каждой группе по type";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
