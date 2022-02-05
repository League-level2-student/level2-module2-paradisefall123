package LeagueInvaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Rocketship extends GameObject {


    Rocketship(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed=11;
        if (m_needImage) {
            loadImage ("rocket.png");
        }
    }

    public void up() {
        m_y-=m_speed;
    }
    public void down() {
        m_y+=m_speed;
    }
    public void right() {

        m_x+=m_speed;
    }
    public void left() {

        m_x-=m_speed;
    }

    public Projectile createProjectile() {

        return new Projectile(m_x+m_width/2, m_y, 10, 10);
    }


}
