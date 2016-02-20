import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Puzzle {

    private static State currentState;
    private static CoolButton[][] buttons = new CoolButton[3][3];
    private static JFrame frame;
    private static JPanel panel;
    private static JPanel commandPanel;
    private static JButton controlButtons;

    public static void main (String[] args) {
        // Randomly generate puzzle
        currentState = Puzzle.createRandomState();

        frame = new JFrame("8-Puzzle by Marcos");

        panel = new JPanel();
        // Initialize panel settings
        panel.setSize(new Dimension(600, 600));
        initializeBoard();
        drawBoard();

        // Initiate Command Panel (bottom of the board)
        commandPanel = new JPanel();
        commandPanel.setSize(new Dimension(600, 100));
        initializeCommandPanel();

        // Initialize frame settings.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 700));
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.add(commandPanel, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

    private static void initializeCommandPanel() {
        controlButtons = new JButton("Solve");
        // controlButtons.setRolloverEnabled(false);
        // controlButtons.setEnabled(false);
        controlButtons.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent ev){
                State tempState = Solver.solve(currentState);
                // if unsolvable, solver returns null
            }
            public void mousePressed(MouseEvent ev){}
            public void mouseEntered(MouseEvent ev){}
            public void mouseReleased(MouseEvent ev){}
            public void mouseExited(MouseEvent ev){}

        });
        controlButtons.setSize(new Dimension(600, 100));

        commandPanel.setSize(new Dimension(600, 100));
        commandPanel.add(controlButtons);
    }

    private static void initializeBoard() {
        // Initializes the panel with buttons.
        // The states of the buttons are also initialized here.
        panel.setSize(new Dimension(600, 600));
        panel.setLayout(new GridLayout(3, 3));

        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                buttons[i][j] = new CoolButton(i, j);
                buttons[i][j].setRolloverEnabled(false);
                // buttons[i][j].setEnabled(false);
                buttons[i][j].addMouseListener(new MouseListener() {
                    public void mouseClicked(MouseEvent ev){
                        Puzzle.move(((CoolButton)ev.getSource()).triggerAction());
                    }
                    public void mousePressed(MouseEvent ev){}
                    public void mouseEntered(MouseEvent ev){}
                    public void mouseReleased(MouseEvent ev){}
                    public void mouseExited(MouseEvent ev){}

                });
                panel.add(buttons[i][j]);
            }
        }
    }

    private static void move(Action e) {
        // Moves the board
        currentState = currentState.doAction(e);
        currentState.setNullParent(); // Null parent to save space
        drawBoard();
    }

    public static void drawBoard() {
        // Change the labels of the board.
        for(int i = 0 ; i < 3 ; i++) {
            for(int j = 0 ; j < 3 ; j++) {
                if(currentState.getValues()[i][j] != 0) {
                    // Draw a number if value is not zero
                    buttons[i][j].setBackground(Color.WHITE);
                    buttons[i][j].setText(currentState.getValues()[i][j] + "");
                }
                else {
                    // If the number is zero, Do not draw the number and chage the background
                    buttons[i][j].setBackground(Color.GRAY);
                    buttons[i][j].setText("");
                }
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 40));
            }
        }

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
