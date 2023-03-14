package moves;
import ru.ifmo.se.pokemon.*;

public class LeechLife extends PhysicalMove{

    public LeechLife(){
        super(Type.BUG,80,100);
    }

    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP, (int) (p.getHP() + ((p.getStat(Stat.ATTACK)) / 2d)));
    }

    @Override
    protected String describe() {
        return "использует Leech Life";
    }
}
