import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Test{
    public static void main(String[] args) {
        long start = System.nanoTime();
        int vertexNum = 0;
        HashMap<Integer, vertex> vertexList = new HashMap<>();
        HashMap<String, Float> preComputedDistance = new HashMap<>();
        try {
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            int n = 0;
            String currentVertex;
            while((currentVertex = br.readLine()) != null){
                if(n == 0){
                    vertexNum = Integer.parseInt(currentVertex);
                }else{
                    String[] temp = currentVertex.split("\s", 0);
                    float x = Float.parseFloat(temp[0]);
                    float y = Float.parseFloat(temp[1]);
                    vertex newVertex = new vertex(n, x, y);
                    vertexList.put(n, newVertex);
                }
                n++;
            }
            for(int a = 1; a <= vertexNum; a++){
                for(int b = a + 1; b <= vertexNum; b++){
                    vertex aV = vertexList.get(a);
                    vertex bV = vertexList.get(b);
                    float x = aV.x - bV.x;
                    float y = aV.y - bV.y;
                    float distanceAB = (float)Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)); 
                    String ab = a + " " + b;
                    String ba = b + " " + a;
                    preComputedDistance.put(ab, distanceAB);
                    preComputedDistance.put(ba, distanceAB);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        vertex startVertex = vertexList.get(1);
        TSP tsp = new TSP(vertexNum, preComputedDistance);
        int result = tsp.computeTSP(startVertex);
        System.out.println(result);
        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(duration);        

    }
}