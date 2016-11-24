package edu.Frostburg.COSC310_Spellchecker;

import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Random;

/**
 * Abstract Hash Map class, used for implementation os Chain Hash Map
 * Class is based heavily from the textbook
 * @author aarondavis
 *
 * @param <K>
 * @param <V>
 */
public abstract class AbstractHashMap<K,V> extends AbstractMap<K,V> {
	protected int n = 0;
	protected int capacity;
	private int prime;
	private long scale, shift;
	
	public AbstractHashMap(int capacity, int prime) {
		this.prime = prime;
		this.capacity = capacity;
		
		Random ran = new Random(System.currentTimeMillis());
		scale = ran.nextInt(prime-1) + 1;
		shift = ran.nextInt(prime);
		createTable();
	}
	
	public AbstractHashMap(int capacity) {
		this(capacity, 109354121);
	}
	
	public AbstractHashMap(){
		this(50000);
	}
	
	@Override
	public int size() {
		return n;
	}

	@Override
	public V get(K key) {
		return bucketGet(hashValue(key), key);
	}

	@Override
	public V put(K key, V value) {
		V answer = bucketPut(hashValue(key), key, value);
		if(n > capacity / 2){
			resize(2 * capacity - 1);
		}
		
		return answer;
	}

	@Override
	public V remove(K key) {
		return bucketRemove(hashValue(key), key);
	}

	private int hashValue(K key){
		return (int)((Math.abs(key.hashCode() * scale + shift) % prime) % capacity);
	}
	
	private void resize(int newCapacity){
		ArrayList<Entry<K,V>> buffer = new ArrayList<>(n);
		for(Entry<K,V> e: entrySet()){
			buffer.add(e);
		}
		
		capacity = newCapacity;
		createTable();
		n = 0;
		
		for(Entry<K,V> e: buffer){
			put(e.getKey(), e.getValue());
		}
	}
	
	protected abstract void createTable();
	protected abstract V bucketGet(int h, K key);
	protected abstract V bucketPut(int h, K key, V value);
	protected abstract V bucketRemove(int h, K key);
}
