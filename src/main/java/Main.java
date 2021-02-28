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
        boolean validName = false;
        String playerName = "";
        while (!validName) {
            playerName = scanner.nextLine();
            if (!playerName.isBlank()) {
                validName = true;
            } else {
                System.out.println("Name must contain at least one character!");
            }
        }
        player1 = new Player(playerName);
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


    //TODO possibly tidy play()
    private static void play() {
        System.out.println("\nStart!");
        boolean winner = false;
        while (!winner) {
            System.out.println(player1.getName() + "'s turn");
            player1.printEnemyGrid();
            player1.takeTurn(player1, player2);
            if (player1.hasWon()) {
                winner = true;
                System.out.println(player1.getName() + " has won!");
            } else {
                //TODO implement Computer AI logic
                System.out.println("\nCOMPUTER AI LOGIC");
                /*System.out.println(player2.getName() + "'s turn");
                player2.printEnemyGrid();
                player2.takeTurn(player2, player1);
                if (player2.hasWon()) {
                    winner = true;
                    System.out.println(player2.getName() + " has won!");
                }*/
            }
        }
    }
}
