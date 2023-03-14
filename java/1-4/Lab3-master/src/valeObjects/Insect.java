package valeObjects;


import abilities.AbleToFeelWeather;
import dataStructures.Sizes;

public abstract class Insect extends ValeObject implements AbleToFeelWeather {

    private double maxEnergyPointsValue = 100;

    public void setMaxEnergyPointsValue(double value) {
        maxEnergyPointsValue = value;
    }

    public double getMaxEnergyPointsValue() {
        return maxEnergyPointsValue;
    }

    // the value of fly's energy (from 0 to maxEnergyPointsValue)
    private double energyPoints = 50;

    public double getEnergyPoints() {
        return energyPoints;
    }

    public boolean checkEnergyPoints() {
        return energyPoints > maxEnergyPointsValue * 0.7;
    }

    public void setEnergyPoints(double energyPoints) {
        this.energyPoints = Math.min(Math.max(energyPoints, 0), getMaxEnergyPointsValue());
    }

    // sizes of fly in 3 dimensions: x, y, z
    public Sizes<Double> sizes = new Sizes<>(1.0, 1.0, 1.0);

    abstract void makeSound();
}
