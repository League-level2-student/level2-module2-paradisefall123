import java.awt.*;

public class Rocketship extends GameObject {

    Rocketship(int x, int y, int width, int height) {
        super(x, y, width, height);
        m_speed=11;
    }
    void draw(Graphics g){
        g.setColor(Color.MAGENTA);
       // System.out.println("Value x: "+m_x+" Value y: "+m_y+" Value w: "+m_width+" Value h: "+m_height);
        g.fillRect(m_x, m_y, m_width, m_height);
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
}
