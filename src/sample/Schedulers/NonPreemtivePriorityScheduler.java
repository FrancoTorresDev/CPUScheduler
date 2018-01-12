package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Comparators.PriorityComp;
import sample.Inheritance.NonPreemtiveScheduler;
import java.util.PriorityQueue;

public class NonPreemtivePriorityScheduler extends NonPreemtiveScheduler {
    public NonPreemtivePriorityScheduler() {
        readyQueue = new PriorityQueue<>(new PriorityComp());
    }
}
