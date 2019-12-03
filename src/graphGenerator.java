
import java.io.FileWriter;
import java.io.File;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.FileNotFoundException;


public class graphGenerator {


    private static double getRandomInteger(double min, double max) {
        double x = (int) (Math.random() * ((max - min) + 1)) + min;
        return x;
    }


    public void generate(int nodes, String fileName) {


        File file = new File(fileName);
        FileWriter fr = null;
        BufferedWriter br = null;
        try {
            fr = new FileWriter(file);
            br = new BufferedWriter(fr);
            br.write(nodes + System.getProperty("line.separator"));
            for (int i = nodes; i > 0; i--) {
                for (int j = nodes; j > 0; j--) {
                    if (getRandomInteger(0, 10) <= 5) {
                        br.write("1 ");
                    } else {
                        br.write("0 ");
                    }

                }

                br.write(System.getProperty("line.separator"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


    }
}
