package education.infoprotection;

import java.util.Arrays;

public class State {

    private int[][] field;
    private State parent;
    int[] knightCoord;

    public State () {
        field = new int[8][8];
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
