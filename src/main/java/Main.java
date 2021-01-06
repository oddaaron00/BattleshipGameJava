import java.util.Locale;
import java.util.Scanner;

public class Main {

    public static Player player1;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greetPlayer();
        System.out.println("Starting new game...");
        player1.setGrid();
        player1.printGrid();
        player1.initShips();
        System.out.println("Do you want to set ships randomly? (Y/N)");
        if (scanner.nextLine().matches("(?i)Y|YES")) {
            player1.setShips();
        } else {
            player1.setShipsRandom();
        }
    }

    private static void greetPlayer() {
        System.out.println("Welcome player! What should I call you?");
        player1 = new Player(scanner.nextLine());
        System.out.println("Hello, " + player1.getName() + "!");
    }
}
