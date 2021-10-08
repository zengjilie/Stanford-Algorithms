import java.util.ArrayList;
import java.util.HashMap;

public class Graph {
    private HashMap<Integer, ArrayList<Integer>> graph;
    //public ArrayList<Integer> order; 
    private ArrayList<Integer> stack;
    private int[] magicOrder;
    private ArrayList<Integer> sccNums;
    private boolean[] visited;
    public  int[] order;
    private int tracker;
    private int vertexNum;


    Graph(HashMap<Integer, ArrayList<Integer>> graph, int vertexNum){
        this.graph = graph;
        this.vertexNum = vertexNum;
        this.visited = new boolean[this.vertexNum + 1];
        visited[0] = true;
        tracker = 0;
        order = new int[this.vertexNum];
        stack = new ArrayList<Integer>();
    }

    Graph(HashMap<Integer, ArrayList<Integer>> graph, int[] magicOrder, int vertexNum){
        this.graph = graph;
        this.vertexNum = vertexNum;
        this.visited = new boolean[this.vertexNum + 1];
        visited[0] = true;
        tracker = 0;
        this.magicOrder = magicOrder;
        sccNums = new ArrayList<Integer>();  
    }

    public void DFS(){
        for(Integer startV : graph.keySet()){
            if(!(tracker == vertexNum)){
                //System.out.println("start vertex " + startV);
                DFSLoop(startV);
            }
        }
        //System.out.println("stack is " + stack);
        int j = 0;
        for(int i = stack.size() - 1; i >= 0; i--){
            order[j] = stack.get(i);
            j++;
        }
        
    }

    public void DFSLoop(Integer startV){
        if(visited[startV] == false){
            visited[startV] = true;
            tracker++; 
            if(graph.containsKey(startV)){
                for(Integer nextV : graph.get(startV)){
                    if(visited[nextV] == false){
                        DFSLoop(nextV);
                    }
                }
            }
            stack.add(startV);
        }
            
    }
    
    public void DFSLoopSCC(Integer startV){
        if(visited[startV] == false){
            visited[startV] = true;
            tracker++;
            if(graph.containsKey(startV)){
                for(Integer nextV : graph.get(startV)){
                    if(visited[nextV] == false){
                        DFSLoopSCC(nextV);
                    }
                }

            }
        }
    }

    public ArrayList<Integer> SCC (){
        int visitedNum = 0;
        for(Integer startV : magicOrder){
            //System.out.println("startvertex" + startV);
            if(visited[startV] == false){
                if(!(tracker == this.vertexNum)){
                    DFSLoopSCC(startV);
                }

                //System.out.println("tracker is " + tracker);
                int currentSCCNum = tracker - visitedNum; 
                visitedNum = tracker;
                sccNums.add(currentSCCNum);
                //System.out.println("curretnSCCNum " + currentSCCNum);
            }
        }

        return sccNums;

    }

    
}
