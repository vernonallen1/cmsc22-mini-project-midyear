package powerups;

import application.Hunter;
import application.Sprite;
import javafx.scene.image.Image;

public class Shoes extends Sprite implements PowerUp{
	
	public static final Image SHOES = new Image("file:src/images/shoes.png",PowerUp.WIDTH,PowerUp.LENGTH,false,false);
	public static final int SPEED_INCREASE = 3;
	public static final int EFFECT_DURATION = 8;
	
	private int timeCollected;
	
	public Shoes(int xPos, int yPos) {
		super(xPos, yPos);
		this.loadImage(SHOES);
		this.visible = false;
	}

	@Override
	public void applyEffect(Hunter ship) {
		ship.increaseSpeed(SPEED_INCREASE);
	}
	
	@Override
	public void setTimeCollected(int seconds) {
		this.timeCollected = seconds;
	}
	
	@Override
	public void removeEffect(Hunter hunter) {
		hunter.removeExtraSpeed(SPEED_INCREASE);
	}

	@Override
	public int getTimeCollected() {
		return this.timeCollected;
	}

	@Override
	public int getEffectDuration() {
		return Shoes.EFFECT_DURATION;
	}
}
