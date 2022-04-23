package LeagueInvaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Alien extends GameObject {
    public static BufferedImage image;
    public static boolean needImage = true;
    public static boolean gotImage = false;

    Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed = 1;
        if (m_needImage) {
            loadImage("alien.png");
        }
    }

    void update() {
        m_y += m_speed;
        super.update();

    }

    void draw(Graphics g){
        if (gotImage) {
            g.drawImage(image, m_x, m_y, m_width, m_height, null);
        } else {
            g.setColor(Color.BLUE);
            g.fillRect(m_x, m_y, m_width, m_height);
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


}

