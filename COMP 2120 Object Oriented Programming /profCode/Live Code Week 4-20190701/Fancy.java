public class Fancy implements AddObject
{
  public Object add(Object o)
  {
    return o;
  }

  public Object identityElement()
  {
    return this;
  }

  public static void main(String[] args)
  {
    Fancy f;
    f.add(null);
  }
}
