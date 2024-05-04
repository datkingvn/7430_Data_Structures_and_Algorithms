package ArrayList;
import java.util.*;

public interface AbstractArrayList<E> {
    boolean add( E element ); // Adds the specified element to the list
    boolean add( int index, E element ); // Inserts the specified element at the specified index in the list
    E get( int index ); // Returns the element at the specified index.
    E set( int index, E element ); // Replaces the element at the specified index with the specified element
    E remove( int index ); // Removes the element at the specified index from the list
    int size( ); // Returns the number of elements in the list.
    int indexOf( E element ); // Returns the index of the first occurrence of the specified element in the list
    boolean contains( E element ); // Checks if the list contains the specified element
    boolean isEmpty( ); // Checks if the list is empty
}
