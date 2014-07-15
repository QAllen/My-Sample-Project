/**
 * 
 */
package com.allen.project.epis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.allen.project.Util.JdbcUtil;

/**
 * @author Allen
 *  当前零件信息数据
 *  Table:ti_epis_backinterface_current
 */
public class PartCodeCurrentDetail {
			/**
			 * 获取tt_part_base中的SAP零件号并且ti_epis_backinterface_current没有此零件号
			 * @return
			 */
            public List<String> getAddPartCode(){
            	List<String> list = new ArrayList<String>();
            	Connection conn = null;
        		String sql = "select part_sapcode from tt_part_base tpb where not exists (select 1 from ti_epis_backinterface_current tebc where tebc.part_number = tpb.part_sapcode) and status =1";
        		PreparedStatement ps = null;
        		ResultSet rs = null;
        		try {
        			conn = JdbcUtil.getConnection();
        			ps = conn.prepareStatement(sql);
        			rs = ps.executeQuery();
        			while(rs.next()){
        				list.add(rs.getString(1));
        			}
        		} catch (SQLException e) {
        			e.printStackTrace();
        		} finally{
        			JdbcUtil.close(rs, ps, conn);
        		}
            	return list;
            }
            /**
             * 新增数据ti_epis_backinterface_current 
             * @param conn
             * @param partcodeList
             * @throws SQLException
             */
            public void addPartCodeCurrent(Connection conn, List<String> partcodeList) throws SQLException{
            	String sql = "insert into ti_epis_backinterface_current" +
            					"  (id, create_by, create_date, record_type, part_number)" + 
            					" values" + 
            					"  (seq_ti_epis_backinterface_cur.nextval, ?, sysdate, ?, ?)";
            	PreparedStatement ps = null;
            	ps = conn.prepareStatement(sql);
            	for(String partcode : partcodeList){
            		ps.setLong(1, BackInterface.USERID);
            		ps.setString(2, BackInterface.RECORD_TYPE);
            		ps.setString(3, partcode);
            		ps.addBatch();
            	}
            	ps.executeBatch();
            	ps.close();
            }
            /**
             * 获取零件属性值有变化的零件集合
             * @param conn
             * @return
             * @throws SQLException 
             */
            public Set<String> getModifyPartCode(Connection conn) throws SQLException{
            	Set<String> partcodeList = new HashSet<String>();
            	String getCurrentPartSql = "select part_number," +
			            					"       production_plant," +
			            					"       kind_of_material," + 
			            					"       available_stock," + 
			            					"       total_consumption_current_year," + 
			            					"       total_consumption," + 
			            					"       end_of_supply_time," + 
			            					"       fertigungsort_s," + 
			            					"       price_euro," + 
			            					"       price_date," + 
			            					"       weight," + 
			            					"       weight_unit," + 
			            					"       dimension," + 
			            					"       estimated_weight," + 
			            					"       estimated_dimension," + 
			            					"       stock_zero," + 
			            					"       limited_storing_time," + 
			            					"       product_hierarchie" + 
			            					"  from ti_epis_backinterface_current";
            	PreparedStatement ps = null;
            	ResultSet rs = null;
            	ps = conn.prepareStatement(getCurrentPartSql);
            	rs = ps.executeQuery();
            	while(rs.next()){
            		String part_code = StringUtils.defaultString(rs.getString("part_number"));
            		String production_plant = StringUtils.defaultString(rs.getString("production_plant"));
            		String kind_of_material = StringUtils.defaultString(rs.getString("kind_of_material"));
            		String available_stock = StringUtils.defaultString(rs.getString("available_stock"));
            		String total_consumption_current_year = StringUtils.defaultString(rs.getString("total_consumption_current_year"));
            		String total_consumption = StringUtils.defaultString(rs.getString("total_consumption"));
            		String end_of_supply_time = StringUtils.defaultString(rs.getString("end_of_supply_time"));
            		String fertigungsort_s = StringUtils.defaultString(rs.getString("fertigungsort_s"));//company code 14/79
            		String price_euro = StringUtils.defaultString(rs.getString("price_euro"));
            		String price_date = StringUtils.defaultString(rs.getString("price_date"));
            		String weight = StringUtils.defaultString(rs.getString("weight"));
            		String weight_unit = StringUtils.defaultString(rs.getString("weight_unit"));
            		String dimension = StringUtils.defaultString(rs.getString("dimension"));
            		String estimated_weight = StringUtils.defaultString(rs.getString("estimated_weight"));
            		String estimated_dimension = StringUtils.defaultString(rs.getString("estimated_dimension"));
            		String stock_zero = StringUtils.defaultString(rs.getString("stock_zero"));
            		String limited_storing_time = StringUtils.defaultString(rs.getString("limited_storing_time"));
            		String product_hierarchie = StringUtils.defaultString(rs.getString("product_hierarchie"));
            		//比较工厂代码
            		if(compareProductionPlant(part_code,production_plant,conn)){
            			partcodeList.add(part_code);
            		}
            		//比较库存
            		if(compareAvailableStock(part_code,available_stock,conn)){
            			partcodeList.add(part_code);
            		}
            	}
            	rs.close();
            	ps.close();
            	return partcodeList;
            }
            /**
             * 比较工厂代码，由于工厂代码默认值为7810，直接比较7810可以判断出哪些是新增的零件号
             * @param part_code
             * @param production_plant 工厂代码
             * @param conn
             * @return
             * @throws SQLException 
             */
            public boolean compareProductionPlant(String part_code, String production_plant, Connection conn) throws SQLException{
            	boolean flag = false;
            	String productionPlantNew = "7810";
            	if(!productionPlantNew.equals(production_plant)){
            		PreparedStatement ps = null;
                	String sql = "update ti_epis_backinterface_current set production_plant = ?,update_by = ?,update_date=sysdate where part_number = ?";
                	ps = conn.prepareStatement(sql);
                	ps.setString(1, productionPlantNew);
                	ps.setLong(2, BackInterface.USERID);
                	ps.setString(3, part_code);
                	ps.executeUpdate();
                	ps.close();
                	flag = true;
            	}
            	return flag;
            }
            /**
             * 比较库存数据是否一致，不一致则更新ti_epis_backinterface_current对应数据
             * @param part_code
             * @param available_stock 当前库存
             * @param conn
             * @return true不一致，false一致
             * @throws SQLException 
             */
            public boolean compareAvailableStock(String part_code, String available_stock, Connection conn) throws SQLException{
                    boolean flag = false;
                    String availableStockNew = getAvailableStockByPartCode(part_code,conn);
                    if(!available_stock.equals(availableStockNew)){//不一样就更新
                    	PreparedStatement ps = null;
                    	String sql = "update ti_epis_backinterface_current set available_stock = ?,update_by = ?,update_date=sysdate where part_number = ?";
                    	ps = conn.prepareStatement(sql);
                    	ps.setString(1, availableStockNew);
                    	ps.setLong(2, BackInterface.USERID);
                    	ps.setString(3, part_code);
                    	ps.executeUpdate();
                    	ps.close();
                    	flag = true;
                    }
                    return flag;
            }
            /**
             * 通过零件号获取现在库存
             * @param part_code
             * @param conn
             * @return
             * @throws SQLException 
             */
            public String getAvailableStockByPartCode(String part_code, Connection conn) throws SQLException{
            	String availableStock = "";
            	String sql = "select nvl(sh_total,0)+nvl(XA_TOTAL,0)+nvl(TJ_TOTAL,0)+nvl(GZ_TOTAL,0)+nvl(BJ_TOTAL,0)+nvl(HD_TOTAL,0)+nvl(JS_TOTAL,0)+nvl(RDC_TOTAL,0) "
            			    + " from TI_PART_SAP_SUBCENTER_STOCK t where part_code = ?";
            	PreparedStatement ps = null;
            	ResultSet rs = null;
            	ps = conn.prepareStatement(sql);
            	ps.setString(1, part_code);
            	rs = ps.executeQuery();
            	if(rs.next()){
            		availableStock = rs.getString(1);
            	}
            	rs.close();
            	ps.close();
            	return availableStock;
            }
            
            /**
             * 插入零件增量表
             * @param conn
             * @param partcodeList
             * @throws SQLException
             */
            public void addPartCodeIncreament(Connection conn, Set<String> partcodeList) throws SQLException{
            	String sql = "insert into ti_part_increament" +
            					"  (id, part_sapcode, status, create_by, create_date)" + 
            					" values" + 
            					"  (seq_ti_part_increament.nextval, ?, ?, ?, sysdate)";

            	PreparedStatement ps = null;
            	ps = conn.prepareStatement(sql);
            	for(String partcode : partcodeList){
            		ps.setString(1, partcode);
            		ps.setString(2, "S");
            		ps.setLong(3, BackInterface.USERID);
            		ps.addBatch();
            	}
            	ps.executeBatch();
            	ps.close();
            }
            
            public static void main(String[] args) {
            	long startTime = System.currentTimeMillis(); // 获取开始时间
            	PartCodeCurrentDetail pccd = new PartCodeCurrentDetail();
            	List<String> a = pccd.getAddPartCode();
            	Connection conn = JdbcUtil.getConnection();
            	try {
            		conn.setAutoCommit(false);
					pccd.addPartCodeCurrent(conn,a);//新增零件
					Set<String> partcodeList = pccd.getModifyPartCode(conn);//比较零件字段并更新零件,返回记录有更改记录的零件号
					System.out.println(partcodeList.size());
					pccd.addPartCodeIncreament(conn,partcodeList);//插入增量表
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
            	long endTime = System.currentTimeMillis(); // 获取结束时间
        		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
			}
            
}
