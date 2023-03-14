package valeObjects;

import abilities.AbleToFeelWeather;
import enums.Weather;

public class Tree extends ValeObject {

    public Tree() {
        System.out.println("Дерево создано");
    }

    /**
     * Depending on the type of current weather void outputs the tree's status
     */
    @Override
    public void feelWeather(Weather weather) {
        switch (weather) {
            case EXTREMELY_HOT -> {
                System.out.println("Деревья изнемогают от страшной жары :(");
            }
            case EXTREMELY_COLD -> {
                System.out.println("Деревья изнемогают от страшного мороза :(");
            }
            default -> {
                System.out.println("Деревья легко переносят эту погоду");
            }
        }
    }

    // let all trees be equal to each other
    @Override
    public boolean equals(Object obj) {
        return getClass() == obj.getClass();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    @Override
    public String toString() {
        return "дерево";
    }
}
