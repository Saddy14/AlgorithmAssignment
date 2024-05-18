package Q1;
import java.io.FileWriter;
import java.io.IOException;

public class dataSet2 {
    public static void main(String[] args) throws IOException {
        
        Star[] myStars = new Star[20];
        
        for (int i = 0; i < myStars.length; i++) {
            
            myStars[i] = new Star();
            myStars[i].setName(i+1);
        }

        Star.setStarProperties(myStars); // Set All Star Properties x, y, z, weight & profit

        // Star.cyclicConnect(myStars); //20 Edges Used
        // Star.connect2(myStars); //20 Edges Used, Left 14
        // Star.connect3(myStars); // 14 Edges Used, All 54 Edges used
        FileWriter writer = new FileWriter("./dataSet2.csv");
        writer.write("Star Name, x, y, z, weight, profit \n");

        for (Star star : myStars) {
            
            // System.out.println(star);
            // writer.append();
            writer.append(String.valueOf(star.getName()) + ", " + String.valueOf(star.getX()) + ", " + String.valueOf(star.getY()) + 
            ", "+ String.valueOf(star.getZ()) + ", " + String.valueOf(star.getWeight()) + ", " + String.valueOf(star.getProfit())  + "\n");

        }
        writer.close();

        int totalWeight = 0;

        for (Star star : myStars) {
            
            totalWeight += star.getWeight();
        }

        System.out.println("Total Weight: " + totalWeight);
        // System.out.println(Star.edgeCount);

    }
}
