import java.util.*;
import java.lang.*;

public class State {
    private State parent;
    private int[][] board = new int[3][3];
    private int g; //cost
    private int h; //remaining distance
    private int f; // g+h

    public State (State parent, int[][] board, int g) {
        this.parent = parent;
        this.g = g;
        // this.h = h;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                this.board[i][j] = board[i][j];
            }
        }
        this.h = this.getManhattanDistance();
        this.f = this.g + this.h;

    }

    public State doAction(Action a) {
        int i = a.getI();
        int j = a.getJ();
        // Get index of 0
        int hotI, hotJ; //location of i and j
        int[][] tempBoard = new int[3][3];

        Action index0 = new Action(0, 0);

        // Initialize tempBoard
        for(int x = 0 ; x < 3 ; x++) {
            for(int y = 0 ; y < 3 ; y++) {
                tempBoard[x][y] = this.board[x][y];
            }
        }
        outerloop:
        for(int x = 0 ; x < 3 ; x++) {
            for(int y = 0 ; y < 3 ; y++) {
                if(tempBoard[x][y] == 0) {
                    index0 = new Action(x, y);
                    break outerloop;
                }
            }
        }

        hotI = index0.getI(); hotJ = index0.getJ();

        // Swap
        tempBoard[hotI][hotJ] = tempBoard[i][j];
        tempBoard[i][j] = 0;

        return new State(this, tempBoard, g+1); //Temporary
    }

    public ArrayList<Action> getActions() {
        // Returns an arraylist of all possible action to be done with current state

        Action index0 = new Action(0, 0);
        int hotI, hotJ; //location of i and j
        ArrayList<Action> tempActions = new ArrayList<Action>();
        // Get index of 0
        outerloop:
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(this.board[i][j] == 0) {
                    index0 = new Action(i, j);
                    break outerloop;
                }
            }
        }
        hotI = index0.getI(); hotJ = index0.getJ();
        // Get surroundings of Action index0 that are legit to use
        try {
            tempActions.add(new Action(hotI+1, hotJ));
        } catch(Exception e) {}

        try {
            tempActions.add(new Action(hotI-1, hotJ));
        } catch(Exception e) {}

        try {
            tempActions.add(new Action(hotI, hotJ+1));
        } catch(Exception e) {}

        try {
            tempActions.add(new Action(hotI, hotJ-1));
        } catch(Exception e) {}

        return tempActions;
    }

    private Action getEmpty() {
        // Returns the empty button in its board and returns it as an action
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(board[i][j] == 0) {
                    return new Action(i, j);
                }
            }
        }
        return new Action(0, 0);
    }

    public void printMe() {
        // Prints the entire state. For debugging.
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println("");
        }
        System.out.println("G: " + g);
        System.out.println("H: " + h);
        System.out.println("F: " + f);
        System.out.println("");
    }

    private int getManhattanDistance () {
        // returns manhattan distance of this state
        int manhattanDistance = 0;
        int x = 1;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                // Find the location of X
                Action location = findLocation(x);
                int realI = location.getI();
                int realJ = location.getJ();
                // System.out.println(x + ") X: " + realI + " J: " + realJ);
                int tempa = Math.abs(realI - i) + Math.abs(realJ - j);
                // System.out.println("Tempa (" + x + "):" + tempa);
                if(x < 9) {
                    manhattanDistance += tempa;
                }
                x++;
            }
        }
        return manhattanDistance;
    }

    private Action findLocation(int x) {
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(x == board[i][j]) {
                    return new Action(i, j);
                }
            }
        }

        return new Action(0, 0);
    }

    public boolean goalTest() {
        int num = 1;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(this.board[i][j] != num) {
                    System.out.println("Not yet solved!");
                    return false;
                }
                num++;
            }
        }
        return true;
    }
}
