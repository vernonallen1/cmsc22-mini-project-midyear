package application;

import powerups.*;
import statusbar.StatusBar;

import java.util.ArrayList;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;

import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/*
 * The GameTimer is a subclass of the AnimationTimer class. It must override the handle method. 
 */
 
public class GameTimer extends AnimationTimer{
	private GameStage stage;
	private GraphicsContext gc;
	private Scene theScene;
	private Hunter myHunter;
	private ArrayList<Zombie> zombies;
	private ArrayList<PowerUp> powerUpsOnScreen;
	private BossZombie bossZombie;
	private int addedExtraZombies;
	private long startTime;
	private int endOfVisibility;
	private long timeSpent;
	private int secondsSpent;
	
	public static final int MAX_NUM_FISHES = 7;
	public static final int END_GAME = 60;
	
	
	GameTimer(GraphicsContext gc, Scene theScene, GameStage stage){
		this.stage = stage;
		this.gc = gc;
		this.theScene = theScene;
		this.myHunter = new Hunter("Going merry",100,100, stage);
		//instantiate the ArrayList of Fish
		this.zombies = new ArrayList<Zombie>();
		this.addedExtraZombies = 0;
		
		//call the spawnFishes method
		this.spawnZombies();
		this.spawnBossZombie();
		
		//call method to handle mouse click event
		this.handleKeyPressEvent();
		
		this.powerUpsOnScreen = new ArrayList<PowerUp>();
		this.startTime = System.currentTimeMillis();
	}

	@Override
	public void handle(long currentNanoTime) {
		this.gc.clearRect(0, 0, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		long now = System.currentTimeMillis();
		this.timeSpent = now - startTime;
		this.secondsSpent = (int) Math.floor(timeSpent/1000);
		this.stage.getStatusBar().getTime().incrementSeconds(secondsSpent);
		
		addZombies();
		
		shootZombieBullets();
		
		moveZombies();
		moveBullets();
		moveBossZombie();
		moveZombieBullets();
		
		this.myHunter.move(); //changes hunter's location
		this.myHunter.setImage(); //toggles hunter's appearance
		this.myHunter.render(this.gc); //render the ship
		this.myHunter.removePowerUpEffect(secondsSpent); //removes effect
		
		renderZombies();
		renderBossZombie();
		renderBullets();
		renderZombieBullets();
		
		renderPowerUp();
		//remove power up from the screen
		removePowerUp();
		
		//remove powerup effect from the ship
		
		//stops animation
		stopAnimation();
	}	
	
	private void removePowerUp() {
		for (int i = 0; i < this.powerUpsOnScreen.size(); i++) {
			PowerUp powerup = this.powerUpsOnScreen.get(i);
			if (this.myHunter.collectPowerUp(powerup, secondsSpent)) {
				this.powerUpsOnScreen.remove(powerup);
			}
		}
	}
	
	//method that will render/draw the fishes to the canvas
	private void renderZombies() {
		for (Zombie f : this.zombies){
			f.render(this.gc);
		}
	}
	
	private void renderBossZombie() {
		if (secondsSpent >= BossZombie.SPAWN_TIME && this.bossZombie.isAlive()) {
			this.bossZombie.render(gc);
			this.bossZombie.visible = true;
		}
	}
	
	//method that will render/draw the bullets to the canvas
	private void renderBullets() {

		for (Bullet bullet : myHunter.getBullets()) {
			bullet.render(this.gc);
		}
	}
	
	private void renderZombieBullets() {
		if (this.bossZombie.getBulletsList().size() != 0) {
			for (ZombieBullet zbullet:this.bossZombie.getBulletsList()) {
				zbullet.render(this.gc);
			}
		}
	}
	
	private void renderPowerUp() {
		if (secondsSpent % PowerUp.SPAWN_TIME == 0 && secondsSpent >= PowerUp.SPAWN_TIME && this.powerUpsOnScreen.size() == 0) {
			spawnPowerUps();
			this.endOfVisibility = secondsSpent + PowerUp.VISIBLE_DURATION;
		} else if (this.powerUpsOnScreen.size() > 0 && this.endOfVisibility == secondsSpent) {
			for (int i = 0; i < this.powerUpsOnScreen.size(); i++) {
				PowerUp powerup = this.powerUpsOnScreen.get(i);
				this.powerUpsOnScreen.remove(powerup);
			}
		} else if (this.powerUpsOnScreen.size() > 0) {
			for  (PowerUp powerup : this.powerUpsOnScreen) {
				if (powerup instanceof Ammo) {
					Ammo ammo = (Ammo) powerup;
					ammo.render(this.gc);
				} else if (powerup instanceof Shield) {
					Shield shield = (Shield) powerup;
					shield.render(this.gc);
				} else if (powerup instanceof Shoes) {
					Shoes shoes = (Shoes) powerup;
					shoes.render(this.gc);
				}
			}
		}
	}
	
	//method that will spawn/instantiate three fishes at a random x,y location
	private void spawnZombies(){
		Random r = new Random();
		for(int i=0;i<GameTimer.MAX_NUM_FISHES;i++){
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2) + GameStage.WINDOW_WIDTH/2;
			int y = r.nextInt(GameStage.WINDOW_HEIGHT - Zombie.ZOMBIE_WIDTH*2) + StatusBar.STATUS_BAR_HEIGHT;

			Zombie newZombie = new Zombie(x, y);
			this.zombies.add(newZombie);	
		}
	}
	
	private void spawnBossZombie() {
		Random r = new Random();
		int x = r.nextInt(GameStage.WINDOW_WIDTH/2) + GameStage.WINDOW_WIDTH/2 - 50;
		int y = r.nextInt(GameStage.WINDOW_HEIGHT - Zombie.ZOMBIE_WIDTH*3) + StatusBar.STATUS_BAR_HEIGHT;
		this.bossZombie = new BossZombie(x,y);
	}
	
	private void addZombies() {
		if (secondsSpent > 0 && secondsSpent % Zombie.ZOMBIE_SPAWN_INTERVAL == 0 && Math.floor(secondsSpent/Zombie.ZOMBIE_SPAWN_INTERVAL) <= Zombie.MAX_ADDITIONAL_ZOMBIES && Math.floor(secondsSpent/Zombie.ZOMBIE_SPAWN_INTERVAL)-1 == this.addedExtraZombies) {
			Random r = new Random();
			int x = r.nextInt(GameStage.WINDOW_WIDTH/2) + GameStage.WINDOW_WIDTH/2;
			int y = r.nextInt(GameStage.WINDOW_HEIGHT - Zombie.ZOMBIE_WIDTH*2) + StatusBar.STATUS_BAR_HEIGHT;
			
			Zombie newZombie = new Zombie(x, y);
			this.zombies.add(newZombie);
			this.addedExtraZombies++;
		}
	}
	
	private void spawnPowerUps() {
		spawnAmmo();
		spawnShield();
		spawnShoes();
	}
	
	private void spawnAmmo() {
		Random r = new Random();
		int xPos = r.nextInt(GameStage.WINDOW_WIDTH/2);
		int yPos = r.nextInt(GameStage.WINDOW_HEIGHT - 70) + 50;
		Ammo ammo = new Ammo(xPos, yPos);
		this.powerUpsOnScreen.add(ammo);
	}
	
	private void spawnShield() {
		Random r = new Random();
		int xPos = r.nextInt(GameStage.WINDOW_WIDTH/2);
		int yPos = r.nextInt(GameStage.WINDOW_HEIGHT - 70) + 50;
		Shield shield = new Shield(xPos, yPos);
		this.powerUpsOnScreen.add(shield);
	}
	
	private void spawnShoes() {
		Random r = new Random();
		int xPos = r.nextInt(GameStage.WINDOW_WIDTH/2);
		int yPos = r.nextInt(GameStage.WINDOW_HEIGHT -70) + 50;
		Shoes shoes = new Shoes(xPos, yPos);
		this.powerUpsOnScreen.add(shoes);
	}
	
	//method that will move the bullets shot by a ship
	private void moveBullets(){
		//create a local arraylist of Bullets for the bullets 'shot' by the ship
		ArrayList<Bullet> bList = this.myHunter.getBullets();
		
		//Loop through the bullet list and check whether a bullet is still visible.
		for(int i = 0; i < bList.size(); i++){
			Bullet b = bList.get(i);

			if (b.visible) {
				b.move();
				hitsZombie(b);
			} else {
				myHunter.getBullets().remove(b);
			}
		}
	}
	
	private void moveZombieBullets() {
		for (int i = 0; i < this.bossZombie.getBulletsList().size(); i++) {
			ZombieBullet zb = this.bossZombie.getBulletsList().get(i);
			zb.move();
			this.hitsHunter(zb);
			
		}
	}
	
	private void shootZombieBullets() {
		if (secondsSpent > BossZombie.SPAWN_TIME) {
			if (this.bossZombie.isAlive() && this.secondsSpent != this.bossZombie.getTimeBulletShot()) { //one bullet every second
				this.bossZombie.shoot();
				this.bossZombie.setTimeBulletShot(secondsSpent);
			}
		}
	}
	
	//method that will move the fishes 
	private void moveZombies(){
		//Loop through the fishes arraylist
		for(int i = 0; i < this.zombies.size(); i++){
			Zombie z = this.zombies.get(i);

			if (z.isAlive()) {
				z.move();
				if (z.collidesWith(this.myHunter) && !this.myHunter.getImmortality()) {
					z.hitsZombie(this.myHunter);
					this.zombies.remove(z);
				}
			} else {
				this.zombies.remove(z);
			}
		}
	}
	
	private void moveBossZombie() {
		if (secondsSpent >= BossZombie.SPAWN_TIME && this.bossZombie != null) {
			this.bossZombie.move();
			if (this.bossZombie.collidesWith(this.myHunter) && !this.myHunter.getImmortality()) {
				this.bossZombie.hitsZombie(myHunter);
				myHunter.setOnZombie(true);
			} else if (!this.bossZombie.collidesWith(this.myHunter)) {
				myHunter.setOnZombie(false);
			}
		}
	}
	
	private void hitsZombie(Bullet b) {
		for (Zombie zombie : this.zombies) {
			if (b.collidesWith(zombie)) {
				this.zombies.remove(zombie);
				this.myHunter.getBullets().remove(b);
				this.stage.getStatusBar().getScoreBoard().incrementScore();
				break;
			}
		}
		
		if (this.bossZombie != null) {
			if (b.collidesWith(this.bossZombie) && this.bossZombie.isAlive() && this.bossZombie.getVisibility()) {
				this.bossZombie.deductHealth(this.myHunter.getDamage());
				this.myHunter.getBullets().remove(b);
				if (this.bossZombie.getHealth() < 0) {
					this.bossZombie.setAlive(false);
					this.bossZombie.setVisibility(false);
				}
			}
		}
	}
	
	private void hitsHunter(ZombieBullet zb) {
		if (zb.collidesWith(this.myHunter) && !this.myHunter.getImmortality()) {
			this.myHunter.setStrength(-ZombieBullet.DAMAGE);
			this.bossZombie.getBulletsList().remove(zb);
		}
	}
	
	private void stopAnimation(){
		if (this.bossZombie != null) {
			if (secondsSpent >= BossZombie.SPAWN_TIME && this.zombies.size() == 0 && !this.bossZombie.isAlive()) {
				this.stop();
				System.out.println("You win");
				showGameOver("win");
			} else if (this.myHunter.getStrength() <= 0) {
				this.stop();
				System.out.println("You lose");
				showGameOver("lose");
			} else if (this.secondsSpent == GameTimer.END_GAME) {
				this.stop();
				System.out.println("You win");
				showGameOver("win");
			}
		}
	}
	
	private void showGameOver(String result) {
		GameOver gameover = new GameOver(result, this.stage.getStatusBar().getScoreBoard().getScore());
		gameover.setStage(stage.getStage());
	}
	
	//method that will listen and handle the key press events
	private void handleKeyPressEvent() {
		this.theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
			public void handle(KeyEvent e){
            	KeyCode code = e.getCode();
                moveMyHunter(code);
			}
		});
		
		this.theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
		            public void handle(KeyEvent e){
		            	KeyCode code = e.getCode();
		                stopMyHunter(code);
		            }
		        });
    }
	
	//method that will move the ship depending on the key pressed
	private void moveMyHunter(KeyCode ke) {
		if(ke==KeyCode.UP) this.myHunter.setDY(-myHunter.getSpeed());

		if(ke==KeyCode.LEFT) this.myHunter.setDX(-myHunter.getSpeed());

		if(ke==KeyCode.DOWN) this.myHunter.setDY(myHunter.getSpeed());
		
		if(ke==KeyCode.RIGHT) this.myHunter.setDX(myHunter.getSpeed());
		
		if(ke==KeyCode.SPACE) this.myHunter.shoot();			
		
//		System.out.println(ke+" key pressed.");
   	}
	
	//method that will stop the ship's movement; set the ship's DX and DY to 0
	private void stopMyHunter(KeyCode ke){
		this.myHunter.setDX(0);
		this.myHunter.setDY(0);
	}
	
}
