import java.io.IOException;


public class Heap {
    public static void main(String[] args) throws IOException {
        Heapsort.processFile("Set1.csv", "Heap1.csv");
        System.out.println("");
        Heapsort.processFile("Set2.csv", "Heap2.csv");
        System.out.println("");
        Heapsort.processFile("Set3.csv", "Heap3.csv");
        System.out.println("");
        Heapsort.processFile("Set4.csv", "Heap4.csv");
        System.out.println("");
        Heapsort.processFile("Set5.csv", "Heap5.csv");
        System.out.println("");
        Heapsort.processFile("Set6.csv", "Heap6.csv");
    }
}