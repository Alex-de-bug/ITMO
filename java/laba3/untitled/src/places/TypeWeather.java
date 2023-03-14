package places;
//тип погоды
public enum TypeWeather {
    SUNNY("солнечно"),
    RAINY("дождливо"),
    CLOUDY("пасмурно"),
    WINDY("ветрено"),
    SNOWY("снежно");

    private String name;
    TypeWeather(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
