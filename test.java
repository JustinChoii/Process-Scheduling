import java.util.Comparator;
import java.util.*;

public class test {

    public static void main(String[] args){
        Comparator<Process> Compare2 = new Comparator<Process>() {
            @Override
            public int compare(Process p1, Process p2) {
                // Sorts by BurstTime
                if (p1.runTime == p2.runTime){
                    if (p1.arrivalTime < p2.arrivalTime){
                        return 1;
                    }
                    else{
                        return -1;
                    }
                }

            if (p1.runTime < p2.runTime) {
                    return -1;
                } else {
                    return 1;
                }



            }
        };
        PriorityQueue<Process> p = new PriorityQueue<>(Compare2);
        p.add(new Process(0, 2, 0));
        p.add(new Process(1, 2, 1));
        p.add(new Process(0, 4, 2));
        p.add(new Process(3, 4, 3));
        Process x;
        while((x = p.poll()) != null){
            System.out.println(x.pid);
        }

    }
}
