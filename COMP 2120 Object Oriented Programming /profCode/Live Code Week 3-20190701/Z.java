public class Z
{
  public static final void main(String[] args)
  {
    X x1 = new X(4,3);
    X x2 = new Y(4,3);
    x1.output();
    x2.output();
    //---
    
    X[] xes = new X[2];
    xes[0] = x1;
    xes[1] = x2;

    for (int i=0; i != xes.length; ++i)
      xes[i].output();

    for (X element : xes)
      element.output();
  }
}
