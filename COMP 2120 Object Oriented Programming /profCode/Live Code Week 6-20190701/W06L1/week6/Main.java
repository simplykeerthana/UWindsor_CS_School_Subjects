package week6;

public class Main
{
  public static final void main1(String[] args)
  {
    Object[] data = new Object[3];
    data[0] = new Implementation1();
    data[1] = new Implementation2();
    data[2] = null;
    for (int i=0; i != data.length; ++i)
    {
      //if (data[i] != null)
        //System.out.println(data[i].isEmpty());
    }
  }

  public static final void main2(String[] args)
  {
    Object[] data = new Object[4];
    data[0] = new Implementation1();
    data[1] = new Implementation2();
    data[2] = null;
    data[3] = new String();
    for (int i=0; i != data.length; ++i)
    {
      if (data[i] != null && data[i] instanceof Simple)
      {
        Simple s = (Simple)data[i];
        System.out.println(s.isEmpty());
      }
    }
  }
  
  public static final void main3(String[] args)
  {
    Simple[] data = new Simple[3];
    data[0] = new Implementation1();
    data[1] = new Implementation2();
    data[2] = null;
    //data[3] = new String(); NOTE: This is an error
    for (int i=0; i != data.length; ++i)
    {
      if (data[i] != null)
      {
        Simple s = data[i];
        System.out.println(s.isEmpty());
      }
    }
  }
  
  public static final void process(Object[] data)
  {
    for (int i=0; i != data.length; ++i)
    {
      if (data[i] != null)
        System.out.println(data[i].toString());
    }
  }

  public static final void process(Simple[] data)
  {
    for (int i=0; i != data.length; ++i)
      if (data[i] != null)
      {
        Simple s = data[i];
        System.out.println(s.isEmpty());
      }
  }

  public static final void main(String[] args)
  {
    Simple[] data = new Simple[3];
    data[0] = new Implementation1();
    data[1] = new Implementation2();
    data[2] = null;
    process(data);
  }
}
