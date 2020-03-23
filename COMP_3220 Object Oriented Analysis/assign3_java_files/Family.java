package aPackage;

public class Family {
	
          private static int numFamilies = 0; 
          private int familyNumber = 0; 
          public  int numberOfMembers = 0;
          private Human family[]; 

          public Family() {
                    family = new Human[10]; 
                    numFamilies++; 
                    familyNumber = numFamilies;

          } 

          public void insertFamilyMember (Human newMember) {
                    family[numberOfMembers] = newMember;
                    numberOfMembers++;
          } 

          public String toString() 
          {
                    int numberOfRichMembers = 0; 
                    int totalIncome = 0;
                    Human familyMember; 
                    for(int index = 0; index < numberOfMembers; index++ ) 
                    {
                              
                              familyMember = family[index]; 
                              totalIncome += familyMember.income();
                              if  (familyMember instanceof Adult)
                              { 
                                  if ( ((Adult)familyMember).isRich()) numberOfRichMembers++;
                              } 
                              
                    }
                    return ("Family Member " + familyNumber + " has " + numberOfMembers + 
                             " members, total income " + totalIncome + " and " + numberOfRichMembers + 
                             " rich people.");
          }
}
