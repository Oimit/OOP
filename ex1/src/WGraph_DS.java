package ex1.src;

import java.io.Serializable;
import java.util.ArrayList;

import java.util.Collection;
import java.util.HashMap;

public class WGraph_DS implements weighted_graph, Serializable {

	private HashMap <Integer, node_info> nodes;
	private int numOfEdges;
	private int mc;

	//Constructor\\
	public WGraph_DS() {
		this.nodes = new HashMap <Integer, node_info>();
		this.numOfEdges = 0;
		this.mc = 0;
	}
	
	/**
	 * return the node_info by the key.
	 */
	@Override
	public node_info getNode(int key) {
		return this.nodes.get(key);
	}

	/**
	 * Here we checking if there is an edge between two specifics nodes.
	 * First, we check if the nodes excist in the list of the nodes.
	 * than, we check in the list of each node if the contains the other node.
	 * if they are - its true.
	 * if not - its false.
	 * 
	 * @param node1 - first node.
	 * @param node2 - second node.
	 * @return True\False - if has edge or not.
	 */
	@Override
	public boolean hasEdge(int node1, int node2) {

		NodeInfo n1 = (NodeInfo) nodes.get(node1);
		NodeInfo n2 = (NodeInfo) nodes.get(node2);
		if (!nodes.containsKey(node1) || !nodes.containsKey(node2)) return false;
		return ((n1.edges.containsKey(node2)) && (n2.edges.containsKey(node1)));
	}
	
	/**
	 * Here we getting the weight between teo nodes.
	 * First, we check it there is an edge between these two nodes.
	 * If there is, were enter to the node HashMap of edges and we getting
	 * the second node and returning the weight.
	 * If there isnt edge between these two nodes we return -1.
	 * 
	 * @param node1 - first node.
	 * @param node2 - second node.
	 * @return - the weight of their edge or -1 in case there isnt edge.
	 */
	@Override
	public double getEdge(int node1, int node2) {

		if (hasEdge(node1, node2)) {
			NodeInfo n1 = (NodeInfo) nodes.get(node1);
			return n1.edges.get(node2).getWeight();
		}
		return -1;
	}

	/**
	 * Here we adding a node to the graph by using the data structure of
	 * HashMap names "nodes" that includes all the nodes of graph.
	 * 
	 * @param key - the adding node.
	 */
	@Override
	public void addNode(int key) {

		if (nodes.containsKey(key)) return;
		nodes.put(key, new NodeInfo(key));
		mc++;
	}

	/**
	 * Here we connect two nodes with edge and give the edge a weight.
	 * First, we check if the first node and the second node are diffrent.
	 * if they arent we just return.
	 * if they are different we check if they already has an edge between them
	 * if there isnt an edge between them were adding to the list of each node 
	 * the second node to the node.edges (the list of their neighbors) and we set
	 * a weight between them.
	 * If there is an edge between them we change the weight of them to the new 
	 * given weight.
	 * 
	 * @param node1 - first node.
	 * @param node2 - second node.
	 * @param w - the weight between the nodes.
	 */
	@Override
	public void connect(int node1, int node2, double w) {

		NodeInfo n1 = (NodeInfo) nodes.get(node1);
		NodeInfo n2 = (NodeInfo) nodes.get(node2);
		if (node1 == node2) return;
		if (n1 == null || n2 == null) return;
		if (hasEdge(node1, node2)) {
			n1.edges.get(node2).setWeight(w);
			n2.edges.get(node1).setWeight(w);
			mc++;
		}
		else {
			n1.edges.put(node2, new Edge(w, n2));
			n2.edges.put(node1, new Edge(w, n1));
			numOfEdges++;
			mc++;
		}
	}

	/**
	 * Here we returning a list of all the nodes of the graph.
	 * we using here the values of the hashMap which is node_info.
	 * 
	 */
	@Override
	public Collection<node_info> getV() {
		return nodes.values();
	}
	
	/**
	 * Here we returning the neighbors of a specific node.
	 * First, we check if this node is excist in the graph.
	 * if it is, were addidng all the neighbors of its node to a list
	 * and we running on the neighbors node by the node edges that includes
	 * all the nodes that connects to him.
	 * we run on a for each loop and adding to the list the nodes.
	 * 
	 * @param node_id - the node that we returning the neighbors of him.
	 * @return - neighbors - the list that includes all the node neighbors.
	 */
	@Override
	public Collection<node_info> getV(int node_id) {

		if (!nodes.containsKey(node_id)) return null;
		ArrayList <node_info> neighbors = new ArrayList <node_info>();
		NodeInfo current = (NodeInfo) nodes.get(node_id);
		for (Edge nd : current.edges.values())
			neighbors.add(nd.getNode());
		return neighbors;
	}

	/**
	 * Here we removing a node from the graph.
	 * First, we check the node is exist the graph.\
	 * if it is, we running on all its neighbors and remove this node
	 * from their list. Than we remove the neighbor from the node list we 
	 * want to remove.
	 * And after we make sure we removed from all the neighbors that node and 
	 * also from that specific node, we remove the node from the graph. 
	 * 
	 * @param key - the node that we want to remove.
	 */
	@Override
	public node_info removeNode(int key) {

		if (!nodes.containsKey(key)) return null;
		NodeInfo node = (NodeInfo) nodes.get(key);
		ArrayList<Edge> neighbors = new ArrayList<Edge>(node.edges.values());
		for (Edge e : neighbors) {
			NodeInfo current = (NodeInfo) e.getNode();
			current.edges.remove(key);
			node.edges.remove(current.getKey());
			numOfEdges--;
			mc++;
		}
		mc++;
		return nodes.remove(key);
	}

	/**
	 * Here we removing an edge between two nodes.
	 * first, we check that there isnt an edge between them.
	 * If there is, we removing the other node from the node list
	 * of neighbors and also with the second node. 
	 * 
	 * @param node1 - first node.
	 * @param node2 - second node.
	 */
	@Override
	public void removeEdge(int node1, int node2) {

		if (!hasEdge(node1, node2)) return;
		NodeInfo n1 = (NodeInfo) nodes.get(node1);
		NodeInfo n2 = (NodeInfo) nodes.get(node2);
		n1.edges.remove(node2);
		n2.edges.remove(node1);

		mc++;
		numOfEdges--;
	}

	/**
	 * Here we returning the number of nodes we have on the graph.
	 */
	@Override
	public int nodeSize() {
		return nodes.size();
	}

	/**
	 * Here we returning the number of edges we have on the graph.
	 */
	@Override
	public int edgeSize() {
		return numOfEdges;
	}

	/**
	 * Here we returning the number of operations we did on the graph.
	 */
	@Override
	public int getMC() {
		return mc;
	}

	private class NodeInfo implements node_info, Serializable {

		private double tag;
		private String info;
		private int id;
		private HashMap <Integer, Edge> edges;

		//Constructor\\
		public NodeInfo(int key) {

			this.edges = new HashMap <Integer,Edge>();
			this.id = key;
			this.tag = Double.POSITIVE_INFINITY;
			this.info = "id: " +id;
		}
		
		//Getters and Setters\\
		
		@Override
		public int getKey() {
			return this.id;
		}

		@Override
		public String getInfo() {
			return info;
		}

		@Override
		public void setInfo(String s) {
			this.info = s;
		}

		@Override
		public double getTag() {
			return tag;
		}

		@Override
		public void setTag(double t) {
			this.tag = t;
		}

		//----------------------------\\
		
		/**
		 * Here we check if what we check is equal.
		 * First, we check if the type are equals. 
		 * If they arent we return false.
		 * If they are, we check their id, tag and info.
		 * If all are equals we return true, if not we return false.
		 */
		@Override
		public boolean equals(Object obj) {

			if (this == obj) return true;
			if (obj == null || getClass() != obj.getClass()) return false;
			NodeInfo other = (NodeInfo) obj;

			return edges.equals(other.edges) &&
					id == other.id &&
					Double.compare(tag, other.tag) == 0 &&
					info.equals(other.info);
		}
	}

	/**
	 * Here we check if what we check is equal.
	 * First, we check if the type are equals. 
	 * If they arent we return false.
	 * If they are we check their nodes, numOfEdges and their mc.
	 * If all are equals we return true, if not we return false.
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false; //checking the types.
		WGraph_DS other = (WGraph_DS) obj;
		
		return (nodes.equals(other.nodes) &&
				numOfEdges == other.numOfEdges &&
				mc == other.mc);
	}
}
