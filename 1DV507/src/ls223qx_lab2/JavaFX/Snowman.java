package ls223qx_lab2.JavaFX;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Snowman extends Application {
	public void start(Stage primaryStage) {
		
		Group root = new Group();
		Scene sc = new Scene(root, 400, 400); // Make new 400x400 window
		
		Rectangle background = new Rectangle(0,0,400,300); // Start X Start Y, Width, Height (in pixels, presumably)
		background.setFill(Color.AQUA); //Make the background aqua
		
		// A higher Y value makes the objects go down, which i find quite weird.
		
		Circle head = new Circle(200,150,30); // Create a head at X 200, Y 150 and with radius of 30
		head.setFill(Color.WHITE);
		
		Circle body1 = new Circle(200, 200, 40); // Create another circle at Y 200, radius 40
		body1.setFill(Color.WHITE);
		
		Circle body2 = new Circle(200, 260, 50); // Another circle at Y 260, radius 50
		body2.setFill(Color.WHITE);
		
		Circle theSun = new Circle(300, 100, 60); // Create the sun in the sky
		theSun.setFill(Color.YELLOW);
		
		
		Circle eye1 = new Circle(190, 140, 3); // Make the eyes at Y position 140
		Circle eye2 = new Circle(210, 140, 3);
		
		Line mouth = new Line(180, 150, 220, 150); // Mouth - start X, start Y, end X, end Y
		
		
		Circle btn1 = new Circle(200, 180, 4); // Snowman buttons 
		Circle btn2 = new Circle(200, 200, 4);
		Circle btn3 = new Circle(200, 220, 4);
		
		root.getChildren().addAll(background, head, body1, body2, eye1, eye2, btn1, btn2, btn3, mouth, theSun); 
		// Add all shapes into the scene, background first so it gets drawn over.
		
		primaryStage.setTitle("Snowman"); // Set window title
		primaryStage.setScene(sc); // Put all objects into the scene
		primaryStage.show(); // Create the window and show it
		
		
	}
	public static void main(String[] args) {
		launch(args); // do 'start'
	}

}
