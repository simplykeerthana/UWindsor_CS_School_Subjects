import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.accessibility.*;
import java.util.*;
import java.awt.event.*;
public class TurtoiseAndHare extends JFrame
{
    private ImageIcon hare;
    private ImageIcon tortoise;
    private ImageIcon start;
    private ImageIcon finish;
    int turtle_x = 0;
    int turtle_y = 159;
    int hare_x = 0;
    int hare_y = 35;
    int ha_w;
    int ha_h;
    int to_w;
    int to_h;
    float fastplod;
    float slip;
    float slowplod;
    float sleep;
    float bighop;
    float bigslip;
    float smallhop;
    float smallslip;
    Display p;
    Move mo;
    TnHThread tornhare;
    Button StartB;
    Button Stop;
    String message = "Who will win this amazing race!";
    public TurtoiseAndHare()
    {
        setTitle ("Tortoise and the Hare race!");
        setSize(1000, 405);
        mo = new Move(this);
        Container contentPane = this.getContentPane();
        getContentPane().setLayout(new BorderLayout());
        p = new Display();
       
        JPanel q = new JPanel();
        q.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        StartB = new Button("  Start  ");
        q.add(StartB);
        JPanel m = new JPanel();
        m.setBorder(BorderFactory.createLineBorder(Color.BLUE));
        m.setBackground(Color.getHSBColor(25,55,45));
        JLabel msg = new JLabel(message);
        m.add(msg);
        hare = new ImageIcon(getClass().getResource("bunnyrunning.gif"));
        tortoise = new ImageIcon(getClass().getResource("turtlerunning.gif"));
        start = new ImageIcon(getClass().getResource("start.png"));
        finish = new ImageIcon(getClass().getResource("finish.jpg"));
        contentPane.add(p, BorderLayout.CENTER);
        contentPane.add(q, BorderLayout.SOUTH);
        contentPane.add(m, BorderLayout.NORTH);
        Handler handler = new Handler();
        StartB.addActionListener(handler);
    }
    class Display extends Canvas
    {
        public void paint(Graphics  g)
        {
            ha_w = hare.getIconWidth();
            ha_h = hare.getIconHeight();
            to_w = tortoise.getIconWidth();
            to_h = tortoise.getIconHeight();
           // super.paint(g);
           for (int i = 0; i < 780; i++)
           {
               tortoise.paintIcon(p, g, i+5, turtle_y);
 
           };
            tortoise.paintIcon(p, g, turtle_x, turtle_y);
            hare.paintIcon(p, g, hare_x, hare_y);
            start.paintIcon(p, g, 125, 0);
            finish.paintIcon(p, g, 850, 0);
            g.drawString(message,170,55);
        }
    } //end of Display
    public void raicing(int animal, int x, int y)
    {
        if (animal == 1)
        {
            hare_x = x;
            hare_y = y;
            message = "the hare is at position " + hare_x;
        }
        if (animal == 2)
        {
            turtle_x = x;
            turtle_y = y;
            message = "The Turtoise is at position " + turtle_x;
        }
        p.repaint();
    }//END of Raicing
    public static void main(String[] args)
    {
        // TODO Auto-generated method stub
        TurtoiseAndHare myrace = new TurtoiseAndHare();
        myrace.setVisible(true);
  myrace.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }//END of Main
    class Handler implements ActionListener
    {
        public void actionPerformed (ActionEvent e)
        {
            int send;
            Random startNo = new Random();
            send = 1 + startNo.nextInt(2);
            //start button was pressed
            if (send == 1)
            {
                TnHThread h = new TnHThread(send,mo);
                h.start();
            }
            else if (send == 2)
            {
                TnHThread t = new TnHThread(send,mo);
                t.start();
            }
        }
    }
}
