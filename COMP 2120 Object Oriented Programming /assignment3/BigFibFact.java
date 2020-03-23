/*
     Name: Keerthana Madhavan
     Date: 30/5/19
     Program: Assignment 3, this is Professor's X code and I made the recommended changes
*/

import java.util.ArrayList;
import java.math.BigInteger;

public class BigFibFact
{
  private static ArrayList<BigInteger> fib_cache = new ArrayList<BigInteger>();
  private static ArrayList<BigInteger> fact_cache = new ArrayList<BigInteger>();

  public static BigInteger fib(int N)
  {
    int size = fib_cache.size();

    if (N < size)
      return fib_cache.get(N); // return previously computed answer
    else if (fib_cache.isEmpty())
    {
      // ensure fib_cache contains "base case" entries...
      fib_cache.add(0, BigInteger.ONE);
      fib_cache.add(1, BigInteger.ONE);
      size = fib_cache.size();
    }

    // Ensure that N >= size...
    if (N < size)
      return fib_cache.get(N); // return previously computed answer

    // It is safe to start at size-2 since base case values are present...
    int i = size-2;

    // Add the first two previously computed values...
    BigInteger value1 = fib_cache.get(i++);
    BigInteger value2 = fib_cache.get(i++);
    BigInteger valueNew;


    // and then compute fib(i) until i == N...
    do
    {
        //  valueNew = value1 + value2; // fib(i)
        valueNew = value1.add(value2);
        fib_cache.add(i++, valueNew);  // store fib(i) result

        // Adjust last two values...
        value1 = value2;
        value2 = valueNew;
    }
    while (i <= N);
    return valueNew;
  }

  public static BigInteger fact(int N)
  {
    int size = fact_cache.size();

    if (N < size)
      return fact_cache.get(N); // return previously computed answer
    else if (fact_cache.isEmpty())
    {
      // ensure fact_cache contains "base case" entry...
      fact_cache.add(0, BigInteger.ONE);
      size = fact_cache.size();
    }

    // Ensure that N >= size...
    if (N < size)
      return fact_cache.get(N); // return previously computed answer

    // It is safe to start at size-1 since base case value is present...
    int i = size-1;

    // Add the first two previously computed values...
    BigInteger prev = fact_cache.get(i++);
    BigInteger valueNew;

    // and then compute fib(i) until i == N...
    do
    {
      

        valueNew = prev.multiply(BigInteger.valueOf(i)); // fact(i)
      fact_cache.add(i++, valueNew);  // store fact(i) result

      // Adjust prev...
      prev = valueNew;
    }
    while (i <= N);
    return valueNew;
  }

  public static final void main(String[] args)
  {
    
      for (int i=0; i != 110; ++i)
        System.out.println("fib("+i+") = "+fib(i));
   

    
      for (int i=0; i != 40; ++i)
        System.out.println("fact("+i+") = "+fact(i));
  }
}
