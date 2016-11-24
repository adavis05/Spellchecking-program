package edu.Frostburg.COSC310_Spellchecker;

import java.util.ArrayList;
import java.util.Map.Entry;

/**
 * ChainHashMap - uses seperate chaining to solve collision problem
 * Class is based heavily upon books implementation
 * @author aarondavis
 *
 * @param <K>
 * @param <V>
 */
public class ChainHashMap<K,V> extends AbstractHashMap<K, V> {

	private UnsortedTableMap<K,V>[] table;
	
	/**
	 * Calling super class' constructor
	 */
	public ChainHashMap(){
		super();
	}
	
	/**
	 * Calling super class' constructor
	 * @param capacity
	 */
	public ChainHashMap(int capacity){
		super(capacity);
	}
	
	/**
	 * Calling super class' constructor
	 * @param capacity
	 * @param prime
	 */
	public ChainHashMap(int capacity, int prime){
		super(capacity, prime);
	}
	
	/**
	 * Creates array with capacity predefined in super class
	 */
	protected void createTable(){
		table = (UnsortedTableMap<K,V>[]) new UnsortedTableMap[capacity];
	}
	
	@Override
	/**
	 * Returns iterable collection of all key-value entries
	 */
	public Iterable<Entry<K, V>> entrySet() {
		ArrayList<Entry<K,V>> buffer = new ArrayList<>();
		for(int h = 0; h < capacity; h++){
			if(table[h] != null){
				for(Entry<K,V> entry: table[h].entrySet()){
					buffer.add(entry);
				}
			}
		}
		
		return buffer;
	}

	@Override
	/**
	 * Returns value associated with key k in buck with hash value h, or null
	 * @param h
	 * @param key
	 */
	protected V bucketGet(int h, K key) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null){
			return null;
		}
		
		return bucket.get(key);
	}

	@Override
	/**
	 * Associates key k with value v in bucket with hash value h, or else null
	 * @param h
	 * @param key
	 * @param value
	 */
	protected V bucketPut(int h, K key, V value) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null){
			bucket = table[h] = new UnsortedTableMap<>();
		}
		
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		n += (bucket.size() - oldSize);
		
		return answer;
		
	}

	@Override
	protected V bucketRemove(int h, K key) {
		UnsortedTableMap<K,V> bucket = table[h];
		if(bucket == null){
			return null;
		}
		
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		n -= (oldSize - bucket.size());
		
		return answer;
	}

}
