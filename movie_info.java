import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import java.awt.Frame;
import java.awt.event.*;
import java.io.*;
import java.sql.*;

class imageLoad extends Canvas
{
	Image img;
	public imageLoad(Image img) 
		{
			this.img = img;
		}
	public void paint (Graphics g) 
	{
			if (img != null)
				{
					g.drawImage(img, 0, 0, 150, 150, this);
				}
	}

	
	public void setImage (Image img)
	{
		this.img = img;
	}
}

public class movie_info extends Frame  implements ActionListener
{

	JLabel Label1 = new JLabel("Label1 ");
	JButton Button1 = new JButton("Browse...");

	Image Image1;
	imageLoad Canvas1;
	FileDialog fd = new FileDialog(this,"Open", FileDialog.LOAD);
	Panel p1,p2,p3,p4,p5,p6;
	JLabel lbname,lbdir,lbstar,lbdate,lbduration;
	TextField txtname,txtdir,txtstar,txtdate,txtduration;
     JButton	btnSave;
     String d;
     
    movie_info(String str)
    {
    	super(str);
   
    	setLayout(new GridLayout(6,1));
    	setLocation(0,0);
    	setSize(1000,1000);
    	Canvas1 = new imageLoad(null);
		Canvas1.setSize(150,150);
		
		
        addWindowListener(new WindowAdapter()
         					{
        	 					public void windowClosing(WindowEvent we)
        	 								{
        	 								setVisible(false);
        	 							
        	 								}
         					});
         p1=new Panel ();
         p1.setLayout(new FlowLayout(FlowLayout.LEFT));
         p2=new Panel ();
         p2.setLayout(new FlowLayout(FlowLayout.LEFT));
         p3=new Panel ();
         p3.setLayout(new FlowLayout(FlowLayout.LEFT));
         p4=new Panel ();
         p4.setLayout(new FlowLayout(FlowLayout.LEFT));
         p5=new Panel ();
         p5.setLayout(new FlowLayout(FlowLayout.LEFT));
          p6=new Panel();
    
          lbname=new JLabel("Name :",Label.RIGHT); 
          lbdir=new JLabel("Director :",Label.RIGHT);
          lbstar=new JLabel("Starring :",Label.RIGHT);
          
          lbdate=new JLabel("Release Date :",Label.RIGHT);
          lbduration  =new JLabel("Duration(Hours) :",Label.RIGHT);
          txtname=new TextField(15);
          txtdir=new TextField(15);
          txtstar=new TextField(15);
          txtdate=new TextField(15);
          txtduration=new TextField(15);
          p1.add(lbname);
          p1.add(txtname);
          p2.add(lbdir);
          p2.add(txtdir);
          p3.add(lbstar);
          p3.add(txtstar);
          p4.add(lbdate);
          p4.add(txtdate);
          p5.add(lbduration);
          p5.add(txtduration);
          btnSave=new JButton("save Records");
          btnSave.addActionListener(this);
          Button1.addActionListener(this);
          
          p6.add(btnSave);
          p1.add(Canvas1);
          p1.add(Button1);
          
          add(p1);
          add(p2);
          add(p3);
          add(p4);
          add(p5);
          add(p6);
          setBackground(Color.PINK);
          //add(Canvas1);
  	
          setVisible(true);
    }
    
    void imageload () 
	{
			fd.show();
			if(fd.getFile() == null)
			{
				Label1.setText("You have not chosen any image files yet");
			}
			else
			{
					 d = (fd.getDirectory() + fd.getFile());
					 System.out.println(d);
					 
					Toolkit toolkit = Toolkit.getDefaultToolkit();
					Image1 = toolkit.getImage(d);
					Canvas1.setImage(Image1);
					Canvas1.repaint();
			}	
    
    
	}
    public void actionPerformed(ActionEvent ae)
    {
  
    String src=ae.getActionCommand();
   // Button b = (Button)ae.getSource();
    if(src.equals("Browse..."))
    {
    try
    {
    imageload();
    }
    catch(Exception e)
    {
    	System.out.println("helloooo"+e);
    }
    }
    if(src.equals(btnSave.getLabel()))
    	{
    		try
    		{
    			accessDB();
    		}
    		catch(Exception e)
    		{
    			System.out.println("hello"+e);
    		}
    	}
    }
    void accessDB()throws Exception
    {   FileInputStream fis;
        File fing=new File(d);
    	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	
    	String sql="INSERT INTO movie_info(name,moviedirector,starcasting,releasedate,duration,image1)VALUES(?,?,?,?,?,?)";
    	
    	PreparedStatement pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1,txtname.getText());
    	pstmt.setString(2,txtdir.getText());
    	pstmt.setString(3,txtstar.getText());
    	pstmt.setString(4,txtdate.getText());
    	pstmt.setString(5,txtduration.getText());
    	fis=new FileInputStream(fing);
    	pstmt.setBinaryStream(6,(InputStream)fis,(int)(fing.length()));
    
    	pstmt.executeUpdate();
    	System.out.println("hey......");
    	
    	conn.close();
    	
    	
    	txtname.setText("");
    	txtdir.setText("");
    	txtstar.setText("");
    	txtdate.setText("");
    
    	txtduration.setText("");
    	System.out.println("hey");
    	}
    public static void main(String args[])
    {
    	new movie_info("MOVIE INFORMATION");
    }
     }
       
    


  


