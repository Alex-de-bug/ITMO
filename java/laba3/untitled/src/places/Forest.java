package places;

public class Forest extends WorldPlaces{
    public Forest(String name) {
        super(name);

    }

    public void birdsSinging(int value){
        if(value==1)
            System.out.println("В месте "+getName()+" погода - "+getWeather()+", поэтому слышутся птичьи голоса. ");
        else
            System.out.println("В месте "+getName()+" погода - "+getWeather()+", поэтому птички не хотят петь. ");
    }
    public void chanceRain(){
        float ch = (float) (Math.random());
        System.out.print("Шанс смены погоды составляет: " + ch+". ");
        if(ch>0.5) {
            TypeWeather[] type = {TypeWeather.SUNNY, TypeWeather.CLOUDY, TypeWeather.RAINY, TypeWeather.WINDY, TypeWeather.SNOWY};
            int chance = (int) (Math.random() * ((type.length) - 1 - 0 + 1) + 0);
            if (type[chance].equals(getWeather())) {
                chanceRain();
            }
            else {
                setWeather(type[chance]);
            }
        }
    }


    @Override
    public void join(){
        appear();
        setWeather(TypeWeather.SUNNY);
        chanceRain();
        if(getWeather().equals(TypeWeather.SUNNY.getName())){
            birdsSinging(1);
        }
        else{
            birdsSinging(0);
        }
        System.out.print("Сейчас тут находится: ");
        for (int i = 0; i < getContains().size(); i++) {
            System.out.print(getContains().get(i).getName()+", который "+getContains().get(i).getAct().getName()+"; ");
        }
        System.out.println();
    }
    @Override
    public void appear(){
        System.out.print(getName()+" очень таинственно выглядит и вокруг стоят высокие деревья. ");
    }
    @Override
    public void outlook() {
        System.out.println(getFootPrints());
    }
    @Override
    public void disconnect() {}
    @Override
    public void request() {}
    @Override
    public String toString(){
        return ("МЕСТО:" + getName());
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
