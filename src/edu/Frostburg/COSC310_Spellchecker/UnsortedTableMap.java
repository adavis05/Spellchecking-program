package edu.Frostburg.COSC310_Spellchecker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Map.Entry;

public class UnsortedTableMap<K, V> extends AbstractMap<K, V> {
	private ArrayList<MapEntry<K,V>> table = new ArrayList<>();
	
	public UnsortedTableMap() {}
	
	private int locateIndex(K key){
		int size = table.size();
		for(int i = 0; i < size; i++){
			if(table.get(i).getKey().equals(key)){
				return i;
			}
		}
		return -1;
	}
	
	@Override
	public int size() {
		return table.size();
	}

	@Override
	public V get(K key) {
		int found = locateIndex(key);
		if(found == -1){
			return null;
		}
		
		return table.get(found).getValue();
	}

	//@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) {
		int found = locateIndex(key);
		if(found == -1){
			table.add(new MapEntry<K,V>());
			return null;
		}
		else{
			return table.get(found).setValue(value);
		}
	}

	@Override
	public V remove(K key) {
		int found = locateIndex(key);
		int size = size();
		
		if(found == -1){
			return null;
		}
		
		V answer = table.get(found).getValue();
		if(found != size - 1){
			table.set(found, table.get(size-1));
		}
		
		table.remove(size-1);
		return answer;
	}

	/**
	 * 
	 * @author aarondavis
	 *
	 */
	private class EntryIterator implements Iterator<Entry<K,V>>{
		private int j = 0;

		@Override
		public boolean hasNext() {
			return j < table.size();
		}

		@Override
		public Entry<K, V> next() {
			if(j == table.size()){
				throw new NoSuchElementException();
			}
			
			return table.get(j++);
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class EntryIterable implements Iterable<Entry<K,V>>{
		public Iterator<Entry<K,V>> iterator(){
			return new EntryIterator();
		}
	}
	
	@Override
	public Iterable<Entry<K, V>> entrySet() {
		return new EntryIterable();
	}
}
