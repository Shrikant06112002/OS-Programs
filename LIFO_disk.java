import java.util.*;

public class LIFO_disk {
    public static void main(String[] args) {
        int incom[] = {1,2,3,4,1,2,5,1,2,3,4,5};
        int frameS = 3,pageFault=0;

        Queue<Integer> queue = new LinkedList<Integer>();
        for (int i = 0; i < incom.length; i++) {
            if(!queue.contains(incom[i])) {
                if(queue.size()==frameS){
                    int remove = queue.poll();
                    queue.add(incom[i]);
                }else{
                    queue.add(incom[i]);
                }
                pageFault++;
            }
            System.out.println(queue.toString());
        }
        System.out.println("the page faults is "+pageFault);
    }
}
