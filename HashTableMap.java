import java.util.LinkedList;
import java.util.NoSuchElementException;

// --== CS400 File Header Information ==--
// Name: Guilhem Ane
// Email: gane@wisc.edu
// Team: Blue
// Group: JD
// TA: Xinyi
// Lecturer: Florian Heimerl
// Notes to Grader: Uses the KeyValuePair<> class.

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType>{

    private LinkedList<KeyValuePair<KeyType, ValueType>>[] table;

    public HashTableMap(){
	table = new LinkedList[10];
    }

    public HashTableMap(int capacity){
	table = new LinkedList[capacity];
    }

    public boolean put(KeyType key, ValueType value){
	if(containsKey(key) || key == null)
	    return false;
        
	int hashIndex = key.hashCode() % table.length;
	if(table[hashIndex] == null)
	    table[hashIndex] = new LinkedList<KeyValuePair<KeyType, ValueType>>();

	KeyValuePair newKVP = new KeyValuePair<KeyType, ValueType>(key, value);
	table[hashIndex].add(newKVP);
	
	if((float) size()/(float)table.length >= 0.85f){
	    doubleSizeAndRehash();
	}
	
	return true;
    }

    public ValueType get(KeyType key) throws NoSuchElementException{
	int hashCode = key.hashCode() % table.length;
	if(table[hashCode] == null)
	    throw new NoSuchElementException();
	else{
	    for(KeyValuePair<KeyType, ValueType> kvp : table[hashCode]){
		if(kvp.getKey().equals(key))
		    return kvp.getValue();
	    }
	}
	throw new NoSuchElementException();
    }

    public int size(){
	int finalSize = 0;
	
	for(int i=0; i<table.length; i++){
	    if(table[i] != null){
	        finalSize += table[i].size();
	    }
	}
	return finalSize;
    }

    
    public boolean containsKey(KeyType key){
	int hashIndex = key.hashCode() % table.length;

	if(table[hashIndex] != null){
	    for(KeyValuePair<KeyType, ValueType> kvp : table[hashIndex]){
		if(kvp.getKey().equals(key))
		    return true;
	    }
	}
	return false;
    }

    public ValueType remove(KeyType key){
	int hashCode = key.hashCode() % table.length;
        for(KeyValuePair<KeyType, ValueType> kvp : table[hashCode]){
	    if(kvp.getKey().equals(key)){
		table[hashCode].remove(kvp);
		return kvp.getValue();
	    }
	}
	return null;
    }

    public void clear(){
	table = new LinkedList[table.length];
    }

    public int getCapacity(){
	return table.length;
    }

    private void doubleSizeAndRehash(){
	LinkedList<KeyValuePair<KeyType, ValueType>>[] oldTable = table;
        table = new LinkedList[table.length * 2];

	for(int i=0; i<oldTable.length; i++){
	    if(oldTable[i] != null){
		for(KeyValuePair<KeyType, ValueType> kvp : oldTable[i]){
		    put(kvp.getKey(), kvp.getValue());
		}
	    }
	}
    }
}
