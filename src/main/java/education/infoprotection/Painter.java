package education.infoprotection;

public class Painter {

    private final String WHITE_KNIGHT = "N "; // 1
    private final String BLACK_KING = "K "; // 2
    private final String FIRE = "F "; // 3
    private final String ALREADY_VISITED = "V "; // 4
    private final String START = "S "; // 5
    private final String WHITE_CELL = "□ ";
    private final String BLACK_CELL = "■ ";
    private final int SIZE = 8;

    public void printBoard(int[][] field) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (field[row][col] == 1) {
                    System.out.print(WHITE_KNIGHT);
                }
                else if (field[row][col] == 2) {
                    System.out.print(BLACK_KING);
                }
                else if (field[row][col] == 3) {
                    System.out.print(FIRE);
                }
                else if (field[row][col] == 4) {
                    System.out.print(ALREADY_VISITED);
                }
                else {
                    if ((row + col) % 2 == 0) {
                        System.out.print(BLACK_CELL);
                    } else {
                        System.out.print(WHITE_CELL);
                    }
                }
            }
            System.out.println();
        }
    }

}