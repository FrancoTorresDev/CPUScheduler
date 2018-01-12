package sample.Models;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
public class Process {

    private int burstTime;
    private String name;
    private int priority = 0;
    private int arrivalTime;
    private int initialArrivalTime;
    //verify if response time have being calculated per Process
    private boolean checkResponseTime = false;

    public Process(int burstTime, String name, int priority, int arrivalTime) {
        this.burstTime = burstTime;
        this.name = name;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.initialArrivalTime = arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setCheckResponseTime(boolean checkResponseTime) {
        this.checkResponseTime = checkResponseTime;
    }

    public boolean isCheckResponseTime() {
        return checkResponseTime;
    }

    public int getInitialArrivalTime(){
        return initialArrivalTime;
    }

}
