import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class SetOne {
    
    SetOne () throws IOException {

        FileWriter writer = new FileWriter("./Set1.csv");
        Random random = new Random();
        // random.setSeed(1211101398); //setting the seed to get random-nums more distinct from other grp's
        // int[] seedRef = {1, 2, 1, 1, 1, 0, 1, 3, 9, 8};
        int[] seedRef = {1, 2, 2, 1, 3, 0, 1, 8, 7, 4};

        // ArrayList<Integer> casePicker = new ArrayList<>(Arrays.asList(0,1,2) );
        System.out.println(seedRef.length);

        for (int i = 0; i < 100; i++) {

            // Collections.shuffle(casePicker);
            
            switch (random.nextInt(3)) {

                case 0:
                    generateT(random, seedRef, writer);
                    break;
                case 1:
                    generateH(random, seedRef, writer);
                    break;
                case 2:
                    generateTh(random, seedRef, writer);
                    break;
                case 3:
    

                default:
                    break;
            }

        }

        writer.close();
    }

    void generateT(Random random, int[] seedRef, FileWriter writer) throws IOException { //for 10s

        int temp = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp2 = seedRef[Math.abs(random.nextInt()) % seedRef.length];

        writer.append(String.valueOf(temp));
        writer.append(String.valueOf(temp2) + "\n");
    }

    void generateH(Random random, int[] seedRef, FileWriter writer) throws IOException { //for 100s

        int temp = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp2 = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp3 = seedRef[Math.abs(random.nextInt()) % seedRef.length];

        writer.append(String.valueOf(temp));
        writer.append(String.valueOf(temp2));
        writer.append(String.valueOf(temp3) + "\n");
    }

    void generateTh(Random random, int[] seedRef, FileWriter writer) throws IOException { //for 1,000s

        
        int temp = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp2 = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp3 = seedRef[Math.abs(random.nextInt()) % seedRef.length];
        int temp4 = seedRef[Math.abs(random.nextInt()) % seedRef.length];

        writer.append(String.valueOf(temp));
        writer.append(String.valueOf(temp2));
        writer.append(String.valueOf(temp3));
        writer.append(String.valueOf(temp4) + "\n");
    }
}
