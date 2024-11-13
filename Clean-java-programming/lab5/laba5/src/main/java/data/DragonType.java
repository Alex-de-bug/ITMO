package data;

/**
 * Класс, содержащий данные о типе Дракона
 */

public enum DragonType {
    WATER("water"),
    UNDERGROUND("underground"),
    AIR("air"),
    FIRE("fire");
    String type;
    DragonType(String type){
        this.type=type;
    }

    /**
     * Получение строкового представления объекта
     * @return String
     */

    @Override
    public String toString() {
        return type;
    }

    public String getType() {
        return type;
    }
}
