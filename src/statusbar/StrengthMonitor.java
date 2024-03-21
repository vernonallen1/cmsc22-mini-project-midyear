package statusbar;

import java.util.Random;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class StrengthMonitor {
	public static final Image STRENGTH_ICON = new Image("file:src/images/strength.png",StatusBar.ICON_DIMENSION,StatusBar.ICON_DIMENSION,false,false);
	
	private HBox strengthMonitorContainer;
	private ImageView strengthIcon;
	private Text label, strengthValue;
	private int strength;
	
	StrengthMonitor() {
		this.strength = 0;
		this.strengthMonitorContainer = new HBox();
		this.strengthMonitorContainer.setSpacing(StatusBar.ICON_DIMENSION-20);
		this.strengthMonitorContainer.setAlignment(Pos.CENTER_LEFT);
		this.strengthMonitorContainer.setPrefWidth(StatusBar.CHILD_HBOX_WIDTH+100);
		
		Random r = new Random();
		this.strength = r.nextInt(51)+100;
		
		createStrengthIcon();
		createLabel();
		createStrengthValue();
		
		this.strengthMonitorContainer.getChildren().addAll(strengthIcon, label, strengthValue);
	}
	
	private void createStrengthIcon() {
		this.strengthIcon = new ImageView(STRENGTH_ICON);
		this.strengthIcon.setFitHeight(StatusBar.ICON_DIMENSION);
		this.strengthIcon.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	private void createLabel() {
		this.label = new Text("STR:");
		this.label.setFont(Font.font("Verdana",  FontWeight.BOLD, 15));
	}
	
	private void createStrengthValue() {
		this.strengthValue = new Text("" + strength);
		this.strengthValue.setFont(Font.font("Verdana",  FontWeight.BOLD, 15));
	}
	
	public void setStrength(int strength) {
		this.strength += strength;
		this.strengthMonitorContainer.getChildren().remove(strengthValue);
		createStrengthValue();
		this.strengthMonitorContainer.getChildren().add(strengthValue);
	}
	
	public int getStrength() {
		return this.strength;
	}
	
	public HBox getHBox() {
		return this.strengthMonitorContainer;
	}
}
