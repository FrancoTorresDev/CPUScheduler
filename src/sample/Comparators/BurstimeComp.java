package sample.Comparators;
/* Franco Torres
 * Ian Benthien
 * Abdel Rivera*/
import sample.Models.Process;

import java.util.Comparator;

public class BurstimeComp implements Comparator<Process> {
    @Override
    //Use to sort Priority Queue
    public int compare(Process o1, Process o2) {
        if( o1.getBurstTime() > o2.getBurstTime()){
            return 1;
        } else {
            return -1;
        }
    }
}
