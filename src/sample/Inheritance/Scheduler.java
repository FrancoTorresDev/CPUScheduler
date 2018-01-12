package sample.Inheritance;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Models.Process;
import java.util.Queue;

public class Scheduler {
    private float averageWaitingTime = 0;
    private float averageTurnAroundTime = 0;
    private float averageResponseTime = 0;
    protected int clock = 0;
    protected boolean complete = false;
    protected  float totalWaitingTime = 0;
    protected float totalTurnAroundTime = 0;
    protected float totalResponseTime = 0;
    protected float size = 0;
    protected Process nextToReady = null;
    protected  Process processInCpu = null;
    protected static Queue<Process> readyQueue;
    protected float completed = 0;
    private String throughputPerTimeUnitPrint = "";
    private String separator = ",";
    private float timeSlice = 3;
    private String throughputPerTimeUnit = "";

    //set the time interval to evaluate the throughput
    public void setTimeSlice(float timeSlice) {
        this.timeSlice = timeSlice;
    }
    //calculate AverageWaitingTime, AverageTurnAroundTime & AverageResponseTime
    private void calculateStatistics(){
        averageResponseTime = totalResponseTime/size;
        averageWaitingTime = totalWaitingTime/size;
        averageTurnAroundTime = totalTurnAroundTime/size;
    }

    protected  void throughputCalculator(){
        if(clock%timeSlice == 0 ){
            throughputPerTimeUnit += System.lineSeparator() + "Period: "+ clock +" Completed: " + completed + " ";
            throughputPerTimeUnitPrint += System.lineSeparator() + separator + clock + separator + completed;
            completed = 0;
        }
    }
    //verify is simulation is complete
    protected boolean isComplete(Queue<Process> processQueue, Process processInCpu){
        if(processQueue.isEmpty() && readyQueue.isEmpty()&& processInCpu.getBurstTime() == 0){
            calculateStatistics();
            return true;
        }
        return false;
    }
    //determine if the process arrivalTime is the same of the clock which means is ready to be added to the readyQueue
    protected Process processToReadyQueue(Queue<Process> processQueue){
        if (!processQueue.isEmpty()){
            if (processQueue.peek().getArrivalTime() == clock) {
                //if arrival time is same cpu clock is put into the ready queue
               return processQueue.poll();

            }else{
                return null;
            }
        }
        else
            return null;
    }
    //give results to the Controller
    public String getData(){
        String data = "AverageWaitingTime: " + averageWaitingTime + System.lineSeparator() +
                "AverageTurnAround: " + averageTurnAroundTime + System.lineSeparator() +
                "AverageResponseTime:" + averageResponseTime + System.lineSeparator() +
                throughputPerTimeUnit;
        return data;
    }
    //give result in .csv  file to the Controller
    public String print(){
        String data = "AverageWaitingTime:," + averageWaitingTime + separator +
                "AverageTurnAround:," + averageTurnAroundTime + separator +
                "AverageResponseTime:," + averageResponseTime + separator +
                throughputPerTimeUnitPrint;
        return data;
    }
    //send process list to the sheduler to be processed
    public void run(Queue<Process> processQueue){

    }

}
