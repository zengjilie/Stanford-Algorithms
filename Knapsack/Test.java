import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Test{

    public static void main(String[] args) {
        int capacity = 0;
        int itemNum = 0;
        //Greedy
        ArrayList<item> itemList = new ArrayList();
        //DP

        try {
            FileReader fr = new FileReader(".\\Data_big.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentItem;
            int n = 0;
            while((currentItem = br.readLine()) != null){
                if(n == 0){
                    String[] temp = currentItem.split("\s", 0);
                    capacity = Integer.parseInt(temp[0]);
                    itemNum = Integer.parseInt(temp[1]);
                    item newItem = new item(0,0,0);
                    itemList.add(newItem);
                }else{
                    String[] temp = currentItem.split("\s", 0);
                    int currentValue = Integer.parseInt(temp[0]);
                    int currentWeight = Integer.parseInt(temp[1]);
                    double currentRatio = (double)currentValue/currentWeight;
                    item newItem = new item(n, currentValue, currentWeight);
                    itemList.add(newItem);
                }
                n++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        Knapsack ks = new Knapsack(itemList, capacity, itemNum);
        int value = ks.computeOptimal(itemNum, capacity, 0);
        System.out.println(value);
    }
}
