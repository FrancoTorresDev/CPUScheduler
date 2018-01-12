package sample.Comparators;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Models.Process;

import java.util.Comparator;

public class PriorityComp implements Comparator<Process> {
    //Use to sort Priority Queue
    @Override
    public int compare(Process o1, Process o2) {
        if( o1.getPriority() > o2.getPriority()){
            return 1;
        } else {
            return -1;
        }
    }
}
