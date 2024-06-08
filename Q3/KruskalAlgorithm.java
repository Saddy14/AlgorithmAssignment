package Q3;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import Q1.Star;

class Edge{
    int src, dest;
    int distance;

    Edge(int src, int dest, int distance) {
        this.src = src;
        this.dest = dest;
        this.distance = distance;
    }

}

public class KruskalAlgorithm {
    private int[] parent;

    public int find(int i) {
        //This condition checks whether the current element i is its own parent. 
        //If i is its own parent, it means i is the root of its subset
        if (parent[i] != i)
            parent[i] = find(parent[i]);
        return parent[i];
    }

    public void Kruskal(ArrayList<Edge> edges, int V) {
        //sorts the edges of the graph based on their weights in ascending order.
        // Sort using Comparator
        Collections.sort(edges, new Comparator<Edge>() {
            @Override
            public int compare(Edge edge1, Edge edge2) {
                return Integer.compare(edge1.distance, edge2.distance);
            }
        });

        parent = new int[V+1];
        for (int i = 0; i <= V; i++) {
            parent[i] = i;
        }

        int mst_weight = 0;
        List<Edge> mst = new ArrayList<>();

        for (Edge edge : edges) {
            //
            int x = find(edge.src);
            int y = find(edge.dest);

           // System.out.println(x);
            //System.out.println(y);
            //If x and y are different, it indicates that edge.src and edge.dest are in different subsets, and the edge can be safely added without forming a cycle, 
            if (x != y) {
                mst.add(edge);
                //u.union(x, y);
                parent[y] = x;
                mst_weight += edge.distance;
            }
            if (mst.size() == V - 1) break;
        }

        System.out.println("Edges in MST");
        for (Edge edge : mst) {
            System.out.println(edge.src + " -- " + edge.dest + "  distance:  " + edge.distance);
        }
        System.out.println("\ntotal MST: " + mst_weight);
    }

    public static void main(String[] args) {
       // String filePath = "dataSet2.csv"; // Path to the CSV file
        ArrayList<Edge> edges = new ArrayList<>();
        int vertices = 20;  // Number of vertices in graph

        HashMap<Integer, double[]> starPositions = new HashMap<>();

         // Read dataset2.csv to get star positions
        try (BufferedReader br = new BufferedReader(new FileReader("dataset2.csv"))) {
            String line = br.readLine(); // Read header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int starName = Integer.parseInt(values[0]);
                double x = Double.parseDouble(values[1]);
                double y = Double.parseDouble(values[2]);
                double z = Double.parseDouble(values[3]);
                starPositions.put(starName, new double[]{x, y, z});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        
        // Read connected_stars.csv and compute distances
        try (BufferedReader br = new BufferedReader(new FileReader("connected_stars.csv"))) {
            String line =br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                int star1 = Integer.parseInt(values[0]);
                int star2 = Integer.parseInt(values[1]);
                double[] pos1 = starPositions.get(star1);
                double[] pos2 = starPositions.get(star2);
                int distance = Star.calculateDistance(pos1, pos2);
                edges.add(new Edge(star1, star2, distance));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        


        //edges.add(new Edge(0, 1, 1));
        //edges.add(new Edge(1, 2, 2));
       // edges.add(new Edge(2, 0, 2)); //Edge(2, 0, 2): find(2) traces back to 0, and find(0) finds root 0. Since the roots are the same, adding this edge would create a cycle. Skip this edge.
        

        KruskalAlgorithm kruskal = new KruskalAlgorithm();
        kruskal.Kruskal(edges, vertices);
    }
}
