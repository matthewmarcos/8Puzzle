import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class Solver {

    // Preventing instantiating a Solver class
    private Solver() {}

    public static State solve(State s) {
        ArrayList<State> openList = new ArrayList<State>();
        ArrayList<State> closedList = new ArrayList<State>();
        openList.add(s);
        System.out.println("Initial s:");
        s.printMe();
        while(!openList.isEmpty()) {
            State bestNode = removeMinF(openList);
            System.out.println(bestNode.getF());
            closedList.add(bestNode);
            if(bestNode.goalTest()) {
                return bestNode;
            }
            ArrayList<Action> actionList = new ArrayList<Action>();
            actionList = bestNode.getActions();
            for(Action a : actionList) {
                State tempState = bestNode.doAction(a); //Execute the action
                if((!isMemberOf(tempState, openList) || !isMemberOf(tempState, closedList))) {
                    // If resulting state is not in the open or closed list, add it.
                    openList.add(tempState);
                }
                else {
                    // Resulting state is in the open or closed list
                    State duplicate = null;
                    if(isMemberOf(tempState, openList)) {
                        // Member of openlist
                        int index = getIndexOfEqual(tempState, openList);
                        duplicate = openList.get(index);
                        if(duplicate.getG() > tempState.getG()) {
                            openList.remove(index);
                            openList.add(tempState);
                        }
                    }
                    else {
                        // Member of closed list
                        int index = getIndexOfEqual(tempState, closedList);
                        duplicate = closedList.get(index);
                        if(duplicate.getG() > tempState.getG()) {
                            closedList.remove(index);
                            openList.add(tempState);
                        }
                    }
                }
            }

        }

        return null;
    }

    public static State solveFake() {
        int[][] board = new int[3][3];

        board[0][0] = 0; board[0][1] = 1; board[0][2] = 3;

        board[1][0] = 5; board[1][1] = 2; board[1][2] = 6;

        board[2][0] = 4; board[2][1] = 7; board[2][2] = 8;

        State tempState = new State(null, board, 0);
        return Solver.solve(tempState);
    }

    private static int getIndexOfEqual(State s, ArrayList<State> list) {
        for(int i = 0 ; i < list.size() ; i++) {
            if(list.get(i).equals(s)) {
                return i;
            }
        }
        return -1;
    }

    private static boolean isMemberOf(State s, ArrayList<State> list) {
        for(State f : list) {
            if(f.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private static State removeMinF(ArrayList<State> openList) {
        State bestNode = openList.get(0);
        for(State x : openList) {
            //find State with minimum F in the openList
            if(x.getF() < bestNode.getF()) {
                bestNode = x;
            }
        }
        openList.remove(bestNode);

        return bestNode;
    }
}
