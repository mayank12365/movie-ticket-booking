
import java.awt.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
    public class seat_select extends JFrame implements ActionListener
    {
    	Frame f=new Frame();
    	JButton temp[]=new JButton[500];
    	JButton b1;
    	String s1[]= new String[500];
    	String seat_choice[]= new String[500];
    	int zz=0;
    	
    	String seat_sel="";
    	String movnm, movdt,movtime,movclass,movqty,movaudi;
    	byte[] bytes=null;
    	ImageIcon icon;
    	JPanel p1,p2,p3,p4,p5,p6;
    	JLabel msg,msg1,lbmovie,lbdate,lbtime,lbclass,lbqty,lbseat,lb,lbimg,lbaudi;
    	seat_select(String ss1,String ss2, String ss3)
    	{
    		
    		
    	  super();
    	  setSize(1020,800);
    	  setLayout(null);
    	  setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	  
    	  movclass=ss2;
    	  movqty=ss3;
    	  
    	  b1=new JButton("Click to Confirm");
    	  b1.addActionListener(this);
  		
    	  JButton ob[][]= new JButton[30][30];
    	  JButton ob1[][]= new JButton[30][30];
    	  
    	  msg=new JLabel("GOLD",JLabel.LEFT);
    	  msg1=new JLabel("SILVER",JLabel.LEFT);
    	  p1=new JPanel();
    	  p1.setLayout(null);
    	  Color cgold= new Color(242,213,40);
    	  
    	  p1.setBackground(cgold);
    	  p2=new JPanel();
    	  p2.setLayout(null);
    	  Color csilver= new Color(232,226,221);
    	  p2.setBackground(csilver);
    	  p3=new JPanel();
    	  p3.setLayout(null);
    	  
    	   p4=new JPanel();
    	  p5=new JPanel();
    	  p5.setLayout(null);
    	  p5.setBackground(Color.CYAN);
    	  p6=new JPanel();
    	  
    	  show1(ss1);
    	  lb=new JLabel(new ImageIcon("F:/screen.jpg"));
    	  lbmovie=new JLabel("Movie   :  "+ movnm,Label.RIGHT);
    	  lbdate=new JLabel( "Date    :  "+ movdt,Label.RIGHT);
    	  lbtime=new JLabel( "Time    :  "+ movtime,Label.RIGHT);
    	  lbclass=new JLabel("Class   :  "+ movclass,Label.RIGHT);
    	  lbqty=new JLabel(  "Quantity:  "+ movqty,Label.RIGHT);
    	  lbseat=new JLabel( "Seats   :  ",Label.RIGHT);
    	  lbimg= new JLabel();
    	  lbaudi = new JLabel("Audi   :  "+ movaudi, Label.RIGHT);
    	  
    	  
    	  lbmovie.setBounds(0,0,200,50);
    	  p5.add(lbmovie);
    	  lbdate.setBounds(0,60,200,50);
    	  p5.add(lbdate);
    	  lbtime.setBounds(0,110,200,50);
    	  p5.add(lbtime);
    	  lbclass.setBounds(0,170,200,50);
    	  p5.add(lbclass);
    	  lbqty.setBounds(0,230,200,50);
    	  p5.add(lbqty);
    	  lbseat.setBounds(0,290,200,50);
    	  p5.add(lbseat);
    	  lbimg.setIcon(icon);
    	  
    	  lbimg.setBounds(0, 350, 250, 250);
    	  p5.add(lbimg);
    	  b1.setBounds(0,610,200,50);
    	  p5.add(b1);
    	  p6.add(lb);
    	 
    	  int x=0;
    	 
    	  byte b=65;
    	  byte b1=65;
    	  int n=1;
    	  int n1=1;
    	  p3.add(msg);
    	  msg.setBounds(350, 5, 100, 50);
    	  p3.add(lbaudi);
    	  lbaudi.setBounds(20, 5, 100, 50);
    	  
    	  p3.setBounds(0,0,750,50);
    	 add(p3);
    	   int k=10,l=20;
    	   int k1=10,l1=20;
    	   for(int i=0;i<9;i++)
    	   { 
    		   try
    		   {
    		   for(int j=0;j<26;j++)
    		   {
    				String s= (char) b +"-" + n;
    				Image img = ImageIO.read(getClass().getResource("chair_a.png"));
    				ImageIcon seat = new ImageIcon(img);
    			
    				if((j==13 || j==14) && i!=0)
    				{
    					k+=25;
    					continue;
    				}
    				ob[i][j]= new JButton(seat);
    			  temp[x]=new JButton();
    			  temp[x]=ob[i][j];
    			  temp[x].addActionListener(this);
    			  //temp[x].setLabel(s);
    			  s1[x]= s;
    			
    			ob[i][j].setBounds(k,l,20,20);
    	
    				k=k+25;
    				p1.add(ob[i][j]);
    				ob[i][j].setBackground(Color.WHITE);
     				n++;
    				x++;
    			}
    		   }
    		   catch(Exception e)
    		   {
    			   System.out.println("hello"+e);
    		   }
    		   k=10;
    		   l+=30;
    		   b++;
    		   n=1;
    	   }
    	 
    	   p1.setBounds(0,50, 750,250);
    	   
    	   
    	   add(p1);
    	   p4.add(msg1);
    	   p4.setBounds(0,300,750,50);
    	   for(int i=0;i<7;i++)
    	   { 
    		   try
    		   {
    		   for(int j=0;j<20;j++)
    		   {
    				String s= (char) b +" " + n1;
    				Image img = ImageIO.read(getClass().getResource("chair_na.png"));
    				ImageIcon seat = new ImageIcon(img);
    			
    				if((j==13 || j==14) )
    				{
    					k1+=25;
    					continue;
    				}
    				ob[i][j]= new JButton(seat);
    			  temp[x]=new JButton();
    			  temp[x]=ob[i][j];
    			  temp[x].addActionListener(this);
    			  //temp[x].setLabel(s);
    			  s1[x]= s;
    			
    			ob[i][j].setBounds(k1,l1,20,20);
    	
    				k1=k1+25;
    				p2.add(ob[i][j]);
    				ob[i][j].setBackground(Color.WHITE);
     				n1++;
    				x++;
    			}
    		   }
    		   catch(Exception e)
    		   {
    			   System.out.println("hello"+e);
    		   }
    		   k1=10;
    		   l1+=30;
    		   b++;
    		   n1=1;
    	   }
    	   
    	 
    	 
    	  p2.setBounds(0,350,750,250);
    	  p5.setBounds(760,50,270,650);
    	  p6.setBounds(0,610,750,100);
    	  add(p4);
    	  add(p2);
    	  add(p5);
    	  add(p6);
    	 booked(movnm);
    	   setVisible(true);
    	   
    	 
    	}
    	
    	 public void actionPerformed(ActionEvent ae)
    	 {
			 seat_sel="";
			 
		for(int i=0;i<temp.length;i++)
		    {
			if(ae.getSource()==temp[i])
			
			    {		
			   Color a=temp[i].getBackground();
			   if(a== Color.WHITE)
			      {
				   try
				   {
				   Image img = ImageIO.read(getClass().getResource("chair_sel.png"));
   				ImageIcon seat = new ImageIcon(img);
   				temp[i].setIcon(seat);
   				
   				
				  temp[i].setBackground(Color.GREEN);
				   }
				   catch(Exception e)
				   {
					   System.out.println(e);
					   
				   }
				
		        }
			   else if(a== Color.GREEN)
			   {
				   try{
					   Image img = ImageIO.read(getClass().getResource("chair_a.png"));
	   				ImageIcon seat = new ImageIcon(img);
	   				temp[i].setIcon(seat);
	   				
					  temp[i].setBackground(Color.WHITE);
					   }catch(Exception e)
					   {
						   System.out.println(e);
						   
					   }
			   }
			  
			    }
		     
			if(i<=343)
			{
			Color a=temp[i].getBackground();
			   if(a== Color.GREEN)
			{
				seat_sel+= s1[i] +",";
			}
			}
			
		}
		String src=ae.getActionCommand();
		
		if(src.equals(b1.getLabel()))
		{
			
			try
			{
		//		System.out.println("hello   " +src);
				
				accessDB();
				this.setVisible(false);
				
			}
			catch(Exception e)
			{
				System.out.println(" Hello ===" +e);
			}
		}
		lbseat.setText("Seat   :"+ seat_sel);
		
		//System.out.println("hey...."+ seat_sel);
}
    	 
    	 void accessDB()throws Exception
    	 {
    	 		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
    	 		Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
    	 		int j=0;
    	 		
    	 		for(int i=0;i<temp.length;i++)
    		    {
    		     
    			if(i<=343)
    			{
    			Color a=temp[i].getBackground();
    			   if(a== Color.GREEN)
    			{
    				seat_choice[zz]=s1[i];
    				System.out.println("==>s["+zz+"]=="+ seat_choice[zz]);
    				zz++;
    			}
    			}
    		    			
    			
    		}		
    	 		
    	 	   	 		
    	 		while(seat_choice[j]!=null)
    	 		{
    	 		String sql="INSERT INTO seat_booked(movie_name,date1,timing,class,qty,seat_no,audi)VALUES(?,?,?,?,?,?,?)";
    	 		//System.out.println(ch1.getState());
    	 		
    	 		
    	 		PreparedStatement pstmt=conn.prepareStatement(sql);
    	 		System.out.println("hello   ");
    	 		pstmt.setString(1,movnm);
    	 		pstmt.setString(2,movdt);
    	 		pstmt.setString(3,movtime);
    	 		pstmt.setString(4,movclass);
    	 		pstmt.setString(5,movqty);
    	 		pstmt.setString(6,seat_choice[j]);
    	 		pstmt.setString(7, movaudi);
    	 		
    	 		pstmt.executeUpdate();
    	 		j++;
    	 		
    	 		}
    	 		
    			conn.close();
    	 }
    	 void show1(String mname)
         {
        	 try
        	 {
        	 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
        	 	
        		//System.out.println("movie=="+ mname);
        	 	String sql="SELECT * FROM timings,movie_info WHERE timings.movie=movie_info.name and  movie='"+mname+"'";
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
    	 
    	 void booked(String mname)
         {
    		 String seatbook="";
    		 
        	 try
        	 {
        	 	Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        	 	Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
        	 	
        		//System.out.println("movie=="+ mname);
        	 	String sql="SELECT * FROM seat_booked WHERE  movie_name='"+movnm+"' and date1='" + movdt +"' and timing='"+ movtime +"' and class='"+ movclass+ "' and audi='"+ movaudi+"'";
        	 
        	 	System.out.println(sql);
        	 	
        	 	
        	 	
        	 	Statement st=conn.createStatement();
        	    boolean hasResult=st.execute(sql);
        	    if(hasResult)
        	    {
        	    	ResultSet rs = st.getResultSet( ) ;
        	    	
        	    	System.out.println("step 0");
        	    if ( rs != null)
        	    {
        	    	System.out.println("step 1");
        	    	
        	    	while(rs.next())
        	    	{
        	    		System.out.println("step 2");
        	    		seatbook= rs.getString("seat_no");
        	    		for(int i=0;i<temp.length;i++)
            		    {	
            		     
            			if(i<=343)
            			{
            				if(seatbook.equals(s1[i]))		
            				{
            					temp[i].setBackground(Color.RED);
            					
            					  Image img = ImageIO.read(getClass().getResource("chair_booked.png"));
            		   				ImageIcon seat = new ImageIcon(img);
            					
            					temp[i].setIcon(seat);
            				}
            					
            			}
            		    			
            			
            		    }		
        	    	}
        	    	
        	    }
        	    
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
        	movnm =rs.getString("movie");
        	//msg1.setText(tempname);
        	movdt=rs.getString("show_d");
        
        	movtime=rs.getString("show_time");
        	movaudi= rs.getString("audi");
			 bytes = rs.getBytes("image1");
			 
	         Image image = f.getToolkit().createImage(bytes);
	 icon=new ImageIcon(image);
	         

        	}
        	}
    	    	 
    	/*public static void main(String arg[])
    	{
    		new seat_select();
    	}*/
   
    }
    
    
    
