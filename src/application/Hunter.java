package application;

import powerups.*;
import java.util.ArrayList;

import javafx.scene.image.Image;



public class Hunter extends Sprite{
	private String name;
	private int strength;
	private int speed;
	private boolean alive;
	private boolean isImmortal;
	private ArrayList<PowerUp> activePowerUps;
	private GameStage stage;
	private boolean onZombie;
	
	private ArrayList<Bullet> bullets;
	public final static Image ZOMBIE_HUNTER_IMAGE = new Image("file:src/images/zombie_hunter.png",Hunter.HUNTER_WIDTH,Hunter.HUNTER_WIDTH,false,false);
	public final static Image HUNTER_WITH_SHIELD = new Image("file:src/images/hunter_with_shield.png",Hunter.HUNTER_WIDTH,Hunter.HUNTER_WIDTH,false,false);
	private final static int HUNTER_WIDTH = 50;
	public final static int INITIAL_SPEED = 3;

	public Hunter(String name, int x, int y, GameStage stage){
		super(x,y);
		this.name = name;
		this.stage = stage;
		this.strength = this.stage.getStatusBar().getStrengthMonitor().getStrength();
		this.speed = Hunter.INITIAL_SPEED;
		this.alive = true;
		this.isImmortal = false;
		this.onZombie = false;
		this.bullets = new ArrayList<Bullet>();
		this.loadImage(Hunter.ZOMBIE_HUNTER_IMAGE);
		this.activePowerUps = new ArrayList<PowerUp>();
	}
	
	public void setImage() {
		if (this.isImmortal) {
			this.loadImage(HUNTER_WITH_SHIELD);
		} else {
			this.loadImage(ZOMBIE_HUNTER_IMAGE);
		}
	}

	public boolean isAlive(){
		if(this.alive) return true;
		return false;
	} 
	public String getName(){
		return this.name;
	}
	
	public int getStrength() {
		return this.strength;
	}

	public void die(){
    	this.alive = false;
    }

	//method that will get the bullets 'shot' by the ship
	public ArrayList<Bullet> getBullets(){
		return this.bullets;
	}
	
	//method called if spacebar is pressed
	public void shoot(){
		//compute for the x and y initial position of the bullet
		int x = (int) (this.x + this.width+20);
		int y = (int) (this.y + this.height/2);

		Bullet bullet = new Bullet(x, y);
		this.bullets.add(bullet);
    }
	
	public boolean collectPowerUp(PowerUp p, int secondsSpent) {
		if (p instanceof Ammo) {
			Ammo pearl = (Ammo) p;
			if (pearl.collidesWith(this)) {
				applyToHunter(p, secondsSpent);
				this.stage.getStatusBar().getActivePowerUps().setAmmoVisibility(true);
				return true;
			}
		} else if (p instanceof Shield) {
			Shield star = (Shield) p;
			if (star.collidesWith(this)) {
				applyToHunter(p, secondsSpent);
				this.stage.getStatusBar().getActivePowerUps().setShieldVisibility(true);
				return true;
			}
		} else if (p instanceof Shoes) {
			Shoes fisherman = (Shoes) p;
			if (fisherman.collidesWith(this)) {
				applyToHunter(p, secondsSpent);
				this.stage.getStatusBar().getActivePowerUps().setShoesVisibility(true);
				return true;
			}
		}
		return false;
	}
	
	public void removePowerUpEffect(int secondsSpent) {
		for (int i = 0; i < this.activePowerUps.size(); i++) {
			PowerUp powerup = this.activePowerUps.get(i);
			if (secondsSpent >= powerup.getTimeCollected() + powerup.getEffectDuration() && !(powerup instanceof Ammo)) {
				powerup.removeEffect(this);
				if (powerup instanceof Shield) {
					this.stage.getStatusBar().getActivePowerUps().setShieldVisibility(false);
				} else if (powerup instanceof Shoes) {
					this.stage.getStatusBar().getActivePowerUps().setShoesVisibility(false);
				}
				this.activePowerUps.remove(powerup);
			}
		}	
	}
	
	private void applyToHunter(PowerUp p, int secondsSpent) {
		this.activePowerUps.add(p);
		p.applyEffect(this);
		p.setTimeCollected(secondsSpent);
	}
	
	//method called if up/down/left/right arrow key is pressed.
	public void move() {

		if (this.x < GameStage.WINDOW_WIDTH && this.x > 0) {
			this.x += this.dx; 
		}
		
		if (this.y < GameStage.WINDOW_HEIGHT && this.y > 0) {
			this.y += this.dy;
		}
	}
	
	public void setStrength(int addedStrength) {
		this.strength += addedStrength;
		this.stage.getStatusBar().getStrengthMonitor().setStrength(addedStrength);
	}

	public void increaseSpeed(int speedIncrease) {
		this.speed += speedIncrease;
	}

	public int getSpeed() {
		return this.speed;
	}
	
	public void removeExtraSpeed(int speedIncrease) {
		this.speed -= speedIncrease;
	}
	
	public boolean getImmortality() {
		return this.isImmortal;
	}
	
	public void setImmortality(boolean isImmortal) {
		this.isImmortal = isImmortal;
	}
	
	public boolean getOnZombie() {
		return this.onZombie;
	}
	
	public void setOnZombie(boolean isOnZombie) {
		this.onZombie =  isOnZombie;
	}
	
	public int getDamage() {
		return this.strength;
	}
}
