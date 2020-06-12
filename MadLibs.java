
/* Name:Kesojan Premakumar
 * March 26 2020
 * MadLib Game
 * Input= Words according to the madlib (nouns,adjectives,etc)
 * Output=Generates new madlib file with your words which can be accessed through view
 */
import java.util.*;
import java.io.*;

public class MadLibs {
	public static void main(String[] args) throws FileNotFoundException {
		// main menu acts as UI for program
		Scanner console = new Scanner(System.in);
		int quit = 0;
		System.out.println("Welcome to the game of Mad Libs.");
		System.out.println("I will ask you to provide various words and phrases to fill in a story.");
		System.out.println("The result will be written to an output file.\n");
		// loop to keep playing
		while (quit != 1) {
			System.out.print("(C)reate mad-lib, (V)iew mad-lib, (Q)uit? ");
			String decision = console.nextLine();

			if (decision.equals("C") || decision.equals("c")) {
				System.out.print("Input file name: ");
				String fileName = console.nextLine();
				vowelCheck(fileName);
				createMadLib(madLibExists(fileName, console), console);
			} else if (decision.equals("V") || decision.equals("v")) {
				System.out.print("Input file name: ");
				String fileName = console.nextLine();
				view(madLibExists(fileName, console));
			} else if (decision.equals("Q") || decision.equals("q")) {
				quit = 1;
			}
		}
	}

	public static String vowelCheck(String word) {
		// this method checks to assign correct transition word
		String transition = " ";
		//split up the capitals and lowercase to meet the 100 char line limit
		if (word.startsWith("a") || word.startsWith("e") || word.startsWith("i")) {
			transition = "an";
		} 
		else if (word.startsWith("A") || word.startsWith("E") || word.startsWith("I")) {
			transition = "an";
		}
		else if (word.startsWith("o") || word.startsWith("u")) {
			transition = "an";
		}
		else if (word.startsWith("O") || word.startsWith("U")) {
				transition = "an";
		} 
		else {
			transition = "a";
		}
		return transition;
	}

	public static void view(String fileName) throws FileNotFoundException {
		// this method allows users to view the file
		Scanner input = new Scanner(new File(fileName));
		System.out.println();

		// printing out line by line the user choice of file
		while (input.hasNextLine()) {
			String text = input.nextLine();
			System.out.println(text);
		}
		System.out.println();
	}

	public static String madLibExists(String fileName, Scanner console) {
		// this method checks if user input file exists in system
		File fileCheck = new File(fileName);
		while (!fileCheck.exists()) {
			System.out.print("File not found. Try again: ");
			fileName = console.nextLine();
			fileCheck = new File(fileName);
		}
		return fileName;
	}

	public static void createMadLib(String fileName, Scanner console) throws FileNotFoundException {
		// this method creates the madlib based on user input
		Scanner input = new Scanner(new File(fileName));
		System.out.print("Output file name: ");
		String outputName = console.nextLine();
		System.out.println();
		PrintStream output = new PrintStream(outputName);
		// loop to parse through word by word and line by line
		while (input.hasNextLine()) {
			String Line = input.nextLine();
			Scanner words = new Scanner(Line);
			while (words.hasNext()) {
				String word = words.next();
				if (word.startsWith("<") && word.endsWith(">")) {
					System.out.print("Please type " + vowelCheck(getWord(word)) + " " + getWord(word) + ": ");
					output.print(console.nextLine() + " ");
				} else {
					output.print(getWord(word) + " ");
				}
			}
			output.println();
		}
		System.out.println("Your mad-lib has been created!\n");
	}

	public static String getWord(String word) {
		// retrieves correct word without dashes and other factors
		if (word.length() != 1) {
			if (word.startsWith("<") && word.endsWith(">")) {
				// replaces the irrelevent characters
				word = word.replace("<", "");
				word = word.replace(">", "");
				for (int i = 0; i < word.length(); i++) {
					if (word.charAt(i) == '-') {
						char letter = word.charAt(i);
						word = word.replace(letter, ' ');
					}
				}
			}
		}
		return word;
	}
}
