package edu.sjsu.cmpe.cache.client;

import com.google.common.hash.HashFunction;
import com.google.common.hash.Hashing;

import java.util.Map;
import java.util.HashMap;
import java.util.Collection;


/**
 * Created by Meghana on 5/7/2015.
 */
public class HRWHashing<T> {

    private final HashFunction hashFunction;
    private final HashMap<Integer,T> nodeList = new HashMap<Integer,T>();

    public HRWHashing(Collection<T> nodes){

        this.hashFunction = Hashing.md5();
        for(T node: nodes){
            add(node);
        }
        System.out.println("Node list: "+ nodeList);
    }
    public void add(T node) {

        int hash = hashFunction.newHasher().putString(node.toString()).hash().asInt();
        System.out.println("Hash when adding : " + hash);
        nodeList.put(hash, node);

    }

    public T getCache(Object key){
        if(nodeList.isEmpty()){
            return null;
        }
        Integer maxHash = Integer.MIN_VALUE;
        T maxNode = null;

        for (Map.Entry<Integer, T> node : nodeList.entrySet()) {
            int current = hashFunction.newHasher()
                    .putString(key.toString())
                    .putString(node.getValue().toString()).hash().asInt();

            if (current > maxHash) {
                maxHash = current;
                maxNode = node.getValue();

            }
        }

        return maxNode;
    }
}
