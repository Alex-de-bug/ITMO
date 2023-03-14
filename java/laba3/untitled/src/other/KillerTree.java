package other;
import interfaces.*;
import people.*;

public class KillerTree extends Tree {
    public static class Apple implements Connect, View{
        public void fallToPerson(Person goal){
            System.out.println("Дерево трещит и яблоко начинает быстро падать на "+goal.getName()+" и убивает его на смерть!");
            goal.setFeelings(Feelings.NORMAL);
            goal.setAct(Action.NOTHING);
        }
        @Override
        public void join() {
            System.out.println("Яблоко выросло на дереве");
        }

        @Override
        public void disconnect() {
            System.out.println("Яблоко сгнило и пропало");
        }

        @Override
        public void request() {
            System.out.println("Вырастет ли яблоко, пока загадка");
        }

        @Override
        public void appear() {
            System.out.println("Яблоко переливается на солнышке");
        }

        @Override
        public void outlook() {
            System.out.println("Падающие яблоки делали все эти неизвестные следы, это самые жуткие убийцы в лесах. Их боялись даже буки");
        }
    }
    @Override
    public void join() {
        System.out.println("Дерево резко выросло");
    }

    @Override
    public void disconnect() {
        System.out.println("Дерево упало и пропало");
    }

    @Override
    public void request() {
        System.out.println("Есть вероятность, что дерево выростет");
    }

    @Override
    public void appear() {
        System.out.println("Дерево устрашающе шелестит листочками");
    }

    @Override
    public void outlook() {
        System.out.println("Это дерево похоже на убийцу");
    }
}
