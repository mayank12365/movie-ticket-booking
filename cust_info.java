import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;

public class cust_info extends Frame implements ActionListener
{
	
Panel p1,p2,p3,p4,p5,p6;
 JLabel lbname,lbdob,lbphno,lbid,msg;
TextField txtname,txtdob,txtphno,txtid;
Button Save;
char ch='@';
int in;
cust_info(String str)
{
	super(str);
	setSize(700,500);
	setLayout( new GridLayout(6,1));
	
	p1=new Panel();
	p1.setLayout(new FlowLayout(FlowLayout.LEFT));
	p2=new Panel();
	p2.setLayout(new FlowLayout(FlowLayout.LEFT));
	p3=new Panel();
	p3.setLayout(new FlowLayout(FlowLayout.LEFT));
	p4=new Panel();
	p4.setLayout(new FlowLayout(FlowLayout.LEFT));
	p5=new Panel();
	p5.setLayout(new FlowLayout(FlowLayout.LEFT));
	p6=new Panel();
	lbname=new JLabel("Customer Name:",Label.RIGHT);
	lbdob=new JLabel("Date of Birth :",Label.RIGHT);
	lbphno=new JLabel("Phone No    :",Label.RIGHT);
	lbid=new JLabel("Email Id      :",Label.RIGHT);
	msg=new JLabel("",Label.RIGHT);
	txtname=new TextField(15);
	txtdob=new TextField(15);
	txtphno=new TextField(15);
	txtid=new TextField(15);
	p1.add(lbname);
	p1.add(txtname);
	p2.add(lbdob);
	p2.add(txtdob);
	p3.add(lbphno);
	p3.add(txtphno);
	p4.add(lbid);
	p4.add(txtid);
	Save=new Button("Save");
    Save.addActionListener(this);
    p5.add(Save);
    p6.add(msg);
	add(p1);
	add(p2);
	add(p3);
	add(p4);
	add(p5);
	add(p6);
	setBackground(Color.CYAN);
	 setVisible(true);
}

	public void actionPerformed(ActionEvent ae)
	{
		String src=ae.getActionCommand();
		
		if(src.equals(Save.getLabel()))
		{
			
			try
			{
				System.out.println("hello   " +src);
				
				accessDB();
			}
			catch(Exception e)
			{
				System.out.println(" Hello ===" +e);
			}
		}
	}
	
void accessDB()throws Exception
{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
		
		String sql="INSERT INTO cust_info(name,dob,phno,id)VALUES(?,?,?,?)";
		
		String str= txtid.getText();
		
		in=str.indexOf(ch);
		
		if(in==-1)
		{  
		
			new warningDialog(this);
			txtname.setText("");
			txtdob.setText("");
			txtphno.setText("");
			txtid.setText("");
			System.out.println("Invalid Id");
			
		}
		else
		{
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		System.out.println("hello   ");
		pstmt.setString(1,txtname.getText());
		pstmt.setString(2,txtdob.getText());
		pstmt.setString(3,txtphno.getText());
		
		
			pstmt.setString(4,txtid.getText());
		
		
		
        pstmt.executeUpdate();
		conn.close();
		
		
		txtname.setText("");
		txtdob.setText("");
		txtphno.setText("");
		txtid.setText("");
		}
}
public static void main(String args[])
{
	new cust_info("CUSTOMER INFORMATION");
}}
class warningDialog extends JDialog
{
	JLabel msg;
	warningDialog( Frame parent)
	{
		super(parent,"",true);
		msg=new JLabel("Invalid Id");
		JPanel p=new JPanel();
		p.add(msg);
		setContentPane(p);
		setSize(100,100);
		setLocation(250,350);
		setVisible(true);
		
	}
}