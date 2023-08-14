package shakespeare;

import javax.annotation.processing.SupportedSourceVersion;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


// Your class. Notice how it has no generics.
// This is because we use generics when we have no idea what kind of data we are getting
// Here we know we are getting two pieces of data:  a string and a line number
public class IndexTree{

	// This is your root 
	// again, your root does not use generics because you know your nodes
	// hold strings, an int, and a list of integers
	private IndexNode root;
	
	// Make your constructor
	// It doesn't need to do anything
	public IndexTree() {
	}
	// complete the methods below
	
	// this is your wrapper method
	// it takes in two pieces of data rather than one
	// call your recursive add method
	public void add(String word, int lineNumber){
		root = add(root,word,lineNumber);
	}
	

	// your recursive method for add
	// Think about how this is slightly different the regular add method
	// When you add the word to the index, if it already exists, 
	// you want to  add it to the IndexNode that already exists
	// otherwise make a new indexNode
	private IndexNode add(IndexNode root, String word, int lineNumber){
		if (root == null) {
			return new IndexNode(word, lineNumber);
		}
		if (word.compareTo(root.word) == 0){
			root.occurrences++;
			root.list.add(lineNumber);
		}
		//if root word is alphabetically before the word,
		//so it returns a value > 0 if root.word is before word
		if (word.compareTo(root.word) > 0){
			root.right = add(root.right,word,lineNumber);
		}
		//if root word is alphabetically after the word,
		//so it returns a value < 0 if root.word is after word
		else if (word.compareTo(root.word) < 0){
			root.left = add(root.left,word,lineNumber);
		}
		//it already exists
		return root;
	}
	
	
	
	
	// returns true if the word is in the index
	public IndexNode findNode(String word){
		return findNode(root, word);
	}
	public IndexNode findNode(IndexNode root, String word){
		if (root == null){
			return null;
		}
		if (word.compareTo(root.word)==0){
			//System.out.println("Equal");
			return root;
		}
		//if root word is after the word
		if (word.compareTo(root.word) < 0){
			//System.out.println("word is < root going to the left " + root.word );
			return findNode(root.left, word);

		}
		//if root word is before the word
		if (word.compareTo(root.word) > 0){
			//System.out.println("word is > root going to the right " + root.word );
			return findNode(root.right, word);
		}
		return root;
	}
	public boolean contains(String word){
		return contains(root, word);
	}
	public boolean contains(IndexNode root, String word){
		//System.out.println(root.word);
		if (root == null){
			return false;
		}
		if (word.compareTo(root.word)==0){
			//System.out.println("Equal");
			return true;
		}
		//if root word is after the word
		if (word.compareTo(root.word) < 0){
			//System.out.println("word is < root going to the left " + root.word );
			return contains(root.left, word);

		}
		//if root word is before the word
		if (word.compareTo(root.word) > 0){
			//System.out.println("word is > root going to the right " + root.word );
			return contains(root.right , word);
		}
		return false;
	}
	
	// call your recursive method
	// use book as guide

	//This method searches the right subtree for the node (to the left) containing the first
	//alphabetical word, and it returns the word associated with that node
	private IndexNode firstRightSubtreeNodeWord(IndexNode root) {
		if (root.left == null){
			return root;
		}
		else{
			return firstRightSubtreeNodeWord(root.left);
		}
	}
	public void delete(String word){
		root = delete(root,word);
	}
	
	// your recursive case
	// remove the word and all the entries for the word
	// This should be no different than the regular technique.
	private IndexNode delete(IndexNode root, String word){
		if (root == null){
			return null;
		}
		if (word.compareTo(root.word)==0){
			//The root node is a leaf node
			if (root.left == null && root.right == null) {
				return null;
			}
			//The root node only has 1 child
			if (root.right == null) {
				return root.left;
			}
			if (root.left == null) {
				return root.right;
			}
			//The root node has two children
			IndexNode firstWord = firstRightSubtreeNodeWord(root.right);
			root = firstWord;
//			root.right = delete(root.right, firstWord);
			return root;
		}
		//if root word is alphabetically before the word,
		//so it returns a value > 0 if root.word is before word
		if (word.compareTo(root.word) > 0){
			root.right = delete(root.right,word);
			return root;
		}
		//if root word is alphabetically after the word,
		//so it returns a value < 0 if root.word is after word
		else if (word.compareTo(root.word) < 0){
			root.left = delete(root.left,word);
			return root;
		}
		return root;
	}
	
	
	// prints all the words in the index in inorder order
	// To successfully print it out
	// this should print out each word followed by the number of occurrences and the list of all occurrences
	// each word and its data gets its own line

	public void printIndex(){
		printInorder(root);
	}
	void printInorder(IndexNode node)
	{
		if (node == null)
			return;

		/* first recur on left child */
		printInorder(node.left);

		/* then print the data of node */
		System.out.println(node.toString());


		/* now recur on right child */
		printInorder(node.right);
	}

//	public void printIndex(IndexNode node){
//		if (node != null) {
//			printIndex(node.left);
//			System.out.println(node.word+node.list.toString()+ node.occurrences);
//			printIndex(node.right);
//		}
//	}
	
	public static void main(String[] args) throws FileNotFoundException {
		IndexTree index = new IndexTree();
		File text = new File("C:\\Users\\lahod\\IdeaProjects\\Module 6\\src\\shakespeare\\shakespeare.txt");
		Scanner sc = new Scanner(text);
		String string;
		String word = "";
		int lineNumber = 0;
//		index.add("d", 1);
//		index.add("b", 1);
//
//		index.add("a", 1);
//		index.add("a", 2);
//		index.add("e", 10);
//		if (index.contains("b")) {
//			System.out.println("b was found");
//		} else{
//			System.out.println("b was not found");
//		}
//		index.delete("a");
//		index.delete("b");
//		index.delete("c");
		//add all the words to the tree
		while (sc.hasNextLine()){
			lineNumber++;
			string = sc.nextLine();
			string = string.replaceAll("\\p{Punct}","");
			string = string.replaceAll("\\d+", "");
			//whitespace removal
			string = string.replaceAll("\\s+"," ").trim();
			string = string.toLowerCase() + ' ';
			int wordCount = 0;
			for (int k = 0; k < string.length(); k++){
				if (string.charAt(k) == ' '){
					wordCount++;
				}
			}
			int i = 0;
			for (int j = 0; j < wordCount; j++) {
				word = "";
				while (string.charAt(i) != ' ') {
					word += string.charAt(i);
					i++;
				}
				i++;
				//add nodes to tree here
				index.add(word,lineNumber);
			}
		}
		// print out the index
		index.printIndex();
		System.out.println("What word do you want to find?");
		Scanner input = new Scanner(System.in);
		String find = input.nextLine();
		try {
			if (index.contains(find)) {
				System.out.println(index.findNode(find).toString());
			}
		}
		catch(NullPointerException e){
			System.out.println("it does not contain the word");
		}
		// test removing a word from the index
		index.delete("hell");
		try {
			System.out.println(index.findNode("hell").toString());
		}
		catch (NullPointerException d){
			System.out.println("it has been deleted");
		}
		//I DID IT!!!!
	}
}