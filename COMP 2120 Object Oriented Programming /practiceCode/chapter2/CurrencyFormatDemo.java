import java.text.NumberFormat;
// import java.text.* same as the above import but it imports all classes
import java.util.Locale; // for different locations

public class CurrencyFormatDemo
{
    public static void main(String [] args)
    {
        System.out.println("Without formatting");
        
        System.out.println(19.8);
        System.out.println(19.81111);
        System.out.println(19.89999);
        System.out.println(19);
        System.out.println();
        
        System.out.println("Default location: ");
        
        NumberFormat moneyFormatter = NumberFormat.getCurrencyInstance(); // for default locale
        
        System.out.println(moneyFormatter.format(19.8));
        System.out.println(moneyFormatter.format(19.81111));
        System.out.println(moneyFormatter.format(19.89999));
        System.out.println(moneyFormatter.format(19));
        System.out.println();
        
         NumberFormat moneyFormatter2 = NumberFormat.getCurrencyInstance(Locale.US); // for US locale
        
        System.out.println(moneyFormatter2.format(19.8));
        System.out.println(moneyFormatter2.format(19.81111));
        System.out.println(moneyFormatter2.format(19.89999));
        System.out.println(moneyFormatter2.format(19));
        System.out.println();
        
          NumberFormat moneyFormatter3 = NumberFormat.getCurrencyInstance(Locale.GERMANY); // for Germany locale
        
        System.out.println(moneyFormatter3.format(19.8));
        System.out.println(moneyFormatter3.format(19.81111));
        System.out.println(moneyFormatter3.format(19.89999));
        System.out.println(moneyFormatter3.format(19));
        System.out.println();
    }

}
