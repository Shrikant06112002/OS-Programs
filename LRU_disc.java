import java.util.*;

public class LRU_disc {
    public static void main(String[] args) {
        int incom[] = {1,2,3,4,1,2,5,1,2,3,4,5};
        int frameS = 3,pageFault=0;
        HashSet<Integer> set = new HashSet<Integer>();
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < incom.length; i++) {
            if(set.size()<frameS) {
                if (!set.contains(incom[i])) {
                    set.add(incom[i]);
                    map.put(incom[i],i);
                    pageFault++;
                }
            }else{
                if(!set.contains(incom[i])) {
                    int minIn = Integer.MAX_VALUE, val = Integer.MIN_VALUE;
                    for (int j:set) {
                        int temp = j;
                        if (minIn>map.get(temp)) {
                            minIn = map.get(temp);
                            val = j;
                        }
                    }
                    set.remove(val);
                    set.add(incom[i]);
                    map.remove(val);
                    pageFault++;
                }
                map.put(incom[i],i);
            }
            System.out.println(set.toString());
            
        }
        System.out.println(pageFault);

    }
}
