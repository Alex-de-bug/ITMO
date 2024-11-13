package Converters;

import data.Dragon;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс, переводящий List в Json
 */

public class ListToJsonParser {
    private List<String> dragonsString;
    /**
     * Метод, преобразующий List в Json
     * @param  dragonsList
     * @return "List<String>"
     */

    public List<String> converter(List<Dragon> dragonsList){
        dragonsString = new ArrayList<>();
        dragonsString.add(
                "{\n" +
                "  \"dragons\":\n" +
                "    ["
        );
        for(Dragon dragon : dragonsList) {
            if (dragon.getType() != null) {
                dragonsString.add(
                        "      {\n" +
                                "        \"id\": " + dragon.getId() + ",\n" +
                                "        \"name\": \"" + dragon.getName().replaceAll("\\\\","\\\\\\\\").replaceAll("\"","\\\\\"") + "\",\n" +
                                "        \"coordinates\": [\n" +
                                "          " + dragon.getCoordinates().getX() + ",\n" +
                                "          " + dragon.getCoordinates().getY() + "\n" +
                                "        ],\n" +
                                "        \"creationDate\": \"" + dragon.getCreationDate() + "\",\n" +
                                "        \"age\": " + dragon.getAge() + ",\n" +
                                "        \"wingspan\": " + dragon.getWingspan() + ",\n" +
                                "        \"color\": \"" + dragon.getColor().getColor() + "\",\n" +
                                "        \"type\": \"" + dragon.getType().getType() + "\",\n" +
                                "        \"head\": " + dragon.getHead().getEyesCount() + "\n" +
                                "      }"
                );
            }else{
                dragonsString.add(
                        "      {\n" +
                                "        \"id\": " + dragon.getId() + ",\n" +
                                "        \"name\": \"" + dragon.getName().replaceAll("\"","\\\"1") + "\",\n" +
                                "        \"coordinates\": [\n" +
                                "          " + dragon.getCoordinates().getX() + ",\n" +
                                "          " + dragon.getCoordinates().getY() + "\n" +
                                "        ],\n" +
                                "        \"creationDate\": \"" + dragon.getCreationDate() + "\",\n" +
                                "        \"age\": " + dragon.getAge() + ",\n" +
                                "        \"wingspan\": " + dragon.getWingspan() + ",\n" +
                                "        \"color\": \"" + dragon.getColor().getColor() + "\",\n" +
                                "        \"type\": null,\n" +
                                "        \"head\": " + dragon.getHead().getEyesCount() + "\n" +
                                "      }");
            }
        }
        dragonsString.add(
                "    ]\n" +
                        "}"
        );
        return dragonsString;
    }
}
