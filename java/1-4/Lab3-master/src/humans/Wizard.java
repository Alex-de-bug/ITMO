package humans;

import abilities.AbleToBePardoned;
import abilities.AbleToBecomeJuice;
import abilities.AbleToMakeJuice;
import enums.Weather;
import valeObjects.Hat;
import valeObjects.Water;

public class Wizard extends Human implements AbleToMakeJuice {

    public static class WizardsHat extends Hat implements AbleToMakeJuice, AbleToBePardoned {

        private boolean isPardoned = false;
        private double probability = 0;

        @Override
        public void makeJuice(AbleToBecomeJuice object) {
            if (isPardoned) {
                object.becomeJuice(probability);
            } else {
                System.out.println("Не помилованная шляпа не может превратить воду в сок :(");
            }
        }

        /**
         * Method sets owner to a magic hat. The owner can be only a wizard
         */
        public void setOwner(Wizard owner) {
            super.setOwner(owner);
        }

        @Override
        public boolean checkPardon() {
            return isPardoned;
        }

        @Override
        public void makePardoned() {
            isPardoned = true;
            System.out.printf("Шляпа волшебника %s помилована\n", getOwner().getName());
        }

        @Override
        public void feelWeather(Weather weather) {
            switch (weather) {
                case EXTREMELY_HOT, EXTREMELY_COLD -> {
                    probability = 0.4;
                    System.out.println("Из-за невыносимой погоды магические свойства шляпы сильно уменьшились " +
                            "(вероятность превратить воду в сок = 0.4)");
                }
                case HOT, COLD -> {
                    probability = 0.6;
                    System.out.println("Из-за погоды магические свойства шляпы немного уменьшились " +
                            "(вероятность превратить воду в сок = 0.6)");
                }

            }
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            WizardsHat hat = (WizardsHat) obj;
            return hashCode() == hat.hashCode();
        }

        @Override
        public int hashCode() {
            return (toString() + (isPardoned ? '1' : '0')).hashCode();
        }

        @Override
        public String toString() {
            return "Шляпа волшебника";
        }
    }


    public WizardsHat hat;

    public Wizard(String name, WizardsHat hat) {
        super(name);
        this.hat = hat;
        hat.setOwner(this);
        System.out.printf("Волшебник %s с шляпой создан\n", name);
    }

    public Wizard(String name) {
        super(name);
        System.out.printf("Волшебник %s создан\n", name);
    }

    public void setHat(WizardsHat hat) {
        this.hat = hat;
        this.hat.setOwner(this);
    }

    public WizardsHat getHat() {
        return hat;
    }

    @Override
    public void makeJuice(AbleToBecomeJuice object) {
        hat.makeJuice(object);
    }

    @Override
    public String toString() {
        return String.format("Волшебник %s", getName());
    }
}
