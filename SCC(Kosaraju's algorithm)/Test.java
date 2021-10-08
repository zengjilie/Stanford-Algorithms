import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

public class Test {
    public static void QuickSort (int[] arr, int l, int r){
        if(l < r){
            int pp = arr[r];
            int i = l, j = l;
            if((r - l + 1) > 1){
                while(i < r && j < r){
                    if(arr[j] < pp){
                        int temp = arr[i];
                        arr[i] = arr[j];
                        arr[j] = temp;
                        i++;
                        j++;
                    }else{
                        j++;
                    }
                }
                int temp = arr[i];
                arr[i] = pp;
                arr[r] = temp;
                QuickSort(arr, l, i - 1);
                QuickSort(arr, i + 1, r);
            }
        } 
    }
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<Integer, ArrayList<Integer>>();
        HashMap<Integer, ArrayList<Integer>> graphReverse = new HashMap<Integer, ArrayList<Integer>>();
        try{
            //FileReader file = new FileReader(".\\GraphSmallData_2.txt");
            //FileReader file = new FileReader(".\\GraphSmallData.txt");
            FileReader file = new FileReader(".\\GraphData.txt");
            BufferedReader br = new BufferedReader(file);
            String nl;
            while((nl = br.readLine()) != null){
                String[] vertex = nl.split("\s", 0);
                //System.out.println(vertex[0] + " " + vertex[1]);
                if(!graph.containsKey(Integer.parseInt(vertex[0]))){
                    ArrayList<Integer> edge = new ArrayList<Integer>();
                    edge.add(Integer.parseInt(vertex[1]));
                    graph.put(Integer.parseInt(vertex[0]), edge);
                }else{
                    ArrayList<Integer> edge = graph.get(Integer.parseInt(vertex[0]));
                    edge.add(Integer.parseInt(vertex[1]));
                }

                if(!graphReverse.containsKey(Integer.parseInt(vertex[1]))){
                    ArrayList<Integer> edge = new ArrayList<Integer>();
                    edge.add(Integer.parseInt(vertex[0]));
                    graphReverse.put(Integer.parseInt(vertex[1]), edge);
                }else{
                    ArrayList<Integer> edge = graphReverse.get(Integer.parseInt(vertex[1]));
                    edge.add(Integer.parseInt(vertex[0]));
                }
            }
            br.close();
        }catch (Exception e){
            System.out.println("error");
        }

        // for(Integer key : graph.keySet()){
        //     System.out.println(key + " " + graph.get(key));
        // }
        
        // System.out.println("\n");
        // for(Integer key : graphReverse.keySet()){
        //     System.out.println(key + " " + graphReverse.get(key));
        // }
        
        int vertexNum = 875714;//GrpahData.txt
        //int vertexNum = 12;//GraphSmallData_2.txt
        //int vertexNum = 7;//GraphSmallData.txt

        Graph graReverse = new Graph(graphReverse, vertexNum);
        graReverse.DFS();
        int[] magicOrder = graReverse.order;
        //System.out.println("magicOrder is " + Arrays.toString(magicOrder));


        Graph gra = new Graph(graph, magicOrder, vertexNum);
        ArrayList<Integer> sccNums = gra.SCC();
        int[] result = new int[sccNums.size()];
        int i = 0;
        for(Integer currentInt : sccNums){
            result[i] = currentInt.intValue();
            i++;
        }
        QuickSort(result, 0, result.length - 1);   
        for(int j = result.length - 1; j >= result.length - 5; j--){
            System.out.println(result[j]);

        }
        


    }
}
