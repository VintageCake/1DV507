package ls223qx_lab1;

import java.io.File;

public class PrintJavaMain {
	public static void main(String[] args) {
		if (args[0] == null) {
			System.err.println("no input provided");
			System.exit(0);
		}
		File file = new File(args[0]);
		if (file.exists()) {
			printAllJavaFiles(file); // recursive
		}
		else {
			System.err.println("directory or file does not exist");
			System.exit(-1);
		}
	}

	
	private static int depth = 0; // keep track of directory depth, starting depth will be 0
	private static int depthModifier = 2; // how much every increment should be

	public static void printAllJavaFiles(File directory) {
		if (directory.isDirectory()) {
			for (int i = 0; i < depth; i++) {
				System.out.print(" ");
			}
			System.out.println(directory.getName());
			File[] fArr = directory.listFiles();
			if (fArr != null && fArr.length != 0) { // prevent operation if access denied, which is the same as fArr being empty
				for (File subFile : fArr) {
					depth += depthModifier; // move deeper on each visit to a new sub directory
					printAllJavaFiles(subFile);
					depth -= depthModifier; // come back every time you leave a sub directory
				}
			}
		}
		else if (directory.isFile() && directory.toString().contains((".java"))) { // find java files
			printFile(directory);
		}
	}

	private static void printFile(File file) {
		for (int i = 0; i < depth; i++) { // pad every file with depth
			System.out.print(" ");
		}
		String x1 = file.getName();
		while (x1.length() < 35) { // Buffer string to at least 35 characters long, to make output length
									// reasonably readable
			x1 += "-";
		}
		x1 += " " + file.length() + " bytes";
		System.out.println(x1);
	}
}
