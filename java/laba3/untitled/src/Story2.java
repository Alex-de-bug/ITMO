import other.KillerTree;
import people.*;
import places.*;

import java.util.ArrayList;

public class Story2 {
    public void go(){
        WorldPlaces rosha = new Forest("Роща");
        KillerTree.Apple apple = new KillerTree.Apple();
        WorldPlaces ray = new Paradise("Рай");
        WorldPlaces nearTheDomPita = new NearSweetHomePit("дорожка около дома Пита");
        Person Puka = new Person("Пука", Type.ANIMAL, rosha, Action.WALK);
        Person Buka = new Person("Бука", Type.ANIMAL,rosha, Action.WALK);
        Person pita = new Person("Пяточoк", Type.ANIMAL, nearTheDomPita, Action.CLEAN_SNOW);
        Person vini = new Person("Вини-Пух", Type.ANIMAL, nearTheDomPita, Action.WALK){
            @Override
            public void LookAround(){
                place.thereWereFootPrints();
                System.out.print(getName()+" наклонил голову вниз и увидел на дорожке cледы");
                for (int i = 0; i < place.getFootPrints().size(); i++) {
                    if(place.getFootPrints().get(i).contains("Бука")){
                        System.out.println(". Oни были ужасны и похожи на следы бук.");
                        break;
                    }
                }
            }
        };
        Person ded_inside = new Grandfather("Дед_Великий_Охотник", Type.SPIRIT, ray);
        Person Cristofer = new Person("Кристофер-Робин", Type.PEOPLE);

        nearTheDomPita.join();
        pita.LookAround();
        pita.changePlace(vini, rosha);
        rosha.join();
        Buka.walkWithTo(Puka, 26);
        vini.LookAround();
        Person threeNonamePerson = new Person("Три неизвестные персоны",Type.SPIRIT, rosha,Action.SEARCH_FOR_PREY){
            private Person death;
            @Override
            public void think(Person about, Boolean see) {
                about.setFeelings(Feelings.AFRAID);
                if(see){System.out.println(
                        getName()+" Увидели жертву и ждут момент для убийства "+about.getName());
                        about.setFeelings(Feelings.AFRAID);
                        setFeelings(Feelings.BAD);
                        setAct(Action.KILL);
                        apple.fallToPerson(about);
                        apple.outlook();
                }
                else{System.out.println(getName()+" Вглядываются в даль, ища жертву под именем "+about.getName());}
            }
            @Override
            public void LookAround(){
                System.out.println(getName()+" вглядыватся в следы и чувствуюn запах: ");
                ArrayList<Person> deathNote = new ArrayList<Person>();
                for (int i = 0; i < place.getContains().size(); i++) {
                    if(!this.equals(place.getContains().get(i))) {
                        deathNote.add(place.getContains().get(i));
                        System.out.print(place.getContains().get(i).getName()+" "+place.getContains().get(i).getBody().getName()+" "+place.getContains().get(i).getDieseases().getName());
                        System.out.println();
                    }
                }
                this.death = deathNote.get((int) (Math.random() * ((deathNote.size()) - 1 - 0 + 1) + 0));
                System.out.println("Выбрал рандомную жертву: "+death.getName());
                think(death,false);
            }
        };
        threeNonamePerson.LookAround();
        vini.connectInDialogAboutPerson(Dialog.SPEAKER, pita, Buka);
        pita.connectInDialogAboutPerson(Dialog.SPEAKER,vini,ded_inside);
        vini.think(Cristofer, true);
        pita.think(ded_inside,true);
        threeNonamePerson.think(pita,true);

        System.out.println("the end");
    }
}