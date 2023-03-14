package dataStructures;

import exceptions.graphExceptions.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Data structure to contain graph that can show moomins friendships (of course it can contain anything, but in
 * this task we use it to contain friendships).
 * Graph is assumed to be complete (all moomins have friendships with each other).
 * If graph does not contain the edge between vertex1 and vertex2, it means that vertex1 and vertex2
 * are in a quarrel.
 */

public class Graph<T> {

    private Map<T, ArrayList<T>> graph = new HashMap<>();

    /**
     * Method attaches vertex to all other vertexes in graph
     */
    public void addVertex(T vertex) throws AlreadyContainsVertexException {
        if (graph.containsKey(vertex)) {
            throw new AlreadyContainsVertexException();
        }

        if (graph.isEmpty()) {
            graph.put(vertex, new ArrayList<T>());
        } else {
            ArrayList<T> adjacencyList = new ArrayList<>();

            // adding all graph vertexes to vertexNeighbours list and new edges
            for (Map.Entry<T, ArrayList<T>> entry : graph.entrySet()) {
                adjacencyList.add(entry.getKey());
                ArrayList<T> currentVertexAdjacencyList = entry.getValue();
                currentVertexAdjacencyList.add(vertex);
                graph.put(entry.getKey(), currentVertexAdjacencyList);
            }

            graph.put(vertex, adjacencyList);
        }
    }

    /**
     * Method removes vertex and all its edges
     */
    public void removeVertex(T vertex) throws VertexNotFoundException {
        if (graph.containsKey(vertex)) {
            graph.remove(vertex);
        } else {
            throw new VertexNotFoundException();
        }

        for (Map.Entry<T, ArrayList<T>> entry : graph.entrySet()) {
            ArrayList<T> currentVertexAdjacencyList = entry.getValue();
            currentVertexAdjacencyList.remove(vertex);
            graph.put(entry.getKey(), currentVertexAdjacencyList);
        }
    }

    /**
     * Method adds edge between vertex1 and vertex2
     */
    public void addEdge(T vertex1, T vertex2) throws AlreadyContainsEdgeException, VertexNotFoundException {

        if (graph.containsKey(vertex1)) {
            ArrayList<T> adjacencyListVertex1 = graph.get(vertex1);
            adjacencyListVertex1.add(vertex2);

            if (graph.get(vertex1).contains(vertex2)) {
                throw new AlreadyContainsEdgeException();
            }

            graph.put(vertex1, adjacencyListVertex1);
        } else {
            throw new VertexNotFoundException();
        }

        if (graph.containsKey(vertex2)) {
            ArrayList<T> adjacencyListVertex2 = graph.get(vertex2);
            adjacencyListVertex2.add(vertex1);
            graph.put(vertex2, adjacencyListVertex2);
        } else {
            throw new VertexNotFoundException();
        }
    }

    /**
     * Method removes edge between vertex1 and vertex2
     */
    public void removeEdge(T vertex1, T vertex2) {
        ArrayList<T> adjacencyListVertex1 = graph.get(vertex1);
        adjacencyListVertex1.remove(vertex2);
        graph.put(vertex1, adjacencyListVertex1);

        ArrayList<T> adjacencyListVertex2 = graph.get(vertex2);
        adjacencyListVertex2.remove(vertex1);
        graph.put(vertex2, adjacencyListVertex2);
    }

    public Map<T, ArrayList<T>> getGraph() {
        return graph;
    }

    @Override
    public String toString() {
        StringBuilder stringGraph = new StringBuilder();

        for (Map.Entry<T, ArrayList<T>> entry : graph.entrySet()) {
            stringGraph.append(entry.getKey()).append(": \n");
            entry.getValue().forEach(value -> stringGraph.append(value).append("; "));
            stringGraph.append('\n');
        }

        return stringGraph.toString();
    }


    /**
     * Method equals checks that both graphs have the same set of vertices and edges
     */
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;

        Graph<?> g = (Graph<?>) object;
        Map<?, ? extends ArrayList<?>> graph1 = g.getGraph();

        if (graph1.isEmpty() && graph.isEmpty()) return true;
        if (graph1.isEmpty() || graph.isEmpty()) return false;

        class ListsEqualityChecker {

            public boolean checkEquality(ArrayList<T> a, ArrayList<T> b) {
                for (T element : a) {
                    if (!b.contains(element)) {
                        return false;
                    }
                }
                return true;
            }
        }

        ListsEqualityChecker checker = new ListsEqualityChecker();

        for (Map.Entry<T, ArrayList<T>> entry : graph.entrySet()) {

            ArrayList<T> currentVertexAdjacencyList = entry.getValue();

            if (!graph1.containsKey(entry.getKey()) || graph1.get(entry.getKey()).getClass() != ArrayList.class ||
                    !checker.checkEquality(currentVertexAdjacencyList, (ArrayList<T>) graph1.get(entry.getKey()))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(graph);
    }
}
