package people;

public enum Dialog {
    SPEAKER("говорит", "теряет силы говорения"),
    LISTENER("слушает", "восполняет силы языка"),
    VOID("молчит", "развивает социофобию");
    private String name;
    private String description;
    Dialog(String name, String description){
        this.name = name;
        this.description= description;
    }

    public String getName() {
        return name;
    }
    public String getDescription(){return description;}
    public void setDescription(String name) {
        this.description = description;
    }
    public void setName(String name) {
        this.name = name;
    }
}
