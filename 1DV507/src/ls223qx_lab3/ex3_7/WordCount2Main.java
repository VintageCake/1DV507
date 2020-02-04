package ls223qx_lab3.ex3_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

@SuppressWarnings("unused")
public class WordCount2Main {
	public static void main(String[] args) {
		File in = new File("C:\\Temp\\words.txt");
		if (!in.exists()) {
			System.out.println("file not found");
			System.exit(-1);
		}
		WordSet hSet = new HashWordSet();
		WordSet tSet = new TreeWordSet();
		try (Scanner sc = new Scanner(in)) {
			while (sc.hasNext()) {
				String s = sc.next();
				tSet.add(new Word(s));
				hSet.add(new Word(s));
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Wordcount tree-set " + tSet.size()); // Returns 349, probably because the words with capitalisation and without are considered duplicates.
		System.out.println("Wordcount hash-set " + hSet.size());
		
		Iterator<Word> iterationT = tSet.iterator();
		Iterator<Word> iterationH = hSet.iterator();
		 
		 while (iterationT.hasNext()) { // Change to iterationH to check hash iteration.
			System.out.println(iterationT.next().toString());
		}
		
		

	}

}
