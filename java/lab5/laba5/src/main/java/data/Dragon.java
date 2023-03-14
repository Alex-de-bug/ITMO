package data;

import java.time.LocalDateTime;
import java.util.Comparator;

/**
 * Класс описывающий дракона
 * */

public class Dragon implements Comparable<Dragon> {
    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.LocalDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private int age; //Значение поля должно быть больше 0
    private double wingspan; //Значение поля должно быть больше 0
    private Color color; //Поле не может быть null
    private DragonType type; //Поле может быть null
    private DragonHead head; //Поле может быть null

    public Dragon(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int age, double wingspan, Color color, DragonType type, DragonHead head) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.age = age;
        this.wingspan = wingspan;
        this.color = color;
        this.type = type;
        this.head = head;
    }

    public Dragon() {
    }

    /**
     * Получение строкового представления объекта
     * @return String
     */
    @Override
    public String toString() {
        return  "\n****************************\n"+
                "id: " + id +
                "\nname: " + name +
                "\ncoordinates: " + coordinates +
                "\ncreationDate: " + creationDate +
                "\nage: " + age +
                "\nwingspan: " + wingspan +
                "\ncolor: " + color +
                "\ntype: " + type +
                "\nhead: " + head +
                "\n****************************\n";
    }

//    @Override
//    public String toString() {
//        return "Dragon{" +
//                "\n id=" + id +
//                ", name='" + name + '\'' +
//                ", coordinates=" + coordinates +
//                ", creationDate=" + creationDate +
//                ", age=" + age +
//                ", wingspan=" + wingspan +
//                ", color=" + color +
//                ", type=" + type +
//                ", head=" + head +
//                '}';
//    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getWingspan() {
        return wingspan;
    }

    public void setWingspan(double wingspan) {
        this.wingspan = wingspan;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public DragonType getType() {
        return type;
    }

    public void setType(DragonType type) {
        this.type = type;
    }

    public DragonHead getHead() {
        return head;
    }

    public void setHead(DragonHead head) {
        this.head = head;
    }

    @Override
    public int compareTo(Dragon o) {
        return this.age - o.getAge();
    }

}
