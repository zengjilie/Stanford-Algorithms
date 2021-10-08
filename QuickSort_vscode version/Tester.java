import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class Tester {
    public static void main(String[] args) throws IOException{
        try {
            int[] arr = new int[10000];
            File file = new File(".\\data.txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String sr;
            int i = 0;
            while((sr = br.readLine()) != null){
                arr[i] = Integer.parseInt(sr);
                i++;
            }
            br.close();
            
            // test on small data
            // int[] arr = new int[] {3,1,5,2};

            int S = 0;
            int E = arr.length - 1;
            //System.out.println(E);
            QuickSort qs = new QuickSort(arr, S, E);
            int[] sortedArray = qs.sortedArray;
            int comp = qs.comparisons;
            System.out.println(Arrays.toString(sortedArray));
            System.out.println(comp); 
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }
        
    
}
