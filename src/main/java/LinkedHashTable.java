import sun.plugin.dom.exception.InvalidStateException;

import java.util.HashSet;
import java.util.Set;

public class LinkedHashTable<K,V> implements HashTable<K,V> {

    private List<Node<KeyValue<K,V>>> buckets;
    private List<K> keys = new ArrayList<>();
    private int capacity = 32;

    public LinkedHashTable() {
        refreshBuckets();
    }

    @Override
    public void add(K key, V value) {
        if (keys.contains(key)) {
            throw new InvalidStateException("The element with the same key already exist.");
        }

        keys.add(key);

        if (buckets.size() * 0.75 <= keys.size()) {
            increaseBucketsAndRehash();
        }

        addInternal(key, value);
    }

    @Override
    public V get(K key) {
        int index = getIndex(key);
        Node<KeyValue<K, V>> node = buckets.get(index);

        while (node != null) {
            if (node.getData().key.equals(key)) {
                return node.getData().value;
            }

            node = node.getNext();
        }

        return null;
    }

    @Override
    public V remove(K key) {
        V result = get(key);

        if (result == null)
            return null;

        int index = getIndex(key);
        Node<KeyValue<K, V>> node = buckets.get(index);

        Node<KeyValue<K, V>> newNode = node.deleteNode(node.getData());
        buckets.set(index, newNode);

        return result;
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
        int hash = key.hashCode();

        if (hash < 0)
            hash *= -1;

        return hash % buckets.size();
    }

    private void addInternal(K key, V value) {
        KeyValue<K, V> item = new KeyValue<>(key, value);
        int index = getIndex(key);

        Node<KeyValue<K, V>> oldNode = buckets.get(index);

        if (oldNode != null) {
            oldNode.appendToTail(item);
        }
        else {
            buckets.set(index, new Node<>(item));
        }
    }

    private void increaseBucketsAndRehash() {
        List<Node<KeyValue<K,V>>> oldBuckets = buckets;

        capacity *= 2;
        refreshBuckets();

        for (int i = 0; i < oldBuckets.size(); i++) {
            Node<KeyValue<K, V>> node = oldBuckets.get(i);

            while (node != null) {
                addInternal(node.getData().key, node.getData().value);
                node = node.getNext();
            }
        }
    }

    private void refreshBuckets() {
        buckets = new ArrayList<>();
        for (int i = 0; i < capacity; i++) {
            buckets.add(null);
        }
    }

    class KeyValue<K,V> {
        final K key;
        V value;

        KeyValue(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
}
