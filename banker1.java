

import java.util.*;

public class banker1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int pro;
        System.out.println("Enter the number of process"); 
        pro = sc.nextInt();
        int allo[][] = new int[pro][4];
        int max[][] = new int[pro][4];
        int avail[] = new int[4];
        int need[][] = new int[pro][4];
        boolean flag[] = new boolean[pro];
        int seq[] = new int[pro];
        Arrays.fill(flag,false);
        System.out.println("Enter the Allocation for the process");
        for (int i = 0;i<pro;i++){
            System.out.println("P"+i);
            for (int j = 0;j<4;j++){
                allo[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the Max for the process");
        for (int i = 0;i<pro;i++){
            System.out.println("P"+i);
            for (int j = 0;j<4;j++){
                max[i][j] = sc.nextInt();
            }
        }
        System.out.println("Enter the Available for the process");

            System.out.println("P0");
            for (int j = 0;j<4;j++){
                avail[j] = sc.nextInt();
            }
        // System.out.println("Enter the Need for the process");
        for (int i = 0;i<pro;i++){
            // System.out.println("P%d"+i);
            for (int j = 0;j<4;j++){
                need[i][j] = max[i][j]-allo[i][j];
            }
        }
        System.out.println("Allocation Matrix");
        for (int i = 0;i<pro;i++){
            for (int j = 0;j<4;j++){
                System.out.print(allo[i][j]+"\t");
            }
            System.out.println();
        }
        System.out.println("Max Matrix");
        for (int i = 0;i<pro;i++){
            for (int j = 0;j<4;j++){
                System.out.print(max[i][j]+"\t");
            }
            System.out.println();
        }        
        System.out.println("Need Matrix");
        for (int i = 0;i<pro;i++){
            for (int j = 0;j<4;j++){
                System.out.print(need[i][j]+"\t");
            }
            System.out.println();
        }
        int seqi=0;
    for (int k=0;k<pro;k++){
        for (int i = 0;i<pro;i++){
            if (!flag[i]) {
                boolean check1 = false;
                    for (int m=0;m<4;m++){
                        if(need[i][m]>avail[m]){
                            check1 =true;
                            break;
                        }
                    }
                if(!check1){
                    for(int l=0;l<4;l++) {
                        avail[l]+=allo[i][l];
                    }
                    seq[seqi++] = i;
                    flag[i] = true;
                }
            }
        }
    }
    boolean check=false;
    for (int i = 0; i < pro; i++) {
        if(!flag[i]){
            check=true;
        }
    }
    if (check) {
        System.out.println("safe squence not found");
    }else{
        System.out.println("the safe squence of process is");
        for (int i = 0; i < pro; i++) {
            System.out.print(seq[i]+"\t");
        }
    }
        

    }
}
