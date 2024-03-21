package powerups;

import application.Hunter;
import application.Sprite;
import javafx.scene.image.Image;

public class Shield extends Sprite implements PowerUp{
	
	public static final Image SHIELD = new Image("file:src/images/shield.png",PowerUp.WIDTH,PowerUp.LENGTH,false,false);
	public static final int EFFECT_DURATION = 5;
	
	private int timeCollected;
	
	public Shield(int xPos, int yPos) {
		super(xPos, yPos);
		this.loadImage(SHIELD);
		this.visible = false;
	}

	@Override
	public void applyEffect(Hunter ship) {
		ship.setImmortality(true);
	} 
	
	@Override
	public void setTimeCollected(int seconds) {
		this.timeCollected = seconds;
	}
	
	@Override
	public void removeEffect(Hunter hunter) {
		hunter.setImmortality(false);
	}

	@Override
	public int getTimeCollected() {
		return this.timeCollected;
	}

	@Override
	public int getEffectDuration() {
		return Shield.EFFECT_DURATION;
	}
}
