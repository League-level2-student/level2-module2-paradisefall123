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
        super.update();
        m_y=m_y-m_speed;
      //  m_y-=m_speed;
    }


}

