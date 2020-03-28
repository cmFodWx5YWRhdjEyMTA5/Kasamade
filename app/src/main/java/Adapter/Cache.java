package Adapter;

import android.support.v4.util.LruCache;

public class Cache {

    private static Cache instance;
    private LruCache<Object, Object> lru;

    private Cache() {
        final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        int cacheSize = maxMemory / 8;
        cacheSize=5024;
        lru = new LruCache<Object, Object>(cacheSize);

    }

    public static Cache getInstance() {

        if (instance == null) {

            instance = new Cache();
        }

        return instance;

    }

    public LruCache<Object, Object> getLru() {
        return lru;
    }
}