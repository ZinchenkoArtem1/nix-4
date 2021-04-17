package ua.com.zinchenko;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import java.util.*;

public class OrderedList<E extends Comparable<E>> implements List<E>{

    private final static int INITIAL_CAPACITY = 10;
    private final static double RESIZE_COEFFICIENT_FOR_INCREASING = 2.0;
    private final static double RESIZE_COEFFICIENT_FOR_DECREASING = 0.5;

    private E[] arr;
    private int capacity;
    private int size;

    @SuppressWarnings("unchecked")
    public OrderedList() {
        size = 0;
        capacity = INITIAL_CAPACITY;
        arr = (E[]) new Comparable[capacity];
    }

    @SuppressWarnings("unchecked")
    public OrderedList(int capacity) {
        size = 0;
        this.capacity = capacity;
        arr = (E[]) new Comparable[capacity];
    }

    private int insertPoint(E item) {
        // Lower and upper bounds
        int start = 0;
        int end = size - 1;

        // Traverse the search space
        while (start <= end)
        {
            int mid = (start + end) / 2;

            // If item is found
            if (arr[mid].equals(item))
                return mid;

            else if (arr[mid].compareTo(item) < 0)
                start = mid + 1;

            else
                end = mid - 1;
        }

        // Return insert position
        return end + 1;
    }

    // Method for resize inner array when capacity equals size
    private void resize(double coefficient) {
        capacity *= coefficient;
        @SuppressWarnings("unchecked")
        E[] newArray = (E[]) (new Comparable[capacity]);
        System.arraycopy(arr, 0, newArray, 0, size);
        arr = newArray;
    }


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new OrderedList<E>.Itr();
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(arr, size);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < size)
            return (T[]) Arrays.copyOf(arr, size, a.getClass());
        System.arraycopy(arr, 0, a, 0, size);
        if (a.length > size)
            a[size] = null;
        return a;
    }


    @Override
    public boolean add(E item) {

        if (size + 1 > capacity) {
            resize(RESIZE_COEFFICIENT_FOR_INCREASING);
        }
        int index = insertPoint(item);
        System.arraycopy(arr, index, arr, index + 1, size - index);
        arr[index] = item;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if(size < capacity / 4) {
            resize(RESIZE_COEFFICIENT_FOR_DECREASING);
        }
        for(int i = 0; i < size; i++) {
            if(arr[i].equals(o)) {
                remove(i);
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object e : c)
            if (!contains(e))
                return false;
        return true;
    }

    @Deprecated
    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Deprecated
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new NotImplementedException();
    }

    @Deprecated
    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Deprecated
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        // clear to let GC do its work
        for (int i = 0; i < size; i++)
            arr[i] = null;

        size = 0;
    }

    @Override
    public E get(int index) {
        rangeCheckForAdd(index);
        return arr[index];
    }

    @Deprecated
    @Override
    public E set(int index, E element) {
        throw new NotImplementedException();
    }

    @Deprecated
    @Override
    public void add(int index, E element) {
        throw new NotImplementedException();
    }

    @Override
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++)
                if (arr[i] == null)
                    return i;
        } else {
            for (int i = 0; i < size; i++)
                if (o.equals(arr[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        if(size < capacity / 4) {
            resize(RESIZE_COEFFICIENT_FOR_DECREASING);
        }
        E item = get(index);
        rangeCheckForAdd(index);
        System.arraycopy(arr, index + 1, arr, index, size - index);
        size--;
        return item;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (o == null) {
            for (int i = size-1; i >= 0; i--)
                if (arr[i] == null)
                    return i;
        } else {
            for (int i = size-1; i >= 0; i--)
                if (o.equals(arr[i]))
                    return i;
        }
        return -1;
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size())
            throw new IndexOutOfBoundsException("does not exist element with index: " + index);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        rangeCheckForAdd(index);

        return new OrderedList<E>.ListItr(index);
    }

    //check
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList<E>(this, fromIndex, toIndex);
    }

    private class Itr implements Iterator<E> {
        /**
         * Index of element to be returned by subsequent call to next.
         */
        int cursor = 0;

        /**
         * Index of element returned by most recent call to next or
         * previous.  Reset to -1 if this element is deleted by a call
         * to remove.
         */
        int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public E next() {
            try {
                int i = cursor;
                E next = get(i);
                lastRet = i;
                cursor = i + 1;
                return next;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                OrderedList.this.remove(lastRet);
                if (lastRet < cursor)
                    cursor--;
                lastRet = -1;
            } catch (IndexOutOfBoundsException e) {
                throw new ConcurrentModificationException();
            }
        }
    }

    private class ListItr extends OrderedList<E>.Itr implements ListIterator<E> {
        ListItr(int index) {
            cursor = index;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public E previous() {
            try {
                int i = cursor - 1;
                E previous = get(i);
                lastRet = cursor = i;
                return previous;
            } catch (IndexOutOfBoundsException e) {
                throw new NoSuchElementException();
            }
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(E e) {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                OrderedList.this.set(lastRet, e);
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        public void add(E e) {
            try {
                int i = cursor;
                OrderedList.this.add(i, e);
                lastRet = -1;
                cursor = i + 1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }
}

class SubList<E> extends AbstractList<E> {
    private final List<E> l;
    private final int offset;
    private int size;

    SubList(List<E> list, int fromIndex, int toIndex) {
        if (fromIndex < 0)
            throw new IndexOutOfBoundsException("fromIndex = " + fromIndex);
        if (toIndex > list.size())
            throw new IndexOutOfBoundsException("toIndex = " + toIndex);
        if (fromIndex > toIndex)
            throw new IllegalArgumentException("fromIndex(" + fromIndex +
                    ") > toIndex(" + toIndex + ")");
        l = list;
        offset = fromIndex;
        size = toIndex - fromIndex;
    }

    public E set(int index, E element) {
        rangeCheck(index);
        return l.set(index+offset, element);
    }

    public E get(int index) {
        rangeCheck(index);
        return l.get(index+offset);
    }

    public int size() {
        return size;
    }

    public void add(int index, E element) {
        rangeCheckForAdd(index);
        l.add(index+offset, element);
        size++;
    }

    public E remove(int index) {
        rangeCheck(index);
        E result = l.remove(index+offset);
        size--;
        return result;
    }

    public boolean addAll(Collection<? extends E> c) {
        return addAll(size, c);
    }

    public boolean addAll(int index, Collection<? extends E> c) {
        rangeCheckForAdd(index);
        int cSize = c.size();
        if (cSize==0)
            return false;

        l.addAll(offset+index, c);
        size += cSize;
        return true;
    }

    public Iterator<E> iterator() {
        return listIterator();
    }

    public ListIterator<E> listIterator(final int index) {
        rangeCheckForAdd(index);

        return new ListIterator<E>() {
            private final ListIterator<E> i = l.listIterator(index+offset);

            public boolean hasNext() {
                return nextIndex() < size;
            }

            public E next() {
                if (hasNext())
                    return i.next();
                else
                    throw new NoSuchElementException();
            }

            public boolean hasPrevious() {
                return previousIndex() >= 0;
            }

            public E previous() {
                if (hasPrevious())
                    return i.previous();
                else
                    throw new NoSuchElementException();
            }

            public int nextIndex() {
                return i.nextIndex() - offset;
            }

            public int previousIndex() {
                return i.previousIndex() - offset;
            }

            public void remove() {
                i.remove();
                size--;
            }

            public void set(E e) {
                i.set(e);
            }

            public void add(E e) {
                i.add(e);
                size++;
            }
        };
    }

    public List<E> subList(int fromIndex, int toIndex) {
        return new SubList<>(this, fromIndex, toIndex);
    }

    private void rangeCheck(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }

    private String outOfBoundsMsg(int index) {
        return "Index: "+index+", Size: "+size;
    }
}