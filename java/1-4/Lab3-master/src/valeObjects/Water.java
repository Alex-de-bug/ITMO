package valeObjects;

import abilities.AbleToBeLiquid;
import abilities.AbleToBecomeJuice;
import enums.WaterColour;
import enums.Weather;

import java.util.Objects;

public class Water extends ValeObject implements AbleToBeLiquid, AbleToBecomeJuice {

    private boolean ableToBecomeJuice = false;

    private double waterLevel = 0;

    /**
     * Method changes level of water.
     * If shift is > 0, then water level increases.
     * In the other case it decreases, but can't be < 0;
     */
    @Override
    public void changeLevel(double shift) {
        if (waterLevel + shift > 0) {
            waterLevel += shift;
        } else {
            waterLevel = 0;
        }
    }

    public double getWaterLevel() {
        return waterLevel;
    }


    private WaterColour waterColour = WaterColour.TRANSPARENT;

    public void setWaterColour(WaterColour waterColour) {
        this.waterColour = waterColour;
    }

    public WaterColour getWaterColour() {
        return waterColour;
    }


    private double flowingSpeed = 0;

    public double getFlowingSpeed() {
        return flowingSpeed;
    }

    /**
     * Sets flowing speed only if it is positive
     */
    public void setFlowingSpeed(double flowingSpeed) {
        if (flowingSpeed > 0) {
            this.flowingSpeed = flowingSpeed;
            flow();
        }
    }

    /**
     * River can't flow if flowingSpeed is equals to 0.
     * If method flow is called and flowingSpeed == 0, flowingSpeed will become 1 meter per second
     */
    @Override
    public void flow() {
        if (flowingSpeed == 0) {
            flowingSpeed = 1;
        }
        System.out.printf("Вода течет со скоростью %.2f м/с", flowingSpeed);
    }

    /**
     * If weather is auspicious, water is able to become juice.
     * In the other case, you need to change weather before calling this method
     */
    @Override
    public void becomeJuice(double probability) {
        if (!ableToBecomeJuice) {
            System.out.println("От невыносимой жары вода не может превратиться в сок :(");
        } else if (Math.random() < probability) {
            waterColour = WaterColour.ORANGE;
            System.out.println("Магия! Вода превратилась в апельсиновый сок!");
        } else {
            System.out.println("Шляпа не смогла превратить воду в сок");
        }
    }


    public void feelWeather(Weather weather) {
        // if the weather is extremely hot or extremely cold, water is not able to become juice
        ableToBecomeJuice = !(weather.equals(Weather.EXTREMELY_HOT) || weather.equals(Weather.EXTREMELY_COLD));
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Water water = (Water) obj;
        return Double.compare(water.waterLevel, waterLevel) == 0 && waterColour == water.waterColour;
    }

    @Override
    public int hashCode() {
        return Objects.hash(waterLevel, waterColour);
    }

    @Override
    public String toString() {
        return "Вода";
    }
}
