package LeagueInvaders;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class GameObject {
    public BufferedImage m_image;
    public boolean m_needImage = true;
    public boolean m_gotImage = false;
    Rectangle collisionBox;
    int m_x;
    int m_y;
    int m_width;
    int m_height;
    int m_speed = 0;
    boolean m_isActive = true;

    GameObject(int x, int y, int width, int height) {
        m_x = x;
        m_y = y;
        m_width = width;
        m_height = height;
        collisionBox = new Rectangle(x, y, width, height);
    }

    void update() {
        collisionBox.setBounds(m_x, m_y, m_width, m_height);
    }

    void loadImage(String imageFile) {
        if (m_needImage) {
            try {
                m_image = ImageIO.read(this.getClass().getResourceAsStream(imageFile));
                m_gotImage = true;
            } catch (Exception e) {

            }
            m_needImage = false;
        }
    }

    void draw(Graphics g) {
        if (m_gotImage) {
            g.drawImage(m_image, m_x, m_y, m_width, m_height, null);
        } else {
            g.setColor(Color.MAGENTA);
            g.fillRect(m_x, m_y, m_width, m_height);
        }
    }
}
