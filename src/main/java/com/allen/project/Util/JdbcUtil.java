package com.allen.project.Util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;
import java.sql.ResultSet;  
import java.sql.ResultSetMetaData;  
import java.sql.SQLException;
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
        "jdbc:oracle:thin:@10.180.6.151:1521:SBP";  
      String usr = "epms";  
      String pwd = "epms";  
      Connection con = null;  
      try{  
        con = DriverManager.getConnection(url,usr,pwd);  
      }catch(Exception e){  
    	  throw new RuntimeException(e);    
      }  
      return con;  
    }  
    public static void close(Statement stmt,Connection conn){  
        try{  
          if(stmt!=null) stmt.close();  
          if(conn!=null) conn.close();  
        }catch(Exception ex){  
        	throw new RuntimeException(ex);  
        }  
    }  
    public static void close(ResultSet rs, Statement stmt,Connection conn){  
    	try{  
    		if(rs!=null) rs.close();  
    		if(stmt!=null) stmt.close();  
    		if(conn!=null) conn.close();  
    	}catch(Exception ex){  
    		 throw new RuntimeException(ex);    
    	}  
    }  
    public static void close(ResultSet rs, Statement stmt){  
    	try{  
    		if(rs!=null) rs.close();  
    		if(stmt!=null) stmt.close();  
    	}catch(Exception ex){  
    		throw new RuntimeException(ex);    
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
          sb.append("");  
        }  
        System.out.print(sb.toString());  
      }catch(Exception e){  
    	  throw new RuntimeException(e); 
      }  
    }
    
    public static void addErrorLog(Throwable t, String partcode, String errorInfro){
    	Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into ti_back_interface_error_log" +
						"      (id, error_info, error_detail, error_partcode, create_by, create_date)" + 
						" values" + 
						"     (SEQ_TI_BACK_INTERFACE_ERRLOG.Nextval, ?, ?, ?, -1, sysdate)";
		conn = JdbcUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			conn.setAutoCommit(false);
			ps.setString(1, errorInfro);
			ps.setString(2, JdbcUtil.exception(t));
			ps.setString(3, partcode);
			ps.executeUpdate();
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally{
			JdbcUtil.close(ps, conn);
		}

    }
    public static void addErrorLog(Throwable t, String errorInfro){
    	Connection conn = null;
    	PreparedStatement ps = null;
    	String sql = "insert into ti_back_interface_error_log" +
    			"      (id, error_info, error_detail, create_by, create_date)" + 
    			" values" + 
    			"     (SEQ_TI_BACK_INTERFACE_ERRLOG.Nextval, ?, ?, -1, sysdate)";
    	conn = JdbcUtil.getConnection();
    	try {
    		ps = conn.prepareStatement(sql);
    		conn.setAutoCommit(false);
    		ps.setString(1, errorInfro);
    		ps.setString(2, JdbcUtil.exception(t));
    		ps.executeUpdate();
    		conn.commit();
    	} catch (Exception e) {
    		e.printStackTrace();
    		try {
    			conn.rollback();
    		} catch (SQLException e1) {
    			e1.printStackTrace();
    		}
    	} finally{
    		JdbcUtil.close(ps, conn);
    	}
    	
    }
    
    /**
     * 将异常信息转化成字符串
     * @param t
     * @return
     * @throws IOException 
     */
    private static String exception(Throwable t) throws IOException{
        if(t == null)
            return null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            t.printStackTrace(new PrintStream(baos));
        }finally{
            baos.close();
        }
        return baos.toString();
    }
}  