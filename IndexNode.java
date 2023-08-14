package shakespeare;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class IndexNode extends IndexTree{

	String word;
	int occurrences;
	List<Integer> list;
	
	IndexNode left;
	IndexNode right;


	// Constructors
	// Constructor should take in a word and a line number
	// it should initialize the list and set occurrences to 1
	public IndexNode(String word, int lineNumber){
		this.word = word;
		list = new ArrayList<Integer>();
		list.add(lineNumber);
		occurrences = 1;
	}

	// Complete This
	// return the word, the number of occurrences, and the lines it appears on.
	// string must be one line
	public String toString(){
		return word + ", " + occurrences + ", "+list.get(0);
		//+ Arrays.toString(list.toArray()) to add the ENTIRE array of lines
	}
}
