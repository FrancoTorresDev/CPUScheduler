package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Inheritance.PreemtiveScheduler;
import sample.Models.Process;
import java.util.LinkedList;
import java.util.Queue;

public class RoundRobin extends PreemtiveScheduler {

    private int quantum;
    public RoundRobin() {
        readyQueue = new LinkedList<>();
        quantum = 0;
    }

    @Override
    public void run(Queue<Process> processQueue) {

        complete = false;
        clock = 0;
        int currQuantum = 0;
        totalTurnAroundTime = 0;
        totalWaitingTime = 0;
        size = processQueue.size();

        while (!complete) {

            //if arrival time is same cpu clock is put into the ready queue if not return null
            nextToReady = processToReadyQueue(processQueue);
            if(nextToReady != null){
                readyQueue.add(nextToReady);
            }

                if (!readyQueue.isEmpty()) {

                    if (currQuantum < quantum && readyQueue.peek().getBurstTime() != 0) {
                        /*if the quantum hasnt completed and the current process burst time hasnt finished
                         * update burstime & quantum*/
                        readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() - 1);
                        currQuantum++;
                    } else {

                        if (readyQueue.peek().getBurstTime() != 0) {
                            /* if the process burst time is not but the quantum is complete a context switch
                            * will occur, we initialize the quantum to one to compensate the fact that two
                            * process didn't run concurrently within the same time unit*/
                            currQuantum = 1;
                            readyQueue.peek().setArrivalTime(clock);
                            /*since a context switch must occur but the process has not finished its burst time
                            the process must be reinserted into the readyQueue*/
                            readyQueue.add(readyQueue.poll());
                            //update burstTime
                            readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() -1);
                            if(!readyQueue.peek().isCheckResponseTime()){
                                totalResponseTime +=  clock - readyQueue.peek().getInitialArrivalTime();
                                readyQueue.peek().setCheckResponseTime(true);
                            }
                            totalWaitingTime += clock - readyQueue.peek().getArrivalTime();
                        } else {
                            /*When a process is completed, the quantum time must be reset.
                            * We verify that the response time hasn't been calculated, if not
                            * we calculate it*/
                            currQuantum = 0;
                            totalTurnAroundTime += clock - readyQueue.peek().getInitialArrivalTime();
                            readyQueue.peek().setArrivalTime(clock);
                            processInCpu = readyQueue.poll();
                            completed++;
                            if(!readyQueue.isEmpty()){

                                if(!readyQueue.peek().isCheckResponseTime()){
                                    totalResponseTime +=  clock - readyQueue.peek().getInitialArrivalTime();
                                    readyQueue.peek().setCheckResponseTime(true);
                                }
                                totalWaitingTime += clock - readyQueue.peek().getArrivalTime();
                                //update burstTime
                                readyQueue.peek().setBurstTime(readyQueue.peek().getBurstTime() -1);
                                currQuantum++;
                            }
                            //check if simulation has ended
                            complete = isComplete(processQueue, processInCpu);

                        }
                    }

                }
                throughputCalculator();
            clock++;
            }

    }

    public void setQuantum(int quantum) {
        this.quantum = quantum;
    }
}
