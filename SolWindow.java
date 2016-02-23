import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class SolWindow {

    private static CoolButton[][] buttons = new CoolButton[3][3];
    private static ArrayList<State> states;
    private static JFrame frame;
    private static JPanel panel;
    private static JPanel commandPanel;
    private static JButton controlButtons;
    private static int location;
    private static State currentState;

    public SolWindow(ArrayList<State> states) {
        this.states = states;
        this.location = 0;
        frame = new JFrame("8-Puzzle Solution by Marcos");
        panel = new JPanel();
        initializeBoard();
        // drawBoard();



        // Initialize frame settings.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(600, 700));
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        // frame.add(commandPanel, BorderLayout.SOUTH);
        frame.setVisible(true);

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
                buttons[i][j].setEnabled(false);
                panel.add(buttons[i][j]);
            }
        }

    }


    // Prevent implicit declaration
    private SolWindow() {}
}
