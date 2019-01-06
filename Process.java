public class Process {
    int arrivalTime;
    int runTime;
    int startingTime;
    int finishTime;
    int totalTime;
    int waitTime;
    int time;
    int pid;
    int sjf;
    int rtime;
    boolean arrived;
    boolean waiting;
    boolean running;
    boolean done;

    // Constructor, only set arrival and running time.
    public Process(int a, int r, int p){
        this.arrivalTime = a;
        this.runTime = r;
        this.startingTime = 0;
        this.finishTime = 0;
        this.totalTime = 0;
        this.waitTime = 0;
        this.rtime = 0;
        this.time = a;
        this.arrived = false;
        this.waiting = false;
        this.running = false;
        this.done = false;

        this.sjf = 0;
        this.pid = p;
    }
    public void reset(){
        this.startingTime = 0;
        this.finishTime = 0;
        this.rtime = 0;
        this.totalTime = 0;
        this.waitTime = 0;
        this.time = this.runTime;
        this.arrived = false;
        this.waiting = false;
        this.running = false;
        this.done = false;

        this.sjf = 0;
    }

    // ***********************************
    // Functions to get values from the Process object.
    // ***********************************

    public int getArrivalTime(){
        return this.arrivalTime;
    }
    public int getRunTime(){
        return  this.runTime;
    }
    public int getStartingTime(){
        return this.startingTime;
    }
    public int getFinishTime(){
        return this.finishTime;
    }
    public int getTotalTime(){
        return this.totalTime;
    }
    public int getWaitTime(){
        return this.waitTime;
    }
    public boolean getWaiting(){
        return this.waiting;
    }
    public boolean getRunning(){
        return this.running;
    }

    // ***********************************
    // Functions to set values for the Process objects.
    // ***********************************

    public void setStartingTime(int st){
        this.startingTime = st;
    }
    public void setFinishTime(int ft){
        this.finishTime = ft;
    }
    public void setTotalTime(int tt){
        this.totalTime = tt;
    }
    public void setWaitTime(int wt){
        this.waitTime = wt;
    }
    public void setWaiting(boolean w){
        this.waiting = w;
    }
    public void setRunning(boolean r){
        this.running = r;
    }
    public void setDone(boolean d){
        this.done = d;
    }
}
