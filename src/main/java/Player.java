import java.util.Arrays;
import java.util.Scanner;

public class Player {
    private final String name;
    private Ship[] ships;
    private Grid grid;

    public Player(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Ship[] getShips() {
        return ships;
    }

    public void setGrid() {
        grid = new Grid();
    }

    public void printGrid() {
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char[][] x = grid.getGrid();
        int rowCodePoint = 65;
        for (char[] row : x) {
            System.out.print((char) rowCodePoint++);
            for (char e : row) {
                System.out.print(" " + e);
            }
            System.out.println();
        }
    }

    public void initShips() {
        Ship carrier = new Ship("Carrier");
        Ship battleship = new Ship("Battleship");
        Ship cruiser = new Ship("Cruiser");
        Ship submarine = new Ship("Submarine");
        Ship destroyer = new Ship("Destroyer");
        ships = new Ship[]{carrier, battleship, cruiser, submarine, destroyer};
    }

    public void setShips() {
        Scanner scanner = new Scanner(System.in);
        for (Ship currentShip : ships) {
            System.out.println("Enter the coordinates of the " + currentShip.getName()
                    + " (" + currentShip.getLength() + " cells):");
            boolean invalid = true;
            while (invalid) {
                String[] coords = scanner.nextLine().split(" ");
                int[][] parsedCoords = parseCoords(coords);
                if (checkIfValid(parsedCoords, currentShip.getLength())) {
                    invalid = false;
                    boolean successful = grid.addShipToGrid(parsedCoords);
                    if (!successful) {
                        invalid = true;
                    } else {
                        printGrid();
                    }
                } else {
                    System.out.println("Invalid coordinates. Please enter again:");
                }
            }
        }
    }
    public void setShipsRandom() {

    }

    /**
     * @param coords - the coordinates the user inputted in the form {row1col1, row2col2}
     * @return - the parsed coordinates in the form {{row1index, row2index}, {col1index, col2index}}
     */
    public static int[][] parseCoords(String[] coords) {
        String[] first = coords[0].split("(?=\\d)", 2);
        String[] second = coords[1].split("(?=\\d)", 2);
        int[] rowLetter = new int[]{(int) first[0].charAt(0) - 65, (int) second[0].charAt(0) - 65};
        int[] colNumber = new int[]{Integer.parseInt(first[1]) - 1, Integer.parseInt(second[1]) - 1};
        return new int[][]{rowLetter, colNumber};
    }

    private boolean checkIfValid(int[][] parsedCoords, int length) {
        int rowDistance = Math.abs(parsedCoords[0][1] - parsedCoords[0][0] + 1);
        int colDistance = Math.abs(parsedCoords[1][1] - parsedCoords[1][0] + 1);
        return rowDistance == length && colDistance == 1 || rowDistance == 1 && colDistance == length;
    }
}
