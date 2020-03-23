public class Copy2
{
  private String name;

  public Copy2()
  {
    super();
    name = new String();
  }

  public Copy2(String name)
  {
    super();
    this.name = name;
  }
 
  // Step 1: A copy constructor accepts 1 argument whose type is the
  //   same as this' type, i.e., Copy2 here:
  public Copy2(Copy2 other)
  {
    // Step 2: Call super(other) to copy construct the base class
    //   if the base class has a copy constructor --otherwise call
    //   super().
    super(); // NOTE: java.lang.Object does not have a copy constructor.

    // Step 3: Appropriately copy (i.e., using clone() and/or copy 
    //   constructors) each member.
    name = new String(other.name);
  }

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  @Override
  public String toString()
  {
    return getName();
  }

  public static final void main(String[] args)
  {
    Copy2 a = new Copy2();
    Copy2 b = new Copy2("Hello World");
    Copy2 c = new Copy2(a);
    Copy2 d = new Copy2(b);
   
    a.setName("--");
    b.setName("hello world");

    System.out.println(a + "," + b + "," + c + "," + d + ".");

    c = a;

    System.out.println(a + "," + b + "," + c + "," + d + ".");

    c.setName("c");
    d.setName("d");

    System.out.println(a + "," + b + "," + c + "," + d + ".");
  }
}
