import java.util.Scanner;

public class Main {

    public static Player player1, player2;
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        playerOneInit();
        playerTwoInit();
        play();
    }

    private static void greetPlayer() {
        System.out.println("Welcome player! What should I call you?");
        player1 = new Player(scanner.nextLine());
        System.out.println("Hello, " + player1.getName() + "!");
    }

    private static void playerOneInit() {
        greetPlayer();
        System.out.println("Starting new game...");
        player1.printGrid();
        System.out.println("Do you want to set ships randomly? (Y/N)");
        if (scanner.nextLine().matches("(?i)Y|YES")) {
            player1.setShipsRandom();
        } else {
            player1.setShips();
        }
        player1.printGrid();
    }

    private static void playerTwoInit() {
        player2 = new Player("Computer");
        player2.setShipsRandom();
        player2.printGrid();
    }

    private static void play() {
        System.out.println("Start!");
        boolean winner = false;
        while (!winner) {
            player1.takeTurn(player1, player2);
            if (player1.hasWon()) {
                winner = true;
                System.out.println(player1.getName() + " has won!");
            } else {
                player2.takeTurn(player2, player1);
                if (player2.hasWon()) {
                    winner = true;
                    System.out.println(player2.getName() + " has won!");
                }
            }
        }
    }
}
