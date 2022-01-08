import java.awt.*;

public class Alien extends GameObject {


    Alien(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed=1;
    }

    void update(){
    m_y+=m_speed;
    }

    void draw(Graphics g){
        g.setColor(Color.YELLOW);
        g.fillRect(m_x, m_y, m_width, m_height);
    }
}

