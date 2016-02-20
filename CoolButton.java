import java.util.*;
import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class CoolButton extends JButton{

    private int i;
    private int j;

    public CoolButton(int i, int j) {
        // Instantiate this button with i, j, and label
        super();
        this.i = i;
        this.j = j;
    }

    public Action triggerAction() {
        // Return action instance when this is pressed.
        // The action instance should be passed to the state
        return new Action(this.i, this.j);
    }

    // Disable default constructor
    private CoolButton(){}
}
