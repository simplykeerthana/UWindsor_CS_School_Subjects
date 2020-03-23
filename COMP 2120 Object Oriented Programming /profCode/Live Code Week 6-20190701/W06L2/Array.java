import java.util.*;

public class Array
{
  private Integer[] array; // NOTE: Integer is an Object.

  Array()
  {
    super();
    array = new Integer[0];
  }

  Array(Array other)
  {
    super();
    array = other.array.clone(); // NOTE: All arrays can be cloned.
  }

  void add(int value)
  {
    Integer[] newarray = new Integer[array.length+1];
    int i;
    for (i=0; i < array.length; ++i)
      newarray[i] = array[i];
    newarray[i] = value;
    array = newarray;
    return;
  }

  public Iterator<Integer> iterator()
  {
    class Iter implements Iterator<Integer>
    {
      int pos = 0;

      public boolean hasNext()
      {
        return pos < array.length;
      }

      public Integer next() throws NoSuchElementException
      {
        if (hasNext())
        {
          Integer retval = array[pos];
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
    Array a = new Array();
    a.add(1);
    a.add(2);
    a.add(3);
    for (Iterator<Integer> i=a.iterator(); i.hasNext(); )
    {
      Integer value = i.next();
      System.out.println(value);
    }
  }
}
