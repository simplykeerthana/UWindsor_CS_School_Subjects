import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
public class page extends JPanel implements ActionListener
{
	String text="failed.";
	JTextField tfield;
	JLabel label;
	public page()
	{
		text="";
		//setVisible(true);
		tfield=new JTextField();
		tfield.addActionListener(this);
		tfield.setColumns(20);
		label=new JLabel("Student Name:");
		label.setLabelFor(tfield);
		add(label);
		add(tfield);
		setBounds(0,0,500,500);
		
	}
	
	
	public static void main(String[] args)
	{
		JFrame pp=new JFrame("tester");
		pp.setSize(500,500);
		
		pp.setLayout(new BorderLayout());
		pp.getContentPane().add(new page(), BorderLayout.CENTER);
		pp.setVisible(true);
		pp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		text=tfield.getText();
		this.text = "";
		tfield.setText("");
		
	}
	public JTextField getMyField()
	{
		return tfield;
	}
	public String getMessage()
	{
		return text;
	}
}
