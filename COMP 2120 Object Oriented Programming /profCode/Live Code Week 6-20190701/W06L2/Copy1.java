public class Copy1 implements Cloneable 
  // Step 1: Implement the Cloneable interface.
{
  private String name;

  public Copy1()
  {
    super();
    name = new String();
  }

  public Copy1(String name)
  {
    super();
    this.name = name;
  }

  // Step 2: Override clone(), make it public, and (ideally) return
  //         this' type.
  @Override
  public Copy1 clone()
  {
    // Step 3 (Optional): Since we are defining clone() here, the clone
    //   operation is supported --so there is no need to 
    //   "throws CloneNotSupported" with this clone() method-- thus,
    //   use a try block and catch CloneNotSupported here.
    try 
    { 
      Copy1 copy = (Copy1)super.clone(); // Step 4: Clone the parent.
  
      // Step 5: Clone / copy each member appropriately...
      //copy.name = (String)name.clone(); // Would work if String implemented Cloneable.
      copy.name = new String(name); // String implements a copy constructor.

      return copy; // Step 6: Return the new copy.
    }
    catch (CloneNotSupportedException e) 
    { 
      return null;
    }
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
    Copy1 a = new Copy1();
    Copy1 b = new Copy1("Hello World");
    Copy1 c = a.clone();
    Copy1 d = b.clone();
   
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
