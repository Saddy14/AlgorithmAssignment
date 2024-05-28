package Q1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import Q1.Star;
/* 
class Star {
    int name;
    int x, y, z;

    public Star(int name, int x, int y, int z) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public double calculateDistance(Star other) {
        return Math.sqrt(Math.pow(other.x - this.x, 2) + Math.pow(other.y - this.y, 2) + Math.pow(other.z - this.z, 2));
    }
}
*/

class Edge {
    int vertex;
    double weight;

    public Edge(int vertex, double weight) {
        this.vertex = vertex;
        this.weight = weight;
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

    public void addStar(int name, int x, int y, int z) {
        star = new Star();
        //stars.put(name, new Star(name, x, y, z));
        star.setName(name);
        star.setX(x);
        star.setY(y);
        star.setZ(z);
        stars.put(name, star);
    }

    public void addEdge(int sourceId, int destinationId) {
        if (stars.containsKey(sourceId) && stars.containsKey(destinationId)) {
            double weight = stars.get(sourceId).calculateDistance(stars.get(destinationId));
            adjacencyList.get(sourceId).add(new Edge(destinationId, weight));
            adjacencyList.get(destinationId).add(new Edge(sourceId, weight)); 
        }
    }

    public void printAdjacencyList() {
        System.out.println("Adjacency List:");
        for (int i = 1; i < adjacencyList.size()-1; i++) {
            System.out.print("Star " + i + ": ");
            for (Edge edge : adjacencyList.get(i)) {
                System.out.print("(" + edge.vertex + ", " + String.format("%.2f", edge.weight) + ") ");
            }
            System.out.println();
        }
    }
}

public class Dijkstra{
    private Graph graph;

    public Dijkstra(Graph graph) {
        this.graph = graph;
    }

    public void execute(int sourceVertex) {
        boolean[] visited = new boolean[graph.vertices + 1];
        double[] distances = new double[graph.vertices + 1];
        int[] predecessors = new int[graph.vertices+1];
        Arrays.fill(predecessors, -1);
        Arrays.fill(distances, Double.MAX_VALUE);
        distances[sourceVertex] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingDouble(e -> e.weight));
        pq.offer(new Edge(sourceVertex, 0));

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentVertex = current.vertex;
            if (visited[currentVertex]) continue;
            visited[currentVertex] = true;

            for (Edge edge : graph.adjacencyList.get(currentVertex)) {
                if (!visited[edge.vertex]) {
                    double newDist = distances[currentVertex] + edge.weight;
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

    private void printDistances(double[] distances, int sourceVertex) {
        System.out.println("Shortest paths from vertex " + sourceVertex + ":");
        for (int i = 1; i < distances.length; i++) {
            System.out.println("To vertex " + i + " - " + (distances[i] == Double.MAX_VALUE ? "No path" : String.format("%.2f", distances[i])));
        }
    }

    public static void printPaths(int sourceVertex, int[] predecessors, int vertices) {
        System.out.println("\nGraph representing shortest Paths from vertex " + sourceVertex + ":");
        for (int i = 0; i <= vertices; i++) {
            if (i != sourceVertex && predecessors[i] != -1) {
                System.out.print("Path to " + i + ": ");
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

    private static void initializeEdges(Graph graph) {
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 20);

        for (int startStar = 2; startStar <= 14; startStar++) {
            for (int i = 1; i <= 3; i++) {
                int endStar = startStar + i;
                graph.addEdge(startStar, endStar);
            }
        }
        for (int startNode = 15; startNode <= 18; startNode++) {
            for (int i = 1; i <= 2; i++) {
                int endNode = startNode + i;
                graph.addEdge(startNode, endNode);
            }
        }
        graph.addEdge(19, 1);
        graph.addEdge(19, 20);
        graph.addEdge(20, 2);
        
    }


    public static void main(String[] args) {
        Graph graph = new Graph(20); // Example with 21 vertices
        String filePath = "dataSet2.csv"; // Path to the CSV file

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = reader.readLine(); // Read header to skip it
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                int starId = Integer.parseInt(data[0].trim());
                int x = Integer.parseInt(data[1].trim());
                int y = Integer.parseInt(data[2].trim());
                int z = Integer.parseInt(data[3].trim());
                graph.addStar(starId, x, y, z);
            }
        } catch (IOException e) {
            System.out.println("Error reading the file: " + e.getMessage());
            return;
        }

        initializeEdges(graph);
      
        //uncomment this for adjacency list
       // graph.printAdjacencyList();

        Dijkstra dijkstra = new Dijkstra(graph);

         // Start timing
         long startTime = System.currentTimeMillis();
         dijkstra.execute(1);
         // End timing
         long endTime = System.currentTimeMillis();

         // Calculate elapsed time
         long elapsedTime = endTime - startTime;
 
         // Print the execution time
         System.out.println("\nExecution time in milliseconds: " + elapsedTime);
    }
}
