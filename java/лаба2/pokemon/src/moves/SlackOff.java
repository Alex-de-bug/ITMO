package moves;
import ru.ifmo.se.pokemon.*;

public class SlackOff extends StatusMove{

    public SlackOff(){
        super(Type.NORMAL,0,0);
    }
    @Override
    protected void applySelfEffects(Pokemon p){
        p.setMod(Stat.HP, (int) (p.getHP()+(p.getStat(Stat.HP)/2d)));
    }

    @Override
    protected String describe() {
        return "использует SlackOff";
    }
}
