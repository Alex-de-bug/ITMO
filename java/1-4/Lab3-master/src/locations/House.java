package locations;

public class House extends Location {

    public House(String name) {
        setName(name);
        System.out.printf("%s создан\n", name);
    }

    public House() {
        System.out.printf("%s создан\n", getName());
    }

    public void becomeJungle() {
        System.out.printf("%s превратился в джунгли\n", getName());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        return getName().equals(((House) obj).getName());
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public String toString() {
        return getName();
    }
}
