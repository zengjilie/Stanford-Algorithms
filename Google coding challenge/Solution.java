public class Solution {
    public static int solution(String x) {
        int output = 0;
        int partSize = 0;
    	if(x == null){
    	    return 0;
    	}else{
    	    for(int i = 1; i <= x.length(); i++){
    	        String cur = x.substring(0,i);
                System.out.println(cur);
    	        int matches = countMatches(x,cur);
    	        if( matches*cur.length() == x.length() && cur.length() > partSize && matches != 1){
    	            output = matches;
                    partSize = cur.length();
                    System.out.println("partSize is " + partSize);
    	        }
    	    }
    	}
        System.out.println(partSize);su
    	return output;
    	
    }
    
    public static int countMatches(String x, String cur){
        int index = 0, count = 0;
        System.out.println("index is "+ index);
        while(x.indexOf(cur,index) != -1){
            count ++;
            System.out.println("count is " + count);
            index = x.indexOf(cur,index) + cur.length();
        }
        return count;
    }

    public static void main(String[] args) {
        String x = "abccbaabccba" ;
        int matches = solution(x);
        System.out.println("matches are " + matches);
    }
}


