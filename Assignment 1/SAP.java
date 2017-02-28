import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.BST;

import java.util.ArrayList;


public class SAP {

	private static class WordNet {
		Digraph net;
		BST<String, String[]> synMap = new BST();
	
		// constructor takes the name of the two input files
		public WordNet(String synsets, String hypernyms) {
			In synStr = new In(synsets);
			In hypStr = new In(hypernyms);
			
			// handle synset input
			
			while (synStr.hasNextLine()) {
				String line = synStr.readLine();
				String [] lineElements = line.split(",");
				String synset = lineElements[1];
				String [] idGlossPair = {lineElements[0], lineElements[2]};
				System.out.println(lineElements[0]);
				System.out.println(idGlossPair);
				synMap.put( synset, idGlossPair );
			}
			this.net = new Digraph(synMap.size());

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
		public boolean isNoun(String word) { return synMap.contains(word); }

		// a synset (second field of synsets.txt) that is a shortest common ancestor
		// of noun1 and noun2 (defined below)
		//public String sca(String noun1, String noun2) {

		//}

		// distance between noun1 and noun2 (defined below)
		//public int distance(String noun1, String noun2) {

		//}

		// do unit testing of this class
		
	}

	private static WordNet myNet;

	public static void main(String[] args) {
		myNet = new WordNet("synsets.txt", "hypernyms.txt");
		System.out.println(myNet.isNoun("Aegina"));
	}
}