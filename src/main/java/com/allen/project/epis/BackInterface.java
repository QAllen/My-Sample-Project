package com.allen.project.epis;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.allen.project.Util.FileUtil;
import com.allen.project.Util.JdbcUtil;
import com.allen.project.Util.StringFormat;

/**
 * 
 * @author Allen TI_EPIS_BACKINTERFACE_RECORD 表字段：
 *         id,ver_no,year_version,create_by
 *         ,create_date,update_by,update_date,record_type
 *         ,part_number,production_plant
 *         ,kind_of_material,available_stock,total_consumption_current_year
 *         ,total_consumption
 *         ,location,life_circle,disposition_code,end_of_sipply_time
 *         ,surface_treat
 *         ,Premiumkennzeichen,Fertigungsort_S,Statussymbol_T,Gesamt_Lagernull
 *         ,Kennzeichen
 *         ,material_group,Entfall_W,Lager_Bestand,Price,price_euro,price_date
 *         ,discount_group,price_v_parts,price_v_parts_euro,price_v_parts_date,
 *         material_group_4
 *         ,price_y_parts,price_y_parts_euro,price_y_parts_date,dicount_group
 *         ,estimated_price
 *         ,weight,weight_unit,dimension,estimated_weight,estimated_dimension
 *         ,material_planer,subject_area_pricing,purchaser_group,stock_zero,
 *         Freigabe_Organisation
 *         ,Votex_Import,Dangerous_Goods_Indicator,Environmentally_relevant
 *         ,Hazardous_material_numbe
 *         ,Minimum_remaining_shelf_life,shelf_life_expiration_date
 *         ,calculation_of_SLED
 *         ,Storage_percentage,Total_shelf_life,Danger_good_Key
 *         ,Maximum_storage_period
 *         ,maximum_storage_perio,Postponed_spare_starting_date
 *         ,Lock_of_Availability_check
 *         ,number_X_Part,item_type,Component_type,Limited_storing_time
 *         ,Dangerous_goods
 *         ,Class_of_fire_danger,Ball_bearing_flag,Packaging_Number
 *         ,Manufacturer_Part_No
 *         ,Product_hierarchie,Dispocode,ID_for_Product,alternative_Partno
 */
public class BackInterface {
	public final static String FORWARD_SLASH = "/";// 正斜杠占位符

	public final static String SPACE = " ";// 空格占位符

	public final static String X = "x";// x占位符

	public final static Long USERID = -1L;// 系统用户

	public final static String RECORD_TYPE = "1"; // 接口代码

	public final static String PRODUCTION_PLANT = "7810";// 工厂编号

	public final static String FILE_PATH_SRC = "F:/yonyou/EPS/epms/epis_interface/back_interface/source/";// 生成文件路径

	public final static String FILE_PATH_BAK = "F:/yonyou/EPS/epms/epis_interface/back_interface/bak/";// 文件备份路径，每一个版本一个文件，界面上提供下载功能

	public final static String FILE_NAME = "DET.K7UHBE.D1B317SV";// 文件名称，德国要求名称固定

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	private static String ver_no = ""; // 版本

	protected int performExecute() {

		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<String> list = null;
		try {
			conn = JdbcUtil.getConnection();
			String sql = "select id," + "       create_by," + "       create_date," + "       update_by," + "       update_date," + "       record_type," + "       part_number,"
					+ "       production_plant," + "       kind_of_material," + "       available_stock," + "       total_consumption_current_year," + "       total_consumption," + "       location,"
					+ "       life_circle," + "       disposition_code," + "       end_of_supply_time," + "       surface_treat," + "       premium_code," + "       fertigungsort_s,"
					+ "       statussymbol_t," + "       gesamt_lagernull," + "       kennzeichen," + "       material_group," + "       entfall_w," + "       lager_bestand," + "       price,"
					+ "       price_euro," + "       price_date," + "       discount_group," + "       price_v_parts," + "       price_v_parts_euro," + "       price_v_parts_date,"
					+ "       material_group_4," + "       price_y_parts," + "       price_y_parts_euro," + "       price_y_parts_date," + "       dicount_group," + "       estimated_price,"
					+ "       weight," + "       weight_unit," + "       dimension," + "       estimated_weight," + "       estimated_dimension," + "       material_planer,"
					+ "       subject_area_pricing," + "       purchaser_group," + "       stock_zero," + "       compound_supplier," + "       dangerous_goods_indicator,"
					+ "       environmentally_relevant," + "       hazardous_material_numbe," + "       minimum_remaining_shelf_life," + "       shelf_life_expiration_date,"
					+ "       calculation_of_sled," + "       storage_percentage," + "       total_shelf_life," + "       danger_good_key," + "       maximum_storage_period,"
					+ "       unit_maximum_storage_period," + "       postponed_spare_starting_date," + "       lock_of_availability_check," + "       number_x_part," + "       item_type,"
					+ "       component_type," + "       limited_storing_time," + "       dangerous_goods," + "       class_of_fire_danger," + "       ball_bearing_flag," + "       packaging_number,"
					+ "       manufacturer_part_no," + "       product_hierarchie," + "       dispocode," + "       id_for_product," + "       alternative_partno"
					+ "  from ti_epis_backinterface_current tebc " + " where exists (select 1 from ti_part_increament tpi where tebc.part_number=tpi.part_sapcode)";

			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			StringBuilder sb = new StringBuilder();
			list = new ArrayList<String>();
			while (rs.next()) {
				// 对应接口文件字段NO.1
				sb.append(StringFormat.fillRightPadString(rs.getString("RECORD_TYPE"), 1, BackInterface.SPACE));// 接口代码，长度为1
				// 对应接口文件字段NO.2
				sb.append(StringFormat.fillRightPadString(rs.getString("PART_NUMBER"), 14, BackInterface.SPACE));// 零件SAP号，长度为14
				// 对应接口文件字段NO.3
				sb.append(StringFormat.fillRightPadString(rs.getString("PRODUCTION_PLANT"), 4, BackInterface.SPACE));// 工厂代码,长度为4
				// 对应接口文件字段NO.4
				sb.append(StringFormat.fillRightPadString(rs.getString("KIND_OF_MATERIAL"), 2, BackInterface.SPACE));// 零件类别,长度2
				// 对应接口文件字段NO.5
				sb.append(StringFormat.fillLeftPadString(rs.getString("AVAILABLE_STOCK"), 14, "0"));// 库存,长度14
				// 对应接口文件字段NO.6
				sb.append(StringFormat.fillLeftPadString(rs.getString("TOTAL_CONSUMPTION_CURRENT_YEAR"), 14, "0"));// 当年销量,长度14
				// 对应接口文件字段NO.7
				sb.append(StringFormat.fillLeftPadString(rs.getString("TOTAL_CONSUMPTION"), 14, "0"));// 前一年销量,长度14
				// 对应接口文件字段NO.8
				sb.append(StringFormat.fillRightPadString(rs.getString("LOCATION"), 4, BackInterface.SPACE));
				// 对应接口文件字段NO.9
				sb.append(StringFormat.fillRightPadString(rs.getString("LIFE_CIRCLE"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.10
				sb.append(StringFormat.fillRightPadString(rs.getString("DISPOSITION_CODE"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.11
				sb.append(StringFormat.fillRightPadString(rs.getString("END_OF_SUPPLY_TIME"), 4, BackInterface.SPACE));// 停供日期,长度4
				// 对应接口文件字段NO.12
				sb.append(StringFormat.fillRightPadString(rs.getString("SURFACE_TREAT"), 4, BackInterface.SPACE));// 长度4
				// 对应接口文件字段NO.13
				sb.append(StringFormat.fillRightPadString(rs.getString("PREMIUM_CODE"), 3, BackInterface.SPACE));// 长度3
				// 对应接口文件字段NO.14
				sb.append(StringFormat.fillRightPadString(rs.getString("FERTIGUNGSORT_S"), 4, BackInterface.SPACE));// company
																													// code
																													// from
																													// EPIS，长度4
				// 对应接口文件字段NO.15
				sb.append(StringFormat.fillRightPadString(rs.getString("STATUSSYMBOL_T"), 2, BackInterface.SPACE));
				// 对应接口文件字段NO.16
				sb.append(StringFormat.fillRightPadString(rs.getString("GESAMT_LAGERNULL"), 8, "0"));
				// 对应接口文件字段NO.17
				sb.append(StringFormat.fillRightPadString(rs.getString("KENNZEICHEN"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.18
				sb.append(StringFormat.fillRightPadString(rs.getString("MATERIAL_GROUP"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.19
				sb.append(StringFormat.fillRightPadString(rs.getString("ENTFALL_W"), 10, "0"));
				// 对应接口文件字段NO.20
				sb.append(StringFormat.fillRightPadString(rs.getString("LAGER_BESTAND"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.21,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE")) == true ? "0.000" : rs.getString("PRICE"), 12, BackInterface.SPACE));
				// 对应接口文件字段NO.22,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_EURO")) == true ? "0.000" : rs.getString("PRICE_EURO"), 12, BackInterface.SPACE));// 单价
				// 对应接口文件字段NO.23
				sb.append(StringFormat.fillRightPadString(rs.getString("PRICE_DATE"), 8, "0"));// 定价日期
				// 对应接口文件字段NO.24
				sb.append(StringFormat.fillRightPadString(rs.getString("DISCOUNT_GROUP"), 2, BackInterface.SPACE));
				// 对应接口文件字段NO.25,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_V_PARTS")) == true ? "0.000" : rs.getString("PRICE_V_PARTS"), 12, BackInterface.SPACE));
				// 对应接口文件字段NO.26,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_V_PARTS_EURO")) == true ? "0.000" : rs.getString("PRICE_V_PARTS_EURO"), 12, BackInterface.SPACE));
				// 对应接口文件字段NO.27
				sb.append(StringFormat.fillRightPadString(rs.getString("PRICE_V_PARTS_DATE"), 8, "0"));
				// 对应接口文件字段NO.28
				sb.append(StringFormat.fillRightPadString(rs.getString("MATERIAL_GROUP_4"), 2, BackInterface.SPACE));
				// 对应接口文件字段NO.29,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_Y_PARTS")) == true ? "0.000" : rs.getString("PRICE_Y_PARTS"), 12, BackInterface.SPACE));
				// 对应接口文件字段NO.30,单价字段默认0.000
				sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_Y_PARTS_EURO")) == true ? "0.000" : rs.getString("PRICE_Y_PARTS_EURO"), 12, BackInterface.SPACE));
				// 对应接口文件字段NO.31
				sb.append(StringFormat.fillRightPadString(rs.getString("PRICE_Y_PARTS_DATE"), 8, "0"));
				// 对应接口文件字段NO.32
				sb.append(StringFormat.fillRightPadString(rs.getString("DICOUNT_GROUP"), 2, BackInterface.SPACE));
				// 对应接口文件字段NO.33
				sb.append(StringFormat.fillRightPadString(rs.getString("ESTIMATED_PRICE"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.34
				sb.append(StringFormat.fillLeftPadString(rs.getString("WEIGHT"), 14, BackInterface.SPACE));// 重量
				// 对应接口文件字段NO.35
				sb.append(StringFormat.fillRightPadString(rs.getString("WEIGHT_UNIT"), 3, BackInterface.SPACE));// 重量单位
				// 对应接口文件字段NO.36
				sb.append(StringFormat.fillRightPadString(rs.getString("DIMENSION"), 32, BackInterface.SPACE));// 长宽高
				// 对应接口文件字段NO.37
				sb.append(StringFormat.fillRightPadString(rs.getString("ESTIMATED_WEIGHT"), 1, BackInterface.SPACE));// 是否预估重量
				// 对应接口文件字段NO.38
				sb.append(StringFormat.fillRightPadString(rs.getString("ESTIMATED_DIMENSION"), 1, BackInterface.SPACE));// 是否预估长度
				// 对应接口文件字段NO.39
				sb.append(StringFormat.fillRightPadString(rs.getString("MATERIAL_PLANER"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.40
				sb.append(StringFormat.fillRightPadString(rs.getString("SUBJECT_AREA_PRICING"), 4, BackInterface.SPACE));
				// 对应接口文件字段NO.41
				sb.append(StringFormat.fillRightPadString(rs.getString("PURCHASER_GROUP"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.42
				sb.append(StringFormat.fillRightPadString(rs.getString("STOCK_ZERO"), 8, "0"));// 零件出零日期
				// 对应接口文件字段NO.43
				sb.append(StringFormat.fillRightPadString(rs.getString("COMPOUND_SUPPLIER"), 10, BackInterface.SPACE));
				// 对应接口文件字段NO.44
				sb.append(StringFormat.fillRightPadString(rs.getString("DANGEROUS_GOODS_INDICATOR"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.45
				sb.append(StringFormat.fillRightPadString(rs.getString("ENVIRONMENTALLY_RELEVANT"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.46
				sb.append(StringFormat.fillRightPadString(rs.getString("HAZARDOUS_MATERIAL_NUMBE"), 18, BackInterface.SPACE));
				// 对应接口文件字段NO.47
				sb.append(StringFormat.fillRightPadString(rs.getString("MINIMUM_REMAINING_SHELF_LIFE"), 4, "0"));
				// 对应接口文件字段NO.48
				sb.append(StringFormat.fillRightPadString(rs.getString("SHELF_LIFE_EXPIRATION_DATE"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.49
				sb.append(StringFormat.fillRightPadString(rs.getString("CALCULATION_OF_SLED"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.50
				sb.append(StringFormat.fillRightPadString(rs.getString("STORAGE_PERCENTAGE"), 3, "0"));
				// 对应接口文件字段NO.51
				sb.append(StringFormat.fillRightPadString(rs.getString("TOTAL_SHELF_LIFE"), 4, "0"));
				// 对应接口文件字段NO.52
				sb.append(StringFormat.fillRightPadString(rs.getString("DANGER_GOOD_KEY"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.53
				sb.append(StringFormat.fillRightPadString(rs.getString("MAXIMUM_STORAGE_PERIOD"), 5, "0"));
				// 对应接口文件字段NO.54
				sb.append(StringFormat.fillRightPadString(rs.getString("UNIT_MAXIMUM_STORAGE_PERIOD"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.55
				sb.append(StringFormat.fillRightPadString(rs.getString("POSTPONED_SPARE_STARTING_DATE"), 8, "0"));
				// 对应接口文件字段NO.56
				sb.append(StringFormat.fillRightPadString(rs.getString("LOCK_OF_AVAILABILITY_CHECK"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.57
				sb.append(StringFormat.fillRightPadString(rs.getString("NUMBER_X_PART"), 18, BackInterface.SPACE));
				// 对应接口文件字段NO.58
				sb.append(StringFormat.fillRightPadString(rs.getString("ITEM_TYPE"), 4, BackInterface.SPACE));
				// 对应接口文件字段NO.59
				sb.append(StringFormat.fillRightPadString(rs.getString("COMPONENT_TYPE"), 3, BackInterface.SPACE));
				// 对应接口文件字段NO.60
				sb.append(StringFormat.fillRightPadString(rs.getString("LIMITED_STORING_TIME"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.61
				sb.append(StringFormat.fillRightPadString(rs.getString("DANGEROUS_GOODS"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.62
				sb.append(StringFormat.fillRightPadString(rs.getString("CLASS_OF_FIRE_DANGER"), 2, BackInterface.SPACE));
				// 对应接口文件字段NO.63
				sb.append(StringFormat.fillRightPadString(rs.getString("BALL_BEARING_FLAG"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.64
				sb.append(StringFormat.fillRightPadString(rs.getString("PACKAGING_NUMBER"), 10, BackInterface.SPACE));
				// 对应接口文件字段NO.65
				sb.append(StringFormat.fillRightPadString(rs.getString("MANUFACTURER_PART_NO"), 40, BackInterface.SPACE));
				// 对应接口文件字段NO.66
				sb.append(StringFormat.fillRightPadString(rs.getString("PRODUCT_HIERARCHIE"), 18, BackInterface.SPACE));// 格式化的HTG
				// 对应接口文件字段NO.67
				sb.append(StringFormat.fillRightPadString(rs.getString("DISPOCODE"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.68
				sb.append(StringFormat.fillRightPadString(rs.getString("ID_FOR_PRODUCT"), 1, BackInterface.SPACE));
				// 对应接口文件字段NO.69
				sb.append(StringFormat.fillRightPadString(rs.getString("ALTERNATIVE_PARTNO"), 18, BackInterface.SPACE));
				list.add(sb.toString());
				sb.delete(0, sb.length());
			}
			// String ver_no = sdf.format(new Date());
			// TODO:此处上测试生产需要更改为上边注释的
			String ver_no = "2014-04-27";
			// 插入接口数据历史表，更新哪些生成零件号的日期，并删除增量表数据
			addBackInterfaceRecordHistory(ver_no);

			String filePath_src = BackInterface.FILE_PATH_SRC + BackInterface.FILE_NAME;

			// 生成文件
			FileUtil.generateFile(list, filePath_src, "UTF-8");// 生成接口文件
			// 生成备份文件
			String targetDir = BackInterface.FILE_PATH_BAK + ver_no;
			File dir = new File(targetDir);// 创建目录
			if (!dir.exists()) {// 如果目录不存在则创建此目录
				dir.mkdir();
			}
			String targetFile = targetDir + "/" + BackInterface.FILE_NAME;
			FileUtil.copyFile(filePath_src, targetFile);
			// 插入文件历史列表
			addFileHistory(ver_no, BackInterface.FILE_NAME);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs, ps, conn);
		}
		return 0;
	}

	/**
	 * 生成接口文件后把增量表中生成的数据删除
	 * 
	 * @throws SQLException
	 */
	public void modifyIncreamPartCode(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "delete from ti_part_increament";
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * 把生成的增量表中数据更新到TI_PART_BASE_BI_RECORD
	 * 
	 * @throws SQLException
	 */
	public void modifyTtPartBasePartCode(Connection conn) throws SQLException {
		PreparedStatement ps = null;
		String sql = "update TI_PART_BASE_BI_RECORD a set status = '1',update_by = -1,update_date = sysdate "
				+ " where exists (select 1 from ti_part_increament tpi where a.part_sapcode=tpi.part_sapcode)";
		try {
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
		} finally {
			ps.close();
		}
	}

	/**
	 * 插入接口数据历史表,并删除增量表中数据
	 * 
	 * @param ver_no
	 *            版本号
	 */
	public void addBackInterfaceRecordHistory(String ver_no) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into ti_epis_backinterface_record"
				+ " select SEQ_TI_EPIS_BACKINTERFACE_HIS.Nextval as id,'"
				+ ver_no
				+ "' as ver_no, create_by, create_date, update_by, update_date, record_type, part_number, production_plant, kind_of_material, available_stock, total_consumption_current_year, total_consumption, location, life_circle, disposition_code, end_of_supply_time, surface_treat, premium_code, fertigungsort_s, statussymbol_t, gesamt_lagernull, kennzeichen, material_group, entfall_w, lager_bestand, price, price_euro, price_date, discount_group, price_v_parts, price_v_parts_euro, price_v_parts_date, material_group_4, price_y_parts, price_y_parts_euro, price_y_parts_date, dicount_group, estimated_price, weight, weight_unit, dimension, estimated_weight, estimated_dimension, material_planer, subject_area_pricing, purchaser_group, stock_zero, compound_supplier, dangerous_goods_indicator, environmentally_relevant, hazardous_material_numbe, minimum_remaining_shelf_life, shelf_life_expiration_date, calculation_of_sled, storage_percentage, total_shelf_life, danger_good_key, maximum_storage_period, unit_maximum_storage_period, postponed_spare_starting_date, lock_of_availability_check, number_x_part, item_type, component_type, limited_storing_time, dangerous_goods, class_of_fire_danger, ball_bearing_flag, packaging_number, manufacturer_part_no, product_hierarchie, dispocode, id_for_product, alternative_partno "
				+ " from ti_epis_backinterface_current tebc" + " where exists (select 1 from ti_part_increament tpi where tebc.part_number=tpi.part_sapcode)";

		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.execute();
			modifyTtPartBasePartCode(conn);// 同步更新零件号生成状态
			// TODO:记得开启
			// modifyIncreamPartCode(conn);//删除增量表数据

			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps, conn);
		}
	}

	/**
	 * 生成文件记录
	 * 
	 * @param ver_no
	 *            版本号
	 */
	public void addFileHistory(String ver_no, String fileName) {
		Connection conn = null;
		PreparedStatement ps = null;
		String sql = "insert into ti_back_interface_file" + "  (id, file_name, ver_no, create_by, create_date)" + " values" + "  (SEQ_TI_BACK_INTERFACE_FILE.Nextval, ?, ?, ?, sysdate)";
		try {
			conn = JdbcUtil.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement(sql);
			ps.setString(1, fileName);
			ps.setString(2, ver_no);
			ps.setLong(3, BackInterface.USERID);
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				throw new RuntimeException(e1);
			}
			throw new RuntimeException(e);
		} finally {
			JdbcUtil.close(ps, conn);
		}
	}

	public static void main(String[] args) {
		BackInterface bi = new BackInterface();
		bi.performExecute();
	}
}
