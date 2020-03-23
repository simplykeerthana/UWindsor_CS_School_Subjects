/*
     Name: Keerthana Mahdavan
     Date: 6/1/19
     Program: video game machines redeem
 */

/*
    The video game machines at your local arcade output coupons according to how well you play the game. You can redeem 10 coupons for a candy bar or 3 coupons for a gumball. You prefer  candy bars to gumballs. Write a program that defines a variable initially assigned to a number of coupons you win. Next, the program should output how many candy bars and gumballs you can get if you spend all of your coupons on candy first,and any remainng coupons on gumballs.
 
 
 */
public class question2
{
    public static void main(String [] args)
    {
        // 10 coupons for candy bar
        // 3 copouns for gumball
        
        int numberOfCouponsWon = 200;
        int candyCouponCount = 10, gumballCouponCount = 3;
        int getCandyBar = 0, getGumball = 0;
        
        getCandyBar = numberOfCouponsWon / candyCouponCount;
        getGumball = (numberOfCouponsWon % candyCouponCount) / gumballCouponCount;
        
        System.out.println("The number of candy bars you can get is: " + getCandyBar);
        System.out.println("The number of gumball you can get is: " + getGumball);
        
    }

}
