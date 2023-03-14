package net.alephdev.commands;

import net.alephdev.Main;
import net.alephdev.models.Organization;
/**
 * Command for searching element with maximal annual turnover
 * @author MixaDev
 */
public class MaxByAnnualTurnoverCommand implements Command {
    @Override
    public void execute(String[] args) {
        Organization result = null;
        for(Organization organization : Main.getStorageManager().getAll()) {
            if(result == null || organization.getAnnualTurnover() > result.getAnnualTurnover())
                result = organization;
        }
        if(result == null)
            System.out.println("Коллекция пуста");
        else
            System.out.println(result);
    }
    @Override
    public String getDesctiption() {
        return "вывод организации с наибольшим ежегодным доходом";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
