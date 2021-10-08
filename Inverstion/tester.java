
/**
 * Write a description of tester here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.*;
import java.io.*;
import edu.duke.*;
import java.math.*;
public class tester 
{
    public void test()
    {
        FileResource fr = new FileResource("numbers.txt");
        ArrayList<Integer> arrl = new ArrayList<Integer>();
        for (String temp : fr.lines())
        {
            int num = Integer.parseInt(temp);
            arrl.add(num);
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
        BigInteger inverNum= inv.countInversions();
        //System.out.println(Arrays.toString(newArr));
        System.out.println(inverNum.toString());
        //System.out.println(arr.length);
    }   

    public static void main(String[] args) {
        tester tt = new tester();
        tt.test();
    }
}
