/*
     Name: Keerthana Madhavan
     Date: 30/5/2019
     Program: Assignment 2
 
 */

import java.util.Scanner; //imports the scanner utility

public class Polynomial
{
    private double[] coeffs; //polynomial coeffecients
    
    public Polynomial(double coeffs[]) .. constructor
    {
        this.coeffs = new double[coeffs.length]; // new object of the coeffs double array
        
        for(int i = 0; i != this.coeffs.length; ++i)
        {
            this.coeffs[i] = coeffs[i]; // sets the values of coeffs to the the this.coeffs object.
            
        }
        
    }

    public String toString() // uses the String class to convert the buffer arguements
    {
        StringBuilder buf = new StringBuilder(); // buffer
        
        for (int i = this.coeffs.length; i!=0; --i)
        {
            if(this.coeffs[i-1] == 0 && i-1 !=0)
                continue;
            
            buf.append(this.coeffs[i-1]);
            
            switch(i-1) // output expressions
            {
                    
                default: buf.append("*x^" + (i-1)+ " + ");
                        //System.out.println("x^<exponent> +");
                    
                case 0: buf.append("");

            }
        }
        
        return buf.toString();
    }
    
    public boolean isNil() //if empty coeffs
    {
        if(this.coeffs.length ==0)
            return true;
        else
            return false;
    }
    
    public boolean isScalar() //atleast 1 constant
    {
        if(this.coeffs.length == 1)
            return true;
        else
            return false;
    }
    
    public boolean isPolynomial() // more than one degress
    {
        if(this.coeffs.length > 1)
            return true;
        else
            return false;
    }
    
    public int degree()
    {
        int retval = 0;
        for( int i = this.coeffs.length; i!= 0; --i) // high exponent identifier
        {
            if(this.coeffs[i-1] != 0)
            {
                retval = i-1;
                break;
            }
        }
        
        return retval; // return the degree of the polynomial
    }
    
    public double coeffecient(int exponent) throws ArrayIndexOutOfBoundsException
    {
        if(exponent >= 0 && exponent < this.coeffs.length) //checks if exponent is valid
            return coeffs[exponent]; //return the coefficients
        else
            throw new ArrayIndexOutOfBoundsException("Polynomial coefficient exponent must be < " +  this.coeffs.length + " but was " + exponent);
        
    }
    
    public Polynomial derivative()
    {
        if(this.coeffs.length == 0)
            return this;
        else
        {
            double new_coeffs[] = new double [this.coeffs.length-1];
            
           // new_coeffs = new double[this.coeffs.length -1];
            
            for(int i = 0; i != new_coeffs.length; i++)
            {
                new_coeffs[i] = (i+1) * this.coeffs[i + 1];
                
            }
             return new  Polynomial(new_coeffs);
        }
        
    }

    
    
        
        
    public double[] roots() throws ArithmeticException
    {
        switch (this.degree())
        {
            default: throw new ArithmeticException("Unable to find root of a Polynomial of degree " + this.degree());
            case 1:
                {
                    if (this.coeffs[1] != 0)
                    {
                        //there is one real root
                        
                        double[] retval = new double[1];
                        retval[0] = this.coeffs[0] / this.coeffs[1] * -1.0;
                        return retval;
                    }
                    else
                        return new double[0];
                }
            case 2:
                {
                    final double a = this.coeffs[2];
                    final double b = this.coeffs[1];
                    final double c = this.coeffs[0];
                    final double discriminant = Math.sqrt(b*b-4*a*c);
                    final double q = (b+Math.signum(b)*discriminant)/-2;
                    final double root1 = q / a;
                    final double root2 = c / q;
                    
                    if (Double.isNaN(root1) && Double.isNaN(root2))
                    {
                         return new double[0]; // no roots
                        
                    }
                    else if (!Double.isNaN(root1) && Double.isNaN(root2))
                    {
                        double[] retval = new double[1];
                        retval[0] = root1;
                        return retval;
                    }
                    else if((Double.isNaN(root1)) && (!Double.isNaN(root2)))
                    {
                        double[] retval = new double[1];
                        retval[0] = root2;
                        return retval;
                        
                    }
                    else if ((!Double.isNaN(root1)) && (!Double.isNaN(root2)))
                    {
                        if(root1 == root2)
                        {
                            double[] retval = new double[1];
                            retval[0] = root1;
                            return retval;
                        }
                        else
                        {
                            double[] retval = new double[2];
                            retval[0] = root1;
                            retval[1] = root2;
                            return retval;
                        }
                    }

            }
        }
        return new double[0];
    }
    
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        
        do
        {
            System.out.print("Enter polynomial coefficients: ");
            if (!in.hasNextLine())
                break;
            
            String pline = in.nextLine();
            
            // NOTE: Next line was used for non-interactive input to produce
            // output for assignment instructions...
            System.out.println(pline);
            
            int count = 0;
            Scanner lineIn = new Scanner(pline);
            while (lineIn.hasNextDouble())
            {
                lineIn.nextDouble();
                ++count;
            }
            
            double[] coeffs = new double[count];
            lineIn = new Scanner(pline);
            while (lineIn.hasNextDouble())
                coeffs[--count] = lineIn.nextDouble();
            
            Polynomial p = new Polynomial(coeffs);
            System.out.println("  Polynomial: " + p);
            System.out.println("       isNil: " + p.isNil());
            System.out.println("    isScalar: " + p.isScalar());
            System.out.println("isPolynomial: " + p.isPolynomial());
            System.out.println("      Degree: " + p.degree());
            System.out.println("  Derivative: " + p.derivative());
            
            try
            {
                final double[] roots = p.roots();
                if (roots.length == 0)
                    System.out.println("       Roots: <None>");
                else
                {
                    System.out.print("       Roots: { ");
                    for (int i=0; i != roots.length; ++i)
                    {
                        System.out.print(""+roots[i]);
                        if (i+1 != roots.length)
                            System.out.print(", ");
                        else
                            System.out.println(" }");
                    }
                }
            }
            catch (Exception e)
            {
                System.out.println("Error occurred finding roots: "+e.getMessage());
            }
        }
        while (true);
    }


}
