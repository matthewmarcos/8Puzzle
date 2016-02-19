public class Action {
    private int i;
    private int j;

    public Action(int i, int j) throws IllegalArgumentException {
        if(i < 0 || i > 2 || j < 0 || j > 2) throw new IllegalArgumentException("Illegal coordinates");
        this.i = i;
        this.j = j;
    }

    public int getI() {
        return this.i;
    }

    public int getJ() {
        return this.j;
    }

    public void printMe() {
        System.out.println("I: " + this.i);
        System.out.println("J: " + this.j);
        System.out.println("");
    }

    public boolean equals (Action a) {
        // Checks of two actions are equal
        if(a.getI() == this.i && a.getJ() == this.j) {
            return true;
        }
        return false;
    }
}
