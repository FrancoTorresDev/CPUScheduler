package sample.Inheritance;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Models.Process;

import java.util.Queue;

public class PreemtiveScheduler extends Scheduler {

    @Override
    public void run(Queue<Process> processQueue) {
        clock = 0;
        totalWaitingTime=0;
        totalTurnAroundTime= 0;
        size = processQueue.size();

        //start execute the process until complete
        while (!complete) {

            if(!readyQueue.isEmpty()){

                if(processInCpu != null){
                    /*Since in preemtive mode we cant know when a context switch occurs
                    * we veryfy if the previous process has a diferent name from the current one
                    * and calculates the response and and waiting time respectively*/
                    if(!readyQueue.peek().getName().equals(processInCpu.getName())){
                        if(!readyQueue.peek().isCheckResponseTime()){
                            //To calculate the correct numbers we compensate  for the fact that the change happened
                            // it was in the previous time unit by subtracting one time unit.
                            totalResponseTime +=  clock - 1 - readyQueue.peek().getInitialArrivalTime();
                            readyQueue.peek().setCheckResponseTime(true);
                        }
                        totalWaitingTime += clock - 1 - readyQueue.peek().getArrivalTime();
                        // If process the process in the front changed(It has a higher priority)
                        //It changes to the correct current process
                        processInCpu = readyQueue.peek();
                    }
                }
                //update burstTime
                readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() -1);
                readyQueue.peek().setArrivalTime(clock);

                if(readyQueue.peek().getBurstTime() == 0) {
                    //When process complete remove from ready queue
                    totalTurnAroundTime += clock - readyQueue.peek().getInitialArrivalTime();
                    processInCpu = readyQueue.poll();
                    //counts how many processes have completed to calculate throughput within the time slice
                    completed++;
                    //check if simulation has ended
                    complete = isComplete(processQueue,processInCpu);
                }

            }

            //if arrival time is same cpu clock is put into the ready queue if not return null
            nextToReady = processToReadyQueue(processQueue);
            if(nextToReady != null){
                readyQueue.add(nextToReady);
                //if processInCpu is empty loads the next available process
                if(processInCpu == null){
                    processInCpu = readyQueue.peek();
                }
            }
            throughputCalculator();
            clock++;
        }



    }
}
