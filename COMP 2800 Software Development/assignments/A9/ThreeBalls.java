import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

class MovingBalls implements Runnable
{
	int xPos;
	int yPos;
	Random randomStop;
	MovingBalls()
	{
		xPos=0;
		yPos=0;
		randomStop=new Random();
	}
	public void run() 
	{
		while(true)
        {
            yPos=yPos+30;
		if(yPos>420)
		{
			yPos=0;
		}
		
		try
            {
			Thread.sleep(1000);
		} catch (InterruptedException e)
            {

			e.printStackTrace();
		}
		}
	}
	int getX()
	{
		return xPos;
	}
	int getY()
	{
		return yPos;
	}
}
public class ThreeBalls extends JPanel implements Runnable
{
	MovingBalls ball1,ball2,ball3;
	public void paintComponent(Graphics g) {
       super.paintComponent(g);
		g.drawOval(ball1.getX(), ball1.getY(), 50, 50);
		g.drawOval(ball2.getX(), ball2.getY(), 50, 50);
		g.drawOval(ball3.getX(), ball3.getY(), 50, 50);
	}
	public void run()
	{
		while(true) {
		this.repaint();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e)
            {
        
			e.printStackTrace();
		}
		}
	}
	ThreeBalls()
	{
		ball1=new MovingBalls();
		ball1.xPos=60;
		ball2=new MovingBalls();
		ball2.xPos=150;
		ball3=new MovingBalls();
		ball3.xPos=240;
		Thread t1=new Thread(ball1);
		Thread t2=new Thread(ball2);
		Thread t3=new Thread(ball3);
		t1.start();
		t2.start();
		t3.start();
		JLayeredPane lp=new JLayeredPane();
		JFrame mainFrame =new JFrame();
		JPanel controls=new JPanel();
		controls.setLayout(new FlowLayout());
		controls.setBackground(Color.WHITE);
		JCheckBox checkbox1 = new JCheckBox("Suspended");
	    JCheckBox checkbox2 = new JCheckBox("Suspended");
	    JCheckBox checkbox3 = new JCheckBox("Suspended");
	    checkbox1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					try {
						t1.suspend();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					t1.resume();
				}
			}
	    });
	    checkbox2.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				// TODO Auto-generated method stub
				if(e.getStateChange()==ItemEvent.SELECTED)
				{
					try {
						t2.suspend();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					t2.resume();
				}
			}
	    });
	    checkbox3.addItemListener(new ItemListener() {
	    	@Override
	    	public void itemStateChanged(ItemEvent e) {
	    		// TODO Auto-generated method stub
	    		if(e.getStateChange()==ItemEvent.SELECTED)
				{
					try {
						t3.suspend();
						
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
				{
					t3.resume();
				}
	    	}
	    });
	    controls.add(checkbox1);
	    controls.add(checkbox2);
	    controls.add(checkbox3);
	    setBackground(Color.WHITE);
	    setBounds(0, 0, 400, 500);
	    controls.setBounds(0, 420, 350, 50);
	    lp.add(this,0,0);
	    lp.add(controls,1,0);
	    mainFrame.add(lp);
		mainFrame.setSize(400,500);
        mainFrame.setTitle("Moving Three Balls");
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
	}
	public static void main(String[] args)
	{
		ThreeBalls randomStop=new ThreeBalls();
		Thread startMoving =new Thread(randomStop);
		startMoving.start();
	}
}
