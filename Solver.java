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
            closedList.add(bestNode);
            if(bestNode.goalTest()) {
                return bestNode;
            }
            ArrayList<Action> actionList = new ArrayList<Action>();
            actionList = bestNode.getActions();
            for(Action a : actionList) {
                State tempState = bestNode.doAction(a);
                if((!isMemberOf(tempState, openList) || !isMemberOf(tempState, closedList))) {
                    openList.add(tempState);
                }
                else {
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
