In this project we creating a undirectional weighted graph and makes on it some algorithms.
We have 3 classes we implemets. NodeInfo, WGraph_DS and WGraph_Algo.
And we also have a class that I have created calles "Edge" ehich represents the weight.

*The class node Info is an Inner class on WGraph_DS.


Class WGraph_DS:

In this class we have an inner class which is NodeInfo.
In WGraph_DS class we adding nodes to the graph, connect them by edge, checking all the nodes
inside it, checking all the neighbors of a specific node and getting a node.
This class is builds a graph.
In this class we using the Data structures of HashMap and ArrayList.

    Methods:

1) getNode - This method  returns the node_data by the node_id.
2) hasEdge - This method returns true if and only if there is an edge between node1 and node2.
3) getEdge - This method returns the weight of the edge (node1, node2). In case there is no such edge -> return -1.
4) addNode - This method adds a new node to the graph with the given key.
5) connect - This method connects an edge between node1 and node2, with an edge with weight bigger than 0.
6) getV() - This method return a pointer (shallow copy) for a Collection representing all the nodes in the graph.
7) getV(int node_id) - This method returns a Collection containing all the nodes connected to node_id.
8) removeNode - This method deletes the node (with the given ID) from the graph - and removes all 
                           edges which starts or ends at this node.
9) removeEdge - This method deletes the edge from the graph.
10) nodeSize - This method returns the number of vertices (nodes) in the graph.
11) edgeSize - This method returns the number of edges (undirectional graph).
12) getMC - This method returns the Mode Count - for testing changes in the graph.
13) equals - This mehod checks if an other object equal to this.


Inner class NodeInfo:

In this inner class we creates a node and create a list of each node neighbors by using the
data structure of HashMap.

    Methods:

1) getKey - This method returns the key (id) associated with this node.
2) getInfo - This method returns the remark (meta data) associated with this node.
3) setInfo - This method allows changing the remark (meta data) associated with this node.
4) getTag - This method has temporal data (aka distance, color, or state).
5) setTag - This method allows setting the "tag" value for temporal marking an node - common practice for marking by algorithms.
6) equals - This method checks if an other object equal to this.

Moreover, this inner class using a class i build called "Edge", which represents the weight of nodes.


Class WGraph_Algo:

This class represents an Undirected (positive) Weighted Graph Theory algorithms.
In this class we using the data structures of HashMap, PriorityQueue and ArrayList.
In this class were using the algorithem of Dijakstra and we also using a function called:
"copyToGraph" which using deep copying to the method copy and copying all the nodes
and edges from a graph to the other copied graph.

    Methods:
 
1) init - This method initilize the graph on which this set of algorithms operates on.
2) getGraph - This method returns the underlying graph of which this class works.
3) copy - This method computes a deep copy of this weighted graph.
4) isConnected - This method returns true if and only if there is a valid path from every node to each other node.
5) shortestPathDist - This method returns the length of the shortest path between src to dest. Note: if no such path -> returns -1.
6) shortestPath - This method returns the the shortest path between src to dest - as an ordered List of nodes: src--> n1-->n2-->...dest.
7) save - This method saves this weighted (undirected) graph to the given file name.
8) load - This method loads a graph to this graph algorithm. 
              if the file was successfully loaded - the underlying graph of this class will be changed (to the loaded one),
              in case the graph was not loaded the original graph should remain "as is".
9) equals - This mehod checks if an other object equal to this.
