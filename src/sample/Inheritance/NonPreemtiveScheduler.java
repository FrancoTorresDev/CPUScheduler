package sample.Inheritance;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Models.Process;

import java.util.Queue;

public class NonPreemtiveScheduler extends Scheduler {

    @Override
    public void run(Queue<Process> processQueue) {
        clock = 0;
        totalWaitingTime=0;
        totalTurnAroundTime= 0;
        size = processQueue.size();
        //start execute the process until complete
        while (!complete){

            if(processInCpu != null){
                //update burstTime
                processInCpu.setBurstTime(processInCpu.getBurstTime() -1);
                // verify if process ended
                if(processInCpu.getBurstTime() == 0){
                    //if ends calculate totalTurnAroundTime
                    totalTurnAroundTime += clock  - processInCpu.getArrivalTime();
                    if(!readyQueue.isEmpty()){
                        //since the previous process ended, the next available process will be loaded
                        processInCpu = readyQueue.poll();
                        //counts how many processes have completed to calculate throughput within the time slice
                        completed++;
                        totalWaitingTime += clock - processInCpu.getArrivalTime();
                        totalResponseTime = totalWaitingTime;
                    }
                    //check if simulation has ended
                    complete = isComplete(processQueue, processInCpu);
                }
            }

            //if arrival time is same cpu clock is put into the ready queue if not return null
            nextToReady = processToReadyQueue(processQueue);
            if(nextToReady != null){
                readyQueue.add(nextToReady);
                //if processInCpu is empty loads the next available process
                if(processInCpu == null){
                    processInCpu = readyQueue.poll();
                }
            }
            throughputCalculator();
            clock++;
        }
    }
}
