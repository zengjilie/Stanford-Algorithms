import java.util.HashMap;

public class TSP {
    private double[][] preComputedDistance;
    private HashMap<Integer, Integer> visitedCities;
    private int cityNum;
    public TSP(double[][] preComputedSidance, int cityNum){
        this.preComputedDistance = preComputedSidance;
        visitedCities = new HashMap<>();
        this.cityNum = cityNum;
    }

    public double computeDistance(int startIndex){
        double result = 0;
        visitedCities.put(startIndex, 0);
        double shortestDistance = (double)Integer.MAX_VALUE;
        int nextStartIndex = 0;
        
        while(visitedCities.size() != cityNum){
            for(int n = 1; n <= cityNum; n++){
                if(preComputedDistance[startIndex][n] < shortestDistance && visitedCities.containsKey(n) == false){
                    shortestDistance = preComputedDistance[startIndex][n];
                    nextStartIndex = n;
                }
            }
            result += Math.sqrt(shortestDistance);
            startIndex = nextStartIndex;
            visitedCities.put(startIndex, 0);
            shortestDistance = (double)Integer.MAX_VALUE;
        }
        result += Math.sqrt(preComputedDistance[startIndex][1]);


        return result;
    }
}
