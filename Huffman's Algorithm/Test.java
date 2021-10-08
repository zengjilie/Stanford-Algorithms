import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Test{
    public static void main(String[] args) {
        PriorityQueue<symbol> minHeap = new PriorityQueue<>(new AscendingSort());
        HashMap<String, node> tree = new HashMap<>();
        int symbolNum = 0;

        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentSymbol;
            int n = 0;
            while((currentSymbol = br.readLine()) != null){
                if(n == 0){
                    symbolNum = Integer.parseInt(currentSymbol);
                }else{
                    symbol newSymbol = new symbol(Integer.toString(n), Integer.parseInt(currentSymbol));
                    minHeap.add(newSymbol);
                    node newNode = new node(Integer.toString(n),null,null);
                    tree.put(Integer.toString(n), newNode);
                }
                n++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
        //check if the order is right? yes
        // for(symbol s: minHeap){
        //     System.out.println("symbol is "  + s.symbol + " weight is " + s.weight);
        // }
        //System.out.println(tree);

        AlphabetEncode ae = new AlphabetEncode(minHeap, tree);
        ArrayList<EncodeWord> result = ae.result;
        Collections.sort(result, new AscendingSort_encodeWord());

        System.out.println(result.get(0).codeLength);
        System.out.println(result.get(result.size() - 1).codeLength);
    }
}

class AscendingSort implements Comparator<symbol>{
    public int compare (symbol symbol1, symbol symbol2){
        return symbol1.weight - symbol2.weight;
    }
}

class AscendingSort_encodeWord implements Comparator<EncodeWord>{
    public int compare (EncodeWord word1, EncodeWord word2){
        return word1.codeLength - word2.codeLength;
    }
}
