import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.util.*;

public class multi_frame extends JFrame
{
    //private JPanel contentPane;
    /**
    	 *
    	 */
    private static final long serialVersionUID = 1L;

    JFrame frame;

    multi_frame()
    {
        frame = new JFrame("parent Frame");
        frame.setVisible(true); 

      
        
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

       JButton parent_button = new JButton("Create Child");
       
        // here you set the frame, size and size
        frame.add(parent_button);
        frame.setSize(500, 600); 
        frame.setLayout(null); 
      
       

        parent_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                
                 child_Frame(frame);

            }

        });

        parent_button.setBounds(200,150,90,50);
        parent_button.setFont(new Font("Microsoft YaHei UI", Font.BOLD, 12));
        parent_button.setBounds(200,150,90,50);
       // contentPane.add(parent_button);



    }

    public void child_Frame(JFrame frame2) 
    {
       //JFrame cFrame;
        //cFrame = new JFrame("Child Frame");

        JFrame  c_Frame = new JFrame("Child Frame");
        c_Frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        
                

        JButton child_button = new JButton("Color");
        child_button.setBounds(200,150,90,50);

        
        c_Frame.add(child_button);
        c_Frame.setSize(500, 600); 
        c_Frame.setLayout(null); 
        c_Frame.setVisible(true);


        child_button.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                int red, green, blue;
                red = (int)(Math.random()*256);
                green = (int)(Math.random()*256);
                blue = (int)(Math.random()*256);
                frame2.getContentPane().setBackground( new Color(red,green,blue));
                
            }

        });

    }


    public static void main(String[] args) 
    { 
       
        new multi_frame();
    } 

}
