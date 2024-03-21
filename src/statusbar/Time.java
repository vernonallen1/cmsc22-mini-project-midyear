package statusbar;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Time {
	public static final Image TIME_ICON = new Image("file:src/images/time.png",StatusBar.ICON_DIMENSION,StatusBar.ICON_DIMENSION,false,false);
	
	private int seconds;
	private HBox timeContainer;
	private ImageView timeIcon;
	private Text label, timeView;
	
	Time() {
		this.seconds = 0;
		this.timeContainer = new HBox();
		this.timeContainer.setSpacing(StatusBar.ICON_DIMENSION-10);
		this.timeContainer.setAlignment(Pos.CENTER_LEFT);
		this.timeContainer.setPrefWidth(StatusBar.CHILD_HBOX_WIDTH-50);
		
		createTimeIcon();
		createLabel();
		createTimeView();
		
		timeContainer.getChildren().addAll(timeIcon, label, timeView);
	}
	
	private void createTimeIcon() {
		this.timeIcon = new ImageView(TIME_ICON);
		this.timeIcon.setFitHeight(StatusBar.ICON_DIMENSION);
		this.timeIcon.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	private void createLabel() {
		this.label = new Text("TIME: ");
		this.label.setFont(Font.font("Verdana",  FontWeight.BOLD, 15));
	}
	
	private void createTimeView() {
		String currentTime;
		String secondsStr;
		String minutesStr;
		
		//set seconds
		if (seconds%60 < 10) {
			secondsStr = ":0" + (seconds- ((int) Math.floor(seconds/60))*60);
		} else {
			secondsStr = ":" + seconds%60;
		}
		
		//set minutes
		minutesStr = "" + (int) Math.floor(seconds/60); 
		
		//build time
		currentTime = minutesStr + secondsStr;
		
		this.timeView = new Text(currentTime);
		this.timeView.setFont(Font.font("Verdana",  FontWeight.BOLD, 15));
	}
	
	public void incrementSeconds(int secondsSpent) {
		if (secondsSpent != seconds)
			this.seconds++;
			timeContainer.getChildren().remove(timeView);
			createTimeView();
			timeContainer.getChildren().add(timeView);
	}
	
	public HBox getHBox() {
		return this.timeContainer;
	}
	
}
