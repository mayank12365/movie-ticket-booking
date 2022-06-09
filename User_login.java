import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class User_login extends Frame implements ActionListener 
{
	
Panel p1,p2,p3;
JLabel lbid,lbpass,msg;
TextField txtid,txtpass;
JButton login;
String tid1="aaa",tpass;
User_login(String lbl)
{
super(lbl);
setLayout(null);
setLocation(0,0);
setSize(500,500);

addWindowListener(new WindowAdapter()
{
	public void windowClosing(WindowEvent ae)
	{
		setVisible(false);
		System.exit(0);
	}
});
p1=new Panel();
p1.setLayout(null);
//p2=new Panel();
//p2.setLayout(new FlowLayout(FlowLayout.LEFT));
//p3=new Panel();
lbid=new JLabel("User_id",Label.RIGHT);
lbid.setBounds(10,50,70,50);
lbpass=new JLabel("Password",Label.RIGHT);
lbpass.setBounds(10,150,80,50);
msg=new JLabel(new ImageIcon("F:/multiplex.jpg"));
msg.setBounds(300,50,150,150);
txtid=new TextField(10);
txtid.setBounds(80,60,90,20);
txtpass=new TextField(10);
txtpass.setBounds(90,160,90,20);
txtpass.setEchoChar('*');
p1.add(lbid);
p1.add(txtid);
p1.add(msg);
p1.add(lbpass);
p1.add(txtpass);
login=new JButton("LOGIN");
login.setBounds(200,300,70,30);
login.addActionListener(this);
p1.add(login);
p1.setBounds(0,0,500,500);
add(p1);
//add(p2);
//add(p3);
setBackground(Color.PINK);
setVisible(true);
}

//public void ActionPerformed(ActionEvent ae)
//{System.out.println("hello..");
	/*if(ae.getSource()==login);
	{
		if(txtid.getText().equals("tid")&&txtpass.getText().equals("tpass"))
		{
			msg.setText("Logging in...");
		}
	}}*/
public void actionPerformed(ActionEvent ae) 
{
	String src=ae.getActionCommand();
	
	if(src.equals(login.getLabel()))
	{
		try
		{
			String	idsel= txtid.getText();
			String	passsel= txtpass.getText();
		accessDB(idsel,passsel);
	}
		catch(Exception e)
		{ 
			System.out.println(e);
		}
}
}

void accessDB(String tid,String tpass)
{
try{

	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");             
	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
  String sql = "SELECT * from user_info WHERE user_id='"+tid+"'and  password='"+tpass+"'";
  
  Statement st =conn.createStatement();
	  
			ResultSet rs= st.executeQuery(sql);
			
			if(rs.next())
			{
				tid1=rs.getString("user_id");
	System.out.println("login successfully");
    txtid.setText("");
    txtpass.setText("");
    displayResultsmod(rs);
			}
			else
			{
				
				System.out.println("Invalid Data");	
	
				txtid.setText("");
			    txtpass.setText("");
			    
			}	
		

}
		
		
	catch(Exception e)
	{
	System.out.println(e);
	}
	}



	  void displayResultsmod(ResultSet rs)throws SQLException	
	  {
	  	JMenuFrame w1=new JMenuFrame("MULTIPLEXES   User:"+tid1);
	  //	w1.lbwel.setText("WELCOME ...."+tid1);
	  }	

 

public static void main(String args[])
{
new User_login("MULTIPLEXES - LOGIN");


}
}
	
