import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
public class select  extends Frame implements ActionListener,ItemListener 
{
	Frame f=new Frame();
	Panel p1,p2;
	Choice ch,ch1;
	JButton b1,b2;
	String tempname,tempaudi,tempdate,temptime;
	JLabel r,n,msg,msg1,m2,m3,m4,m5,m6,m7,lb1;
     select(String s1)
     {
    	 super();
    	 setSize(700,500);
    	 setLayout(null);
    	 addWindowListener(new WindowAdapter()
 		{
 			public void windowClosing(WindowEvent ae)
 			{
 				setVisible(false);
 				//System.exit(0);
 			}
 		
 		});
    //	 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	 p1=new Panel();
    	 p1.setLayout( null);
    	 p2=new Panel();
    	 p2.setLayout(null);
    	 n=new JLabel("");
         r=new JLabel("Rs. ");
         lb1=new JLabel();
         msg=new JLabel("MOVIE");
         m2=new JLabel("AUDI");
         m3=new JLabel("TIME");
         m4=new JLabel("DATE");
         msg1=new JLabel(s1);
         msg.setBounds(10,50,50,50);
         msg1.setBounds(60,50,200,50);
         m5=new JLabel();
         m2.setBounds(10,70,50,50);
         m5.setBounds(60,70,200,50);
         m6=new JLabel();
         m3.setBounds(10,90,50,50);
         m6.setBounds(60,90,200,50);
         m7=new JLabel();
         m4.setBounds(10,110,50,50);
         m7.setBounds(60,110,200,50);
         lb1.setBounds(0,250,250,350);
         show1(s1);
        
      accessDB(s1);
    	 ch=new Choice();
    	 ch.add("SELECT CLASS");
    	 ch.add("SILVER(160)");
    	 ch.add("GOLD(170)");
    	 ch.select(0);
    	
    	 ch1=new Choice();
    	 ch1.add("QTY");
    	 ch1.add("1"); 
    	 ch1.add("2");
    	 ch1.add("3");
    	 ch1.add("4");
    	 ch1.add("5");
    	 ch1.add("6");
    	 ch1.add("7");
    	 ch1.add("8");
    	 ch1.add("9");
    	 ch1.add("10");
    	 ch1.select(0);
    	 
    	 ch1.addItemListener(this);
    	 ch.setBounds(10,50,150,50);
    	 p1.add(ch);
    	 ch1.setBounds(170,50,100,50);
    	 p1.add(ch1);
    	 r.setBounds(280,35,50,50);
    	 n.setBounds(300,35,50,50);
    	 System.out.println("hello");
    	 p1.add(r);
    	 p1.add(n);
    	 //p1.add(b1);
    	 //b1=new JButton("NETDUE");
    	 //b1.addActionListener(this);
    	 b2=new JButton("SEAT SELECTION");
    	 b2.addActionListener(this);
   
    	 b2.setBounds(200,150,150,30);
    	 p1.add(b2);
    	 p2.add(msg);
    	 p2.add(msg1);
    	 p2.add(m2);
    	 p2.add(m5);
    	 p2.add(m3);
    	 p2.add(m6);
    	 p2.add(m4);
    	 p2.add(m7);
    	 p2.add(lb1);
    	 p1.setBounds(0,0,500,500);
    	 p1.setBackground(Color.CYAN);
    	 p2.setBounds(500,0,200,500);
    	 p2.setBackground(Color.PINK);
    	 add(p1);
    	 add(p2);
    	 setVisible(true);
     }
 
     public void itemStateChanged(ItemEvent ie)
     {
    	 Choice src=(Choice) ie.getSource();
    	String item=src.getSelectedItem();
    	 if(item.equals(ch1.getSelectedItem()))
    	 {
    	 System.out.println("hie..");
    	 if(ch.getSelectedItem()=="SILVER(160)")
    	 {
    	
    	int	 n1=ch1.getSelectedIndex()*160;
    		n.setText(String.valueOf(n1)); 
    	
    	 }
    	 if(ch.getSelectedItem()=="GOLD(170)")
    	 {
    		 int	 n1=ch1.getSelectedIndex()*170;
     		n.setText(String.valueOf(n1)); 
    	 }
    	 
    	/* try
    		 {
    			 access();
    		 }
    		 catch(Exception e)
    		 {
    			 System.out.println("hyeee"+e);
    		 }*/
    	 }
     }
     
     public void actionPerformed(ActionEvent ae)
     { 
    	 System.out.println("hey...........");
    	 String src=ae.getActionCommand();
    	 
    	 if(src.equals(b2.getLabel()))
    	 {
    		 this.setVisible(false);
    		 
    		 new seat_select(msg1.getText(),ch.getSelectedItem(),ch1.getSelectedItem());
    	 }
     }
     
     void show1(String mname)
     {
    	 try
    	 {
    	 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	 	
    		//System.out.println("movie=="+ mname);
    	 	String sql="SELECT * FROM timings WHERE movie='"+mname+"'";
    	 	Statement st=conn.createStatement();
    	    boolean hasResult=st.execute(sql);
    	    if(hasResult)
    	    {
    	    	ResultSet rs = st.getResultSet( ) ;
    	    	
    	    
    	    if ( rs != null)
    	    
    	    	displayResultsmod(rs);
    	    }
    	    conn.close();
    		
    	}
    	catch(Exception e) 
    	{
    	System.out.println("hiii"+e);	
    	}
    	}
    	void displayResultsmod(ResultSet rs) throws SQLException
    	{
    	while(rs.next( ))
    	{
    	tempname =rs.getString("movie");
    	msg1.setText(tempname);
    	tempaudi=rs.getString("audi");
    	m5.setText(tempaudi);
    	tempdate=rs.getString("show_time");
    	m6.setText(tempdate);
    	temptime=rs.getString("show_d");
    	m7.setText(temptime);
    	//System.out.println("movie==");

    	}
    	}

    	void accessDB(String mname)
        {
       	 try
       	 {
       	 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
       	 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
       	 	System.out.println("movie==>>"+ mname);
       	 	
       	 	String sql="SELECT image1 FROM movie_info WHERE name='"+mname+"'";
       	 	Statement st=conn.createStatement();
       	 System.out.println("movie==>>"+ mname);
       	  //  boolean hasResult=st.execute(sql);
       	boolean hasResult= st.execute(sql);
       	
       	    if(hasResult)
       	    {
       	    	ResultSet rs1 = st.getResultSet( ) ;
       	    	
       	    
       	    if ( rs1!= null)
       	    
       	    	displayResultsmod1(rs1);
       	    }
       	    conn.close();
       		
       	}
       	catch(Exception e) 
       	{
       	System.out.println("heeeeeeellllloooo----"+e);	
       	}
       	}
       	void displayResultsmod1(ResultSet rs1) throws SQLException
       	{
       	while(rs1.next( ))
       	{
       
       		byte[] bytes=null;
			 bytes = rs1.getBytes("image1");
			 
	         Image image = f.getToolkit().createImage(bytes);
	         ImageIcon icon=new ImageIcon(image);
	         lb1.setIcon(icon);
	        
       		
       		
       	}
       	}

       	/*void access()throws Exception
        {
        	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
        	String sql="INSERT INTO select(class,quantity)VALUES(?,?)";
        	PreparedStatement pstmt=conn.prepareStatement(sql);
       	pstmt.setString(1,ch.getSelectedItem());
       	pstmt.setString(2,ch1.getSelectedItem());
           pstmt.executeUpdate();
       	conn.close();

       	
       	System.out.println("hey");
       	}*/
        
        
     
     
     
     
     
     
     
   /*  public  static void main(String arg[])
     {
    	 new select();
     }*/
}
