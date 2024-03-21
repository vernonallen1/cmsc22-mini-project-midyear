package application;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import statusbar.*;

public class GameStage{
	public static final int WINDOW_HEIGHT = 500;
	public static final int WINDOW_WIDTH = 800;
	private Scene scene;
	private Stage stage;
	private Group root;
	private Canvas canvas;
	private GraphicsContext gc;
	private GameTimer gametimer;
	private StatusBar statusBar;
	private HBox statusBarHBox;
	
	//the class constructor
	public GameStage() {
		this.root = new Group();
		this.scene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.BLACK);	
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);	
		this.gc = canvas.getGraphicsContext2D();
		//instantiate an animation timer
		this.statusBar = new StatusBar();
		this.statusBarHBox = this.statusBar.getHBox();
		this.gametimer = new GameTimer(this.gc,this.scene, this);
	}

	//method to add the stage elements
	public void setStage(Stage stage) {
		this.stage = stage;
		
		//set stage elements here	     
		this.root.getChildren().addAll(canvas, statusBarHBox);
		
		this.stage.setTitle("Mini Zombie Hunter Shooting Game");
		this.stage.setScene(this.scene);
		
		//invoke the start method of the animation timer
		this.gametimer.start();
		
		this.stage.show();
	}
	
	public StatusBar getStatusBar() {
		return this.statusBar;
	}
	
	public Canvas getCanvas() {
		return this.canvas;
	}
	
	public HBox getStatusBarHbox() {
		return this.statusBarHBox;
	}
	
	public Stage getStage() {
		return this.stage;
	}
}

