package edu.Frostburg.COSC310_Spellchecker;

import java.util.Iterator;
import java.util.Map.Entry;

public abstract class AbstractMap<K,V> implements MapADT<K,V> {

	public boolean isEmpty(){
		return size() == 0;
	}
	
	protected static class MapEntry<K,V> implements Entry<K,V>{
		private K key;
		private V value;
		
		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return value;
		}

		@Override
		public V setValue(V value) {
			V temp = this.value;
			this.value = value;
			return temp;
		}
	}
	
	/**
	 * 
	 * @author aarondavis
	 * Support for public keySet method
	 */
	private class KeyIterator implements Iterator<K>{
		private Iterator<Entry<K,V>> entries = entrySet().iterator();
		
		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public K next() {
			return entries.next().getKey();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class KeyIterable implements Iterable<K>{
		public Iterator<K> iterator(){
			return new KeyIterator();
		}
	}
	
	public Iterable<K> keySet(){
		return new KeyIterable();
	}
	
	/**
	 * 
	 * @author aarondavis
	 * Support for public value methods
	 */
	private class ValueIterator implements Iterator<V>{
		private Iterator<Entry<K,V>> entries = entrySet().iterator();

		@Override
		public boolean hasNext() {
			return entries.hasNext();
		}

		@Override
		public V next() {
			return entries.next().getValue();
		}

		@Override
		public void remove() {
			throw new UnsupportedOperationException();
		}
	}
	
	private class ValueIterable implements Iterable<V>{
		public Iterator<V> iterator(){
			return new ValueIterator();
		}
	}
	
	public Iterable<V> values(){
		return new ValueIterable();
	}
}
