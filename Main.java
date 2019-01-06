import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Main{
    public static BufferedReader br;
    public static PrintWriter pw;
    public static File outputFile;
    public static List<Pair<Integer, Integer>> processes = new ArrayList<>();
    public static Process[] processList;
    public void setupOutput(){
        try{
            String filename = "outputs/40532889.txt";
            outputFile = new File(filename);
            this.pw = new PrintWriter(outputFile);

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void closeOutput(){
        this.pw.close();
    }
    public static void save_to_output(String text){
        pw.append(text);
        pw.println();
        pw.flush();
    }
    public void open_and_parse_input(){
        try{
            this.br = new BufferedReader(new FileReader("inputs/input.txt"));
            String line;
            String [] temp;
            while ((line = this.br.readLine()) != null){
                temp = line.split(" ");
                for(int i = 0; i < temp.length; i += 2){
                    this.processes.add(new Pair<>(Integer.parseInt(temp[i]), Integer.parseInt(temp[i+1])));
                }
            }

        }
        catch(Exception e){
            e.printStackTrace();
        }

        finally{
            try{
                if (this.br != null){
                    this.br.close();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args){
        Main main = new Main();
        main.open_and_parse_input();
        try{
//            for (int i = 0; i < main.processes.size(); i++){
//                String temp = (main.processes.get(i).getKey() + ", " + main.processes.get(i).getValue().toString());
//                main.save_to_output(temp);
//            }
            main.setupOutput();
            main.processList = new Process[main.processes.size()];
            for (int i = 0; i < main.processes.size(); i++){
                main.processList[i] = new Process(main.processes.get(i).getKey(),  main.processes.get(i).getValue(), i);
            }

            for (int i = 0; i < main.processList.length; i++){
//                System.out.printf("start: %d, run: %d\n", main.processList[i].getArrivalTime(), main.processList[i].getRunTime());
            }
            FIFO fifo = new FIFO();
            fifo.run();

            sjf2 sjf = new sjf2();
            sjf.run();

            SRT srt = new SRT();
            srt.run();




        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally{
            main.closeOutput();
        }
    }
}