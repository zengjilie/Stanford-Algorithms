import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Test_big {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> vertexList = new HashMap<>();
        int bit = 0;
        int vertexNum = 0;
        try {
            FileReader fr = new FileReader(".\\Data_big.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentVertex;
            int n = 0;
            while((currentVertex = br.readLine()) != null){
                if(n == 0){
                    String[] temp = currentVertex.split("\s", 0);
                    bit = Integer.parseInt(temp[1]);
                    vertexNum = Integer.parseInt(temp[0]);
                }else{
                    int currentScore = 0;
                    String[] temp = currentVertex.split("\s", 0);
                    int m = bit - 1;
                    for(String currentBit : temp){
                        currentScore = Integer.parseInt(currentBit) * (int)Math.pow(2, m) + currentScore;
                        m--;
                    }
                    if(vertexList.containsKey(currentScore) == false){
                        ArrayList<Integer> vertexWithSameSocre = new ArrayList<>();
                        vertexWithSameSocre.add(n);
                        vertexList.put(currentScore, vertexWithSameSocre);
                    }else{
                        ArrayList<Integer> vertexWithSameSocre = vertexList.get(currentScore);
                        vertexWithSameSocre.add(n);
                        vertexList.put(currentScore, vertexWithSameSocre);
                    }
                }
                n++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
            //System.out.println(vertexList); 
        HashMap<Integer, Integer> visited = new HashMap<>();
        ArrayList<edge> edgeList = new ArrayList<>();

        int[] shiftBit1 = new int[bit];
        int[] shiftBit2 = new int[bit * (bit - 1)/2];

        for(int m = 0; m < bit; m++){
            shiftBit1[m] = (int)Math.pow(2,m);
        }
        int x = 0;
        for(int m = 0; m < bit; m++){
            for(int n = m + 1; n < bit; n++){
                shiftBit2[x] = (int)Math.pow(2,m) + (int)Math.pow(2,n);
                x++;
            }
        }

            // System.out.println(Arrays.toString(shiftBit1)); 

        
        //xor operator 
        for(Integer score : vertexList.keySet()){
            visited.put(score, 1);
            if(vertexList.get(score).size() > 1){//distance = 0 
                ArrayList<Integer> vertices = vertexList.get(score);
                for(int n = 0; n < vertices.size(); n++){
                    for(int m = n + 1; m < vertices.size(); m++){
                        edge newEdge0 = new edge(0, vertices.get(n), vertices.get(m));
                        edgeList.add(newEdge0);
                    }
                }
            }

            for(int bitSet : shiftBit1){//distance = 1
                int xorResult = score ^ bitSet;//00000 = 00001^00001
                if(vertexList.containsKey(xorResult) && visited.containsKey(xorResult) == false){
                    ArrayList<Integer> vertices = vertexList.get(score); 
                    ArrayList<Integer> counterVertices = vertexList.get(xorResult);
                    for(Integer vertex : vertices){
                        for(Integer counterVertex : counterVertices){
                            edge newEdge1 = new edge(1, vertex, counterVertex);
                            edgeList.add(newEdge1);
                        }
                    }
                }
            }

            for(int bitSet : shiftBit2){//distance = 2
                int xorResult = score ^ bitSet;// 00010= 00001 + 00011
                if(vertexList.containsKey(xorResult) && visited.containsKey(xorResult) == false){
                    ArrayList<Integer> vertices = vertexList.get(score); 
                    ArrayList<Integer> counterVertices = vertexList.get(xorResult);
                    for(Integer vertex : vertices){
                        for(Integer counterVertex : counterVertices){
                            edge newEdge2 = new edge(2, vertex, counterVertex);
                            edgeList.add(newEdge2);
                        }
                    }
                }
            }
        }
//32_18
        Collections.sort(edgeList, new sortByCost());

            // for(edge e : edgeList){
            //     System.out.println(e.cost + " " + e.nodeA + ">" + e.nodeB);
            // }
        //vertices in the edges with distance of 1 or 2
        //vertices that are outside of this pool are guaranteed to have distance at least 3 
 
        HashMap<Integer, Integer> vertexInEdgeList = new HashMap<>();

        for(edge e : edgeList){
            if(vertexInEdgeList.containsKey(e.nodeA) == false){
                vertexInEdgeList.put(e.nodeA, 1);
            }
            if(vertexInEdgeList.containsKey(e.nodeB) == false){
                vertexInEdgeList.put(e.nodeB, 1);
            }
        }
            System.out.println("original cluster num is " + vertexInEdgeList.size()); 
            System.out.println("edge size is " + edgeList.size()); 

        int verticesOutSide = vertexNum - vertexInEdgeList.size();

        Cluster clu = new Cluster(edgeList, vertexInEdgeList.size());
        int clustersInEdgeList = clu.computeClusters();

        int result = verticesOutSide + clustersInEdgeList;
        System.out.println("result is " + result);
        
    }
}