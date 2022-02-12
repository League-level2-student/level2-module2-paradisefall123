package LeagueInvaders;

import java.awt.*;

public class Alien extends GameObject {


    Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed=1;
        if (m_needImage) {
            loadImage ("alien.png");
        }
    }

    void update(){

        m_y+=m_speed;
    }


}

