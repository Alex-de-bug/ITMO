package data;
/**
 * Класс, содержащий данные о координатах Дракона
 */

public class Coordinates {
    private Integer x; //Максимальное значение поля: 962, Поле не может быть null
    private float y; //Максимальное значение поля: 450

    public Coordinates(Integer x, float y) {
        this.x = x;
        this.y = y;
    }

    public void setX(Integer x) {
        if ((x != null)|(x != 962)) {
            this.x = x;
        }
    }
    public void setY(float y){
        if (y <= 450.0) {
            this.y = y;
        }
    }
    public Integer getX() {return x;}

    public float getY() {return y;}

    @Override
    public String toString() {
        return "x=" + x +
                ", y=" + y;
    }
}
