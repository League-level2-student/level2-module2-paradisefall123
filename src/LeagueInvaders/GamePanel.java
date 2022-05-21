package LeagueInvaders;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements ActionListener, KeyListener {
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    final int INSTRUCTION = 3;
    int currentState = MENU;
    Timer frameDraw;
    Timer timer_alienSpawn;
    Font titleFont;
    Font startFont;
    Font instructionsFont;
    Font endFont;
    String headingEnd = "GAME OVER";
    String subtitleEndPrefix = "You killed ";
    String subtitleEndSuffix = " enemies";
    String subtitle2End = "Press ENTER to restart";
    String headingStart = "LEAGUE INVADERS";
    String subtitleStart1 = "Press ENTER to start";
    String subtitleStart2 = "Press I for instructions";
    String subtitleIns1 = "Use the arrow keys to move Rocketship";
    String subtitleIns2 = "Press the space bar to attack the aliens";
    String subtitleIns3 = "Avoid getting hit by the alien or it is GAME OVER";
    String subtitleIns4 = "Have fun! Press ENTER to begin!";
    Rocketship rocketship = new Rocketship(LeagueInvaders.WIDTH / 2, LeagueInvaders.HEIGHT - 120, 50, 50);
    ObjectManager man_object = new ObjectManager(rocketship);
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;

    GamePanel() {
        titleFont = new Font("Arial", Font.BOLD, 35);
        startFont = new Font("Arial", Font.PLAIN, 15);
        instructionsFont = new Font("Arial", Font.PLAIN, 12);
        endFont = new Font("Arial", Font.BOLD, 25);
        frameDraw = new Timer(1000 / 60, this);
        if (needImage) {
            loadImage("space.png");
        }
        frameDraw.start();

    }

    void updateMenuState() {

        currentState = MENU;
    }

    void updateGameState() {
        currentState = GAME;
        man_object.update();
        if (!rocketship.m_isActive) {
            updateEndState();
        }
    }

    void updateEndState() {

        currentState = END;
    }

    void updateInstructionState() {
        currentState = INSTRUCTION;
    }

    void drawMenuState(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);


        g.setColor(Color.CYAN);
        g.drawString(headingStart, getStart(g, headingStart), 200);

        g.setColor(Color.YELLOW);
        g.drawString(subtitleStart1, getStart(g, subtitleStart1), 300);

        g.setColor(Color.RED);
        g.drawString(subtitleStart2, getStart(g, subtitleStart2), 400);
    }

    void drawInstructionState(Graphics g) {
        g.setColor(Color.PINK);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);

        g.setColor(Color.BLACK);

        g.drawString(subtitleIns1, getInstructions(g, subtitleIns1), 200);

        g.drawString(subtitleIns2, getInstructions(g, subtitleIns2), 300);

        g.drawString(subtitleIns3, getInstructions(g, subtitleIns3), 400);

        g.drawString(subtitleIns4, getInstructions(g, subtitleIns4), 500);
    }

    void drawGameState(Graphics g) {
        String scoreboard = " Score: " + man_object.getScore();
        if (gotImage) {
            g.drawImage(image, 0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT, null);
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        }
        g.setColor(Color.white);
        g.drawRect(20, 20, 60, 30);
        g.drawString(scoreboard, 20, 40);
        //instead of calling rocketship again we called the object manager to do all the drawing
        // draw depends on update but update does not depend on draw; change the state to use the changed state to draw
        man_object.update();
        man_object.draw(g);


    }


    void drawEndState(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        g.setColor(Color.BLACK);


        g.drawString(headingEnd, getStart(g, headingEnd), 200);


        String subtitle = subtitleEndPrefix + man_object.score+ subtitleEndSuffix;
        g.drawString(subtitle, getStart(g, subtitle), 300);

        g.drawString(subtitle2End, getStart(g, subtitle2End), 400);
    }

    private int getStart(Graphics g, String headingEnd) {
        Font myFont = new Font("Serif", Font.BOLD, 36);
        g.setFont(myFont);
        int stringLen = (int) g.getFontMetrics().getStringBounds(headingEnd, g).getWidth();
        return (LeagueInvaders.WIDTH - stringLen) / 2;
    }
    private int getInstructions(Graphics g, String subtitleIns) {
        Font myFont = new Font("Serif", Font.BOLD, 22);
        g.setFont(myFont);
        int stringLen = (int) g.getFontMetrics().getStringBounds(subtitleIns, g).getWidth();
        return (LeagueInvaders.WIDTH - stringLen) / 2;
    }


    void restart() {
        man_object.resetState();
        startGame();

    }

    @Override
    public void paintComponent(Graphics g) {
        // paint component is a native method and always getting called; from here we can call draw methods
        if (currentState == MENU) {
            drawMenuState(g);
        } else if (currentState == GAME) {
            drawGameState(g);
        } else if (currentState == END) {
            drawEndState(g);
        } else if (currentState == INSTRUCTION) {
            drawInstructionState(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //System.out.println("*** action performed *** "+e.getActionCommand()+"  "+e.getSource());
        // This method is called when the timer executes
        if (currentState == MENU) {
            updateMenuState();
        } else if (currentState == GAME) {
            updateGameState();
        } else if (currentState == END) {
            updateEndState();
        } else if (currentState == INSTRUCTION) {
            updateInstructionState();
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
                updateGameState();
                startGame();


            }

            //collision box is moving
        } else if (e.getKeyCode() == KeyEvent.VK_I) {
            if (currentState == MENU) {
                currentState = INSTRUCTION;
            }

        } else if (currentState == END) {
            stopGame();
            restart();
            //creatingRocket();
        } else if ((currentState == MENU) || (currentState == END) || (currentState == INSTRUCTION)) {
            // checking if current state is in MENU or END state because we want the code to
            //only check the arrow keys while in the GAME state

        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // System.out.println("SPACE BAR");
            man_object.addProjectile(rocketship.createProjectile());
        } else if (e.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("UP");
            if (rocketship.m_y > 3) {
                rocketship.up();
            }

        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("DOWN");
            if (rocketship.m_y < LeagueInvaders.HEIGHT - 115) {
                //System.out.println(m_billy.m_y);
                rocketship.down();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            // System.out.println("RIGHT");
            if (rocketship.m_x < LeagueInvaders.WIDTH - 55) {
                rocketship.right();
            }
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("LEFT");
            if (rocketship.m_x > 1) {
                rocketship.left();
            }
        }
    }

    void loadImage(String imageFile) {
        if (needImage) {
            try {
                image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
                gotImage = true;
            } catch (Exception e) {

            }
            needImage = false;
        }
    }

    void startGame() {
        timer_alienSpawn = new Timer(1000, man_object);
        timer_alienSpawn.start();

    }

    void stopGame() {

        timer_alienSpawn.stop();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}

