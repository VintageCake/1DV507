package test.JavaFX;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class javafxtest extends Application {
	public void start(Stage primaryStage) {
		Text t = new Text(20, 50, "Thing");
		Group root = new Group();
		root.getChildren().add(t);
		Scene sc = new Scene(root, 100, 100);
		
		
		primaryStage.setScene(sc);
		primaryStage.show();
		
		
	}
	public static void main(String[] args) {
		launch();
	}

}
