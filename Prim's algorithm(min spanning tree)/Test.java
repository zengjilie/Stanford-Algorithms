import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.LinkedList;

public class Test{
    public static void main(String[] args) {
        /**
         * hashmap
         * 1=[[2,4],[4,3]]
         * 2=
         */
        HashMap<Integer, LinkedList<node>> graph = new HashMap<>();
        int vertexNum = 0;
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String vertex; 
            int n = 0;
            while((vertex = br.readLine()) != null){
                 if(n == 0){
                    String[] temp = vertex.split("\s", 0);
                    vertexNum = Integer.parseInt(temp[0]);
                    n++;
                 }else{
                    String[] temp = vertex.split("\s", 0);
                    int vertexH = Integer.parseInt(temp[0]);
                    int vertexT = Integer.parseInt(temp[1]);
                    int cost = Integer.parseInt(temp[2]);
                    node vertexTCost = new node(vertexT, cost);
                    node vertexHCost = new node(vertexH, cost);
                    if(graph.containsKey(vertexH)){
                        LinkedList<node> adjV = graph.get(vertexH);
                        adjV.add(vertexTCost);
                    }else{
                        LinkedList<node> adjV1 = new LinkedList<>();
                        adjV1.add(vertexTCost);
                        graph.put(vertexH, adjV1);
                    }
                    if(graph.containsKey(vertexT)){
                        LinkedList<node> adjV = graph.get(vertexT);
                        adjV.add(vertexHCost);
                    }else{
                        LinkedList<node> adjV2 = new LinkedList<>();
                        adjV2.add(vertexHCost);
                        graph.put(vertexT, adjV2);
                    }
                 }
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        // for(Integer vertexH : graph.keySet()){
        //     LinkedList<node> adjV = graph.get(vertexH);
        //     System.out.println("vertexH " + vertexH);
        //     for(node vertexT : adjV){
        //         System.out.println(vertexT.vertexT + " " + vertexT.cost);
        //     }
        // }
        MST newGraph = new MST(graph, vertexNum);
        int minCost = newGraph.computeMinCost();
        System.out.println("minCost is " +  minCost);
    } 
}

class node{
    public int vertexT;
    public int cost;
    public node(int vertexT, int cost){
        this.vertexT = vertexT;
        this.cost = cost;
    }
}