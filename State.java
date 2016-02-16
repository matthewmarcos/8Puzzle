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
        // Check if action is legit
        if()

        return this; //Temporary
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
    }

    private int getManhattanDistance () {
        // returns manhattan distance of this state
        int manhattanDistance = 0;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {

            }
        }
        return manhattanDistance;
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
