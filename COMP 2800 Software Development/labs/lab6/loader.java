

import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;

import javax.swing.*;

public class loader extends JFrame{
	
	boolean resolve;
	String name;
	JPanel a = new JPanel();
	Object c;
	
	
	public loader() {
		super("loader");
		
		JLayeredPane l = new JLayeredPane();
		JPanel top = new JPanel();
		JPanel bot = new JPanel();
		setLayout(new BorderLayout());
		getContentPane().add(l, BorderLayout.CENTER);
		
		l.setBounds(0, 0, 600, 800);
        top.setBounds(0, 0, 600, 25);
        
        bot.setBackground(Color.white);
        bot.setBounds(0,30,600,775);
        
        l.add(top, 0, 0);
        l.add(bot, 1, 0);
		
		
		JLabel string = new JLabel("Enter Class Name:");
		JTextField classname = new JTextField();
		classname.setColumns(20);
		
		classname.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				
				name = classname.getText();
				JFrame f = new JFrame();
				System.out.println(name);
				
				
				try {
					c = Class.forName(name).newInstance();
					
					Method[] m1 = c.getClass().getDeclaredMethods();
					
					System.out.println(m1.length);
					
					a.removeAll();
					a = (JPanel) c;
					
					a.setBounds(0,30,600,600);
					a.revalidate();
					
					l.add(a,1,0);
					
					
				} catch (Exception e1) {
					System.out.println(e1);
				}
				
				if(name.equals("ColorSlider")) {
					
					
					JTextField r = new JTextField("0");
					JTextField g = new JTextField("0");
					JTextField b = new JTextField("0");
					
					f.setSize(300,300);
					
					f.setLayout(new GridLayout(3,2));
					
					f.add(new JLabel("red:"));
					f.add(r);
					f.add(new JLabel("green:"));
					f.add(g);
					f.add(new JLabel("blue:"));
					f.add(b);
					
					r.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Method m = c.getClass().getDeclaredMethod("setRed", int.class);
								m.setAccessible(true);
								m.invoke(c,Integer.parseInt(r.getText()) );
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.println("nope");
								e1.printStackTrace();
							}
						}
						
					});
					g.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Method m = c.getClass().getDeclaredMethod("setGreen", int.class);
								m.setAccessible(true);
								m.invoke(c,Integer.parseInt(g.getText()) );
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.println("nope");
								e1.printStackTrace();
							}
							
						}
						
					});
					b.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								Method m = c.getClass().getDeclaredMethod("setBlue", int.class);
								m.setAccessible(true);
								m.invoke(c,Integer.parseInt(b.getText()) );
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.println("nope");
								e1.printStackTrace();
							}
							
						}
						
					});
					
					f.setSize(300,300);
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					f.setVisible(true);
					
				}
				else if(name.equals("GridLayoutDemo2")) {
					f.setLayout(new GridLayout(2,2));
					JTextField b = new JTextField("True");
					JButton r = new JButton("refresh");
					
					f.add(new JLabel("Toggle"));
					f.add(b);
					f.add(r);
					
					b.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								boolean t = true;
								Method m = c.getClass().getDeclaredMethod("setToggle", boolean.class);
								m.setAccessible(true);
								if((b.getText().toLowerCase()).equals("false")){
									t = false;
								}
								else {
									t = true;
								}
								m.invoke(c,t);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.println("nope");
								e1.printStackTrace();
							}
							
						}
						
					});
					
					r.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								boolean t = true;
								Method m = c.getClass().getDeclaredMethod("setToggle", boolean.class);
								m.invoke(c,t);
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								System.out.println("nope");
								e1.printStackTrace();
							}
							
						}
						
					});
					f.setSize(300,300);
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					f.setVisible(true);
					
				}
				
			}
			
		});
		a.setVisible(true);
		
		top.add(string);
		top.add(classname);
		
	}
	
	

	public static void main(String[] args) {
		loader frame = new loader();		
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setSize(600,800);
		frame.setVisible(true);
		
		
	}
	
}

