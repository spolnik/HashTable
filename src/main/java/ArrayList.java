public class ArrayList<E> implements List<E> {
    private final static int DEFAULT_CAPACITY = 16;

    private Object[] source = new Object[DEFAULT_CAPACITY];
    private int capacity = DEFAULT_CAPACITY;
    private int count = 0;

    @Override
    public void add(E item) {
        checkCapacity();
        source[count] = item;
        count++;
    }

    @Override
    @SuppressWarnings("ManualArrayCopy")
    public E remove(int index) {
        if (index < 0 || index >= count) {
            throw new IndexOutOfBoundsException();
        }

        E removedItem = elementData(index);
        count--;

        // could be optimized with System.arrayCopy (native),
        // for full coverage of algorithm in code the java version is left there
        for (int i = index; i < count; i++) {
            source[i] = source[i+1];
        }

        return removedItem;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public E get(int index) {
        return elementData(index);
    }

    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) source[index];
    }

    @SuppressWarnings("ManualArrayCopy")
    private void checkCapacity() {
        if (count < capacity) {
            return;
        }

        capacity *= 2;
        Object[] newSource = new Object[capacity];

        // could be optimized with System.arrayCopy (native),
        // for full coverage of algorithm in code the java version is left there
        for (int i = 0; i < count; i++) {
            newSource[i] = source[i];
        }

        source = newSource;
    }
}
