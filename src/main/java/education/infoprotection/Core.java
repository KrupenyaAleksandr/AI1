package education.infoprotection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Core {

    private Painter painter;

    public Core() {
        painter = new Painter();
    }

    public void Start() {
        Stack<State> O = new Stack<>(); // general list
        Stack<State> C = new Stack<>(); // visited list
        Stack<State> path;

        State startState = new State();
        startState.makeInitialState();

        State targetState = new State();
        targetState.makeTargetState();

        boolean solved = false;

        State firstState = new State();
        State[] childrenArray = new State[8];
        int potentialRow, potentialCol;
        List<Integer> threats = new ArrayList<>();
        threats = Arrays.asList(1, 3, 4);

        O.push(startState);

        while(!O.empty()) {
            firstState = O.pop();

            if (firstState.equals(targetState)) {
                solved = true;
                break;
            }

            C.push(firstState);

            // left-up down
            potentialRow = firstState.knightRow - 1;
            potentialCol = firstState.knightCol - 2;
            if ((potentialRow >= 0 && potentialCol >= 0) &&
                    !threats.contains(firstState.field[potentialRow][potentialCol])) {
                childrenArray[0] = new State(firstState, potentialRow, potentialCol);
                childrenArray[0].field[firstState.knightRow][firstState.knightCol] = 4;
                childrenArray[0].field[potentialRow][potentialCol] = 1;
                if (!C.contains(childrenArray[0]) && !O.contains(childrenArray[0])) {
                    O.push(childrenArray[0]);
                }
            }

            // left-up up
            potentialRow = firstState.knightRow - 2;
            potentialCol = firstState.knightCol - 1;
            if ((potentialRow >= 0 && potentialCol >= 0) &&
                    !threats.contains(firstState.field[potentialRow][potentialCol])) {
                childrenArray[1] = new State(firstState, potentialRow, potentialCol);
                childrenArray[1].field[firstState.knightRow][firstState.knightCol] = 4;
                childrenArray[1].field[potentialRow][potentialCol] = 1;
                if (!C.contains(childrenArray[1]) && !O.contains(childrenArray[1])) {
                    O.push(childrenArray[1]);
                }
            }

            // right-up up
            if (firstState.knightRow + 1 <= 7 &&
                    firstState.knightCol - 2 >= 0) {
                childrenArray[2] = new State(firstState,
                        firstState.knightRow + 1,
                        firstState.knightCol - 2);
                if (!C.contains(childrenArray[2]) && !O.contains(childrenArray[2]));
                O.push(childrenArray[2]);
            }

            // right-up down
            if (firstState.knightRow + 2 <= 7 &&
                    firstState.knightCol - 1 >= 0) {
                childrenArray[3] = new State(firstState,
                        firstState.knightRow + 2,
                        firstState.knightCol - 1);
                if (!C.contains(childrenArray[3]) && !O.contains(childrenArray[3]));
                O.push(childrenArray[3]);
            }
        }
        if (solved) {
            System.out.println("Решение найдено");
            path = new Stack<>();
            while (!firstState.equals(startState)) {
                path.push(firstState);
                firstState = firstState.parent;
            }
        }
        else {
            System.out.println("Решение не было найдено");
        }
    }
}