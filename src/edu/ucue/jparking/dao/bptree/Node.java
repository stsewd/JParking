/**
 * Node / Nodo
 */
package edu.ucue.jparking.dao.bptree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

/**
 *
 * @author Santos Gallegos
 * @param <K> Clave
 * @param <V> Valor
 */
public final class Node<K, V> implements Serializable {
    private boolean leaf; // Hoja
    
    private int nodeSize; // Tamaño actual del nodo
    private Node parent;
    private Node next;
    private Node prev;
    
    private final K[] keys; // Claves
    private final Node[] children; // hijos
    private final V[] values; // Valores
    
    private final int keysNumber; // Número máximo de claves
    private final Comparator<K> comparator; // Comparador de claves

    public Node(boolean leaf, int keysNumber, Comparator<K> comparator) {
        this.leaf = leaf;
        this.nodeSize = 0;
        this.keysNumber = keysNumber;
        this.keys = (K[]) new Object[this.keysNumber + 1];
        this.comparator = comparator;
        if(leaf){
            this.values = (V[]) new Object[this.keysNumber + 1];
            this.children = null;
        }else {
            this.values = null;
            this.children = new Node[this.keysNumber + 2];
        }
    }
    
    /**
     * Retorna el siguiente nodo hoja (lista de claves).
     * @return 
     */
    public Node next(){
        return next;
    }
    
    public Node prev(){
        return prev;
    }
    
    public void setNext(Node node){
        this.next = node;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }

    public void setNodeSize(int nodeSize) {
        this.nodeSize = nodeSize;
    }
    
    public V getValue(int index){
        return values[index];
    }

    public void setValue(int index, V value){
        values[index] = value;
    }
    
    public K getKey(int index){
        return keys[index];
    }
    
    public void setKey(int index, K key){
        keys[index] = key;
    }
    
    public Node getChild(int index){
        return children[index];
    }
    
    public void setChild(int index, Node child){
        children[index] = child;
    }

    public int getNodeSize() {
        return nodeSize;
    }

    public boolean isLeaf() {
        return leaf;
    }

    public void setLeaf(boolean leaf) {
        this.leaf = leaf;
    }
   
    public void insert(K key, V value){
        int i = getNodeSize() - 1;
        while(i >= 0 && comparator.compare(key, getKey(i)) < 0){
            keys[i + 1] = keys[i];
            values[i + 1] = values[i];
            i--;
        }
        keys[i + 1] = key;
        values[i + 1] = value;
        nodeSize++;
    }
    
    public void insert(K key, Node child){
        int i = getNodeSize() - 1;
        while(i >= 0 && comparator.compare(key, getKey(i)) < 0){
            keys[i + 1] = keys[i];
            children[i + 2] = children[i + 1];
            children[i + 1] = children[i];
            i--;
        }
        keys[i + 1] = key;
        children[i + 2] = child;
        nodeSize++;
    }
    
    public void remove(K key){
        int i;
        for(i = 0; i < getNodeSize(); i++){
            if(keys[i] == key)
                break;
        }
        
        if(i >= getNodeSize())
            return;
        
        for(int j = i + 1; j < getNodeSize(); j++){
            keys[j - 1] = keys[j];
            if(leaf){
                values[j -1] = values[j];
            }else {
                children[j] = children[j + 1];
            }
        }
        nodeSize--;
    }

    /**
     * @return the parent
     */
    public Node getParent() {
        return parent;
    }

    /**
     * @param parent the parent to set
     */
    public void setParent(Node parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        String str = "";
        for(int i = 0; i < getNodeSize(); i++)
            str += keys[i] + " ";
        return str;
    }

    public Collection<V> values() {
        ArrayList<V> v = new ArrayList<>();
        for(int i = 0; i < nodeSize; i++)
            v.add(values[i]);
        return v;
    }
}
