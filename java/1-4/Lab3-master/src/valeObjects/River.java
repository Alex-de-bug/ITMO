package valeObjects;

import abilities.AbleToBeLiquid;
import enums.Weather;

public class River extends ValeObject implements AbleToBeLiquid {

    public final Water water = new Water();

    public River(double waterLevel) {
        water.changeLevel(waterLevel);
        System.out.printf("Создана речка с уровнем воды %.2f(м)\n", waterLevel);
    }


    @Override
    public void changeLevel(double shift) {
        water.changeLevel(shift);

        if (isShallow()) {
            System.out.println("Речка обмелела :(");
        } else {
            System.out.println("Уровень воды в реке " + (shift > 0 ? "увеличился" : "стал ниже"));
        }
    }


    @Override
    public void feelWeather(Weather weather) {
        switch (weather) {
            case EXTREMELY_HOT -> {
                changeLevel(-water.getWaterLevel());
            }
            case HOT -> {
                changeLevel(-1);
            }
        }
        flow();
        water.feelWeather(weather);
    }

    private boolean isShallow() {
        return water.getWaterLevel() < 1;
    }

    /**
     * The pace of river flowing depends on the water level.
     */
    @Override
    public void flow() {
        if (isShallow()) {
            System.out.println(this + " еле струится");
        } else {
            System.out.println(this + " течет");
        }
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        River river = (River) obj;
        return water.equals(river.water) && toString().equals(river.toString());
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        if (isShallow()) {
            return "обмелевшая " + water.getWaterColour().getTranslation() + " река";
        } else {
            return water.getWaterColour().getTranslation() + " река";
        }
    }
}
