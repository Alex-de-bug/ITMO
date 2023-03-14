package places;

public class NearSweetHomePit extends WorldPlaces{
    public NearSweetHomePit(String name) {
        super(name);

    }


    @Override
    public void join(){
        appear();
        setWeather(TypeWeather.SNOWY);
    }
    @Override
    public void appear(){
        System.out.print("Всё вокруг "+getName()+" чудесно выглядит. ");
    };
    @Override
    public void disconnect() {

    }
    @Override
    public void request() {

    }
    @Override
    public void outlook() {}
}
