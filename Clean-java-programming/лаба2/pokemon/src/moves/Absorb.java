package moves;
import ru.ifmo.se.pokemon.*;

public class Absorb extends SpecialMove{

    public Absorb(){
        super(Type.GRASS,20,100);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP, (int) (p.getHP()+((p.getStat(Stat.HP)-p.getHP())/2d)));
    }

    @Override
    protected String describe() {
        return "использует Absorb";
    }
}
