package ReaderAndWriter;


import TaskCommand.*;
import command.Command;
import command.CommandWithArgument;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Класс, читающий команды
 */

public class CommandReader implements Reader{
    private HashMap<String, Command> commandsNoArgument;
    private HashMap<String, CommandWithArgument> commandsWithArgument;
    static public List<String> history = new ArrayList<>();
    /**
     * Метод, определяющий команду
     * @param  command
     * @return String
     */

    @Override
    public String read(String command) {
        commandsNoArgument = new HashMap<>();
        commandsWithArgument = new HashMap<>();

        commandsNoArgument.put("help", new Help()); //
        commandsNoArgument.put("info", new Info()); //
        commandsNoArgument.put("show", new Show()); //
        commandsWithArgument.put("insert", new InsertNull()); //
        commandsWithArgument.put("update", new UpdateId()); //
        commandsWithArgument.put("remove_key", new RemoveKey());//
        commandsNoArgument.put("clear", new Clear());//
        commandsNoArgument.put("save", new Save());//
        commandsWithArgument.put("execute_script", new ExecuteScript());
        commandsNoArgument.put("exit", new Exit());//
        commandsWithArgument.put("remove_lower", new RemoveLower());//
        commandsNoArgument.put("history", new History());//
        commandsWithArgument.put("remove_lower_key", new RemoveLowerKey());//
        commandsNoArgument.put("min_by_head", new MinByHead());//
        commandsNoArgument.put("print_descending", new PrintDescending());//
        commandsNoArgument.put("print_field_descending_type", new PrintFieldDescendingType());//

        if((command.trim().split(" ").length>1)&&(commandsWithArgument.containsKey(command.trim().split(" ")[0]))){
            // with argument
            commandsWithArgument.get(command.trim().split(" ")[0]).getArgument(command.trim().split(" ")[1]);
//            history.add(command);
        }else if((command.trim().split(" ").length==1)&&commandsNoArgument.containsKey(command.trim().split(" ")[0])){
            //without argument
            commandsNoArgument.get(command.trim().split(" ")[0]).execute();
//            history.add(command);
        }else{
            //not found command
            System.out.println("Неверно введена команда!");
        }
        history.add(command);
        return null;
    }
}
