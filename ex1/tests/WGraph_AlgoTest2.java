package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_Algo;
import ex1.src.WGraph_DS;

class WGraph_AlgoTest2 {

	@Test
	void testCopy() {
		WGraph_Algo graph = new WGraph_Algo();
		WGraph_DS g = new WGraph_DS();
		g.addNode(5);
		g.addNode(6);
		g.connect(5, 6, 2);
		graph.init(g);
		WGraph_DS g2 = (WGraph_DS) graph.copy();
		assertTrue(g.equals(g2));
		g2.addNode(7);
		assertFalse(g.equals(g2));
	}

	@Test
	void testIsConnected() {
		WGraph_Algo graph = new WGraph_Algo();
		WGraph_DS g = new WGraph_DS();
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.connect(5, 6, 2);
		g.connect(6, 7, 4);
		graph.init(g);
		assertTrue(graph.isConnected());
		g.addNode(8);
		graph.init(g);
		assertFalse(graph.isConnected());
	}

	@Test
	void testShortestPathDist() {
		WGraph_Algo graph = new WGraph_Algo();
		WGraph_DS g = new WGraph_DS();
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.addNode(8);
		g.addNode(9);
		g.addNode(10);
		g.connect(5, 6, 2);
		g.connect(6, 7, 4);
		g.connect(7, 8, 8);
		g.connect(7, 9, 3);
		g.connect(9, 10, 1);
		g.connect(8, 10, 1);
		graph.init(g);
		assertEquals(10, graph.shortestPathDist(5, 10));
	}

	@Test
	void testShortestPath() {
		WGraph_Algo graph = new WGraph_Algo();
		WGraph_DS g = new WGraph_DS();
		g.addNode(5);
		g.addNode(6);
		g.addNode(7);
		g.addNode(8);
		g.addNode(9);
		g.addNode(10);
		g.connect(5, 6, 2);
		g.connect(6, 7, 4);
		g.connect(7, 8, 8);
		g.connect(7, 9, 3);
		g.connect(9, 10, 1);
		g.connect(8, 10, 1);
		graph.init(g);
		ArrayList <Integer> al = new ArrayList<Integer>();
		al.add(5);
		al.add(6);
		al.add(7);
		al.add(9);
		al.add(10);
		assertEquals(al, graph.shortestPath(5, 10));
		
		
	}
}
