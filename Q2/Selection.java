package Q2;
import java.io.IOException;

public class Selection {
    public static void main(String[] args) throws IOException {
        //don run all at once maybe will make your laptop crash
        Selectionsort.processFile("Set1.csv", "Selection1.csv");
        System.out.println("");
        Selectionsort.processFile("Set2.csv", "Selection2.csv");
        System.out.println("");
        Selectionsort.processFile("Set3.csv", "Selection3.csv");
        System.out.println("");
        Selectionsort.processFile("Set4.csv", "Selection4.csv");
        System.out.println("");
        Selectionsort.processFile("Set5.csv", "Selection5.csv");
        System.out.println("");
        Selectionsort.processFile("Set6.csv", "Selection6.csv");
    }
}