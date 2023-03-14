package locations;

import enums.Month;
import enums.TimeOfDay;
import enums.Weather;

public class Vale extends Location {

    public Vale(String name) {
        setName(name);
        System.out.printf("%s создана\n", name);
    }

    public Vale() {
        System.out.printf("%s создана\n", getName());
    }

    public Vale(String name, Month month, Weather weather, TimeOfDay timeOfDay) {
        this.month = month;
        this.weather = weather;
        this.timeOfDay = timeOfDay;
        setName(name);
        System.out.printf("%s создана.\n", name);
        printInfo();
    }

    /**
     * Method prints current month and weather
     */
    private void printInfo() {
        System.out.printf("Месяц: %s, погода: %s\n", month.getTranslation(), weather.getTranslation());
    }


    // current weather
    private Weather weather = Weather.WARM;

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Weather getWeather() {
        return weather;
    }


    // current month
    private Month month = Month.MAY;

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }


    // current time of day
    private TimeOfDay timeOfDay = TimeOfDay.AFTERNOON;

    public TimeOfDay getTimeOfDay() {
        return timeOfDay;
    }

    public void setTimeOfDay(TimeOfDay timeOfDay) {
        this.timeOfDay = timeOfDay;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return getName().equals(((Vale) obj).getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
