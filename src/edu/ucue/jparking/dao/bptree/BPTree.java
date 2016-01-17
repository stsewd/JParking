/**
 * B+ Tree / Árbol B+
 */
package edu.ucue.jparking.dao.bptree;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.RandomAccessFile;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Objects;

/**
 * Árbol B+.
 * @author Santos Gallegos
 * @param <K> Clave
 */
public class BPTree<K> implements Serializable {
    private Long root; // Nodo raíz
    private final int keysNum; // Máximo de claves u órden de árbol
    private final int minKeysNum; // Mínimo de claves que puede tener un nodo (excepto root)
    private final Comparator<K> comparator; // Comparador de claves
    private final File PATH; // Ruta donde se almacenara la tabla de indices
    private final int OBJ_SIZE; // Tamaño max reservado para cada nodo.
    private final int EXTRA_BYTES = 4; // Bytes extras que contienen el tamaño del nodo.
    
    /**
     * Crea un nuevo árbol vacío.
     * @param keysNumber Número máximo de claves.
     * @param comparator Objeto necesario para comparar las claves.
     * @param path
     * @param objSize
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    private BPTree(int keysNumber, Comparator<K> comparator, String path, int objSize, Long root)
            throws FileNotFoundException, IOException
    {
        
        this.keysNum = keysNumber;
        this.comparator = comparator;
        this.minKeysNum = (int) Math.ceil(keysNum/2.0);
        this.OBJ_SIZE = objSize;
        this.PATH = new File(path);
        
        setRoot(root);
    }
    
    public static BPTree getTree(int keysNumber, Comparator comparator, String path, int objSize)
            throws FileNotFoundException, IOException, ObjectSizeException
    {
        BPTree tree = null;
        
        File treePath = new File(path);
        if(!treePath.exists()){
            tree = new BPTree(keysNumber, comparator, path, objSize, 8L);
            tree.saveNode(new Node(true, keysNumber, comparator));
            return tree;
        }
        
        RandomAccessFile raf = null;
        
        try{
            raf = new RandomAccessFile(treePath, "rw");
            raf.seek(0);
            Long root = raf.readLong();
            tree = new BPTree(keysNumber, comparator, path, objSize, root);
        } finally {
            raf.close();
        }

        return tree;
    }
        

    private void setRoot(Long root) throws FileNotFoundException, IOException {
        RandomAccessFile raf = null;
        try {
            raf = new RandomAccessFile(PATH, "rw");
            raf.seek(0);
            raf.writeLong(root);
        } finally {
            raf.close();
        }
        this.root = root;
    }
    
    /**
     * Busca un valor por su clave, si no hay una coincidencia
     * retorna null.
     * @param key
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Long search(K key) throws IOException, FileNotFoundException, ClassNotFoundException{
        return search(key, root);
    }
    
    private Long search(K key, Long nodePos) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        Node node = getNode(nodePos);
        
        if(node.isLeaf()){
            for(int i = 0; i < node.getNodeSize(); i++){
                if(comparator.compare(key, (K) node.getKey(i)) == 0)
                    return node.getValue(i);
            }
        }else {
            int i = 0;
            while(i < node.getNodeSize() && comparator.compare(key, (K) node.getKey(i)) >= 0)
                i++;
            return search(key, node.getChild(i));
        }
        return null;
    }
    
    /**
     * Retorna la hoja en donde debería ser insertada
     * una clave, o donde podría estar esta.
     * @param key
     * @return 
     */
    private Long searchLeaf(K key) throws IOException, FileNotFoundException, ClassNotFoundException{
        
        Node leaf = getNode(root);
        int i;
        while(!leaf.isLeaf()){
            i = leaf.getNodeSize() - 1;
            while(i >= 0 && comparator.compare(key, (K) leaf.getKey(i)) < 0)
                i--;
            leaf = getNode(leaf.getChild(i + 1));
        }
        return leaf.getPos();
    }
    
    /**
     * Agrega un valor, con su respectiva clave
     * al árbol, manteniendo todos sus elementos
     * ordenados por clave.
     * @param key
     * @param value 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.bptree.ObjectSizeException 
     */  
    public void add(K key, Long value)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException 
    {
        // Buscar en qué hoja pertenece la clave a insertar
        Node leaf = getNode(searchLeaf(key));
        
        leaf.insertValue(key, value);
        
        updateNode(leaf);
        
        if(leaf.getNodeSize() <= keysNum)
            return;
       
        Node newLeaf = new Node(true, keysNum, comparator);
        saveNode(newLeaf);
        
        // Dividir claves/valores del nodo hoja
        int k = 0;
        int j = 0;
        for(j = minKeysNum; j < keysNum + 1; j++){
            newLeaf.setKey(k, leaf.getKey(j));
            newLeaf.setValue(k, leaf.getValue(j));
            k++;
        }

        leaf.setNodeSize(minKeysNum);
        newLeaf.setNodeSize(keysNum + 1 - minKeysNum);
        
        updateNode(leaf);
        updateNode(newLeaf);
        
        // Si el padre del nodo es nulo, la hoja es root
        if(leaf.getParent() == null){
            
            Node newRoot = new Node(false, keysNum, comparator);
            
            newRoot.setKey(0, (K) newLeaf.getKey(0));
            newRoot.setNodeSize(1);
            
            saveNode(newRoot);
            
            setRoot(newRoot.getPos());
            
            newLeaf.setNext(null);
            newLeaf.setPrev(leaf.getPos());
            leaf.setNext(newLeaf.getPos());
            leaf.setPrev(null);

            leaf.setParent(newRoot.getPos());
            newLeaf.setParent(newRoot.getPos());
            
            updateNode(leaf);
            updateNode(newLeaf);
            
            newRoot.setChild(0, leaf.getPos());
            newRoot.setChild(1, newLeaf.getPos());
            
            updateNode(newRoot);
            
        }else {
            
            newLeaf.setNext(leaf.next());
            if(leaf.next() != null){
                Node leafNext = getNode(leaf.next()); 
                leafNext.setPrev(newLeaf.getPos());
                updateNode(leafNext);
            }
            newLeaf.setPrev(leaf.getPos());
            leaf.setNext(newLeaf.getPos());
            
            updateNode(leaf);
            updateNode(newLeaf);
            
            
            Node parent = getNode(leaf.getParent());
            newLeaf.setParent(parent.getPos());
            
            updateNode(newLeaf);

            parent.insertChild(newLeaf.getKey(0), newLeaf.getPos());

            updateNode(parent);
            
            if(parent.getNodeSize() > keysNum)
                split(parent.getPos());
        }
    }
    
    /**
     * Divide un nodo de manera recursiva hasta que todos
     * los nodos tengan el numero de claves adecuado.
     * @param node 
     */
    private void split(Long nodePos) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        Node node = getNode(nodePos);
        
        Node newNode = new Node(false, keysNum, comparator);
        saveNode(newNode);
        
        // Dividir claves/valores del nodo interno
        int k = 0;
        int j = 0;
        for(j = minKeysNum + 1; j < keysNum + 1; j++){
            
            Node child = getNode(node.getChild(j));
            child.setParent(newNode.getPos());
            updateNode(child);
            
            newNode.setKey(k, node.getKey(j));
            newNode.setChild(k, child.getPos());
            updateNode(newNode);

            k++;
        }
        Node child = getNode(node.getChild(j));
        child.setParent(newNode.getPos());
        updateNode(child);
        
        newNode.setChild(k, child.getPos());
        updateNode(newNode);
        
        node.setNodeSize(minKeysNum);
        newNode.setNodeSize(keysNum - minKeysNum);
        
        newNode.setNext(node.next());
        if(node.next() != null){
            Node nextNode = getNode(node.getPos());
            nextNode.setPrev(newNode.getPos());
            updateNode(nextNode);
        }
        newNode.setPrev(node.getPos());
        node.setNext(newNode.getPos());
        
        updateNode(node);
        updateNode(newNode);
        
        // Si el nodo es raiz
        if(node.getParent() == null){
            Node newRoot = new Node(false, keysNum, comparator);
            
            newRoot.setKey(0, (K) node.getKey(minKeysNum));
            newRoot.setNodeSize(1);
            
            saveNode(newRoot);
            
            setRoot(newRoot.getPos());
            
            node.setParent(newRoot.getPos());
            newNode.setParent(newRoot.getPos());
            
            updateNode(node);
            updateNode(newNode);
            
            newRoot.setChild(0, node.getPos());
            newRoot.setChild(1, newNode.getPos());
            
            updateNode(newRoot);

        }else {
            
            Node parent = getNode(node.getParent());
            newNode.setParent(parent.getPos());
            
            updateNode(newNode);
            
            parent.insertChild(node.getKey(minKeysNum), newNode.getPos());
            
            updateNode(parent);
            
            if(parent.getNodeSize() > keysNum)
                split(parent.getPos());
        }
    }
    
    public void del(K key) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException{
        delLeaf(key, root);
    }
    
    private void delLeaf(K key, Long nodePos)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {        
        Node leaf = getNode(nodePos);
        
        int i = 0;
        // Comprobar si la clave existe
        if(search(key) == null)
            return;

        // Buscar la hoja donde pertenece la clave a eliminar
        while(!leaf.isLeaf()){
            i = leaf.getNodeSize() - 1;
            while(i >= 0 && comparator.compare(key, (K) leaf.getKey(i)) < 0)
                i--;
            leaf = getNode(leaf.getChild(i + 1));
        }
        
        Node parent = getNode(leaf.getParent());
        
        // Eliminar clave.
        leaf.remove(key);
        updateNode(leaf);
        
        parent.setKey(i, leaf.getKey(0));
        updateNode(parent);
        
        // Si es raiz o cumple con el minimo de valores. Salir.
        if(leaf.getNodeSize() >= minKeysNum || (Objects.equals(leaf.getPos(), root)))
            return;
        
        // Pedir prestado de hermano izquierdo
        Node leftNode = getNode(leaf.prev());
        if(leftNode != null && Objects.equals(leftNode.getParent(), leaf.getParent()) && leftNode.getNodeSize() > minKeysNum){
            int last = leftNode.getNodeSize() - 1;
            leaf.insertValue(leftNode.getKey(last), leftNode.getValue(last));
            updateNode(leaf);

            leftNode.setNodeSize(leftNode.getNodeSize() - 1);
            updateNode(leftNode);

            parent.setKey(i, leaf.getKey(0));
            updateNode(parent);
            return;
        }
        
        // Pedir prestado de hermano derecho
        Node rightNode = getNode(leaf.next());
        if(rightNode != null && Objects.equals(rightNode.getParent(), leaf.getParent()) && rightNode.getNodeSize() > minKeysNum){
            int first = 0;
            leaf.insertValue(rightNode.getKey(first), rightNode.getValue(first));
            updateNode(leaf);

            rightNode.remove(rightNode.getKey(first));
            updateNode(rightNode);

            parent.setKey(i, rightNode.getKey(first));
            updateNode(parent);

            return;
        }
        
        // Merge con vecino izq
        if(leftNode != null && leftNode.getNodeSize() == minKeysNum){
            for(int j = 0; j < leaf.getNodeSize(); j++)
                leftNode.insertValue(leaf.getKey(j), leaf.getValue(j));
            updateNode(leftNode);

            Node nextNode = getNode(leaf.next());

            if(nextNode != null){
                leftNode.setNext(nextNode.getPos());
                nextNode.setPrev(leftNode.getPos());

                updateNode(nextNode);
                updateNode(leftNode);
            }else {
                leftNode.setNext(null);
                updateNode(leftNode);
            }

            delNode((K) parent.getKey(i), parent.getPos());

            return;
        }
        
        // Merge con vecino derecho
        if(rightNode != null && rightNode.getNodeSize() == minKeysNum){
            for(int j = 0; j < leaf.getNodeSize(); j++)
                leaf.insertValue(rightNode.getKey(j), rightNode.getValue(j));
            updateNode(leaf);
            
            Node nextNode = getNode(rightNode.next());
            
            if(nextNode != null){
                leaf.setNext(nextNode.getPos());
                nextNode.setPrev(leaf.getPos());
                
                updateNode(leaf);
                updateNode(nextNode);
            }else {
                leaf.setNext(null);
                updateNode(leaf);
            }
            
            delNode((K) parent.getKey(i), parent.getPos());
        }
    }
    
    private void delNode(K key, Long nodePos)
            throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException
    {
        Node node = getNode(nodePos);
        Node parent = getNode(node.getParent());

        int i = 0;
        
        // Buscar posicion de hijo en padre.
        i = 0;
        for(i = 1; i < parent.getNodeSize() + 1; i++){
            if(Objects.equals(parent.getChild(i), node.getPos()))
                break;
        }
        
        // Eliminar clave.
        node.remove(key);
        
        updateNode(node);
        
        // Nueva raiz.
        if(node.getNodeSize() == 0){
            Node child = getNode(node.getChild(0));
            child.setParent(null);
            updateNode(child);
            
            setRoot(child.getPos());
            return;
        }
        
        // Si es raiz o cumple con el minimo de valores. Salir.
        if(node.getNodeSize() >= minKeysNum || (Objects.equals(node.getPos(), root)))
            return;

        // Pedir prestado de hermano izquierdo
        Node leftNode = getNode(node.prev());
        if(leftNode != null && Objects.equals(leftNode.getParent(), node.getParent()) && leftNode.getNodeSize() > minKeysNum){
            int last = leftNode.getNodeSize() - 1;
                
            // Correr 1 posicion las claves/valores
            int k = node.getNodeSize() - 1;
            while(k >= 0) {
                node.setKey(k + 1, node.getKey(k));
                node.setChild(k + 2, node.getChild(k + 1));
                node.setChild(k + 1, node.getChild(k));
                k--;
            }
            // Insertar nuevo valor
            Node child = getNode(leftNode.getChild(last + 1));
            node.setKey(0, parent.getKey(i - 1));
            node.setChild(0, child.getPos());
            node.setNodeSize(node.getNodeSize() + 1);
            updateNode(node);

            child.setParent(node.getPos());
            updateNode(child);

            leftNode.setNodeSize(leftNode.getNodeSize() - 1);  
            updateNode(leftNode);

            // Actualizar padre
            parent.setKey(i - 1, node.getKey(0));
            updateNode(parent);
            return;
        }
        
        // Pedir prestado de hermano derecho
        Node rightNode = getNode(node.next());
        if(rightNode != null && Objects.equals(rightNode.getParent(), node.getParent()) && rightNode.getNodeSize() > minKeysNum){
            int first = 0;
            /*
            - Buscar nodo "generador" desde el padre del nodo.
            - Bajar esa clave a la última + 1 posicion.
            - Tomar hijo del nodo vecino.
            - Borrar primer hijo de nodo vecino y primera clave.
            - Poner nueva clave en nodo generador del padre.
                ejemplo:
                      u
                s t      w x y
               1 2 3    4 5 6 7

                Borrando t (se fusionan 2 y 3)
                Caso actual:
                    u
                s       w x y
               1 23    4 5 6 7
                Balanceando arbol
                      u 
                s  u     w x y
               1 23 4   4 5 6 7

                     u 
                s  u    x y
               1 23 4  5 6 7

                      x 
                s  u    x y
               1 23 4  5 6 7

                Listo!
            */

            // Insertar nuevo valor
            Node child = getNode(rightNode.getChild(0));
            node.setKey(node.getNodeSize(), parent.getKey(i + 1));
            node.setChild(node.getNodeSize() + 1, child.getPos());
            node.setNodeSize(node.getNodeSize() + 1);
            updateNode(node);

            child.setParent(node.getPos());
            updateNode(child);

            // Correr 1 posicion las claves/valores
            int k = 0;
            while(k < rightNode.getNodeSize()) {
                rightNode.setKey(k, rightNode.getKey(k + 1));
                rightNode.setChild(k, rightNode.getChild(k + 1));
                k++;
            }
            
            rightNode.setNodeSize(rightNode.getNodeSize() - 1);  
            updateNode(rightNode);

            // Actualizar padre
            parent.setKey(i + 1, rightNode.getKey(0));
            updateNode(parent);

            return;
        }
        
        // Merge con vecino izq
        if(leftNode != null && Objects.equals(leftNode.getParent(), node.getParent()) && leftNode.getNodeSize() == minKeysNum){
            /*
            - Buscar posicion de hijo en padre
            - Insertar i-ésima key de padre en vecino izq.
            - Vecino izq absorbe nodo.
            - Reparar prev y next de los nodos.
            - Eliminar i-ésima key del padre
            - Si al eliminar key no queda nada. Hacer nueva root a hijo
            */
            
            leftNode.setKey(leftNode.getNodeSize(), parent.getKey(i -1));
            leftNode.setChild(leftNode.getNodeSize() + 1, node.getChild(0));
            
            int k = leftNode.getNodeSize() + 1;
            for(int j = 0; j < node.getNodeSize(); j++){
                leftNode.setKey(k, node.getKey(j));
                leftNode.setChild(k + 1, node.getChild(j + 1));
                k++;
            }
            leftNode.setNodeSize(keysNum);
            
            updateNode(leftNode);
            
            leftNode.setNext(node.next());
            if(node.next() != null){
                Node leafNext = getNode(node.next()); 
                leafNext.setPrev(leftNode.getPos());
                updateNode(leafNext);
            }
            updateNode(leftNode);
            
            delNode((K) parent.getKey(i - 1), parent.getPos());
            
            return;
        }
        
        // Merge con vecino derecho
        if(rightNode != null && Objects.equals(rightNode.getParent(), node.getParent()) && rightNode.getNodeSize() == minKeysNum){

            node.setKey(node.getNodeSize(), parent.getKey(i + 1));
            node.setChild(node.getNodeSize() + 1, rightNode.getChild(0));
            
            int k = node.getNodeSize() + 1;
            for(int j = 0; j < rightNode.getNodeSize(); j++){
                node.setKey(k, rightNode.getKey(j));
                node.setChild(k + 1, rightNode.getChild(j + 1));
                k++;
            }
            node.setNodeSize(keysNum);
            
            updateNode(node);
            
            node.setNext(rightNode.next());
            if(rightNode.next() != null){
                Node leafNext = getNode(rightNode.next()); 
                leafNext.setPrev(node.getPos());
                updateNode(leafNext);
            }
            updateNode(node);
            
            delNode((K) parent.getKey(i + 1), parent.getPos());
        }
        
    }
    
    /**
     * 
     * @return 
     */
    public Long getRoot() {
        return root;
    }

    /**
     * @return the comparator
     */
    public Comparator<K> getComparator() {
        return comparator;
    }
    

    @Override
    public String toString() {
        try {
            return toString(root);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
    
    /**
     * Retorna el árbol recorrido en preorden.
     * @param node
     * @param index
     * @return 
     */
    private String toString(Long nodePos) throws IOException, FileNotFoundException, ClassNotFoundException{
        Node node = getNode(nodePos);
        String str = "";
        str += node.toString();
        
        if(node.getParent() != null)
            str += " padre: (" + node.getParent().toString() + ")";
        
        str += "\n";
        
        if(node.isLeaf())
            return str;
        
        for(int i = 0; i < node.getNodeSize() + 1; i++)
            str += toString(node.getChild(i));
        
        return str;
    }
    
    public void showAll() throws IOException, FileNotFoundException, ClassNotFoundException{
        // Buscar primera hoja
        Node leaf = getNode(root);
        while(!leaf.isLeaf())
            leaf = getNode(leaf.getChild(0));
        
        while(leaf != null){
            System.out.print(leaf);
            leaf = getNode(leaf.next());
        }
    }
    
    public Collection<Long> values() throws IOException, FileNotFoundException, ClassNotFoundException{
        ArrayList<Long> values = new ArrayList();
        
        // Buscar primera hoja.
        Node leaf = getNode(root);
        while(!leaf.isLeaf())
            leaf = getNode(leaf.getChild(0));
        
        // Recorrer todos los elementos del arbol de forma ordenada.
        while(leaf != null){
            values.addAll(leaf.values());
            leaf = getNode(leaf.next());
        }
        return values;
    }
    
    /**
     * Retorna una cadena de bytes que representan la serializacion del objeto.
     * @param obj
     * @return
     * @throws IOException 
     */
    private byte[] serialize(Object obj) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(out);
        os.writeObject(obj);
        return out.toByteArray();
    }
    
    /**
     * Dada una cadena de bytes, retorna el objeto que representa.
     * @param data
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private Object deserialize(byte[] data) throws IOException, ClassNotFoundException {
        ByteArrayInputStream in = new ByteArrayInputStream(data);
        ObjectInputStream is = new ObjectInputStream(in);
        return is.readObject();
    }
    
    private Long saveNode(Node node) throws IOException, ObjectSizeException{
        byte[] obj;
        byte[] rest;
        long pos = 0;
        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(PATH, "rw");
            pos = raf.length();
            
            node.setPos(pos);
            
            obj = serialize(node);
            
            if(obj.length > OBJ_SIZE)
                throw new ObjectSizeException();
            
            raf.seek(pos);
            raf.writeInt(obj.length);
            raf.write(obj);
            
            // Llenar de bytes
            rest = new byte[OBJ_SIZE - obj.length + EXTRA_BYTES];
            raf.write(rest);
        } finally {
            raf.close();
        }
        return pos;
    }
    
    private void updateNode(Node newNode, Long pos) throws FileNotFoundException, IOException, ObjectSizeException{
        byte[] obj;
        byte[] rest;
        RandomAccessFile raf = null;
        try{
            raf = new RandomAccessFile(PATH, "rw");
            newNode.setPos(pos);
            obj = serialize(newNode);
            
            if(obj.length > OBJ_SIZE)
                throw new ObjectSizeException();
                
            raf.seek(pos);
            raf.writeInt(obj.length);
            raf.write(obj);
            
            // Llenar de bytes
            rest = new byte[OBJ_SIZE - obj.length + EXTRA_BYTES];
            raf.write(rest);
            
        } finally {
            raf.close();
        }
    }
    
    private void updateNode(Node node) throws IOException, FileNotFoundException, ObjectSizeException{
        updateNode(node, node.getPos());
    }
    
    private Node getNode(Long pos) throws FileNotFoundException, IOException, ClassNotFoundException {
        if(pos == null)
            return null;
        RandomAccessFile raf = null;
        byte[] objByte;
        Node obj = null;
        try {
            raf = new RandomAccessFile(PATH, "rw");
            
            raf.seek(pos);
            objByte = new byte[raf.readInt()];
            raf.read(objByte);
            
            obj = (Node) deserialize(objByte);
        } finally {
            raf.close();
        }
        return obj;
    }
}
