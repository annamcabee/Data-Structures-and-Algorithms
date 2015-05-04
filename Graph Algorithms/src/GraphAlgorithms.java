import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;


public class GraphAlgorithms {
    // Anna McAbee
    /**
     * Find the shortest distance between the start vertex and all other
     * vertices given a weighted graph.
     * You should use the adjacency list representation of the graph.
     *
     * Assume the adjacency list contains adjacent nodes of each node in the
     * order they should be visited. There are no negative edge weights in the
     * graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph or start vertex is null
     * @param graph the graph to search
     * @param start the starting vertex
     * @return map of the shortest distance between start and all other vertices
     */
    public static Map<Vertex, Integer> dijkstraShortestPath(Graph graph,
                                                            Vertex start) {
        if (graph != null && start != null) {
            Map<Vertex, Integer> distance = new HashMap<>();
            PriorityQueue<VertexDistancePair> pq = new PriorityQueue<>();
            for (Vertex v : graph.getVertices()) {
                distance.put(v, Graph.INF);
            }
            pq.add(new VertexDistancePair(start, 0));
            distance.put(start, 0);
            while (!pq.isEmpty()) {
                Vertex currentVertex = pq.remove().getVertex();
                Map<Vertex, Integer> map = graph.getAdjacencies(currentVertex);
                if (map != null) {
                    for (Vertex v : map.keySet()) {
                        if ((map.get(v) + distance.get(currentVertex))
                                < distance.get(v)) {
                            pq.add(new VertexDistancePair(v, map.get(v)
                                    + distance.get(currentVertex)));
                            distance.put(v, map.get(v)
                                    + distance.get(currentVertex));
                        }
                    }
                }
            }
            return distance;
        } else {
            throw new IllegalArgumentException("Graph or Vertex can't be null");
        }
    }

    /**
     * Run Floyd Warshall on the given graph to find the length of all of the
     * shortest paths between vertices.
     *
     * You will also detect if there are negative cycles in the
     * given graph.
     *
     * You should use the adjacency matrix representation of the graph.
     *
     * If there is no path from from the start vertex to a given vertex,
     * have the distance be INF as seen in the graphs class.
     *
     * @throws IllegalArgumentException if graph is null
     * @param graph the graph
     * @return the distances between each vertex. For example, given {@code arr}
     * represents the 2D array, {@code arr[i][j]} represents the distance from
     * vertex i to vertex j. Return {@code null} if there is a negative cycle.
     */
    public static int[][] floydWarshall(Graph graph) {
        if (graph != null) {
            int[][] adjacencies = graph.getAdjacencyMatrix();
            int[][] path = new int[adjacencies.length][adjacencies.length];
            for (int i = 0; i < adjacencies.length; i++) {
                for (int j = 0; j < adjacencies.length; j++) {
                    path[i][j] = adjacencies[i][j];
                }
            }
            for (int k = 0; k < adjacencies.length; k++) {
                for (int l = 0; l < adjacencies.length; l++) {
                    for (int m = 0; m < adjacencies.length; m++) {
                        if (path[l][k] + path[k][m] < path[l][m]) {
                            path[l][m] = path[l][k] + path[k][m];
                        }
                    }
                }
            }
            for (int x = 0; x < path.length; x++) {
                for (int y = 0; y < path.length; y++) {
                    if (path[x][y] < 0 && x == y) {
                        return null;
                    }
                }
            }
            return path;
        } else {
            throw new IllegalArgumentException("Graph can't be null");
        }
    }

    /**
     * A topological sort is a linear ordering of vertices with the restriction
     * that for every edge uv, vertex u comes before v in the ordering.
     *
     * You should use the adjacency list representation of the graph.
     * When considering which vertex to visit next while exploring the graph,
     * choose the next vertex in the adjacency list.
     *
     * You should start your topological sort with the smallest vertex
     * and if you need to select another starting vertex to continue
     * with your sort (like with a disconnected graph),
     * you should choose the next smallest applicable vertex.
     *
     * @throws IllegalArgumentException if the graph is null
     * @param graph a directed acyclic graph
     * @return a topological sort of the graph
     */
    public static List<Vertex> topologicalSort(Graph graph) {
        if (graph != null) {
            List<Vertex> sortedVertices = new ArrayList<>();
            boolean[] visited = new boolean[graph.getVertices().size()];
            for (Vertex i : graph.getVertices()) {
                topologicalVisit(graph, i, sortedVertices, visited);
            }
            return sortedVertices;
        } else {
            throw new IllegalArgumentException("Graph can't be null");
        }
    }

    /**
     * helper method for topological sort
     * @param g the graph
     * @param root the vertex
     * @param sorted the list
     * @param visited visited vertices
     */
    private static void topologicalVisit(Graph g, Vertex root,
                                     List<Vertex> sorted, boolean[] visited) {
        if (!visited[root.getId()]) {
            visited[root.getId()] = true;
            if (g.getAdjacencies(root) != null) {
                for (Vertex v : g.getAdjacencies(root).keySet()) {
                    topologicalVisit(g, v, sorted, visited);
                }
            }
            sorted.add(0, root);
        }
    }

    /**
     * A class that pairs a vertex and a distance. Hint: might be helpful
     * for Dijkstra's.
     */
    private static class VertexDistancePair implements
        Comparable<VertexDistancePair> {
        private Vertex vertex;
        private int distance;

        /**
         * Creates a vertex distance pair
         * @param vertex the vertex to store in this pair
         * @param distance the distance to store in this pair
         */
        public VertexDistancePair(Vertex vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        /**
         * Gets the vertex stored in this pair
         * @return the vertex stored in this pair
         */
        public Vertex getVertex() {
            return vertex;
        }

        /**
         * Sets the vertex to be stored in this pair
         * @param vertex the vertex to be stored in this pair
         */
        public void setVertex(Vertex vertex) {
            this.vertex = vertex;
        }

        /**
         * Gets the distance stored in this pair
         * @return the distance stored in this pair
         */
        public int getDistance() {
            return distance;
        }

        /**
         * Sets the distance to be stored in this pair
         * @param distance the distance to be stored in this pair
         */
        public void setDistance(int distance) {
            this.distance = distance;
        }

        @Override
        public int compareTo(VertexDistancePair v) {
            return (distance < v.getDistance() ? -1
                                          : distance > v.getDistance() ? 1 : 0);
        }
    }
}
