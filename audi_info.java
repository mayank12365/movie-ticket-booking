import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class audi_info extends Frame implements ActionListener
{
	Panel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbno,lbseats,lbrows,lbspr,lbtype,lbsound;
	TextField txtno,txtseats,txtrows,txtspr,txttype,txtsound;
     JButton	btnSave,btnmodify;
    audi_info(String str)
    {
    	super(str);
    	setLayout(new GridLayout(7,1));
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
          
          lbno=new JLabel("Audi Number :",Label.RIGHT); 
          lbseats=new JLabel("Total Seats  :",Label.RIGHT);
          lbrows=new JLabel("Number of Rows:",Label.RIGHT);
          lbspr=new JLabel("Seats per Row :",Label.RIGHT);
          lbtype=new JLabel("Type of Movie :",Label.RIGHT);
          lbsound =new JLabel("sound quality:",Label.RIGHT);
          txtno=new TextField(15);
          txtseats=new TextField(15);
          txtrows=new TextField(15);
          txtspr=new TextField(15);
          txttype=new TextField(15);
          txtsound=new TextField(15);
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
          btnSave=new JButton("save Records");
          btnSave.addActionListener(this);
            p7.add(btnSave);
        
          add(p1);
          add(p2);
          add(p3);
          add(p4);
          add(p5);
          add(p6);
          add(p7);
          
          setBackground(Color.PINK);
           setVisible(true);
      
    }

    public void actionPerformed(ActionEvent ae)
    {
    	String src=ae.getActionCommand();
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
    {
    	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	String sql="INSERT INTO audi_info(audi_no,total_seats,total_rows,seats_per_row,type,sound_system)VALUES('"+txtno.getText()+"','"+txtseats.getText()+"','"+txtrows.getText()+"','"+txtspr.getText()+"','"+txttype.getText()+"','"+txtsound.getText()+"')";
    	Statement st=conn.createStatement();
    	st.executeUpdate(sql);
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
    	new audi_info("AUDITORIUM INFORMATION");
    }
    }


  


