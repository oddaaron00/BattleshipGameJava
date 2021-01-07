/**
 * Class to represent an individual ship
 */

public class Ship {

    private final String name;
    private int length;


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
    }

    public String getName() {
        return name;
    }
    public int getLength() {
        return length;
    }

}
