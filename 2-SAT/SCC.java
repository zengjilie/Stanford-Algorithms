import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class SCC{
    //HashMap , ArrayList
    HashMap<String, HashSet<String>> graph;
    HashMap<String, HashSet<String>> reverseGraph;

    ArrayList<ArrayList<String>> SCC;
    HashSet<String> visited;
    ArrayList<String> stack;
    ArrayList<String> magicalOrder;

    public SCC(HashMap<String, HashSet<String>> graph, HashMap<String, HashSet<String>> reverseGraph){
        this.graph = graph;
        this.reverseGraph = reverseGraph;  
        this.visited = new HashSet<>();
        this.magicalOrder = new ArrayList<>();
        stack = new ArrayList<>(); 
    }

    public ArrayList<ArrayList<String>> findAllSCCs(){
        //DFS graph + get magicalOrder
        DFS(graph);

        //DFS reverseGraph + use magicalOrder
        stack.clear();
        visited.clear();
        ArrayList<ArrayList<String>> allSCCs = reverseDFS(reverseGraph, magicalOrder);
        return allSCCs;
    }

    public void DFS (HashMap<String, HashSet<String>> graph){
        for(String startV : graph.keySet()){
            if(visited.contains(startV) == false){
                DFSLoop(startV);
                for(int i = stack.size() - 1; i >= 0; i--){
                    magicalOrder.add(0, stack.get(i));
                }
                stack.clear();
            }
        }
    }

    public ArrayList<ArrayList<String>> reverseDFS (HashMap<String, HashSet<String>> reverseGraph, ArrayList<String> magicalOrder){
        ArrayList<ArrayList<String>> allSCCs = new ArrayList<>();
        for(String startV : magicalOrder){
            if(visited.contains(startV) ==  false){
                DFSLoopReverse(startV);
                ArrayList<String> newSCC = new ArrayList<>();
                for(String backTrackV : stack){
                    newSCC.add(backTrackV);
                }
                allSCCs.add(newSCC);
                stack.clear();
            }
        }
        return allSCCs;
    }

    public void DFSLoop(String startV){
        if(visited.contains(startV) == false){
            visited.add(startV);
            stack.add(startV);
            if(graph.containsKey(startV)){
                for(String adjV : graph.get(startV)){
                    DFSLoop(adjV);
                }
            }else{
                stack.remove(stack.size() - 1);
                magicalOrder.add(0,startV);
            }
        }
        
    }

    public void DFSLoopReverse(String startV){
        if(visited.contains(startV) == false){
            stack.add(startV);
            visited.add(startV);
            if(reverseGraph.containsKey(startV)){
                for(String adjV : reverseGraph.get(startV)){
                    DFSLoopReverse(adjV);
                }
            }
        }
    }
   
}