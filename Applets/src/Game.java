import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.*;
import java.awt.image.BufferedImage;

/**
 * Created by Oro on 10/25/2015.
 */
public class Game extends JComponent {

    private Ellipse2D.Double ball = new Ellipse2D.Double(100, 100, 15, 15);
    private RoundRectangle2D.Double bat = new RoundRectangle2D.Double(200, 200, 100, 10, 20, 20);

    private double speed = 10.0;
    private double bat_speed = 10.0;

    private int xDirectionBall = 1;
    private int yDirectionBall = 1;

    private BufferedImage buffer;

    public Game() {

        addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                System.out.println();

            }

            @Override
            public void mouseMoved(MouseEvent e) {

                //System.out.println(String.format("%s %s", e.getX(), e.getY()));
                bat.x = e.getX() - bat.getWidth() / 2;
                bat.y = e.getY() - bat.getHeight() / 2;

            }
        });

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ball.x = e.getX();
                ball.y = e.getY();
            }
        });

        Cursor hiddenCursor = getToolkit().createCustomCursor(new BufferedImage(1,1,BufferedImage.TYPE_INT_ARGB),new Point(1,1), "");
        setCursor(hiddenCursor);

        //setFocusable(true);
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                int key=e.getKeyCode();
                switch (key){
                    case KeyEvent.VK_UP:
                        bat.y -= bat_speed;
                        break;
                    case KeyEvent.VK_DOWN:
                        bat.y += bat_speed;
                        break;
                    case KeyEvent.VK_LEFT:
                        bat.x -= bat_speed;
                        break;
                    case KeyEvent.VK_RIGHT:
                        bat.x += bat_speed;
                        break;
                }
                return false;
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {

        if (buffer == null) {
            buffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);

        }
        Graphics2D g2 = (Graphics2D) buffer.getGraphics();


        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.black);
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setColor(Color.red);
        g2.fill(ball);

        g2.setColor(Color.blue);
        g2.fill(bat);

        g.drawImage(buffer, 0, 0, null);
    }

    public void update() {

        ball.x += xDirectionBall * speed;
        ball.y += yDirectionBall * speed;

        if (ball.x < 0) {
            xDirectionBall = 1;
            ball.x = 0;
        } else if (ball.x > getWidth() - ball.getBounds().width) {
            xDirectionBall = -1;
            ball.x = getWidth() - ball.getBounds().width;
        }

        if (ball.y < 0) {
            yDirectionBall = 1;
            ball.y = 0;
        } else if (ball.y > getHeight() - ball.getBounds().height) {
            yDirectionBall = -1;
            ball.y = getHeight() - ball.getBounds().height;
        }

        repaint();
    }

}
