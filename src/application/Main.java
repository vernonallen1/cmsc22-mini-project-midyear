package application;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage){
		StartScene theStartScene = new StartScene();
		theStartScene.setStage(stage);
	}

}
