package mysql;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class PHPMysql 
{
	private Connection con = null; //Database objects 
	//連接object 
	private Statement stat = null; 
	//執行,傳入之sql為完整字串 
	private ResultSet rs = null; 
	//結果集 
	private PreparedStatement pst = null; 
	//執行,傳入之sql為預儲之字申,需要傳入變數之位置 
	private String dataBaseName="HouseTmp";
	private String selectSQL = "SELECT * FROM `"; 
	private String deleteDataSQL="Delete FROM `HouseTmp`";
	private String insertdbSQL = "insert into HouseTmp(ID,Title,Address,OrginURL,money) " + 
		      "select ifNULL(max(ID),0)+1,?,?,?,? FROM `HouseTmp`"; 
	public PHPMysql() 
	{
		try { 
			Class.forName("com.mysql.jdbc.Driver"); 
			//註冊driver 
			con = (Connection) DriverManager.getConnection( 
					"jdbc:mysql://localhost:3306/CloudHouse?useUnicode=true&characterEncoding=UTF-8", 
					"root","ntoucs"); 
			//取得connection
		} 
		catch(ClassNotFoundException e) 
		{ 
			System.out.println("DriverClassNotFound :"+e.toString()); 
		}//有可能會產生sqlexception 
		catch(SQLException x) { 
			System.out.println("Exception :"+x.toString()); 
		} 
	}
	
	
	//完整使用完資料庫後,記得要關閉所有Object 
		//否則在等待Timeout時,可能會有Connection poor的狀況 
		public void close() 
		{ 
			try 
			{ 
				if(rs!=null) 
				{ 
					rs.close(); 
					rs = null; 
				} 
				if(stat!=null) 
				{ 
					stat.close(); 
					stat = null; 
				} 
				if(pst!=null) 
				{ 
					pst.close(); 
					pst = null; 
				} 
			} 
			catch(SQLException e) 
			{ 
				System.out.println("Close Exception :" + e.toString()); 
			} 
		}
		
		public void sqlGenerateFormTitle(String title)
		{
			selectSQL="";
			selectSQL = "SELECT * FROM `";
			selectSQL+=dataBaseName;
			selectSQL+="` ";
			selectSQL+="WHERE Title = '";
			selectSQL+=title;
			selectSQL+="' ";
			
			
		}
		 //新增資料 
		  //可以看看PrepareStatement的使用方式 
		  public void insertTable(String title,String address,String orginURL,int money) 
		  { 
		    try 
		    { 
		      pst = (PreparedStatement) con.prepareStatement(insertdbSQL); 
		      
		      pst.setString(1, title);
		      pst.setString(2, address);
		      pst.setString(3, orginURL);
		      pst.setInt(4, money);
		      pst.executeUpdate(); 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("InsertDB Exception :" + e.toString()); 
		    } 
		  
		  }
		  public int getTitleID(String title)
		  {
			  this.sqlGenerateFormTitle(title);
			  return this.getdataTableID();
		  }
		  
		private int getdataTableID() 
		{ 
			ArrayList tmp=new ArrayList ();
			try 
			{ 
				stat=(Statement) con.createStatement();
				rs = stat.executeQuery(selectSQL);
				int i = 0;
				while(rs.next()) 
				{ 
					tmp.add(rs.getInt("ID"));
					
				}
			} 
			catch(SQLException e) 
			{ 
				System.out.println("DropDB Exception :" + e.toString()); 
			}
			return Integer.parseInt(tmp.get(0).toString());
		
		}
		public void get_URLS(ArrayList tmp,String houseAgency,int max)
		{
			String selectedurl="SELECT `OrginURL` FROM `HouseTmp` WHERE `OrginURL` LIKE \"%";
			selectedurl+=houseAgency;
			selectedurl+="%\"";
			try 
			{ 
				stat=(Statement) con.createStatement();
				rs = stat.executeQuery(selectedurl);
				int i = 1;
				while(rs.next()) 
				{ 
					tmp.add(rs.getString("OrginURL"));
					i++;
					if(i==max)
						break;
				}
			} catch(SQLException e) 
			{ 
				System.out.println("DropDB Exception :" + e.toString()); 
			}
			
			
		}
		public void delete_TableData()
		{
			try 
		    { 
		      stat = (Statement) con.createStatement(); 
		      stat.executeUpdate(this.deleteDataSQL); 
		    } 
		    catch(SQLException e) 
		    { 
		      System.out.println("DropDB Exception :" + e.toString()); 
		    } 
		    
		}
		public void change_TableName(String oldName,String newName)
		{
			try
			{
				stat = (Statement)con.createStatement(); 
		      stat.executeUpdate(
		        "ALTER TABLE "+oldName+" RENAME "+newName);
			}
			catch(Exception e)
			{
				System.err.print("change :"+oldName+"to"+newName);
				System.err.println("change table name exception : " + e.toString());
			
			}
			
		}
	
	
	
	

}
