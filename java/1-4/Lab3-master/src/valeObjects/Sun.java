package valeObjects;

import enums.Month;
import enums.Weather;
import locations.Vale;

import java.util.ArrayList;

public class Sun {

    /**
     * If the value of radiation Intensity is greater than 2000, the weather is extremely hot.
     * If it is between 1500 and 2000, the weather is hot.
     * If it is between 1000 and 1500, the weather is warm or cool (it depend on the current month).
     * If it is between 500 and 1000, the weather is cold.
     * If it is lower than 500, the weather is extremely cold
     */
    private int radiationIntensity;

    //  list of vales that are illuminated by the sun
    private ArrayList<Vale> vales = new ArrayList<>();

    public Sun(int radiationIntensity) {
        this.radiationIntensity = radiationIntensity;
        System.out.println("Солнце создано");
    }


    /**
     * Method adds vale to the list of vales that are illuminated by the sun
     */
    public void addVale(Vale vale) {
        vales.add(vale);
    }

    /**
     * Method removes vale from the list of vales that are illuminated by the sun
     */
    public void removeVale(Vale vale) {
        vales.remove(vale);
    }


    /**
     * Method changes the value of radiation intensity
     * @param shift if it is greater than 0, that value increases. In the other case it decreases or does not change
     */
    public void changeRadiationIntensity(int shift) {
        radiationIntensity += shift;
    }

    public int getRadiationIntensity() {
        return radiationIntensity;
    }


    /**
     * Method changes the weather in vales from the list of vales that are illuminated by the sun
     */
    public void shine() {

        for (Vale vale : vales) {

            switch (vale.getTimeOfDay()) {

                case MORNING, AFTERNOON -> {

                    if (radiationIntensity > 2000) {

                        System.out.printf("В долине %s солнце печет как никогда\n", vale);
                        vale.setWeather(Weather.EXTREMELY_HOT);

                    } else if (radiationIntensity > 1500) {

                        System.out.println("Ясный солнечный день. Жара в долине " + vale);
                        vale.setWeather(Weather.HOT);

                    } else if (radiationIntensity > 1000) {

                        Month month = vale.getMonth();
                        if (month != Month.MAY && month != Month.JUNE && month != Month.JULY && month != Month.AUGUST) {
                            vale.setWeather(Weather.COOL);
                            System.out.println("Прохладный ясный день в долине " + vale);
                        } else {
                            vale.setWeather(Weather.WARM);
                            System.out.println("Теплый облачный день в долине " + vale);
                        }

                    } else if (radiationIntensity > 500) {

                        System.out.println("Солнце еле светит. Мороз в долине " + vale);
                        vale.setWeather(Weather.COLD);

                    } else {

                        System.out.printf("В долине %s олод как на северном полюсе\n", vale);
                        vale.setWeather(Weather.EXTREMELY_COLD);

                    }
                }

                case EVENING -> {
                    System.out.println("Красивейший красный закат. Солнце заливает долину теплым светом");
                    Weather weather = vale.getWeather();
                    if (weather == Weather.EXTREMELY_HOT || weather == Weather.HOT) {
                        vale.setWeather(Weather.WARM);
                        System.out.println("Становится прохладнее");
                    }
                }

                case NIGHT -> {
                    System.out.println("Ночь. Солнце не светит");
                }
            }
        }
    }
}
