import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Cluster {
    private ArrayList<edge> edgeList;
    //DFS
    private int k;   
    private int clusterNum;
    private HashMap<Integer, LinkedList<Integer>> graph;
    private boolean exist;
    private HashMap<Integer, Integer> heap;
    //Union - Find
    private HashMap<Integer, Integer> groups;
    private HashMap<Integer, ArrayList<Integer>> groupSize;

    public Cluster(ArrayList<edge> edgeList, int k, int vertexNum){
        this.edgeList = edgeList;
        this.k = k;
        clusterNum = vertexNum;
        graph = new HashMap<>();
        exist = false;
        heap = new HashMap<>();
    }

    public Cluster(ArrayList<edge> edgeList, int vertexNum){
        this.edgeList = edgeList;
        clusterNum = vertexNum;
        groups = new HashMap<>();
        groupSize = new HashMap<>();
    }
    //DFS
    public int computeMaxSpacing(){
        int maxSpacing = 0;
        int n = 0;
        edge currentEdge;
        while(clusterNum != k){
            currentEdge = edgeList.get(n);
                //System.out.println("currentEdge cost " + currentEdge.cost);
            int cost = currentEdge.cost;
            int nodeA = currentEdge.nodeA;
            int nodeB = currentEdge.nodeB;
                //System.out.println(nodeA + ">" + nodeB);
            if(existPath(nodeA, nodeB) == false){
                if(graph.containsKey(nodeA) == true){
                    LinkedList<Integer> adjV = graph.get(nodeA);
                    adjV.add(nodeB);
                }else{
                    LinkedList<Integer> adjV = new LinkedList<>();
                    adjV.add(nodeB);
                    graph.put(nodeA, adjV);
                } 
                if (graph.containsKey(nodeB) == true){
                    LinkedList<Integer> adjV = graph.get(nodeB);
                    adjV.add(nodeA);
                }else{
                    LinkedList<Integer> adjV = new LinkedList<>();
                    adjV.add(nodeA);
                    graph.put(nodeB, adjV);
                }
                clusterNum--;
            }
                //System.out.println("current clusterNum " + clusterNum);
            n++;
        }
            //System.out.println(graph);
        currentEdge = edgeList.get(n);
        int cost = currentEdge.cost;
        int nodeA = currentEdge.nodeA;
        int nodeB = currentEdge.nodeB;
            //System.out.println(nodeA + ">" + nodeB);
        maxSpacing = cost;
        while(existPath(nodeA, nodeB) == true){
            n++;
            currentEdge = edgeList.get(n);
            cost = currentEdge.cost;
            nodeA = currentEdge.nodeA;
            nodeB = currentEdge.nodeB;
            maxSpacing = cost;
                //System.out.println(nodeA + ">" + nodeB);
        }

        return maxSpacing;
    }
    //Union-find
    public int computeClusters(){
        int n = 0;
        edge currentEdge;
        while(n != edgeList.size()){
            currentEdge = edgeList.get(n);
            int nodeA = currentEdge.nodeA;
            int nodeB = currentEdge.nodeB;
            if(groups.containsKey(nodeA) || groups.containsKey(nodeB)){
                if(groups.containsKey(nodeA) == false){//exist nodeB
                    int leader_nodeB = groups.get(nodeB);
                    groups.put(nodeA, leader_nodeB);
                    ArrayList<Integer> adjV = groupSize.get(leader_nodeB);
                    adjV.add(nodeA);
                    groupSize.put(leader_nodeB, adjV);
                    clusterNum--;
                }else if(groups.containsKey(nodeB) == false){//exist nodeA
                    int leader_nodeA = groups.get(nodeA);
                    groups.put(nodeB, leader_nodeA);
                    ArrayList<Integer> adjV = groupSize.get(leader_nodeA);
                    adjV.add(nodeB);
                    groupSize.put(leader_nodeA, adjV);
                    clusterNum--;
                }else{//exist both nodeA and B
                    int leader_nodeA = groups.get(nodeA);
                    int leader_nodeB = groups.get(nodeB);
                    if(leader_nodeA != leader_nodeB){//union operation
                        if(groupSize.get(leader_nodeA).size() <= groupSize.get(leader_nodeB).size()){
                            for(Integer adjNode : groupSize.get(leader_nodeA)){
                                ArrayList<Integer> adjV_leader_nodeB = groupSize.get(leader_nodeB);
                                groups.put(adjNode, leader_nodeB);
                                adjV_leader_nodeB.add(adjNode);
                                groupSize.put(leader_nodeB, adjV_leader_nodeB);
                            }
                            groupSize.remove(leader_nodeA);
                        }else{
                            for(Integer adjNode : groupSize.get(leader_nodeB)){
                                ArrayList<Integer> adjV_leader_nodeA = groupSize.get(leader_nodeA);
                                groups.put(adjNode, leader_nodeA);
                                adjV_leader_nodeA.add(adjNode);
                                groupSize.put(leader_nodeA, adjV_leader_nodeA);
                            }
                            groupSize.remove(leader_nodeB);
                        }
                        clusterNum--;
                    }
                }
            }else{
                groups.put(nodeA, nodeA);     
                groups.put(nodeB, nodeA);
                ArrayList<Integer> adjV = new ArrayList<>();
                adjV.add(nodeA);
                adjV.add(nodeB);
                groupSize.put(nodeA, adjV);
                clusterNum--;
            }
            n++;
        }
        System.out.println("n is " + n);
        System.out.println("clusterNum is " + clusterNum);
        return clusterNum;
    }

    //DFS
    public boolean existPath(int s, int e){
        exist = false;
        if(graph.containsKey(s)){
            heap.put(s, 1);
            DFSLoop(s,e);
        }
        heap.clear();
        return exist;
    }

    public void DFSLoop(int s, int e){
        for(Integer adjV : graph.get(s)){
            if(heap.containsKey(adjV) ==  false){
                if(adjV != e ){
                    heap.put(adjV, 1);
                    DFSLoop(adjV, e);
                }else if(adjV == e){
                    exist = true;
                        //System.out.println("exist " + true + " " + e);
                }
            }
        }
    }
   
}
