package application;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.stage.Stage;

public class StartScene {
	public final static Image TITLE_IMAGE = new Image("file:src/images/title.png",400,200,false,false);
	public final static int VBOX_SPACING = 20;
	public final static int BUTTON_WIDTH = 200;
	public final static int IMAGE_TITLE_WIDTH = 400;
	public final static int VBOX_XPOS = (int) (GameStage.WINDOW_WIDTH-IMAGE_TITLE_WIDTH)/2;
	public final static int VBOX_YPOS = 50;
	public final static Insets PADDING = new Insets(10, 20, 10, 20);
	
	private Stage stage;
	private Scene scene;
	private Group root;
	private VBox vbox;
	private ImageView title;
	private Button newGame, instruction, about, backInstruction, backAbout;
	private GameStage theGameStage;
	private InstructionScene theInstructionScene;
	private AboutScene theAboutScene;
	
	public StartScene() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.BLACK);
		
		createVbox();
		createTitle();
		createInstructionBackBtn();
		createAboutBackBtn();
		createButton(this.newGame, "New Game");
		createButton(this.instruction, "Instruction");
		createButton(this.about, "About");
		
		theInstructionScene = new InstructionScene(this.backInstruction);
		theAboutScene = new AboutScene(this.backAbout);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		this.root.getChildren().addAll(vbox);
		
		this.stage.setTitle("Mini Zombie Hunter Shooting Game");
		setSplashScreen();
		
		this.stage.show();
	}
	
	private void createVbox() {
		this.vbox = new VBox();
		this.vbox.setSpacing(VBOX_SPACING);
		this.vbox.setTranslateX(VBOX_XPOS);
		this.vbox.setTranslateY(50);
		this.vbox.setAlignment(Pos.CENTER);
	}
	
	private void createTitle() {
		this.title = new ImageView();
		this.title.setImage(TITLE_IMAGE);
		this.vbox.getChildren().add(this.title);
	}
	
	private void createButton(Button btn, String label) {
		btn = new Button(label);
		btn.setPrefWidth(BUTTON_WIDTH);
		btn.setPadding(PADDING);
		btn.setFont(Font.font("Verdana", FontPosture.REGULAR, 15));
		this.vbox.getChildren().add(btn);
		addEventHandler(btn);
	}
	
	private void createInstructionBackBtn() {
		this.backInstruction = new Button("Back");
		this.backInstruction.setPrefWidth(BUTTON_WIDTH);
		this.backInstruction.setPadding(PADDING);
		this.backInstruction.setFont(Font.font("Verdana", FontPosture.REGULAR, 15));
		this.backInstruction.setTranslateX(GameStage.WINDOW_WIDTH/2 - backInstruction.getPrefWidth()/2);
		this.backInstruction.setTranslateY(700);
		addEventHandler(backInstruction);
	}
	
	private void createAboutBackBtn() {
		this.backAbout = new Button("Back");
		this.backAbout.setPrefWidth(BUTTON_WIDTH);
		this.backAbout.setPadding(PADDING);
		this.backAbout.setFont(Font.font("Verdana", FontPosture.REGULAR, 15));
		this.backAbout.setTranslateX(GameStage.WINDOW_WIDTH/2 - backAbout.getPrefWidth()/2);
		this.backAbout.setTranslateY(700);
		addEventHandler(backAbout);
	}
	
	public void setSplashScreen() {
		//sets the scene on the stage
		this.stage.setScene(this.scene);
	}
	
	public void setStart() {
		theGameStage = new GameStage();
		theGameStage.setStage(stage);
	}
	
	public void setInstruction() {
		//sets the stage's scene to instruction scene
		theInstructionScene.setStage(stage);
	}
	
	public void setAbout() {
		theAboutScene.setStage(stage);
	}
	
	
	private void addEventHandler(Button btn) {	
		btn.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				switch(btn.getText()) {
				case ("New Game"): {
					setStart();
					break;
				}
				case ("Instruction"): {
					setInstruction();
					break;
				}
				case ("About"): {
					setAbout();
					break;
				}
				case ("Back"): {
					setSplashScreen();
					break;
				}
				}
			}
		});
		
	}
}
