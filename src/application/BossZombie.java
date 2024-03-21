package application;

import java.util.ArrayList;
import java.util.Random;

import javafx.scene.image.Image;

public class BossZombie extends Zombie{

	public final static Image ZOMBIE_BOSS_IMAGE = new Image("file:src/images/boss_zombie.png",Zombie.ZOMBIE_WIDTH*2,Zombie.ZOMBIE_WIDTH*2,false,false);
	public final static int DAMAGE = 50;
	public final static int SPAWN_TIME = 30;
	public final static int INITIAL_HEALTH = 3000;
	
	private int health;
	private ArrayList<ZombieBullet> bullets;
	private int timeBulletShot;
	
	BossZombie(int x, int y) {
		super(x, y);
		this.health = BossZombie.INITIAL_HEALTH;
		this.timeBulletShot = 0;
		this.bullets = new ArrayList<ZombieBullet>();
		this.loadImage(ZOMBIE_BOSS_IMAGE);
		this.setDamage(DAMAGE);
		this.visible = false;
		Random r = new Random();
		this.setSpeed(r.nextInt(Zombie.MAX_ZOMBIE_SPEED) + 1);
	}
	
	public void shoot() {
		Random r = new Random();
		int x = (int) (this.x);
		int y = (int) (this.y + r.nextInt(90));
		ZombieBullet b = new ZombieBullet(x, y);
		this.bullets.add(b);
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void deductHealth(int healthReduction) {
		this.health -= healthReduction;
	}
	
	public void removeBullet(ZombieBullet b) {
		this.bullets.remove(b);
	}
	
	public ArrayList<ZombieBullet> getBulletsList(){
		return this.bullets;
	}
	
	public int getTimeBulletShot() {
		return this.timeBulletShot;
	}
	
	public void setTimeBulletShot(int time) {
		this.timeBulletShot = time;
	}
}
