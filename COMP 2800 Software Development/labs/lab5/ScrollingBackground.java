import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class ScrollingBackground {
    
    private static String image_name;
    
    private static JButton toggleButton = new JButton("Toggle");
       private boolean rotateValue = false;

    public static void main(String[] args) {
        new ScrollingBackground();
        
        image_name = args[0];
    }

    public ScrollingBackground() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
             

                JFrame frame = new JFrame("Moving Image");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new BackgroundPane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                toggleButton.setBounds(50, 100, 150, 20);
                         toggleButton.setVisible(true);
                frame.add(toggleButton, BorderLayout.SOUTH);
                frame.setVisible(true);
                
            }
        });
    }

    public class BackgroundPane extends JPanel {

        private BufferedImage bg;
        private int yOffset = 0;
        private int yDelta = 4;

        public BackgroundPane() {
            try {
                bg = ImageIO.read(new File(image_name));
            
                System.out.println(">>>>> Width: " + bg.getWidth());
                System.out.println("Height: " + bg.getHeight());
                System.out.println("Image Type: " + bg.getType());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            Timer timer = new Timer(40, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    
                   if(!rotateValue)
                    {
                        yOffset += yDelta;
                    }
                    else
                    {
                        yOffset -= yDelta;
                    }
                    if (yOffset > getHeight()) {
                        yOffset = 0;
                    }
                    repaint();;
                }
            });
            timer.start();
            
            toggleButton.addActionListener(new ActionListener()
            {
                public void actionPerformed(ActionEvent e)
                {
                    rotateValue = !rotateValue; // everytime the toggle button is clicked  it would move
                
                    System.out.println("The toggle button has been clicked:");
                }
            });

        }

        @Override
        public Dimension getPreferredSize()
        {
            return bg == null ? new Dimension(200, 200) : new Dimension(bg.getWidth(), bg.getHeight());
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                Graphics2D g2d = (Graphics2D) g.create();

                int xPos = (getWidth() - bg.getWidth()) / 2;
                int yPos = yOffset;

                while (yPos > 0) {
                    yPos -= bg.getHeight();
                    g2d.drawImage(bg, xPos, yPos, this);
                }

                yPos = yOffset;
                while (yPos < getHeight()) {
                    g2d.drawImage(bg, xPos, yPos, this);
                    yPos += bg.getHeight();
                }

                g2d.dispose();
            }
        }
    }
}
