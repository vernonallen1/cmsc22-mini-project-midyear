package powerups;

import application.Hunter;
import application.Sprite;
import javafx.scene.image.Image;

public class Ammo extends Sprite implements PowerUp{
	
	public static final Image AMMO = new Image("file:src/images/ammos.png",PowerUp.WIDTH,PowerUp.LENGTH,false,false); 
	
	private int timeCollected;

	public Ammo(int xPos, int yPos) {
		super(xPos, yPos);
		this.loadImage(AMMO);
		this.visible = false;      
	}

	@Override
	public void applyEffect(Hunter ship) {
		ship.setStrength(ship.getStrength());
	}

	@Override
	public void setTimeCollected(int seconds) {
		this.timeCollected = seconds;
	}
	
	@Override
	public void removeEffect(Hunter hunter) {
		
	}

	@Override
	public int getTimeCollected() {
		return this.timeCollected;
	}

	@Override
	public int getEffectDuration() {
		return 0;
	}
}
