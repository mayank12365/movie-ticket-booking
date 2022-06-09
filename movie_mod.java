import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import java.awt.Frame;
import java.awt.event.*;
import java.io.*;
import java.sql.*;


public class movie_mod extends JFrame  implements ActionListener
{    JFrame f=new JFrame();
	 JLabel lb1=new JLabel();
	JLabel Label1 = new JLabel("Label1 ");
	
	
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbname,lbdir,lbstar,lbdate,lbduration;
	JTextField txtname,txtdir,txtstar,txtdate,txtduration;
     JButton	modify,show;
     String d;
     String tname,tdir,tstar,tdate,tduration;
     
    movie_mod()
    {
    	super();
   
    	setLayout(new GridLayout(7,1));
    	setLocation(0,0);
    	setSize(700,500);
    	
	
		
		
       
         p1=new JPanel ();
         p1.setLayout(new FlowLayout(FlowLayout.LEFT));
         p2=new JPanel ();
         p2.setLayout(new FlowLayout(FlowLayout.LEFT));
         p3=new JPanel ();
         p3.setLayout(new FlowLayout(FlowLayout.LEFT));
         p4=new JPanel ();
         p4.setLayout(new FlowLayout(FlowLayout.LEFT));
         p5=new JPanel ();
         p5.setLayout(new FlowLayout(FlowLayout.LEFT));
          p6=new JPanel();
          p6.setLayout(new FlowLayout(FlowLayout.LEFT));
          p7=new JPanel();
          
    
          lbname=new JLabel("Name :",Label.RIGHT); 
          lbdir=new JLabel("Director :",Label.RIGHT);
          lbstar=new JLabel("Starring :",Label.RIGHT);
          
          lbdate=new JLabel("Release Date :",Label.RIGHT);
          lbduration  =new JLabel("Duration Time :",Label.RIGHT);
          txtname=new JTextField(15);
          txtdir=new JTextField(15);
          txtstar=new JTextField(15);
          txtdate=new JTextField(15);
          txtduration=new JTextField(15);
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
          show=new JButton("Show Records");
          show.addActionListener(this);
         
          modify=new JButton("modify");
          modify.addActionListener(this);
          
          p6.add(show);
          p7.add(modify);
          
          
          p6.add(lb1);
          add(p1);
          add(p2);
          add(p3);
          add(p4);
          add(p5);
          add(p6);
          add(p7);
          
  	
          setVisible(true);
    }
    
  
    public void actionPerformed(ActionEvent ae)
    {
  
    String src=ae.getActionCommand();
 
    if(src.equals(show.getLabel()))
    	{
    		try
    		{
    			String nm=txtname.getText();
    			accessDBMOD(nm);
    		}
    		catch(Exception e)
    		{
    			System.out.println("hello"+e);
    		}
    	}
    		else if(src.equals(modify.getLabel()))
    		{
    			try
    			{
    		accessDB();
    			}
    			catch(Exception e)
    			{
    				System.out.println("hey..."+e);
    			}
    		}
    	}
    
    void accessDB()throws Exception
    {   FileInputStream fis;
        File fing=new File(d);
    	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	String sql="UPDATE movie_info SET moviedirector=?,starcasting=?,releasedate=?,duration=?,image1=? WHERE name=?";
    	
    	PreparedStatement pstmt=conn.prepareStatement(sql);
    	pstmt.setString(1,txtdir.getText());
    	pstmt.setString(2,txtstar.getText());
    	pstmt.setString(3,txtdate.getText());
    	pstmt.setString(4,txtduration.getText());
    	System.out.println("a..........");
    	
    	fis=new FileInputStream(fing);
    	pstmt.setBinaryStream(5,(InputStream)fis,(int)(fing.length()));
    	pstmt.setString(6,txtname.getText());
    	pstmt.executeUpdate();
    	conn.close();
    	txtname.setText("");
    	txtdir.setText("");
    	txtstar.setText("");
    	txtdate.setText("");
    	txtduration.setText("");
    }	
    
    void accessDBMOD(String mname)
    {
    			try
    			{          byte[] bytes=null;
    					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
    					Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
    					 String sql = "SELECT * FROM movie_info WHERE name='"+mname+"'";
    					Statement st = conn.createStatement( ) ;
    					boolean hasResult = st.execute(sql) ;
    					if (hasResult)
    					{
    					ResultSet rs = st.getResultSet( ) ;
    					if ( rs != null)
    					
    					displayResultsmod(rs) ;
    					conn.close( ) ;
    			 		
    					}
    					
    		  	}    
    			catch(Exception e) 
    			{
    				System.out.println(e);
    			}
    }
    void displayResultsmod(ResultSet rs) throws SQLException
    {
    			while(rs.next( ))
    			{
    				tname =rs.getString("name");
    				txtname.setText(tname) ;
    				txtname.setEditable(false) ;
    				tdir=rs.getString("moviedirector");
    				txtdir.setText(tdir);
    				tstar=rs.getString("starcasting");
    				txtstar.setText(tstar) ;
    				tdate =rs.getString("releasedate");
    				txtdate.setText(tdate) ;
    				tduration=rs.getString("duration");
    				txtduration.setText(tduration);
    				byte[] bytes=null;
    				 bytes = rs.getBytes("image1");
			         Image image = f.getToolkit().createImage(bytes);
			         ImageIcon icon=new ImageIcon(image);
			         lb1.setIcon(icon);
			        
    			
    	         
    			}
    }
    public static void main(String args[])
    {
    	new movie_mod();
    }
     }
       
    


  


