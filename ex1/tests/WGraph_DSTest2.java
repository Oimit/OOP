package ex1.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.Iterator;

import org.junit.jupiter.api.Test;

import ex1.src.WGraph_DS;
import ex1.src.node_info;

class WGraph_DSTest2 {
	
	
	@Test
	void testHasEdge() {
		
		WGraph_DS graph = new WGraph_DS();
		graph.addNode(5);
		graph.addNode(6);
		assertFalse(graph.hasEdge(5, 6));
		assertFalse(graph.hasEdge(5, 5));
		assertFalse(graph.hasEdge(5, 7));
		graph.connect(5, 6, 2);
		assertTrue(graph.hasEdge(5, 6));
	}

	@Test
	void testGetEdge() {
		WGraph_DS graph = new WGraph_DS();
		graph.addNode(5);
		graph.addNode(6);
		assertEquals(-1, graph.getEdge(7, 8));
		assertEquals(-1, graph.getEdge(5, 6));
		graph.connect(5, 6, 7);
		assertEquals(7, graph.getEdge(5, 6));
		
	}

	@Test
	void testAddNode() {
		WGraph_DS graph = new WGraph_DS();
		graph.addNode(5);
		assertNotNull(graph.getNode(5));
		assertNull(graph.getNode(9));
		graph.addNode(5);
		assertEquals(1, graph.nodeSize());
	}

	@Test
	void testConnect() {
		WGraph_DS graph = new WGraph_DS();
		graph.addNode(5);
		graph.addNode(6);
		graph.connect(5, 5, 3);
		assertEquals(-1, graph.getEdge(5, 5));
		graph.connect(5, 8, 2);
		assertEquals(-1, graph.getEdge(5, 8));
		graph.connect(5, 6, 4);
		assertTrue(graph.hasEdge(5, 6));
		graph.connect(5, 6, 7);
		assertEquals(7, graph.getEdge(5, 6));
		assertEquals(1, graph.edgeSize());
	}

	@Test
	void testGetV() {
        WGraph_DS g = new WGraph_DS();
        g.addNode(5);
        g.addNode(6);
        g.addNode(7);
        g.connect(5,6,3);
        g.connect(5,7,1);
        g.connect(6,7,8);
        Collection<node_info> nodes = g.getV();
        Iterator<node_info> it = nodes.iterator();
        while (it.hasNext()) {
            node_info n = it.next();
            assertNotNull(n);
        }
	}

	@Test
	void testRemoveNode() {
        WGraph_DS g = new WGraph_DS();
        g.addNode(5);
        g.addNode(6);
        g.addNode(7);
        g.connect(5,6,3);
        g.connect(5,7,1);
        g.connect(6,7,8);
        g.removeNode(5);
        assertFalse(g.hasEdge(5,6));
        assertEquals(2,g.nodeSize());
	}

	@Test
	void testRemoveEdge() {
        WGraph_DS g = new WGraph_DS();
        g.addNode(5);
        g.addNode(6);
        g.addNode(7);
        g.connect(5,6,3);
        g.connect(5,7,1);
        g.connect(6,7,8);
        g.removeEdge(6,7);
        assertEquals(-1, g.getEdge(6, 7));	
        }

	@Test
	void testEqualsObject() {
		WGraph_DS graph = new WGraph_DS();
		WGraph_DS graph2 = new WGraph_DS();
		graph.addNode(5);
		graph.addNode(6);
		graph2.addNode(5);
		graph2.addNode(6);
		graph.connect(5, 6, 2);
		graph2.connect(5, 6, 2);
		assertTrue(graph.equals(graph2));
		graph2.addNode(8);
		assertFalse(graph.equals(graph2));
		
	}

}
