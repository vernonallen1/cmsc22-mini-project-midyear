package powerups;

import application.Hunter;

public interface PowerUp{
	public static final int VISIBLE_DURATION = 5;
	public static final int SPAWN_TIME = 10;
	public static final int WIDTH = 30;
	public static final int LENGTH = 30;
	public static final int POWER_UPS_COUNT = 3;
	
	abstract void applyEffect(Hunter ship);
	abstract void removeEffect(Hunter ship);
	abstract void setTimeCollected(int seconds);
	abstract int getTimeCollected();
	abstract int getEffectDuration();
}
