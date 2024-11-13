package places;

import interfaces.*;
import people.Feelings;
import people.Person;

import java.util.ArrayList;

//общая характеристика мест
public abstract class WorldPlaces implements Connect, View {
    private String name;
    private TypeWeather weather;
    private ArrayList<String> FootPrints = new ArrayList<String>();
    public ArrayList<Person> contains = new ArrayList<Person>();

    public WorldPlaces(String name){this.name=name;}

    public void thereWereFootPrints(){
        for (int i = 0; i < getContains().size(); i++) {
            getContains().get(i).setFeelings(Feelings.AFRAID);
        }
        System.out.println("В месте "+getName()+" появились неизветсные следы и все стали "+Feelings.AFRAID.getName()+"ы");
    }



    public String getName(){return this.name;}
    public String getWeather(){return weather.getName();}
    public void setWeather(TypeWeather type){
        this.weather=type;
        System.out.println("В месте "+getName()+" "+type.getName()+".");
    }
    public void addFootPrints(String elementToAdd) {
        FootPrints.add(elementToAdd);
    }
    public void addContains(Person elementToAdd) {
        contains.add(elementToAdd);
    }
    public ArrayList<String> getFootPrints() {
        return FootPrints;
    }
    public ArrayList<Person> getContains() {
        return contains;
    }


    @Override
    public void join(){
        System.out.println("Место "+getName()+" подключилось к сценке");
        appear();
        setWeather(TypeWeather.SUNNY);
    }
    @Override
    public void appear(){
        System.out.println(getName()+" очень таинственно выглядит");
    }
}
