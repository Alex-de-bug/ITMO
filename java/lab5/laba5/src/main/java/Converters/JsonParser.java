package Converters;


import ReaderAndWriter.FileReader;
import data.*;
import java.time.LocalDateTime;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Класс, переводящий json-файл в List
 */


public class JsonParser {
    private FileReader fileReader;
    private JSONParser parser;

    /**
     * Метод, преобразующий json-файл в List
     * @param filepath
     * @return "List<Dragon>"
     */
    public List<Dragon> fromJsonToObject(String filepath) {
        DragonType type1;
        DragonHead dragonHead;
        Coordinates coordinates;

        //cчитывание файла fileReader'ом
        fileReader = new FileReader();
        parser = new JSONParser();
        String file;
        file = fileReader.read(filepath);
        List<Dragon> dragonsList = new ArrayList<>();

        if (file.isEmpty()){
            System.out.println("Файл пуст, некоторые функции недоступны");
            return dragonsList;
        };

        //парсинг файла
        try {
            JSONObject dragonsJsonObject = (JSONObject) parser.parse(file);
            JSONArray dragonsJsonArray = (JSONArray) dragonsJsonObject.get("dragons");

            for (Object it : dragonsJsonArray) {
                JSONObject dragonJsonObject = (JSONObject) it;

                long id = (Long)dragonJsonObject.get("id");

                String name = (String)dragonJsonObject.get("name");


                JSONArray coordinatesJsonArray = (JSONArray)dragonJsonObject.get("coordinates");
                long x = (Long)coordinatesJsonArray.get(0);
                double y = (Double)coordinatesJsonArray.get(1);
                coordinates = new Coordinates((int) x, (float) y);



                String creationDateString = (String)dragonJsonObject.get("creationDate");
                LocalDateTime dateTime = LocalDateTime.parse(creationDateString);

                long age = (Long)dragonJsonObject.get("age");

                double wingspan = (Double)dragonJsonObject.get("wingspan");

                String colorString = (String)dragonJsonObject.get("color");

                try{
                    String type = (String)dragonJsonObject.get("type");
                    type1 = DragonType.valueOf(type.toUpperCase(Locale.ROOT));
                }catch (NullPointerException e){
                    type1 = null;
                }
                try{
                    long countEyes = (long)dragonJsonObject.get("head");
                    dragonHead = new DragonHead(countEyes);
                }catch (NullPointerException e){
                    dragonHead = null;
                }

                Dragon dragon1 = new Dragon((int) id, name, coordinates, dateTime, (int) age, (double) wingspan, Color.valueOf(colorString.toUpperCase(Locale.ROOT)), type1, dragonHead);
                dragonsList.add(dragon1);
            }

        } catch (ParseException e1){
            System.err.println("Файл не является пригодным для парсинга, один из тегов пуст или файл содержит критическую ошибку конструкции json. \n "+e1.getMessage());
            System.exit(1);
        } catch (ClassCastException e2){
            System.err.println("Перепроверьте правильность введённых данных в файле. \n "+e2.getMessage());
            System.exit(1);
        } catch (NullPointerException e3){
            System.err.println("Файл не является пригодным для парсинга, один из тегов не найден. \n "+e3.getMessage());
            System.exit(1);
        }
        return dragonsList;
    }
}
