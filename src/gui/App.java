package gui;

import javax.swing.*;

/**
 * Created by Oro on 8/4/2015.
 */
public class App {

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                 MainFrame frame = new MainFrame();
            }
        });

    }
}
