import java.util.Arrays;

public class QuickSort{
    public int comparisons;
    public int[] sortedArray;

    public QuickSort(int[] arr, int Start, int End){ 
        // System.out.println(Start );
        // System.out.println(End);
        comparisons = 0;
        sortArray(arr,Start,End); 
        sortedArray = arr;     
        
    }

    public void sortArray(int[] arr, int S, int E){
        int m = E-S+1;
        if(m == 1){
            arr[S] = arr[S];    
        }
        else if(m > 1){
            comparisons = comparisons + (m-1);
            //take the fisrt as pivot 
            int P = arr[S];
            int i = S+1;
            int j;
            for (j = i; j < E+1; j++){
                if(arr[j] < P){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                    i++;
                }
            }
            arr[S] = arr[i-1];
            arr[i-1] = P;
            sortArray(arr, S, i-2);
            sortArray(arr, i ,E);

            //take the final as pivot 
            // int temp1 = arr[S];
            // arr[S] = arr[E];
            // arr[E] = temp1;
            // int P = arr[S];
            // int i = S+1;
            // int j;
            // for (j = i; j < E+1; j++){
            //     if(arr[j] < P){
            //         int temp2 = arr[j];
            //         arr[j] = arr[i];
            //         arr[i] = temp2;
            //         i++;
            //     }
            // }
            // arr[S] = arr[i-1];
            // arr[i-1] = P;
            // sortArray(arr, S, i-2);
            // sortArray(arr, i,E);

            //pivot is the meidian of three, pp(pivot picker), map pp to ppClone to find the original index;
            // int[] pp = new int[3];
            // int[] ppClone;
            // int pIndex = 0;
            // if(m%2 == 1){
            //     pp[0] = arr[S];
            //     pp[1] = arr[(S+E)/2];
            //     pp[2] = arr[E];
            //     ppClone = pp.clone();
            //     int p = pp[0];
            //     for(int a=0; a<3; a++){
            //         for(int b = a+1; b<3; b++){
            //             if(pp[a]<pp[b]){
            //                 int temp = pp[a];
            //                 pp[a] = pp[b];
            //                 pp[b] = temp;
            //             }
            //         }
            //     }
            //     int P = pp[1];
            //     if(P == ppClone[0]){
            //         pIndex = S;
            //     }else if(P == ppClone[1]){
            //         pIndex = (S+E)/2;
            //     }else if(P == ppClone[2]){
            //         pIndex = E;
            //     }
                
            // }else if(m%2 != 1){
            //     pp[0] = arr[S];
            //     pp[1] = arr[(S+E-1)/2];
            //     pp[2] = arr[E];
            //     ppClone = pp.clone();
            //     int p = pp[0];
            //     for(int a=0; a<3; a++){
            //         for(int b = a+1; b<3; b++){
            //             if(pp[a]<pp[b]){
            //                 int temp1 = pp[a];
            //                 pp[a] = pp[b];
            //                 pp[b] = temp1;
            //             }
            //         }
            //     }
            //     int P = pp[1];
            //     if(P == ppClone[0]){
            //         pIndex = S;
            //     }else if(P == ppClone[1]){
            //         pIndex = (S+E-1)/2;
            //     }else if(P == ppClone[2]){
            //         pIndex = E;
            //     }
            // } 
            // int temp2 = arr[S];
            // arr[S] = arr[pIndex];
            // arr[pIndex] = temp2;
            
            // int P = arr[S];
            // int i = S+1;
            // int j;
            // for (j = i; j < E+1; j++){
            //     if(arr[j] < P){
            //         int temp3 = arr[j];
            //         arr[j] = arr[i];
            //         arr[i] = temp3;
            //         i++;
            //     }
            // }
            // arr[S] = arr[i-1];
            // arr[i-1] = P;
            // sortArray(arr, S, i-2);
            // sortArray(arr, i ,E);
            
            
            

            
            
        }
    }



}