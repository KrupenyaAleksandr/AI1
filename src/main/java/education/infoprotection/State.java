package education.infoprotection;

import java.util.Arrays;

public class State {

    public int[][] field;
    public State parent;
    public int knightRow;
    public int knightCol;

    public State () {}

    public State(State parent, int newKnightRow, int newKnightCol) {
        field = new int[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                field[i][j] = parent.field[i][j];
            }
        }
        this.parent = parent;
        knightRow = newKnightRow;
        knightCol = newKnightCol;
    }

    public void makeInitialState() {
        field = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 2, 0, 0, 0, 0, 0, 0},
                { 3, 0, 0, 0, 0, 3, 0, 0},
                { 0, 0, 1, 0, 0, 0, 0, 0},
        };
        knightRow = 7;
        knightCol = 2;
    }

    public void makeTargetState() {
        field = new int[][] {
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 0, 0, 0, 0, 0, 0},
                { 0, 1, 0, 0, 0, 0, 0, 0},
                { 3, 0, 0, 0, 0, 0, 0, 0},
                { 0, 0, 4, 0, 0, 0, 0, 0},
        };
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return Arrays.deepEquals(field, state.field); // check deepEquals
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(field);
    }
}
