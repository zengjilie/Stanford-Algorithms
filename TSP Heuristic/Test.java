import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

class Teset{
    public static void main(String[] args) {
        int cityNum = 0;
        HashMap<Integer, City> allCities = new HashMap<>();
        double[][] preComputedDistance;
        try {
            FileReader fr = new FileReader("Data.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            int n = 0;
            while((line = br.readLine()) != null){
                if(n == 0){
                    cityNum = Integer.parseInt(line);
                }else{
                    int a = 0;
                    String[] temp = line.split("\s", 0);
                    int index = Integer.parseInt(temp[0]);
                    double x = Double.parseDouble(temp[1]);
                    double y = Double.parseDouble(temp[2]);
                    City newCity = new City(index, x, y);
                    allCities.put(index, newCity);
                }
                n++;
                int a = 0;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        int a = 1;

        //precompute distance //  not square root > simplify computation
        preComputedDistance = new double[cityNum + 1][cityNum + 1];

        for(int n = 1; n <= cityNum; n++){
            for(int m = n + 1; m <= cityNum; m++){
                City cityN = allCities.get(n);
                City cityM = allCities.get(m);
                double distanceNM_X = (double)Math.pow((cityN.x - cityM.x), 2); 
                double distanceNM_Y = (double)Math.pow((cityN.y - cityM.y), 2);
                double distanceNM = distanceNM_X + distanceNM_Y;
                preComputedDistance[cityN.index][cityM.index] = distanceNM; 
                preComputedDistance[cityM.index][cityN.index] = distanceNM; 

            }
        }

        TSP tsp = new TSP(preComputedDistance, cityNum);
        double result = tsp.computeDistance(1);

        System.out.println(result);
        System.out.println((int)result);

    }
}