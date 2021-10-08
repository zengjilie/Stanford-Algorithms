import java.util.Collections;
import java.util.PriorityQueue;

public class MedianMaintenance {
    private int[] arr;
    private double sumOfMedians;
    private PriorityQueue<Integer> Lh;
    private PriorityQueue<Integer> Rl;

    MedianMaintenance(int[] arr){
        this.arr = arr;
        countMedian(this.arr);
    }

    public void countMedian(int[] arr){
        this.Lh = new PriorityQueue<Integer>(Collections.reverseOrder());
        this.Rl = new PriorityQueue<Integer>();
        for(int key : arr){
            addNumber(key, this.Lh, this.Rl);
            rebalance(this.Lh, this.Rl);
            double currentMedian = countMedian(this.Lh, this.Rl);
            System.out.println(currentMedian);
            sumOfMedians += currentMedian;
            
        }
    }
    public void addNumber(int key, PriorityQueue<Integer> Lh, PriorityQueue<Integer> Rl ){
        if(Lh.size() == 0 || Lh.peek() > key){
            Lh.add(key);
        }else{
            Rl.add(key);
        }
    }

    public void rebalance(PriorityQueue<Integer> Lh, PriorityQueue<Integer> Rl){
        if((Lh.size() - Rl.size()) > 1){
            int LHighest = Lh.peek();
            Lh.poll();
            Rl.add(LHighest);
        }else if((Lh.size() - Rl.size()) < - 1 ){
            int RLowest = Rl.peek();
            Rl.poll();
            Lh.add(RLowest);
        }
    }

    public double countMedian(PriorityQueue<Integer> Lh, PriorityQueue<Integer> Rl){
        double median = 0;
        if((Lh.size() + Rl.size()) % 2 == 1){
            if(Lh.size() > Rl.size()){
                median = Lh.peek();
            }else{
                median = Rl.peek();
            }
        }else{
            median = Lh.peek();
        }
        return median;
    }

    public double sumMod(int mod){
        double result = sumOfMedians % mod;
        return result;
    }
}
