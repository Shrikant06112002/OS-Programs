import java.util.Scanner;

class Process{
    int proId,arrival,brust,finish,trt,wt;
    Process(int proId,int arrival,int brust){
        this.proId = proId;
        this.arrival = arrival;
        this.brust = brust;
    }
}
public class FCFS {
    public static void main(String[] args) {
        int n=4;
        Scanner sc = new Scanner(System.in);
        Process[] pro = new Process[4];
        for(int i=0;i<n;i++){
            System.out.println("enter the data for p"+i+1);
            int arrival = sc.nextInt();
            int brust = sc.nextInt();
            pro[i] = new Process(i+1,arrival,brust);
        }
        int currentTime = 0;
        for (Process i:pro){
            i.finish = Math.max(i.arrival, currentTime) + i.brust;
            i.trt = i.finish -i.arrival;
            i.wt = i.trt - i.brust;
            currentTime = i.finish;
        }
        System.out.println("Id\tat\tbt\tfinsh\ttat\twt");
        for(Process i:pro){
            System.out.println(i.proId+"\t"+i.arrival+"\t"+i.brust+"\t"+i.finish+"\t"+i.trt+"\t"+i.wt);
        }
    }
}
