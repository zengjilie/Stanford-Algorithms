import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Test{
    public static void main(String[] args) {
        HashMap<Long, Integer> table = new HashMap<>();
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String num;
            while((num = br.readLine()) != null){
                Long currNum = Long.parseLong(num);
                table.put(currNum, 0);
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e); 
        }
        //System.out.println(table);

        TwoSum1 ts1 = new TwoSum1(table);
        TwoSum2 ts2 = new TwoSum2(table);
        TwoSum3 ts3 = new TwoSum3(table);
        TwoSum4 ts4 = new TwoSum4(table);
        TwoSum5 ts5 = new TwoSum5(table);
        long start = System.nanoTime();
        ts1.run();
        ts2.run();
        ts3.run();
        ts4.run();
        ts5.run();
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(duration);

        
        //-2 -1 0 1 2
        //-3 -2 1 0 | -1 0 1 | 1 2 | 3 
        //-3 -2 -1 2 3 | 5
        
        
    }

}