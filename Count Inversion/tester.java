import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.math.BigInteger;
public class tester
{
    public void test() 
    {
        FileReader file = null;
        //IOException is checked exception, it needs to be handled by the develoer.
        try{
            file = new FileReader("numbers.txt");
        }catch(IOException e){
            System.out.println("file not found");
        }
        
        BufferedReader br = new BufferedReader(file);
        ArrayList<Integer> arrl = new ArrayList<Integer>();
        String temp = null;
        try{
            temp = br.readLine();
        }catch(IOException e){
            System.out.println("IOException error");
        }
        
        while (temp != null) 
        {
            int num = Integer.parseInt(temp);
            arrl.add(num);
        }
        try {
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int[] arr = new int[arrl.size()];
        for(int i = 0; i < arrl.size();i++)
        {
            arr[i] = arrl.get(i);
        }

        //int[] test = {8,3,6,5,4,7,2,1};
        //Inversion inv = new Inversion();  
        //int[] newArr = inv.MergeSort(test);
        //int inverNum = inv.countInversions();
        
        Inversion inv = new Inversion();
        int[] newArr = inv.MergeSort(arr);
        BigInteger inverNum = inv.countInversions();
        System.out.println(Arrays.toString(newArr));
        System.out.println(inverNum.toString());
        //System.out.println(arr.length);
    }   

    public static void main(String[] args) {
        tester tt = new tester();
        try{
            tt.test();
        }catch(Exception e){
            System.out.println("function call has something wrong");
        }
        
    }
}
