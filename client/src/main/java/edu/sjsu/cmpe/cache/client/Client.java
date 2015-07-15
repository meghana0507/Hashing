package edu.sjsu.cmpe.cache.client;

import java.util.ArrayList;

/**
 * Created by Meghana on 5/7/2015.
 */


public class Client {

    public static void main(String[] args) throws Exception {

        ArrayList cacheServer = new ArrayList();

        cacheServer.add("http://localhost:3000");
        cacheServer.add("http://localhost:3001");
        cacheServer.add("http://localhost:3002");

	//--------------- CONSISTENT HASHING ------------------//
        System.out.println("Starting Cache Client for ConsistentHashing...");
        ConsistentHashing chObj = new ConsistentHashing(cacheServer);
		
		System.out.println("Values adding to cache servers...");
        for(int i = 1; i<11; i++){
            addToConstCache(i, String.valueOf((char) (i + 96)), chObj);
        }

        System.out.println("Cache retrieved from servers...");
        for(int i =1; i<11; i++){
            String value = (String)getFromConstCache(i,chObj);
            System.out.println("Getting value from cache:"+ value);
        }

        System.out.println("Exiting Cache Client...");

       //------------------ HRW HASHING --------------------// 
        System.out.println("Starting Cache Client for HRWHashing...");
        HRWHashing<String> hrwObj = new HRWHashing(cacheServer);

        System.out.println("Values adding to cache servers...");
        for(int i = 1; i<11; i++){
            addToHRWCache(i, String.valueOf((char) (i + 96)), hrwObj);
        }

        System.out.println("Cache retrieved from servers...");
        for(int i =1; i<11; i++){
            String value = (String)getFromHRWCache(i, hrwObj);
            System.out.println("Getting value from cache:" + value);
        }
		 System.out.println("Exiting Cache Client...");
		

    }

   
    public static void addToConstCache(int Key, String Value, ConsistentHashing chObj){
        String cacheUrl = (String) chObj.getCache(Key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        cache.put(Key,Value);
        System.out.println("put( "+ Key +" => " + Value + ")");
    }

    public static Object getFromConstCache(int Key, ConsistentHashing chObj){
        String cacheUrl = (String) chObj.getCache(Key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        String Value = cache.get(Key);
        System.out.println("get( "+ Key+ " ) => "+ Value);
        return Value;
    }

    public static void addToHRWCache(int Key, String Value, HRWHashing hrwObj){
        String cacheUrl = (String) hrwObj.getCache(Key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        cache.put(Key,Value);
        System.out.println("put( " + Key + " => " + Value + ")");

    }
    public static Object getFromHRWCache(int Key, HRWHashing hrwObj){
        String cacheUrl = (String) hrwObj.getCache(Key);
        CacheServiceInterface cache = new DistributedCacheService(cacheUrl);
        String Value = cache.get(Key);
        System.out.println("get( "+ Key+ " ) => "+ Value);
        return Value;
    }
 
}
