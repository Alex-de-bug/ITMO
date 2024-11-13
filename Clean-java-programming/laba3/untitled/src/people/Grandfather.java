package people;
import places.*;

public class Grandfather extends Person {

    public Grandfather(String name, Type body, WorldPlaces place){
        super(name, body, place);
        setDieseases(Illness.REVMATIT);
        addHistory(" своя история жизни и смерти из-за болезни");
    }

}
