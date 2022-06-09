import java.awt.event.*;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
public class User_info extends Frame implements ActionListener
{
	Panel p1,p2,p3,p4,p5,p6,p7;
	JLabel lbid,lbpass,lbname,lbage,lbsex,lbtype;
	TextField txtid,txtpass,txtname,txtage;
	JButton btnSave;
	Checkbox ch1,ch2;
	CheckboxGroup sex;
	Choice ch;
	User_info(String str)
	
	{
		super(str);
		setLayout(new GridLayout(7,1));
		setLocation(0,0);
		setSize(500,500);
		
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent ae)
			{
				setVisible(false);
				//System.exit(0);
			}
		
		});
		
		
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
		p6.setLayout(new FlowLayout(FlowLayout.LEFT));
		p7=new Panel();
		
		lbid=new JLabel("User_Id :",Label.RIGHT);
		lbpass=new JLabel("Password:",Label.RIGHT);
		lbname=new JLabel("Name    :",Label.RIGHT);
		lbage=new JLabel("Age      :",Label.RIGHT);
		lbsex=new JLabel("Gender   :",Label.RIGHT);
		lbtype=new JLabel("Category:",Label.RIGHT);
		txtid=new TextField(15);
		
		txtname=new TextField(15);
		
		txtpass=new TextField(15);
		
		txtage=new TextField(15);
		
		 sex=new CheckboxGroup();
		
		 ch1=new Checkbox("Male",sex,true);
		 ch2=new Checkbox("Female",sex,false);
		 ch=new Choice();
		
		ch.add("Manager");
		ch.add("Administrator");
		
		txtpass.setEchoChar('*');
		p1.add(lbid);
		p1.add(txtid);
		p2.add(lbpass);
		p2.add(txtpass);
		p3.add(lbname);
		p3.add(txtname);
		p4.add(lbage);
		p4.add(txtage);
		p5.add(lbsex);
		p5.add(ch1);
		p5.add(ch2);
		p6.add(lbtype);
		p6.add(ch);
		btnSave=new JButton("save record");
		
		btnSave.addActionListener(this);
		p7.add(btnSave);
		add(p1);
		add(p2);
		add(p3);
		add(p4);	
		add(p5);
		add(p6);
		add(p7);
		setBackground(Color.ORANGE);
		setVisible(true);
	}
	
	public void actionPerformed(ActionEvent ae)
	{
		String src=ae.getActionCommand();
		
		if(src.equals(btnSave.getLabel()))
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
		
		String sql="INSERT INTO User_info(user_id,password,name,age,sex,type)VALUES(?,?,?,?,?,?)";
		//System.out.println(ch1.getState());
		
		
		PreparedStatement pstmt=conn.prepareStatement(sql);
		System.out.println("hello   ");
		pstmt.setString(1,txtid.getText());
		pstmt.setString(2,txtpass.getText());
		pstmt.setString(3,txtname.getText());
		pstmt.setString(4,txtage.getText());
		//pstmt.setString(5,getState());
		System.out.println(ch1.getState());
		
		if(ch1.getState())
		{
			pstmt.setString(5,ch1.getLabel());
		}
		else
		{
			pstmt.setString(5,ch2.getLabel());
		}
        pstmt.setString(6,ch.getSelectedItem());
        pstmt.executeUpdate();
		conn.close();
		txtid.setText("");
		txtpass.setText("");
		txtname.setText("");
		txtage.setText("");
		
}
public static void main(String args[])
{
	new User_info("USER INFORMATION");
}
}

