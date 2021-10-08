import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;


public class RandomizedContraction{

    public static int Contract(ArrayList<ArrayList<String>> adj){
        int minCut = 0;
        int counter = 1;
        while(adj.size() != 2){
            Collections.shuffle(adj);
            ArrayList<String> x = adj.get(0); 
            ArrayList<String> y = adj.get(1); 
            String win = x.get(0);
            String loss = y.get(0);
            for(int i = 0; i < y.size(); i++){
                String v = y.get(i);
                if(x.contains(v) == false){
                    x.add(v);
                }
            }
            while(x.contains(loss)){
                x.remove(x.indexOf(loss));
            }
            //System.out.println("here");
            adj.remove(1);
            for(int j = 1; j < adj.size(); j++){
                ArrayList<String> update = adj.get(j);
                while(update.contains(loss)){
                    update.set(update.indexOf(loss), win);
                }
            }
            
            
            //System.out.println("Contraction "+ adj.toString());
            //counter++;
        }
        minCut = Math.max(adj.get(0).size(), adj.get(1).size()) - 1;
        System.out.println(adj.toString());


        return minCut;
    }
    public static void main(String[] args) {
      
        ArrayList<ArrayList<String>> adjList = new ArrayList<ArrayList<String>>();
        try{
            File f = new File("Graph.txt");
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String vertices;
            while((vertices = br.readLine()) != null){
                ArrayList<String> edges = new ArrayList<String>();
                String[] partition = vertices.split("\t", 0);
                for(String cache : partition){
                    edges.add(cache);
                }
                adjList.add(edges);
            }
            
        }catch(Exception e){
            System.out.println("error");
        }
        
        //print the adjList
        //System.out.println(adjList.toString());
        
        int minCut = Contract(adjList);
        System.out.println("minCut is " + minCut);

        
           
        
        
        

    }
}