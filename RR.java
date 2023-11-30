import java.util.*;

class Process{
    int id,at,bt,ft,tat,wt,remBt;
    Process(int id,int at,int bt,int remBt){
        this.id = id;
        this.at = at;
        this.bt = bt;
        this.remBt = remBt;
    }
}
public class RR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of process");
        int n = sc.nextInt();
        System.out.println("Enter the Time Quntum");
        int tc = sc.nextInt();

        Queue<Process> que = new LinkedList<>();
        System.out.println("Enter the details (ArrivalTime BrustTime)");
        for(int i=0;i<n;i++){
            System.out.println("p"+(i+1));
            int at = sc.nextInt();
            int bt = sc.nextInt();
            que.add(new Process(i+1, at, bt, bt));
        }
        Queue<Process> que2 = new LinkedList<>(que);
        roundRobin(que, tc);
        display(que2);

    }
    public static void display(Queue<Process> pro){
        System.out.println("id\tat\tbt\tft\ttat\twt");
        for(Process i:pro){
            System.out.println(i.id+"\t"+i.at+"\t"+i.bt+"\t"+i.ft+"\t"+i.tat+"\t"+i.wt);
        }
    }
    public static void roundRobin(Queue<Process> pro,int tc){
        int currT =0;
        while(!pro.isEmpty()){
            Process currP = pro.poll();
            int excuTime = Math.min(currP.remBt,tc);
            currP.remBt-=excuTime;
            currT+=excuTime;
            if(currP.remBt>0){
                pro.add(currP);
            }else{
                currP.ft = currT;
                currP.tat = currP.ft - currP.at;
                currP.wt = currP.tat - currP.bt;
            }
        }
    }
}
