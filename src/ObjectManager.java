import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
    Rocketship obm_ross;
    ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    ArrayList<Alien> aliens = new ArrayList<Alien>();
    Random random = new Random();

    ObjectManager(Rocketship obm_ross) {
        obm_ross = new Rocketship(LeagueInvaders.WIDTH - 50, LeagueInvaders.HEIGHT - 120, 50, 50);
    }

    void addProjectile(Projectile object) {
        projectiles.add(new Projectile(10, 10, 10, 10));
    }

    void addAlien(Alien object) {
        aliens.add(new Alien(random.nextInt(LeagueInvaders.WIDTH), 0, 50, 50));

    }

    void update() {
        for (int i = 0; i < aliens.size(); i++) {
            aliens.get(i).update();
            if(aliens.get(i).m_y > 0 || aliens.get(i).m_y < LeagueInvaders.HEIGHT){
            aliens.get(i).m_isActive=false;
            }

        }
        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
            if(projectiles.get(i).m_y > 0 || projectiles.get(i).m_y < LeagueInvaders.HEIGHT){
                projectiles.get(i).m_isActive=false;
            }
        }
    }

    void draw(Graphics e){
        obm_ross.draw(e);

        for (int i = 0; i < aliens.size(); i++) {
        aliens.get(i).draw(e);
        }

        for (int i = 0; i < projectiles.size(); i++) {
        aliens.get(i).draw(e);
        }
    }

    void purgeObjects(){
        for (int i = 0; i < aliens.size(); i++) {
            if(aliens.get(i).m_isActive=false){
                aliens.remove(aliens.get(i));
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if(projectiles.get(i).m_isActive=false){
                projectiles.remove(projectiles.get(i));
            }
        }
    }
}


