public class State {
    private State parent;
    private int[][] board = new int[3][3];
    private int g; //cost
    private int h; //remaining distance
    private int f; // g+h

    public State (State parent, int[][] board, int g, int h) {
        this.parent = parent;
        this.g = g;
        this.h = h;
        this.f = g + h;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                this.board[i][j] = board[i][j];
            }
        }

    }

    public State doAction(Action a) {
        // Check if action is legit
        return this; //Temporary
    }

    private Action getEmpty() {
        // Returns the empty button in its board
    }

    public void printMe() {
        // Prints the entire state. For debugging.

    }

    private int getManhattanDistance () {
        // returns manhattan distance of this state
        return 0;
    }

    public boolean goalTest() {
        int num = 1;
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(this.board[i][j] != num) {
                    System.out.println("False");
                    return false;
                }
                num++;
            }
        }
        return true;
    }
}
