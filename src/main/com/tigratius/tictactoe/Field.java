package main.com.alex.tictactoe;

enum State {BLANK, X, O}

public class Field {

    private String[][] field;
    static final int FIELD_WIDTH = 3;

    Field() {
        field = new String[FIELD_WIDTH][FIELD_WIDTH];
    }

    public void init() {
        int counter = 0;

        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                field[i][j] = String.valueOf(counter++);
            }
        }
    }

    public boolean isBlank(int index) {

        int x = index / FIELD_WIDTH;
        int y = index % FIELD_WIDTH;

        if (field[x][y].equals(State.X.toString()) ||
            field[x][y].equals(State.O.toString()))
            return false;

        return true;
    }

    public void save(int index, String s) {
        field[index / FIELD_WIDTH][index % FIELD_WIDTH] = s;
    }

    public void show() {
        System.out.println("****************************************");
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {

                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("****************************************");
    }

    public boolean isRowFinished(int row) {
        for (int i = 1; i < FIELD_WIDTH; i++) {
            if (field[row][i] != field[row][i - 1]) {
                break;
            }

            if (i == FIELD_WIDTH - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isColumnFinished(int column) {
        for (int i = 1; i < FIELD_WIDTH; i++) {
            if (field[i][column] != field[i - 1][column]) {
                break;
            }

            if (i == FIELD_WIDTH - 1) {
                return true;
            }
        }
        return false;
    }

    public boolean isDiagonalFromTopLeftFinished(int x, int y) {
        if (x == y) {
            for (int i = 1; i < FIELD_WIDTH; i++) {
                if (field[i][i] != field[i - 1][i - 1]) {
                    break;
                }
                if (i == FIELD_WIDTH - 1) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isDiagonalFromTopRightFinished(int x, int y) {
        if (FIELD_WIDTH - 1 - x == y) {
            for (int i = 1; i < FIELD_WIDTH; i++) {
                if (field[FIELD_WIDTH - 1 - i][i] != field[FIELD_WIDTH - i][i - 1]) {
                    break;
                }
                if (i == FIELD_WIDTH - 1) {
                    return true;
                }
            }
        }
        return false;
    }
}

