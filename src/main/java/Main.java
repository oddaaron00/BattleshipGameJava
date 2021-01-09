import java.util.Scanner;

public class Main {

    public static Player player1, player2;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        greetPlayer();
        System.out.println("Starting new game...");
        player1.printGrid();
        System.out.println("Do you want to set ships randomly? (Y/N)");
        if (scanner.nextLine().matches("(?i)Y|YES")) {
            System.out.println("YES");
            player1.setShipsRandom();
        } else {
            System.out.println("NO");
            player1.setShips();
        }
        player1.printGrid();
        player2 = new Player("Computer");
        player2.setShipsRandom();
        player2.printGrid();
    }

    private static void greetPlayer() {
        System.out.println("Welcome player! What should I call you?");
        player1 = new Player(scanner.nextLine());
        System.out.println("Hello, " + player1.getName() + "!");
    }
}
