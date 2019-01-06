import java.util.*;
public class sjf2 {
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
        System.out.println("SJF: " + output);
        Main.save_to_output(output);
    }
    // Constructor. Build a queue with all of the processes from the list.
    public sjf2(){
        this.total_time = 0;
        this.current_time = 0;
    }
    public void run(){
        System.out.println("\nStarting SJF");
        Process p;
        ArrayList<Process> plist = new ArrayList<>();
        ArrayList<Process> arrived = new ArrayList<>();

        for (Process process : Main.processList){
            plist.add(process);
        }
        // The arrival time of the first process will always be the first time it runs.
        this.current_time = plist.get(0).arrivalTime;


        // For each process in plist, if it has arrived, then set it to true.
        int count = 0;
        while (count < Main.processList.length){
            // For each process in plist, if it has arrived, and not in visited, then change flag and append.
            for (Process process : plist){
                // If process has arrived, then append to visited.
                if (process.arrivalTime <= this.current_time && process.arrived == false){
                    process.arrived = true;
                    process.waiting = true;
                    arrived.add(process);
                }
            }
            arrived.sort(Compare);
            if(arrived.size() > 0){
                p = arrived.remove(0);
                if (this.current_time < p.arrivalTime){
                    this.current_time = p.arrivalTime;
                }
                p.startingTime = this.current_time;
                p.waitTime = this.current_time - p.arrivalTime;
//                System.out.println("pid: " + (p.pid+1) + " waits: " + p.waitTime + " runs: " + p.runTime + " -> total time = " + (p.waitTime + p.runTime));
                p.totalTime = p.waitTime + p.runTime;
                p.finishTime = this.current_time + p.runTime;
                this.current_time = p.finishTime;
                this.total_time = p.finishTime;
                p.waiting = false;
                p.done = true;
                this.running_times[p.pid] = p.totalTime;

            }
            for (Process process : arrived){
                if (process.done){
                    plist.remove(process);
                }
            }
            if(count < Main.processList.length - 1){
                if (Main.processList[count+1].arrivalTime >= this.current_time){
                    this.current_time = Main.processList[count+1].arrivalTime;
                }
            }
            count += 1;
        }
            this.print_to_output();
    }
}