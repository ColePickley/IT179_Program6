/*
 * Created on: December 2, 2025
 * 
 * ULID: ctpickl
 * Class: IT 179
 */
package ilstu.edu;

/*
 * Stores tree data and executes tree functions
 * 
 * @author Cole Pickley
 */
public class MorseTree {
	
	protected Node root;
	
	/**
	 * Constructor for MorseTree object
	 * Sets the root Node to the Node parameter
	 * 
	 * @param root
	 */
	public MorseTree(Node root) {
		this.root = root;
	}
	
	/**
	 * Calls the recursive helper method getLetter(String, Node, int)
	 * Returns the letter represented by the Morse Code contained in the first String parameter as a String
	 * Returns null if the letter doesn't exist
	 * 
	 * @param code
	 * @return String
	 */
	public String getLetter(String code) {
		return this.getLetter(code, this.root, 0);
	}
	
	/**
	 * Recursive helper method for getLetter(String)
	 * Returns the letter represented by the Morse Code contained in the first String parameter as a String
	 * Returns null if the letter doesn't exist
	 * 
	 * @param code
	 * @param start
	 * @param charIndex
	 * @return String
	 */
	private String getLetter(String code, Node start, int charIndex) {
		if (start == null)
			return null;
		if (charIndex > code.length() - 1)
			return start.letter;
		if (code.charAt(charIndex) == '.')
			return this.getLetter(code, start.left, charIndex + 1);
		if (code.charAt(charIndex) == '-')
			return this.getLetter(code, start.right, charIndex + 1);
		return null;
	}
	
	/**
	 * Calls the recursive helper method add(Node, String, String, int) to add a Node to the tree
	 * Location of the Node is based on the Morse Code pattern contained in the first String parameter
	 * Letter of the Node is set as the second String parameter
	 * 
	 * @param code
	 * @param letter
	 */
	public void add(String code, String letter) {
		this.root = this.add(this.root, code, letter, 0);
	}
	
	/**
	 * Recursive helper method for add(String, String)
	 * Adds a new Node to the tree
	 * Returns the start Node if the start Node is not null
	 * Returns the newly created Node being added if the start Node is null
	 * 
	 * @param start
	 * @param code
	 * @param letter
	 * @param charIndex
	 * @return Node
	 */
	private Node add(Node start, String code, String letter, int charIndex) {
		if (start == null)
			return new Node(letter);
		if (code.charAt(charIndex) == '.') {
			start.left = this.add(start.left, code, letter, charIndex + 1);
			return start;
		}
		start.right = this.add(start.right, code, letter, charIndex + 1);
		return start;
	}
	
	/*
	 * Stores a left and right Node as well as a letter stored as a String
	 * 
	 * @author Cole Pickley
	 */
	protected static class Node {
		protected String letter;
		protected Node left;
		protected Node right;
		
		/**
		 * Constructor for a Node object
		 * Sets letter to the String parameter
		 * 
		 * @param letter
		 */
		public Node(String letter) {
			this.letter = letter;
		}
	}
}
