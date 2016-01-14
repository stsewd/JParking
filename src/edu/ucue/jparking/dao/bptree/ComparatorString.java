package edu.ucue.jparking.dao.bptree;

import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Santos Gallegos
 */
public class ComparatorString implements Comparator<String>, Serializable {

    @Override
    public int compare(String o1, String o2) {
        return o1.compareToIgnoreCase(o2);
    }   
}
