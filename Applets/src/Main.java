import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Oro on 10/25/2015.
 */
public class Main extends JApplet implements ActionListener {

    private Timer timer;
    private Game game;

    @Override
    public void init() {

        game = new Game();

        System.out.println("Init is called");
        setLayout(new BorderLayout());

        timer = new Timer(10, this);

        setSize(600, 500);
        add(game, BorderLayout.CENTER);
    }

    @Override
    public void start() {
        timer.start();
    }

    @Override
    public void destroy() {
    }

    @Override
    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
    }
}
