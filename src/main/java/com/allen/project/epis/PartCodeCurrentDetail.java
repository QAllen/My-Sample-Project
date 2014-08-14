/**
 * 
 */
package com.allen.project.epis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.allen.project.Util.JdbcUtil;

/**
 * @author Allen 当前零件信息数据 Table:ti_epis_backinterface_current
 */
public class PartCodeCurrentDetail {
	/**
	 * 获取tt_part_base中的SAP零件号并且ti_epis_backinterface_current没有此零件号
	 * 
	 * @return
	 */
	public List<String> getAddPartCode() {
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		// String sql =
		// "select part_sapcode from tt_part_base tpb where not exists (select 1 from ti_epis_backinterface_current tebc where tebc.part_number = tpb.part_sapcode) and status =1 and  exists(select 1 from tt_part_supp a where a.part_id=tpb.part_id) and CLS_ID is not null";

		String sql = "select part_sapcode from tt_part_base tpb where   status =1 and exists(select 1 from tt_part_supp a where a.part_id=tpb.part_id) and CLS_ID is not null and rownum<1001";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JdbcUtil.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return list;
	}

	/**
	 * 新增数据ti_epis_backinterface_current
	 * 
	 * @param conn
	 * @param partcodeList
	 * @throws SQLException
	 */
	public void addPartCodeCurrent(Connection conn, List<String> partcodeList) throws SQLException {
		String sql = "insert into ti_epis_backinterface_current" + "  (id, create_by, create_date, record_type, part_number)" + " values"
				+ "  (seq_ti_epis_backinterface_cur.nextval, ?, sysdate, ?, ?)";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		for (String partcode : partcodeList) {
			ps.setLong(1, BackInterface.USERID);
			ps.setString(2, BackInterface.RECORD_TYPE);
			ps.setString(3, partcode);
			ps.addBatch();
		}
		ps.executeBatch();
		ps.close();
	}

	/**
	 * 删除数据ti_epis_backinterface_current
	 * 如果tt_part_base中有删除零件号则ti_epis_backinterface_current中也应删除此零件号
	 * 
	 * @param conn
	 * @param partcodeList
	 * @throws SQLException
	 */
	public void delPartCodeCurrent(Connection conn) throws SQLException {
		String sql = "delete from ti_epis_backinterface_current tebc where not exists (select 1 from tt_part_base tpb where tpb.part_sapcode = tebc.part_number and status = 1)";
		PreparedStatement ps = null;
		ps = conn.prepareStatement(sql);
		ps.executeUpdate();
		ps.close();
	}

	/**
	 * 获取零件集合
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<TiEpisBackinterfaceCurrent> getModifyPartCode() {
		List<TiEpisBackinterfaceCurrent> partcodeList = new ArrayList<TiEpisBackinterfaceCurrent>();
		String getCurrentPartSql = "select part_number," + "       production_plant," + "       kind_of_material," + "       available_stock," + "       total_consumption_current_year,"
				+ "       end_of_supply_time," + "       fertigungsort_s," + "       weight," + "       weight_unit," + "       dimension," + "       estimated_weight,"
				+ "       estimated_dimension," + "       stock_zero," + "       product_hierarchie," + "       limited_storing_time" + "  from ti_epis_backinterface_current";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		conn = JdbcUtil.getConnection();
		try {
			ps = conn.prepareStatement(getCurrentPartSql);
			rs = ps.executeQuery();
			while (rs.next()) {
				String part_code = StringUtils.defaultString(rs.getString("part_number"));
				String production_plant = StringUtils.defaultString(rs.getString("production_plant"));// 工厂代码
				String kind_of_material = StringUtils.defaultString(rs.getString("kind_of_material"));// 零件种类HT
																										// KT
				String available_stock = StringUtils.defaultString(rs.getString("available_stock"));// 库存
				String total_consumption_current_year = StringUtils.defaultString(rs.getString("total_consumption_current_year"));// 当年销量
				String end_of_supply_time = StringUtils.defaultString(rs.getString("end_of_supply_time"));// 停供日期
				String fertigungsort_s = StringUtils.defaultString(rs.getString("fertigungsort_s"));// company
																									// code
																									// 14/79
				String weight = StringUtils.defaultString(rs.getString("weight"));// 重量
				String weight_unit = StringUtils.defaultString(rs.getString("weight_unit"));// 重量单位
				String dimension = StringUtils.defaultString(rs.getString("dimension"));// 尺寸
				String estimated_weight = StringUtils.defaultString(rs.getString("estimated_weight"));// 是否预估重量
				String estimated_dimension = StringUtils.defaultString(rs.getString("estimated_dimension"));// 是否预估尺寸
				String stock_zero = StringUtils.defaultString(rs.getString("stock_zero"));// 出零日期
				String product_hierarchie = StringUtils.defaultString(rs.getString("product_hierarchie"));// HTG
				String limited_storing_time = StringUtils.defaultString(rs.getString("limited_storing_time"));// 是否有质保

				TiEpisBackinterfaceCurrent tebf = new TiEpisBackinterfaceCurrent();
				tebf.setPartNumber(part_code);
				tebf.setProductionPlant(production_plant);
				tebf.setKindOfMaterial(kind_of_material);
				tebf.setAvailableStock(available_stock);
				tebf.setTotalConsumptionCurrentYear(total_consumption_current_year);
				tebf.setEndOfSupplyTime(end_of_supply_time);
				tebf.setFertigungsortS(fertigungsort_s);
				tebf.setWeight(weight);
				tebf.setWeightUnit(weight_unit);
				tebf.setDimension(dimension);
				tebf.setEstimatedWeight(estimated_weight);
				tebf.setEstimatedDimension(estimated_dimension);
				tebf.setStockZero(stock_zero);
				tebf.setProductHierarchie(product_hierarchie);
				tebf.setLimitedStoringTime(limited_storing_time);
				partcodeList.add(tebf);

			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return partcodeList;
	}

	/**
	 * 更新反向接口主表零件号、新增、删除
	 * 
	 * @throws SQLException
	 */
	public void operBackInterfaceMaindata() {
		List<String> addPartCode = getAddPartCode();
		Connection conn = JdbcUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			delPartCodeCurrent(conn);// TT_PART_BASE中如果有零件号删除，则反向接口主表也要删除对应数据。
			addPartCodeCurrent(conn, addPartCode);// 反向接口主表新增零件
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(null, conn);
		}
	}

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		// List<TiEpisBackinterfaceCurrent> partcodeList =
		// pccd.getModifyPartCode(conn);//比较零件字段并更新零件,返回记录有更改记录的零件号
		PartCodeCurrentDetail p = new PartCodeCurrentDetail();
		p.operBackInterfaceMaindata();
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
	}

}
