import java.math.BigDecimal;
import java.util.Stack;

public class DijkstraSP {
	private double[] distTo;          // distTo[v] = distance  of shortest s->v path
	private DirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
	private IndexMinPQ<Double> pq;    // priority queue of vertices
	private int BL[];
	/**
	 * Computes a shortest-paths tree from the source vertex <tt>s</tt> to every other
	 * vertex in the edge-weighted digraph <tt>G</tt>.
	 *
	 * @param  G the edge-weighted digraph
	 * @param  s the source vertex
	 * @throws IllegalArgumentException if an edge weight is negative
	 * @throws IllegalArgumentException unless 0 &le; <tt>s</tt> &le; <tt>V</tt> - 1
	 */
	public DijkstraSP(EdgeWeightedDigraph G, int s,int bl[]) {
		for (DirectedEdge e : G.edges()) {
			if (e.weight() < 0)
				throw new IllegalArgumentException("edge " + e + " has negative weight");
		}
		BL=bl;
		distTo = new double[G.V()];
		edgeTo = new DirectedEdge[G.V()];
		for (int v = 0; v < G.V(); v++)
			distTo[v] = Double.POSITIVE_INFINITY;
		distTo[s] = 0.0;

		// relax vertices in order of distance from s
		pq = new IndexMinPQ<Double>(G.V());
		pq.insert(s, distTo[s]);
		while (!pq.isEmpty()) {
			int v = pq.delMin();
			for (DirectedEdge e : G.adj(v))
				relax(e);
		}

		// check optimality conditions
		assert check(G, s);
	}

	// relax edge e and update pq if changed
	private void relax(DirectedEdge e) {
		int v = e.from(), w = e.to();
		boolean b=true;
		for (int i = 0; i < BL.length ; i ++){
			if (BL[i]==w){
				b = false;
			}
		}
		if (b){
		if (distTo[w] > distTo[v] + e.weight()) {
			distTo[w] = distTo[v] + e.weight();
			edgeTo[w] = e;
			if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
			else                pq.insert(w, distTo[w]);
		}
	}}

	/**
	 * Returns the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
	 * @param  v the destination vertex
	 * @return the length of a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>;
	 *         <tt>Double.POSITIVE_INFINITY</tt> if no such path
	 */
	public double distTo(int v) {
		return distTo[v];
	}

	/**
	 * Returns true if there is a path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
	 *
	 * @param  v the destination vertex
	 * @return <tt>true</tt> if there is a path from the source vertex
	 *         <tt>s</tt> to vertex <tt>v</tt>; <tt>false</tt> otherwise
	 */
	public boolean hasPathTo(int v) {
		return distTo[v] < Double.POSITIVE_INFINITY;
	}

	/**
	 * Returns a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>.
	 *
	 * @param  v the destination vertex
	 * @return a shortest path from the source vertex <tt>s</tt> to vertex <tt>v</tt>
	 *         as an iterable of edges, and <tt>null</tt> if no such path
	 */
	public Iterable<DirectedEdge> pathTo(int v) {
		if (!hasPathTo(v)) return null;
		Stack<DirectedEdge> path = new Stack<DirectedEdge>();
		for (DirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
				path.push(e);
		}
		return path;
	}


	// check optimality conditions:
	// (i) for all edges e:            distTo[e.to()] <= distTo[e.from()] + e.weight()
	// (ii) for all edge e on the SPT: distTo[e.to()] == distTo[e.from()] + e.weight()
	private boolean check(EdgeWeightedDigraph G, int s) {

		// check that edge weights are nonnegative
		for (DirectedEdge e : G.edges()) {
			if (e.weight() < 0) {
				System.err.println("negative edge weight detected");
				return false;
			}
		}

		// check that distTo[v] and edgeTo[v] are consistent
		if (distTo[s] != 0.0 || edgeTo[s] != null) {
			System.err.println("distTo[s] and edgeTo[s] inconsistent");
			return false;
		}
		for (int v = 0; v < G.V(); v++) {
			if (v == s) continue;
			if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
				System.err.println("distTo[] and edgeTo[] inconsistent");
				return false;
			}
		}

		// check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
		for (int v = 0; v < G.V(); v++) {
			for (DirectedEdge e : G.adj(v)) {
				int w = e.to();
				if (distTo[v] + e.weight() < distTo[w]) {
					System.err.println("edge " + e + " not relaxed");
					return false;
				}
			}
		}

		// check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
		for (int w = 0; w < G.V(); w++) {
			if (edgeTo[w] == null) continue;
			DirectedEdge e = edgeTo[w];
			int v = e.from();
			if (w != e.to()) return false;
			if (distTo[v] + e.weight() != distTo[w]) {
				System.err.println("edge " + e + " on shortest path not tight");
				return false;
			}
		}
		return true;
	}


	/**
	 * Unit tests the <tt>DijkstraSP</tt> data type.
	 */
	public static void main(String[] args) {
		Out out = new Out ("D:\\result1.txt");
		In in = new In("D:\\mediumEWD.txt");
		int i = 0;
		EdgeWeightedDigraph G = new EdgeWeightedDigraph(in);
		In In1 = new In ("D:\\test2.txt");
	        int E = In1.readInt();
	        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
	        for ( i = 0; i < E; i++) {
	            int v = In1.readInt();
	            int w = In1.readInt();
	            if (v < 0 || v >= G.V) throw new IndexOutOfBoundsException("vertex " + v + " is not between 0 and " + (G.V-1));
	            if (w < 0 || w >= G.V) throw new IndexOutOfBoundsException("vertex " + w + " is not between 0 and " + (G.V-1));
	            int numOfB = In1.readInt ();
	            int BL[] = new int [numOfB];
	            for ( int  j = 0 ; j < numOfB ; j++ ){
	            	BL[j] = In1.readInt (); 
	            }
	         // compute shortest paths
	    		DijkstraSP sp = new DijkstraSP(G,v,BL);
	    		if (sp.hasPathTo(w)) {
	    			out.printf("%d %d ", v, w);
	    			out.printf("%d ", BL.length);
	    			for (int l=0; l < BL.length ; l++ ){
	    				out.printf("%d ",BL[l]);
	    			}
	    			out.print((sp.distTo(w))+ "\n");
	    		}
	    		else {
	    			out.printf("%d %d no path\n", v, w);
	    		}
	        }
	}
	
}