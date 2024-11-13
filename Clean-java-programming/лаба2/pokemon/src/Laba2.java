
import pokemons.*;
import ru.ifmo.se.pokemon.Battle;

public class Laba2 {
    public static void main(String[] args) {
        Battle b = new Battle();
        HoOh p1 = new HoOh("ВАНДЕРВАФЛЯ", 1);
        Nincada p2 = new Nincada("ЗМЕЙ ГОРЫНЫЧ", 1);
        Ninjask p3 = new Ninjask("ДОБРЫНЯ", 2);
        Chimchar p4 = new Chimchar("НИКИТИЧ", 1);
        Monferno p5 = new Monferno("МИКУЛА СИЛЬЯНИМОВИЧ", 2);
        Infernape p6 = new Infernape("АЛЁША", 3);
        b.addAlly(p1);
        b.addAlly(p2);
        b.addAlly(p3);
        b.addFoe(p4);
        b.addFoe(p5);
        b.addFoe(p6);
        b.go();
    }
}
