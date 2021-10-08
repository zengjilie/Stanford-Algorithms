import java.util.ArrayList;
import java.util.HashMap;

public class MWIS {
    private HashMap<Integer, vertex> graph;
    HashMap<Integer, ArrayList<vertex>> memory;
    ArrayList<vertex> MIS;

    public MWIS(HashMap<Integer, vertex> pathGraph, int vertexNum){
        graph = pathGraph;
        memory = new HashMap<>();
        vertex firstVertex = graph.get(1);
        vertex secondVertex = graph.get(2);
        vertex biggerOne = firstVertex.weight > secondVertex.weight ? firstVertex : secondVertex;
        ArrayList<vertex> baseCase1 = new ArrayList<>();
        ArrayList<vertex> baseCase2 = new ArrayList<>();
        baseCase1.add(firstVertex);
        baseCase2.add(biggerOne);
        memory.put(1, baseCase1);
        memory.put(2, baseCase2);
        MIS = compute(vertexNum);

        System.out.println("done");
    }

    public ArrayList<vertex> compute(int currentVertex){
        if(currentVertex == 1){
            return memory.get(1); 
        }else if (currentVertex == 2){
            return memory.get(2);
        }else if(memory.containsKey(currentVertex)){
            return memory.get(currentVertex);
        }else{
            ArrayList<vertex> grandChild = compute(currentVertex - 2);
            ArrayList<vertex> child = compute(currentVertex - 1);
            int sumOfGrandChildren = 0;
            int sumOfChildren = 0;
            for(vertex v : grandChild){
                sumOfGrandChildren += v.weight;
            }
            sumOfGrandChildren += graph.get(currentVertex).weight;
            for(vertex v : child){
                sumOfChildren += v.weight;
            }
            if(sumOfGrandChildren > sumOfChildren){
                ArrayList<vertex> newGrandChild = deepCopy(grandChild);
                newGrandChild.add(graph.get(currentVertex));
                memory.put(currentVertex, newGrandChild);
                return newGrandChild;
            }else{
                ArrayList<vertex> newChild = deepCopy(child);
                memory.put(currentVertex, newChild);
                return newChild;
            }
        }
        
    } 

    public ArrayList<vertex> deepCopy(ArrayList<vertex> originalCopy){
        ArrayList<vertex> newCopy = new ArrayList<>();
        for(vertex v : originalCopy){
            newCopy.add(v);
        }
        return newCopy;
    }
}
