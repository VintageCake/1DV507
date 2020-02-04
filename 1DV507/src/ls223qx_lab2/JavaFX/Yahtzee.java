package ls223qx_lab2.JavaFX;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Yahtzee extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane(); // Settings from lecture slides
		pane.setAlignment(Pos.CENTER);
		pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
		pane.setHgap(5.5);
		pane.setVgap(5.5);

		final Image d1 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d1.png"); // file location 'should' work on another pc
		final Image d2 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d2.png");
		final Image d3 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d3.png");
		final Image d4 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d4.png");
		final Image d5 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d5.png");
		final Image d6 = new Image("file:src/ls223qx_lab2/JavaFX/dice_faces/d6.png");
		Image[] allDiceImages = { d1, d2, d3, d4, d5, d6 };
		// Put all references inside array
		// Will later be used to iterate through all images
		
		// This method of putting object references into an array will be used more throughout this program.

		final Dice firstDie = new Dice(); // Dice is an object from a private inner class that holds image and value
		final Dice secondDie = new Dice();
		final Dice thirdDie = new Dice();
		final Dice fourthDie = new Dice();
		final Dice fithDie = new Dice();
		Dice[] allDice = { firstDie, secondDie, thirdDie, fourthDie, fithDie };

		for (Dice x : allDice) { // Set base settings.
			x.getImageView().setImage(d1);
			x.getImageView().setFitHeight(120);
			x.getImageView().setPreserveRatio(true);
		}

		// Button, labels and check boxes
		Label title = new Label("Yahtzee");
		title.setFont(new Font("Sarif", 30));

		Button rollButton = new Button("Roll the dice!"); // The only button
		
		Label firstDescription = new Label("You have ");
		Label counterLabel = new Label("3");
		Label lastDescription = new Label(" roll(s) left");

		HBox bottomText = new HBox();
		bottomText.getChildren().addAll(firstDescription, counterLabel, lastDescription);
		// HBox will later be put in a single cell, results in better order in the gridpane.

		final CheckBox die1b = new CheckBox();
		final CheckBox die2b = new CheckBox();
		final CheckBox die3b = new CheckBox();
		final CheckBox die4b = new CheckBox();
		final CheckBox die5b = new CheckBox();
		CheckBox[] dieCheckBoxes = { die1b, die2b, die3b, die4b, die5b };

		for (CheckBox x : dieCheckBoxes) {
			GridPane.setHalignment(x, HPos.CENTER);
			x.setDisable(true);
			// Align all check boxes with die by putting them in the centre of the 'cell'
			// Disable check boxes at first
		}
		
		final Label endText = new Label();
		GridPane.setValignment(endText, VPos.CENTER);
		endText.setVisible(false);

		AtomicInteger labelNumber = new AtomicInteger();
		labelNumber.set(3);
		AtomicInteger clicks = new AtomicInteger(); 
		// A variable can't get a new memory reference inside the button
		// (or rather, inside a lambda expression)
		// However, you CAN change fields inside an object via its methods, meaning we
		// can use an object as a counter instead of a primitive integer.
		
		rollButton.setOnAction((ActionEvent e) ->
			{
				if (clicks.intValue() == 0) { // On first click
					for (CheckBox x : dieCheckBoxes) { // Enable check boxes
						x.setDisable(false);
					}
					for (int i = 0; i < dieCheckBoxes.length; i++) { // Reroll all
						reroll(allDice[i], allDiceImages);
					}
				}
				else if (clicks.intValue() > 1) { // On final press
					for (int i = 0; i < dieCheckBoxes.length; i++) { // Reroll all dice with checkboxes ticked
						if (dieCheckBoxes[i].isSelected())
							reroll(allDice[i], allDiceImages);
					}
					for (CheckBox x : dieCheckBoxes) { // Disable check boxes
						x.setDisable(true);
						x.setSelected(false);
					}
					endText.setVisible(true);
					endText.setText(identifyHand(allDice));
					bottomText.setVisible(false);
					rollButton.setDisable(true);
					
				}
				else {
					for (int i = 0; i < dieCheckBoxes.length; i++) { // On clicks other than first and last click
						if (dieCheckBoxes[i].isSelected()) // Reroll all dice with checkboxes ticked
							reroll(allDice[i], allDiceImages);
					}
				}
				
				if (labelNumber.get() > 0)
					counterLabel.setText(String.valueOf(labelNumber.decrementAndGet())); // Changes the displayed counter
				clicks.incrementAndGet(); // Increments the click counter

			});

		pane.add(title, 0, 0);

		pane.add(firstDie.getImageView(), 0, 1);
		pane.add(secondDie.getImageView(), 1, 1);
		pane.add(thirdDie.getImageView(), 2, 1);
		pane.add(fourthDie.getImageView(), 3, 1);
		pane.add(fithDie.getImageView(), 4, 1);

		pane.add(die1b, 0, 2);
		pane.add(die2b, 1, 2);
		pane.add(die3b, 2, 2);
		pane.add(die4b, 3, 2);
		pane.add(die5b, 4, 2);

		pane.add(rollButton, 0, 4);
		pane.add(bottomText, 1, 4); // Bottom and end text in same cell, one will be hidden while other is displayed.
		pane.add(endText, 1, 4);

		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("Yahtzee");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private void reroll(Dice d, Image[] images) { // Reroll method, also handles setting image
		Random r = new Random();
		int randomD = r.nextInt(6) + 1;

		if (randomD == 1) {
			d.setImageAndValue(images[0], randomD);
		}
		else if (randomD == 2) {
			d.setImageAndValue(images[1], randomD);
		}
		else if (randomD == 3) {
			d.setImageAndValue(images[2], randomD);
		}
		else if (randomD == 4) {
			d.setImageAndValue(images[3], randomD);
		}
		else if (randomD == 5) {
			d.setImageAndValue(images[4], randomD);
		}
		else if (randomD == 6) {
			d.setImageAndValue(images[5], randomD);
		}
	}
	private String identifyHand(Dice[] dice) { // Identifies what hand you have at the end of the game
		int[] occurrences = new int[6]; // how many times respective number appeared
		int[] hand = new int[5]; // dice numbers
		String result = "You got: ";
		
		for (int i = 0; i < dice.length; i++) { // gather dice numbers from objects
			hand[i] = dice[i].getValue();
		}
		
		for (int x : hand) { // count occurrences 
			occurrences[x-1]++;
		}
		
		Arrays.sort(occurrences);
		int threekind = 0;
		int pair = 0;
		// No point in tracking for 5 or 4 matching because that will be the best outcome
		if (occurrences[occurrences.length-1] == 5) { 
			return "You got: Yahtzee";
		}
		if (occurrences[occurrences.length-1] == 4) {
			return "You got: Four of a kind";
		}
		if (occurrences[occurrences.length-1] == 3) {
			threekind = 1;
		}
		if (occurrences[occurrences.length-1] == 2) {
			pair++;
		}
		if (occurrences[occurrences.length-2] == 2) {
			pair++;
		}
		
		if (threekind == 1 && pair == 1) {
			result += "Full house";
		}
		else if (pair == 2) {
			result += "Two pair";
		}
		else if (threekind == 0 && (pair == 0 || pair == 1)) { // If there is a possibility of a straight
			Arrays.sort(hand); // Order ascending
			
			int prev = 0; // previous dice value
			int consecutive = 0; 
			
			for (int dieValue : hand) { // Look for dice values ascending by 1
				if (dieValue == prev+1) {
					consecutive++;
					prev = dieValue;
				}
				else if  (dieValue != prev+1 && dieValue != prev) {
					consecutive = 1;
					prev = dieValue;
				}
			}
			
			if (consecutive == 4) {
				result += "Small straight";
			}
			else if (consecutive == 5) {
				result += "Large straight";
			}
			else if (pair == 1) {
				result += "One pair";
			}
			else {
				result += "Chance";
			}
		}
		
		return result;
	}

	public static void main(String[] args) {
		launch();
	}

	private class Dice { // Private helper class, dice object
		ImageView v = new ImageView(); // Holds value and object reference to the image view
		int currentValue;

		public Dice() {

		}

		public ImageView getImageView() {
			return v;
		}

		public int getValue() {
			return currentValue;
		}

		public void setImageAndValue(Image iPassed, int value) { // Method to set image and current value
			v.setImage(iPassed);
			currentValue = value;
		}
	}
}
