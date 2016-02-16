import java.util.*;

public class Puzzle {

    private static State currentState;

    public static void main (String[] args) {
        currentState = Puzzle.createRandomState();
        currentState.printMe();
    }

    public static State createRandomState() {
        int [][] tempBoard = new int[3][3];
        Random random = new Random();
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                tempBoard[i][j] = 0;
            }
        }
        for(int i = 1 ; i < 9 ; i++) {
            int tempI;
            int tempJ;
            while(!isExisting(tempBoard, i)) {
                tempI = random.nextInt(3);
                tempJ = random.nextInt(3);
                if(tempBoard[tempI][tempJ] == 0) {
                    tempBoard[tempI][tempJ] = i;
                    break;
                }
            }
        }

        return new State(null, tempBoard, 0);
    }

    private static boolean isExisting(int[][] toTest, int x) {
        // Returns true if number x exists in toTest[][]
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(toTest[i][j] == x) {
                    return true;
                }
            }
        }

        return false;
    }
}
