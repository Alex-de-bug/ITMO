package humans;

public abstract class Human {

    private String name = "Абоба";

    public Human(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Human human = (Human) obj;
        return name.equals(human.getName());
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
