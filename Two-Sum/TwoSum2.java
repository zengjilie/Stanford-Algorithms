import java.util.HashMap;

public class TwoSum2 extends Thread {
    
        private HashMap<Long, Integer> table;
        private int uniqueSum;
        private int lowerBound;
        private int upperBound;
    
        public TwoSum2(HashMap<Long, Integer> table){
            this.table = table;
            uniqueSum = 0;
            lowerBound = -6000;
            upperBound = -2001;
        }
    
        public void run(){
            HashMap<Long, Long> duplicatePairs = new HashMap<Long, Long>();
            for(long t = lowerBound; t <= upperBound; t++){
                    //System.out.println("current t is " + t);
                for(Long x : table.keySet()){
                    if(duplicatePairs.size() < 1){
                        long y = t - x;
                        if(table.containsKey(y) && x != y){
                            duplicatePairs.put(x, y);
                            //System.out.println("current pair is " + x + " " + y);
                        }
                    }
                }
                if(duplicatePairs.size() == 1){
                    uniqueSum++;
                }
                duplicatePairs.clear();
            }
            System.out.println("sum2 is " + uniqueSum);
        }
    
}
