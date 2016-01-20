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
import java.util.List;

/**
 *
 * @author Santos Gallegos
 */
public class BPTreeMap<K, V> implements Serializable {
    private final File PATH; // Ruta donde se almacenará la tabla de valores.
    private final int OBJ_SIZE; // Tamaño max reservado para cada objeto.
    private final int EXTRA_BYTES = 4; // Bytes extras que contienen el tamaño del objeto.
    private final int KEYS_NUMBER;
    private static final int NODO_SIZE = 1500; // Tamaño predefinido para cada nodo.
    private BPTree<K> tree; // Árbol B+, tabla de índices.
    
    private List<BPTree> secTreeIndex; // Lista de árboles que contienen indices secundarios.
    private List<IndexGenerator<V, Object>> indexGenerators; // Lista de generadores de indices secundarios.
    
    private BPTreeMap(int keysNumber, Comparator comparator, String dataPath, String treePath, int objSize, int nodeSize)
            throws IOException, FileNotFoundException, ObjectSizeException
    {
        KEYS_NUMBER = keysNumber;
        tree = BPTree.getTree(keysNumber, comparator, treePath, nodeSize);
        PATH = new File(dataPath);
        OBJ_SIZE = objSize;
        
        secTreeIndex = new ArrayList<>();
        indexGenerators = new ArrayList<>();
    }
    
    /**
     * Recupera un árbol existente de la ruta dada, si no existe
     * se retorna un nuevo árbol con los parámetros establecidos.
     * @param keysNumber
     * @param comparator
     * @param dataPath
     * @param treePath
     * @param objSize
     * @return 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.bptree.ObjectSizeException 
     */
    public static BPTreeMap getTree(int keysNumber, Comparator comparator, String dataPath, String treePath, int objSize)
            throws FileNotFoundException, IOException, ClassNotFoundException, ObjectSizeException
    {
        return new BPTreeMap(keysNumber, comparator, dataPath, treePath, objSize, NODO_SIZE);
    }
    
    /*
    necesito:
    - Generador de indices
    - ruta del nuevo arbol de indices
    - Almacenar en memoria arbol creado a partir de ruta y generador.
    */
    public void addSecIndex(String treePath, IndexGenerator indexGenerator)
            throws IOException, FileNotFoundException, ObjectSizeException
    {
        secTreeIndex.add(BPTree.getTree(KEYS_NUMBER, indexGenerator.getComparator(), treePath, NODO_SIZE));
        indexGenerators.add(indexGenerator);
    }
    
    /**
     * Retorna el número de elementos que contiene
     * el árbol.
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public int size() throws IOException, FileNotFoundException, ClassNotFoundException {
        return tree.values().size();
    }
    
    /**
     * Retorna true si el árbol está vacío.
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public boolean isEmpty() throws IOException, FileNotFoundException, ClassNotFoundException {
        return size() == 0;
    }
    
    /**
     * Retorna true si la clave key se encuentre
     * dentro del árbol.
     * @param key
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public boolean containsKey(K key) throws IOException, FileNotFoundException, ClassNotFoundException {
        return tree.search(key) != null;
    }
    
    /**
     * Agrega un elemento V, y lo ordena de acuerdo a su
     * clave key.
     * @param key
     * @param value 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public void put(K key, V value) throws FileNotFoundException, IOException, ClassNotFoundException, ObjectSizeException {
        RandomAccessFile ram = null;
        byte[] obj;
        byte[] rest;
        long pos = 0;
        
        try {
            ram = new RandomAccessFile(PATH, "rw");
            
            obj = serialize(value);
            
            if(obj.length > OBJ_SIZE)
                throw new ObjectSizeException(PATH);
            
            pos = ram.length();
            ram.seek(pos);
            ram.writeInt(obj.length);
            ram.write(obj);
            
            // Llenar de bytes
            rest = new byte[OBJ_SIZE - obj.length + EXTRA_BYTES];
            ram.write(rest);
        } finally {
            ram.close();
        }
        
        tree.add(key, pos);
        
        // Agregar claves en arboles secundarios.
        for(int i = 0; i < secTreeIndex.size(); i++){
            IndexGenerator ig = indexGenerators.get(i);
            secTreeIndex.get(i).add(ig.getKey(value), pos);
        }
    }
    
    /**
     * Retorna una colección con todos los elementos
     * almacenados en el árbol.
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public Collection<V> values() throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList values = new ArrayList();
        for(Long pos : tree.values()){
            values.add(getObject(pos));
        }
        return values;
    }
    
    /**
     * Retorna una coleccion con todos los elementos
     * ordenados de acuerdo al arbol secundario especificado.
     * @param indexSecTree
     * @return
     * @throws IOException
     * @throws FileNotFoundException
     * @throws ClassNotFoundException 
     */
    public Collection<V> valuesOf(int indexSecTree) throws IOException, FileNotFoundException, ClassNotFoundException {
        ArrayList values = new ArrayList();
        BPTree<Object> t = secTreeIndex.get(indexSecTree);
        for(Long pos : t.values()){
            values.add(getObject(pos));
        }
        return values;
    }

    /**
     * Retorna un objeto dado su clave,
     * retorna null si este no existe.
     * @param key
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    public V get(K key) throws IOException, FileNotFoundException, ClassNotFoundException {
        Long pos = tree.search(key);
        if(pos == null)
            return null;
        return getObject(pos);
    }
    
    /**
     * Retorna la posicion donde se encuentra el objeto.
     * @param key
     * @return 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     */
    private Long getPos(K key) throws IOException, FileNotFoundException, ClassNotFoundException{
        return tree.search(key);
    }

    /**
     * Elimina un elemento del árbol dada su clave
     * (sólo se elimina de la tabla de índices, no
     * de la tabla de valores).
     * @param key 
     * @throws java.io.IOException 
     * @throws java.io.FileNotFoundException 
     * @throws java.lang.ClassNotFoundException 
     * @throws edu.ucue.bptree.ObjectSizeException 
     */
    public void remove(K key) throws IOException, FileNotFoundException, ClassNotFoundException, ObjectSizeException {
        V obj = getObject(tree.search(key));
        tree.del(key);
        // Borrar claves en arboles secundarios.
        for(int i = 0; i < secTreeIndex.size(); i++){
            IndexGenerator ig = indexGenerators.get(i);
            secTreeIndex.get(i).del(ig.getKey(obj));
        }
    }
    
    /**
     * Obtiene un objeto dada su posicion dentro del archivo de
     * acceso aleatorio.
     * @param pos
     * @return 
     */
    private V getObject(long pos) throws FileNotFoundException, IOException, ClassNotFoundException {
        RandomAccessFile raf = null;
        byte[] objByte;
        V obj = null;
        try {
            raf = new RandomAccessFile(PATH, "rw");
            
            raf.seek(pos);
            objByte = new byte[raf.readInt()];
            raf.read(objByte);
            obj = (V) deserialize(objByte);
        } finally {
            raf.close();
        }
        
        return obj;
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
    
    /**
     * Actualiza un objeto de la tabla de valores.
     * @param key
     * @param newValue
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException
     * @throws ObjectSizeException 
     */
    public void update(K key, V newValue) throws FileNotFoundException, IOException, ClassNotFoundException, ObjectSizeException {
        RandomAccessFile raf = null;
        byte[] obj;
        byte[] rest;
        long pos = 0;
        
        try {
            raf = new RandomAccessFile(PATH, "rw");
            
            obj = serialize(newValue);
            
            if(obj.length > OBJ_SIZE)
                throw new ObjectSizeException(PATH);
            
            pos = getPos(key);
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
    
    public void showAll() throws IOException, FileNotFoundException, ClassNotFoundException{
        tree.showAll();
    }
}
