package LeagueInvaders;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager implements ActionListener {
    Rocketship m_rocketship;
    ArrayList<Projectile> m_projectiles = new ArrayList<Projectile>();
    ArrayList<Alien> m_aliens = new ArrayList<Alien>();
    Random random = new Random();
    int score=0;

    public int getScore(){
        return  score;
    }

    public void setScore(int score){
        this.score=score;

    }
    ObjectManager(Rocketship rocketship) {
        //Want to reuse rocketship
        m_rocketship = rocketship;
    }

    void addProjectile(Projectile projectile) {

        m_projectiles.add(projectile);
    }

    void addAlien() {
        Alien random_alien = new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50);
        m_aliens.add(random_alien);
        //System.out.println("Alien added " + random_alien.m_x);
    }

    void update() {
        for (int i = 0; i < m_aliens.size(); i++) {
            m_aliens.get(i).update();
            if (m_aliens.get(i).m_y < 0 || m_aliens.get(i).m_y > LeagueInvaders.HEIGHT) {
                m_aliens.get(i).m_isActive = false;
            }

        }
        for (int i = 0; i < m_projectiles.size(); i++) {
            m_projectiles.get(i).update();
            if (m_projectiles.get(i).m_y < 0 || m_projectiles.get(i).m_y > LeagueInvaders.HEIGHT) {
                m_projectiles.get(i).m_isActive = false;
            }
        }
        checkCollision();
        purgeObjects();
    }

    void draw(Graphics e) {
        //calling rocketship
        if(m_rocketship.m_isActive){
            m_rocketship.draw(e);
        }


        //System.out.println("DRAW ALIENS AND STUFF");
        //Going through the aliens and drawing them
        for (int i = 0; i < m_aliens.size(); i++) {
            m_aliens.get(i).draw(e);

        }
        // going through the projectiles and drawing them
        for (int i = 0; i < m_projectiles.size(); i++) {
            m_projectiles.get(i).draw(e);
        }
    }

    void purgeObjects() {
        for (int z = 0; z < m_aliens.size(); z++) {
            if (!m_aliens.get(z).m_isActive) {
                m_aliens.remove(m_aliens.get(z));
            }
        }

        for (int i = 0; i < m_projectiles.size(); i++) {
            if (!m_projectiles.get(i).m_isActive) {
                m_projectiles.remove(m_projectiles.get(i));
            }
        }

        if(!m_rocketship.m_isActive){

        }
    }

    void checkCollision() {
        checkAlienCollision();
        checkProjectileCollision();
    }



    private void checkAlienCollision() {
        for (int i = 0; i < m_aliens.size(); i++) {
          //  System.out.println("\n"+i);
            System.out.println("rocketship x: "+m_rocketship.m_x+" Aliens x: "+m_aliens.get(i).m_x);
            System.out.println("rocketship y: "+m_rocketship.m_y+" Aliens y: "+m_aliens.get(i).m_y);

            if (m_rocketship.collisionBox.intersects(m_aliens.get(i).collisionBox)) {
                m_aliens.get(i).m_isActive = false;
                m_rocketship.m_isActive = false;

                System.out.println("alien collision and i: "+i);
            }
        }
    }


    private void checkProjectileCollision() {
        for (int i = 0; i < m_projectiles.size(); i++) {
            for (int z = 0; z < m_aliens.size(); z++) {
                if (m_projectiles.get(i).collisionBox.intersects(m_aliens.get(z).collisionBox)) {
                    m_aliens.get(z).m_isActive = false;
                    m_projectiles.get(i).m_isActive = false;
                    score++;

                }
            }
        }
    }

    public void resetState(){
        m_aliens.clear();
        m_projectiles.clear();
        m_rocketship.m_x= LeagueInvaders.WIDTH / 2;
        m_rocketship.m_y= LeagueInvaders.HEIGHT - 120;
        m_rocketship.m_isActive = true;
        setScore(0);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //To add an alien everytime timer expires
       // if(m_aliens.size()==0){
            addAlien();
        //}
    }
}


