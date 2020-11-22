package ex1.src;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class WGraph_Algo implements weighted_graph_algorithms {

	private WGraph_DS graph;
	private PriorityQueue <node_info> pq;
	private HashMap <Integer, Integer> visited;
	private HashMap <Integer, node_info> parent;

	//Constructor\\
	public WGraph_Algo() {

		this.pq = new PriorityQueue<node_info>((n1,n2) -> (int)(n1.getTag() - n2.getTag()));
		this.parent = new HashMap<Integer, node_info>();
	}

	/**
	 * initllize the new graph. 
	 * @param g - The graph were initializing
	 */
	@Override
	public void init(weighted_graph g) {

		this.graph = (WGraph_DS) g;
		for (node_info nd : g.getV()) {
			nd.setTag(Double.POSITIVE_INFINITY);
		}
		this.parent = new HashMap<Integer, node_info>();
		this.visited = new HashMap<Integer, Integer>();
		for (node_info node : g.getV()) {
			parent.put(node.getKey(), null);
			visited.put(node.getKey(), 0);
		}
		while (!pq.isEmpty()) {
			pq.remove();
		}
	}

	@Override
	/**
	 * getting the graph.
	 */
	public weighted_graph getGraph() {
		return this.graph;
	}

	/**
	 * Using deep copy for the graph.
	 * Were taking a node from the graph and using on him the function "copyToGraph",
	 * which getting a graph and a node that it is the source for the deep copying and
	 * and were copying and the nodes from the graph to the new graph using a Queue and
	 * the algorithem of BFS.
	 * We also adding all the edges of the graph to the new graph by using the
	 * HashMap of "parent" to know who is the "father" of who.
	 * In case the graph isn't connected were checking in the HashMap "visited"
	 * if every node has been visited or not. In case its now were using the
	 * function "copyToGraph" again on that specific node.
	 * 
	 * @return copyGraph - the new copy graph.
	 */
	@Override
	public weighted_graph copy() {

		WGraph_DS copyGraph = new WGraph_DS();
		Iterator <node_info> itr = graph.getV().iterator();
		if (itr.hasNext()) {
			node_info node = itr.next();
			copyToGraph(copyGraph, node);
			for (Integer i : visited.keySet()) {
				if (visited.get(i) == 0) {
					copyToGraph(copyGraph, graph.getNode(i));
				}
			}
		}
		return copyGraph;
	}

	/**
	 * Here we using the "Dijakstra" algorithem to know if the graph is connected or not.
	 * dijakstra algorithem similar with the BFS algorithem, but
	 * were using here a priorityQueue for the weight of every edge and updates 
	 * all the nodes to their correct weight.
	 * Were taking a node from the graph and see if we can reach all the nodes from him.
	 * If we succeed to get all nodes from that node - the graph is connected.
	 * if not - the graph is not connected.
	 * We see if the graph connected r not with the HashMap of "visited".
	 * if the value of every node is "1", the graph is connected.
	 * if not all the values are "1" and there is even onw node that his value
	 * is "0" - the graph is not connected.
	 * 
	 * @return - True\False - if the graph is connected.
	 */
	@Override
	public boolean isConnected() {

		init(graph);
		Iterator <node_info> itr = graph.getV().iterator();

		if (itr.hasNext()) {
			node_info current = itr.next(); //a node that from him we check if 
			dijakstra(current);				//the graph is connected.
		}
		if (visited.containsValue(0)) return false;

		return true;
	}

	/**
	 * Here we using again the "Dijakstra" algorithem to find the shortest path 
	 * from a source node to a destination node.
	 * were updating the tag of each node to be the weight (edge) with other node.
	 * first, we initilize the tags to inifinity.
	 * were checking if we have the src or the dest are nulls.
	 * if they are - we return -1, because their isnt path between them.
	 * if src = dest - we return 0 because they are equal and the distance
	 * between them is 0.
	 * in Dijakstra algorithem we update all the tags of a node to be the weight.
	 * 
	 * @param src - the source node we check the distance.
	 * @param dest - the destination node we check the distance.
	 * @return n2.getTag - the distance.
	 */
	@Override
	public double shortestPathDist(int src, int dest) {

		init(graph);
		node_info n1 = graph.getNode(src);
		node_info n2 = graph.getNode(dest);
		if (n1 == null || n2 == null) return -1;
		if (src == dest) return 0;
		dijakstra(n1);
		if (n2.getTag() == Double.POSITIVE_INFINITY) return -1;
		return n2.getTag();
	}

	/**
	 * Here we check the Path between a source node to a destination node.
	 * we returning a list thats includes all the nodes of the path between
	 * src to dest.
	 * we using again "Dijakstra" algorithem and this method very similar to shortedPathDist
	 * but, the dijakstra algorithem starts with the dest and not with the src
	 * because we want to return the path list inorder (src->node1->...->dest).
	 * after dijakstra is finished, we check if we reach the src node
	 * (if were a path between src and dest). if hadnt, we return null.
	 * than, we using the HashMap parent to know who is the father of who and
	 * by that we adding to the list that node and than we change it to be the next node.
	 * we using a "while" loop that stops when n1=n2 (by the parent).
	 * 
	 * @param src - the source node we check the distance.
	 * @param dest - the destination node we check the distance.
	 * @return list - the list that includes all the nodes path.
	 */
	@Override
	public List<node_info> shortestPath(int src, int dest) {

		init(graph);
		node_info n1 = graph.getNode(src);
		node_info n2 = graph.getNode(dest);
		if (n1 == null || n2 == null) return null;
		ArrayList <node_info> list = new ArrayList <node_info>();
		if (src == dest) {
			list.add(n1);
			return list;
		}
		dijakstra(n2);
		if (n1.getKey() == Double.POSITIVE_INFINITY) return null;
		while (n1 != n2) {
			list.add(n1);
			n1 = parent.get(n1.getKey());
		}
		list.add(n2);
		return list;
	}

	/**
	 * Saves the file(graph) on the computer.
	 * the file is the graph.
	 */
	@Override
	public boolean save(String file) {

		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(graph);
			fos.close();
			oos.close();
		} 
		catch (IOException e1) {
			e1.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * load a file (graph) from the computer.
	 * the file is a graph.
	 */
	@Override
	public boolean load(String file) {

		FileInputStream fis;
		try {
			fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			weighted_graph gr = (weighted_graph) ois.readObject();
			graph = (WGraph_DS) gr;
			fis.close();
			ois.close();
		} 
		catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * dijakstra algorithem.
	 * using a PriorityQueue and HashMaps.
	 * we updates the priorityQueue by the weight of each node(the tag).
	 * were using the hashMap of visited to know if we visited on that 
	 * node or not.
	 * were using the HashMap parent to update who is the father of who.
	 * and we remove a node from the quese and put him back after we update its weight (tag).
	 * source: https://www.youtube.com/watch?v=_lHSawdgXpI
	 * source: https://www.youtube.com/watch?v=pVfj6mxhdMw
	 * 
	 * @param src - the node we start from it the algorithem.
	 */
	public void dijakstra (node_info src) {

		src.setTag(0);
		pq.add(src);
		while (!pq.isEmpty()) {
			node_info current = pq.poll();
			for (node_info neighbors : graph.getV(current.getKey())) {
				double newDist = graph.getEdge(current.getKey(), neighbors.getKey()) + current.getTag();
				if (visited.get(neighbors.getKey()) == 0 && neighbors.getTag() > newDist) {
					neighbors.setTag(newDist);
					parent.put(neighbors.getKey(), current);
					pq.remove(neighbors);
					pq.add(neighbors); //Enter the new nei with the new tag back to the PriorityQueue.
				}
			}
			visited.put(current.getKey(), 1);
		}
	}
	
	public void copyToGraph (WGraph_DS gr, node_info src) {

		Queue <node_info> q = new LinkedList<node_info>();
		gr.addNode(src.getKey());
		gr.getNode(src.getKey()).setTag(src.getTag());
		gr.getNode(src.getKey()).setInfo(src.getInfo());
		q.add(src);

		while (!q.isEmpty()) {
			node_info current = q.poll();
			for (node_info nd : graph.getV(current.getKey())) {
				if (visited.get(nd.getKey()) == 0 && gr.getNode(nd.getKey()) == null) {
					gr.addNode(nd.getKey());
					gr.getNode(nd.getKey()).setTag(nd.getTag());
					gr.getNode(nd.getKey()).setInfo(nd.getInfo());
					q.add(nd);
				}
				if (!graph.hasEdge(current.getKey(), nd.getKey())) {
					gr.connect(current.getKey(), nd.getKey(), graph.getEdge(current.getKey(), nd.getKey()));
				}
			}
			visited.put(current.getKey(), 1);
		}
	}
}
