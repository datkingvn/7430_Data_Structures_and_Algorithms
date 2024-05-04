package ArrayList;

import java.util.Arrays;

public class ArrayListEnhanced<E> implements AbstractArrayList<E> {
    private final int DEFAULT_CAPACITY = 5;
    private E[] elements;
    private int index = 0;

    // constructor
    public ArrayListEnhanced( ) {
        this.elements = (E[]) new Object[ DEFAULT_CAPACITY ];
    }
    @Override
    public boolean add( E element ) {
        if ( this.index == elements.length ) {
            this.elements = Arrays.copyOf( this.elements, this.elements.length * 2 );
        }

        this.elements[ this.index ] = element;
        this.index = this.index + 1;
        return true;

    }
    @Override
    public boolean add( int index, E element ) {
        if ( index < 0 || index >= this.index ) {
            throw new IndexOutOfBoundsException( "Index out of bound." );
        }

        if ( this.index == elements.length ) {
            this.elements = Arrays.copyOf( this.elements, this.elements.length * 2 );
        }

        // 1: move data to the right -> loop
        for ( int i = this.index; i > index; i-- ) {
            elements[ i ] = elements[ i - 1 ];
        }

        // 2: insert new data
        elements[ index ] = element;
        this.index = this.index + 1;

        return true;
    }
    @Override
    public E get( int index ) {
        if ( index < 0 || index >= this.index ) {
            throw new IndexOutOfBoundsException( "Index out of bound." );
        }
        return this.elements[ index ];
    }
    @Override
    public E set( int index, E element ) {
        if ( index < 0 || index >= this.index ) {
            throw new IndexOutOfBoundsException( "Index out of bound." );
        }

        E temp = this.elements[ index ];
        this.elements[ index ] = element;
        return temp;
    }
    @Override
    public E remove( int index ) {
        if ( index < 0 || index >= this.index ) {
            throw new IndexOutOfBoundsException( "Index out of bound." );
        }

        E temp = this.elements[ index ];

        for ( int i = index; i < this.index; i++ ) {
            this.elements[ i ] = elements[ i + 1 ];
        }

        this.elements[ this.index - 1 ] = null;
        this.index = this.index - 1;

        // check and decrease array size if needed
        if ( this.index <= elements.length / 3 ) {
            this.elements = Arrays.copyOf( this.elements, this.elements.length / 2 );
        }

        return temp;
    }
    @Override
    public int size( ) {
        return this.index;
    }
    @Override
    public int indexOf( E element ) {
        int result = -1;
        for ( int i = 0; i < this.index; i++ ) {
            if ( elements[ i ] == element ) {
                result = i;
                break;
            }
        }
        return result;
    }
    @Override
    public boolean contains( E element ) {
        boolean result = false;
        for ( int i = 0; i < this.index; i++ ) {
            if ( elements[ i ] == element ) {
                result = true;
                break;
            }
        }
        return result;
    }
    @Override
    public boolean isEmpty( ) {
        if ( size( ) == 0 ) { // if empty returns true
            return true;
        } else { // if not empty returns false
            return false;
        }
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size(); i++) {
            if (elements[i] != null) {
                sb.append(elements[i].toString());
                if (i < size() - 1) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

}
