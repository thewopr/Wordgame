import java.util.*;
import java.io.*;

public class Wordgame {

	public static void main(String args[]) {

		System.out.print("Welcome to the WordGame\n\nBuilding graph....");

		Scanner userInput, fileInput = null;

		userInput = new Scanner(System.in);

		File f = new File(args[0]);

		try {
			fileInput = new Scanner(f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		ArrayList<Vertex> graph = initGraph(fileInput);

		Vertex source;
		Vertex target;

		while (true) {

			System.out.print("Enter the first five-letter word: ");
			String word1 = userInput.next().toUpperCase();

			if ((target = searchFor(word1, graph)) == null) {
				System.out.println("Sorry " + word1
						+ " was not in the dictionary\n");
			} else {

				System.out.print("Enter the second five-letter word: ");
				String word2 = userInput.next().toUpperCase();

				if ((source = searchFor(word2, graph)) == null) {
					System.out.println("Sorry " + word1
							+ " was not in the dictionary\n");
				} else {
					// COMMNENCE WITH DISJKTRAS

					for (Vertex v : graph) {
						v.setDistance(Integer.MAX_VALUE);
						v.predecessor = null;
					}

					dijkstra(source, target);
				}
			}

			askToTryAgain(userInput);
		}
	}

	private static ArrayList<Vertex> initGraph(Scanner fileInput) {
		ArrayList<Vertex> graph = new ArrayList<Vertex>();
		long curTime = System.currentTimeMillis();
		while (fileInput.hasNext()) {

			if (System.currentTimeMillis() - curTime >= 100) {
				curTime = System.currentTimeMillis();
				System.out.print(".");
			}

			Vertex temp = new Vertex(fileInput.next());

			graph.add(temp);

			for (int i = 0; i < graph.size(); i++) {
				Vertex tryandadd = (Vertex) graph.get(i);
				if (!tryandadd.equals(temp))
					temp.addVertex(tryandadd);
			}
		}
		System.out.print("\n\n");
		return graph;
	}

	private static void dijkstra(Vertex source, Vertex target) {
		source.setDistance(0);

		Heap Q = new Heap();
		Q.add(source);

		while (Q != null && !Q.isEmpty()) { // Loop until the PQ is
			Vertex u = (Vertex) Q.removeMin();

			if(u.equals(target)) {
				foundit(source, target);
				break;
			}
			for (Vertex v : u.edges) {

				if (v.getDistance() > u.getDistance() + v.diff(u)) {
					v.setDistance(u.getDistance() + v.diff(u));
					v.predecessor = u;
					Q.add(v);
				}
			}
		}

	}

	private static void foundit(Vertex source, Vertex temp) {
		int cost = 0;
		String s = "";
		Vertex target = temp;
		while (!temp.equals(source)) {
			s += String.format("%s  ", temp.word);
			cost += temp.diff(temp.predecessor);
			temp = temp.predecessor;
		}
		s += String.format("%s  ", temp.word);

		System.out.printf("The best score for %s to %s is %d points.\n\n",
				source.word, target.word, cost);
		System.out.printf("\t%s\n\n", s);
	}

	private static Vertex searchFor(String query, ArrayList<Vertex> graph) {
		for (int i = 0; i < graph.size(); i++) {
			Vertex v = (Vertex) graph.get(i);
			if (v.word.equals(query)) {
				// v.printNeighbors();
				return v;
			}
		}
		return null;
	}

	public static void askToTryAgain(Scanner userInput) {

		System.out.println("Would you like to try again?");

		String query = "";
		boolean keepTrying = true;

		do {

			query = userInput.next().toUpperCase();

			if (query.equals("YES") || query.equals("Y")) {
				keepTrying = false;
			} else if (query.equals("NO") || query.equals("N")) {
				System.exit(0);
			} else {
				System.out.println("What?");
			}
		} while (keepTrying);
	}
}