import javax.swing.*;

public class LeagueInvaders {
    JFrame frame;
    GamePanel gpanel;
    public static final int WIDTH = 500;
    public static final int HEIGHT = 800;

    public static void main(String[] args) {
        LeagueInvaders setUpFrame = new LeagueInvaders();
        setUpFrame.setup();
    }

    LeagueInvaders() {
        frame = new JFrame();
        gpanel=new GamePanel();
    }

    void setup() {
        frame.add(gpanel);
        frame.setVisible(true);
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
