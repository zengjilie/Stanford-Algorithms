import java.util.ArrayList;
import java.util.LinkedList;

public class Schedule {
    private ArrayList<LinkedList<Double>> arrayJob;
    private long sum;
    public Schedule(ArrayList<LinkedList<Double>> arrayJob){
        this.arrayJob = arrayJob;
        int start = 0;
        int end = arrayJob.size() - 1;
        QuickSortDecrease(start, end);
            //System.out.println(arrayJob);
    }

    public void QuickSortDecrease(int start, int end){
        //[[1,2,3],[2,3,4],[2,4,5]]
        //  i
        //          j
        if(end - start > 0){
            double pivot = arrayJob.get(end).get(0);
                
            int i = start, j = start;
            while(i < end & j < end){
                if(arrayJob.get(j).get(0) > pivot){
                    LinkedList<Double> temp = new LinkedList<Double>(arrayJob.get(i));
                    arrayJob.set(i, arrayJob.get(j));
                    arrayJob.set(j, temp);
                    i++;
                    j++;
                }else if (arrayJob.get(j).get(0) == pivot){
                    if(arrayJob.get(j).get(1) > arrayJob.get(end).get(1)){
                        LinkedList<Double> temp = new LinkedList<Double>(arrayJob.get(i));
                        arrayJob.set(i, arrayJob.get(j));
                        arrayJob.set(j, temp);
                        i++;
                        j++;
                    }else{
                        j++;
                    }
                }else{
                    j++;
                }
            }
            LinkedList<Double> temp = new LinkedList<Double>(arrayJob.get(i));
            arrayJob.set(i, arrayJob.get(end));
            arrayJob.set(end, temp);
            int startL = start;
            int endL = i - 1;
            int startR = i + 1;
            int endR = end;
            QuickSortDecrease(startL, endL);
            QuickSortDecrease(startR, endR);
        }


    }
//2*1 8*4 13*3

    public long sumOfCompletion(){
        int currentComTime = 0; 
        int currentSum = 0;
        int i = 1;
        for (LinkedList<Double> currentJob  : arrayJob){
            double w = currentJob.get(1);
            double l = currentJob.get(2);
            currentComTime += l;
            currentSum = (int)w * currentComTime;
            sum += (long)currentSum;
            
            i++;
        }
        return sum;
    }
}
