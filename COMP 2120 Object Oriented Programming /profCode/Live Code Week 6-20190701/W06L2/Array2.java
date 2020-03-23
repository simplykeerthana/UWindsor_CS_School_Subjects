import java.util.*;

public class Array2<E>
{
  private Object[] array;

  public Array2()
  {
    super();
    array = new Object[0];
  }

  public Array2(Array2 other)
  {
    super();
    array = other.array.clone();
  }

  public void add(E value)
  {
    Object[] newarray = new Object[array.length+1];
    int i;
    for (i=0; i < array.length; ++i)
      newarray[i] = array[i];
    newarray[i] = value;
    array = newarray;
    return;
  }

  public void setValueAtIndex(int i, E value)
  {
    array[i] = value;
  }

  public Iterator<E> iterator()
  {
    class Iter implements Iterator<E>
    {
      int pos = 0;

      public boolean hasNext()
      {
        return pos < array.length;
      }

      public E next() throws NoSuchElementException
      {
        if (hasNext())
        {
          // NOTE: Arrays of generic type names in Java all become
          //       arrays of type Object when compiled. This creates
          //       casting issues, etc. Since it is impossible to inject
          //       a non-E type into this.array, it is safe to cast
          //       to type E and to suppress the compiler warning...
          @SuppressWarnings("unchecked")
          E retval = (E)array[pos];
          ++pos;
          return retval;
        }
        else
          throw new NoSuchElementException();
      }

      public void remove() throws UnsupportedOperationException
      {
        throw new UnsupportedOperationException();
      }
    }
    return new Iter();
  }

  public static final void main(String[] args)
  {
    Array2<String> a = new Array2<String>();
    a.add("Hello");
    a.add("World");
    a.add("Today");
    for (Iterator<String> i=a.iterator(); i.hasNext(); )
      System.out.println(i.next());

    Array2<String> b = new Array2<String>(a);

    // demonstrate that copy constructor is copying...
    a.setValueAtIndex(0, "dasdasdas");
    for (Iterator<String> i=b.iterator(); i.hasNext(); )
      System.out.println(i.next());
  }
}
