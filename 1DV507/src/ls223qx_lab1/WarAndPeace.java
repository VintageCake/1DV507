package ls223qx_lab1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Stream;

public class WarAndPeace {
	public static void main(String[] args) throws IOException {
		File input = null;
		
		if (args.length != 0) {
			input = new File(args[0]);
		}
		else {
			System.err.println("Needs input parameters");
			System.exit(1);
		}
		
		String wholeBook = null;
		if (input.exists()) {
			try {
				wholeBook = readText(input);
			}
			catch (FileNotFoundException e) {
				System.err.println("Something went wrong");
				System.exit(-1);
			}
		}
		else {
			System.err.println("file not found");
			System.exit(-1);
		}
		String[] allWords = wholeBook.split(" ");
		System.out.println("Wordcount non-unique: " + allWords.length);
		// I am very suspicious about the fact that the word count differs from what's in the example code.
		// The word count that i get is 562500 with the scanner method.
		
		// Using a new String(readAllBytes, etc) implementation i got an even lower word count of around 511k.
		
	 Stream<String> s = Arrays.stream(allWords)
			 .map(str -> str.toLowerCase().replaceAll("[^a-zA-Z'-]", ""))
			 .filter(str -> !str.isEmpty())
			 .distinct();
	 // .stream Stream the allWords string array.
	 // .map will make all the words lower case and replace all characters that do NOT match the given regex.
	 // The regex can essentially be translated to: all characters that do not match a-z, A-Z, ', -,
	 // replaceAll will then replace all characters that don't match that regex with "" (nothing).
	 
	 // regex tested with https://regex101.com/
	 
	 // .filter will pick all elements that are NOT empty.
	 // .distinct will make sure all elements are unique
	 System.out.println("Unique wordcount: " + s.count()); // Output the stream.count() result.
	 // I get 20184.
	}
	
	public static String readText(File f) throws FileNotFoundException {
		Scanner sc = new Scanner(new FileInputStream(f));
		StringBuilder str = new StringBuilder();
		while (sc.hasNext()) {
			str.append(sc.next() + " ");
		}
		sc.close();
		return str.toString();
		
	}
	/*public static String readFile(File f)  // Alternative method which gives weird result
			  throws IOException 
			{
			  byte[] encoded = Files.readAllBytes(Paths.get(f.toString()));
			  return new String(encoded, Charset.defaultCharset());
			}*/
}
