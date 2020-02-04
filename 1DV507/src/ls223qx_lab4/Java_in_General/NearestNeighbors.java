package ls223qx_lab4.Java_in_General;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class NearestNeighbors extends Application {
	private static ArrayList<Chip> chips = new ArrayList<>(); // Static arraylist that will hold training data

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Microchips");
		final NumberAxis xAxis = new NumberAxis(-1.2, 1.2, 0.1);
		final NumberAxis yAxis = new NumberAxis(-1.2, 1.2, 0.1);
		final ScatterChart<Number, Number> scatter = new ScatterChart<>(xAxis, yAxis);
		scatter.setTitle("Microchips Overview");
		
		xAxis.setLabel("Property 1");
		yAxis.setLabel("Property 2");
		XYChart.Series<Number,Number> series1 = new XYChart.Series<>();
		series1.setName("Pass");
		XYChart.Series<Number,Number> series2 = new XYChart.Series<>();
		series2.setName("Fail");
		
		for (Chip chip : chips) { // Add all chips to series
			if (chip.pass == 1) {
				series1.getData().add(new XYChart.Data<>(chip.x, chip.y));
			}
			else {
				series2.getData().add(new XYChart.Data<>(chip.x, chip.y));
			}
		}
		
		scatter.getData().addAll(series1, series2);
		Scene s = new Scene(scatter, 800, 800);
		s.getStylesheets().add("ls223qx_lab4/Java_in_General/chart.css"); // Some basic css to change symbol and symbol colour
		stage.setScene(s);
		stage.setResizable(false);
		stage.show();

	}

	public static void main(String[] args) {
		File f = null;
		int k = 0;
		try {
			f = new File(args[0]);
			k = Integer.parseInt(args[1]);
		}
		catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Please provide an input file using the file as the first argument, k value as the second argument");
			System.exit(1);
		}

		try (Scanner s = new Scanner(f)) {
			String x;
			String y;
			String val;
			int count = 0;
			while (s.hasNext()) {
				String[] splitLine = s.next().split(",");
				x = splitLine[0];
				y = splitLine[1];
				val = splitLine[2];

				chips.add(new Chip(Double.parseDouble(x), Double.parseDouble(y), Integer.parseInt(val)));
				count++;
			}
			System.out.println("Found " + count + " chips");

		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		Chip c1 = new Chip(-0.3, 1.0);
		Chip c2 = new Chip(-0.5, -0.1);
		Chip c3 = new Chip(0.6, 0.0);

		System.out.println(c1.toString() + " -> " + (predictValue(c1, k) == 1 ? "Pass" : "Fail")); // Evaluate 3 chips as per the assignment
		System.out.println(c2.toString() + " -> " + (predictValue(c2, k) == 1 ? "Pass" : "Fail"));
		System.out.println(c3.toString() + " -> " + (predictValue(c3, k) == 1 ? "Pass" : "Fail"));

		launch(); // Display graph
	}

	private static double getDistance(Chip c1, Chip c2) { // Find distance between two chips
		return Math.hypot(c1.x - c2.x, c1.y - c2.y); // Distance formula made easy
	}

	private static Chip[] findClosest(Chip c, int k) { // Look through all training data, attach distance from evaluation chip to all other chips in a custom class.
		ArrayList<DistancePair> dis = new ArrayList<>();
		for (Chip chip : chips) {
			Double distance = getDistance(c, chip);
			dis.add(new DistancePair(distance, chip));
		}
		dis.sort(null); // Sort based on comparator, least distance goes first
		Chip[] hold = new Chip[k];
		for (int i = 0; i < k; i++) { // Get k amount of chips, in order with the least distance first
			hold[i] = dis.get(i).chip;
		}
		return hold;
	}

	private static int predictValue(Chip c, int k) {
		if (k == 0) {
			throw new IllegalArgumentException("k value can not be 0");
		}
		Chip[] closest = findClosest(c, k);
		int pass = 0;
		int fail = 0;
		for (Chip chip : closest) { // Evaluate the closest chips, count number of pass/fail chips.
			if (chip.pass == 1) {
				pass++;
			}
			else {
				fail++;
			}
		}
		if (fail > pass) {
			return 0;
		}
		else {
			return 1;
		}
	}
	
	// Holds x, y and pass/fail value
	// Not bothering with encapsulation since this should never see the light of day outside this class.
	private static class Chip {
		double x;
		double y;
		int pass;

		Chip(double xPass, double yPass, int p) {
			x = xPass;
			y = yPass;
			pass = p;
		}

		Chip(double xPass, double yPass) { // Overloaded for chips to be evaluated.
			x = xPass;
			y = yPass;
		}
		public String toString() { // Simple toString, didn't bother with pass/fail.
			return "["+ Double.toString(x) + " " + Double.toString(y) + "]";
		}
	}
	// Pairs a chip with a distance value along with a comparable.
	// This is used for comparison with a singular chip, the comparable allows me to later sort an array list so that lowest distance comes first.
	private static class DistancePair implements Comparable<DistancePair> { 
		Chip chip;
		Double distance;

		DistancePair(double dis, Chip c) {
			chip = c;
			distance = dis;
		}

		@Override
		public int compareTo(DistancePair pPair) {
			return distance.compareTo(pPair.distance);
		}
	}
}
