package com.example.liam.framework;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Liam on 17/06/2015.
 */
public class Pool<T> {

    //Create a object
    public interface PoolObjectFactory<T>{
        public T createObject();
    }

    //How many entities free to use
    private final List<T> freeObjects;
    private final PoolObjectFactory<T> factory;
    private final int maxSize;

    //Pool constructor, assigning all variables
    public Pool(PoolObjectFactory<T> factory, int maxSize){
        this.factory = factory;
        this.maxSize = maxSize;
        this.freeObjects = new ArrayList<T>(maxSize);
    }

    //Creates new object to be used in game
    public T newObject() {
        T object = null;

        if (freeObjects.size() == 0)
            object = factory.createObject();
        else
            object = freeObjects.remove(freeObjects.size() - 1);

        return object;
    }

    //Free selected objects
    public void free(T object) {
        if (freeObjects.size() < maxSize)
            freeObjects.add(object);
    }
}
