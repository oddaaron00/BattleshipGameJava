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
                    + "(" + currentShip.getLength() + " cells):");
            boolean invalid = true;
            while (invalid) {

            }
        }
    }
    public void setShipsRandom() {

    }
}
