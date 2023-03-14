package main1;

import Converters.ListToHashtable;
import FileChecker.DragonCheck;
import ReaderAndWriter.CommandReader;
import WelcomeToConsole.LineReader;
import data.Dragon;
import Converters.JsonParser;
import data.DragonAgeComparator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Класс, который считывает файл и запускает считывание команд
 */


public class Loader {
    private JsonParser jsonParser;
    private DragonCheck dragonCheck;
    private ListToHashtable listToHashtable;
    private Scanner scanner;

    public static LineReader lineReader;
    static public Hashtable<Integer, Dragon> dragonHashtable;

    /**
     * Метод загружает коллекцию
     */
    public void startInput() throws FileNotFoundException {

        jsonParser = new JsonParser();
        dragonCheck = new DragonCheck();
        listToHashtable = new ListToHashtable();
        lineReader = new LineReader();
        scanner = new Scanner(System.in);


        List<Dragon> dragonsList;


        dragonHashtable = new Hashtable<>();
        String jsonInput="";

        String ans1 = "";
        File file1 = new File("/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/timeMashine.json");
        if(!(file1.length() == 0)){
            System.out.println("У вас есть несохранённые данные после аварийного завершения программы, хотите продолжить с ними (y/n)");
            ans1 = scanner.nextLine();
            System.out.println(ans1);
            if(ans1.equals("y")){
                jsonInput = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/timeMashine.json";
                System.out.println(1);
            }else{
                File FileDel = new File("/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/timeMashine.json");
                PrintWriter writer = new PrintWriter(FileDel);
                writer.print("");
                writer.close();
                System.out.print("Введите название файла json с его расширением: ");
                jsonInput = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/"+scanner.nextLine();
            }
        }else{
            System.out.print("Введите название файла json с его расширением: ");
            jsonInput = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/"+scanner.nextLine();
        }


//        String jsonInput = "/Users/alexalex/Desktop/Prog/laba5/src/main/java/main1/dragons.json";

        try{
            //открытие файла
            File file = new File(jsonInput);

            //проверка файла
            if (!file.canWrite() || !file.isFile()  || !file.canRead()) throw new IOException();

            //парсинг файла
            dragonsList = jsonParser.fromJsonToObject(file.getPath());

            //валидация полуенных значений
            dragonsList = dragonCheck.check(dragonsList);

            //перевод в коллекцию
            if(dragonsList.size()!=0){
            dragonHashtable = listToHashtable.converter(dragonsList);
            }else{System.out.println("(В файле нет драконов)");}

        }catch (IOException  e) {
            System.out.println(e.getMessage());
            System.out.println("Файл отсутствует, либо отказано в доступе!");
            System.out.println("Хотите ли вы продолжить без файла? y/n");
            String ans="";
            while (ans.equals("")){
                ans = scanner.nextLine();
            }
            if(ans.equals("y")){
                working();
            }else{
                startInput();
            }
        }catch (ClassCastException e1){
            System.err.println("В файле не соблюдены все правила заполнения значений. \n "+e1.getMessage());
            System.exit(1);
        }
        try {
            working();
        }catch (NoSuchElementException e){
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Метод, работающий в течение работы программы и считывающий команды
     */
    public void working(){

        System.out.println("\n Добро пожаловать! Программа запущена!");
        System.out.println("Чтобы получить список доступных комманд, введите 'help'");
        while (true){
            new blowSaver().save();
            System.out.println("\nВведите имя команды: ");
            String command = lineReader.read();
            if(!command.equals("")){
                new CommandReader().read(command);
            }
        }
    }
    /**
     * Метод, работающий в течение работы программы и считывающий команды со скрипта
     */
    public void workingScript(Scanner scanner, String argument){
        System.out.println("\n Добро пожаловать! Программа запущена!");
        CommandReader commandReader = new CommandReader();
        while (true){
            lineReader.setScanner(scanner);
            try {
                String command = lineReader.read();
                if(!command.trim().equals("execute_script "+argument)){
                    System.out.println("\n"+command);
                    if(command!=""){
                        new blowSaver().save();
                        commandReader.read(command);
                    }
                }
            }catch (Exception e){
                System.out.println("\n"+"exit");

                commandReader.read("exit");
            }
        }
    }
}