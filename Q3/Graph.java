package Q3;


import Q1.Star;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;



class Edge {
    int vertex;
    int distance;

    public Edge(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    public int getVertex() {
        return vertex;
    }
}

class Graph {
    int vertices;
    Map<Integer, Star> stars;
    List<List<Edge>> adjacencyList;
    Star star;

    public Graph(int vertices) {
        this.vertices = vertices;
        stars = new HashMap<>();
        adjacencyList = new ArrayList<>();
        for (int i = 0; i <= vertices; i++) {
            adjacencyList.add(new ArrayList<>());
        }
    }


    public void addEdge(int source, int destination, int distance) {
        // Add edge from source to destination if it doesn't exist
        if (!edgeExists(source, destination, distance)) {
            adjacencyList.get(source).add(new Edge(destination, distance));
        }
        
        // Since this is an undirected graph, add edge from destination to source if it doesn't exist
        if (!edgeExists(destination, source, distance)) {
            adjacencyList.get(destination).add(new Edge(source, distance));
        }
    }

    public boolean edgeExists(int from, int to, double distance) {
        for (Edge edge : adjacencyList.get(from)) {
            if (edge.getVertex() == to && Double.compare(edge.distance, distance) == 0) {
                return true;
            }
        }
        return false;
    }

    public void printAdjacencyList() {
        System.out.println("Adjacency List:");
        for (int i = 1; i < adjacencyList.size(); i++) {
            System.out.print("Star " + i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print("(" + edge.vertex + ", " +  edge.distance + ") ");
            }
            System.out.println();
        }
    }

    public static void dijkstra(Graph graph, int sourceVertex) {
        // keeps track of whether a vertex has been fully processed
        boolean[] visited = new boolean[graph.vertices + 1];

        // stores the shortest distance from the source vertex to every other vertex
        int[] distances = new int[graph.vertices + 1];

        // holds the previous vertex from the source for each vertex
        int[] predecessors = new int[graph.vertices + 1];

        // arrays filled with -1 indicating no predecessors initially
        // Arrays initially filled with distances set to Integer.MAX_VALUE
        Arrays.fill(predecessors, -1);
        Arrays.fill(distances, Integer.MAX_VALUE);

        // The distance to the source vertex is set to 0, as no distance to itself
        distances[sourceVertex] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.distance));
        pq.offer(new Edge(sourceVertex, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentVertex = current.vertex;

            if (visited[currentVertex]) {
                continue;
            }

            visited[currentVertex] = true;

            List<Edge> edges = graph.adjacencyList.get(currentVertex);
            for (Edge edge : edges) {
                if (!visited[edge.vertex]) {
                    int newDist = distances[currentVertex] + edge.distance;
                    if (newDist < distances[edge.vertex]) {
                        distances[edge.vertex] = newDist;
                        predecessors[edge.vertex] = currentVertex;
                        pq.offer(new Edge(edge.vertex, newDist));
                    }
                }
            }
        }

        printDistances(distances, sourceVertex);
        printPaths(sourceVertex, predecessors, graph.vertices);
    }

    private static void printDistances(int[] distances, int sourceVertex) {
        System.out.println("\nShortest paths from star " + sourceVertex + ":");
        for (int i = 1; i < distances.length; i++) {
            System.out.println("To star " + i + " - "
                    + (distances[i] == Integer.MAX_VALUE ? "No path" : Integer.toString(distances[i])));
        }
    }

    public static void printPaths(int sourceVertex, int[] predecessors, int vertices) {
        System.out.println("\nGraph representing shortest Paths from star " + sourceVertex + ":");
        for (int i = 0; i <= vertices; i++) {
            if (i != sourceVertex && predecessors[i] != -1) {
                System.out.print("Path to star " + i + ": ");
                printPath(i, predecessors);
                System.out.println();
            }
        }
    }

    public static void printPath(int vertex, int[] predecessors) {
        if (predecessors[vertex] != -1) {
            printPath(predecessors[vertex], predecessors);
            System.out.print(" -> ");
        }
        System.out.print(vertex);
    }

    public static void processFile(Graph graph) {
        HashMap<Integer, double[]> starPositions = new HashMap<>();

        //read dataset2
        try (BufferedReader br = new BufferedReader(new FileReader("dataSet2.csv"))) {
            String line = br.readLine(); 
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int starName = Integer.parseInt(values[0]);
                double x = Double.parseDouble(values[1]);
                double y = Double.parseDouble(values[2]);
                double z = Double.parseDouble(values[3]);
                starPositions.put(starName, new double[] { x, y, z });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read connected_stars.csv and compute distances
        try (BufferedReader br = new BufferedReader(new FileReader("connected_stars.csv"))) {
            String line = br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int star1 = Integer.parseInt(values[0]);
                int star2 = Integer.parseInt(values[1]);
                double[] pos1 = starPositions.get(star1);
                double[] pos2 = starPositions.get(star2);
                int distance = Star.calculateDistance(pos1, pos2);
                graph.addEdge(star1, star2, distance);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        Graph graph = new Graph(20); // Example with 20 vertices

       processFile(graph);

        // uncomment this for adjacency list
       //graph.printAdjacencyList();

        // Start timing
        long startTime = System.currentTimeMillis();

        dijkstra(graph, 1);

        // End timing
        long endTime = System.currentTimeMillis();

        // Calculate elapsed time
        long elapsedTime = endTime - startTime;

        // Print the execution time
        System.out.println("\nExecution time in milliseconds: " + elapsedTime);
    }

}
