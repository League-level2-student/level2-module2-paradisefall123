package LeagueInvaders;

import java.awt.*;

public class Projectile extends GameObject {
    Projectile(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed=1;
        if (m_needImage) {
            loadImage ("bullet.png");
        }
    }

    void update(){

        m_y-=m_speed;
    }

    void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillRect(m_x, m_y, m_width, m_height);
    }
}

