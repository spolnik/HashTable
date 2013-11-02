public interface List<E> {
    void add(E item);

    E remove(int index);

    int size();

    E get(int index);

    void set(int index, E item);

    boolean contains(E key);
}
