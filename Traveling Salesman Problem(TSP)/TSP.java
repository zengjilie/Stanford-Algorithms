import java.util.HashMap;

public class TSP {
    private int vertexNum;
    private HashMap<String, Float> preComputedDistance;
    // private HashMap<String, Float> memory;
    private float[][] memory;
    private int[] bitMask;
    private vertex startVertex;
    private int remainderDecimal;
    private String cacheKey;
    
    public TSP(int vertexNum, HashMap<String, Float> preComputedDistance){
        this.vertexNum = vertexNum;
        this.preComputedDistance = preComputedDistance;
        // memory = new HashMap<>();
        memory = new float [vertexNum + 1][(int)Math.pow(2, vertexNum) + 1];
        StringBuilder binaryString = new StringBuilder();
        bitMask = new int[vertexNum];
        for(int n = 0; n < vertexNum; n++ ){
            if(n == vertexNum - 1){
                binaryString.append(0);
            }else{
                binaryString.append(1);
            }
            int currentMask = (int)Math.pow(2, n);
            bitMask[n] = currentMask;
        }
        remainderDecimal = Integer.parseInt(binaryString.toString(), 2);
    }

    public int computeTSP(vertex startVertex){
        this.startVertex = startVertex;
        int startNumber = startVertex.number;
        //bitmasking
        int result = (int)TSPLoop(startNumber, remainderDecimal);
        return result;
    }
    
    //use bitmasking to remember the index of remainder vertex in bits form

    public float TSPLoop(int from, int remainder){
        float distance = 0;
        if(remainder == 0){//base case, zero reaminder
            distance = preComputedDistance.get(from + " " + 1);
            memory[from][remainder] = distance;            
            return distance;
        }else if(memory[from][remainder] != 0) { //memorization
            return memory[from][remainder];
        }else{ // substructure
                //System.out.println(remainderBinaryString);
            for(int n = 0; n < vertexNum; n++){
                int maskResult = remainder & bitMask[n];
                if(maskResult != 0){
                    int nextStartIndex = n + 1;
                    int nextRemainder = remainder - (int)Math.pow(2, nextStartIndex - 1);
                    float candidate = TSPLoop(nextStartIndex, nextRemainder);
                    //String temp = from + " " + nextStartIndex;
                    float distanceIK = preComputedDistance.get(from + " " + nextStartIndex);
                    candidate += distanceIK;
                    if(distance == 0){
                        distance = candidate;
                    }else{
                        if(candidate < distance){
                            distance = candidate;
                        }
                    }
                }
            }
            memory[from][remainder] = distance;
            return distance;
        }
    }

}