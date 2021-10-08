import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Test{
    public static void main(String[] args) {
        /**
         * cost, nodeA, nodeB
         * 
         */
        ArrayList<edge> edgeList = new ArrayList<>(); 
        int vertexNum = 0;
        try {
            FileReader fr = new FileReader(".\\Data_big.txt");
            BufferedReader br = new BufferedReader(fr);
            String vertex;
            int n = 0;
            while((vertex = br.readLine()) != null){
                if(n == 0){
                    vertexNum = Integer.parseInt(vertex);
                    n++;
                }else{
                    String[] temp = vertex.split("\s", 0);
                    int cost = Integer.parseInt(temp[2]);
                    int nodeB = Integer.parseInt(temp[1]);
                    int nodeA = Integer.parseInt(temp[0]);
                    edge currentEdge = new edge(cost, nodeA, nodeB);
                    edgeList.add(currentEdge);
                }
            }   

        } catch (Exception e) {
            System.out.println(e);
        }
        Collections.sort(edgeList, new sortByCost());
            //System.out.println(vertexNum + " initial clusterings");
        for(edge currentEdge: edgeList){
            //System.out.println("currentEdge is " + currentEdge.cost + "\s" + currentEdge.nodeA + ">" + currentEdge.nodeB);
        }
        int k = 4;
        Cluster clu = new Cluster(edgeList, k, vertexNum);
        int maxSpacing = clu.computeMaxSpacing();
        System.out.println("maxSpacing is " + maxSpacing);
    }

}

class sortByCost implements Comparator<edge>{
    public int compare(edge a, edge b){
        return a.cost - b.cost;
    }
}