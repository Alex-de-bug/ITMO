package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;


public class HoOh extends Pokemon{
    public HoOh(String name, int level){
        super(name, level);
        setStats(106,130,90,110,154,90);
        setType(Type.FIRE,Type.FLYING);
        setMove(new DreamEater(), new Overheat(), new Thunderbolt(), new WillOWisp());
    }
}
