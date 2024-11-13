package people;

public enum Illness {
    REVMATIT("Ревматит"),
    GRIPP("Грипп"),
    HEALTHY("Здоровый"),
    VETRIANKA("Ветрянка");
    private String name;
    Illness(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
