package main.com.alex.tictactoe;

import java.util.Scanner;

public class GameLogic {

    private Field f;
    private boolean isTurnCrossPlayer;
    private int turnNumber;
    private int turnsLeftCounter;
    private boolean isGameOver;
    private boolean isGameExit;
    private State winner;
    private Scanner sc = new Scanner(System.in);

    GameLogic() {
        f = new Field();
        reset();
    }

    //метод процесса игры
    public void run() {

        System.out.println("Начинаем новую игру.");
        reset();

        while (true) {
            showField();
            showGameInfo();
            makeTurn();

            if (isGameExit)
                break;

            if (isGameOver) {
                showWinner();

                if (!tryAgain()) {
                    break;
                }
            }
        }
        sc.close();
    }

    //Перезапуск
    private void reset() {
        f.init();
        isTurnCrossPlayer = true;
        isGameOver = false;
        isGameExit = false;
        turnNumber = 1;
        winner = State.BLANK;
        turnsLeftCounter = Field.FIELD_WIDTH * Field.FIELD_WIDTH;
    }

    //метод сделать ход
    private void makeTurn() {
        try {
            int index = sc.nextInt();

            if (!isIndexCorrect(index))
                return;

            f.save(index, (isTurnCrossPlayer) ? State.X.toString() : State.O.toString());
            turnNumber++;
            turnsLeftCounter--;

            isGameOver = isGameOver(index);
        } catch (Exception ex) {

            if (sc.next().equalsIgnoreCase("q")) {
                isGameExit = true;
            } else {
                System.out.println("Ошибка! Повторите ход!");
            }
        }
    }

    private boolean isIndexCorrect(int index) {
        if (index < 0 ||
                index >= Field.FIELD_WIDTH * Field.FIELD_WIDTH ||
                !f.isBlank(index)) {
            System.out.println("Ошибка! Повторите ход!");
            return false;
        }

        return true;
    }

    //метод завершения игры
    private boolean isGameOver(int index) {

        if (turnsLeftCounter == 0)
            return true;

        int x = index / Field.FIELD_WIDTH;
        int y = index % Field.FIELD_WIDTH;

        if (f.isRowFinished(x) ||
                f.isColumnFinished(y) ||
                f.isDiagonalFromTopLeftFinished(x, y) ||
                f.isDiagonalFromTopRightFinished(x, y)) {

            winner = (isTurnCrossPlayer) ? State.X : State.O;
            return true;
        }

        isTurnCrossPlayer = !isTurnCrossPlayer;
        return false;
    }

    private void showWinner() {
        showField();
        System.out.print("Игра окончена!");

        if (winner == State.BLANK) {
            System.out.println(" Ничья.");
        } else {
            System.out.println(" Выиграл игрок " + winner + ".");
        }
    }

    //Метод для повтора игры по желанию пользователя
    private boolean tryAgain() {
        if (wantTryAgain()) {
            reset();
            System.out.println("Началась новая игра.");
            return true;
        }

        return false;
    }

    private boolean wantTryAgain() {
        while (true) {
            System.out.println("Хотите сыграть снова? (да/нет): ");
            String response = sc.next();
            if (response.equalsIgnoreCase("да")) {
                return true;
            } else if (response.equalsIgnoreCase("нет")) {
                return false;
            }
            System.out.println("Неправильный ввод. Повторите попытку!");
        }
    }

    //метод отображения игорового поля
    private void showField() {
        f.show();
    }

    //метод отображения информации об игре
    private void showGameInfo() {
        System.out.print("Ход номер " + turnNumber + ".");
        System.out.print(" Осталось ходов " + turnsLeftCounter + ".");
        System.out.println(" Ходит игрок " + ((isTurnCrossPlayer) ? State.X : State.O) + ".");
        System.out.println("Укажите координату или q для выхода:");
    }
}
