import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class TWOSAT{
    public static boolean test(){
        boolean result = true;

        //create implication graph
        HashMap<String, HashSet<String>> graph = new HashMap<>();
        HashMap<String, HashSet<String>> reverseGraph = new HashMap<>();
        HashSet<String> vertices = new HashSet<>(); 
        try {
            FileReader fr = new FileReader("2sat6.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int n = 0;
            while((line = br.readLine()) != null){
                if(n == 0){
                   n++; 
                }else{
                    //bookkeep vertices
                    String[] temp = line.split("\s", 0);
                    String a = temp[0];
                    String b = temp[1];
                    String aNot = a.charAt(0) == '-'? a.substring(1, a.length()):"-" + a;
                    String bNot = b.charAt(0) == '-'? b.substring(1, b.length()):"-" + b;
                    
                    vertices.add(a);
                    vertices.add(b);
                    vertices.add(aNot);
                    vertices.add(bNot);
                    //build implication graph
                    if(a.equals(b)){
                        //aNot > b
                        if(graph.containsKey(aNot) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(b);
                            graph.put(aNot, adjNew);
                        }else{
                            HashSet<String> adj = graph.get(aNot);
                            adj.add(b);
                        }
                        //reverVersion b > aNot
                        if(reverseGraph.containsKey(b) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(aNot);
                            reverseGraph.put(b, adjNew);
                        }else{
                            HashSet<String> adj = reverseGraph.get(b);
                            adj.add(aNot);
                        }
                    }else{
                        //aNot > b
                        if(graph.containsKey(aNot) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(b);
                            graph.put(aNot, adjNew);
                        }else{
                            HashSet<String> adj = graph.get(aNot);
                            adj.add(b);
                        }
                        //reverseVersion  b > aNot

                        if(reverseGraph.containsKey(b) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(aNot);
                            reverseGraph.put(b, adjNew);
                        }else{
                            HashSet<String> adj = reverseGraph.get(b);
                            adj.add(aNot);
                        }
                        //bNot > a

                        if(graph.containsKey(bNot) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(a);
                            graph.put(bNot, adjNew);
                        }else{
                            HashSet<String> adj = graph.get(bNot);
                            adj.add(a);
                        }
                        //reverseVersion  a > bNot

                        if(reverseGraph.containsKey(a) == false){
                            HashSet<String> adjNew  = new HashSet<>();
                            adjNew.add(bNot);
                            reverseGraph.put(a, adjNew);
                        }else{
                            HashSet<String> adj = reverseGraph.get(a);
                            adj.add(bNot);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error is: " + e);
        }
        
        //create normal grpah find bugs;

        // try {
        //     FileReader fr = new FileReader("graph_test.txt");
        //     BufferedReader br = new BufferedReader(fr);
        //     String line;
        //     while((line = br.readLine()) != null){
        //         String[] temp = line.split("\s", 0);
        //         String from = temp[0];
        //         String to = temp[1];
        //         if(graph.containsKey(from) == false){
        //             HashSet<String> set = new HashSet<>();
        //             set.add(to);
        //             graph.put(from, set);
        //         }else{
        //             HashSet<String> set = graph.get(from);
        //             set.add(to);
        //         }
        //         //reverse
        //         if(reverseGraph.containsKey(to) == false){
        //             HashSet<String> set = new HashSet<>();
        //             set.add(from);
        //             reverseGraph.put(to, set);
        //         }else{
        //             HashSet<String> set = reverseGraph.get(to);
        //             set.add(from);
        //         }
        //     }
        //     br.close();
        // } catch (Exception e) {
        //     System.out.println("Error:" + e);
        // }

        //find all SCCs
        SCC scc = new SCC(graph, reverseGraph);
        ArrayList<ArrayList<String>> allSCCs = scc.findAllSCCs(); 
        //check for contradiction -1 > 1 and 1 > -1
        int i = 0;
        for(ArrayList<String> SCC : allSCCs){
            if(findContradiction(SCC) == true){
                result = false;
                System.out.println(SCC);
                System.out.println("i: " + i);
                break;
            }
            i++;
        }
        
        
        return result;
    }

    public static boolean findContradiction(ArrayList<String> SCC){
        boolean contradiction = false;
        HashSet<String> set = new HashSet<>();
        for(String s : SCC){
            set.add(s);
        }
        for(String s : SCC){
            String sNot = s.charAt(0) == '-'? s.substring(1, s.length()):"-" + s;
            if(set.contains(sNot)){
                System.out.println("sNot: " + sNot);
                contradiction = true;
            }
        }
        //for()
        return contradiction;
    }
    public static void main(String[] args) {

        boolean result = TWOSAT.test();

        System.out.println(result);
    }
}