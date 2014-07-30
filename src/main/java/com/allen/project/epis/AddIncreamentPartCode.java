package com.allen.project.epis;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.allen.project.Util.JdbcUtil;

public class AddIncreamentPartCode {
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis(); // 获取开始时间
		AddIncreamentPartCode a = new AddIncreamentPartCode();
		PartCodeCurrentDetail b = new PartCodeCurrentDetail();
		a.gerTransferPartCode(b.getModifyPartCode());
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
//		DecimalFormat df = new DecimalFormat("#.000");
//		System.out.println(df.format(Double.valueOf("00042.111")));
	}
	
	/**
	 * 比较零件号各个属性值是否改变，并插入要传输的增量表中。
	 */
	public void gerTransferPartCode(
			List<TiEpisBackinterfaceCurrent> partcodeList) {
		Connection conn = null;
		conn = JdbcUtil.getConnection();
		try {
			for (TiEpisBackinterfaceCurrent tebfc : partcodeList) {
				String part_code = tebfc.getPartNumber();
				String production_plant = tebfc.getProductionPlant();// 工厂代码
				String kind_of_material = tebfc.getKindOfMaterial();// 零件种类HT KT
				String available_stock = tebfc.getAvailableStock();// 库存
				String total_consumption_current_year = tebfc
						.getTotalConsumptionCurrentYear();// 当年销量
				String end_of_supply_time = tebfc.getEndOfSupplyTime();// 停供日期
				String fertigungsort_s = tebfc.getFertigungsortS();// company
																	// code
																	// 14/79
				String weight = tebfc.getWeight();// 重量
				String weight_unit = tebfc.getWeightUnit();// 重量单位
				String dimension = tebfc.getDimension();// 尺寸
				String estimated_weight = tebfc.getEstimatedWeight();// 是否预估重量
				String estimated_dimension = tebfc.getEstimatedDimension();// 是否预估尺寸
				String stock_zero = tebfc.getStockZero();// 出零日期
				String product_hierarchie = tebfc.getProductHierarchie();// HTG
				String limited_storing_time = tebfc.getLimitedStoringTime();// 是否有质保
				try {
					conn.setAutoCommit(false);
					// 比较工厂代码
					boolean a = compareProductionPlant(part_code,
							production_plant, conn);
					// 比较库存
					boolean b = compareAvailableStock(part_code,
							available_stock, conn);
					// 比较当年销量
					boolean c = compareCurrentSale(part_code,
							total_consumption_current_year, conn);
					// 比较停供日期
					boolean d = compareStopDate(part_code, end_of_supply_time,
							conn);
					// 比较零件类型
					String kind_of_material_companycode = kind_of_material
							+ fertigungsort_s;
					boolean e = compareKindOfMaterial(part_code,
							kind_of_material_companycode, conn);
					// 比较HTG
					boolean f = compareHTG(part_code, product_hierarchie, conn);

					// 比较出零日期
					boolean g = compareStockZero(part_code, stock_zero, conn);
					
					//比较尺寸,并且会更新是否预估尺寸
					boolean h = compareDimension(part_code, dimension, conn);
					
					//比较重量，并且会更新重量单位、是否预估重量
					boolean i = compareWeight(part_code,weight, conn);
					
					//比较是否有质保日期
					boolean j = compareLimitedStoringTime(part_code,limited_storing_time,conn);

					// 如果有不一样的属性信息则插入增量表中
					if (a || b || c || d || e || f || g || h || i || j) {
						System.out.println(part_code + "==a:" + a + "==b:" + b
								+ "==c:" + c + "==d:" + d + "==e:" + e + "==f:"
								+ f + "==g:" + g+ "==h:" + h+ "==i:" + i+ "==j:" + j);
						addPartCodeIncreament(conn, part_code);
					}
					conn.commit();
				} catch (Exception e) {
					e.printStackTrace();
					JdbcUtil.addErrorLog(e, part_code,
							"AddIncreamentPartCode报错，零件号有问题："+part_code);
					try {
						if (conn != null) {
							conn.rollback();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}

				}

			}
		} finally {
			JdbcUtil.close(null, conn);
		}
	}

	/**
	 * 比较工厂代码，由于工厂代码默认值为7810，直接比较7810可以判断出哪些是新增的零件号
	 * 
	 * @param part_code
	 * @param production_plant
	 *            工厂代码
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public boolean compareProductionPlant(String part_code,
			String production_plant, Connection conn) throws SQLException {
		boolean flag = false;
		String productionPlantNew = "7810";
		if (!productionPlantNew.equals(production_plant)) {
			String sql = "update ti_epis_backinterface_current set production_plant = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, productionPlantNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}

		}
		return flag;
	}

	/**
	 * 比较库存数据是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param available_stock
	 *            当前库存
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 */
	public boolean compareAvailableStock(String part_code,
			String available_stock, Connection conn) throws SQLException {
		boolean flag = false;
		String availableStockNew = StringUtils
				.defaultString(getAvailableStockByPartCode(part_code, conn));
		if (!available_stock.equals(availableStockNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set available_stock = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, availableStockNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}

		}
		return flag;
	}

	/**
	 * 通过零件号获取现在库存
	 * 
	 * @param part_code
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public String getAvailableStockByPartCode(String part_code, Connection conn)
			throws SQLException {
		String availableStock = "";
		String sql = "select nvl(sh_total,0)+nvl(XA_TOTAL,0)+nvl(TJ_TOTAL,0)+nvl(GZ_TOTAL,0)+nvl(BJ_TOTAL,0)+nvl(HD_TOTAL,0)+nvl(JS_TOTAL,0)+nvl(RDC_TOTAL,0) "
				+ " from TI_PART_SAP_SUBCENTER_STOCK t where part_code = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				availableStock = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return availableStock;
	}

	/**
	 * 比较当年销量数据是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param available_stock
	 *            当前库存
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 */
	public boolean compareCurrentSale(String part_code, String currentSale,
			Connection conn) throws SQLException {
		boolean flag = false;
		String currentSaleNew = StringUtils
				.defaultString(getPartSalesCurrentYear(part_code, conn));
		if (!currentSale.equals(currentSaleNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set total_consumption_current_year = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, currentSaleNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}

		}
		return flag;
	}

	/**
	 * 通过零件号获取当年销量
	 * 
	 * @param part_code
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public String getPartSalesCurrentYear(String part_code, Connection conn)
			throws SQLException {
		String part_count = "";
		String sql = "select part_count from ti_part_sale where part_sapcode = ? and year = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			ps.setString(2, year + "");
			rs = ps.executeQuery();
			if (rs.next()) {
				part_count = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return part_count;
	}

	/**
	 * 比较停供日期数据是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param stop_date
	 *            当前停供日期
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 */
	public boolean compareStopDate(String part_code, String stop_date,
			Connection conn) throws SQLException {
		boolean flag = false;
		String stop_dateNew = StringUtils.defaultString(getPartStopDate(
				part_code, conn));
		if (!stop_date.equals(stop_dateNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set end_of_supply_time = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, stop_dateNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}

	/**
	 * 比较零件类型数据是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param kindOfMaterialCompanyCode
	 *            当前零件类型+公司代码
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 */
	public boolean compareKindOfMaterial(String part_code,
			String kindOfMaterialCompanyCode, Connection conn)
			throws SQLException {
		boolean flag = false;
		String kindOfMaterialCompanyCodeNew = StringUtils
				.defaultString(getKindOfMaterial(part_code, conn));
		if (!kindOfMaterialCompanyCode.equals(kindOfMaterialCompanyCodeNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set KIND_OF_MATERIAL = ?,FERTIGUNGSORT_S = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				String kind_of_material = kindOfMaterialCompanyCodeNew
						.substring(0, 2);// 零件类型 HT、KT
				String FERTIGUNGSORT_S = kindOfMaterialCompanyCodeNew
						.substring(2, kindOfMaterialCompanyCodeNew.length());// 公司代码：78
																				// 14
																				// 31等
				ps = conn.prepareStatement(sql);
				ps.setString(1, kind_of_material);
				ps.setString(2, StringUtils.defaultString(FERTIGUNGSORT_S));
				ps.setLong(3, BackInterface.USERID);
				ps.setString(4, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}

	/**
	 * 比较零件HTG是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param kindOfMaterialCompanyCode
	 *            当前零件类型+公司代码
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 */
	public boolean compareHTG(String part_code, String product_hierarchie,
			Connection conn) throws SQLException {
		boolean flag = false;
		String product_hierarchieNew = StringUtils.defaultString(getHtg(conn,
				part_code));
		if (!product_hierarchie.equals(product_hierarchieNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set PRODUCT_HIERARCHIE = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, product_hierarchieNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}

	/**
	 * 比较出零日期是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param stock_zero
	 *            当前出零日期
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean compareStockZero(String part_code, String stock_zero,
			Connection conn) throws SQLException, ParseException {
		boolean flag = false;
		String stock_zeroNew = StringUtils.defaultString(getStockZeroDate(conn,
				part_code));
		if (!stock_zero.equals(stock_zeroNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set STOCK_ZERO = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, stock_zeroNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}
	/**
	 * 比较尺寸是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param dimension 当前尺寸
	 *            
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean compareDimension(String part_code, String dimension,
			Connection conn) throws SQLException, ParseException {
		boolean flag = false;
		String dimensionNew = StringUtils.defaultString(getDimension(conn,
				part_code));
		String estimatedDimension = StringUtils.defaultString(getEstimatedDimension(conn,part_code));
		if(StringUtils.isEmpty(dimensionNew)){//如果尺寸为空值则给一个默认值00333x00333x00333
			dimensionNew = "00333X00333X00333";
			estimatedDimension = "J";
		}
		if (!dimension.equals(dimensionNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set DIMENSION = ?,ESTIMATED_DIMENSION = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, dimensionNew);
				ps.setString(2, estimatedDimension);
				ps.setLong(3, BackInterface.USERID);
				ps.setString(4, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}
	/**
	 * 比较重量是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param weight 当前重量
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean compareWeight(String part_code, String weight,
			Connection conn) throws SQLException, ParseException {
		boolean flag = false;
		String weightNew = StringUtils.defaultString(getWeight(conn,
				part_code));
		String estimatedWeight = StringUtils.defaultString(getEstimatedWeight(conn,part_code));
		String weight_unit = StringUtils.defaultString(getWeightUnit(conn,part_code));
		if(StringUtils.isEmpty(weightNew)){//如果重量为空值则给一个默认值333.000
			weightNew = "333.000";
			estimatedWeight = "J";
			weight_unit = "G";
		}
		if (!weight.equals(weightNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set WEIGHT = ?,ESTIMATED_WEIGHT = ?,WEIGHT_UNIT = ?,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, weightNew);
				ps.setString(2, estimatedWeight);
				ps.setString(3, weight_unit);
				ps.setLong(4, BackInterface.USERID);
				ps.setString(5, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}
	/**
	 * 比较是否有质量有效期是否一致，不一致则更新ti_epis_backinterface_current对应数据
	 * 
	 * @param part_code
	 * @param weight 当前重量
	 * @param conn
	 * @return true不一致，false一致
	 * @throws SQLException
	 * @throws ParseException
	 */
	public boolean compareLimitedStoringTime(String part_code, String limited_storing_time,
			Connection conn) throws SQLException, ParseException {
		boolean flag = false;
		String limitedStoringTimeNew = StringUtils.defaultString(getLimitedStoringTime(conn,
				part_code));
		if(!StringUtils.isEmpty(limitedStoringTimeNew)){//如果不为空则默认J
			limitedStoringTimeNew = "J";
		}
		
		if (!limited_storing_time.equals(limitedStoringTimeNew)) {// 不一样就更新
			String sql = "update ti_epis_backinterface_current set LIMITED_STORING_TIME = ? ,update_by = ?,update_date=sysdate where part_number = ?";
			PreparedStatement ps = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, limitedStoringTimeNew);
				ps.setLong(2, BackInterface.USERID);
				ps.setString(3, part_code);
				ps.executeUpdate();
				flag = true;
			} finally {
				ps.close();
			}
		}
		return flag;
	}

	/**
	 * 通过零件号获取停供日期
	 * 
	 * @param part_code
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public String getPartStopDate(String part_code, Connection conn)
			throws SQLException {
		String stop_date = "";
		String sql = "select stop_date from tt_part_base where part_sapcode = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				stop_date = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return stop_date;
	}

	/**
	 * 获取零件类型
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getKindOfMaterial(String part_code, Connection conn)
			throws SQLException {
		String km = "";
		String forward = getSA2KindOfMaterial(conn, part_code);
		if (StringUtils.isEmpty(forward)) {
			String clsId = "";
			clsId = getClsId(conn, part_code);
			if ("1".equals(clsId)) {// 国产件默认KT78
				km = "KT78";
			} else if ("3".equals(clsId)) {// 自制件默认HT78
				km = "HT78";
			} else if ("2".equals(clsId)) {// 进口件查供应商是K14、K31、K51的，如果不是这些则默认KT14
				String supp = getSupp(conn, part_code);
				if ("K14".equals(supp)) {
					km = "KT14";
				} else if ("K31".equals(supp)) {
					km = "KT31";
				} else if ("K51".equals(supp)) {
					km = "KT51";
				} else {
					km = "KT14";
				}
			}
		} else {
			km = forward;
		}
		return km;
	}

	/**
	 * 获得零件的供应商
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getSupp(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select ts.supp_sap_code" + " from tt_part_base tpb"
				+ " inner join tt_part_supp tps on tpb.part_id = tps.part_id"
				+ " inner join tm_supplier ts on tps.supp_id = ts.supp_id"
				+ " where  tpb.part_sapcode = ? and rownum = 1";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 从EPS主数据获取零件clsid： 1 国产件 2 进口件 3 自制件
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getClsId(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select cls_id from tt_part_base where part_sapcode = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的零件类型
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2KindOfMaterial(Connection conn, String part_code)
			throws SQLException {
		String sa2km = "";
		String sql = "select purchase_type||part_epis from ti_sa2_biz_back_interface where MATERIAL = ? and purchase_type is not null";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				sa2km = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return sa2km;
	}

	/**
	 * 先看sa2接口中是否有htg 没有则默认xxx
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getHtg(Connection conn, String part_code) throws SQLException {
		String htg = "";
		String forward = getSA2HTG(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 没有查到值则默认格式化后的xxx
			htg = "    X   XX     XXX";
		} else {
			htg = forward;
		}

		return htg;
	}

	/**
	 * 获取正向接口中的零件HTG
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2HTG(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select '    '||SUBSTR(decode(PRODUCT_HIERARCHY,'','XXX',PRODUCT_HIERARCHY),1,1)||'   '||SUBSTR(decode(PRODUCT_HIERARCHY,'','XXX',PRODUCT_HIERARCHY),1,2)||'     '||SUBSTR(decode(PRODUCT_HIERARCHY,'','XXX',PRODUCT_HIERARCHY),1,3) PRODUCT_HIERARCHY from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取出零日期，正向接口中有则取正向接口，没有则静态出零+SAP接口出零
	 * 
	 * @param conn
	 * @param part_code
	 * @return YYYYMMDD格式的日期
	 * @throws SQLException
	 * @throws ParseException
	 */
	public String getStockZeroDate(Connection conn, String part_code)
			throws SQLException, ParseException {
		String stockzero = "";
		String forward = getSA2StockZeroDate(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 没有查到正向接口值则静态出零+SAP接口出零
			boolean staticZero = getStaticStockZero(conn, part_code);
			boolean dynamicZero = getDynamicStockZero(conn, part_code);
			if (staticZero && dynamicZero) {
				SimpleDateFormat stockZeroDate = new SimpleDateFormat(
						"yyyyMMdd");
				stockzero = stockZeroDate.format(new Date());
			}
		} else {
			String temp = forward;
			stockzero = strTransDate(temp);
		}

		return stockzero;
	}

	/**
	 * 静态出零，各个分中心在运都为0则为出零
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public boolean getStaticStockZero(Connection conn, String part_code)
			throws SQLException {
		boolean flag = false;
		String temp = "";
		String sql = " select nvl(sh_road_num,0)+nvl(xa_road_num,0)+nvl(tj_road_num,0)+nvl(gz_road_num,0)+nvl(bj_road_num,0)+nvl(js_road_num,0) from TI_PART_SAP_SUBCENTER_STOCK t"
				+ " where part_code = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
			if ("0".equals(temp)) {// 如果在运相加都为0则为静态出零
				flag = true;
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return flag;
	}

	/**
	 * 动态出零，查询SAP接口中采购组为出零的
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public boolean getDynamicStockZero(Connection conn, String part_code)
			throws SQLException {
		boolean flag = false;
		String temp = "";
		String sql = "select purchasing_group from TT_MATERIAL_PLANTDAT where part_code = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
			if (getPurchasingGroupZero(conn).contains(temp)) {// 如果出零采购组包含零件的采购组则出零
				flag = true;
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return flag;
	}

	/**
	 * 查询维护出零采购组的数据
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public List<String> getPurchasingGroupZero(Connection conn)
			throws SQLException {
		List<String> temp = new ArrayList<String>();
		String sql = "select purchasing_group from TI_Purchasing_Group";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				temp.add(rs.getString(1));
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的零件出零日期（DDMMYYYY）
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2StockZeroDate(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select run_out_date from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 更新是否预估尺寸 J/N
	 * 
	 * @param conn
	 * @param part_code
	 * @throws SQLException
	 */
	public void updateEstimatedDimension(Connection conn, String part_code,
			String estimate) throws SQLException {
		String sql = "update ti_epis_backinterface_current set ESTIMATED_DIMENSION = ?,update_by = ?,update_date=sysdate where part_number = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, estimate);
			ps.setLong(2, BackInterface.USERID);
			ps.setString(3, part_code);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * 更新是否预估重量 J/N
	 * 
	 * @param conn
	 * @param part_code
	 * @throws SQLException
	 */
	public void updateEstimatedWeight(Connection conn, String part_code,
			String estimate) throws SQLException {
		String sql = "update ti_epis_backinterface_current set ESTIMATED_WEIGHT = ?,update_by = ?,update_date=sysdate where part_number = ?";
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, estimate);
			ps.setLong(2, BackInterface.USERID);
			ps.setString(3, part_code);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * 获取零件尺寸信息
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getDimension(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String forward = getSA2Dimension(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 如果正向接口中没有尺寸则查EPS中的
			String length = StringUtils
					.defaultString(getLength(conn, part_code));
			String width = StringUtils.defaultString(getWidth(conn, part_code));
			String height = StringUtils
					.defaultString(getHeight(conn, part_code));
			if (!StringUtils.isEmpty(length) && !StringUtils.isEmpty(width)
					&& !StringUtils.isEmpty(height)) {// 长宽高都不为空时返回格式化后的尺寸，否则返回空值
				temp = length + "X" + width + "X" + height;
			}else{
				temp = "";
			}

		} else {
			temp = forward;
		}
		return temp;
	}
	/**
	 * 获取零件重量信息
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getWeight(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String forward = getSA2Weight(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 如果正向接口中没有重量则查EPS中的净重
			String sql = " select  to_char(net_weight, 'fm999990.000') from tt_part_base tpb"
					+ " inner join tt_part_phy tpp on tpb.part_id = tpp.part_id"
					+ " where tpb.part_sapcode = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, part_code);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getString(1);
				}
			} finally {
				JdbcUtil.close(rs, ps);
			}
			
		} else {
			temp = forward;
		}
		return temp;
	}
	
	/**
	 * 获取零件预估尺寸
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getEstimatedDimension(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String forward = getSA2EstimatedDimension(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 如果正向接口中没有是否预估尺寸则默认给N
			temp = "N";
		} else {
			temp = forward;
		}
		return temp;
	}
	/**
	 * 获取零件预估重量
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getEstimatedWeight(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String forward = getSA2EstimatedWeight(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 如果正向接口中没有是否预估重量则默认给N
			temp = "N";
		} else {
			temp = forward;
		}
		return temp;
	}
	/**
	 * 获取零件重量单位
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getWeightUnit(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String forward = getSA2UnitWeigth(conn, part_code);
		if (StringUtils.isEmpty(forward)) {// 如果正向接口中没有重量则查询eps的重量单位
			String sql = " select  weight_unit from tt_part_base tpb"
					+ " inner join tt_part_phy tpp on tpb.part_id = tpp.part_id"
					+ " where tpb.part_sapcode = ?";
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = conn.prepareStatement(sql);
				ps.setString(1, part_code);
				rs = ps.executeQuery();
				if (rs.next()) {
					temp = rs.getString(1);
				}
				if(StringUtils.isEmpty(temp)){
					temp = "G";
				}
				
			} finally {
				JdbcUtil.close(rs, ps);
			}
		} else {
			temp = forward;
		}
		return temp;
	}

	/**
	 * 获取零件长度，舍去小数点后位数，长度为5位
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getLength(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = " select  lpad(TRUNC(tpp.length,0),5,'0') from tt_part_base tpb"
				+ " inner join tt_part_phy tpp on tpb.part_id = tpp.part_id"
				+ " where tpb.part_sapcode = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取零件宽度，舍去小数点后位数，长度为5位
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getWidth(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = " select  lpad(TRUNC(tpp.width,0),5,'0') from tt_part_base tpb"
				+ " inner join tt_part_phy tpp on tpb.part_id = tpp.part_id"
				+ " where tpb.part_sapcode = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取零件高度，舍去小数点后位数，长度为5位
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getHeight(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = " select  lpad(TRUNC(tpp.height,0),5,'0') from tt_part_base tpb"
				+ " inner join tt_part_phy tpp on tpb.part_id = tpp.part_id"
				+ " where tpb.part_sapcode = ?";

		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}
	/**
	 * 是否有质保有效期
	 * 
	 * @param conn
	 * @param part_code
	 * @return
	 * @throws SQLException
	 */
	public String getLimitedStoringTime(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select trim(warray_validtime) from tt_part_base where part_sapcode = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的是否预估尺寸
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2EstimatedDimension(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select ASSESSED_DIMENSION from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的是否预估重量
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2EstimatedWeight(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select ASSESSED_WEIGHT from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的尺寸
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2Dimension(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select dimension from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 获取正向接口中的重量
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2Weight(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select weight from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
			if(!StringUtils.isEmpty(temp)){
				DecimalFormat df = new DecimalFormat("#.000");//格式化小数后面3位
				temp = df.format(Double.valueOf(temp));
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}
	/**
	 * 获取正向接口中的重量单位
	 * 
	 * @param conn
	 * @param partcode
	 * @return
	 * @throws SQLException
	 */
	public String getSA2UnitWeigth(Connection conn, String part_code)
			throws SQLException {
		String temp = "";
		String sql = "select UNIT_WEIGTH from ti_sa2_biz_back_interface where material = ?";
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, part_code);
			rs = ps.executeQuery();
			if (rs.next()) {
				temp = rs.getString(1);
			}
		} finally {
			JdbcUtil.close(rs, ps);
		}
		return temp;
	}

	/**
	 * 插入零件增量表
	 * 
	 * @param conn
	 * @param partcodeList
	 * @throws SQLException
	 */
	public void addPartCodeIncreament(Connection conn, String partcode)
			throws SQLException {
		String sql = "insert into ti_part_increament"
				+ "  (id, part_sapcode, create_by, create_date)" + " values"
				+ "  (seq_ti_part_increament.nextval, ?, ?, sysdate)";

		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, partcode);
			ps.setLong(2, BackInterface.USERID);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * 把ddMMyyyy格式的日期 格式化微yyyyMMdd
	 * 
	 * @param dateString
	 * @return
	 * @throws ParseException
	 */
	public String strTransDate(String dateString) throws ParseException {
		Date date = null;
		String dateStr = "";
		DateFormat format = new SimpleDateFormat("ddMMyyyy");
		SimpleDateFormat time = new SimpleDateFormat("yyyyMMdd");
		if (!("".equals(dateString)) && dateString != null) {
			date = format.parse(dateString);
			dateStr = time.format(date);
		}
		return dateStr;
	}

}
