import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class JMenuFrame extends JFrame implements ActionListener
{
	JMenu user_info,Movie_info,Booking,audi_info,cust_info ;
	JMenuItem create_user,modify_user,delete_user;
	JMenuItem insert_mov,update_mov,del_mov;
	JMenuItem adv_booking,cur_booking;
	JMenuItem audi,update;
	JMenuItem cust;
	JLabel l1;
	JPanel p1;
	JMenuFrame(String label)
	{
		super(label) ; 
		JMenuBar mbar = new JMenuBar( );
		p1=new JPanel();
		user_info=new JMenu("User Information") ;
		user_info.setLayout(new BorderLayout( )) ; 
		user_info.setMnemonic('U');
		
		Movie_info=new JMenu("Movie Information") ;
		Movie_info.setMnemonic('M') ;
		
		Booking=new JMenu("Booking Information") ;
		Booking.setMnemonic('B') ;
		
		audi_info=new JMenu("Auditorium Information");
		audi_info.setMnemonic('A');
		
		cust_info=new JMenu("Customer Information");
		cust_info.setMnemonic('C');
		
		create_user =new JMenuItem("Create User") ;
		modify_user =new JMenuItem("Modify User") ;
		
		
		insert_mov = new JMenuItem("Insert Movie") ;
	    update_mov = new JMenuItem("Update Movie") ;

	    
		adv_booking = new JMenuItem("Booking") ;
		
		
		audi=new JMenuItem("Auditorium");
		update=new JMenuItem("Update");
		
		cust=new JMenuItem("Customers");
		
		create_user.addActionListener(this);
		modify_user.addActionListener(this);
		insert_mov.addActionListener(this);
		update_mov.addActionListener(this);
		audi.addActionListener(this);
		update.addActionListener(this);
		cust.addActionListener(this);
		adv_booking.addActionListener(this);
		
		user_info.add(create_user);
		user_info.add(modify_user);
		
	
		
		Movie_info.add(insert_mov);
		Movie_info.add(update_mov);
			
		Booking.add(adv_booking);

		
		audi_info.add(audi);
		audi_info.add(update);
		
		cust_info.add(cust);
		
		mbar.add(user_info) ;
		mbar.add(Movie_info) ;
		mbar.add(Booking) ;
		mbar.add(audi_info);
		mbar.add(cust_info);
		
		setJMenuBar(mbar) ;
		mbar.setBackground(Color.LIGHT_GRAY);
		l1=new JLabel(new ImageIcon("F:/T.jpg"));
		p1.add(l1);
		p1.setBounds(0,0,600,600);
		p1.setBackground(Color.PINK);
		add(p1);
		setSize(500,250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		String src=(String)ae.getActionCommand();
		
		if(src.equals("Create User"))
		try
		{
			System.out.println("hello");
			User_info u1=new User_info("USER INFORMATION");
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(src.equals("Modify User"))
		try
		{
		User_mod u2=new User_mod("MODIFY USER");	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(src.equals("Insert Movie"))
		try		
		{
			movie_info u3=new movie_info("MOVIE INFORMATION");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
		if(src.equals("Update Movie"))
			try		
			{
				movie_mod u4=new movie_mod();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		if(src.equals("Auditorium"))
		try
		{
		audi_info u4=new audi_info("AUDITORIUM INFORMATION");	
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		if(src.equals("Update"))
			try
			{
			audi_mod u5=new audi_mod();	
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		if(src.equals("Customers"))
			try
			{
			cust_info u6=new cust_info("CUSTOMER INFORMATION");	
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
			if(src.equals("Booking"))
				try
				{
				timings1 u7=new timings1("BOOKING INFORMATION");	
				}
				catch(Exception e)
				{
					System.out.println(e);
				}
		}
	
	public static void main(String args[]) 
	{
		new JMenuFrame("MULTIPLEXES...") ;
	}
}