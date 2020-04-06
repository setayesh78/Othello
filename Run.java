import java.util.Scanner;

/**
 * make the game run and play
 * @author Setayesh
 */
public class Run {

    public static void main(String[] args){
        Board b = new Board();
        Othello r = new Othello();
        Scanner scanner = new Scanner(System.in);
        boolean flag = true;
        while(flag){
            System.out.println(" 1 . play with computer: ");
            System.out.println(" 2 . play with a friend : ");
            System.out.println(" 3 . exit..");
            switch (scanner.nextInt()){
                case 1 :
                    r.onePlayer(b);
                    break;
                case 2 :
                    r.twoPlayers(b);
                    break;
                case 3 :
                    flag = false;
                    break;
            }

        }
    }
}
