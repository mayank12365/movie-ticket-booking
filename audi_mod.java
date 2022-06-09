import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class audi_mod extends Frame implements ActionListener
{
	Panel p1,p2,p3,p4,p5,p6,p7,p8;
	JLabel lbno,lbseats,lbrows,lbspr,lbtype,lbsound;
	JTextField txtno,txtseats,txtrows,txtspr,txttype,txtsound;
     JButton	btnshow,btnmodify;
     String tno,tseats,trows,tspr,ttype,tsound;
    audi_mod()
    {
    	super();
    	setLayout(new GridLayout(8,1));
    	setLocation(0,0);
    	setSize(700,500);
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
          p6.setLayout(new FlowLayout(FlowLayout.LEFT));
          p7=new Panel();
          p7.setLayout(new FlowLayout(FlowLayout.LEFT));
          p8=new Panel();
          lbno=new JLabel("Audi Number  :",Label.RIGHT); 
          lbseats=new JLabel("Total Seats   :",Label.RIGHT);
          lbrows=new JLabel("Number of Rows:",Label.RIGHT);
          lbspr=new JLabel("Seats per Row :",Label.RIGHT);
          lbtype=new JLabel("Type of Movie  :",Label.RIGHT);
          lbsound =new JLabel("Sound Quality :",Label.RIGHT);
          txtno=new JTextField(15);
          txtseats=new JTextField(15);
          txtrows=new JTextField(13);
          txtspr=new JTextField(14);
          txttype=new JTextField(15);
          txtsound=new JTextField(15);
          p1.add(lbno);
          p1.add(txtno);
          p2.add(lbseats);
          p2.add(txtseats);
          p3.add(lbrows);
          p3.add(txtrows);
          p4.add(lbspr);
          p4.add(txtspr);
          p5.add(lbtype);
          p5.add(txttype);
          p6.add(lbsound);
          p6.add(txtsound);
          btnshow=new JButton("show Records");
          btnshow.addActionListener(this);
          btnmodify=new JButton("Modify Records");
          btnmodify.addActionListener(this);
          p7.add(btnshow);
          p8.add(btnmodify);
          add(p1);
          add(p2);
          add(p3);
          add(p4);
          add(p5);
          add(p6);
          add(p7);
          add(p8);
          setBackground(Color.CYAN);
           setVisible(true);
      
    }

    public void actionPerformed(ActionEvent ae)
    {
    	String src=ae.getActionCommand();
    	if(src.equals(btnshow.getLabel()))

    		{
    		  try
    		  {
    			  String tno=txtno.getText();
    		  
    			  show(tno);
    		  }
    		  catch(Exception e)
    		  {
    			  System.out.println("arpan"+e);
    		  }
    		}
    	else if(src.equals(btnmodify.getLabel()))
    		
    	{
    		try
    		{
    			accessDBMOD();
    		}
    		catch(Exception e)
    		{
    			System.out.println("hello"+e);
    		}
    	}
    }
    void show(String mn)
    {
    			try
    			{
    					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
    					Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
    					String sql = "SELECT * FROM audi_info WHERE audi_no='"+mn+"'";
    					Statement st = conn.createStatement( ); 
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
    				System.out.println("heelo"+e);
    			}
    }
    void displayResultsmod(ResultSet rs) throws SQLException
    {
    			while(rs.next( ))
    			{
    				tno =rs.getString("audi_no");
    				txtno.setText(tno) ;
    				txtno.setEditable(false) ;
    				tseats=rs.getString("total_seats");
    				txtseats.setText(tseats);
    				trows=rs.getString("total_rows");
    				txtrows.setText(trows) ;
    				tspr =rs.getString("seats_per_row");
    				txtspr.setText(tspr) ;
                    ttype=rs.getString("type");
    				txttype.setText(ttype);
    				tsound=rs.getString("sound_system");
    				txtsound.setText(tsound);  				
    	
    			
    	         
    			}
    }
    void accessDBMOD()throws Exception
    {
        	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	String sql="UPDATE audi_info SET total_seats=?,total_rows=?,seats_per_row=?,type=?,sound_system=? WHERE audi_no=?";
    	PreparedStatement pstmt=conn.prepareStatement(sql);
		pstmt.setString(1,txtseats.getText());
		pstmt.setString(2,txtrows.getText());
		pstmt.setString(3,txtspr.getText());
		pstmt.setString(4,txttype.getText());
    	pstmt.setString(5, txtsound.getText());
		pstmt.setString(6,txtno.getText());
        pstmt.executeUpdate();
    	conn.close();
    	txtno.setText("");
    	txtseats.setText("");
    	txtrows.setText("");
    	txtspr.setText("");
    	txttype.setText("");
    	txtsound.setText("");
    	System.out.println("hey");
    	}
    public static void main(String args[])
    {
    	new audi_mod();
    }
    }


  


