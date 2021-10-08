import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeSet;

public class MST {
    private HashMap<Integer, LinkedList<node>> graph;
    private int minCost;
    private LinkedList<Integer> visited;
    private int tracker;
    private TreeSet<node> minHeap;

    public MST(HashMap<Integer, LinkedList<node>> graph, int vertexNum){
        this.graph = graph;
        minCost = 0;
        visited = new LinkedList<>();
        minHeap = new TreeSet<>(new comparator()); 
        tracker = 0; 
    }

    public int computeMinCost(){
        int vertexS = 1;
        visited.add(vertexS);
        tracker ++;
        while(tracker != graph.size()){
            for(int visitedV : visited){
                for(node adjV : graph.get(visitedV)){
                    if(visited.contains(adjV.vertexT) == false){
                        minHeap.add(adjV);
                            //System.out.println(adjV.vertexT);
                    }
                }
            }
                //System.out.println("size of heap " + minHeap.size());
            node chosenV = minHeap.first();
            visited.add(chosenV.vertexT);
                //System.out.println("choosen vertex is " + chosenV.vertexT);
            minCost += chosenV.cost;
            tracker++;
            minHeap.clear();
        }
        return minCost;
    }
}

class comparator implements Comparator<node>{
    public int compare(node node1, node node2){
        return node1.cost - node2.cost;
    }
}