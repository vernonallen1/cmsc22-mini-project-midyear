package statusbar;

import javafx.geometry.Insets;
import javafx.scene.layout.HBox;

public class StatusBar {
	public static final int ICON_DIMENSION = 25;
	public static final int CHILD_HBOX_WIDTH = 94;
	public static final int TOP_PADDING = 10;
	public static final int LEFT_PADDING = 20;
	public static final int BOTTOM_PADDING = 10;
	public static final int RIGHT_PADDING = 10;
	public static final int SPACING = 50;
	public static final int STATUS_BAR_HEIGHT = ICON_DIMENSION + TOP_PADDING + BOTTOM_PADDING;
	
	private HBox statusBar;
	private StrengthMonitor strengthMonitor;
	private ScoreBoard scoreboard;
	private Time time;
	private ActivePowerUps activePowerUps;
	
	public StatusBar() {
		createStatusBar();
		this.strengthMonitor = new StrengthMonitor();
		this.scoreboard = new ScoreBoard();
		this.time = new Time();
		this.activePowerUps = new ActivePowerUps();
		this.statusBar.getChildren().addAll(activePowerUps.getHBox(), time.getHBox(), scoreboard.getHBox(), strengthMonitor.getHBox());
	}
	
	private void createStatusBar() {
		this.statusBar = new HBox();
		this.statusBar.setSpacing(SPACING);
		this.statusBar.setPadding(new Insets(TOP_PADDING, LEFT_PADDING, BOTTOM_PADDING, RIGHT_PADDING));
		this.statusBar.setStyle("-fx-background-color: #336699;");
	}
	
	public HBox getHBox() {
		return this.statusBar;
	}
	
	public StrengthMonitor getStrengthMonitor() {
		return this.strengthMonitor;
	}
	
	public ScoreBoard getScoreBoard() {
		return this.scoreboard;
	}
	
	public Time getTime() {
		return this.time;
	}
	
	public ActivePowerUps getActivePowerUps(){
		return this.activePowerUps;
	}
}
