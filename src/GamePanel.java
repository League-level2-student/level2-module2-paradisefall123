import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Timer frameDraw;
    Font titleFont;
    Font startFont;
    Font instructionsFont;
    Font endFont;
    String headingEnd="GAME OVER";
    String subtitleEnd="You killed enemies";
    String subtitle2End="Press ENTER to restart";
    GamePanel() {
        titleFont = new Font("Arial", Font.BOLD, 35);
        startFont = new Font("Arial", Font.PLAIN, 15);
        instructionsFont = new Font("Arial", Font.PLAIN, 15);
        endFont = new Font("Arial", Font.BOLD, 25);
        frameDraw = new Timer(1000 / 60, this);
        frameDraw.start();
    }

    void updateMenuState() {
    }

    void updateGameState() {

    }

    void updateEndState() {

    }

    void drawMenuState(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.CYAN);
        g.drawString("LEAGUE INVADERS", 100, 200);
        g.setFont(startFont);
        g.setColor(Color.YELLOW);
        g.drawString("Press ENTER to start", 170, 320);
        g.setFont(instructionsFont);
        g.setColor(Color.RED);
        g.drawString("Press SPACE for instructions", 150, 400);
    }

    void drawGameState(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

    }

    void drawEndState(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        g.setColor(Color.BLACK);


        g.drawString(headingEnd, getStart(g,headingEnd), 200);


        g.drawString(subtitleEnd, getStart(g,subtitleEnd), 300);

        g.drawString(subtitle2End, getStart(g,subtitle2End), 400);
    }

    private int getStart(Graphics g, String headingEnd) {
        Font myFont = new Font("Serif", Font.BOLD, 36);
        g.setFont(myFont);
        int stringLen = (int) g.getFontMetrics().getStringBounds(headingEnd, g).getWidth();
        return (LeagueInvaders.WIDTH - stringLen)/2;
    }

    @Override
    public void paintComponent(Graphics g) {

        if (currentState == MENU) {
            drawMenuState(g);
        } else if (currentState == GAME) {
            drawGameState(g);
        } else if (currentState == END) {
            drawEndState(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (currentState == MENU) {
            updateMenuState();
        } else if (currentState == GAME) {
            updateGameState();
        } else if (currentState == END) {
            updateEndState();
        }
        //System.out.println("action");
        this.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            if (currentState == END) {
                currentState = MENU;
            } else {
                currentState++;
            }
        } else if ((currentState == MENU) || (currentState == END)) {
            // checking if current state is in MENU or END state because we want the code to
            //only check the arrow keys while in the GAME state

        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            System.out.println("DOWN");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("RIGHT");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("LEFT");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

