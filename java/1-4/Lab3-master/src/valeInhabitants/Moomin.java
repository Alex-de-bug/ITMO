package valeInhabitants;

import abilities.AbleToDig;
import abilities.AbleToSleep;
import dataStructures.Sizes;
import enums.Gender;
import exceptions.NotEnoughPlaceException;
import exceptions.FreePlaceNotFoundException;
import locations.Cave;

import java.util.Objects;

public class Moomin implements AbleToSleep, AbleToDig {

    private final String name;
    private final Gender gender;

    public Moomin(String name, Gender gender) {
        this.name = name;
        this.gender = gender;
    }

    // sizes of mummiFamily friend in 3 dimensions: x, y, z
    public Sizes<Integer> sizes = new Sizes<>(10, 1, 1);

    public Moomin(String name, Gender gender, int sizeX, int sizeY, int sizeZ) {
        this.name = name;
        this.gender = gender;
        sizes.setSizeX(sizeX);
        sizes.setSizeY(sizeY);
        sizes.setSizeZ(sizeZ);
    }


    /**
     * Moomin digs a pit on the cave on the first at the first free place we met
     * Method throws FreePlaceNotFoundException if there is no free place to dig
     * Method throws NotEnoughPlaceException if moomin is bigger than cave
     */
    @Override
    public void dig(Cave cave) throws FreePlaceNotFoundException {

        if (sizes.sizeX() > cave.getSizeX() || sizes.sizeY() > cave.getSizeY()) {
            throw new NotEnoughPlaceException(name + " имеет слишком большие размеры чтобы рыть себе ямку в этом гроте");
        }

        boolean placeFound = false;

        outerCycle:
        for (int i = 0; i < cave.getSizeX() - sizes.sizeX(); i++) {
            for (int j = 0; j < cave.getSizeY() - sizes.sizeY(); j++) {
                boolean isFree = true;

                for (int k = i; k < sizes.sizeX() + i; k++) {
                    for (int q = j; q < sizes.sizeY() + j; q++) {
                        isFree = isFree && cave.getCellValue(k, q) == 0;
                    }
                }

                if (isFree) {
                    for (int k = i; k < sizes.sizeX() + i; k++) {
                        for (int q = j; q < sizes.sizeY() + j; q++) {
                            cave.setCellValue(k, q, sizes.sizeZ());
                        }
                    }
                    placeFound = true;
                    System.out.println(getName() + " вырыл себе ямку в форме свего тела в гроте");
                    break outerCycle;
                }
            }
        }

        if (!placeFound) {
            throw new FreePlaceNotFoundException();
        }
    }

    public void playSong() {
        System.out.printf(getName() + " играет %s песню\n", Math.random() < 0.5 ? "грустную" : "веселую");
    }


    private boolean sleepingStatus = false;
    private boolean lyingStatus = false;

    public void setLyingStatus(boolean value) {
        lyingStatus = value;
    }

    @Override
    public void sleep() {
        sleepingStatus = true;
        lyingStatus = true;
        System.out.println(getName() + (gender == Gender.MAN ? " уснул" : " уснула"));
    }

    public void lie() {
        lyingStatus = true;
        System.out.println(name + "лежит");
    }

    @Override
    public void getUp() {
        sleepingStatus = false;
        lyingStatus = false;
        System.out.println(getName() + (gender == Gender.MAN ? " проснулся" : " проснулась"));
    }

    @Override
    public boolean getSleepingStatus() {
        return sleepingStatus;
    }

    public String getName() {
        return name;
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Moomin moomin = (Moomin) object;
        return Objects.equals(name, moomin.name) && gender == moomin.gender && sizes.equals(moomin.sizes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, sizes, sleepingStatus);
    }

    @Override
    public String toString() {
        return getName();
    }
}

