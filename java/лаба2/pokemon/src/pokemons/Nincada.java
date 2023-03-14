package pokemons;
import moves.*;
import ru.ifmo.se.pokemon.*;


public class Nincada extends Pokemon{
    public Nincada(String name, int level){
        super(name, level);
        setStats(31,45,90,30,30,40);
        setType(Type.BUG,Type.GROUND);
        setMove(new SandAttack(), new LeechLife(), new Absorb());
    }
}
