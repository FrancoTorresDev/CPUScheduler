package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Comparators.BurstimeComp;
import sample.Inheritance.NonPreemtiveScheduler;

import java.util.PriorityQueue;

public class NonPreemtiveSJFScheduler extends NonPreemtiveScheduler {
    public NonPreemtiveSJFScheduler() {
        readyQueue = new PriorityQueue<>(new BurstimeComp());
    }
}
