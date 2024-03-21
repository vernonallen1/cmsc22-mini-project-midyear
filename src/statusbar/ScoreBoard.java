package statusbar;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class ScoreBoard {
	public static final Image SCORE_ICON = new Image("file:src/images/normal_zombie.png",StatusBar.ICON_DIMENSION,StatusBar.ICON_DIMENSION,false,false);
	
	private HBox scoreBoardContainer;
	private ImageView fishIcon;
	private Text label, score;
	private int killedFishes;
	
	ScoreBoard() {
		this.scoreBoardContainer = new HBox();
		this.scoreBoardContainer.setSpacing(StatusBar.ICON_DIMENSION-10);
		this.scoreBoardContainer.setAlignment(Pos.CENTER_LEFT);
		this.scoreBoardContainer.setPrefWidth(StatusBar.CHILD_HBOX_WIDTH);
		this.killedFishes = 0;
		
		createScoreIcon();
		createLabel();
		createScoreMonitor();
		
		this.scoreBoardContainer.getChildren().addAll(fishIcon, label, score);
	}
	
	private void createScoreIcon() {
		this.fishIcon = new ImageView(SCORE_ICON);
		this.fishIcon.setFitHeight(StatusBar.ICON_DIMENSION);
		this.fishIcon.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	private void createLabel() {
		this.label = new Text("SCORE:");
		this.label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
	}
	
	private void createScoreMonitor() {
		this.score = new Text("" + killedFishes);
		this.score.setFont(Font.font("Verdana",  FontWeight.BOLD, 15));
	}
	
	public void incrementScore() {
		killedFishes++;
		this.scoreBoardContainer.getChildren().remove(this.score);
		createScoreMonitor();
		this.scoreBoardContainer.getChildren().add(this.score);
	}
	
	public int getScore() {
		return this.killedFishes;
	}
	
	public HBox getHBox() {
		return this.scoreBoardContainer;
	}
}
