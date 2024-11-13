package moves;

import ru.ifmo.se.pokemon.*;

public class DreamEater extends SpecialMove {

    public DreamEater(){
        super(Type.PSYCHIC,100,100);
    }

    @Override
    protected boolean checkAccuracy(Pokemon att, Pokemon def){
        Status PdefStat = def.getCondition();
        if(PdefStat.equals(Status.SLEEP)){
            return (accuracy * att.getStat(Stat.ACCURACY) / def.getStat(Stat.EVASION)) > Math.random();
        }
        return false;
    }
    @Override
    protected void applySelfEffects(Pokemon p) {
        p.setMod(Stat.HP, (int) (p.getHP() + ((p.getStat(Stat.ATTACK)) / 2d)));
    }
    @Override
    protected String describe() {
        return "использует Dream Eater";
    }
}
