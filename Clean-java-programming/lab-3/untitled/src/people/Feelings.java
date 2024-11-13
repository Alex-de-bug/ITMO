package people;

public enum Feelings {
    BAD("злится"),
    JOY("веселится"),
    AFRAID("встревожен"),
    NORMAL("не беспкоится");
    private String name;
    Feelings(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
