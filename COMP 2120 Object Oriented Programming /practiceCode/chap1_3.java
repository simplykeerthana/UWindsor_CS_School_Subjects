
public class chap1_3
{
    public static void main(String [] args)
    {
        String Blessing = "Live long and prosper";
        
        System.out.println(Blessing);
        
        System.out.println(Blessing.length());
        
        System.out.println(Blessing.equalsIgnoreCase("Live lLng and Prosper"));
 
        
        String name = "  KEER\tTH\nANA";
        String name1 = "keerthana";
        
        System.out.println(name);
        
        System.out.println(name1.charAt(3));
        
        System.out.println(name1.substring(4));
        System.out.println(name1.substring(0,3));
        System.out.println(Blessing.indexOf("long", 2));
        System.out.println(Blessing.lastIndexOf("and"));
        
        
        String entry = "adventure";
        
        System.out.println(entry.compareTo("zoo")); //return -1
        System.out.println(entry.compareTo("adventure")); //returns 0;
        System.out.println(entry.compareTo("above")); //positive num
        
        System.out.println("2 + 2 = " + (2+2));
        System.out.println("2 + 2 = " + 2 + 2);
        
    }



}
