public class dataSet2 {
    public static void main(String[] args) {
        
        Star[] myStars = new Star[20];
        char[] name = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O','P', 'Q', 'R', 'S', 'T'};
        
        for (int i = 0; i < myStars.length; i++) {
            
            myStars[i] = new Star();
            myStars[i].setName(name[i]);
        }

        Star.setStarProperties(myStars);

        Star.cyclicConnect(myStars); //20 Edges Used

        for (Star star : myStars) {
            
            System.out.println(star);
        }

        int totalWeight = 0;

        for (Star star : myStars) {
            
            totalWeight += star.getWeight();
        }

        System.out.println("Total Weight: " + totalWeight);

        

        
        // System.out.println(Star.genRandomValue());
    }
}
