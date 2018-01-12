package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Comparators.BurstimeComp;
import sample.Inheritance.PreemtiveScheduler;

import java.util.PriorityQueue;

public class PreemtiveSJFScheduler extends PreemtiveScheduler {
    public PreemtiveSJFScheduler() {
        readyQueue = new PriorityQueue<>(new BurstimeComp());
    }
}
