package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AboutScene {
	private Group root;
	private Scene instructionScene;
	private Button backBtn;
	private Canvas canvas;
	private GraphicsContext gc;
	private Stage stage;
	private GridPane grid;
	
	public AboutScene (Button backBtn) {
		this.backBtn = backBtn;
		this.root = new Group();
		this.canvas = new Canvas(GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT);
		this.gc = canvas.getGraphicsContext2D();
		this.grid = new GridPane();
		
		createAbout();
		createImageReferences();
		setBackBtn();
		
		this.instructionScene = new Scene(root, GameStage.WINDOW_WIDTH,GameStage.WINDOW_HEIGHT,Color.MAROON);
		this.root.getChildren().addAll(canvas, grid, backBtn);
	}
	
	public void setStage(Stage stage) {
		this.stage = stage;
		
		this.stage.setScene(this.instructionScene);

		this.stage.show();
	}
	
	
	private void createAbout() {
		String title = "ABOUT";
		this.gc.setFont(Font.font("Impact", FontWeight.BOLD, 30));
		this.gc.fillText(title, 350, 45);
		
		String instructionText = "This mini project was developed by VERNON ALLEN FAJILAN for CMSC 22 Object-Oriented Programming"
				+ ".This was submitted to\nMa'am Mylah Restie Anacleto of University of the Philippines Los Banos, August 15,2022, Midyear.";
		this.gc.setFont(Font.font("Helvetica", FontWeight.BOLD, 12));
		this.gc.fillText(instructionText, 35, 70);
	}
	
	private void createImageReferences() {
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(0, 10, 0, 10));
		grid.setTranslateX(30);
		grid.setTranslateY(100);
		grid.setAlignment(Pos.CENTER);
		
		Text category = new Text("Image Name");
		category.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(category, 0, 0);
		
		Text chartTitle = new Text("                                         Online Source");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(chartTitle, 1, 0);
		
		Text ammo = new Text("Ammo");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(ammo, 0, 1);
		
		Text ammoSource = new Text("https://www.mariowiki.com/images/c/cd/BulletBillMK8.png");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(ammoSource, 1, 1);
		
		Text time = new Text("Time Icon");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(time, 0, 2);
		
		Text timeSource = new Text("https://www.onlinewebfonts.com/icon/394732");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(timeSource, 1, 2);
		
		Text strength = new Text("Strength Icon");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(strength, 0, 3);
		
		Text strengthSource = new Text("https://www.onlinewebfonts.com/icon/185439");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(strengthSource, 1, 3);
		
		Text hunter = new Text("Zombie Hunter");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(hunter, 0, 4);
		
		Text hunterSource = new Text("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ_sN6NLpUD9Etj-7hb24e1ynnI-8zI3RkJXA&usqp=CAU");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(hunterSource, 1, 4);
		
		Text bullet = new Text("Bullet");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(bullet, 0, 5);
		
		Text bulletSource = new Text("https://www.pinclipart.com/picdir/big/85-855469_5hyt-bullet-clipart-animated-bullet-clipart-png-download.png");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(bulletSource, 1, 5);
		
		Text normalZombie = new Text("Normal Zombie");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(normalZombie, 0, 6);
		
		Text normalZombieSource = new Text("https://webstockreview.net/images/zombie-clipart-zombie-run-4.png");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(normalZombieSource, 1, 6);
		
		Text bossZombie = new Text("Boss Zombie");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(bossZombie, 0, 7);
		
		Text bossZombieSource = new Text("https://webstockreview.net/images/zombie-clipart-5.png");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(bossZombieSource, 1, 7);
		
		Text zombieBullet = new Text("Zombie Bullet");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(zombieBullet, 0, 8);
		
		Text zombieBulletSource = new Text("https://www.imagenspng.com.br/wp-content/uploads/2020/05/coronavirus-png-122.png");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(zombieBulletSource, 1, 8);
		
		Text shield = new Text("Shield");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(shield, 0, 9);
		
		Text shieldSource = new Text("https://static.turbosquid.com/Preview/2019/05/03__08_15_35/shhield01.png6DE9484C-F233-4C84-989D-76D4EF7CD457Default.jpg");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(shieldSource, 1, 9);
		
		Text shoes = new Text("Shoes");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(shoes, 0, 10);
		
		Text shoesSource = new Text("https://www.kindpng.com/imgv/mxwiTw_transparent-cartoon-shoes-png-shoes-png-cartoon-png/");
		chartTitle.setFont(Font.font("Arial", FontWeight.BOLD, 20));
		grid.add(shoesSource, 1, 10);
	}
	
	private void setBackBtn() {
		this.backBtn.setTranslateX(GameStage.WINDOW_WIDTH/2 - this.backBtn.getPrefWidth()/2);
		this.backBtn.setTranslateY(400);
	}
}