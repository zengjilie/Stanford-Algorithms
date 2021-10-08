import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class AlphabetEncode {
    private PriorityQueue<symbol> minHeap;
    private HashMap<String, node> tree;
    private node rootNode;
    public ArrayList<EncodeWord> result;
    
    public AlphabetEncode(PriorityQueue<symbol> minHeap, HashMap<String, node> tree){
        this.minHeap = minHeap;
        this.tree = tree;
        result = new ArrayList<>();
        computeTree();
    }   
    public void computeTree(){
        String metaSymbolName = "";
        while(minHeap.size() != 1){
            symbol minWeightSymbol1 = minHeap.poll();
            symbol minWeightSymbol2 = minHeap.poll();
            metaSymbolName = minWeightSymbol1.symbol + minWeightSymbol2.symbol;
            int metaSymbolWeight = minWeightSymbol1.weight + minWeightSymbol2.weight;
            symbol metaSymbol = new symbol(metaSymbolName, metaSymbolWeight);
            minHeap.add(metaSymbol);

            node minWeightNode1 = tree.get(minWeightSymbol1.symbol);
            node minWeightNode2 = tree.get(minWeightSymbol2.symbol); 
            node metaNode = new node(metaSymbolName, minWeightNode1, minWeightNode2);
            tree.put(metaSymbolName, metaNode);
        }
        rootNode = tree.get(metaSymbolName);
        StringBuilder code = new StringBuilder();
        traverse(rootNode, code, 0);
        
        System.out.println("done");
    }

    public void traverse (node rootNode, StringBuilder code, int length){
        node leftNode = rootNode.leftNode;
        node rightNode = rootNode.rightNode;
        StringBuilder codeLeft = new StringBuilder(code.toString());
        StringBuilder codeRight = new StringBuilder(code.toString());
        int lengthLeft = length;
        int lengthRight = length;
        if(leftNode != null){
            codeLeft.append("0");
            lengthLeft++;
            traverse(leftNode, codeLeft, lengthLeft);
        }
        //length--;
        //code.deleteCharAt(length-1);
        if(rightNode != null){
            codeRight.append("1");
            lengthRight++;
            traverse(rightNode, codeRight, lengthRight);
        }

        if(leftNode == null && rightNode == null){
            EncodeWord newEW = new EncodeWord(rootNode.name, code.toString(), length);
            result.add(newEW);
        }
    }
    
}
