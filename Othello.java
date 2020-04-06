import java.util.HashSet;
import java.util.Scanner;

/**
 * this class make the game playing and print suitable notes for players
 *  @author Setayesh
 */
public class Othello {
    /**
     * playing the game...
     *
     * @param B board class
     */
    public static void twoPlayers(Board B) {
        char w = 9675;
        char b = 9679;
        Scanner scan = new Scanner(System.in);
        Point move = new Point(-1, -1);
        System.out.println("Black Moves first");

        int result;
        Boolean pass;
        String input;

        while (true) {
            pass = false;

            HashSet<Point> blackLocations = B.getLocations(b, w);
            HashSet<Point> whiteLocations = B.getLocations(w, b);

            B.showLocations(blackLocations, b, w);
            result = B.gameResult(whiteLocations, blackLocations);

            if (result == -1) {
                System.out.println("Black wins: " + B.BScore + ":" + B.WScore);
                break;
            } else if (result == 0) {
                System.out.println("It is a draw.");
                break;
            } else if (result == 1) {
                System.out.println("White wins: " + B.WScore + ":" + B.BScore);
                break;
            }

            if (blackLocations.isEmpty()) {
                System.out.println("PASS");
                pass = true;
            }

            if (!pass) {
                System.out.println("Black :  ");
                input = scan.next();
                move.x = (Integer.parseInt(input.charAt(0) + "") - 1);//change the char to its int value
                move.y = B.coordinateX(input.charAt(1));

                while (!blackLocations.contains(move)) {
                    System.out.println("Invalid move!\n\nBlack :  ");
                    input = scan.next();
                    move.x = Integer.parseInt((input.charAt(0) + "")) - 1;
                    move.y = B.coordinateX(input.charAt(1));
                }
                B.placeMove(move, b, w);
                B.updateScores();
                System.out.println("\nBlack: " + B.BScore + " White: " + B.WScore);
            }
            pass = false;

            whiteLocations = B.getLocations(w, b);
            blackLocations = B.getLocations(b, w);

            B.showLocations(whiteLocations, w, b);
            result = B.gameResult(whiteLocations, blackLocations);

            if (result == -1) {
                System.out.println("Black wins: " + B.BScore + ":" + B.WScore);
                break;
            } else if (result == 0) {
                System.out.println("It is a draw.");
                break;
            } else if (result == 1) {
                System.out.println("White wins: " + B.WScore + ":" + B.BScore);
                break;
            }

            if (whiteLocations.isEmpty()) {
                System.out.println("PASS");
                pass = true;
            }

            if (!pass) {
                System.out.println("White : ");
                input = scan.next();
                move.x = (Integer.parseInt(input.charAt(0) + "") - 1);
                move.y = B.coordinateX(input.charAt(1));

                while (!whiteLocations.contains(move)) {
                    System.out.println("Invalid move!\n\nWhite : ");
                    input = scan.next();
                    move.x = (Integer.parseInt(input.charAt(0) + "") - 1);
                    move.y = B.coordinateX(input.charAt(1));
                }
                B.placeMove(move, w, b);
                B.updateScores();
                System.out.println("\nBlack : " + B.BScore + " White : " + B.WScore);
            }
        }
    }

    /**
     * playin with computer method
     *
     * @param B Board class
     */
    public static void onePlayer(Board B) {
        char w = 9675;
        char b = 9679;
        Scanner scan = new Scanner(System.in);
        Point move = new Point(-1, -1);
        System.out.println("you Move first!");
        //you are black and computer is white
        int result;
        Boolean pass;
        String input;

        while (true) {
            pass = false;

            HashSet<Point> humanLocations = B.getLocations(b, w);
            HashSet<Point> computerLocations = B.getLocations(w, b);

            B.showLocations(humanLocations, b, w);
            result = B.gameResult(computerLocations, humanLocations);

            if (result == -1) {
                System.out.println("you win: " + B.BScore + ":" + B.WScore);
                break;
            } else if (result == 0) {
                System.out.println("It is a draw.");
                break;
            } else if (result == 1) {
                System.out.println("computer wins: " + B.WScore + ":" + B.BScore);
                break;
            }

            if (humanLocations.isEmpty()) {
                System.out.println("PASS");
                pass = true;
            }

            if (!pass) {
                System.out.println("you :  ");
                input = scan.next();
                move.x = (Integer.parseInt(input.charAt(0) + "") - 1);//change the char to its int value
                move.y = B.coordinateX(input.charAt(1));

                while (!humanLocations.contains(move)) {
                    System.out.println("Invalid move!\n\nyou :  ");
                    input = scan.next();
                    move.x = Integer.parseInt((input.charAt(0) + "")) - 1;
                    move.y = B.coordinateX(input.charAt(1));
                }
                B.placeMove(move, b, w);
                B.updateScores();
                System.out.println("\nyou: " + B.BScore + " computer: " + B.WScore);
            }
            pass = false;

            computerLocations = B.getLocations(w, b);
            humanLocations = B.getLocations(b, w);

            B.showLocations(computerLocations, w, b);
            result = B.gameResult(computerLocations, humanLocations);

            if (result == -1) {
                System.out.println("you win : " + B.BScore + ":" + B.WScore);
                break;
            } else if (result == 0) {
                System.out.println("It is a draw.");
                break;
            } else if (result == 1) {
                System.out.println("computer wins : " + B.WScore + ":" + B.BScore);
                break;
            }

            if (computerLocations.isEmpty()) {
                System.out.println("PASS");
                pass = true;
            }
            if (!pass) {
                System.out.println("computer : ");
                B.placeMove(B.computerSmartMove(computerLocations), w, b);
                B.updateScores();
                System.out.println("\nyou : " + B.BScore + " computer : " + B.WScore);
            }
        }
    }
}
