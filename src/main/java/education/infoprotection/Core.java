package education.infoprotection;

import java.util.*;

public class Core {

    private final Painter painter = new Painter();
    Stack<State> O = new Stack<>(); // general list
    Set<State> C = new HashSet<>(); // visited list
    LinkedList<State> path;
    long iterations = 0, maxOpen = 0, maxStorage = 0;
    boolean solved = false;
    List<Integer> threats = Arrays.asList(3, 4);

    public void Start() {
        State startState = new State();
        startState.makeInitialState();

        State targetState = new State();
        targetState.makeTargetState();

        State firstState = new State();
        O.push(startState);
        firstState = O.pop();

        if (firstState.equals(targetState)) {
            solved = true;
        }

        C.add(firstState);

        List<State> listChildren = generateKnightMoves(firstState, 0);
        addToOpenList(listChildren);

        while(!O.isEmpty()) {

            firstState = O.pop();

            if (firstState.equals(targetState)) {
                solved = true;
                break;
            }

            C.add(firstState);

            listChildren = generateKnightMoves(firstState, 4);
            addToOpenList(listChildren);
        }
        if (solved) {
            System.out.println("Решение найдено");
            System.out.println("Итерации: " + iterations);
            System.out.println("Максимальная длина O: " + maxOpen);
            maxStorage = O.size() + C.size();
            System.out.println("Максимальная длина O + C: " + maxStorage + "\n");
            path = new LinkedList<>();
            while (!firstState.equals(startState)) {
                path.addFirst(firstState);
                firstState = firstState.parent;
            }
            path.addFirst(firstState);
            printPath();
        }
        else {
            System.out.println("Решение не было найдено");
        }
    }

    public void addToOpenList(List<State> listChildren) {
        for (State child : listChildren) {
            if (!C.contains(child) && !O.contains(child)) {
                O.add(child);
            }
        }

        iterations++;
        maxOpen = Math.max(O.size(), maxOpen);
        listChildren.clear();
    }

    public List<State> generateKnightMoves(State currentState, int cell) {
        List<State> listChildrens = new ArrayList<>();
        int[][] moves = { // start : left-up down to right
                {-1, -2}, {-2, -1}, {-2, 1}, {-1, 2},
                {1, 2}, {2, 1}, {2, -1}, {1, -2}
        };

        for (int[] move : moves) {
            int newRow = currentState.knightRow + move[0];
            int newCol = currentState.knightCol + move[1];

            if (newRow >= 0 && newRow <= 7 && newCol >= 0 && newCol <= 7 &&
                !threats.contains(currentState.field[newRow][newCol])) {
                State newState = new State(currentState, newRow, newCol);
                newState.field[currentState.knightRow][currentState.knightCol] = cell;
                newState.field[newRow][newCol] = 1;
                listChildrens.add(newState);
            }
        }

        return listChildrens;
    }

    public void printPath() {
        for (State state : path) {
            painter.printBoard(state.field);
            System.out.println();
        }
    }
}