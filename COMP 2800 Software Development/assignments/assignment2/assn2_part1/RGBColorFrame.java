import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

@SuppressWarnings("serial")
public class RGBColorFrame extends JFrame {
    private static final int FRAME_WIDTH = 300;
    private static final int FRAME_HEIGHT = 400;
    private JPanel colorPanel;
    private JSlider redSlider;
    private JSlider greenSlider;
    private JSlider blueSlider;
    private  JLabel r1 = new JLabel("");
    private JLabel g1 = new JLabel("");
    private  JLabel b1 = new JLabel("");
    public RGBColorFrame() {
        this.createComponents();
        this.setTitle("Slider for Colors");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    class ColorListener implements ChangeListener {
        @Override
        public void stateChanged(ChangeEvent e) {
            setFrameColor();
        }
    }

    private void createComponents() {
        this.createPanel();
        this.createSliders();
        this.setFrameColor();
    }

    private void createPanel() {
        this.colorPanel = new JPanel();
        this.add(this.colorPanel, BorderLayout.CENTER); // the color panel set to the center
    }

    private void createSliders()
    {
        ChangeListener listener = new ColorListener();

        this.redSlider = new JSlider(JSlider.HORIZONTAL,0, 255, 0);
        redSlider.setPaintLabels(true);
        this.redSlider.addChangeListener(listener);

        this.greenSlider = new JSlider(0, 255, 0);
        greenSlider.setPaintLabels(true);
        this.greenSlider.addChangeListener(listener);

        this.blueSlider = new JSlider(0, 255, 0);
        blueSlider.setPaintLabels(true);
        this.blueSlider.addChangeListener(listener);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(3, 2));

        controlPanel.add(new JLabel("Red"));
        controlPanel.add(this.redSlider);
        controlPanel.add(r1); // value
        controlPanel.add(new JLabel("Green"));
        controlPanel.add(this.greenSlider);
        controlPanel.add(g1); //value
        controlPanel.add(new JLabel("Blue"));
        controlPanel.add(this.blueSlider);
        controlPanel.add(b1); //value

        this.add(controlPanel, BorderLayout.SOUTH);
    }
    
    //sets the frame color and value

    private void setFrameColor() {
        int redValue = this.redSlider.getValue();
        r1.setText(Integer.toString(redValue));
        int greenValue = this.greenSlider.getValue();
        g1.setText(Integer.toString(greenValue));
        int blueValue = this.blueSlider.getValue();
         b1.setText(Integer.toString(blueValue));
        this.colorPanel.setBackground(new Color(redValue, greenValue, blueValue));
    }

    @SuppressWarnings("unused")
    public static void main(String[] args) {
        JFrame frame = new RGBColorFrame();
    }
}
