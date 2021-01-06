import java.util.Arrays;

public class Grid {
    private final char FOG = '~';

    private char[][] gridArr = new char[10][10];

    public Grid() {
        for (int row = 0; row < 10; row++) {
            Arrays.fill(gridArr[row], FOG);
        }
    }

    public char[][] getGrid() {
        return gridArr;
    }

}
