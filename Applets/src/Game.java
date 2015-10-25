import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

/**
 * Created by Oro on 10/25/2015.
 */
public class Game extends JComponent {

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.BLACK);
        g2.fillRect(0,0,getWidth(),getHeight());

        g2.setColor(Color.RED);
        g2.fill(new Ellipse2D.Double(0, 0, 15, 15));

        g2.setColor(Color.BLUE);
        g2.fill(new RoundRectangle2D.Double(200,200,100,10,20,20));
    }
}
