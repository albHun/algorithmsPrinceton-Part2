import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.RedBlackBST;

import java.util.ArrayList;

public class WordNet {
	Digraph net;
	RedBlackBST<Integer, String> idSynMap = new RedBlackBST<Integer, String>();
	RedBlackBST<String, String> synGloMap = new RedBlackBST<String, String>();

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		In synStr = new In(synsets);
		In hypStr = new In(hypernyms);
		
		// handle synset input
		
		while (synStr.hasNextLine()) {
			String line = synStr.readLine();
			String [] lineElements = line.split(",");
			idSynMap.put( Integer.parseInt(lineElements[0]), lineElements[1] );
			System.out.println(Integer.parseInt(lineElements[0]));
			System.out.println(lineElements[1]);
			for (String noun : lineElements[1].split(" ")) {
				if (!synGloMap.contains(noun)) {
					synGloMap.put(noun, lineElements[2]);
					System.out.println(noun);
					System.out.println(lineElements[2]);
				}
			}
		}

		this.net = new Digraph(idSynMap.size());

		// handle hypernyms and construct Digraph
		while (hypStr.hasNextLine()) {
			String [] lineElements = hypStr.readLine().split(",");
			int synID = Integer.parseInt(lineElements[0]);
			for (int i = 1; i < lineElements.length; i++) {
				int hypID = Integer.parseInt(lineElements[i]);
				this.net.addEdge(synID, hypID);
			}
		}
	}

	// all WordNet nouns
	// public Iterable<String> nouns() {

	//}

	// is the word a WordNet noun?
	public boolean isNoun(String word) { return synGloMap.contains(word); }

	// a synset (second field of synsets.txt) that is a shortest common ancestor
	// of noun1 and noun2 (defined below)
	//public String sca(String noun1, String noun2) {

	//}

	// distance between noun1 and noun2 (defined below)
	//public int distance(String noun1, String noun2) {

	//}

	// do unit testing of this class

	public static void main(String[] args) {
		WordNet myNet = new WordNet("synsets.txt", "hypernyms.txt");
		System.out.println(myNet.isNoun("Aegina"));
	}
}