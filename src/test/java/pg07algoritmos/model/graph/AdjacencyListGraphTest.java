package pg07algoritmos.model.graph;

import org.junit.jupiter.api.Test;
import pg07algoritmos.model.Queue.QueueException;
import pg07algoritmos.model.linkedList.ListException;
import pg07algoritmos.model.stack.StackException;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyListGraphTest {

    @Test
    void testMethodsPracticeExam2() {
        AdjacencyListGraph<Integer> graph = new AdjacencyListGraph<>(10, false); //es no dirigido

        try {

            // Agregar vértices
            for (int i = 1; i <= 10; i++) {
                graph.addVertex(i);
            }

            // Agregar aristas con pesos
            graph.addEdgeAndWeight(1, 5, 3);
            graph.addEdgeAndWeight(2, 4, 6);
            graph.addEdgeAndWeight(2, 6, 5);
            graph.addEdgeAndWeight(3, 4, 2);
            graph.addEdgeAndWeight(3, 7, 8);
            graph.addEdgeAndWeight(4, 5, 9);
            graph.addEdgeAndWeight(4, 8, 1);
            graph.addEdgeAndWeight(5, 6, 4);
            graph.addEdgeAndWeight(5, 9, 7);

            System.out.println(graph);
            System.out.println("Recorrido DFS: "+graph.dfs());
            System.out.println("Recorrido BFS: "+graph.bfs());

            System.out.println("getVertexDegree(2): "+graph.getVertexDegree(2));
            System.out.println("getGraphDegree(): "+graph.getGraphDegree());
            System.out.println("totalEdges(): "+graph.totalEdges());
            System.out.println("totalEdges(3): "+graph.totalEdges(3));
            System.out.println("getEdges(4): "+graph.totalEdges(4));


        } catch (StackException e) {
            throw new RuntimeException(e);
        } catch (ListException e) {
            throw new RuntimeException(e);
        } catch (QueueException e) {
            throw new RuntimeException(e);
        }

    }
}