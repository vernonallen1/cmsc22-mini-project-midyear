package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import powerups.Ammo;
import powerups.Shield;
import powerups.Shoes;

public class InstructionScene {
	private Group root;
	private Scene instructionScene;
	private Button backBtn;
	private Canvas canvas;
	private GraphicsContext gc;
	private Stage stage;
	
	public InstructionScene (Button backBtn) {
		this.backBtn = backBtn;
		this.root = new Group();
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();	
		
		createInstructions();
		createPowerUpsInfo();
		setBackBtn();
		
		this.instructionScene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.MAROON);
		this.root.getChildren().addAll(canvas, backBtn);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		
		this.stage.setScene(this.instructionScene);

		this.stage.show();
	}
	
	private void createInstructions() {
		String instructionTitle = "INSTRUCTION";
		this.gc.setFont(Font.font("Impact", FontWeight.BOLD, 40));
		this.gc.fillText(instructionTitle, 280, 50);
		
		String instructionText = 
				  "1. Move the using the arrow keys and avoid being hit by zombies.\n"
				+ "2. Shoot the zombies using the spacebar key to eliminate them out \n"
				+ "of the game. Dodge bullets coming from the boss zombie.\n"
				+ "3. Collect power-ups to boost strength and gain brief movement speed,\n"
				+ " and have a temporary immortality against strength reduction.\n"
				+ "4. Kill all the zombies including the boss zombie or survive for one \n"
				+ "minute to win the game";
		this.gc.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
		this.gc.fillText(instructionText, 50, 80);
	}
	
	private void createPowerUpsInfo() {
		String powerUpsTitle = "POWER-UPS";
		this.gc.setFont(Font.font("Impact", FontWeight.BOLD, 40));
		this.gc.fillText(powerUpsTitle, 300, 280);
		
		//ammolition info
		this.gc.drawImage(Ammo.AMMO, 50, 320);
		this.gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		this.gc.fillText("Doubles the\nstrength of \nthe hunter", 100, 320);
		
		//immortality 
		this.gc.drawImage(Shield.SHIELD, 250, 320);
		this.gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		this.gc.fillText("Gives the hunter \nan immortality \nfor 5 seconds", 300, 320);
		
		//speed
		this.gc.drawImage(Shoes.SHOES, 480, 320);
		this.gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 20));
		this.gc.fillText("Temporarily increases \nhunter's movement\n speed for 8 seconds", 530, 320);
	}
	
	private void setBackBtn() {
		this.backBtn.setTranslateX(GameStage.WINDOW_WIDTH/2 - this.backBtn.getPrefWidth()/2);
		this.backBtn.setTranslateY(400);
	}
}
