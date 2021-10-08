import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Test{
    public static void main(String[] args) {
        int[] arr = new int[10000];
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int i = 0;
            while((line = br.readLine()) != null){
                arr[i] = Integer.parseInt(line);
                i++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println("error");
        }
        System.out.println(Arrays.toString(arr));
        MedianMaintenance mm = new MedianMaintenance(arr);
        double result = mm.sumMod(10000);
        System.out.println(result);
    }
}