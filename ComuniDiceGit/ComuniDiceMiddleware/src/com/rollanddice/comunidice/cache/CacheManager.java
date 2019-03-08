package com.rollanddice.comunidice.cache;

import java.util.HashMap;
import java.util.Map;

public class CacheManager {
	
	private  Map<String, Cache> caches = null;
	
	public CacheManager() {
		
	}

	public  <K, V> Cache<K, V> create(String nombre, Class<K> keyClass, Class<V> valueClass){
		
		Cache<K, V> newCache = new CacheImpl<K, V>();
		caches.put(nombre, newCache);
		
		return newCache;
	}
	
	public  <K, V> Cache<K, V> getCache(String nombre,Class<K> keyClass, Class<V> valueClass) {
		
		return (Cache<K, V>)caches.get(nombre);
		
	}

}
