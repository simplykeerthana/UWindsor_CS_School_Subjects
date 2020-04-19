import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Color;
import java.awt.BasicStroke;
import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import javax.swing.BorderFactory;

public class GraphPanel extends JComponent
{

    private static final int WIDE = 1500;
    private static final int HIGH = 1500;
    private static final int RADIUS = 10; // size of the oval
    private ControlPanel control = new ControlPanel();
    private int radius = RADIUS;
    private Kind kind = Kind.Circular; // so its a oval shaped node
    private List<Node> nodes = new ArrayList<Node>();
    private List<Node> selected = new ArrayList<Node>();
    private List<Edge> edges = new ArrayList<Edge>();
    private Point mousePt = new Point(WIDE / 2, HIGH / 2);
    private Rectangle mouseRect = new Rectangle(); // to know if something is selected or not
    private boolean selecting = false; // selction of nodes

    public static void main(String[] args) throws Exception
    {
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                JFrame f = new JFrame("Directed Edges to Nodes");
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                GraphPanel gp = new GraphPanel();
                f.add(gp.control, BorderLayout.NORTH);
                f.add(new JScrollPane(gp), BorderLayout.CENTER);
                f.getRootPane().setDefaultButton(gp.control.defaultButton);
                f.pack();
                f.setLocationByPlatform(true);
                f.setVisible(true);
                
                JButton instructionPanel = new JButton("1. add nodes to add an edge ||| 2.  Click on two or more nodes to add a edge,  you can't add a edge if there is only one node in the graph ||| 3. To delete an edge click on the associated node to delete it");
                //instructionPanel.setBounds(50, 200, 150, 20);
                instructionPanel.setBackground(Color.YELLOW);
                f.add(instructionPanel, BorderLayout.SOUTH);
            }
        });
    }

    public GraphPanel()
    { // where all thee nodes and edges are added
        this.setOpaque(true);
        this.addMouseListener(new MouseHandler());
        this.addMouseMotionListener(new MouseMotionHandler());
    }
   
    @Override
    public Dimension getPreferredSize()
    { // size of the graph panel
        return new Dimension(WIDE, HIGH);
    }

    @Override
    public void paintComponent(Graphics g)
    { // for every node created there is a rectangle added to it
        g.setColor(new Color(0x00f0f0f0));
        g.fillRect(0, 0, getWidth(), getHeight());
        for (Edge e : edges) {
            e.draw(g);
        }
        for (Node n : nodes) {
            n.draw(g);
        }
        if (selecting) { // if the node is selected, draw a mini rectanglee over it
            g.setColor(Color.darkGray);
            g.drawRect(mouseRect.x, mouseRect.y,
                mouseRect.width, mouseRect.height);
        }
    }

    private class MouseHandler extends MouseAdapter {

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
        public void mousePressed(MouseEvent e) {
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
        { // shows content sensitive to the screeen
            control.popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private class MouseMotionHandler extends MouseMotionAdapter
    {

        Point delta = new Point(); // to move one or moree nodes to a new location

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
        return control; // a toolbar on top of the screen with all the available features as JRadioButton
    }

    private class ControlPanel extends JToolBar
    {
        private Action newNode = new NewNodeAction("New Node");
        private Action deleteNode = new DeleteAction("Delete Node");
        private Action addEdge = new ConnectAction("Add Edge");
        private Action deleteEdge = new DeleteAction("Delete Edge");
        private Action connect = new ConnectAction("Add Edge");
        private Action delete = new DeleteAction("Delete");
        private JButton defaultButton = new JButton(newNode);
        private JPopupMenu popup = new JPopupMenu();
        private JPopupMenu filePopup = new JPopupMenu();
        private JRadioButton delNode = new JRadioButton(deleteNode);
         private JRadioButton addEd = new JRadioButton(addEdge);
         private JRadioButton delEd = new JRadioButton(deleteEdge);
        private ButtonGroup g1 = new ButtonGroup();
        
        ControlPanel()
        {
            this.setLayout(new FlowLayout(FlowLayout.LEFT));
            this.setBackground(Color.lightGray);
            this.add(defaultButton);
            this.add(delNode);
            this.add(addEd);
            this.add(delEd);
            g1.add(delNode);
            g1.add(addEd);
            g1.add(delEd);
            popup.add(new JMenuItem(newNode));
            popup.add(new JMenuItem(connect));
            popup.add(new JMenuItem(delete));
        }
    }

    
//if the user want to add a edge to the screen the JRadioButton calls this function
    private class ConnectAction extends AbstractAction
    {
        public ConnectAction(String name)
        {
            super(name);
        }

        public void actionPerformed(ActionEvent e)
        {
            Node.getSelected(nodes, selected);
            if (selected.size() > 1)
            {
                for (int i = 0; i < selected.size() - 1; ++i)
                {
                    Node n1 = selected.get(i);
                    Node n2 = selected.get(i + 1);
                    edges.add(new Edge(n1, n2));
                }
            }
            repaint();
        }
    }

    // if the user want to delete a node and its associated edges to the screen the JRadioButton calls this function
    private class DeleteAction extends AbstractAction {

        public DeleteAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e)
        {
            ListIterator<Node> iter = nodes.listIterator();
            while (iter.hasNext()) {
                Node n = iter.next();
                if (n.isSelected())
                {
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


// if the user want to add a node to the screen the JRadioButton calls this function
    private class NewNodeAction extends AbstractAction {

        public NewNodeAction(String name) {
            super(name);
        }

        public void actionPerformed(ActionEvent e) {
            Node.selectNone(nodes);
            Point p = mousePt.getLocation();
            Node n = new Node(p, radius, Color.BLUE, kind);
            n.setSelected(true);
            nodes.add(n);
            repaint();
        }
    }
    

    private enum Kind {
        Circular
    }

    // for pair(s) of nodes, add an edge
    private static class Edge
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
            LineArrow line3 = new LineArrow(p1.x, p1.y, p2.x, p2.y, Color.RED, 1); // to draw an arrowed line
            line3.draw(g);
           // drawArrowHead(Graphics2D g2, )
           // g.setArrow(5,5);
        }
        
        
    }
    
    private static final Polygon ARROW_HEAD = new Polygon();

      static {
          ARROW_HEAD.addPoint(0, 0);
          ARROW_HEAD.addPoint(-5, -10);
          ARROW_HEAD.addPoint(5, -10);
      }

      public static class LineArrow {

          private final int x;
          private final int y;
          private final int endX;
          private final int endY;
          private final Color color;
          private final int thickness;

          public LineArrow(int x, int y, int x2, int y2, Color color, int thickness) {
              super();
              this.x = x;
              this.y = y;
              this.endX = x2 -10;
              this.endY = y2 -10;
              this.color = color;
              this.thickness = thickness;
          }

          public void draw(Graphics g) {
              Graphics2D g2 = (Graphics2D) g;

              // calculate the angle of the sate
              double angle = Math.atan2(endY - y, endX - x);

              g2.setColor(color);
              g2.setStroke(new BasicStroke(thickness));
              g2.drawLine(x, y, (int) (endX - 10 * Math.cos(angle)), (int) (endY - 10 * Math.sin(angle)));

              AffineTransform t1 = g2.getTransform();
              AffineTransform t2 = (AffineTransform) t1.clone();
              t2.translate(endX, endY);
              t2.rotate(angle - Math.PI / 2);
              g2.setTransform(t2);
              g2.fill(ARROW_HEAD);
              g2.setTransform(t1);
          }
      }
    
    

    private static class Node {

        private Point p;
        private int r;
        private Color color;
        private Kind kind;
        private boolean selected = false;
        private Rectangle b = new Rectangle();

        // create new node
        public Node(Point p, int r, Color color, Kind kind) {
            this.p = p;
            this.r = r;
            this.color = color;
            this.kind = kind;
            setBoundary(b);
        }

        // based on the node size calculate its rectangle boundary
        private void setBoundary(Rectangle b) {
            b.setBounds(p.x - r, p.y - r, 2 * r, 2 * r);
        }

        //draw a nodee
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

        //get the node location
        public Point getLocation() {
            return p;
        }

        public boolean contains(Point p) {
            return b.contains(p);
        }

        public boolean isSelected() {
            return selected;
        }

        // mark the selcted nodes as selected
        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        // check if selected nodes to a list
        public static void getSelected(List<Node> list, List<Node> selected) {
            selected.clear();
            for (Node n : list) {
                if (n.isSelected()) {
                    selected.add(n);
                }
            }
        }

        // that no nodes were selected
        public static void selectNone(List<Node> list) {
            for (Node n : list) {
                n.setSelected(false);
            }
        }

        // only one node was seleected , so can't add a edge
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

    // when dragging a node with a rectangle echeck if the a reectangle boundary contains a node or not
        public static void selectRect(List<Node> list, Rectangle r)
        {
            for (Node n : list) {
                n.setSelected(r.contains(n.p));
            }
        }

        //  toggle arre the selected node status, check if the clicked point contains a node or not
        public static void selectToggle(List<Node> list, Point p)
        {
            for (Node n : list) {
                if (n.contains(p)) {
                    n.setSelected(!n.isSelected());
                }
            }
        }
        
        // update the position of each nodes when moved
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


