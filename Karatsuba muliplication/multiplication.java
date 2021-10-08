
/**
 * Write a description of multiplication here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.math.*;
public class multiplication {
    private BigInteger a;
    private BigInteger b;
    private BigInteger c;
    private BigInteger d;
    private BigInteger x;
    private BigInteger y;
    
    public multiplication(BigInteger itemA, BigInteger itemB){
        int n = itemA.toString().length();
        System.out.println(n);//64
        a = new BigInteger(itemA.toString().substring(0,n/2));
        b = new BigInteger(itemA.toString().substring(n/2,n));
        c = new BigInteger(itemB.toString().substring(0,n/2));
        d = new BigInteger(itemB.toString().substring(n/2,n));
       //System.out.println(String.valueOf(Math.pow(10,n/2)));//1.0E32 ???
        BigInteger temp = new BigInteger("10");
        temp = temp.pow(n/2);
        x = a.multiply(temp).add(b);
        y = c.multiply(temp).add(d);
        multiply(x,y);
    }
    
    private void multiply(BigInteger x,BigInteger y){
        BigInteger xy = x.multiply(y);
        //System.out.println ("a is "+ a + "\n" + "b is "+ b + "\n" + "c is " + c + "\n" + "d is " + d );
        System.out.println (xy.toString());
        
    }
    
}

