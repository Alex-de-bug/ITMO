package TaskCommand;

import FileChecker.DragonCheck;
import command.CommandWithArgument;
import data.*;
import main1.Loader;

import java.time.LocalDateTime;
import java.util.*;

import static main1.Loader.dragonHashtable;

/**
 * Класс обновляет значение элемента коллекции, id которого равен заданному
 */

public class UpdateId implements CommandWithArgument {
    private int keyId;


    private List<Dragon> newDrag= new ArrayList<>();
    private boolean add = false;
    DragonCheck dragonCheck = new DragonCheck();

    /**
     * Метод обновляет значение элемента коллекции, id которого равен заданному
     */

    @Override
    public void execute() {
        System.out.println("Вы хотите поменять все поля этого дракона или только одно\n введите число 1 - все поля, 2 - одно из полей");
        try{
            int t = Loader.lineReader.getScanner().nextInt();
            if(t==1){
                changeAllFields();
            }else if(t==2){
                changeOneFields();
            }else{ throw new Exception();}
        }catch (Exception e){
            System.out.println(e.toString());
            System.out.println("Неверный ключ, попробуйте ещё раз.");
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
            if(dragonHashtable.containsKey(Integer.parseInt(argument))){
                System.out.println("Данный ключ подходит");
                keyId = Integer.parseInt(argument);
                execute();
            }else{
                System.out.println("Данный ключ не содержится в коллекции, либо меньшь или равен 0");
            }
        }catch (NumberFormatException e){
            System.err.println("Ключ введён неверно");
        }
    }

    /**
     * Метод изменяющий одно из полей
     */

    private void changeOneFields(){
        List<String> fields = Arrays.asList("id", "name", "coordinates", "age", "wingspan", "color", "type", "head");
        System.out.println("Доступные поля для редактирования - name, coordinates, age, wingspan, color, type, head\n введите поле для редактирования: ");
        String field = "";
        while (field.length()==0) {
            field = Loader.lineReader.getScanner().nextLine();
        }
        if(fields.contains(field.trim())){

            if(field.trim().equals("id")){
                try {
                    System.out.println("Введите int id: ");
                    int newId = Loader.lineReader.getScanner().nextInt();
                    if(!(dragonHashtable.containsKey(newId))&&(newId>0)) {
                        dragonHashtable.get(keyId).setId(newId);

                    }else{ throw new Exception();}
                }catch (Exception e){
                    System.out.println("id не подходит");
                }
            }else if(field.trim().equals("name")){
                try {
                    System.out.println("Введите String name: ");
                    String newName = "";
                    while (newName.length()==0) {
                        newName = Loader.lineReader.getScanner().nextLine();
                    }
                    if(newName!=""){
                        dragonHashtable.get(keyId).setName(newName);
                    }else{ throw new Exception();}
                }catch (Exception e){
                    System.out.println("name не подходит");
                }

            }else if(field.trim().equals("coordinates")){
                try {
                    System.out.println("Введите integer coordinates x (max 962): ");
                    Integer x = Loader.lineReader.getScanner().nextInt();
                    String yString = "";
                    System.out.println("Введите float coordinates y (max 450): ");
                    while (yString.length()==0) {
                        yString = Loader.lineReader.getScanner().nextLine();
                    }
                    float y = Float.parseFloat(yString);
                    Coordinates coordinates = new Coordinates(x, y);
                    if((x<=962)&&(y<=450)&&(x!=null)){
                        dragonHashtable.get(keyId).setCoordinates(coordinates);
                    }else{ throw new Exception();}
                }catch (Exception e){
                    System.out.println("coordinates не подходят");
                }

            }else if(field.trim().equals("age")){
                try {
                    System.out.println("Введите integer age (>0): ");
                    int age = Loader.lineReader.getScanner().nextInt();
                    if(age>0){
                        dragonHashtable.get(keyId).setAge(age);
                    }else{ throw new Exception();}
                }catch (Exception e){
                    System.out.println("age не подходит");
                }
            }else if(field.trim().equals("wingspan")){
                try {
                    String wingspanStr = "";
                    System.out.println("Введите double wingspan (>0): ");
                    while (wingspanStr.length()==0) {
                        wingspanStr = Loader.lineReader.getScanner().nextLine();
                    }
                    double wingspan = Double.parseDouble(wingspanStr);
                    if(wingspan>0){
                        dragonHashtable.get(keyId).setWingspan(wingspan);
                    }else{ throw new Exception();}
                }catch (Exception e){
                    System.out.println("wingspan не подходит");
                }
            }else if(field.trim().equals("color")){
                try {
                    String color = "";
                    System.out.println("Введите color (GREEN, BLUE, YELLOW, ORANGE, WHITE): ");
                    while (color.length()==0) {
                        color = Loader.lineReader.getScanner().nextLine();
                    }
                    if(color.equals("null")){throw new Exception();}
                    Color color1 = Color.valueOf(color.toUpperCase(Locale.ROOT));
                    dragonHashtable.get(keyId).setColor(color1);
                }catch (Exception e){
                    System.out.println("color не подходит");
                }
            }else if(field.trim().equals("type")){
                try {
                    String type = "";
                    System.out.println("Введите type (WATER, UNDERGROUND, AIR, FIRE): ");
                    while (type.length()==0) {
                        type = Loader.lineReader.getScanner().nextLine();
                    }
                    if(!type.equals("null")){
                        DragonType type1 = DragonType.valueOf(type.toUpperCase(Locale.ROOT));
                        dragonHashtable.get(keyId).setType(type1);
                    }
                    else{
                        dragonHashtable.get(keyId).setType(null);
                    }
                }catch (Exception e){
                    System.out.println("type не подходит");
                }
            }else if(field.trim().equals("head")){
                try {
                    System.out.println("Введите long eyes count: ");
                    long eyes = Loader.lineReader.getScanner().nextLong();
                    DragonHead head = new DragonHead(eyes);
                    dragonHashtable.get(keyId).setHead(head);
                }catch (Exception e){
                    System.out.println("head не подходит");
                }
            }

        }else{
            System.out.println("Неверный ключ, пробуйте ещё раз!");
        }
    }

    /**
     * Метод изменяющий все поля
     */

    private void changeAllFields(){Dragon newDragon = new Dragon();
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
                System.out.println(1);
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

}
