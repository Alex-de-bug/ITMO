package data;
/**
 * Класс, описывающий окраску Дракона
 */

public enum Color {
    GREEN("green"),
    BLUE("blue"),
    YELLOW("yellow"),
    ORANGE("orange"),
    WHITE("white");
    String color;
    Color(String color){
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
