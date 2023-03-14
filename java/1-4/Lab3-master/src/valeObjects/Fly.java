package valeObjects;


import enums.Weather;

import java.util.Objects;

public class Fly extends Insect {

    public Fly(double sizeX, double sizeY, double sizeZ) {
        sizes.setSizeX(sizeX);
        sizes.setSizeY(sizeY);
        sizes.setSizeZ(sizeZ);
        System.out.println("Муха создана");
    }

    public Fly() {
        System.out.println("Муха со стандартными размерами создана");
    }


    public void setSizeX(double sizeX) {
        sizes.setSizeX(Math.max(0, Math.min(5, sizeX)));
    }

    public void setSizeY(double sizeY) {
        sizes.setSizeY(Math.max(0, Math.min(5, sizeY)));
    }

    public void setSizeZ(double sizeZ) {
        sizes.setSizeZ(Math.max(0, Math.min(5, sizeZ)));
    }


    // the value of buzzing volume (from 1 to 5)
    private int buzzingVolume = 3;

    public int getBuzzingVolume() {
        return buzzingVolume;
    }

    public void setBuzzingVolume(int buzzingVolume) {
        this.buzzingVolume = Math.min(Math.max(buzzingVolume, 0), 5);
    }

    /**
     * Count of "Bzz" depends on value of the buzzingVolume
     */
    @Override
    public void makeSound() {
        if (checkEnergyPoints()) {
            for (int i = 0; i < buzzingVolume; i++) {
                System.out.print("Bzz ");
            }
            System.out.println();
        } else {
            System.out.println("У мухи нет сил жужжать :(");
        }
    }


    /**
     * Fly flies if it has enough energy.
     */
    public void fly() {
        if (checkEnergyPoints()) {
            makeSound();
            System.out.println("Муха летает! Bzz Bzz Bzz");
        } else {
            System.out.println("У мухи нет сил летать :(");
        }
    }


    @Override
    public void feelWeather(Weather weather) {
        switch (weather) {
            case EXTREMELY_HOT, EXTREMELY_COLD -> {
                setMaxEnergyPointsValue(45);
                setEnergyPoints(10);
            }
            case HOT, COLD -> {
                setMaxEnergyPointsValue(70);
                setEnergyPoints(60);
            }
        }

        fly();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fly fly = (Fly) obj;
        return hashCode() == fly.hashCode() && Objects.equals(sizes.sizeX(), fly.sizes.sizeX())
        && Objects.equals(sizes.sizeY(), fly.sizes.sizeY()) && Objects.equals(sizes.sizeZ(), fly.sizes.sizeZ());
    }

    @Override
    public int hashCode() {
        return ((int) (sizes.sizeX() * 17667049) % 3797191 % 8923) *
                ((int) (sizes.sizeY() * 17667049) % 3797191 % 8923) *
                ((int) (sizes.sizeZ() * 17667049) % 3797191 % 8923);
    }

    @Override
    public String toString() {
        return "Муха";
    }
}
