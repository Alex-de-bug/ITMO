package places;

//дедус попал сюда
public class Paradise extends WorldPlaces {
    public Paradise(String name) {
        super(name);
    }


    @Override
    public void setWeather(TypeWeather type) {
        System.out.print("тут всегда тепло и светит ярко солнце. ");
    }
    @Override
    public void appear() {
        System.out.print(getName() + " выглядит как мечта, ");
    }
    @Override
    public void join() {
        appear();
        setWeather(TypeWeather.SUNNY);
        System.out.print("Сейчас тут находится: ");
        for (int i = 0; i < getContains().size(); i++) {
            System.out.print(getContains().get(i).getName() + " ");
        }
        System.out.println();
    }
    @Override
    public void outlook() {
    }
    @Override
    public void disconnect() {
    }
    @Override
    public void request() {
    }
    @Override
    public String toString() {
        return ("место:" + getName());
    }
    @Override
    public int hashCode() {
        return super.hashCode() + getName().hashCode();
    }
    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.hashCode() == obj.hashCode();
    }
}
