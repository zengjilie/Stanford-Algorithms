import java.util.Arrays;
import java.util.HashMap;

public class Graph {
    private HashMap<Integer, String[]> GraphSrc;
    private HashMap<Integer, Integer> notVisited;
    private boolean[] visited;
    private int[] STPList;
    

    Graph(HashMap<Integer, String[]> GraphSrc, int vertexNum){
        this.GraphSrc = GraphSrc;
        notVisited = new HashMap<Integer, Integer>();
        for(int i = 0; i < vertexNum; i++){
            notVisited.put(i+1, Integer.MAX_VALUE);
        }
            //System.out.println(notVisited);
        visited = new boolean[vertexNum + 1];
        STPList = new int[vertexNum + 1];
        Arrays.fill(STPList, Integer.MAX_VALUE);
        STPList[1] = 0;
        //1:["2,1","4,6"] 
    }
    public int[] STP(){
        Integer pickedV = 1;
        STPList[1] = 0;
        String[] adjVList;
        Integer pickedVNextD = Integer.MAX_VALUE;
        
        while(notVisited.size() != 0){ //check if all vertexes have been traversed
            visited[pickedV] = true;
            notVisited.remove(pickedV);
            adjVList = GraphSrc.get(pickedV);
            for(String adjTuple : adjVList){
                String[] adjTupleProced = adjTuple.split(",", 0);
                int adjV = Integer.parseInt(adjTupleProced[0]);
                if(visited[adjV] == false){
                    int adjDist = Integer.parseInt(adjTupleProced[1]);
                    if(STPList[adjV] > (adjDist + STPList[pickedV])){
                        STPList[adjV] = adjDist + STPList[pickedV];
                        notVisited.put(adjV, STPList[adjV]);
                    }   
                }
            }
                //System.out.println(notVisited);
            for(Integer notVisitedV : notVisited.keySet()){
                int notVisitedDist = notVisited.get(notVisitedV);
                    //System.out.println("notVisitedV " + notVisitedV + " notVisitedVDist " + notVisitedDist );
                if(pickedVNextD > notVisitedDist){
                    pickedVNextD = notVisitedDist;
                    pickedV = notVisitedV;
                }
            }
                //System.out.println("pickedV is " + pickedV);
            pickedVNextD = Integer.MAX_VALUE;
        }
        
        return STPList;
    }
    
}

