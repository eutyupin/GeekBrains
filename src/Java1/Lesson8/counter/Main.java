package Java1.Lesson8.counter;

import java.awt.*;

public class Main {
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AppForm();
            }
        });
    }
}
