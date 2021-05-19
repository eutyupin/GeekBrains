package Lesson8.TicTacToe;

import Lesson8.counter.AppForm;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TicTacToe();
            }
        });

    }
}
