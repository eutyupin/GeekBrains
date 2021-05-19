package Java1.Lesson8.TicTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TicTacToe extends JFrame {
    public final int FIELD_SIZE = 3;
    public final int WIN_FACTOR = 3;
    private static final String DOT_EMPTY = "•";
    private static final String DOT_X = "X";
    private static final String DOT_O = "O";
    public JButton[][] cells = new JButton[FIELD_SIZE][FIELD_SIZE];
    private final Font FONT_CONSTANT = new Font("Arial", Font.BOLD, 48);
    private int[] targetCell = {0, 0};
    JFrame showMessage = new JFrame("Winner");

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 400);
        setResizable(false);
        setVisible(true);
        fillField();
    }

    private void fillField() {
        setLayout(new GridLayout(FIELD_SIZE, FIELD_SIZE));
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                cells[i][j] = new JButton(DOT_EMPTY);
                cells[i][j].setFont(FONT_CONSTANT);
                add(cells[i][j]);
                cells[i][j].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (((JButton) e.getSource()).getText() == DOT_EMPTY) {
                            ((JButton) e.getSource()).setText(DOT_X);
                            if (isWin(DOT_X)) {
                                JOptionPane.showMessageDialog(showMessage, "Вы выиграли!",
                                        "Победитель", JOptionPane.PLAIN_MESSAGE);
                                clearField();
                            } else {
                                if (checkDots()) {
                                    computerTurn();
                                    if (isWin(DOT_O)) {
                                        JOptionPane.showMessageDialog(showMessage, "Компьютер выиграл!",
                                                "Победитель", JOptionPane.PLAIN_MESSAGE);
                                        clearField();
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(showMessage, "НИЧЬЯ!",
                                            "Победитель", JOptionPane.PLAIN_MESSAGE);
                                    clearField();
                                }
                            }
                        }
                    }
                });
            }
        }
    }

    private void clearField() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                cells[i][j].setText(DOT_EMPTY);
            }
        }
    }

    private void computerTurn() {
        Random random = new Random();
        int posX = 0;
        int posY = 0;
        int [] check = {0,0};
        do{
            check = humanAnalysis();
            if((check[0] != 0 || check[1] != 0) && (cells[check[0]][check[1]].getText() == DOT_EMPTY)) {
                posX = check[0];
                posY = check[1];
            } else {
                posX = random.nextInt(FIELD_SIZE);
                posY = random.nextInt(FIELD_SIZE);
            }
        } while (cells[posX][posY].getText() != DOT_EMPTY);
        cells[posX][posY].setText(DOT_O);
    }

    private boolean isWin(String symbol) {
        if (checkRowsCols(symbol)) return true;
        else return checkDiagonals(symbol);
    }

    private boolean checkDots() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (cells[i][j].getText() == DOT_EMPTY) return true;
            }
        }
        return false;
    }

    private boolean checkRowsCols(String symbol) {
        for (int i = 0; i < FIELD_SIZE; i++) {
            int rowCounter = 0;
            int colCounter = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                rowCounter = (cells[i][j].getText() == symbol) ? rowCounter + 1 : 0;
                colCounter = (cells[j][i].getText() == symbol) ? colCounter + 1 : 0;
                if (rowCounter == FIELD_SIZE || colCounter == FIELD_SIZE) return true;
            }
        }
        return false;
    }

    private boolean checkDiagonals(String symbol) {
        int insideDiagonal = 0;
        int outsideDiagonal = 0;
        for (int i = 0; i < FIELD_SIZE; i++) {
            insideDiagonal = (cells[i][i].getText() == symbol) ? insideDiagonal + 1 : 0;
            outsideDiagonal = (cells[i][cells.length - i - 1].getText() == symbol) ? outsideDiagonal + 1 : 0;
            if (insideDiagonal == FIELD_SIZE || outsideDiagonal == FIELD_SIZE) return true;
        }
        return false;
    }

    private int[] humanAnalysis() {
        int check = 0;
        int[] targetCell = {0, 0};

        // проверка строк
        for (int i = 0; i < FIELD_SIZE; i++) {
            check = 0;
            for (int k = 0; k < targetCell.length; k++) targetCell[k] = 0;
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (cells[i][j].getText() == DOT_X) {
                    targetCell[0] = i;
                    if (j == FIELD_SIZE - 1) targetCell[1] = FIELD_SIZE - WIN_FACTOR;
                    else targetCell[1] = j + 1;
                    check++;
                }
            }
            if ((check == WIN_FACTOR - 1) && (cells[targetCell[0]][targetCell[1]].getText() != DOT_O)) return targetCell;
        }
        // проверка столбцов
        for (int j = 0; j < FIELD_SIZE; j++) {
            check = 0;
            for (int k = 0; k < 2; k++) targetCell[k] = 0;
            for (int i = 0; i < FIELD_SIZE; i++) {
                if (cells[i][j].getText() == DOT_X) {
                    targetCell[1] = j;
                    if (i == FIELD_SIZE - 1) targetCell[0] = FIELD_SIZE - WIN_FACTOR;
                    else targetCell[0] = i + 1;
                    check++;
                }
            }
            if ((check == WIN_FACTOR - 1) && (cells[targetCell[0]][targetCell[1]].getText() != DOT_O)) return targetCell;
        }
        // проверка основных диагоналей
        check = 0;
        for (int k = 0; k < 2; k++) targetCell[k] = 0;
        for (int i = 0; i < FIELD_SIZE; i++){
            if(cells[i][i].getText() == DOT_X) {
                if(i == FIELD_SIZE-1) {
                    targetCell[0] = FIELD_SIZE - WIN_FACTOR;
                    targetCell[1] = FIELD_SIZE - WIN_FACTOR;
                } else {
                    targetCell[0] = i + 1;
                    targetCell[1] = i + 1;
                }
                check++;
            }
            if ((check == WIN_FACTOR - 1) && (cells[targetCell[0]][targetCell[1]].getText() != DOT_O)) return targetCell;
        }
        // проверка основных диагоналей
        check = 0;
        for (int k = 0; k < 2; k++) targetCell[k] = 0;
        for (int i = 0; i < FIELD_SIZE; i++){
            if(cells[i][FIELD_SIZE - i - 1].getText() == DOT_X) {
                if(i == FIELD_SIZE-1) {
                    targetCell[0] = FIELD_SIZE - WIN_FACTOR;
                    targetCell[1] = FIELD_SIZE - WIN_FACTOR;
                } else {
                    targetCell[0] = i + 1;
                    targetCell[1] = FIELD_SIZE - i - 2;
                }
                check++;
            }
            if ((check == WIN_FACTOR - 1) && (cells[targetCell[0]][targetCell[1]].getText() != DOT_O)) return targetCell;
        }
        return targetCell;
    }
}


