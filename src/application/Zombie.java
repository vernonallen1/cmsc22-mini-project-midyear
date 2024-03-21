package application;

import java.util.Random;

import javafx.scene.image.Image;

public class Zombie extends Sprite{
	public static final int MAX_ZOMBIE_SPEED = 5;
	public static final int MAX_ADDITIONAL_ZOMBIES = 3;
	public static final int ZOMBIE_SPAWN_INTERVAL = 5;
	public final static Image ZOMBIE_IMAGE = new Image("file:src/images/normal_zombie.png",Zombie.ZOMBIE_WIDTH,Zombie.ZOMBIE_WIDTH,false,false);
	public final static Image GRAVE_IMAGE = new Image("file:src/images/grave.png",Zombie.ZOMBIE_WIDTH,Zombie.ZOMBIE_WIDTH,false,false);
	public final static int ZOMBIE_WIDTH=50;
	public final static int MAX_DAMAGE = 40;
	public final static int MIN_DAMAGE = 30;
	private boolean alive;
	//attribute that will determine if a fish will initially move to the right
	private boolean moveRight;
	private int speed;
	private int damage;
	
	
	Zombie(int x, int y){
		super(x,y);
		this.alive = true;
		this.loadImage(Zombie.ZOMBIE_IMAGE);
		Random r = new Random();
		this.speed = r.nextInt(MAX_ZOMBIE_SPEED) + 1;
		this.moveRight = true;
		this.damage = r.nextInt(1 + Zombie.MAX_DAMAGE - Zombie.MIN_DAMAGE) + Zombie.MIN_DAMAGE;
	}
	
	//method that changes the x position of the fish
	void move(){
		if (moveRight && (this.getX() < GameStage.WINDOW_WIDTH-50)) {
			this.setDX(this.speed);
		} else if (this.getX() >= GameStage.WINDOW_WIDTH-50) {
			moveRight = false;
			this.setDX(-this.speed);
		} else if (!moveRight && this.getX() > 0) {
			this.setDX(-this.speed);
		} else if (this.getX() <= 0) {
			this.moveRight = true;
			this.setDX(this.speed);
		}
	}
	
	public void hitsZombie(Hunter hunter) {
		if (!hunter.getOnZombie()) {
			hunter.setStrength(-this.getDamage());
			System.out.println("Hunter was hit");
			System.out.println("Hunter's health: " + hunter.getStrength());
		}
	}
	
	//getter
	public boolean isAlive() {
		return this.alive;
	}
	
	public void setAlive(Boolean isAlive) {
		this.alive = isAlive;
	}
	
	public int getDamage() {
		return this.damage;
	}
	
	protected void setDamage(int damage) {
		this.damage = damage;
	}
	
	protected void setSpeed(int speed) {
		this.speed = speed;
	}
}
