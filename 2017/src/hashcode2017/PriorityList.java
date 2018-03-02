package hashcode2017;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

public class PriorityList<K> implements Iterable<K>{
	private List<Pair<K, Integer>> list = new LinkedList<Pair<K,Integer>>();
	LinkedHashSet<Pair<K, Integer>>[] array = new LinkedHashSet[1000];
	
	public void insert(K key, Integer value){
		if(value >= array.length){
			LinkedHashSet<Pair<K, Integer>>[] tmp = new LinkedHashSet[value+1];
			System.arraycopy(array, 0, tmp, 0, array.length);
			array = tmp;
		}
		if(array[value] == null){
			array[value] = new LinkedHashSet<Pair<K, Integer>>();
		}
		array[value].add(new Pair<K, Integer>(key, value));
	}
	
	public void sort() {
		for(int i = 0; i < array.length; i++){
			HashSet<Pair<K, Integer>> set = array[i];
			if(set != null && !set.isEmpty()){
				for(Pair<K, Integer> pair : set){
					list.add(pair);
				}
			}
		}
		array = null;
	}
	
	public void sortDesc() {
		for(int i = array.length - 1; i >= 0; i--){
			HashSet<Pair<K, Integer>> set = array[i];
			if(set != null && !set.isEmpty()){
				for(Pair<K, Integer> pair : set){
					list.add(pair);
				}
			}
		}
		array = null;
	}

	public boolean isEmpty(){
		return this.list.isEmpty();
	}
	
	@Override
	public Iterator<K> iterator() {		
		return new Iterator<K>() {
			int i = 0;
			
			@Override
			public boolean hasNext() {
				return i < list.size();
			}

			@Override
			public K next() {
				return list.get(i++).key;
				
			}
		};
	}
	
	@SuppressWarnings("hiding")
	class Pair<K, Integer> {
		K key;
		Integer value;
		
		public Pair(K key, Integer value) {
			this.key = key;
			this.value = value;
		}
		
		@Override
		public String toString() {
			return key + " " + value;
		}
	}

}
