package ls223qx_lab3.ex8_10;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

public class TinyPainter extends Application {
	@Override
	public void start(Stage pStage) throws Exception {
		final Pane root = new Pane();

		HBox fields = new HBox();
		final ComboBox<String> shapes = new ComboBox<>();
		final ComboBox<Double> size = new ComboBox<>();
		shapes.getItems().addAll("Circle", "Dot", "Line", "Rectangle"); // String representations of each shape
		size.getItems().addAll(1.0, 2.0, 5d, 10d, 20d, 40d); // Width selection
		shapes.getSelectionModel().selectFirst(); // Sets default item shown on startup of program, will be the first
													// entry of the combobox.
		size.getSelectionModel().selectFirst();
		root.getChildren().add(fields);

		final ColorPicker cPicker = new ColorPicker();
		fields.getChildren().addAll(shapes, size, cPicker);

		final ShapeReference shapeReference = new ShapeReference();

		root.setOnMousePressed(e ->
			{
				double x = e.getSceneX(); // Get mouse XY inside the pane.
				double y = e.getSceneY();
				System.out.println(x);
				System.out.println(y);
				double width = size.getValue(); // Get colour and width from combo box and colour picker.
				Color color = cPicker.getValue();
				String sh = shapes.getValue();
				
				// Creation of each shape type, depending on what is selected.
				// It is added to the pane, but the object reference is passed to a custom object from a private class, it is later used for movement.
				if (sh.equals("Circle")) {
					Circle circle = new Circle(x, y, width, color);
					root.getChildren().add(circle);
					shapeReference.setReference(circle);
				}
				else if (sh.equals("Dot")) {
					Circle circle = new Circle(x, y, 1, color);
					root.getChildren().add(circle);
					shapeReference.setReference(circle);
				}
				else if (sh.equals("Line")) {
					Line l = new Line(e.getSceneX(), e.getSceneY(), e.getSceneX(), e.getSceneY());
					l.setStrokeWidth(width);
					l.setStroke(color);
					root.getChildren().add(l);
					shapeReference.setReference(l);
				}
				else {
					Rectangle r = new Rectangle(0d, 0d, color);
					r.setX(x); // Rectangle start on mouse cursor.
					r.setY(y);
					root.getChildren().add(r);
					shapeReference.setReference(r);
					shapeReference.setPosRef(x, y); // Positional references are also passed to the object.
				}
			});

		root.setOnMouseDragged(e ->
			{
				Shape shape = shapeReference.getReference(); // Get passed shape
				
				if (shape instanceof Circle) { // Circle is simply moved around in x and y
					((Circle) shape).setCenterX(e.getSceneX()); // Gets the current mouse position.
					((Circle) shape).setCenterY(e.getSceneY());
				}
				else if (shape instanceof Rectangle) { // Rectangles have their width and height modified.
					// Reference xy is useful when calculating how much width and height the rectangle should have.
					double refx = shapeReference.getRefX();
					double refy = shapeReference.getRefY();
					double x = e.getSceneX();
					double y = e.getSceneY();
					
					((Rectangle) shape).setWidth(x-refx);
					((Rectangle) shape).setHeight(y-refy);
				}
				else if (shape instanceof Line) { // Modifies end xy to mouse cursor position.
					((Line) shape).setEndX(e.getSceneX()); 
					((Line) shape).setEndY(e.getSceneY());
				}

			});

		pStage.setScene(new Scene(root, 800, 600));
		pStage.setTitle("Tiny Painter");
		pStage.setResizable(false);
		pStage.show();

	}

	public static void main(String[] args) {
		launch();
	}

	private class ShapeReference { // Maintains a reference to a given shape, useful when 'traversing' event handlers.
		Shape s;
		double refx;
		double refy;

		public ShapeReference() {

		}

		public void setReference(Shape pS) {
			s = pS;
		}

		public Shape getReference() {
			return s;
		}

		public void setPosRef(double x, double y) { // Used for rectangles only, maintains the 'start' position of a rectangle.
			refx = x;
			refy = y;
		}

		public double getRefX() {
			return refx;
		}

		public double getRefY() {
			return refy;
		}
	}
}
