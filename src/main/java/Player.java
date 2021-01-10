import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Player {
    private final String name;
    private Ship[] ships;
    private Grid grid, enemyGrid;
    private boolean won = false;

    public Player(String name) {
        this.name = name;
        this.setGrid();
        this.initShips();
    }

    public boolean hasWon() { return won; }

    public void setWon(boolean won) { this.won = won; }

    public String getName() {
        return name;
    }

    public Ship[] getShips() {
        return ships;
    }

    private void setGrid() {
        grid = new Grid();
        enemyGrid = new Grid();
    }

    public void printGrid() {
        System.out.println("\n" + name);
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

    public void printEnemyGrid() {
        System.out.println("\n" + name);
        System.out.println("  1 2 3 4 5 6 7 8 9 10");
        char[][] x = enemyGrid.getGrid();
        int rowCodePoint = 65;
        for (char[] row : x) {
            System.out.print((char) rowCodePoint++);
            for (char e : row) {
                System.out.print(" " + e);
            }
            System.out.println();
        }
    }

    private void initShips() {
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
            boolean valid = false;
            while (!valid) {
                //TODO add user input check for the format col1row1 col2row2
                String[] coords = scanner.nextLine().split(" ");
                int[][] parsedCoords = parseCoords(coords);
                if (checkIfValid(parsedCoords, currentShip.getLength())) {
                    valid = true;
                    boolean successful = grid.addShipToGrid(parsedCoords);
                    if (!successful) {
                        valid = false;
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
        for (Ship currentShip : ships) {
            boolean valid = false;
            int[][] coords = new int[2][2];
            while (!valid) {
                int[] startingCoords = getRandomStartingPos();
                coords[0][0] = startingCoords[0];
                coords[1][0] = startingCoords[1];
                int[] endingCoords = getEndingPos(startingCoords, currentShip.getLength());
                coords[0][1] = endingCoords[0];
                coords[1][1] = endingCoords[1];
                if (endingCoords[0] != -1 && endingCoords[1] != -1) {
                    valid = true;
                }
            }
            grid.addShipToGrid(coords);
        }
    }

    public int[] getRandomStartingPos() {
        Random random = new Random();
        boolean valid = false;
        int randomRow = -1;
        int randomCol = -1;
        while (!valid) {
            randomRow = random.nextInt(10);
            randomCol = random.nextInt(10);
            if (grid.getType(randomRow, randomCol) == grid.FOG) {
                valid = true;
            }
        }
        return new int[]{randomRow, randomCol};
    }

    public int[] getEndingPos(int[] start, int length) {
        //{0, 1, 2, 3} correspond to {up, down, left, right}
        int[] directions = new int[]{0, 1, 2, 3};
        shuffleArray(directions);
        boolean valid;
        int[] endPos = new int[2];
        for (int direction : directions) {
            valid = true;
            switch (direction) {
                case 0:
                    if (start[0] - length < 0) {
                        valid = false;
                        break;
                    }
                    for (int row = start[0]; row > start[0] - length; row--) {
                        if (grid.getGrid()[row][start[1]] != grid.FOG) {
                            valid = false;
                            break;
                        }
                    }
                    endPos[0] = start[0] - length + 1;
                    endPos[1] = start[1];
                    break;
                case 1:
                    if (start[0] + length > 10) {
                        valid = false;
                        break;
                    }
                    for (int row = start[0]; row < start[0] + length; row++) {
                        if (grid.getGrid()[row][start[1]] != grid.FOG) {
                            valid = false;
                            break;
                        }
                    }
                    endPos[0] = start[0] + length - 1;
                    endPos[1] = start[1];
                    break;
                case 2:
                    if (start[1] - length < 0) {
                        valid = false;
                        break;
                    }
                    for (int col = start[1]; col > start[1] - length; col--) {
                        if (grid.getGrid()[start[0]][col] != grid.FOG) {
                            valid = false;
                            break;
                        }
                    }
                    endPos[0] = start[0];
                    endPos[1] = start[1] - length + 1;
                    break;
                case 3:
                    if (start[1] + length > 10) {
                        valid = false;
                        break;
                    }
                    for (int col = start[1]; col < start[1] + length; col++) {
                        if (grid.getGrid()[start[0]][col] != grid.FOG) {
                            valid = false;
                            break;
                        }
                    }
                    endPos[0] = start[0];
                    endPos[1] = start[1] + length - 1;
                    break;
            }
            if (valid) {
                return endPos;
            }
        }
        return new int[]{-1, -1};
    }

    static void shuffleArray(int[] ar)
    {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int i = ar.length - 1; i > 0; i--)
        {
            int index = rnd.nextInt(i + 1);
            // Simple swap
            int a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
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
        for (int[] x : parsedCoords) {
            for (int e : x) {
                if (!(0 <= e && e < 10)) {
                    return false;
                }
            }
        }
        int rowDistance = Math.abs(parsedCoords[0][1] - parsedCoords[0][0]) + 1;
        int colDistance = Math.abs(parsedCoords[1][1] - parsedCoords[1][0]) + 1;
        return rowDistance == length && colDistance == 1 || rowDistance == 1 && colDistance == length;
    }

    public void takeTurn(Player currentPlayer, Player enemyPlayer) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a coordinate:");
        String coordInput;
        boolean validCoord = false;
        int[] parsedCoord = new int[2];
        //TODO add proper input validation (make sure values are parsed to 0-9
        while (!validCoord) {
            coordInput = scanner.nextLine();
            String[] coordArr = coordInput.split("(?=\\d)", 2);
            parsedCoord = new int[]{(int) coordArr[0].charAt(0) - 65, Integer.parseInt(coordArr[1]) - 1};
            if (currentPlayer.enemyGrid.getType(parsedCoord[0], parsedCoord[1]) != currentPlayer.enemyGrid.FOG) {
                System.out.println("You have previously chosen this coordinate. Please choose again");
            } else {
                validCoord = true;
            }
        }
        //TODO implement winning condition (all enemy ships sank)
        if (enemyPlayer.grid.getType(parsedCoord[0], parsedCoord[1]) == enemyPlayer.grid.SHIP) {
            System.out.println("Hit!");
            currentPlayer.enemyGrid.setType(parsedCoord[0], parsedCoord[1], currentPlayer.grid.HIT);
        } else {
            System.out.println("Miss!");
            currentPlayer.enemyGrid.setType(parsedCoord[0], parsedCoord[1], currentPlayer.grid.MISS);
        }
    }
}
