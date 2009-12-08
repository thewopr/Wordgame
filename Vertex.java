import java.util.*;

public class Vertex extends HeapElt {

	LinkedList<Vertex> edges;
	String word;

	Vertex predecessor;

	public Vertex(String s) {
		record = Integer.MAX_VALUE;
		;

		word = s;
		handle = 0;

		edges = new LinkedList<Vertex>();
		predecessor = null;

	}

	public int getDistance() {
		return (Integer) record;
	}

	public void addEdge(Vertex anEdge) {
		edges.add(anEdge);
	}

	public void addVertex(Vertex v) {

		if (edges.contains(v))
			return;
		else {
			int weight = diff(v);

			if (weight != -1) {
				Vertex go = v;
				Vertex from = this;

				this.addEdge(go);
				v.addEdge(from);
			}
		}
	}

	public int diff(Vertex other) {
		int sum = 0;

		for (int i = 0; i < word.length() && sum < 3; i++) {
			if (word.charAt(i) != other.word.charAt(i)) {
				sum++;
			}
		}

		if (sum > 2) {
			return -1;
		} else if (sum == 2) {
			return 5;
		}

		return 1;
	}

	public String toString() {
		return "Vertex " + word + " (" + getDistance() + ")";
	}

	public void printNeighbors() {
		String s = "The neighbors of " + this.word + " are:\n";

		Iterator<Vertex> iter = edges.iterator();

		int count = 0;

		while (iter.hasNext()) {
			count++;
			Vertex v = iter.next();

			s += v.word + "(" + v.record + ") ";

			if (count % 6 == 0)
				s += "\n";

		}

		System.out.println(s + "\n");
	}

	public void setDistance(int i) {
		record = i;
	}
}
