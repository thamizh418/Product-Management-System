package Customerreg;
import java.util.*;
import java.sql.*;
public class Custreg
{
	public long cphnum,cpin;
	public String cname,caddrs,cmail;
  Scanner sc = new Scanner(System.in);
	public void reg()
	{
		try
		{
			System.out.println("Enter Your Name : ");
			cname=sc.nextLine();
			System.out.println("Enter Your Phone Number :(Number Should be in 10 digits...)");
			cphnum=sc.nextLong();
			System.out.println("Enter Your Address : ");
			caddrs=sc.next();
			System.out.println("Enter Your Pincode : ");
			cpin=sc.nextLong();
			System.out.println("Enter Your Email ID : ");
			cmail=sc.next();
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
			PreparedStatement ps=con.prepareStatement("insert into customerdata values(?,?,?,?,?)");
			ps.setString(1,cname);
			ps.setLong(2,cphnum);
			ps.setString(3,caddrs);
			ps.setLong(4,cpin);
			ps.setString(5,cmail);
			ps.executeUpdate();
			con.close();
			System.out.println("Customer Detail Saved...");
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
}