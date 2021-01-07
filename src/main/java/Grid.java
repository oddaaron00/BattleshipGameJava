import java.util.Arrays;

public class Grid {
    private final char FOG = '~';
    private final char SHIP = 'O';
    private final char HIT = 'X';
    private final char MISS = 'M';

    private final char[][]gridArr = new char[10][10];

    public Grid() {
        for (int row = 0; row < 10; row++) {
            Arrays.fill(gridArr[row], FOG);
        }
    }

    public char[][] getGrid() {
        return gridArr;
    }

    public boolean addShipToGrid(int[][] coords) {
        //The coordinate format is {{row1index, row2index}, {col1index, col2index}}
        if (coords[0][0] == coords[0][1]) {
            int largerIndex = Math.max(coords[1][0], coords[1][1]);
            int smallerIndex = Math.min(coords[1][0], coords[1][1]);
            int row = coords[0][0];
            for (int index = smallerIndex; index <= largerIndex; index++) {
                if (gridArr[row][index] == SHIP) {
                    System.out.println("Cannot place ship there");
                    return false;
                } else {
                    gridArr[row][index] = SHIP;
                }
            }
        } else {
            int largerIndex = Math.max(coords[0][0], coords[0][1]);
            int smallerIndex = Math.min(coords[0][0], coords[0][1]);
            int col = coords[1][0];
            for (int index = smallerIndex; index <= largerIndex; index++) {
                if (gridArr[index][col] == SHIP) {
                    System.out.println("Cannot place ship there. Please enter again:");
                    return false;
                } else {
                    gridArr[index][col] = SHIP;
                }
            }
        }
        return true;
    }

}
