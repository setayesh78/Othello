import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * this class create where to put a player(the suitable location) and how to move the char of player
 * it shows the display and scores
 *  @author Setayesh
 */
public class Board {
    //use FINAL to make it constant
    private final char[][] board;
    int WScore, BScore, remaining;
    private final char boardX[] = new char[]{'A','B','C','D','E','F','G','H'};
    //using uni code to show the players instead of showing by chars
    char w = 9675;
    char b = 9679;
    /**
     * constructor to show the display in first step and organize the suitable place to put chars
     */
    public Board(){
        board = new char[][]{
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_',w,b,'_','_','_'},
                {'_','_','_',b,w,'_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
                {'_','_','_','_','_','_','_','_'},
        };
    }

    /**
     * check all 8 places around the opponent to place the player's char
     * @param player the char that is choosing a new place
     * @param opponent the char waiting for it's turn.
     * @param positions coordinate to put new char
     */
    private void findLocations(char player, char opponent, HashSet<Point> positions) {//using Set for point to avoid repeated locations.
        for (int i = 0; i < 8; ++i) {
            for (int j = 0; j < 8; ++j) {
                if (board[i][j] == opponent) {
                    int I = i, J = j;
                    for (int x = -1; x <= 1; x++) {
                        for (int y = -1; y <= 1; y++) {
                            if (i + x >= 0 && i + x <=7  && j + y >= 0 && j + y <= 7 && board[i + x][j + y] == '_') {
                                i = i - x;
                                j = j - y;
                                    while (i < 7 && i > 0 && j < 7 && j > 0 && board[i][j] == opponent) {
                                        i = i - x;
                                        j = j - y;
                                    }
                                }//always change an opponent to player's char
                                if (i <= 7 && i >= 0 && j <= 7 && j >= 0 && board[i][j] == player) positions.add(new Point(I + x, J + y));
                            i=I;j=J;
                            }
                        }
                    }
                }
            }
        }

    /**
     * show the game in a good way in terminal
     * @param b the board defined on constructor
     */
    public void displayBoard(Board b){
        System.out.print("\n  ");
        //print A to E
        for(int i=0;i<8;++i)System.out.print(boardX[i]+" ");
        System.out.println();
        //print 1 to 8 and the board
        for(int i=0;i<8;++i){
            System.out.print((i+1)+" ");
            for(int j=0;j<8;++j)
                System.out.print(b.board[i][j]+" ");
            System.out.println();
        }
        System.out.println();
    }

    /**
     * compute the scores to show the winner
     * @param whiteLocations
     * @param blackLocations
     * @return
     */
    public int gameResult(Set<Point> whiteLocations, Set<Point> blackLocations){
        updateScores();
        if(remaining == 0){
            if(WScore > BScore) return 1;
            else if(BScore > WScore) return -1;
            else return 0;
        }
        if(WScore==0 || BScore == 0){
            if(WScore > 0) return 1;
            else if(BScore > 0) return -1;
        }
        if(whiteLocations.isEmpty() && blackLocations.isEmpty()){
            if(WScore > BScore) return 1;
            else if(BScore > WScore) return -1;
            else return 0;
        }
        return -2;
    }

    /**
     * return the coordinate of suitable location
     * @param player the char of player
     * @param opponent the char that's waiting for it's turn
     * @return x,y of the location
     */
    public HashSet<Point> getLocations(char player, char opponent){
        HashSet<Point> positions = new HashSet<>();
        findLocations(player, opponent, positions);
        return positions;
    }

    /**
     * showing suitable location with * in terminal and then remove *
     * @param locations the suitable positions of char
     * @param player the player's char
     * @param opponent the opponent char
     */
    public void showLocations(HashSet<Point> locations, char player, char opponent){
        for(Point p:locations)
            board[p.x][p.y]='*';
        displayBoard(this);//print this board with * added
        for(Point p:locations)
            board[p.x][p.y]='_';
    }

    /**
     * choose a place for player and change opponent chars to player chars to set score
     * @param p coordinate of position
     * @param player char of player
     * @param opponent char of opponent
     */
    public void placeMove(Point p, char player, char opponent) {
        int i = p.x, j = p.y;
        board[i][j] = player;
        int I = i, J = j;
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                if (i + x >= 0 && i + x <=7  && j + y >= 0 && j + y <= 7 && board[i + x][j + y] == opponent) {
                    i = i + x;
                    j = j + y;
                    while (i < 7 && i > 0 && j < 7 && j > 0 && board[i][j] == opponent) {
                        i = i + x;
                        j = j + y;
                    }
                    if (i <= 7 && i >= 0 && j <= 7 && j >= 0 && board[i][j] == player) {
                        while (i != I + x || j != J + y) {
                            i = i - x;
                            j = j - y;
                            board[i][j] = player;
                        }
                    }
                }
                i = I;
                j = J;
            }
        }
    }

    /**
     * computing the score by checking the whole board and check chars
     */
    public void updateScores(){
        WScore = 0; BScore = 0; remaining = 0;
        for(int i=0;i<8;++i){
            for(int j=0;j<8;++j){
                if(board[i][j]==w)WScore++;
                else if(board[i][j]==b)BScore++;
                else remaining++;
            }
        }
    }

    /**
     * to make input not case sensitive
     * @param x input char
     * @return number of column
     */
    public int coordinateX(char x){
        for(int i=0;i<8;++i)if(boardX[i]==Character.toLowerCase(x)||boardX[i]==Character.toUpperCase(x))return i;
        return -1; // Illegal move received
    }

    /**
     * random choice of computer
     * @param fineLocation a set of possible locations for computer
     * @return the point that randomly chosen
     */
    public Point computerMove(HashSet<Point> fineLocation){
        Random random = new Random();
        int randomNumber = random.nextInt(fineLocation.size());

        int currentIndex = 0;
        Point randomElement = null;

        for(Point element : fineLocation){

            randomElement = element;

            if(currentIndex == randomNumber)
                return randomElement;
            currentIndex++;
        }
        return randomElement;
    }

    /**
     * computer choose the palace to have more scores
     * @param fineLocation a set of possible locations
     * @return the point that smartly chosen
     */
    public Point computerSmartMove(HashSet<Point> fineLocation){
        int max = 0;
        for (Point point : fineLocation){
            findLocations(w,b,fineLocation);
            updateScores();
            if (WScore > max){
                max = WScore;
            }
        }
        for (Point point : fineLocation){
            if (WScore == max)
                return point;
        }
        return (new Point(0,0));
    }
}
