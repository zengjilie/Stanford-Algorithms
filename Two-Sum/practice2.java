public class practice2 {
    public static void main(String[] args) {
        long start = System.nanoTime();
        int lowerBound = 1;
        int upperBound = 1000;
        countSum cs = new countSum(lowerBound, upperBound);
        int sum = cs.count();

        long end = System.nanoTime();
        long duration = end - start;
        System.out.println(sum);
        System.out.println(duration);
    }

    
}

class countSum{
    int lowerBound;
    int upperBound;
    public countSum(int lowerBound, int upperBound){
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }
    public int count(){
        int sum = 0;
        for(int i = lowerBound; i <= upperBound; i++){
            sum += i;
        }
        return sum;
    }
}