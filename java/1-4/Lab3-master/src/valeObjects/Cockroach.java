package valeObjects;

import abilities.AbleToDig;
import enums.Weather;
import locations.Cave;

import java.util.Objects;

public class Cockroach extends Insect implements AbleToDig {

    public Cockroach(double sizeX, double sizeY, double sizeZ) {
        sizes.setSizeX(sizeX);
        sizes.setSizeY(sizeY);
        sizes.setSizeZ(sizeZ);
        System.out.println("Таракан создана");
    }

    public Cockroach() {
        System.out.println("Таракан со стандартными размерами создан");
    }


    public void setSizeX(double sizeX) {
        sizes.setSizeX(Math.max(0, Math.min(10, sizeX)));
    }

    public void setSizeY(double sizeY) {
        sizes.setSizeY(Math.max(0, Math.min(10, sizeY)));
    }

    public void setSizeZ(double sizeZ) {
        sizes.setSizeZ(Math.max(0, Math.min(10, sizeZ)));
    }


    /**
     * Cockroach is able to crawl in somebody's pit.
     * If there is no pits in the cave, cockroach digs a new one.
     * It is assumed that any cockroach will fit in the smallest pit (x = 1, y = 1, z = 1)
     */
    @Override
    public void dig(Cave cave) {
        boolean pitFound = false;

        outerCycle:
        for (int i = 0; i < cave.getSizeX(); i++) {
            for (int j = 0; j < cave.getSizeY(); j++) {
                if (cave.getCellValue(i, j) != 0) {
                    System.out.println(this + " заполз в готовую ямку");
                    pitFound = true;
                    break outerCycle;
                }
            }
        }

        if (!pitFound) {
            outerCycle:
            for (int i = 0; i < cave.getSizeX(); i++) {
                for (int j = 0; j < cave.getSizeY(); j++) {
                    if (cave.getCellValue(i, j) == 0) {
                        System.out.println(this + " вырыл ямку и заполз в нее");
                        break outerCycle;
                    }
                }
            }
        }
    }

    @Override
    public void feelWeather(Weather weather) {
        System.out.println("Таракану любая погода нипочем");
        makeSound();
    }

    @Override
    void makeSound() {
        System.out.println("sssss sssss sssss");
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cockroach cockroach = (Cockroach) obj;
        return hashCode() == cockroach.hashCode()
                && Objects.equals(sizes.sizeX(), cockroach.sizes.sizeX())
                && Objects.equals(sizes.sizeY(), cockroach.sizes.sizeY())
                && Objects.equals(sizes.sizeZ(), cockroach.sizes.sizeZ());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sizes.sizeX(), sizes.sizeY(), sizes.sizeZ());
    }

    @Override
    public String toString() {
        return "Таракан";
    }
}
