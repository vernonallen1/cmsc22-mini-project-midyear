package application;

import javafx.scene.image.Image;

public class ZombieBullet extends Bullet{
	public final static Image ZOMBIE_BULLET_IMG= new Image("file:src/images/zombie_bullet.png",Bullet.BULLET_WIDTH,Bullet.BULLET_WIDTH,false,false);
	public final static int ZOMBIE_BULLET_SPEED = 10;
	public final static int DAMAGE = 20;
	
	public ZombieBullet(int x, int y) {
		super(x, y);
		this.img = ZOMBIE_BULLET_IMG;
	}
	
	@Override
	public void move() {
		this.x -= ZOMBIE_BULLET_SPEED;
		if (this.getX() < 0) {
			this.visible = false;
		}
	}
	
}
