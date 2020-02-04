package ls223qx_lab1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import org.knowm.xchart.CategoryChart;
import org.knowm.xchart.CategoryChartBuilder;
import org.knowm.xchart.PieChart;
import org.knowm.xchart.PieChartBuilder;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.style.Styler.LegendPosition;

public class HistogramXChart {
	public static void main(String[] args) {
		GenerateRandomTempFile.main(null);
		PieChart pieChart = new PieChartBuilder().width(800).height(600).build(); // Build piechart and blockchart
																					// objects
		pieChart.getStyler().setLegendPosition(LegendPosition.InsideSW);
		CategoryChart blockChart = new CategoryChartBuilder().width(800).height(600).xAxisTitle("Values").build();

		File randomNumbersFile = null; // Empty object declared to be filled later.

		if (args.length != 0) {
			System.out.println("Reading from: " + args[0]);
			randomNumbersFile = new File(args[0]); // Set object file to input argument
			if (!randomNumbersFile.exists()) {
				System.out.println("File does not exist!");
			}
		}
		else {
			randomNumbersFile = new File("C:\\Temp\\heltal.dat"); // Otherwise, use this file at specified location
			System.out.println("Using hardcoded file path");
		}

		int[] simpleCounter = new int[10]; // Counter array
		//List<String> xValues = new ArrayList<String>(); // Will later be used as bottom text for the bar chart
		int[] xValues = new int[simpleCounter.length];

		try (Scanner fileScan = new Scanner(randomNumbersFile)) { // Large try-catch block for scanner file input and
																	// .nextInt handling.

			while (fileScan.hasNextInt()) {
				int currentNumber = fileScan.nextInt();

				for (int i = 0; i < 10; i++) { // Figure out what number range currentNumber belongs to.
					if (currentNumber >= 1 + (i * 10) && currentNumber <= 10 + (i * 10)) {
						simpleCounter[i]++;
					}
					//xValues.add(1 + (i * 10) + "-" + (10 + (i * 10)));
					xValues[i] = i+1;
				}
			}
		}
		catch (InputMismatchException e) {
			System.out.println(e.getMessage());
		}
		catch (FileNotFoundException e) {
			System.out.println("File not found");
			System.exit(-1);
		}
		catch (Exception e) { // Generic exception handling
			e.printStackTrace();
			System.exit(-1);
		}
		System.out.println(Arrays.toString(simpleCounter)); // Debug

		for (int i = 0, j = 1; i < 10; i++, j++) { // Fill pie chart
			pieChart.addSeries("" + j, simpleCounter[i]);
		}
		blockChart.addSeries("Frequency", xValues, simpleCounter); // This function does not take an array of strings as x-axis label, which i find quite weird.
		new SwingWrapper<>(pieChart).displayChart(); // Display the two different charts, very basic and super generic...
		new SwingWrapper<>(blockChart).displayChart(); // More or less just exists as a proof of concept, the data itself is not very readable because of x-axis labels.
	}

}

class GenerateRandomTempFile { // This class generates a file with random numbers in the C:\Temp folder,
	// File generated is used as input for histogram class.
	public static void main(String[] args) {
		File file = new File("C:\\Temp\\heltal.dat");
		Random rand = new Random(); 

		if (file.exists()) {
			System.out.println("File already exists");
			file.delete(); // Modified slightly to generate a new file on every run
		}
			try {
				if (file.createNewFile()) {
					System.out.println("Generating random file");
					PrintWriter output = new PrintWriter("C:\\Temp\\heltal.dat");
					for (int i = 0; i < 500; i++) { // Generate 50 numbers from -149 to 149
						if (i % 2 == 0) {
							output.println(rand.nextInt(150));
						}
						else {
							output.println(-rand.nextInt(150));
						}
					}
					output.close();
				}
			}
			catch (IOException ex) {
				System.err.println("Something went wrong, it is likely that the folder does not exist");
				ex.printStackTrace();
			}
		}
	}