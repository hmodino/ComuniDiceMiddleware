package com.rollanddice.comunidice.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl<K, V> implements Cache<K, V>{

	private Map<K, V> cache = null;
	
	public CacheImpl(){
		cache = new HashMap<K, V>();
	}
	
	@Override
	public void put(K k, V v) {

		cache.put(k, v);
	}

	@Override
	public V get(K k) {

		V v = cache.get(k);
		return v;
	}

}
