package net.alephdev.commands;

import net.alephdev.Main;
/**
 * Command outputs available commands with arguments and description
 * @author MixaDev
 */
public class HelpCommand implements Command {
    @Override
    public void execute(String[] args) {
        for(String commandName : Main.getCommandManager().getCommands().keySet()) {
            System.out.print(commandName);
            Command command = Main.getCommandManager().getCommands().get(commandName);
            for(String argument : command.getArgumentNames())
                System.out.print(" ["+argument+"]");
            System.out.println(": "+command.getDesctiption());
        }
    }
    @Override
    public String getDesctiption() {
        return "справка по доступным командам";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[0];
    }
}
