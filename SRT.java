import java.util.*;
public class SRT {
    int total_time;
    int current_time;
    int[] running_times = new int[Main.processList.length];
    double average_time = 0.00;

    // Comparator
    Comparator<Process> Compare = new Comparator<Process>() {
        @Override
        public int compare(Process p1, Process p2) {
//            // Sorts by BurstTime
            if (p1.runTime == p2.runTime){
                if (p1.arrivalTime < p2.arrivalTime){
                    return -1;
                }
                else{
                    return 1;
                }
            }
            else if (p1.time < p2.time){
                return -1;
            }
            else if (p1.time > p2.time){
                return 1;
            }
            else if (p1.runTime < p2.runTime) {
                return -1;
            }
            else{
                return 1;
            }
        }
    };
    public void calculate_average_time(){
        double avg_time = this.average_time;
        for (int i = 0; i < this.running_times.length; i++){
            avg_time += this.running_times[i];
        }
        this.average_time = avg_time/this.running_times.length;
    }
    public void print_to_output(){
        String output = "";
        this.calculate_average_time();
        output = output + String.format("%.2f", this.average_time);
        for (int i = 0; i < this.running_times.length; i++){
            output = output + " " + Integer.toString(this.running_times[i]);
        }
        System.out.println("SRT: " + output);
        Main.save_to_output(output);
    }

    public SRT() {
        this.total_time = 0;
        this.current_time = 0;
    }
    public void run(){
        System.out.println("\nStarting SRT");
        Process p;
        ArrayList<Process> plist = new ArrayList<>();
        ArrayList<Process> arrived = new ArrayList<>();

        for (Process process : Main.processList){
            plist.add(process);
            this.total_time += process.runTime;
            process.reset();
        }

        this.current_time = plist.get(0).arrivalTime;
        int count = 0;


        for(int i = 0; i < this.total_time; i++){
            for (Process process : plist){
                // If process has arrived, then append to visited.
                if (process.arrivalTime <= this.current_time && process.arrived == false){
                    process.arrived = true;
                    process.waiting = true;
                    arrived.add(process);
                }
            }
            arrived.sort(Compare);

            if (arrived.size() > 0){
                p = arrived.get(0);
                p.time -= 1;
//                System.out.println(p.time);
                if (this.current_time < p.arrivalTime){
                    this.current_time = p.arrivalTime;
                }
//                if (p.pid == 4){
//                    System.out.println(this.current_time);
//                }
                p.startingTime = this.current_time;
                p.rtime += 1;
                p.finishTime = this.current_time;
                p.waitTime = p.finishTime-p.runTime - p.arrivalTime;

                p.totalTime = this.current_time - p.arrivalTime;
//                System.out.println("pid: " + (p.pid+1) + " wait: " + p.waitTime + " time: " + p.time);

                this.current_time += 1;
//                if (p.pid == 4){
//                    System.out.println(this.current_time);
//                    System.out.println("-----");
//                }



                if (p.time == 0){
//                    System.out.println("pid: " + (p.pid+1) + " curr: " + this.current_time + " - arrival: " + p.arrivalTime + " rtime : "+ p.rtime);
                    this.running_times[p.pid] = p.finishTime - p.arrivalTime + 1;
                    p.waiting = false;
                    p.done = true;

                    arrived.remove(p);
                    if(count < Main.processList.length - 1){
                        if (Main.processList[count+1].arrivalTime >= this.current_time){
                            this.current_time = Main.processList[count+1].arrivalTime;
                        }
                    }
                    count += 1;
                }
            }

            for (Process process : arrived){
                if (process.done){
                    plist.remove(process);
                }
            }

        }
        this.print_to_output();
    }

}
