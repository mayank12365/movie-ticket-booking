import java.awt.event.* ;
import java.awt.* ;
import java.sql.* ;
import javax.swing.* ;
class User_mod extends Frame implements ActionListener
{
		Panel p1,p2, p3, p4,p5,p6, p7,p8 ;
		JLabel lbid, lbpass, lbname, lbage, lbsex,lbtype ;
		TextField  txtid, txtname, txtpass, txtage ;
		Choice ch,ch3 ;
		//Button btnSave ;
		JButton btnmodify;
		JButton show;
		String sql ;
		String tid, tpass, tname, tsex, tage,ttype ;
		String Namesel ,tempname;
		Checkbox ch1,ch2;
		CheckboxGroup sex;																													;
		User_mod(String str)
		{
		super(str) ;
		setLayout(new GridLayout(8,1)) ;
		setLocation(0,0) ;
		setSize(700,500) ;
		addWindowListener(new WindowAdapter( )
							{
							public void windowClosing(WindowEvent we)
								{
									setVisible(false) ;
										//System.exit(0) ;
								}
							}) ;

		p1 = new Panel( ) ;
		p1.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p2 = new Panel( ) ;
		p2.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p3 = new Panel( ) ;
		p3.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p4 = new Panel( ) ;
		p4.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p5 = new Panel( ) ;
		p5.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p6 = new Panel( ) ;
		p6.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p7 = new Panel( ) ;
		p7.setLayout(new FlowLayout(FlowLayout.LEFT)) ;
		p8=new Panel();
		lbid = new JLabel("User_Id :", Label.RIGHT) ;
		lbpass  = new JLabel( "Password:", Label.RIGHT) ;
		lbname = new JLabel("Name   :", Label.RIGHT) ;
		lbage   = new JLabel("Age    :", Label.RIGHT) ;
		lbsex   = new JLabel("Sex    :", Label.RIGHT) ;
		lbtype =new JLabel("Category:",Label.RIGHT);
		txtid = new TextField(15) ;
		txtpass  = new TextField(15) ;
		txtname = new TextField(15) ;
		txtage   = new TextField(15) ;
		sex=new CheckboxGroup();
		 ch1=new Checkbox("Male",sex,true);
		 ch2=new Checkbox("Female",sex,false);
		ch=new Choice();
		show = new JButton("Show") ;
		show.addActionListener(this) ;
		btnmodify=new JButton("Modify Record");
		btnmodify.addActionListener(this);
 
		ch.add("Manager");
		ch.add("Administrator");
		
		txtpass.setEchoChar('*');
		
		p1.add(lbid) ;
		p1.add(txtid) ;
		p1.add(show);
		p2.add(lbpass) ;
		p2.add(txtpass) ;
		p3.add(lbname) ;
		p3.add(txtname) ;
		p4.add(lbage) ;
		p4.add(txtage) ;
		p5.add(lbsex) ;
		p5.add(ch1);
		p5.add(ch2);
		p6.add(lbtype);
		p6.add(ch);
		p8.add(btnmodify);
		
		add(p1) ;
		add(p2) ;
		add(p3) ;
		add(p4) ;
		add(p5) ;
		add(p6) ;
		add(p8) ;
		setBackground(Color.ORANGE);
		setVisible(true) ;
  	}
  	/*public void itemStateChanged(ItemEvent ie)
  	{
		Object src = ie.getSource( ) ;
		if (src.equals(ch3))
		{
			Namesel = ch3.getSelectedItem( ).toString( );
			accessDBMOD(Namesel);
		}
  }*/
  public void actionPerformed(ActionEvent ae)
  {
			String src = ae.getActionCommand( ) ;
			
			
			
			if (src.equals(show.getLabel( )))
            {
				String uid= txtid.getText();
				
				accessDBMOD(uid ) ;
			}
			else if (src.equals(btnmodify.getLabel()))
			{
                saveDBMod();
			}
}
void saveDBMod( )
{
			try

			{
				Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
				Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
				String sql="UPDATE User_info SET password=?,name=?,age=?,sex=?,type=? WHERE user_id=?";
				//System.out.println(ch1.getState());
				
				
				PreparedStatement pstmt=conn.prepareStatement(sql);
				pstmt.setString(1,txtpass.getText());
				pstmt.setString(2,txtname.getText());
				pstmt.setString(3,txtage.getText());
			//	pstmt.setString(4,txtsex.getText());
				//pstmt.setString(5,getState());
				System.out.println(ch1.getState());
				
				if(ch1.getState())
				{
					pstmt.setString(4,ch1.getLabel());
				}
				else
				{
					pstmt.setString(4,ch2.getLabel());
				}
		        pstmt.setString(5,ch.getSelectedItem());
		        pstmt.setString(6,txtid.getText());
		        pstmt.executeUpdate();
				conn.close();
				txtid.setText("");
				txtpass.setText("");
				txtname.setText("");
				txtage.setText("");
				
			}
			catch(Exception e)
			{
				System.out.println("hey"+e) ;
			}
}
void accessDBMOD(String mname)
{
			try
			{
					Class.forName("sun.jdbc.odbc.JdbcOdbcDriver") ;
					Connection conn = DriverManager.getConnection("jdbc:odbc:jdata2") ;
					sql = "SELECT * FROM User_info WHERE user_id='"+mname+"'";
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
				tid =rs.getString("user_id");
				txtid.setText(tid) ;
				txtid.setEditable(false) ;
				tpass=rs.getString("password");
				txtpass.setText(tpass);
				tname=rs.getString("name");
				txtname.setText(tname) ;
				tage =rs.getString("age");
				txtage.setText(tage) ;
				if(rs.getString("Sex").equals("Male"))
				{
					ch1.setState(true);
				}
				else
				{
					ch2.setState(true);
					
			    }
				ttype=rs.getString("type");
				ch.select(ttype);
				
	
			
	         
			}
}
/*void showChoice()
{
	try
	{
		Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		Connection conn=DriverManager.getConnection("jdbc:odbc:jdata2");
		Statement st=conn.createStatement();
		sql="SELECT user_id,password,name,age,sex,type FROM user_info ORDER BY user_id ";
		boolean hasResult=st.execute(sql);
		if(hasResult)
		{
			ResultSet rs=st.getResultSet();
			while(rs.next())
			{
				tempname=rs.getString("user_id");
				ch3.add(tempname);		
		    }
				
		}
			conn.close();
			
	}
		catch(Exception e)
		{
			System.out.println(e);
		}
		}*/


public static void main(String args[ ])
{
	new User_mod("Modify Record") ;
}
}
