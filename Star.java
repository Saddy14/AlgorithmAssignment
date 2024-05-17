import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;

public class Star {

    private char name;
    private int x;
    private int y;
    private int z;
    private int weight;
    private int profit;
    private ArrayList<Star> connectedStars = new ArrayList<>();

    static int edgeCount;
    final static int TOTAL_EDGE = 54;
    
    public char getName() {
        return name;
    }
    public void setName(char name) {
        this.name = name;
    }
    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getZ() {
        return z;
    }
    public void setZ(int z) {
        this.z = z;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getProfit() {
        return profit;
    }
    public void setProfit(int profit) {
        this.profit = profit;
    }
    public ArrayList<Star> getConnectedStars() {
        return connectedStars;
    }
    public void addConnectedStars(Star connectedStars) {
        this.connectedStars.add(connectedStars);
    }
    public static int getEdgeCount() {
        return edgeCount;
    }
    public static void setEdgeCount(int edgeCount) {
        Star.edgeCount = edgeCount;
    }
    public static int getTotalEdge() {
        return TOTAL_EDGE;
    }

    @Override
    public String toString() {
        return "Star name = " + name + ", x = " + x + ", y = " + y + ", z = " + z + ", weight = " + weight + ", profit = " + profit
                + ", connectedStarsName = [" + connectedStarsName() + "]";
    }

    String connectedStarsName() {

        StringBuilder connectedStarsName = new StringBuilder();

        for (Star star : connectedStars) {
            
            // System.out.print(star.getName() + ", ");
            connectedStarsName.append(star.getName() + ", ");
        }

        return connectedStarsName.toString();
    }

    public static void cyclicConnect(Star[] myStars) {
        
        for (int i = 0; i < 20; i++)
        {

            if (i == 19) {
    
                myStars[i].addConnectedStars(myStars[0]);
                myStars[i].addConnectedStars(myStars[i-1]);
                // Star::edgeCount+=2;
            }
            else if (i >= 1)
            {
                myStars[i].addConnectedStars(myStars[i+1]);
                myStars[i].addConnectedStars(myStars[i-1]);
                // Star::edgeCount+=2;
            }
            else if (i == 0)
            {
                myStars[i].addConnectedStars(myStars[i+1]);
                myStars[i].addConnectedStars(myStars[19]);
                // Star::edgeCount+=2;
            }
        }

    }

    public static void setRandomName (Star[] myStars) {

        
    }

    public static void setStarProperties(Star[] myStars) {


        for (int i = 0; i < myStars.length; i++) {
            
            myStars[i].setX(genRandomValue());
            myStars[i].setY(genRandomValue());
            myStars[i].setZ(genRandomValue());
            // myStars[i].setWeight(genRandomValue() % 100 + 1);
            // myStars[i].setProfit(genRandomValue() % 99 + 1);
        }

    }

    private static int genRandomValue() {

        // Ern Qi 1221301874 + Teng Hui 1211102289 + Xin Thong 1211104274 = 3633307961
        int[] seedRef = {0, 1, 3, 6, 7, 9};
        // int[] seedRef = {3, 6, 0, 7, 9, 6, 1, 3};

        Random random = new Random();

        switch (random.nextInt(2)) {

            case 0:
                return generateT(random, seedRef);
            case 1:
                return generateH(random, seedRef);
            default:
                return 0;
        }


    }

    private static int generateT(Random random, int[] seedRef) { //for 10s

        int temp = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp2 = seedRef[Math.abs(random.nextInt()) % seedRef.length];

        // System.out.println("temp++ " + temp + temp2 );

        return Integer.parseInt(String.valueOf(temp) + String.valueOf(temp2));
    }

    private static int generateH(Random random, int[] seedRef) { //for 100s

        int temp = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp2 = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp3 = seedRef[Math.abs(random.nextInt()) % seedRef.length];

        // System.out.println("temp+++ " + temp + temp2 + temp3 );

        return Integer.parseInt(String.valueOf(temp) + String.valueOf(temp2) + String.valueOf(temp3));
    }
}


