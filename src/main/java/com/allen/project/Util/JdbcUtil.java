package com.allen.project.Util;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.Statement;  
  
public class JdbcUtil  
{  
    static{  
      String driver = "oracle.jdbc.driver.OracleDriver";  
      try{  
        Class.forName(driver);  
      }catch(Exception e){  
        e.printStackTrace();  
      }  
    }  
    public static Connection getConnection(){  
      String url =   
        "jdbc:oracle:thin:@127.0.0.1:1521:orcl";  
      String usr = "test";  
      String pwd = "test";  
      Connection con = null;  
      try{  
        con = DriverManager.getConnection(url,usr,pwd);  
      }catch(Exception e){  
        e.printStackTrace();  
      }  
      return con;  
    }  
    public static void close(Statement stmt,Connection conn){  
        try{  
          if(stmt!=null) stmt.close();  
          if(conn!=null) conn.close();  
        }catch(Exception ex){  
          ex.printStackTrace();  
        }  
    }  
    public static void close(ResultSet rs, Statement stmt,Connection conn){  
    	try{  
    		if(rs!=null) rs.close();  
    		if(stmt!=null) stmt.close();  
    		if(conn!=null) conn.close();  
    	}catch(Exception ex){  
    		ex.printStackTrace();  
    	}  
    }  
      
    public static void printRs(ResultSet rs){  
      try{  
        StringBuffer sb = new StringBuffer();  
        ResultSetMetaData meta = rs.getMetaData();  
        int cols = meta.getColumnCount();  
        while(rs.next()){  
          for(int i=1;i<=cols;i++){  
            sb.append(meta.getColumnName(i)+"->");  
            sb.append(rs.getString(i)+"  ");
          }  
          sb.append("\n");  
        }  
        System.out.print(sb.toString());  
      }catch(Exception e){  
        e.printStackTrace();  
      }  
    }  
}  