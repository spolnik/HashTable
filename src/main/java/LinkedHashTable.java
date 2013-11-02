import java.util.HashSet;
import java.util.Set;

public class LinkedHashTable<K,V> implements HashTable<K,V> {
    private List<Node<KeyValue<K,V>>> buckets = new ArrayList<>();
    private List<K> keys = new ArrayList<>();

    public LinkedHashTable() {
        for (int i = 0; i < 32; i++) {
            buckets.add(null);
        }
    }

    @Override
    public void add(K key, V value) {
        keys.add(key);
        KeyValue<K, V> item = new KeyValue<>(key, value);
        int index = getIndex(key);

        Node<KeyValue<K, V>> oldNode = buckets.get(index);
        Node<KeyValue<K,V>> newNode = new Node<>(item);

        if (oldNode != null) {
            newNode.next = oldNode;
        }

        buckets.set(index, newNode);
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        return buckets.get(index).data.value;
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Set<K> getKeys() {
        Set<K> result = new HashSet<>();

        for (int i = 0; i < keys.size(); i++) {
            result.add(keys.get(i));
        }

        return result;
    }

    private int getIndex(K key) {
        int hash = getHash(key);

        if (hash < 0)
            hash *= -1;

        return hash % buckets.size();
    }

    private int getHash(K key) {
        return key.hashCode();
    }

    class KeyValue<K,V> {
        final K key;
        V value;

        KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }
}
