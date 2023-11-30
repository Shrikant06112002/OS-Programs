import java.util.*;
public class Optimal_disc {
    public static int replace(HashSet<Integer> set,List<Integer> list){
        int pageReplace = -1 , pageI = -1;
        for(int i:set){
            int tempI = list.indexOf(i);
            if(tempI == -1) return i;
            if(tempI>pageI){
                pageI = tempI;
                pageReplace = i;
            }

        }
        return pageReplace;
    }
    public static void main(String[] args) {
        int incom[] = {1,2,3,4,1,2,5,1,2,3,4,5};
        int frameS = 3,pageFault=0;
        HashSet<Integer> set = new HashSet<Integer>();
        ArrayList<Integer> al = new ArrayList<>();
        for(int i:incom){
            al.add(i);
        } 
        for (int i = 0; i< al.size(); i++) {
            if (set.size()<frameS){ {
                if(!set.contains(al.get(i)))
                    set.add(al.get(i));
                    pageFault++;
                }
            }else{
                if (!set.contains(al.get(i))) {
                    int repval = replace(set, al.subList(i+1, al.size()));
                    set.remove(repval);
                    set.add(al.get(i));
                    pageFault++;
                }
            }
            System.out.println(set.toString());
        }
        System.out.println(pageFault);
    }
}
