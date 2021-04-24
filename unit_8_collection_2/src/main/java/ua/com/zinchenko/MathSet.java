package ua.com.zinchenko;


public class MathSet<T extends Number> {

    private Number[] data;
    private int size;
    private int capacity;

    private final static int INIT_CAPACITY = 10;
    private final static int STANDARD_INCREASE_COEFFICIENT = 2;

    public MathSet() {
        capacity = INIT_CAPACITY;
        data = new Number[capacity];
    }

    public MathSet(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("Incorrect capacity: " + capacity);
        } else {
            this.capacity = capacity;
            data = new Number[this.capacity];
        }
    }

    public MathSet(T[] numbers) {
        //calc capacity for init array
        capacity = numbers.length;

        //init array
        data = new Number[capacity];

        //get items from array of arrays and add in our array
        for (T num : numbers) {
            if(!isContains(num)) {
                data[size] = num;
                size++;
            }
        }
    }

    @SafeVarargs
    public MathSet(T[] ... numbers) {
        //calc capacity for init array
        for (T[] num : numbers) {
            capacity += num.length;
        }

        //init array
        data = new Number[capacity];

        //get items from array of arrays and add in our array
        for (T[] numArray : numbers) {
            for (T num : numArray) {
                if(!isContains(num)) {
                    data[size] = num;
                    size++;
                }
            }
        }
    }

    public MathSet(MathSet<T> numbers) {
        //calc capacity for init array
        capacity = numbers.size();

        //init array
        data = new Number[capacity];
        size = 0;
        //get items from math set and add in our array
        // we don't check item on uniq because MathSet have only uniq items
        for(int i = 0; i < capacity; i++) {
            data[size] = numbers.get(i);
            size++;
        }
    }

    @SafeVarargs
    public MathSet(MathSet<T> ... numbers) {
        //calc capacity for init array
        for(MathSet<T> mathSet : numbers) {
            capacity += mathSet.size();
        }

        //init array
        data = new Number[capacity];

        //get items from array of math sets and add in our array
        for(MathSet<T> mathSet : numbers) {
            for(int i = 0; i < mathSet.size(); i++) {
                T item = mathSet.get(i);
                if(!isContains(item)) {
                    data[size] = item;
                    size++;
                }
            }
        }
    }

    public void add(T item) {
        if(!isContains(item)) {
            if (size == capacity) {
                changeCapacity(capacity * STANDARD_INCREASE_COEFFICIENT);
            }
            data[size] = item;
            size++;
        }
    }

    @SafeVarargs
    public final void add(T... n) {
        for (T num : n) {
            add(num);
        }
    }

    public void join(MathSet<T> ms) {
        int newCapacity = size + ms.size();
        changeCapacity(newCapacity);

        for(int i = 0; i < ms.size(); i++) {
            T item = ms.get(i);
            if(!isContains(item)) {
                data[size] = item;
                size++;
            }
        }
    }


    @SafeVarargs
    public final void join(MathSet<T>... ms) {
        int newCapacity = 0;
        for (MathSet<T> mathSet : ms) {
            newCapacity += mathSet.size();
        }
        newCapacity += size();
        changeCapacity(newCapacity);

        for(MathSet<T> mathSet : ms) {
            for(int i = 0; i < mathSet.size(); i++) {
                T item = mathSet.get(i);
                if(!isContains(item)) {
                    data[size] = item;
                    size++;
                }
            }
        }
    }

    public void sortDesc(){
        quickSortDesc(data, 0, size - 1);
    }

    void sortDesc(int firstIndex, int lastIndex) {
        quickSortDesc(data, firstIndex, lastIndex);
    }

    void sortDesc(T value) {
        sortDesc(getIndex(value), size - 1);
    }

    public void sortAsc(){
        quickSortAsc(data, 0, size - 1);
    }

    void sortAsc(int firstIndex, int lastIndex) {
        quickSortAsc(data, firstIndex, lastIndex);
    }

    void sortAsc(T value) {
        sortAsc(getIndex(value), size - 1);
    }

    @SuppressWarnings("unchecked")
    public T get(int index) {
        if(index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Element with index: " + index + " is does not exist");
        }
        return (T) data[index];
    }

    @SuppressWarnings("unchecked")
    public T getMax() {
        Double max = get(0).doubleValue();
        for(int i = 1; i < size; i++) {
            if(get(i).doubleValue() > max) {
                max = get(i).doubleValue();
            }
        }
        return (T) max;
    }

    @SuppressWarnings("unchecked")
    public T getMin() {
        Double min = get(0).doubleValue();
        for(int i = 1; i < size; i++) {
            if(get(i).doubleValue() < min) {
                min = get(i).doubleValue();
            }
        }
        return (T) min;
    }

    public Number getAverage() {
        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += get(i).doubleValue();
        }
        return sum / size();
    }

    public Double getMedian() {
        Number[] buffer = new Number[size()];
        System.arraycopy(data, 0, buffer, 0, size());

        quickSortAsc(buffer, 0, buffer.length - 1);
        if(buffer.length % 2 == 1) {
            return buffer[(size) / 2].doubleValue();
        } else {
            return ((buffer[size / 2].doubleValue() + buffer[(size) / 2 - 1].doubleValue()) / 2);
        }
    }

    @SuppressWarnings("unchecked")
    public T[] toArray() {
        T[] array = (T[]) new Number[size];
        for (int i = 0; i < size; i++) {
            array[i] = get(i);
        }
        return array;
    }

    @SuppressWarnings("unchecked")
    public T[] toArray(int firstIndex, int lastIndex) {
        T[] array = (T[]) new Number[size];
        if(firstIndex < 0 || firstIndex > lastIndex) {
            throw new IndexOutOfBoundsException("Invalid first index: " + firstIndex);
        }
        if(lastIndex > size - 1) {
            throw new IndexOutOfBoundsException("Invalid last index: " + lastIndex);
        }
        for (int i = firstIndex, j = 0; i <= lastIndex; i++, j++) {
            array[j] = get(i);
        }
        return array;
    }

    public MathSet<T> squash(int firstIndex, int lastIndex) {
        return new MathSet<T>(toArray(firstIndex,lastIndex));
    }

    public void clear() {
        this.data = new Number[INIT_CAPACITY];
        size = 0;
    }

    public void clear(Number[] numbers) {
        int newSize = size;
        for(int i = 0; i < size; i++) {
            for (Number number : numbers) {
                if (number.equals(data[i])) {
                    data[i] = null;
                    newSize--;
                }
            }
        }

        Number[] newData = new Number[newSize];
        for(int i = 0, j = 0; i < size; i++) {
            if(data[i] != null) {
                newData[j] = data[i];
                j++;
            }
        }
        size = newSize;
        data = newData;
    }

    private void changeCapacity(int newCapacity) {
        this.capacity = newCapacity;
        Number[] newData = new Number[capacity];

        System.arraycopy(data, 0, newData, 0, size);

        data = newData;
    }

    private boolean isContains(T item) {
        for(int i = 0; i < size; i++) {
            if(data[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private int partitionAsc(Number[] array, int begin, int end) {
        int counter = begin;
        for (int i = begin; i < end; i++) {
            Comparable<T> arrayI = (Comparable<T>) array[i];
            Comparable<T> arrayPivot = (Comparable<T>) array[end];
            if (arrayI.compareTo((T) arrayPivot) < 0) {
                Number temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        Number temp = array[end];
        array[end] = array[counter];
        array[counter] = temp;

        return counter;
    }

    public void quickSortAsc(Number[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partitionAsc(array, begin, end);
        quickSortAsc(array, begin, pivot-1);
        quickSortAsc(array, pivot+1, end);
    }

    @SuppressWarnings("unchecked")
    private int partitionDesc(Number[] array, int begin, int end) {
        int counter = begin;
        for (int i = begin; i < end; i++) {
            Comparable<T> arrayI = (Comparable<T>) array[i];
            Comparable<T> arrayPivot = (Comparable<T>) array[end];
            if (arrayI.compareTo((T) arrayPivot) > 0) {
                Number temp = array[counter];
                array[counter] = array[i];
                array[i] = temp;
                counter++;
            }
        }
        Number temp = array[end];
        array[end] = array[counter];
        array[counter] = temp;

        return counter;
    }

    public void quickSortDesc(Number[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partitionDesc(array, begin, end);
        quickSortDesc(array, begin, pivot-1);
        quickSortDesc(array, pivot+1, end);
    }

    public int size() {
        return size;
    }

    private int getIndex(T item) {
        int index = -1;
        for(int i = 0; i < size; i++) {
            if (data[i].equals(item)) {
                index = i;
                break;
            }
        }
        if(index == -1) {
            throw new IllegalArgumentException("Value: " + item.doubleValue() + " doesn't exist in set");
        }
        return index;
    }
}