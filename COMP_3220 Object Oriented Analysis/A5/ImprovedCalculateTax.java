/*
 *  Name: Keerthana Madhavan
 *   Description: Rewriting the bad code application using Abstract Factory pattern.
*/
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ImprovedCalculateTax extends JFrame implements ActionListener {
	private JLabel promptName;
	private JLabel promptProvince;
	private JLabel promptAddress;
	private JLabel promptIncome;
	private JTextField inputName;
	private JTextField inputProvince;
	private JTextField inputAddress;
	private JTextArea outputArea;
	private JTextField inputIncome;
	
	private JRadioButton maleButton;
	private JRadioButton femaleButton;
	String gender;
	private ActionListener myActionHandler;
	
/* 
 * Declare all the gui objects and the instance variables needed.	
 */
	public ImprovedCalculateTax(){
		super("Calculate Tax");
		setLayout(new FlowLayout());
		/* myActionHandler handles the event of selecting a radio button */
		myActionHandler = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gender = ((JRadioButton) e.getSource()).getText();
			}
		};
		
	    promptName = new JLabel("Enter Name");
	    add(promptName);	    
	    inputName = new JTextField(20);
	    add(inputName);
	    promptAddress = new JLabel("Enter Address");
	    add(promptAddress);
	    inputAddress = new JTextField(20);
	    add(inputAddress);
	    promptProvince = new JLabel("Enter Province");  
	    add(promptProvince); 
	    inputProvince = new JTextField(20);
	    add(inputProvince);
	    promptIncome = new JLabel("Enter Income"); 
	    add(promptIncome);
	    inputIncome = new JTextField(20);
	    add(inputIncome);
	    inputIncome.addActionListener(this);
	    outputArea = new JTextArea(20, 30);
	    add(outputArea);
	    maleButton = new JRadioButton("male", false);
	    femaleButton = new JRadioButton("female", false);
	    add(maleButton);
	    add(femaleButton);
	    maleButton.addActionListener(myActionHandler);
	    femaleButton.addActionListener(myActionHandler);
	    setSize(400,550);
		setVisible(true);
	}
	
	
	public void actionPerformed(ActionEvent evt){
		String province;
		double income;
		String name;
		String address;
		String letterToBeSent;
		IncomeTaxFactory myFactory;
		TaxMaster myLetterGenerator;
		
		name = inputName.getText();
		address = inputAddress.getText();
		province = inputProvince.getText();
		income = Double.parseDouble(inputIncome.getText());
		
		/*
		 * Steps used:
		 * 1) Extract all the information entered by the user.
		 * 2) Initialize myFactory with the Quebec Factory 
		 *     or the Ontario factory depending on the province 
		 *     selected.
		 * 3) create myLetterGenerator using myFactory. Here 
		 *    myLetterGenerator is an object which will actually generate 
		 *    the letter to be sent and contains methods appropriate for 
		 *    the province.
		 * 4) Using myLetterGenerator, generate the letter to be displayed.
		 * 5) display the letter in the JTextArea
		 */
		if (province.equals("Quebec")){
			myFactory = new QuebecTaxFactory();
		} else {
			myFactory = new OntarioTaxFactory();
		}
		myLetterGenerator = new TaxMaster(myFactory);
		letterToBeSent = myLetterGenerator.generateLetter(name, 
				                                          address, 
                                                          gender, 
                                                          income);
		outputArea.setText(letterToBeSent);
		inputName.setText("");
		inputAddress.setText("");
		inputProvince.setText("");
		inputIncome.setText("");
	}
	
	public static void main(String a[]){
		ImprovedCalculateTax newFrame = new ImprovedCalculateTax();
	}

}
