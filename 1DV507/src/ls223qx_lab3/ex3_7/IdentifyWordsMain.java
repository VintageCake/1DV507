package ls223qx_lab3.ex3_7;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class IdentifyWordsMain {
	public static void main(String[] args) { // Code is partially copied from 1dv507 lab1 WarAndPeace exercise.
		File input = null;
		File output = new File("C:\\Temp\\words.txt"); // Modify this to your location of choice!!
		
		if (args.length != 0) {
			input = new File(args[0]);
		}
		else {
			System.err.println("Needs input parameters, use run configurations");
			System.exit(1);
		}
		
		String[] allWords = null;
		if (input.exists()) {
			try {
				allWords = readWholeWords(input);
			}
			catch (FileNotFoundException e) {
				System.err.println("Something went wrong, file doesn't exist");
				System.exit(-1);
			}
		}
		else {
			System.err.println("file not found");
			System.exit(-1);
		}
		System.out.println("Wordcount non-unique: " + allWords.length);
		try {
			writeToFile(allWords, output);
		}
		catch (IOException e) {
			System.out.println("Something went wrong");
		}
	}

	public static String[] readWholeWords(File f) throws FileNotFoundException {// Lots of room for improvement, but this 'works' so whatever.
		Scanner sc = new Scanner(new FileInputStream(f));
		StringBuilder str = new StringBuilder();
		while (sc.hasNext()) {
			String holdWord = sc.next();
			String holdWordProcessed = "";
			for (int i = 0; i < holdWord.length(); i++) { 
				char c = holdWord.charAt(i);
				if (Character.isLetter(c)) {
					holdWordProcessed += c;
				}
			}
			if (holdWordProcessed.length() != 0) { // Prevents empty entries
				str.append(holdWordProcessed + " "); // A better idea would be to use a list for holding 'finished'
														// words.
			}
		}
		sc.close();
		String hold = str.toString();
		String[] allWords = hold.split(" ");

		return allWords;

	}

	public static void writeToFile(String[] in, File out) throws IOException {
		if (!out.exists()) {
			out.createNewFile();
			System.out.println("Created new file");
		}
		else {
			out.delete();
			out.createNewFile();
			System.out.println("Replaced old file with empty file");
		}
		System.out.println("......");
		BufferedWriter outputWriter = null;
		outputWriter = new BufferedWriter(new FileWriter(out));
		for (int i = 0; i < in.length; i++) { // Write to file, separation with a newline.
			outputWriter.write(in[i]);
			outputWriter.newLine();
		}
		System.out.println("Processing finished, closing file.");
		outputWriter.close();
	}

}
