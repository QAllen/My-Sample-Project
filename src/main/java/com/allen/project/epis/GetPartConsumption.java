/**
 * 
 */
package com.allen.project.epis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.allen.project.Util.JdbcUtil;

/**
 * @author Allen
 * 获取零件销量
 * 主要的表：tt_shippingsheet_item
 */
public class GetPartConsumption {
	
	/**
	 * 获取当年年份
	 * @return
	 */
	public  String getCurrentYear(){
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		return year+"";
	}
	/**
	 * 获取前一年年份
	 * @return
	 */
	public  String getPreviousYear(){
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		int year = cal.get(Calendar.YEAR);
		return year+"";
	}
	/**
	 * 通过指定年份获取销量
	 * @param year 指定年份
	 * @return
	 */
	public  List<Map<String,String>> getPartConsumptionCount(String year){
		System.out.println("-------------------------------getPartConsumptionCount获取零件销量开始-----------------------------------------");
		Connection conn = null;
		List<Map<String,String>> list = new ArrayList<Map<String,String>>();
		String sql = "select part_code,sum(part_count) as part_count from sbpopt.tt_shippingsheet_item where to_char(create_date,'yyyy') = ? group by part_code";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, year);
			rs = ps.executeQuery();
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("PART_CODE", rs.getString("part_code"));
				map.put("PART_COUNT", rs.getString("part_count"));
				list.add(map);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs, ps, conn);
		}
		System.out.println("-------------------------------getPartConsumptionCount获取零件销量结束-----------------------------------------");
		return list;
	}
	/**
	 * 更新操作
	 * @param partSapCode 零件号
	 * @param part_count 销量
	 * @param year 年份
	 * @param conn
	 * @throws SQLException
	 */
	public void updatePartSale(String partSapCode, String part_count, String year, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update ti_part_sale" +
						"          set part_count = ?," + 
						"              update_by = ?," + 
						"              update_date = sysdate" + 
						"           where part_sapcode = ? and year = ?";

		ps = conn.prepareStatement(sql);
		ps.setString(1, part_count);
		ps.setLong(2,BackInterface.USERID);
		ps.setString(3,partSapCode);
		ps.setString(4,year);
		ps.executeUpdate();
		ps.close();
	}
	/**
	 * 新增操作
	 * @param partSapCode 零件号
	 * @param part_count 销量
	 * @param year 年份
	 * @param conn
	 * @throws SQLException
	 */
	public void addPartSale(String partSapCode, String part_count, String year, Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "insert into ti_part_sale" +
						"(id, part_sapcode, part_count, year, create_by, create_date) " + 
						" values" + 
						"  (seq_ti_part_sale.nextval, ?, ?, ?, ?, sysdate)";
		ps = conn.prepareStatement(sql);
		ps.setString(1, partSapCode);
		ps.setString(2, part_count);
		ps.setString(3, year);
		ps.setLong(4, BackInterface.USERID);
		ps.execute();
		ps.close();
	}
	/**
	 * 根据年份获取TI_PART_SALE 零件号
	 * @year 年份
	 * @return
	 */
	public List<String> getPartCodeFromPartSale(String year){
		List<String> list = new ArrayList<String>();
		String sql = "select part_sapcode from  TI_PART_SALE where year = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = JdbcUtil.getConnection();
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, year);
			rs = ps.executeQuery();
			while(rs.next()){
				String partCode = rs.getString(1);
				list.add(partCode);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtil.close(rs, ps, conn);
		}
		return list;
	}
	/**
	 * 获取某年销量
	 * @param year
	 */
	public void modifyPartSale(String year){
		List<Map<String,String>> totalCurrent = getPartConsumptionCount(year);//某年销量
		List<String> part_sapcodeList = getPartCodeFromPartSale(year);//TI_PART_SALE 某年的part_sapcode
		Connection conn = null;
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			for(Map<String,String> map:totalCurrent){
				String part_sapcode = map.get("PART_CODE");//零件号
				String part_count = map.get("PART_COUNT");//销量
				if(part_sapcodeList.contains(part_sapcode)){//存在做更新操作
					updatePartSale(part_sapcode,part_count,year,conn);
				}else{//新增操作
					addPartSale(part_sapcode,part_count,year,conn);
				}
			}
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally{
			JdbcUtil.close(null, conn);
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GetPartConsumption gpc = new GetPartConsumption();
//		List<Map<String,String>> list = gpc.getPartConsumptionCount(gpc.getCurrentYear());
//		for(Map<String,String> map:list){
//			System.out.println(map.get("PART_CODE")+"========"+map.get("PART_COUNT"));
//		}
		gpc.modifyPartSale(gpc.getCurrentYear());
			
	}

}
