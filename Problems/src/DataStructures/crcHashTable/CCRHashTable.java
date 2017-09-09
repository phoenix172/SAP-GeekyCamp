package DataStructures.crcHashTable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

//implements coalescing collision resolution
public class CCRHashTable<K, V> {

	private class HashTableBucket {
		private List<HashTableEntry> contents;
		private Optional<HashTableBucket> nextBucket = Optional.empty();

		public HashTableBucket() {
			this.contents = new LinkedList<HashTableEntry>();
		}

		private Optional<HashTableBucket> ensureNextBucket() {
			if(!nextBucket.isPresent() && !cellarEmpty)
				nextBucket = getCellarBucket();
			return nextBucket;
		}

		public int size() {
			return contents.size();
		}
		
		public boolean isEmpty() {
			return contents.isEmpty();
		}

		public void set(K key, V value) {
			Optional<HashTableEntry> entry = get(key);
			if (entry.isPresent())
				entry.get().value = value;
			else {
				if (this.isEmpty() || !ensureNextBucket().isPresent() 
						|| (ensureNextBucket().isPresent() && nextBucket.get().size()>this.size()))
					contents.add(new HashTableEntry(key, value));
				else
					ensureNextBucket().get().set(key, value);
			}
		}

		private Optional<HashTableEntry> getLocal(K key){
			for(HashTableEntry entry : contents) {
				if(entry.key.equals(key))
					return Optional.of(entry);
			}
			return Optional.empty();
		}
		
		public Optional<HashTableEntry> get(K key) {
			Optional<HashTableEntry> result = getLocal(key);
			if (!result.isPresent() && nextBucket.isPresent()
					&& !nextBucket.get().isEmpty())
				return nextBucket.get().get(key);
			return result;
		}
		
		public void remove(K key) {
			Optional<HashTableEntry> entry = getLocal(key);
			if(entry.isPresent())
				contents.remove(entry.get());
			else if (nextBucket.isPresent() && !nextBucket.get().isEmpty())
				nextBucket.get().remove(key);
		}
	}

	private class HashTableEntry {
		public K key;
		public V value;

		public HashTableEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}
	}

	private static final int TOTAL = 101;
	private static final int STORE = (int) (0.86 * TOTAL);
	private int size;
	private boolean cellarEmpty = false;

	private ArrayList<HashTableBucket> map = new ArrayList<HashTableBucket>();

	public CCRHashTable() {
		for (int i = 0; i < TOTAL; i++) {
			map.add(new HashTableBucket());
		}
		size = 0;
	}

	public int size() {
		return size;
	}

	public Optional<HashTableBucket> getCellarBucket() {
		for(int i = STORE;i<TOTAL;i++) {
			HashTableBucket bucket = map.get(i);
			if(bucket.isEmpty())
				return Optional.of(bucket);
		}
		cellarEmpty = true;
		return Optional.empty();
	}

	public void insert(K key, V value) {
		int hashCode = Math.abs(key.hashCode()) % STORE;
		HashTableBucket bucket = map.get(hashCode);
		bucket.set(key, value);
		size++;
	}

	public V get(K key) {
		int hashCode = Math.abs(key.hashCode()) % STORE;
		HashTableBucket bucket = map.get(hashCode);
		Optional<HashTableEntry> result = bucket.get(key);
		if(result.isPresent())
			return result.get().value;
		return null;
	}

	public void remove(K key) {
		int hashCode = Math.abs(key.hashCode()) % STORE;
		HashTableBucket bucket = map.get(hashCode);
		bucket.remove(key);
	}
}
