/*
    Name: Keerthan Madhavan
    Class: COMP 2800 Software Development
    Program: Assignment 5
 */

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.Canvas;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.*;


public class GraphPanel extends JComponent implements Serializable
{
    private static final int WIDE = 1300;
    private static final int HIGH = 600;
    private static final int RADIUS = 10;
    private ControlPanel control = new ControlPanel();
    private int radius = RADIUS;
    private Kind kind = Kind.Circular;
    private static ArrayList<Node> nodes = new ArrayList<Node>(); // try saving the nodes and edges directly into a text file, and when reading it draw it fresh again
    private static ArrayList<Node> selected = new ArrayList<Node>();
    private static ArrayList<Edge> edges = new ArrayList<Edge>();
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private Rectangle mouseRect = new Rectangle(); // to know if something is selected or not
    private boolean selecting = false;
    private static JPopupMenu edgePopup = new JPopupMenu();
     private static Rectangle imageBounds;
    private static  JFrame f = new JFrame("GraphPanel");
    
    private static  GraphPanel gp = new GraphPanel();
    
    public static void main(String[] args) throws Exception
    {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.add(gp.control, BorderLayout.NORTH);
                f.add(new JScrollPane(gp), BorderLayout.CENTER);
                f.getRootPane().setDefaultButton(gp.control.defaultButton);
                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);
                
                JButton instructionPanel = new JButton("Click on two or more nodes to add a edge you can't add a edge if there is only one node in the graph. To delete an edge click on the associated edge and click delete node button");
                
               
                instructionPanel.setBounds(50, 200, 150, 20);
                instructionPanel.setBackground(Color.YELLOW);
                f.add(instructionPanel, BorderLayout.SOUTH);
            }
        });
    }

    public GraphPanel()
    {
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }
   
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(WIDE, HIGH);
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Edge e : edges) {
            e.draw(g);
        }
        for (Node n : nodes) {
            n.draw(g);
        }
        if (selecting) {
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
    }

    private class MouseHandler extends MouseAdapter
    {
        @Override
        public void mouseReleased(MouseEvent e) {
            selecting = false;
            mouseRect.setBounds(0, 0, 0, 0); // draws a rectangel when the user tries to select everything in the graph
            if (e.isPopupTrigger()) {
                showPopup(e);
            }
            e.getComponent().repaint();
        }

        @Override
        public void mousePressed(MouseEvent e)
        {
            mousePt = e.getPoint();
            if (e.isShiftDown()) // checks if multiple nodes are selected
            {
                Node.selectToggle(nodes, mousePt);
            } else if (e.isPopupTrigger()) {
                Node.selectOne(nodes, mousePt);
                showPopup(e);
            } else if (Node.selectOne(nodes, mousePt)) {
                selecting = false;
            } else {
                Node.selectNone(nodes);
                selecting = true;
            }
            e.getComponent().repaint();
        }

        private void showPopup(MouseEvent e) // when right click is clicked anywherree on the screen
        {
            control.popup.show(e.getComponent(), e.getX(), e.getY());
           // control.edgePopup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter
    {

        Point delta = new Point();

        @Override
        public void mouseDragged(MouseEvent e) {
            if (selecting) {
                mouseRect.setBounds(
                    Math.min(mousePt.x, e.getX()),
                    Math.min(mousePt.y, e.getY()),
                    Math.abs(mousePt.x - e.getX()),
                    Math.abs(mousePt.y - e.getY()));
                Node.selectRect(nodes, mouseRect);
            } else //moving nodes to a new location
            {
                delta.setLocation(
                    e.getX() - mousePt.x,
                    e.getY() - mousePt.y);
                Node.updatePosition(nodes, delta);
                mousePt = e.getPoint();
            }
            e.getComponent().repaint();
        }
    }

    public JToolBar getControlPanel()
    {
        return control; // where all the buttons are
    }

    private class ControlPanel extends JToolBar {

       
        private Action newNode = new NewNodeAction("New Node");
        private Action clearAll = new ClearAction("Clear Graph");
        private Action deleteNode = new DeleteAction("Delete Node");
        private Action addEdge = new ConnectAction("Add Edge");
        private Action deleteEdge = new DeleteAction("Delete Edge");
        private Action connect = new ConnectAction("Add Edge");
        private Action delete = new DeleteAction("Delete");
        private JButton defaultButton = new JButton(newNode);
        private JComboBox kindCombo = new JComboBox();
        private JPopupMenu popup = new JPopupMenu();
        private Action newButton = new ClearAction("New File");
        private Action saveAction = new SaveAction("Save File");
        private Action openAction = new OpenAction("Open File");
        
       
        
        ControlPanel()
        {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.lightGray);
            this.add(new JButton(newButton));
            this.add(new JButton(saveAction));
            this.add(new JButton(openAction));
            this.add(defaultButton);
             this.add(new JButton(deleteNode));
            this.add(new JButton(addEdge));
            this.add(new JButton(deleteEdge));
            this.add(new JButton(clearAll));
            popup.add(new JMenuItem(newNode));
            popup.add(new JMenuItem(connect));
            popup.add(new JMenuItem(delete));
            edgePopup.add(new JMenuItem(deleteEdge));

        }

   
    }

    private class ClearAction extends AbstractAction {

        public ClearAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            nodes.clear();
            edges.clear();
            repaint();
        }
    }
    
    private class SaveAction extends AbstractAction implements Serializable {

          public SaveAction(String name) {
              super(name);
          }

          public void actionPerformed(ActionEvent e) {
             
              save();

             
          }
        
        public void save()
        {
           
            try{
                    FileOutputStream file = new FileOutputStream("graph.ser");
                    ObjectOutputStream out = new ObjectOutputStream(file);
                      
                    // Method for serializing a Item
                    
                    out.writeObject(edges);
                    out.writeObject(nodes);
                      
                    out.close();
                    file.close();
                    
                        JPanel p = new JPanel();
                        JOptionPane.showMessageDialog(p,"Graph has been saved as \"graph.ser\"");
            }
            catch(IOException ex)
            {
                JPanel p = new JPanel();
                JOptionPane.showMessageDialog(p,"Error: Graph couldn't be saved");
            }
            
            
        }
      }
    
    private class OpenAction extends AbstractAction  implements Serializable{

             public OpenAction(String name) {
                 super(name);
             }

             public void actionPerformed(ActionEvent e) {
                 open();
                
                 }
            
            public void open()
            {
                          
                        try
                        {   nodes.clear(); // clears all the current data
                            edges.clear();
                            gp.repaint();
                        
                        
                            FileInputStream file = new FileInputStream("graph.ser");
                           ObjectInputStream in = new ObjectInputStream(file);
                             
                           //Method for deserializing the items in the .ser file
                           edges= (ArrayList<Edge>)in.readObject();
                           nodes = (ArrayList<Node>)in.readObject();
                             
                           in.close();
                           file.close();
                             
                           System.out.println("Graph has been loaded ");
                            gp.repaint();
                        }
                        catch(Exception ex)
                        {
                            JPanel p = new JPanel();
                            JOptionPane.showMessageDialog(p,"Error: Graph couldn't be loaded because you have create a graph, save and then open");
                        }
                }
    }

    private class ConnectAction extends AbstractAction {

        public ConnectAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Node.getSelected(nodes, selected);
            if (selected.size() > 1) {
                for (int i = 0; i < selected.size() - 1; ++i) {
                    Node n1 = selected.get(i);
                    Node n2 = selected.get(i + 1);
                    edges.add(new Edge(n1, n2));
                }
            }
            repaint();
        }
    }

    private class DeleteAction extends AbstractAction {

        public DeleteAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            ListIterator<Node> iter = nodes.listIterator();
            while (iter.hasNext()) {
                Node n = iter.next();
                if (n.isSelected()) {
                    deleteEdges(n);
                    iter.remove();
                }
            }
            repaint();
        }

        private void deleteEdges(Node n) {
            ListIterator<Edge> iter = edges.listIterator();
            while (iter.hasNext()) {
                Edge e = iter.next();
                if (e.n1 == n || e.n2 == n) {
                    iter.remove();
                }
            }
        }
    }



    private class NewNodeAction extends AbstractAction {

        public NewNodeAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Node.selectNone(nodes);
            Point p = mousePt.getLocation();
           // Color color = control.hueIcon.getColor();
            Node n = new Node(p, radius, Color.BLUE, kind);
            n.setSelected(true);
            nodes.add(n);
            repaint();
        }
    }
    
    /**
     * The kinds of node in a graph.
     */
    private enum Kind {

        Circular
    }

    /**
     * An Edge is a pair of Nodes.
     */
    private static class Edge implements Serializable
    {

        private Node n1;
        private Node n2;

        public Edge(Node n1, Node n2) {
            this.n1 = n1;
            this.n2 = n2;
        }
        

        public void draw(Graphics g)
        {

            Point p1 = n1.getLocation();
            Point p2 = n2.getLocation();
            
          
            g.setColor(Color.darkGray);
           // g.drawLine(p1.x, p1.y, p2.x, p2.y);
            LineArrow line3 = new LineArrow(p1.x, p1.y, p2.x, p2.y, Color.RED, 2);
            line3.draw(g);
        
        }
        
        
    }
    
    private static final Polygon ARROW_HEAD = new Polygon();

      static {
          ARROW_HEAD.addPoint(0, 0);
          ARROW_HEAD.addPoint(-5, -10);
          ARROW_HEAD.addPoint(5, -10);
      }

      public static class LineArrow {

          private final int x, y, endX, endY, thickness;
          private final Color color;

          public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
              super();
              this.x = x;
              this.y = y;
              this.endX = x2 -10;
              this.endY = y2 -10;
              this.color = Color.RED;
              this.thickness = thickness;
          }

          public void draw(Graphics g) {
              Graphics2D g2 = (Graphics2D) g;

              // calculate the angle
              double angle = Math.atan2(endY - y, endX - x);
              g2.setColor(color);
              g2.setStroke(new BasicStroke(thickness));
              g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));
              
              AffineTransform tx1 = g2.getTransform();
              AffineTransform tx2 = (AffineTransform) tx1.clone();
              tx2.translate(endX, endY);
              tx2.rotate(angle - Math.PI / 2);
              g2.setTransform(tx2);
              g2.fill(ARROW_HEAD);
              g2.setTransform(tx1);
          }
      }
    
    
// A Node represents a node in a graph.
    private static class Node implements Serializable{

        private Point p;
        private int r;
        private Color color;
        private Kind kind;
        private boolean selected = false;
        private Rectangle b = new Rectangle();

        // create a node based on point
        public Node(Point p, int r, Color color, Kind kind) {
            this.p = p;
            this.r = r;
            this.color = color;
            this.kind = kind;
            setBoundary(b);
        }

        //Calculate this node's rectangular boundary.
        private void setBoundary(Rectangle b) {
            b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
        }

        // Draw this node.
        public void draw(Graphics g) {
            g.setColor(this.color);
            if (this.kind == Kind.Circular) {
                g.fillOval(b.x, b.y, b.width, b.height);
            }
            // draws a rectangle over the selected oval, so the user know that the node is selected
            if (selected) {
                g.setColor(Color.darkGray);
                g.drawRect(b.x, b.y, b.width, b.height);
            }
        }

        //Return this node's location.
        public Point getLocation() {
            return p;
        }

        //Return true if this node contains p.
        public boolean contains(Point p) {
            return b.contains(p);
        }

        //Return true if this node is selected.
        public boolean isSelected() {
            return selected;
        }

        //Mark the node as selected.
        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        //add all the selected nodes in a list
         public static void getSelected(List<Node> list, List<Node> selected) {
            selected.clear();
            for (Node n : list) {
                if (n.isSelected()) {
                    selected.add(n);
                }
            }
        }

        // if no nodes is selected
        public static void selectNone(List<Node> list) {
            for (Node n : list) {
                n.setSelected(false);
            }
        }

    // Select a single node; return true if not already selected.
        public static boolean selectOne(List<Node> list, Point p) {
            for (Node n : list) {
                if (n.contains(p)) {
                    if (!n.isSelected()) {
                        Node.selectNone(list);
                        n.setSelected(true);
                    }
                    return true;
                }
            }
            return false;
        }

      //Select each node in r.
        public static void selectRect(List<Node> list, Rectangle r) {
            for (Node n : list) {
                n.setSelected(r.contains(n.p));
            }
        }

        //Toggle each node in the list
        public static void selectToggle(List<Node> list, Point p) {
            for (Node n : list) {
                if (n.contains(p)) {
                    n.setSelected(!n.isSelected());
                }
            }
        }

     //Update each node's position by d .
        public static void updatePosition(List<Node> list, Point d) {
            for (Node n : list) {
                if (n.isSelected()) {
                    n.p.x += d.x;
                    n.p.y += d.y;
                    n.setBoundary(n.b);
                }
            }
        }
    }
}
