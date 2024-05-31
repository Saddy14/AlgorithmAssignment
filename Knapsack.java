import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Star {
    String name;
    int x, y, z, weight, profit;

    public Star(String name, int x, int y, int z, int weight, int profit) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.z = z;
        this.weight = weight;
        this.profit = profit;
    }

    //Change to string before save into txt files
    @Override
    public String toString() {
        return "Star Name: " + name + ", Weight: " + weight + ", Profit: " + profit;
    }
}

public class Knapsack {
    public static void main(String[] args) {
        String csvFile = "./dataSet2.csv";
        String line;
        String csvSplitBy = ",";
        List<Star> stars = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] starData = line.split(csvSplitBy);

                try {
                    String name = starData[0].trim();
                    int x = Integer.parseInt(starData[1].trim());
                    int y = Integer.parseInt(starData[2].trim());
                    int z = Integer.parseInt(starData[3].trim());
                    int weight = Integer.parseInt(starData[4].trim());
                    int profit = Integer.parseInt(starData[5].trim());

                    stars.add(new Star(name, x, y, z, weight, profit));
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid number format in line: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int maxCapacity = 800;
        knapsack(stars, maxCapacity);
    }

    public static void knapsack(List<Star> stars, int maxCapacity) {
        int n = stars.size();
        int[][] dp = new int[n + 1][maxCapacity + 1];

        for (int i = 1; i <= n; i++) {
            Star star = stars.get(i - 1);
            for (int w = 1; w <= maxCapacity; w++) {
                if (star.weight <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - star.weight] + star.profit);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int w = maxCapacity;
        int totalWeight = 0;
        int totalProfit = 0;
        List<Star> selectedStars = new ArrayList<>();
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                Star star = stars.get(i - 1);
                selectedStars.add(star);
                w -= star.weight;
                totalWeight += star.weight;
                totalProfit += star.profit;
            }
        }

        // Display the results
        System.out.println("Selected Stars:");
        for (Star star : selectedStars) {
            System.out.println("Star Name: " + star.name + ", Weight: " + star.weight + ", Profit: " + star.profit);
        }

        // Display total weight and total profit
        System.out.println("Total Weight: " + totalWeight);
        System.out.println("Total Profit: " + totalProfit);
       
        //Write the results into txt file
        saveResults(selectedStars, totalWeight, totalProfit);
    }

    //Write file
    public static void saveResults(List<Star> selectedStars, int totalWeight, int totalProfit) {
        try (FileWriter saveResult = new FileWriter("Knapsack.txt")) {
            for (Star star : selectedStars) {
                saveResult.write(star.toString() + "\n");
            }
            saveResult.write("Total Weight: " + totalWeight + "\n");
            saveResult.write("Total Profit: " + totalProfit + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
