import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
public class page extends JPanel implements ActionListener
{
	String student = null;
	String file = null;
	
	JTextField sname;
	JTextField fname;
	
	JLabel slabel;
	JLabel flabel;
	
	
	public page()
	{
		
		sname = new JTextField();
		fname = new JTextField();
		sname.addActionListener(this);
		fname.addActionListener(this);
		
		sname.setColumns(20);
		fname.setColumns(20);
		
		slabel = new JLabel("Student Name: ");
		slabel.setLabelFor(sname);
		
		flabel = new JLabel("File Name: ");
		flabel.setLabelFor(fname);
		
		GridBagConstraints gbc = new GridBagConstraints();
		setLayout(new GridBagLayout());
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(slabel,gbc);
		gbc.gridx++;
		add(sname,gbc);
		
		gbc.fill = GridBagConstraints.HORIZONTAL;   
		gbc.gridx = 0;  
		gbc.gridy = 1; 
		add(flabel,gbc);
		gbc.gridx++;
		add(fname,gbc);

	}
	
	
	public static void main(String[] args)
	{
		JFrame f = new JFrame("page");
		f.setSize(500,500);
		
		f.setLayout(new BorderLayout());
		f.getContentPane().add(new page(), BorderLayout.CENTER);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		student = sname.getText();
		file = fname.getText();
		
		fname.setText("");
		sname.setText("");
	}

	public void setName(){
		student = sname.getText();
	}
	public void setFile(){
		file = fname.getText();
	}
	public JTextField getNameField(){
		return sname;
	}
	public JTextField getFileField(){
		return fname;
	}
	public String getName(){
		return student;
	}
	public String getFile(){
		return file;
	}
}
