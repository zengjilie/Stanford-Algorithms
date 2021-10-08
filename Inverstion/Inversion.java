
/**
 * Write a description of Inverstion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
import java.util.*;
import java.math.*;

public class Inversion 
{   
    private BigInteger inverNum ;
    
    public Inversion()
    {
        //MergeSort(arr);
        inverNum = new BigInteger("0");
    }
    
    public int[] MergeSort(int[] arr)
    {
        //start index and end index , not n size()
        int n = arr.length;  
        int mid = n/2;
        if(n == 1)
        {
           return arr;
        }
        else
        {
           int[] L = split(arr, 0, mid-1);
           int[] R = split(arr, mid,n-1);
           int[] Ls = MergeSort(L);
           int[] Rs = MergeSort(R);
           int[] M = merge(n,Ls,Rs);
           return M;
        }
    }
    
    private int[] split(int[] arr, int start, int end)
    {
        int[] half = new int[end - start + 1];
        for(int i = 0 ; i< half.length ; i++)
        {
            half[i] = arr[start];
            start ++;
        }
        //System.out.println(Arrays.toString(half));
        return half;
    }
    
    private int[] merge(int n, int[] Ls, int[] Rs)
    {
        int[] M = new int[n];
        int k = 0;
        int i = 0;
        int j = 0;
        while(i < Ls.length && j < Rs.length)
        {
            if(Ls[i] < Rs[j])
            {
                M[k] = Ls[i];
                i++;
                
            }
            else if(Rs[j] < Ls[i])
            {
                M[k] = Rs[j];
                BigInteger length = BigInteger.valueOf(Ls.length - i);
                inverNum = inverNum.add(length);
                j++;
                
                
            }
            k++;
        }
        if(i == Ls.length)
        {
            while(j < Rs.length)
            {
                M[k] = Rs[j];
                j++;
                k++;
            }
        }
        else if(j == Rs.length)
        {
            while(i < Ls.length)
            {
                M[k] = Ls[i];
                i++;
                k++;
            }
        }
        //System.out.println(Arrays.toString(M));
        return M;
    }
    
    public BigInteger countInversions()
    {
        return inverNum;
    }
    
}