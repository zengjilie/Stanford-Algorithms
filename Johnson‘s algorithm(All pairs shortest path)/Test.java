import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Test{
    public static void main(String[] args) {
        HashMap<String, Integer> edgeList = new HashMap<>();
        int vertexNum = 0;
        int edgeNum = 0;
        HashMap<Integer, Integer> vertexWithOutGoingEdge = new HashMap<>();
        try {
            /**
             * data1 has negative cycle
             * data2 has negative cycle
             * data3 good to go
             */
            FileReader fr = new FileReader(".\\Data3.txt");
            BufferedReader br = new BufferedReader(fr);
            int n = 0;
            String currentEdge;
            while((currentEdge = br.readLine()) != null){
                if(n == 0){
                    String[] temp = currentEdge.split("\s", 0);
                    vertexNum = Integer.parseInt(temp[0]);
                    edgeNum = Integer.parseInt(temp[1]);
                    n++;
                }else{
                    String[] temp = currentEdge.split("\s", 0);
                    String edge = temp[0] + ">" + temp[1];
                    int cost = Integer.parseInt(temp[2]);
                    vertexWithOutGoingEdge.put(Integer.parseInt(temp[0]), 0);
                    edgeList.put(edge, cost);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }

        
        APSP apsp = new APSP(edgeList, vertexNum);
        HashMap<String, Integer> reWeightedEdgeList = apsp.reWeight();
        HashMap<Integer, Integer> vertexScore = apsp.vertexScore;
        
        PriorityQueue<edge> shortesPair = new PriorityQueue<>(new AscendingSort());

        for(int a = 1; a <= vertexNum; a ++){
            if(vertexWithOutGoingEdge.containsKey(a)){
                Dijkstra shortestPath = new Dijkstra(reWeightedEdgeList, a, vertexNum, vertexScore);
                edge lowestCostEdge = shortestPath.computeLowestCost();
                shortesPair.add(lowestCostEdge);
            }
        }
        edge answer = shortesPair.poll();
        System.out.println(answer.head + ">" + answer.tail + " " + answer.cost);
    }
}

class AscendingSort implements Comparator<edge>{
    public int compare(edge a, edge b){
        return a.cost - b.cost;
    }
}