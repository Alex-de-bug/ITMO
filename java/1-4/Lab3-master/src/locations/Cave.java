package locations;

import java.util.Arrays;
import java.util.Objects;

public class Cave extends Location {

    // caves sizes in 2 dimensions: x, y
    private int sizeX = 100, sizeY = 100;

    public Cave(int sizeX, int sizeY) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
    }


    public int getSizeX() {
        return sizeX;
    }

    public int getSizeY() {
        return sizeY;
    }


    /**
     * coordinate plane of caves surface.
     * a[i] == 0 => no one has dug a pit at this point.
     * a[i] != 0 => the value of a[i] is the depth of the pit at this point.
     */
    private int[][] caveSurface = new int[sizeX][sizeY];

    /**
     * Method sets value on caveSurface[x][y]
     */
    public void setCellValue(int x, int y, int value) {
        caveSurface[x][y] = value;
    }

    public int getCellValue(int x, int y) {
        return caveSurface[x][y];
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Cave cave = (Cave) object;
        return sizeX == cave.sizeX && sizeY == cave.sizeY && Arrays.deepEquals(caveSurface, cave.caveSurface);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(sizeX, sizeY);
        result = (3797191 * result + Arrays.deepHashCode(caveSurface)) % 17667049;
        return result;
    }
}
