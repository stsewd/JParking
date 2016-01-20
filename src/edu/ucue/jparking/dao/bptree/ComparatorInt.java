package edu.ucue.jparking.dao.bptree;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Santos Gallegos
 */
public class ComparatorInt implements Comparator<Integer>, Serializable {

    @Override
    public int compare(Integer o1, Integer o2) {
        return o1.compareTo(o2);
    }
}
