package ls223qx_lab2.JavaFX;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CompoundInterest extends Application {
	@Override
	public void start(Stage primaryStage) {
		GridPane pane = new GridPane(); // Settings from lecture slides, they seem to work well enough.
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);
		// This stuff almost has a CSS-vibe to it...

		Text headText = new Text("Compount Interest"); // The main text
		Font largeFont = Font.font("Serif", 20); // A larger font for main text
		headText.setFont(largeFont);

		Button cal = new Button("Calculate");

		final TextField startingAmount = new TextField(); // Declaration of final for elements that needs to be accessed inside the button click event
		final TextField interestField = new TextField();
		final TextField nYears = new TextField();
		final Label money = new Label();

		Label startDesc = new Label("Start amount:"); // Descriptor labels
		Label interestDesc = new Label("Interest:");
		Label yearsDesc = new Label("Years:");
		Label moneyDesc = new Label("In total that will be: ");
		moneyDesc.setVisible(false); // Invisible on start

		cal.setOnAction(e ->
			{
				try { // Attempt conversion to integer, check value sanity.
					double start = Integer.parseInt(startingAmount.getText());
					double interest = Integer.parseInt(interestField.getText()) / 100d + 1;
					int years = Integer.parseInt(nYears.getText());
					
					if (years < 1 || interest < 1 || start < 1) {
						throw new NumberFormatException();
					}

					for (int i = 0; i < years; i++) { // Compound interest
						start = start * interest;
					}
					moneyDesc.setVisible(true); // Show the description label
					money.setText(String.valueOf(Math.round(start)));
				}
				catch (NumberFormatException e1) {
					Alert a = new Alert(AlertType.WARNING);
					a.setContentText("Input was incorrect, please use whole integers and no negative numbers!");
					a.showAndWait();
				}
			});
		pane.add(headText, 0, 0); // Add everything to the GirdPane, using columns and rows.
		pane.add(startDesc, 0, 1);
		pane.add(interestDesc, 0, 2);
		pane.add(yearsDesc, 0, 3);

		pane.add(startingAmount, 1, 1);
		pane.add(interestField, 1, 2);
		pane.add(nYears, 1, 3);

		pane.add(cal, 0, 4);

		pane.add(moneyDesc, 0, 5);
		pane.add(money, 1, 5);

		Scene sc = new Scene(pane);
		primaryStage.setScene(sc);
		primaryStage.setResizable(false);
		primaryStage.setTitle("Compound Interest");
		primaryStage.show();

	}

	public static void main(String[] args) {
		launch();
	}

}
