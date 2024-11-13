import java.lang.Math;


public class Laba1 {
    public static void main(String[] args) {

        //массив чётных чисел в диапазоне от 2 до 22 включительно
        int[] t = new int[22 / 2];
        for (int i = 22; i >= 2; i--) {
            if (i % 2 == 0) {
                t[11 - i / 2] = i;
            }
        }

        //массив на 11 рандомных чисел
        double[] x = new double[11];
        for (int i = 0; i < x.length; i++) {
            double a = getRandomNumber();
            x[i] = a;
        }

        //двумерный массив 11х11 с числами, которые генерируются в зависимости от условия
        double[][] k = new double[11][11];
        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k[i].length; j++) {
                if (t[i] == 12)
                    k[i][j] = getFormula(1, x[j]);

                else if ((t[i] == 2) | (t[i] == 4) | (t[i] == 14) | (t[i] == 16) | (t[i] == 22))
                    k[i][j] = getFormula(2, x[j]);

                else
                    k[i][j] = getFormula(3, x[j]);
            }

        }

        for (int i = 0; i < k.length; i++) {
            for (int j = 0; j < k.length; j++) {
                System.out.printf("%8.2f", k[i][j]);
            }
            System.out.println();

        }
    }

    public static double getRandomNumber() {
        return (Math.random() * (6.0 - (-15.0))) + (-15.0);
    }

    public static double getFormula(int type, double x) {
        if (type == 1) {
            return (Math.pow(Math.asin(1 / (Math.pow(Math.E, Math.abs(x)))), 1 / 3));
        }
        if (type == 2) {
            return (Math.pow((2 * Math.tan(Math.atan((x - 4.5) / (21)))), 2));
        }
        if (type == 3) {
            return (Math.tan(Math.pow(Math.E, x * (x - 1))) * Math.log(Math.pow(Math.pow(Math.tan(x), 2), 1 / 2d)) - 4.0);
        }
        return 0;
    }

}