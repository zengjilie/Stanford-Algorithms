import java.util.HashMap;

public class APSP{
    private HashMap<String, Integer> edgeList;
    private int vertexNum;
    private HashMap<String, Integer> memory;
    public HashMap<Integer, Integer> vertexScore;

    public APSP(HashMap<String, Integer> edgeList, int vertexNum){
        this.edgeList = edgeList;
        this.vertexNum = vertexNum;
        memory = new HashMap<>();
        vertexScore = new HashMap<>();
    }

    //Bellman-Ford Algo
    public HashMap<String, Integer> reWeight(){
        for(int n = 0; n <= vertexNum; n++){
            edgeList.put("0>" + n, 0);
            vertexScore.put(n, 0);
        }
        int a = 0;
        for(int capacity = 0; capacity <= vertexNum; capacity ++){
            for(int endPoint = 0; endPoint <= vertexNum; endPoint ++){
                compute(capacity, endPoint);
                System.out.println(a);
                a++;
            }
        }
        HashMap<Integer, Integer> newVertexScore = deepCopy(vertexScore);
        compute(vertexNum + 1, vertexNum);

        for(int n = 0; n <= vertexNum; n++){
            if(newVertexScore.get(n) != vertexScore.get(n)){
                System.out.println("there exists negative cycle");
                break;
            }
        }


        HashMap<String, Integer> reWeightedEdgeList = new HashMap<>();
        for(String edge : edgeList.keySet()){
            String[] temp = edge.split(">", 0);
            Integer head = Integer.parseInt(temp[0]);
            Integer tail = Integer.parseInt(temp[1]);
            if(head != 0){
                int originalCost = edgeList.get(edge);
                int newCost = originalCost + vertexScore.get(head) - vertexScore.get(tail);
                reWeightedEdgeList.put(edge, newCost);
            }
        }
        return reWeightedEdgeList ;
    }

    public int compute(int capacity, int endPoint){
        int cost = 0;

        if(memory.containsKey(capacity + " " + endPoint)){
            return memory.get(capacity + " " + endPoint); 
        }else if(capacity == 0 || endPoint == 0){
            if(endPoint != 0){
                memory.put(capacity + " " + endPoint, Integer.MAX_VALUE);
                return Integer.MAX_VALUE;
            }else{
                memory.put(capacity + " " + endPoint, 0);
                return 0;
            }
        }else if(capacity == 1){
            memory.put(capacity + " " + endPoint, 0);
            return 0;
        }else{
            int case1 = compute(capacity - 1, endPoint);
            int case2 = Integer.MAX_VALUE;
            for(int n = 0; n <= vertexNum; n++){
                String lastEdge = n + ">" + endPoint;
                if(edgeList.containsKey(lastEdge)){
                    case2 = Math.min(case2, compute(capacity - 1, n) + edgeList.get(lastEdge));
                }
            }
            cost = Math.min(case1, case2); 
            memory.put(capacity + " " + endPoint, cost);
            if(cost < vertexScore.get(endPoint)){
                vertexScore.put(endPoint, cost);
            }
            return cost;
        }
    }

    public HashMap<Integer, Integer> deepCopy(HashMap<Integer, Integer> originalMap){
        HashMap<Integer, Integer> newMap = new HashMap<>();
        for(Integer endPoint : originalMap.keySet()){
            int cost = originalMap.get(endPoint);
            newMap.put(endPoint, cost);
        }
        return newMap;
        
    }
}