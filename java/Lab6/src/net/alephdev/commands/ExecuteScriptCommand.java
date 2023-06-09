package net.alephdev.commands;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import net.alephdev.Main;
/**
 * Command for executing scripts from file
 * @author MixaDev
 */
public class ExecuteScriptCommand implements Command {
    public static List<String> executedScripts = new ArrayList<>();
    @Override
    public void execute(String[] args) {
        if(args.length < 1) {
            System.out.println("Необходимо указать имя файла, использование: execute_script [file_name]");
            return;
        }
        List<String> commands = new ArrayList<>();
        StringBuilder line = new StringBuilder();
        executedScripts.add(args[0]);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(args[0]));
            int c;
            while ((c = reader.read()) != -1) {
                if ((char) c == '\n') {
                    String finished = line.toString();
                    line = new StringBuilder();
                    if(finished.startsWith("execute_script")) {
                        boolean pass = true;
                        for(String script : new ArrayList<>(executedScripts))
                            if(finished.contains(script)) {
                                System.out.println("Нельзя запустить скрипт, запущенный ранее");
                                pass = false;
                                break;
                            }
                        if(pass) {
                            executedScripts.add(finished.replace("\r", ""));
                            commands.add(finished.replace("\r", "")); // Windows adaptation
                        }
                    } else
                        commands.add(finished.replace("\r", "")); // Windows adaptation
                } else
                    line.append((char) c);
            }
        } catch(IOException ex) {
            System.out.println("Не удалось прочитать данные из файла");
        }
        if(line.length() > 0 && line.toString().replace("\r", "").replace("\n", "").length() > 0) {
            if(line.toString().startsWith("execute_script")) {
                boolean pass = true;
                for(String script : new ArrayList<>(executedScripts))
                    if(line.toString().contains(script)) {
                        System.out.println("Нельзя запустить скрипт, запущенный ранее");
                        pass = false;
                        break;
                    }
                if(pass) {
                    executedScripts.add(line.toString().replace("\r", "").replace("\n", ""));
                    commands.add(line.toString().replace("\r", "").replace("\n", "")); // Windows adaptation
                }
            }
            else
                commands.add(line.toString().replace("\r", "").replace("\n", "")); // Windows adaptation
        }
        for(String command : commands)
            Main.getCommandManager().executeInput(command);
    }
    @Override
    public String getDesctiption() {
        return "исполнить скрипт из указанного файла";
    }
    @Override
    public String[] getArgumentNames() {
        return new String[]{"file_name"};
    }
}
