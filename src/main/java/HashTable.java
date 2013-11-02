import java.util.Set;

public interface HashTable<K,V> {
    void add(K key, V value);
    V get(K key);
    V remove(K key);
    Set<K> getKeys();
}
