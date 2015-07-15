package edu.sjsu.cmpe.cache.client;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created by Meghana on 5/7/2015.
 */

public class ConsistentHashing<T> {

    private final HashFunction hashFunction;
    private ArrayList<T> nodeList;

    public ConsistentHashing(ArrayList<T> nodes){
        this.hashFunction = Hashing.md5();
        nodeList  = new ArrayList<T>();
        nodeList.addAll(nodes);
        System.out.println("Node list:" + nodeList);
    }

    public void add(T node){
        nodeList.add(node);
    }

    public void remove(T node){
        nodeList.remove(node);
    }

    public T getCache(Object key){
        int bucket = Hashing.consistentHash(hashFunction.newHasher().putString(key.toString()).hash(), nodeList.size());
        System.out.println("localhost:300"+bucket);
        return nodeList.get(bucket);
    }
}
