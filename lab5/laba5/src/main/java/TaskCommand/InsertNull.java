package TaskCommand;

import FileChecker.DragonCheck;
import command.CommandWithArgument;
import data.*;
import main1.Loader;

import java.time.LocalDateTime;
import java.util.*;

import static main1.Loader.dragonHashtable;

/**
 * Класс для добавления новых элементов
 */

public class InsertNull implements CommandWithArgument {
    private int keyId;
//    private Scanner scanner1 = new LineReader().getScanner();
    private List<Dragon> newDrag= new ArrayList<>();
    private boolean add = false;
    DragonCheck dragonCheck = new DragonCheck();
    /**
     * Метод, который добавляет новый элемент в коллекцию
     */

    @Override
    public void execute() {
        Dragon newDragon = new Dragon();
        String name;
        Coordinates coordinates;
        int age = -5;
        double wingspan = 0;
        Color color1;
        DragonType type1;
        DragonHead head;
        Boolean ans = true;

        try{
            System.out.println("Введите String name: ");
            name = Loader.lineReader.getScanner().nextLine();
            Integer x = 31231234;

            while (ans){
                System.out.println("Введите integer coordinates x (max 962): ");
                x = Loader.lineReader.getScanner().nextInt();
                if(x<=962){
                    ans=false;
                }
            }
            ans = true;

            float y = 467;
            while (ans){
                String yString = "";
                System.out.println("Введите float coordinates y (max 450): ");
                while (yString.length()==0) {
                    yString = Loader.lineReader.getScanner().nextLine();
                }
                y = Float.parseFloat(yString);
                if(y<=450){
                    ans=false;
                }
            }
            ans = true;
            coordinates = new Coordinates(x, y);

            while (ans){
                System.out.println("Введите integer age (>0): ");
                age = Loader.lineReader.getScanner().nextInt();
                if(age>0){
                    ans=false;
                }
            }
            ans = true;

            String color = "";

            while (ans){
                color = "";
                System.out.println("Введите color (GREEN, BLUE, YELLOW, ORANGE, WHITE): ");
                while (color.length()==0) {
                    color = Loader.lineReader.getScanner().nextLine();
                }
                ArrayList<String> colors = new ArrayList<>();
                colors.add("GREEN");
                colors.add("BLUE");
                colors.add("YELLOW");
                colors.add("ORANGE");
                colors.add("WHITE");
                if (colors.contains(color)){
                    ans = false;
                }
            }
            ans = true;


            while (ans){
                String wingspanStr = "";
                System.out.println("Введите double wingspan (>0): ");
                while (wingspanStr.length()==0) {
                    wingspanStr = Loader.lineReader.getScanner().nextLine();
                }
                wingspan = Double.parseDouble(wingspanStr);
                if(wingspan>0){
                    ans=false;
                }
            }
            ans = true;

            String type = "";
            while (ans){
                type = "";
                System.out.println("Введите type (WATER, UNDERGROUND, AIR, FIRE): ");
                ArrayList<String> types = new ArrayList<>();
                types.add("WATER");
                types.add("UNDERGROUND");
                types.add("AIR");
                types.add("FIRE");
                while (type.length()==0) {
                    type = Loader.lineReader.getScanner().nextLine();
                }
                if (types.contains(type)){
                    ans = false;
                }
            }
            ans = true;


            System.out.println("Введите long eyes count: ");
            long eyes = Loader.lineReader.getScanner().nextLong();

            color1 = Color.valueOf(color.toUpperCase(Locale.ROOT));
            head = new DragonHead(eyes);

            if(!type.equals("null")){
                type1 = DragonType.valueOf(type.toUpperCase(Locale.ROOT));
                newDragon = new Dragon(keyId, name, coordinates, LocalDateTime.now(), age, wingspan, color1, type1, head);

            }
            else{
                newDragon = new Dragon(keyId, name, coordinates, LocalDateTime.now(), age, wingspan, color1, null, head);
            }

//            Dragon newDragon = new Dragon(keyId, name, coordinates, LocalDateTime.now(), age, wingspan, color1, null, head);
            newDrag.add(newDragon);
            newDrag = dragonCheck.check(newDrag);
            if((newDrag.size()!=0)){
                dragonHashtable.put(keyId, newDragon);
                System.out.println("Дракоша добавлен!");
            }else{
                System.out.println("Дракоша не добавлен!");
            }
        }catch (Exception e){
            System.out.println("Попробуйте заново \n"+e.toString());
        }
    }

    /**
     * Метод обрабатывающий аргумент
     * @param argument
     */
    @Override
    public void getArgument(String argument) {
        System.out.println("Вы ввели ключ "+argument);
        try {
            if((!(dragonHashtable.containsKey(Integer.parseInt(argument))))&&(Integer.parseInt(argument)>0)){
                System.out.println("Данный ключ подходит");
                keyId = Integer.parseInt(argument);
                execute();
            }else{
                System.out.println("Данный ключ уже содержится в коллекции, либо меньшь или равен 0");
            }
        }catch (NumberFormatException e){
            System.err.println("Ключ введён неверно");
        }

    }
}
