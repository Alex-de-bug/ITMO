package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;


public class Infernape extends Monferno{
    public Infernape(String name, int level){
        super(name, level);
        setStats(76, 104, 71, 104, 71, 108);
        addMove(new RockTomb());
    }
}
