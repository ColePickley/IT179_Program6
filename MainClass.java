/*
 * Created on: December 2, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

import java.util.Scanner;

import ilstu.edu.MorseTree.Node;
import java.io.File;
import java.io.FileNotFoundException;

/*
 * Runs and controls the flow of the program
 * 
 * @author Cole Pickley
 */
public class MainClass {
	
	private static MorseTree tree;
	
	/**
	 * Runs the program
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		boolean programRunning = true;
		System.out.println("Welcome!");
		buildTree();
		while (programRunning) {
			System.out.println("Please enter the line of morse code that you want to be decoded (use '.' and '-' with spaces between letters):");
			decode(scan.nextLine());
			System.out.println("Would you like to decode something else? (y/n)");
			if (scan.next().equals("n"))
				programRunning = false;
			scan.nextLine();
			System.out.println();
		}
		System.out.println("Have a nice day!");
		scan.close();
	}
	
	/**
	 * Creates a MorseTree object and fills it by reading the 'MorseCode.txt' file
	 */
	private static void buildTree() {
		tree = new MorseTree(new Node(null));
		try {
			Scanner scan = new Scanner(new File("MorseCode.txt"));
			for (int i = 0; i < 25; i++) {
				String letter = scan.next();
				String code = scan.next();
				tree.add(code, letter);
				scan.nextLine();
			}
			String letter = scan.next();
			String code = scan.next();
			tree.add(code, letter);
			scan.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Decodes the inputed Morse Code and prints the new message
	 * 
	 * @param encodedString
	 */
	private static void decode(String encodedString) {
		Scanner scan = new Scanner(encodedString);
		String decodedString = "";
		boolean inputValid = true;
		if (encodedString.equals("")) {
			System.out.println();
			System.out.println("Please enter valid Morse Code.");
			System.out.println();
			inputValid = false;
		}
		while (scan.hasNext()) {
			String letter = tree.getLetter(scan.next());
			if (letter == null) {
				System.out.println();
				System.out.println("Please enter valid Morse Code.");
				System.out.println();
				inputValid = false;
				break;
			}
			decodedString += letter;
		}
		if (inputValid) {
			System.out.println();
			System.out.println("Your decoded message:");
			System.out.println(decodedString);
			System.out.println();
		}
		scan.close();
	}
}
