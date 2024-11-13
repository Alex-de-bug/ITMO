package TaskCommand;

import ReaderAndWriter.CommandReader;
import command.Command;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import static ReaderAndWriter.CommandReader.history;


/**
 * Класс завершает программу (без сохранения в файл)
 */
public class Exit implements Command {
    private Scanner scanner = new Scanner(System.in);
    @Override
    public void execute() {
        CommandReader commandReader = new CommandReader();
        File FileDel = new File("/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/timeMashine.json");
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(FileDel);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        writer.print("");
        writer.close();
        if(!history.get(history.size()-1).equals("save")){
            System.out.println("Хотите ли вы сохранить данные перед выходом? (y/n)");
            String answer = scanner.nextLine();
            if (answer.equals("y")){
                commandReader.read("save");
            }
        }
        System.out.println("Программа завершена!");
        System.exit(0);
    }
}
