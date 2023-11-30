import java.util.*;

class Process{
    int proId,arrival,brust,finish,trt,wt,remaingBrust,priority;
    Process(int proId,int arrival,int brust,int remaingBrust,int priority){
        this.proId = proId;
        this.arrival = arrival;
        this.brust = brust;
        this.remaingBrust = brust;
        this.priority = priority;
    }
}
public class Prorit {

    public static void proNonPreemtive(Process pro[]){
        Arrays.sort(pro,(p1,p2)->{
            return Integer.compare(p1.priority,p2.priority);
        });
        int curr=0;
        for(Process i:pro){
            i.finish = Math.max(curr,i.arrival) + i.brust;
            i.trt = i.finish - i.arrival;
            i.wt = i.trt - i.brust;
            curr = i.finish;
        }
    }
    public static void proPreemptive(Process pro[]){
        PriorityQueue<Process> que = new PriorityQueue<>((p1,p2)->p1.priority-p2.priority);
        int comP=0,currT=0;
        while(comP < pro.length){
            for(Process i:pro){
                if (i.arrival <= currT && i.remaingBrust>0) {
                    que.add(i);
                }
            }
            Process currP = que.poll();
            if(currP != null){
                currT++;
                currP.remaingBrust--;
                if(currP.remaingBrust==0){
                    currP.finish = currT;
                    currP.trt = currP.finish - currP.arrival;
                    currP.wt = currP.trt - currP.brust;
                    comP++;
                }
            }else{
                currT++;
            }
        }
    }
    public static void main(String[] args) {
        int n=4;
        Scanner sc = new Scanner(System.in);
        Process[] pro = new Process[4];
        for(int i=0;i<n;i++){
            System.out.println("enter the data for p"+(i+1));
            int arrival = sc.nextInt();
            int brust = sc.nextInt();
            int prio = sc.nextInt();
            pro[i] = new Process(i+1,arrival,brust,brust,prio);
        }
        // sjfNonPreemtive(pro);
        proPreemptive(pro);
        Arrays.sort(pro,(p1,p2)->{
                return Integer.compare(p1.proId,p2.proId);
        });
        System.out.println("Id\tat\tbt\tfinsh\ttat\twt");
        for(Process i:pro){
            System.out.println(i.proId+"\t"+i.arrival+"\t"+i.brust+"\t"+i.finish+"\t"+i.trt+"\t"+i.wt);
        }
    }
}
