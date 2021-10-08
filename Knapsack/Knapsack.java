import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Knapsack {
    private ArrayList<item> itemList;
    private int capacity;
    private HashMap<String, Integer> memory;
   
    public Knapsack(ArrayList<item> itemList, int capacity, int itemNum){
        this.itemList = itemList;
        this.capacity = capacity;
        memory = new HashMap<>();
        
    }

    public int computeOptimal(int index, int capacity, int value){
        int optimalValue = 0;
        String xy = Integer.toString(index) + Integer.toString(capacity);
        if(capacity == 0 || index == 0){
            return value;
        }else if(memory.containsKey(xy)){
            optimalValue = value + memory.get(xy);
            return optimalValue;
        }else{
            item currentItem = itemList.get(index);
            int currentValue = currentItem.value;
            int currentWeight = currentItem.weight;
            if(currentWeight > capacity){
                optimalValue = computeOptimal(index - 1, capacity, value);
                memory.put(xy, optimalValue - value);
                return optimalValue;
            }else{
                int case1 = computeOptimal(index - 1, capacity, value);
                int case2 = computeOptimal(index - 1, capacity - currentWeight, value + currentValue);
                optimalValue = Math.max(case1, case2); 
                memory.put(xy, optimalValue - value);
                return optimalValue;
            }
        }

    }
}
