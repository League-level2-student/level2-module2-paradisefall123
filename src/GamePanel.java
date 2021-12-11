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
    String headingStart="LEAGUE INVADERS";
    String subtitleStart1="Press ENTER to start";
    String subtitleStart2="Press SPACE for instructions";
    Rocketship m_billy=new Rocketship(LeagueInvaders.WIDTH/2,LeagueInvaders.HEIGHT-120,50,50);
    GamePanel() {
        titleFont = new Font("Arial", Font.BOLD, 35);
        startFont = new Font("Arial", Font.PLAIN, 15);
        instructionsFont = new Font("Arial", Font.PLAIN, 15);
        endFont = new Font("Arial", Font.BOLD, 25);
        frameDraw = new Timer(1000 / 60, this);
        frameDraw.start();
    }

    void updateMenuState() {
        currentState=MENU;
    }

    void updateGameState() {
        currentState=GAME;
    }

    void updateEndState() {
        currentState=END;
    }

    void drawMenuState(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);


        g.setColor(Color.CYAN);
        g.drawString(headingStart, getStart(g,headingStart), 200);

        g.setColor(Color.YELLOW);
        g.drawString(subtitleStart1, getStart(g,subtitleStart1), 300);

        g.setColor(Color.RED);
        g.drawString(subtitleStart2, getStart(g,subtitleStart2), 400);
    }

    void drawGameState(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        m_billy.draw(g);
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
       // System.out.println("action");
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
            //System.out.println("UP");
            m_billy.up();
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN");
            m_billy.down();
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
           // System.out.println("RIGHT");
            m_billy.right();
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("LEFT");
            m_billy.left();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

