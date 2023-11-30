import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class matrix {
    final static int MAX = 4;
     
    // matrix A used for muliplication
    static int[][] matA = { { 3, 7, 3, 6 }, { 9, 2, 0, 3 }, { 0, 2, 1, 7 }, { 2, 2, 7, 9 } };
     
    // matrix B used for multiplication
    static int[][] matB = { { 6, 5, 5, 2 }, { 1, 7, 9, 6 }, { 6, 6, 8, 9 }, { 0, 3, 5, 2 } };
     
    // matAdd and matSub to store results
    static int[][] matAdd = new int[MAX][MAX];
    static int[][] matSub = new int[MAX][MAX];
 
    // Function to print matrix in readable format
    static void printMatrix(int[][] mat) {
        for (int i = 0; i < MAX; i++) {
            for (int j = 0; j < MAX; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static void add(int i){
        int start = i * (MAX/4);
        int end = (i+1) * (MAX/4);
        for (int j = start; j < end; j++) {
            for (int j2 = 0; j2 < 4; j2++) {
                matAdd[j][j2] = matA[j][j2] + matB[j][j2];
            }
        }
    }
    public static void main(String[] args) {
        ExecutorService excutor = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 4; i++) {
            int finalI =i ;
            excutor.submit(()->{
                add(finalI);
            });
        }
        excutor.shutdown();
        while(!excutor.isTerminated()){

        }
        printMatrix(matAdd);
    }
}
