import java.io.*;
import java.util.Scanner;
public class test {

    public static void main(String[] args) {
        File file = new File("numbers.txt");
        Scanner scan = null;
        try{
            scan = new Scanner(file);
        }catch(Exception e){
            System.out.println("file not found");
        }
        
        System.out.println(scan.nextLine());
        
    }
}
