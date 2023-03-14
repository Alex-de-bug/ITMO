package TaskCommand;

import ReaderAndWriter.FileReader;
import WelcomeToConsole.LineReader;
import command.CommandWithArgument;
import main1.Loader;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Класс, очищающий коллекцию
 */

public class ExecuteScript implements CommandWithArgument {
    private LineReader lineReader;
    private String link;
    private FileReader fileReader;
    private Loader loader;
    private String argum;

    /**
     * Метод исполняющий скрипт
     */
    @Override
    public void execute() {
        fileReader = new FileReader();
        String script = fileReader.read(link);
        loader = new Loader();
        loader.workingScript(new Scanner(script), argum);
    }

    /**
     * Метод обрабатывающий аргумент
     * @param argument
     */
    @Override
    public void getArgument(String argument) {
        this.link = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/"+argument;
        this.argum = argument;
        try{
            File file = new File(link);
            //проверка файла
            if (!file.canWrite() || !file.isFile()  || !file.canRead()) throw new IOException();
            execute();
        }catch (IOException  e) {
            System.out.println(e.getMessage());
            System.out.println("Файл отсутствует, либо отказано в доступе!");
            System.exit(1);
        }
    }
}
