package exercises;

import java.util.Random;
import java.util.Scanner;

public class RockPaperScissors {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(true) {
            String[] rps = {"r", "p", "s"};

            Random random = new Random();

            String computerMove = rps[random.nextInt(2)];

            String playerMove;

            while (true) {
                System.out.println("Please enter your move (r, p or s)");
                playerMove = scanner.nextLine();
                if (playerMove.equals("r") || playerMove.equals("p") || playerMove.equals("s")) {
                    break;
                }
                System.out.println(playerMove + " is not a valid move");
            }
            System.out.println("Computer played: " + computerMove);

            if (playerMove.equals(computerMove)) {
                System.out.println("The game was tie !");
            }
            //////
            else if (playerMove.equals("r")) {
                if (computerMove.equals("p")) {
                    System.out.println("You lose");
                } else if (computerMove.equals("s")) {
                    System.out.println("You win");
                }
            }
            /////
            else if (playerMove.equals("p")) {
                if (computerMove.equals("r")) {
                    System.out.println("You win");
                } else if (computerMove.equals("s")) {
                    System.out.println("You lose");
                }
            }
            /////
            else if (playerMove.equals("s")) {
                if (computerMove.equals("p")) {
                    System.out.println("You win");
                } else if (computerMove.equals("r")) {
                    System.out.println("You lose");
                }
            }
            System.out.println("Game is over. Play again? (y/n)");
            if(scanner.nextLine().equals("n")){
                break;
            }
        }
    }
}
