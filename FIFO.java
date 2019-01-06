import java.util.*;


public class FIFO {
    Queue<Process> pq;
    int total_time;
    int current_time;
    int[] running_times = new int[Main.processList.length];
    double average_time = 0.00;

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
        System.out.println("FIFO: " + output);
        Main.save_to_output(output);

    }
    // Constructor. Build a queue with all of the processes from the list.
    public FIFO(){
        this.total_time = 0;
        this.current_time = 0;
        this.pq = new LinkedList<>();
        for (int i = 0; i < Main.processList.length; i++){
            pq.add(Main.processList[i]);
        }
//        System.out.println(pq.size());

    }
    public void run(){
        System.out.println("\nStarting FIFO");
        Process p;
        int count = 0;
        while((p = pq.poll()) != null){
            if(count == 0){     // If we are in the first process, then the starting time = arrival time.
                this.current_time = p.arrivalTime;
            }
            if (this.current_time < p.arrivalTime){
                this.current_time = p.arrivalTime;
            }
            p.startingTime = this.current_time;
            p.waitTime = this.current_time - p.arrivalTime;
            p.finishTime = this.current_time + p.runTime;
            p.totalTime = p.waitTime + p.runTime;
            this.current_time = p.finishTime;
            p.done = true;
            this.running_times[count] = p.totalTime;
            this.total_time = p.finishTime;

            count += 1;
        }



        this.print_to_output();
    }
}
