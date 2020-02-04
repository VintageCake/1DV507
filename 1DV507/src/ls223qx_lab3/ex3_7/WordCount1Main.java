package ls223qx_lab3.ex3_7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

@SuppressWarnings("unused")
public class WordCount1Main {
	public static void main(String[] args) {
		File in = new File("C:\\Temp\\words.txt");
		if (!in.exists()) {
			System.out.println("file not found");
			System.exit(-1);
		}
		Set<Word> tSet = new TreeSet<>();
		Set<Word> hSet = new HashSet<>();
		
		try (Scanner sc = new Scanner(in)) { // Put all words in both sets
			while (sc.hasNext()) {
				String s = sc.next();
				tSet.add(new Word(s));
				hSet.add(new Word(s));
			}
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(-1);
		}
		
		System.out.println("Wordcount tree-set " + tSet.size()); // 366 with a String hash-set and 349 with a Word hash set.
		System.out.println("Wordcount hash-set " + hSet.size());
		
		/* Code below shows that the tree set iteration returns stuff in alphabetical order, but produces a lot of output.
		Iterator<Word> iteration = tSet.iterator();
		 
		 while (iteration.hasNext()) {
			System.out.println(iteration.next().toString());
		}
		*/
		

	}

}
