package people;

public enum Type {
    ANIMAL("Животное"),
    PEOPLE("Человек"),
    SPIRIT("Дух");
    private String name;
    Type(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
