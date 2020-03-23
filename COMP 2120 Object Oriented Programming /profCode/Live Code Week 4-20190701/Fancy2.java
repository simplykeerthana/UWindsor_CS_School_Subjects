public class Fancy2<E> implements AddGeneric<E>
{
  public E add(E o)
  {
    return o;
  }

  public E identityElement()
  {
    return null;
  }

  public static void main(String[] args)
  {
    Fancy<Object> a;
    Fancy<Math> b;
  }
}
