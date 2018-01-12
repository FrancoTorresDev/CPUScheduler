package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Comparators.PriorityComp;
import sample.Inheritance.PreemtiveScheduler;

import java.util.PriorityQueue;

public class PreemtivePriorityScheduler extends PreemtiveScheduler {
    public PreemtivePriorityScheduler() {
        readyQueue = new PriorityQueue<>(new PriorityComp());
    }
}
