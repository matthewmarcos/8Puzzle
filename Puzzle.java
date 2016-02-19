import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Puzzle {

    private static State currentState;

    public static void main (String[] args) {
        currentState = Puzzle.createRandomState();
        JFrame frame = new JFrame("8-Puzzle by Marcos");
        JPanel panel = new JPanel();

        // Initialize panel settings
        setBoard(panel);

        // Initialize frame settings.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 700));
        frame.setLayout(new BorderLayout());


        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private static void setBoard(JPanel panel) {
        JButton[][] buttons = new JButton[3][3];

        panel.setSize(new Dimension(600, 600));
        panel.setLayout(new GridLayout(3, 3));

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                buttons[i][j] = new JButton("Button");
                panel.add(buttons[i][j]);
            }
        }


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
