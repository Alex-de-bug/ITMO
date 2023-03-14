import auxiliaryClasses.ConsoleColors;
import dataStructures.Graph;
import enums.*;
import exceptions.graphExceptions.AlreadyContainsVertexException;
import exceptions.FreePlaceNotFoundException;
import humans.*;
import locations.*;
import valeInhabitants.Moomin;
import valeObjects.*;

import java.util.ArrayList;
import java.util.Map;


public class StoryTeller {
    /**
     * Method that creates all objects from the task and prints their status.
     */
    public void go() {
        Vale moominVale = new Vale("Мумми-Дол");
        Sun sun = new Sun(2600);
        moominVale.setMonth(Month.JUNE);
        moominVale.setTimeOfDay(TimeOfDay.AFTERNOON);

        System.out.println();

        sun.addVale(moominVale);
        sun.shine();

        System.out.println();

        Meadow meadow = new Meadow();
        moominVale.addLocation(meadow);

        Cave cave = new Cave(100, 100);
        meadow.addLocation(cave);

        House moominHouse = new House("Мумми-Дом");
        moominVale.addLocation(moominHouse);

        Tree tree = new Tree();
        meadow.addValeObject(tree);

        River river = new River(2);
        meadow.addValeObject(river);
        river.water.setWaterColour(WaterColour.GREY);

        Fly fly = new Fly(1, 2, 1);
        meadow.addValeObject(fly);

        Cockroach cockroach = new Cockroach();
        meadow.addValeObject(cockroach);

        Bird bird = new Bird();

        Wizard.WizardsHat hat = new Wizard.WizardsHat();
        meadow.addValeObject(hat);
        Wizard wizard = new Wizard("Гарри Поттер", hat);
        System.out.println();

        meadow.feelWeather(moominVale.getWeather());

        System.out.println();
        System.out.println("на лугу: " + meadow.getValeObjects());
        System.out.println(moominVale + ": " + moominVale.getLocations() + "\n");

        hat.makeJuice(river.water);
        hat.makePardoned();
        hat.makeJuice(river.water);
        System.out.println();

        moominHouse.becomeJungle();
        System.out.println();

        cockroach.dig(cave);
        System.out.println();

        Moomin snufkin = new Moomin("Снусмумрик", Gender.MAN, 4, 1, 1);

        Moomin snork = new Moomin("фрекен Снорк", Gender.WOMAN, 3, 1, 1) {
            @Override
            public void lie() {
                System.out.println(getName() + " лежит склонив голову");
                setLyingStatus(true);
            }
        };

        Moomin moomin = new Moomin("Мумми-Тролль", Gender.MAN, 3, 1, 2);
        Moomin fillyjonk = new Moomin("Филифьонка", Gender.WOMAN, 5, 1, 1);
        Moomin sniff = new Moomin("Снифф", Gender.MAN, 2, 1, 1);

        Graph<Moomin> friendshipsGraph = new Graph<>() {
            @Override
            public String toString() {
                StringBuilder stringGraph = new StringBuilder();

                for (Map.Entry<Moomin, ArrayList<Moomin>> entry : getGraph().entrySet()) {
                    stringGraph.append(entry.getKey()).append(" дружит с: \n");
                    entry.getValue().forEach(value -> stringGraph.append(value).append("; "));
                    stringGraph.append('\n');
                }

                return stringGraph.toString();
            }
        };

        try {
            friendshipsGraph.addVertex(snufkin);
            friendshipsGraph.addVertex(snork);
            friendshipsGraph.addVertex(moomin);
            friendshipsGraph.addVertex(fillyjonk);
            friendshipsGraph.addVertex(sniff);
            // adding this vertex to graph causes throwing AlreadyContainsVertexException
            friendshipsGraph.addVertex(sniff);
        } catch (AlreadyContainsVertexException e) {
            System.out.println(ConsoleColors.RED + "Нельзя добавить Мумми-тролля, который уже с кем-то дружит!" +
                    ConsoleColors.RESET);
        }

        friendshipsGraph.removeEdge(sniff, snork);
        System.out.printf("%s и %s поссорились\n", sniff.getName(), snork.getName());
        friendshipsGraph.removeEdge(snufkin, fillyjonk);
        System.out.printf("%s и %s поссорились\n", snufkin.getName(), fillyjonk.getName());
        System.out.println();

        System.out.println("Отношения мумми-троллей:");
        System.out.println(friendshipsGraph);

        try {
            snufkin.dig(cave);
            snork.dig(cave);
            moomin.dig(cave);
            fillyjonk.dig(cave);
            sniff.dig(cave);
        } catch (FreePlaceNotFoundException e) {
            System.out.println(ConsoleColors.RED + "Нет столько места в гроте, чтобы уместить всех" + ConsoleColors.RESET);
        }
        System.out.println();

        snufkin.playSong();
        snork.lie();
    }
}
