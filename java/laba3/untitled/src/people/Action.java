package people;

public enum Action {
    NOTHING("ничего не делает",false),
    WALK("гуляет",true),
    SEARCH_FOR_PREY("ищут добычу",false),
    KILL("убивают",true),
    CLEAN_SNOW("убирает снег",true);
    private String name;

    private boolean takeStrength;
    Action(String name, boolean takeStrength){
        this.name = name;
        this.takeStrength = takeStrength;
    }

    public boolean getTakeStrength() {
        return takeStrength;
    }

    public void setTakeStrength(boolean takeStrength) {
        this.takeStrength = takeStrength;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}