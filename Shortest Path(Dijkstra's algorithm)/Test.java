import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashMap;

public class Test {
    public static void main(String[] args) {
        HashMap<Integer, String[]> GraphSrc = new HashMap<Integer, String[]>();
        try {
            //FileReader fr = new FileReader(".\\DataTest.txt");
            FileReader fr = new FileReader(".\\Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                //1:["2,1","4,6"]   
                String[] currentLine = line.split("\t" , 0);
                //System.out.println("currentLine is " + Arrays.toString(currentLine));
                Integer currentVertex = Integer.parseInt(currentLine[0]);
                String[] adjV = Arrays.copyOfRange(currentLine, 1, currentLine.length);
                GraphSrc.put(currentVertex, adjV);
            }
            br.close();
        } catch (Exception e) {
            System.out.println("error");
        }
            for(Integer v : GraphSrc.keySet()){
                System.out.println(v + "=" + Arrays.toString(GraphSrc.get(v)) );
            }
        int vertexNum = 200;
        Graph newGraph = new Graph(GraphSrc, vertexNum);
        int[] result = newGraph.STP();
            System.out.println("result is " + Arrays.toString(result));
        System.out.println(
            result[7] + "," + result[37] + "," + result[59] + "," + 
            result[82] + "," + result[99] + "," + result[115] + "," + 
            result[133] + "," + result[165] + "," + result[188] + "," + result[197]
            );
        
    }
}