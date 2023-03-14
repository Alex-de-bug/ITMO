package net.alephdev.models;

import java.util.Scanner;

public class Coordinates {
    private float x; 
    private int y; 

    public Coordinates(float x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public float getX() {
        return x;
    }

    public void setX(Scanner scan) {
        float x;
        try {
            x = scan.nextFloat();
        } catch(Exception ex) {
            scan.nextLine();
            throw new IllegalArgumentException("Значение X должно быть числом");
        }
        if(x > 182)
            throw new IllegalArgumentException("Значение X не может быть больше 182");
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(Scanner scan) {
        int y;
        try {
            y = scan.nextInt();
        } catch(Exception ex) {
            scan.nextLine();
            throw new IllegalArgumentException("Значение Y должно быть числом");
        }
        if(y > 924)
            throw new IllegalArgumentException("Значение Y не может быть больше 924");
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordinates{" + "x=" + x + ", y=" + y + '}';
    }
    
    
}
