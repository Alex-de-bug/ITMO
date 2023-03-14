package locations;

import enums.Type;
import valeObjects.ValeObject;

import java.util.LinkedList;

public abstract class Location {

    private final Type type = Type.LOCATION;

    public Type getType() {
        return type;
    }


    // list contains all locations that are added on main location
    private LinkedList<Location> locations = new LinkedList<>();

    public void addLocation(Location location) {
        locations.add(location);
    }

    /**
     * removes from the list all objects that have the same class as the argument
     */
    public void removeLocation(Location object) {
        locations.removeIf(location -> location.getClass() == object.getClass());
    }

    public LinkedList<Location> getLocations() {
        return locations;
    }


    // list contains all objects that are added on vale
    private LinkedList<ValeObject> valeObjects = new LinkedList<>();

    public void addValeObject(ValeObject object) {
        valeObjects.add(object);
    }

    /**
     * removes from the list all objects that have the same class as the argument
     */
    public void removeValeObject(ValeObject object) {
        valeObjects.removeIf(valeObject -> valeObject.getClass() == object.getClass());
    }

    public LinkedList<ValeObject> getValeObjects() {
        return valeObjects;
    }


    private String name = "локация";

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
