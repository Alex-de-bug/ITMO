package net.alephdev;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;
import net.alephdev.commands.*;

public class CommandManager {
    private Map<String, Command> commands = new LinkedHashMap<>();
    public CommandManager() {
        registerCommand("help", new HelpCommand()); // +
        registerCommand("info", new InfoCommand()); // +
        registerCommand("show", new ShowCommand()); // +
        registerCommand("add", new AddCommand()); // +
        registerCommand("update", new UpdateCommand()); // +
        registerCommand("remove_by_id", new RemoveByIdCommand()); // +
        registerCommand("clear", new ClearCommand()); // +
        registerCommand("save", new SaveCommand()); // +
        registerCommand("execute_script", new ExecuteScriptCommand()); // +
        registerCommand("exit", new ExitCommand()); // +
        registerCommand("remove_last", new RemoveLastCommand()); // +
        registerCommand("remove_greater", new RemoveGreaterCommand());
        registerCommand("reorder", new ReorderCommand()); // +
        registerCommand("max_by_annual_turnover", new MaxByAnnualTurnoverCommand()); // +
        registerCommand("group_counting_by_type", new GroupCountingByTypeCommand()); // +
        registerCommand("filter_by_type", new FilterTypeCommand()); // +
    }
    /**
     * Start reading user prompt
     */
    public void run() {
        System.out.println("Для вывода доступных команд напишите help");
        Scanner scanner = new Scanner(System.in);
        while(true) {
            ExecuteScriptCommand.executedScripts.clear();
            System.out.print("> ");
            executeInput(scanner.nextLine());
        }
    }
    /**
     * Execute command
     * @param input Command with arguments
     */
    public final void executeInput(String input) {
        String[] command = input.split(" ", 2);
        if(!commands.containsKey(command[0]))
            System.out.println("Неизвестная команда: '"+command[0]+"'");
        else
            commands.get(command[0]).execute(command.length > 1 ? command[1].split(" ") : new String[0]);
    }
    /**
     * Register command
     * @param name Command name
     * @param command Object with command interface
     */
    public final void registerCommand(String name, Command command) {
        commands.put(name, command);
    }
    /**
     * Get list of commands
     * @return Available commands
     */
    public Map<String, Command> getCommands() {
        return commands;
    }
}
