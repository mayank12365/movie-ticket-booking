import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class timings1 extends Frame implements ItemListener
{
Panel p1,p2,p3,p4;
JLabel lbdate,msg1,msg2,msg3;
JTextField txtdate;
String Namesel,audisel,timesel;
String tempname,tempaudi,temptime;

Choice ch,ch1,ch2;
 timings1(String a)
 {
	 super();
	 setSize(700,500);
	 setLayout(new GridLayout(4,1));
	 setBackground(Color.BLUE);
	 
	 
	 addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent ae)
			{
				setVisible(false);
				//System.exit(0);
			}
		
		});
		
	 //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	 p1=new Panel();
	    p1.setLayout(new FlowLayout(FlowLayout.LEFT));
        p2=new Panel ();
        p2.setLayout(new FlowLayout(FlowLayout.LEFT));
        p3=new Panel ();
       
        lbdate=new JLabel("Showing On",JLabel.RIGHT);
         msg1=new JLabel("SELECT MOVIE:",JLabel.RIGHT);
         msg2=new JLabel("SELECT AUDI:",JLabel.CENTER);
         msg3=new JLabel("SELECT TIMINGS:",JLabel.RIGHT);
         
         java.sql.Date cdt= new java.sql.Date(new java.util.Date().getTime());
         
        txtdate=new JTextField(String.valueOf(cdt));
       
        ch=new Choice();
        showChoice();
        ch.addItemListener(this);
        //ch.add("SELECT MOVIE");
         //ch.select(0);
        ch1=new Choice();
        showch();
        ch1.addItemListener(this);
        //ch1.add("SELECT AUDI");
        //ch1.select(0);
        ch2=new Choice();
      //  showCh();
        ch2.addItemListener(this);
        ch2.add("SELECT TIMINGS");
        ch2.add("10:00 AM");
        ch2.add("10:30 AM");
        ch2.add("11:00 AM");
        ch2.add("12:00 PM");
        ch2.add("01:00 PM");
        ch2.add("02:30 PM");
        ch2.add("04:00 PM");
        ch2.add("06:00 PM");
        
        ch2.select(0);
  
    
        p1.add(msg1);    
        p1.add(ch);
        p1.add(msg2);
        p1.add(ch1);
        p1.add(msg3);
        p1.add(ch2);
        p2.add(lbdate);
        p2.add(txtdate);
       
        
        add(p1);
        add(p2);
        add(p3);
      
        setBackground(Color.CYAN);
        setVisible(true);
        
        
        
 }
 public void itemStateChanged(ItemEvent ie)
 {
	 Object src=ie.getSource();
	 if(src.equals(ch))
	 {
		 Namesel=ch.getSelectedItem().toString();
		 accessDBMOD(Namesel);
	 }
	 if(src.equals(ch1))
	 {
		 audisel=ch1.getSelectedItem().toString();
		 accessDBMOD1(audisel);
	 }
	 if(src.equals(ch2))
	 {
		 timesel=ch2.getSelectedItem().toString();
		 //accessDBMOD2(timesel);
		 try
	 		{
	 			accessDB();
	 		}
	 		catch(Exception e)
	 		{
	 			System.out.println("hello"+e);
	 		}
		 
		 this.setVisible(false);
		 
		select si=new select(ch.getSelectedItem());
	      
	 }
	 
 }
 

 
 
/* public void actionPerformed(ActionEvent ae)
 {
	 String src=ae.getActionCommand();
 	if(src.equals(Save.getLabel()))
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
 }*/
 void accessDB()throws Exception
 {
 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
 	String sql="INSERT INTO timings(movie,audi,show_d,show_time)VALUES(?,?,?,?)";
 	PreparedStatement pstmt=conn.prepareStatement(sql);
	pstmt.setString(1,ch.getSelectedItem());
	pstmt.setString(2,ch1.getSelectedItem());
	pstmt.setString(3,txtdate.getText());
	pstmt.setString(4,ch2.getSelectedItem());
    pstmt.executeUpdate();
	conn.close();
	txtdate.setText("");

	
	System.out.println("hey");
	}
 
 
 void accessDBMOD(String mname)
 {
 try
 {
 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
 	String sql="SELECT name FROM movie_info WHERE name='"+mname+"'";
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
tempname =rs.getString("name");
//txtname.setEditable(false) ;

}
}

void accessDBMOD1(String maudi)
{
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
	String sql="SELECT audi_no FROM audi_info WHERE audi_no="+maudi;
	Statement st=conn.createStatement();
   boolean hasResult=st.execute(sql);
   if(hasResult)
   {
   	ResultSet rs = st.getResultSet( ) ;
   	
   
   if ( rs != null)
   
   	displayResultsmod1(rs);
   }
   conn.close();
	
}
catch(Exception e) 
{
System.out.println("hiii"+e);	
}
}
void displayResultsmod1(ResultSet rs) throws SQLException
{
while(rs.next( ))
{
tempaudi =rs.getString("audi_no");
//txtname.setEditable(false) ;

}
}

void accessDBMOD2(String mtime)
{
try
{
	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
	String sql="SELECT show_time FROM timings WHERE show_time='"+mtime+"'";
	System.out.println("arpan....");
	Statement st=conn.createStatement();
   boolean hasResult=st.execute(sql);
   if(hasResult)
   {
   	ResultSet rs = st.getResultSet( ) ;
   	
   
   if ( rs != null)
   
   	displayResultsmod2(rs);
   }
   conn.close();
	
}
catch(Exception e) 
{
System.out.println("hiii"+e);	
}
}
void displayResultsmod2(ResultSet rs) throws SQLException
{
while(rs.next( ))
{
temptime =rs.getString("show_time");
//txtname.setEditable(false) ;

}
}


void showChoice( )
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
Statement st = conn.createStatement( ) ;
String sql = "SELECT name FROM movie_info ORDER BY name " ;
boolean hasResult =  st.execute(sql) ;
if (hasResult)
{
ResultSet rs = st.getResultSet( ) ;
while (rs.next( ))
{
tempname = rs.getString("name") ;
ch.add(tempname) ;
}
}
conn.close( ) ;
}
catch(Exception e)
{ 
	System.out.println("hloooooo"+e);	
}
}

void showch( )
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
Statement st = conn.createStatement( ) ;
String sql = "SELECT audi_no FROM audi_info ORDER BY audi_no " ;
boolean hasResult =  st.execute(sql) ;
if (hasResult)
{
ResultSet rs = st.getResultSet( ) ;
while (rs.next( ))
{
tempaudi = rs.getString("audi_no") ;
ch1.add(tempaudi) ;
}
}
conn.close( ) ;
}
catch(Exception e)
{
System.out.println("hloo"+e);	
}
}

void showCh( )
{
try
{
Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
Statement st = conn.createStatement( ) ;
String sql = "SELECT show_time FROM timings ORDER BY show_time " ;
boolean hasResult =  st.execute(sql) ;
if (hasResult)
{
ResultSet rs = st.getResultSet( ) ;
while (rs.next( ))
{
temptime = rs.getString("show_time") ;
ch2.add(temptime) ;
}
}
conn.close( ) ;
}
catch(Exception e) 
{
	System.out.println("hloooo"+e);	
}
}


/*public static void main(String args[ ])
{
new timings1("") ;
}*/
}