package com.rollanddice.comunidice.cache;

public interface Cache<K, V>{
	
	public void put(K k, V v);
	
	public V get(K k);

}
