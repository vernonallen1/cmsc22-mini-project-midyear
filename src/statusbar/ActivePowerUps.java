package statusbar;

import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import powerups.*;

public class ActivePowerUps {
	public static final int SPACING = 10;
	
	private HBox powerUpsContainer;
	private ImageView ammo, shield, shoes;
//	private Boolean pearlVisibility, starVisibility, fishermanVisibility;
	private Text label;
	
	ActivePowerUps() {
		this.powerUpsContainer = new HBox();
		this.powerUpsContainer.setSpacing(ActivePowerUps.SPACING);
		this.powerUpsContainer.setAlignment(Pos.CENTER_LEFT);
		this.powerUpsContainer.setPrefWidth(StatusBar.CHILD_HBOX_WIDTH*2);
		
		createLabel();
		createAmmoIcon();
		createShieldIcon();
		createShoesIcon();
		powerUpsContainer.getChildren().add(label);
	}
	
	private void createLabel() {
		this.label = new Text("Power Up:");
		this.label.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
	}
	
	private void createAmmoIcon() {
		this.ammo = new ImageView(Ammo.AMMO);
		this.ammo.setFitHeight(StatusBar.ICON_DIMENSION);
		this.ammo.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	private void createShieldIcon() {
		this.shield = new ImageView(Shield.SHIELD);
		this.shield.setFitHeight(StatusBar.ICON_DIMENSION);
		this.shield.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	private void createShoesIcon() {
		this.shoes = new ImageView(Shoes.SHOES);
		this.shoes.setFitHeight(StatusBar.ICON_DIMENSION);
		this.shoes.setFitWidth(StatusBar.ICON_DIMENSION);
	}
	
	public void setAmmoVisibility(Boolean isVisible) {
		if (isVisible && !isChildren(this.powerUpsContainer,ammo)) {
			powerUpsContainer.getChildren().add(ammo);
		} else if (!isVisible && isChildren(this.powerUpsContainer,ammo)){
			powerUpsContainer.getChildren().remove(ammo);
		}
	}
	
	public void setShieldVisibility(Boolean isVisible) {
		if (isVisible && !isChildren(this.powerUpsContainer,shield)) {
			powerUpsContainer.getChildren().add(shield);
		} else if (!isVisible && isChildren(this.powerUpsContainer,shield)){
			powerUpsContainer.getChildren().remove(shield);
		}
	}
	
	public void setShoesVisibility(Boolean isVisible) {
		if (isVisible && !isChildren(this.powerUpsContainer,shoes)) {
			powerUpsContainer.getChildren().add(shoes);
		} else if (!isVisible && isChildren(this.powerUpsContainer,shoes)){
			powerUpsContainer.getChildren().remove(shoes);
		}
	}
	
	private Boolean isChildren(HBox hbox, ImageView icon) {
		for (int i = 0; i < hbox.getChildren().size(); i++) {
			if (icon.equals(hbox.getChildren().get(i)) ) {
				return true;
			}
		}
		return false;
	}
	
	public HBox getHBox() {
		return this.powerUpsContainer;
	}
}
