package locations;

import abilities.AbleToFeelWeather;
import enums.Weather;

public class Meadow extends Location implements AbleToFeelWeather {

    public Meadow() {
        System.out.println("Луг создан");
    }


    /**
     * TODO:
     * Method prints status, that depends on the weather, of the meadow and all objects on it
     */
    @Override
    public void feelWeather(Weather weather) {
        switch (weather) {
            case EXTREMELY_HOT -> {
                System.out.println("Луга жаждут");
            }
            case HOT -> {
                System.out.println("Солнце греет зеленую траву на лугах");
            }
            default -> {
                System.out.println("Погода не влияет на луга");
            }
        }

        for (AbleToFeelWeather object : getValeObjects()) {
            object.feelWeather(weather);
        }
    }


    // let all meadows be equal to each other
    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return "Луг".hashCode();
    }

    @Override
    public String toString() {
        return "Луг";
    }
}
