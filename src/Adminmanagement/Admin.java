package Adminmanagement;
import java.util.*;
import java.sql.*;

public class Admin
{
	public String checkadmin()
	{
		String ok="";
		try
		{
			Scanner s =new Scanner(System.in);
			System.out.println("Welcome Admin...");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from admindata");
			while (rs.next())
			{
				int check=0,check1=0;
				do
				{
					check=0;
					System.out.println("Please enter Your Username : ");
					String admin_username=s.next();
					if(admin_username.equals(rs.getString(1)))
					{
						do
						{
							check1=0;
							System.out.println("Please enter Your Password : ");
							String admin_password=s.next();
							if(admin_password.equals(rs.getString(2)))
							{
								System.out.println("Db");
								ok= "ok";
							}
							else
							{
								System.out.println("Worng Password...");
								String passwordwrong="yes";
								while(passwordwrong.equals("yes"))
								{
									System.out.println("Do you want to retype the password(y/n) : ");
									passwordwrong=s.next();
									passwordwrong=passwordwrong.toLowerCase();
									if(passwordwrong.equals("y")||passwordwrong.equals("yes"))
									{
										check1=1;
									}
									else
									{
										ok= "ok1";
									}
								}
							}
						}while(check1==1);	
					}
					else
					{
						System.out.println("Worng Username...");
						String usernamewrong="yes";
						while(usernamewrong.equals("yes"))
						{
							System.out.println("Do you want to continue(y/n) : ");
							usernamewrong=s.next();
							usernamewrong=usernamewrong.toLowerCase();
							if(usernamewrong.equals("y")||usernamewrong.equals("yes"))
							{
								check=1;
							}
							else
							{
								ok= "ok1";
							}
						}
						
					}
				}while(check==1);
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}	
		return ok;
	}
}