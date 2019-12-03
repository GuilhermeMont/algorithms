import java.io.*;
import java.util.ArrayList;

public class Sat {
    public void loadGraph (File file) throws IOException{

        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        String st = null;
        int numberOfNodes =  Integer.parseInt(br.readLine());

        for (int i =0; i < numberOfNodes ; i ++) {

            try {
                if (!((st = br.readLine()) != null)) break;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
