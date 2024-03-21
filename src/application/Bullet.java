package application;

import javafx.scene.image.Image;

public class Bullet extends Sprite {
	private final int BULLET_SPEED = 20;
	public final static Image BULLET = new Image("file:src/images/bullet.png",Bullet.BULLET_WIDTH,Bullet.BULLET_WIDTH,false,false);
	public final static int BULLET_WIDTH = 20;
	
	public Bullet(int x, int y){
		super(x,y);
		this.loadImage(Bullet.BULLET);
	}


	//method that will move/change the x position of the bullet 
	public void move(){
		/*
		 * TODO: Change the x position of the bullet depending on the bullet speed.
		 * 					If the x position has reached the right boundary of the screen,
		 * 						set the bullet's visibility to false.
		 */
		this.x += BULLET_SPEED;
		if (this.getX() >= GameStage.WINDOW_WIDTH) {
			this.visible = false;
		}
	}
}
