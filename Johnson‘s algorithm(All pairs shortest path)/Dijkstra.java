import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class Dijkstra{
    private HashMap<Integer, LinkedList<edge>> graph;
    private PriorityQueue<edge> heap;
    private HashMap<Integer, Integer> visited;
    private HashMap<Integer, Integer> vertexScore;
    private HashMap<Integer, Integer> table;
    private int vertexNum;
    private int startVertex;


    public Dijkstra(HashMap<String, Integer> edgeList, int startVertex, int vertexNum, HashMap<Integer, Integer> table){
        heap = new PriorityQueue<>(new AscendingSort());
        graph = new HashMap<>();
        this.startVertex = startVertex; 
        this.table = table;
        this.vertexNum = vertexNum;
        visited = new HashMap<>();
        vertexScore = new HashMap<>();
        for(int n = 1; n <= vertexNum; n++){
            if(n == startVertex){
                vertexScore.put(n, 0);
            }else{
                vertexScore.put(n, Integer.MAX_VALUE);
            }
        }

        for(String edge : edgeList.keySet()){
            String[] temp = edge.split(">", 0);
            int head = Integer.parseInt(temp[0]);
            int tail = Integer.parseInt(temp[1]);
            int cost = edgeList.get(edge);
            if(graph.containsKey(head) == false){
                edge newEdge = new edge(head, tail, cost);
                LinkedList<edge> adjV = new LinkedList<>();
                adjV.add(newEdge);
                graph.put(head, adjV);
            }else{
                edge newEdge = new edge(head, tail, cost);
                LinkedList<edge> adjV = graph.get(head);
                adjV.add(newEdge);
                graph.put(head,adjV);
            }
        }
        int a = 0;

    }

    public edge computeLowestCost(){
        int start = startVertex;
        int n = 1;
        while(n != vertexNum){
            visited.put(start, 0);
            if(graph.containsKey(start) == true){
                LinkedList<edge> adjV = graph.get(start);
                for(edge e : adjV){
                    int adjVTail = e.tail;
                    int adjVCost = e.cost;
                    heap.add(e);
                    if(visited.containsKey(adjVTail) == false){
                        int newCost = Math.min(vertexScore.get(adjVTail), vertexScore.get(start) + adjVCost);
                        vertexScore.put(adjVTail, newCost); 
                    }
                } 
                edge shortestEdge = heap.poll();
                start = shortestEdge.tail;
            }
            n++;
        }

        int shortestPairCost = Integer.MAX_VALUE;
        int tail = 0;
        for(int endPoint : vertexScore.keySet()){
            if(endPoint != startVertex){
                int unWeightedCost =  0;
                if(vertexScore.get(endPoint) == Integer.MAX_VALUE){
                    unWeightedCost = Integer.MAX_VALUE;
                }else{
                    unWeightedCost =  vertexScore.get(endPoint) - table.get(startVertex) + table.get(endPoint);
                }
                if(shortestPairCost > unWeightedCost){
                    shortestPairCost = unWeightedCost;
                    tail = endPoint;
                }
            }
        }
        edge result = new edge(startVertex, tail, shortestPairCost);
        return result;
    }
}

