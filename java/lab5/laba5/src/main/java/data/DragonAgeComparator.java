package data;

import java.util.Comparator;

public class DragonAgeComparator implements Comparator<Dragon> {

    @Override
    public int compare(Dragon o1, Dragon o2) {
        return o1.compareTo(o2);
    }
}
