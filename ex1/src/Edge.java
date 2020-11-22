package ex1.src;

import java.io.Serializable;

public class Edge implements Serializable {

	private double weight;
	private node_info node;
	
	//Constructor\\
	public Edge(double weight, node_info node) {
		
		this.weight = weight;
		this.node = node;
	}
	
	//Getters and Setters\\
	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public node_info getNode() {
		return node;
	}

	public void setNode(node_info node) {
		this.node = node;
	}
	
	//------------------------------------\\
	
	/**
	 * Here we check if what we check is equal.
	 * First, we check if the type are equals. 
	 * If they arent we return false.
	 * If they are, we check their weight and their keys.
	 * If all are equals we return true, if not we return false.
	 */
	@Override
	public boolean equals(Object obj) {
		
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false; //checking the types.
		Edge other = (Edge) obj;
		
		return weight == other.weight &&
				node.getKey() == other.getNode().getKey();
	}
}
