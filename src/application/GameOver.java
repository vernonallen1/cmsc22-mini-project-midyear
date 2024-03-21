package application;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class GameOver{
	public final static int BUTTON_WIDTH = 100;
	
	private Group root;
	private VBox vbox;
	private Button retryBtn;
	private Canvas canvas;
	private GraphicsContext gc;
	private String message;
	private Stage stage;
	private int score;
	private Scene gameoverScene;
	private Color sceneColor;
	
	
	
	public GameOver(String message, int score) {
		this.message = message;
		this.score = score;
		
		this.root = new Group();
		this.vbox = new VBox();
		
		createCanvas();
		createRetryButton();
		createMessage();
		setSceneColor();
		
		this.vbox.setTranslateX(GameStage.WINDOW_WIDTH/2 - BUTTON_WIDTH/2);
		this.vbox.setTranslateY(300);
		this.vbox.setAlignment(Pos.CENTER);
		this.gameoverScene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,this.sceneColor);
	}
	
	private void setSceneColor() {
		if (this.message.equals("win")) {
			this.sceneColor = Color.DARKGREEN;
		} else {
			this.sceneColor = Color.DARKRED;
		}
		
	}

	public void setStage(Stage stage) {
		this.stage = stage;
		this.root.getChildren().addAll(canvas, vbox);
		
		this.stage.setScene(this.gameoverScene);
		
		//invoke the start method of the animation timer
		
		this.stage.show();
	}
	
	public Group getGroup() {
		return this.root;
	}
	
	private void createRetryButton() {
		this.retryBtn = new Button("EXIT");
		this.retryBtn.setPrefWidth(BUTTON_WIDTH);
		this.vbox.getChildren().add(retryBtn);
		this.addEventHandler(retryBtn);
	}
	
	private void createCanvas() {
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
	}
	
	private void createMessage() {
		this.gc.setFont(Font.font("Impact", FontWeight.BOLD, 90));
		this.gc.fillText("You " + this.message + "!", 230, 200);
		
		this.gc.setFont(Font.font("Impact", FontWeight.BOLD, 45));
		this.gc.fillText("SCORE: " + this.score, 320, 270);
	}
	

	private void addEventHandler(Button btn) {	
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent arg0) { 
				System.exit(0);
			}
		});
	}

	public Scene getScene() {
		return this.gameoverScene;
	}
	
}
