package people;

import Exceptions.*;
import interfaces.*;
import places.WorldPlaces;
import java.util.ArrayList;
import java.util.Objects;

public class Person implements Connect, View {
    private String name;
    private Type body;
    private Feelings feelings = Feelings.NORMAL;
    private Dialog dialoger = Dialog.LISTENER;
    private Illness dieseases = Illness.HEALTHY;
    private Action act = Action.NOTHING;
    protected WorldPlaces place;
    private ArrayList<String> histories = new ArrayList<String>();

    public Person(String name, Type body) {
        this.name = name;
        this.body = body;
        this.histories.add(" своя история жизни");
    }
    public Person(String name, Type body, WorldPlaces place){
        this.name = name;
        this.body = body;
        this.histories.add(" своя история жизни");
        this.place = place;
        try {
            if (place == null) {
                throw new BadPlaceException();
            }
        }catch (BadPlaceException e){
            throw e;
        }

        place.addContains(this);
    }
    public Person(String name, Type body, WorldPlaces place, Action act){
        this.name = name;
        this.body = body;
        this.act = act;
        this.histories.add(" своя история жизни");
        this.place = place;
        try {
            if (place == null) {
                throw new BadPlaceException();
            }
        }catch (BadPlaceException e){
            throw e;
        }
        place.addContains(this);
    }


    private class Organism{
        public void heal() {
            if(!dieseases.equals(Illness.HEALTHY)) System.out.println(getName() + " когда то лечился от болезни " + getDieseases().getName() + " так как заболел ей после охоты, но в итоге умер.");
            else System.out.println(getName() + " полностью здоров.");
        }
    }

    public void changePlace(Person friend, WorldPlaces newPlace){
        System.out.print(friend.getName()+" и "+getName()+" дошли до "+newPlace.getName()+". ");
        this.place = newPlace;
        friend.place= newPlace;
        startWalkTo(friend, newPlace);
    }
    public void LookAround() {
        System.out.println(getName() + " оглянулся вокруг и увидел: ");
        for (int i = 0; i < place.getContains().size(); i++) {
            if (place.getContains().get(i).equals(this)) {
            } else {
                System.out.println(place.getContains().get(i).getName()+", который "+place.getContains().get(i).getAct().getName());
            }
        }
        for (int i = 0; i < place.getContains().size(); i++) {
            if (place.getContains().get(i).equals(this)) {
            } else {
                startWalkTo(place.getContains().get(i),place);

                break;
            }
        }
    }
    public void startWalkTo(Person friend, WorldPlaces place) {
        place.addFootPrints(" немного следов " + getName());
        place.addFootPrints(" немного следов " + friend.getName());
        place.addContains(this);
        place.addContains(friend);
        System.out.println(getName() + " c " + friend.getName()+ " вместе начинают гулять по " + place.getName());
        this.act=Action.WALK;
    }
    public void walkWithTo(Person friend, int time){
        try {
            try {
                if (place == null) throw new BadPlaceException();
            }catch (BadPlaceException e){
                System.out.println(e.toString());
                throw e;
            }

            if (time == 1) {
                place.addFootPrints("немного следов " + getName());
                place.addFootPrints("немного следов " + friend.getName());
                System.out.println(getName() + " идёт по " + place.getName() + " с " + friend.getName() + " приблизительно " + time + " ч.");
            } else if((time > 1) && (time < 5)) {
                place.addFootPrints("немало следов " + getName());
                place.addFootPrints("немало следов " + friend.getName());
                System.out.println(getName() + " идёт по " + place.getName() + " с " + friend.getName() + " приблизительно " + time + " ч.");
            } else if ((time > 4) && (time < 25)) {
                place.addFootPrints("очень много следов " + getName());
                place.addFootPrints("очень много следов " + friend.getName());
                System.out.println(getName() + " идёт по " + place.getName() + " с " + friend.getName() + " приблизительно " + time + " ч. Тут "+place.getFootPrints().toString());
            }
            else{
                throw new MegaLongTimeException();
            }
        }
        catch (MegaLongTimeException e) {
            System.out.println(e.toString());
            System.err.println("ПЕРЕПРОВЕРЬТЕ ПРАВИЛЬНОСТЬ ВВЕДЁННОГО ВРЕМЕНИ ПРОГУЛКИ У "+this.name);
        }
        this.act=Action.WALK;
    }
    public void think(Person about, Boolean see){
        class Thoughts{
            public void thinks(){
                System.out.println(getName() + " думает как же может выглядеть " + about.getName()+", и вдруг они как раз охотятся на него.");
            }
            public void remembers(){
                System.out.println(getName() + " вспоминает " + about.getName()+" и как бы он поступил на его месте");
            }
            public void nosyalgates(){
                System.out.println(getName() + " жалеет, что " + about.getName()+" сейчас не с ним, а в неизвестном месте.");
            }
        }
        if(see) {
            if(about.getDieseases()==Illness.HEALTHY){
                new Thoughts().remembers();
            }
            else{new Thoughts().nosyalgates();}
        }
        else{new Thoughts().thinks();}
        about.setFeelings(Feelings.JOY);
    }
    public void connectInDialogAboutPerson(Dialog type, Person friend, Person about) {
        this.dialoger = type;
        System.out.print(getName() + " заговорил с " + friend.getName());
        class Topic{
            void aboutPeople(Person about){
                if ((about.getDieseases().equals(Illness.HEALTHY))) {
                    System.out.println(" о cледах " + about.getName()+", которые были в том месте, где они гуляют");
                    addHistory(" история об следах " + about.getName());
                    friend.addHistory(" история об следах " + about.getName() + ", которую рассказал " + getName());
                } else {
                    System.out.println(" о болезне " + about.getName() + ", которая называется " + about.getDieseases().getName());
                    addHistory(" история об болезне " + about.getName());
                    friend.addHistory(" история об болезне " + about.getName() + ", которую рассказал " + getName());
                    about.DescribeTheOrganism();
                }
            }
        }
        new Topic().aboutPeople(about);
        System.out.print("Список историй " + friend.getName() + " теперь состоит из:");
        for (int i = 0; i < friend.getHistories().size(); i++) {
            if (i != friend.getHistories().size() - 1) {
                System.out.print(friend.getHistories().get(i) + ";");
            } else {
                System.out.println(friend.getHistories().get(i) + ".");
            }
        }
        friend.think(about,false);

    }
    public void DescribeTheOrganism() {
        Organism organism = new Organism();
        organism.heal();
    }



    protected void addHistory(String elementToAdd) {
        histories.add(elementToAdd);
    }
    public void setFeelings(Feelings feelings) {
        this.feelings = feelings;
    }
    public Feelings getFeelings() {
        return feelings;
    }
    public String getName() {
        return name;
    }
    public Type getBody() {
        return body;
    }
    public Dialog getDialoger() {
        return dialoger;
    }
    public Illness getDieseases() {
        return dieseases;
    }
    public void setDieseases(Illness dieseases) {
        this.dieseases = dieseases;
    }
    public ArrayList<String> getHistories() {
        return histories;
    }
    public Action getAct() {
        return act;
    }
    public void setAct(Action act) {
        this.act = act;
    }
    public WorldPlaces getPlace() {
        return place;
    }
    public void setPlace(WorldPlaces place) {
        this.place = place;
    }



    @Override
    public void appear() {
        System.out.println("Тип " + getName() + " - " + getBody().getName());
    }
    @Override
    public void join() {
    }
    @Override
    public void disconnect() {
    }
    @Override
    public void request() {
    }
    @Override
    public void outlook() {
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && body == person.body && dialoger == person.dialoger && dieseases == person.dieseases;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, body, dialoger, dieseases);
    }
    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", body=" + body +
                ", dialoger=" + dialoger +
                ", dieseases=" + dieseases +
                '}';
    }
}