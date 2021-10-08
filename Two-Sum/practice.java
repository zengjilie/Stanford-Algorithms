public class practice {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        //int lowerBound1 = 1;
        //int upperBound1 = 5;
        countNum1 cn1 = new countNum1();
        cn1.start();

        //int lowerBound2 = 6;
        //int upperBound2 = 10;
        countNum2 cn2 = new countNum2();
        cn2.start();

        long endTime = System.nanoTime();
        long duration = endTime - startTime;
        
        System.out.println(duration); 
    }
}

class countNum1 extends Thread{
    //int lowerBound1;
    //int upperBound1;
    public int sum;
    // public countNum1(int lowerBound1, int upperBound1){
    //     this.lowerBound1 = lowerBound1;
    //     this.upperBound1 = upperBound1;
    //     sum = 0;
    // }

    public void run(){
        for(int i = 1; i <= 500; i++){
            sum += i;
        }
        System.out.println(sum);
    } 
}

class countNum2 extends Thread{
    // int lowerBound2;
    // int upperBound2;
    public int sum;
    // public countNum2(int lowerBound2, int upperBound2){
    //     this.lowerBound2 = lowerBound2;
    //     this.upperBound2 = upperBound2;
    //     sum = 0;
    // }

    public void run(){
        for(int i = 501; i <= 1000; i++){
            sum += i;
        }
        System.out.println(sum);

    } 
}