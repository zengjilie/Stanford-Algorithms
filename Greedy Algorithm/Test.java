import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Test{
    public static void main(String[] args) {
        // wj-lj> wj> lj
        // wj/lj> wj> lj
        ArrayList<LinkedList<Double>> arrayDiff = new ArrayList<LinkedList<Double>>();
        ArrayList<LinkedList<Double>> arrayRatio = new ArrayList<LinkedList<Double>>();
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String job;
            int n = 0;
            while((job = br.readLine()) != null){
                if(n == 0){
                    int size = Integer.parseInt(job);
                    arrayDiff = new ArrayList<LinkedList<Double>>(size);
                    arrayRatio = new ArrayList<LinkedList<Double>>(size);
                    n++;
                }else{
                    String[] temp = job.split("\s",0);
                    double w = Double.parseDouble(temp[0]);
                    double l = Double.parseDouble(temp[1]);
                    double diff = w - l;
                    double ratio = w/l;
                    LinkedList<Double> currentJobDiff = new LinkedList<Double>();
                    LinkedList<Double> currentJobRatio = new LinkedList<Double>();
                    currentJobDiff.add(diff);
                    currentJobDiff.add(w);
                    currentJobDiff.add(l);
                    currentJobRatio.add(ratio);
                    currentJobRatio.add(w);
                    currentJobRatio.add(l);
                    arrayDiff.add(currentJobDiff);
                    arrayRatio.add(currentJobRatio);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        //System.out.println(arrayDiff);
        //System.out.println(arrayRatio);
        Schedule byDiff = new Schedule(arrayDiff);
        Schedule byRatio = new Schedule(arrayRatio);
        long sumDiff = byDiff.sumOfCompletion();
        long sumRatio = byRatio.sumOfCompletion();
        System.out.println("sumDiff is " + sumDiff);
        System.out.println("sumRatio is " + sumRatio);
    }
}

