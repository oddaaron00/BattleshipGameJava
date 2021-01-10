/**
 * Class to represent an individual ship
 */

public class Ship {

    private final String name;
    private int length;
    private boolean sunk = false;
    private int[] shipArr;


    /**
     * Class constructor
     * @param name - the name of the particular ship
     * Each case initialises the length of the ship
     */
    public Ship(String name) {
        this.name = name;
        switch (name) {
            case "Carrier":
                this.length = 5;
                break;
            case "Battleship":
                this.length = 4;
                break;
            case "Cruiser":
            case "Submarine":
                this.length = 3;
                break;
            case "Destroyer":
                this.length = 2;
                break;
        }
        this.shipArr = new int[length];
    }

    public int[] getShipArr() { return shipArr; }
    public boolean isSunk() { return sunk; }
    public String getName() {
        return name;
    }
    public int getLength() {
        return length;
    }

    public void setSunk(boolean bool) { sunk = true; }

    public void setHit(int index) { shipArr[index] = 1; }
}
