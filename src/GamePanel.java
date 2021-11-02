import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {
    final int MENU = 0;
    final int GAME = 1;
    final int END = 2;
    int currentState = MENU;
    Font titleFont;

    void Font (String typeFont, Font style, int size){
        titleFont = new Font("Arial", Font.PLAIN, 100);

    }
    void updateMenuState() {
    }

    void updateGameState(){

    }

    void updateEndState(){

    }
    void drawMenuState(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
        g.setFont(titleFont);
        g.setColor(Color.YELLOW);
        g.drawString("League Invaders", 150, 150);
    }

    void  drawGameState(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    }

    void drawEndState(Graphics g)  {
        g.setColor(Color.RED);
        g.fillRect(0, 0, LeagueInvaders.WIDTH, LeagueInvaders.HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g) {

        if(currentState == MENU){
            drawMenuState(g);
        }else if(currentState == GAME){
            drawGameState(g);
        }else if(currentState == END){
            drawEndState(g);
        }
    }

}

