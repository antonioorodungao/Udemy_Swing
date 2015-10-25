import java.applet.Applet;
import java.awt.*;

/**
 * Created by Oro on 10/25/2015.
 */
public class Main extends Applet {

    @Override
    public void init() {
        System.out.println("Init is called");
        setLayout(new BorderLayout());

        setSize(600,500);
        add(new Game(), BorderLayout.CENTER);



    }

    @Override
    public void start() {
        System.out.println("Start is called");
    }

    @Override
    public void destroy() {
        System.out.println("Destroy is called");
    }

    @Override
    public void stop() {
        System.out.println("Stop is called.");
    }
}
