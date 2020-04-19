
import java.lang.reflect.Method;
import java.net.Socket;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.*;
public class client extends JFrame
{
    static JPanel ans=new JPanel();
    String filenames[];
    int count=0;
    static  DataOutputStream ii=null;
    static Socket socket = null;
    String message = null;
    client() throws Exception
    {
        super("lab9");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLayeredPane lp=new JLayeredPane();
        JPanel query=new JPanel();
        query.setBackground(Color.GRAY);
        query.setBounds(0, 0, 500, 35);
        lp.add(query,0,0);
        lp.add(ans,1,0);
        setLayout(new BorderLayout());
    
        JTextField ipid = new JTextField();
        ipid.setColumns(20);
        ipid.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e)
            {
                
                String myip=ipid.getText();
                int mysock=Integer.parseInt(JOptionPane.showInputDialog("Which socket should I connect to ?"));
                
                FileOutputStream fos=null;
                
                try {
                    socket = new Socket(myip, mysock);
                }
                catch(Exception ex)
                {}
                DataInputStream os=null;
                try {
                    os = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                try {
                    count = os.readInt();
                    System.out.println("number of files: "+count);
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
                for(int i=0;i<count;i++)
                {
                    byte[] b = new byte[1000];
                    String FILE_TO_RECEIVE = "";
                    try {
                        //get name
                        message = os.readUTF();
                        System.out.println("Received: "+ message);
                        FILE_TO_RECEIVE += message;
                        //get size
                        int size = os.readInt();
                        //get file
                        b = new byte[size];
                        os.read(b);
                        
                        File file = new File(FILE_TO_RECEIVE);
                        FileOutputStream out =  new FileOutputStream(file);
                        out.write(b);
                        out.flush();
                        
                        out.close();
                            
                    } catch (IOException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                }
               
            
            //update the panel
            
            try {
                String name = message.replace(".class","").trim();
                Class newC = Class.forName(name);
                Object cls = newC.newInstance();
              //  Method m = newC.getDeclaredMethod("getMyField");
                
               // m.setAccessible(true);
                ans.removeAll();
                ans=(JPanel)cls;
                ans.revalidate();
                ans.setBounds(0,30,500,675);
                lp.add(ans,1,0);
               
                
                
                }
                catch(Exception ex)
                {
                    System.out.println("help!");
                    ex.printStackTrace();
                }
            
            
            }
        });
        
            
        query.add(ipid);
        getContentPane().add(lp, BorderLayout.CENTER);
        
        setSize(500,700);
        setVisible(true);
    }
    public static void main(String[] args) throws Exception
    {
        client o1=new client();

    }
  
}
