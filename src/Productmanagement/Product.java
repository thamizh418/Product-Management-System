package Productmanagement;
import java.util.*;
import java.sql.*;

public class Product
{
	public int pid,pprice,pstock; 
	public String pname,pbrand,pmfd;
	Scanner sc = new Scanner(System.in);
	public void addprod()
	{
		int add_check=0;
		try
		{
			do
			{
				add_check=0;
				System.out.println("How many Product do Want to Add : ");
				int items=sc.nextInt();
				for(int i=0;i<items;i++)
				{
					Class.forName("com.mysql.jdbc.Driver");
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
					Statement st=con.createStatement();
					ResultSet rs=st.executeQuery("select * from productdata");
					System.out.println("Enter the Product ID : ");
					pid=sc.nextInt();
					int id_exist_check=0;
					while(rs.next())
					{
						if(pid==(rs.getInt(1)))
						{
							id_exist_check++;
						}
					}
					if(id_exist_check==0)
					{
						System.out.println("Enter the Product Name : ");
						pname=sc.next();
						System.out.println("Enter the Product Brand : ");
						pbrand=sc.next();
						System.out.println("Enter the Product Price : ");
						pprice=sc.nextInt();
						System.out.println("Enter the Product Manufactute Date :");
						pmfd=sc.next();
						System.out.println("Enter the Product Stock : ");
						pstock=sc.nextInt();
						PreparedStatement ps=con.prepareStatement("insert into productdata values(?,?,?,?,?,?)");
						ps.setInt(1,pid);
						ps.setString(2,pname);
						ps.setString(3,pbrand);
						ps.setInt(4,pprice);
						ps.setString(5,pmfd);
						ps.setInt(6,pstock);
						ps.executeUpdate();
						con.close();
						System.out.println("Data Saved");
					}
					else
					{
						System.out.println("Product ID Already Exists...... ");
						i--;
					}
				}
				String cont="yes";
				while(cont.equals("yes"))
				{
					System.out.println("Do you want to Add More Products(y/n) : ");
					cont=sc.next();
					cont=cont.toLowerCase();
					if(cont.equals("y")||cont.equals("yes"))
					{
						add_check=1;
					}
				}
				
			}while(add_check==1);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void updateprod()
	{
		int update_check=0,update_check1=0,result;
		try
		{
			do
			{
				update_check=0;
				System.out.println("Enter the Product ID for Update : ");
				pid=sc.nextInt();
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
				do
				{
					update_check1=0;
					System.out.println("Which one you want to update?\n1.Name\t2.Brand\t3.Price\t4.Manufacture Date\t5.Stock");
					int update_choice=sc.nextInt();
					if(update_choice==1)
					{
						System.out.println("Enter the Product Name for Update : ");
						pname=sc.next();
						PreparedStatement ps=con.prepareStatement("update productdata set name=? where pid=?");
						ps.setString(1,pname);
						ps.setInt(2,pid);
						result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Update successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID...");
						}
					}
					else if(update_choice==2)
					{
						System.out.println("Enter the Product Brand for Update : ");
						pbrand=sc.next();
						PreparedStatement ps=con.prepareStatement("update productdata set pbrand=? where pid=?");
						ps.setString(1,pbrand);
						ps.setInt(2,pid);
						result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Update successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID...");
						}
					}
					else if(update_choice==3)
					{
						System.out.println("Enter the Product Price for Update : ");
						pprice=sc.nextInt();
						PreparedStatement ps=con.prepareStatement("update productdata set pprice=? where pid=?");
						ps.setInt(1,pprice);
						ps.setInt(2,pid);
						result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Update successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID...");
						}
					}
					else if(update_choice==4)
					{
						System.out.println("Enter the Product Manufacture Date for Update : ");
						pmfd=sc.next();
						PreparedStatement ps=con.prepareStatement("update productdata set pmfd=? where pid=?");
						ps.setString(1,pmfd);
						ps.setInt(2,pid);
						result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Update successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID...");
						}
					}
					else if(update_choice==5)
					{
						System.out.println("Enter the Product Stock for Update : ");
						pstock=sc.nextInt();
						PreparedStatement ps=con.prepareStatement("update productdata set pstock=? where pid=?");
						ps.setInt(1,pstock);
						ps.setInt(2,pid);
						result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Update successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID...");
						}
					}
					else
					{
						String cont="yes";
						while(cont.equals("yes"))
						{
							System.out.println("Your chioce is Worng...\nDo you want to correct Update Product Again(y/n) : ");
							cont=sc.next();
							cont=cont.toLowerCase();
							if(cont.equals("y")||cont.equals("yes"))
							{
								update_check1=1;
							}
						}
					}
				}while(update_check1==1);
				
				String cont="yes";
				while(cont.equals("yes"))
				{
					System.out.println("Do you want to Update Products Again(y/n) : ");
					cont=sc.next();
					cont=cont.toLowerCase();
					if(cont.equals("y")||cont.equals("yes"))
					{
						update_check=1;
					}
				}
				con.close();
			}while(update_check==1);
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	public void searchprod()
	{
		int search_check=0,search_check1=0;
		try
		{
			do
			{
				search_check=0;
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
				Statement st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from productdata");
				do
				{
					int no_prod=0;
					System.out.println("Which option you want to use for Search?\n1.Product ID\t2.Product Name\t3.Product Brand");
					int search=sc.nextInt();
					if(search==1)
					{
						System.out.println("Enter the Product ID : ");
						int search_id=sc.nextInt();
						while (rs.next())
						{
							if(search_id==(rs.getInt(1)))
							{
								System.out.println("1.Product ID.................: "+rs.getInt(1));
								System.out.println("2.Product Name...............: "+rs.getString(2));
								System.out.println("3.Product Brand..............: "+rs.getString(3));
								System.out.println("4.Product Price..............: "+rs.getInt(4));
								System.out.println("5.Product Manufacture Date...: "+rs.getString(5));
								System.out.println("6.Product Stock..............: "+rs.getInt(6));
							}
							else
							{
								no_prod++;
							}
							
						}
					}
					else if(search==2)
					{
						System.out.println("Enter the Product Name : ");
						String search_name=sc.next();
						while (rs.next())
						{
							if(search_name.equals(rs.getString(2)))
							{
								System.out.println("1.Product ID.................: "+rs.getInt(1));
								System.out.println("2.Product Name...............: "+rs.getString(2));
								System.out.println("3.Product Brand..............: "+rs.getString(3));
								System.out.println("4.Product Price..............: "+rs.getInt(4));
								System.out.println("5.Product Manufacture Date...: "+rs.getString(5));
								System.out.println("6.Product Stock..............: "+rs.getInt(6));
							}
							else
							{
								no_prod++;
							}
						}
					}
					else if(search==3)
					{
						System.out.println("Enter the Product Brand : ");
						String search_brand=sc.next();
						while (rs.next())
						{
							if(search_brand.equals(rs.getString(3)))
							{
								System.out.println("1.Product ID.................: "+rs.getInt(1));
								System.out.println("2.Product Name...............: "+rs.getString(2));
								System.out.println("3.Product Brand..............: "+rs.getString(3));
								System.out.println("4.Product Price..............: "+rs.getInt(4));
								System.out.println("5.Product Manufacture Date...: "+rs.getString(5));
								System.out.println("6.Product Stock..............: "+rs.getInt(6));
							}
							else
							{
								no_prod++;
							}
						}
					}
					else
					{
						String cont="yes";
						while(cont.equals("yes"))
						{
							System.out.println("Your chioce is Worng...\nDo you want to Search Product Again(y/n) : ");
							cont=sc.next();
							cont=cont.toLowerCase();
							if(cont.equals("y")||cont.equals("yes"))
							{
								search_check1=1;
							}
						}
					}
					if(no_prod==1)
					{
						System.out.println("There is no Product in your input Related...");
					}
				}while(search_check1==1);
				
				String cont="yes";
				while(cont.equals("yes"))
				{
					System.out.println("Do you want to Search Products Again(y/n) : ");
					cont=sc.next();
					cont=cont.toLowerCase();
					if(cont.equals("y")||cont.equals("yes"))
					{
						search_check=1;
					}
				}
				con.close();
			}while(search_check==1);			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}
	
	
	public void printprod()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from productdata");
			System.out.println("---------------------------------------");
			while (rs.next())
			{
				System.out.println("1.Product ID.................: "+rs.getInt(1));
				System.out.println("2.Product Name...............: "+rs.getString(2));
				System.out.println("3.Product Brand..............: "+rs.getString(3));
				System.out.println("4.Product Price..............: "+rs.getInt(4));
				System.out.println("5.Product Manufacture Date...: "+rs.getString(5));
				System.out.println("6.Product Stock..............: "+rs.getInt(6));
				System.out.println("---------------------------------------");
			}
			con.close();
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

	
	
	public void deleteprod()
	{
		try
		{
			int delete_check=0,delete_check1=0;
			do
			{
				delete_check=0;
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/productmanagement","root","");
				do
				{
					delete_check1=0;
					System.out.println("Please Select the option for Delecting the Data...\n1.Product ID\t2.Product Name\t3.Product Brand");
					int del_sel=sc.nextInt();
					if(del_sel==1)
					{
						PreparedStatement ps=con.prepareStatement("delete from productdata where pid=?");
						System.out.println("Enter the Product ID : ");
						int del_id=sc.nextInt();
						ps.setInt(1,del_id);
						int result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Deleted successfully...");
						}
						else
						{
							System.out.println("Invalid Product ID");
						}
					}
					else if(del_sel==2)
					{
						PreparedStatement ps=con.prepareStatement("delete from productdata where pname=?");
						System.out.println("Enter the Product Name : ");
						String del_name=sc.next();
						ps.setString(1,del_name);
						int result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Deleted successfully...");
						}
						else
						{
							System.out.println("Invalid Product Name");
						}
					}
					else if(del_sel==3)
					{
						PreparedStatement ps=con.prepareStatement("delete from productdata where pbrand=?");
						System.out.println("Enter the Product Brand : ");
						String del_brand=sc.next();
						ps.setString(1,del_brand);
						int result=ps.executeUpdate();
						if(result!=0)
						{
							System.out.println("Data Deleted successfully...");
						}
						else
						{
							System.out.println("Invalid Product Brand");
						}
					}
					else
					{
						String cont="yes";
						while(cont.equals("yes"))
						{
							System.out.println("Your chioce is Worng...\nDo you want to Delete Product Again(y/n) : ");
							cont=sc.next();
							cont=cont.toLowerCase();
							if(cont.equals("y")||cont.equals("yes"))
							{
								delete_check1=1;
							}
						}
					}
				}while(delete_check1==1);
				String cont="yes";
				while(cont.equals("yes"))
				{
					System.out.println("Do you want to Delete Products Again(y/n) : ");
					cont=sc.next();
					cont=cont.toLowerCase();
					if(cont.equals("y")||cont.equals("yes"))
					{
						delete_check=1;
					}
				}
				con.close();
				System.out.println("Data Saved");
			}while(delete_check==1);		
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
	}

}