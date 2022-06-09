import java.awt.*;
import java.awt.event.*;
import java.sql.*;

import javax.swing.*;
public class booking extends JFrame implements ActionListener
{
	JPanel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbid,lbseatno,lbdate,lbmovie,lbst,lbstatus;
	JTextField txtid,txtseatno,txtdate,txtmovie,txtst,txtstatus;
     JButton	btnSave,btnshow;
    booking(String str)
    {
    	super(str);
    	setLayout(new GridLayout(8,1));
    	setLocation(0,0);
    	setSize(700,500);
         addWindowListener(new WindowAdapter()
   
         		{
	   public void windowClosing(WindowEvent we)
	   {
		   setVisible(false);
		   System.exit(0);
		   }
       });
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
      
          lbid=new JLabel("Id  :",Label.RIGHT); 
          lbseatno=new JLabel("Seat Number  :",Label.RIGHT);
          lbdate=new JLabel("Date  :",Label.RIGHT);
          lbst=new JLabel("Show Time  :",Label.RIGHT);
          lbmovie=new JLabel(" Movie :",Label.RIGHT);
          lbstatus =new JLabel("Status  :",Label.RIGHT);
          txtid=new JTextField(15);
          txtseatno=new JTextField(15);
          txtdate=new JTextField(15);
          txtst=new JTextField(15);
          txtmovie=new JTextField(15);
          txtstatus=new JTextField(15);
          p1.add(lbid);
          p1.add(txtid);
          p2.add(lbseatno);
          p2.add(txtseatno);
          p3.add(lbdate);
          p3.add(txtdate);
          p4.add(lbst);
          p4.add(txtst);
          p5.add(lbmovie);
          p5.add(txtmovie);
          p6.add(lbstatus);
          p6.add(txtstatus);
          btnSave=new JButton("Save Records");
          btnSave.addActionListener(this);
          btnshow=new JButton("Show Records");
          btnshow.addActionListener(this);
          p7.add(btnSave);
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
    	String sql="INSERT INTO booking(id,seatno,date,movie,show_time,status)VALUES('"+txtid.getText()+"','"+txtseatno.getText()+"','"+txtdate.getText()+"','"+txtmovie.getText()+"','"+txtst.getText()+"','"+txtstatus.getText()+"')";
    	Statement st=conn.createStatement();
    	st.executeUpdate(sql);
    	conn.close();
    	txtid.setText("");
    	txtseatno.setText("");
    	txtdate.setText("");
    	txtmovie.setText("");
    	txtst.setText("");
    	txtstatus.setText("");
    	System.out.println("hey");
    	}
    public static void main(String args[])
    {
    	new booking("BOOKING...");
    }
    }


  



