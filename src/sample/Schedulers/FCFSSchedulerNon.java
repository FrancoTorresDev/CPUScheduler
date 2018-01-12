package sample.Schedulers;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Inheritance.NonPreemtiveScheduler;
import java.util.LinkedList;

public class FCFSSchedulerNon extends NonPreemtiveScheduler {
    public FCFSSchedulerNon() {
        readyQueue = new LinkedList<>();
    }
}
