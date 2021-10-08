import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Test{
    public static void main(String[] args) {
        
        HashMap<Integer,vertex> pathGraph = new HashMap<>();
        int vertexNum = 0;
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String currentVertex;
            int n = 0;
            while((currentVertex = br.readLine()) != null){
                if(n == 0){
                    vertexNum = Integer.parseInt(currentVertex);
                    vertex newVertex = new vertex(n, 0);
                    pathGraph.put(n, newVertex);
                }else{
                    vertex newVertex = new vertex(n, Integer.parseInt(currentVertex));
                    pathGraph.put(n, newVertex);
                }
                n++;
            }
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        MWIS result = new MWIS(pathGraph, vertexNum);
        ArrayList<vertex> MIS = result.MIS;
        HashMap<Integer, Integer> temp = new HashMap<>();
        for(vertex v : MIS){
            temp.put(v.number, 0);
        }
        System.out.println("done");
        System.out.println(temp.containsKey(1));
        System.out.println(temp.containsKey(2));
        System.out.println(temp.containsKey(3));//true > false
        System.out.println(temp.containsKey(4));//false > true
        System.out.println(temp.containsKey(17));
        System.out.println(temp.containsKey(117));
        System.out.println(temp.containsKey(517));
        System.out.println(temp.containsKey(997));
        
    }

}