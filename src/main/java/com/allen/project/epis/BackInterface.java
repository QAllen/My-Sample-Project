package com.allen.project.epis;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.allen.project.Util.FileUtil;
import com.allen.project.Util.JdbcUtil;
import com.allen.project.Util.StringFormat;
/**
 * 
 * @author Allen
 * TI_EPIS_BACKINTERFACE_RECORD 表字段：
 * id,ver_no,year_version,create_by,create_date,update_by,update_date,record_type,part_number,production_plant,kind_of_material,available_stock,total_consumption_current_year,total_consumption,location,life_circle,disposition_code,end_of_sipply_time,surface_treat,Premiumkennzeichen,Fertigungsort_S,Statussymbol_T,Gesamt_Lagernull,Kennzeichen,material_group,Entfall_W,Lager_Bestand,Price,price_euro,price_date,discount_group,price_v_parts,price_v_parts_euro,price_v_parts_date,material_group_4,price_y_parts,price_y_parts_euro,price_y_parts_date,dicount_group,estimated_price,weight,weight_unit,dimension,estimated_weight,estimated_dimension,material_planer,subject_area_pricing,purchaser_group,stock_zero,Freigabe_Organisation,Votex_Import,Dangerous_Goods_Indicator,Environmentally_relevant,Hazardous_material_numbe,Minimum_remaining_shelf_life,shelf_life_expiration_date,calculation_of_SLED,Storage_percentage,Total_shelf_life,Danger_good_Key,Maximum_storage_period,maximum_storage_perio,Postponed_spare_starting_date,Lock_of_Availability_check,number_X_Part,item_type,Component_type,Limited_storing_time,Dangerous_goods,Class_of_fire_danger,Ball_bearing_flag,Packaging_Number,Manufacturer_Part_No,Product_hierarchie,Dispocode,ID_for_Product,alternative_Partno
 */
public class BackInterface {
		private final static String PLACEHOLDER = "/";
		
		public static void main(String[] args) {
			Connection conn = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			List<String> list = null;
			try {
				conn = JdbcUtil.getConnection();
				String sql =    "select id," + 
								"       ver_no," + 
								"       create_by," + 
								"       create_date," + 
								"       update_by," + 
								"       update_date," + 
								"       record_type," + 
								"       part_number," + 
								"       production_plant," + 
								"       kind_of_material," + 
								"       available_stock," + 
								"       total_consumption_current_year," + 
								"       total_consumption," + 
								"       location," + 
								"       life_circle," + 
								"       disposition_code," + 
								"       end_of_sipply_time," + 
								"       surface_treat," + 
								"       premium_code," + 
								"       fertigungsort_s," + 
								"       statussymbol_t," + 
								"       gesamt_lagernull," + 
								"       kennzeichen," + 
								"       material_group," + 
								"       entfall_w," + 
								"       lager_bestand," + 
								"       price," + 
								"       price_euro," + 
								"       price_date," + 
								"       discount_group," + 
								"       price_v_parts," + 
								"       price_v_parts_euro," + 
								"       price_v_parts_date," + 
								"       material_group_4," + 
								"       price_y_parts," + 
								"       price_y_parts_euro," + 
								"       price_y_parts_date," + 
								"       dicount_group," + 
								"       estimated_price," + 
								"       weight," + 
								"       weight_unit," + 
								"       dimension," + 
								"       estimated_weight," + 
								"       estimated_dimension," + 
								"       material_planer," + 
								"       subject_area_pricing," + 
								"       purchaser_group," + 
								"       stock_zero," + 
								"       compound_supplier," + 
								"       dangerous_goods_indicator," + 
								"       environmentally_relevant," + 
								"       hazardous_material_numbe," + 
								"       minimum_remaining_shelf_life," + 
								"       shelf_life_expiration_date," + 
								"       calculation_of_sled," + 
								"       storage_percentage," + 
								"       total_shelf_life," + 
								"       danger_good_key," + 
								"       maximum_storage_period," + 
								"       unit_maximum_storage_period," + 
								"       postponed_spare_starting_date," + 
								"       lock_of_availability_check," + 
								"       number_x_part," + 
								"       item_type," + 
								"       component_type," + 
								"       limited_storing_time," + 
								"       dangerous_goods," + 
								"       class_of_fire_danger," + 
								"       ball_bearing_flag," + 
								"       packaging_number," + 
								"       manufacturer_part_no," + 
								"       product_hierarchie," + 
								"       dispocode," + 
								"       id_for_product," + 
								"       alternative_partno" + 
								"  from ti_epis_backinterface_record";

				ps = conn.prepareStatement(sql);
				rs = ps.executeQuery(); 
				StringBuilder sb = new StringBuilder();
				list = new ArrayList<String>();
				while(rs.next()){
					//对应接口文件字段NO.1
					sb.append(StringFormat.fillLeftPadString(rs.getString("RECORD_TYPE"), 1, BackInterface.PLACEHOLDER));//接口代码，长度为1
					//对应接口文件字段NO.2
					sb.append(StringFormat.fillLeftPadString(rs.getString("PART_NUMBER"), 14, BackInterface.PLACEHOLDER));//零件SAP号，长度为14
					//对应接口文件字段NO.3
					sb.append(StringFormat.fillLeftPadString(rs.getString("PRODUCTION_PLANT"), 4, BackInterface.PLACEHOLDER));//工厂代码,长度为4
					//对应接口文件字段NO.4
					sb.append(StringFormat.fillLeftPadString(rs.getString("KIND_OF_MATERIAL"), 2, BackInterface.PLACEHOLDER));//零件类别,长度2
					//对应接口文件字段NO.5
					sb.append(StringFormat.fillLeftPadString(rs.getString("AVAILABLE_STOCK"), 14, "0"));//库存,长度14
					//对应接口文件字段NO.6
					sb.append(StringFormat.fillLeftPadString(rs.getString("TOTAL_CONSUMPTION_CURRENT_YEAR"), 14, "0"));//当年销量,长度14
					//对应接口文件字段NO.7
					sb.append(StringFormat.fillLeftPadString(rs.getString("TOTAL_CONSUMPTION"), 14, "0"));//前一年销量,长度14
					//对应接口文件字段NO.8
					sb.append(StringFormat.fillLeftPadString(rs.getString("LOCATION"), 4, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.9
					sb.append(StringFormat.fillLeftPadString(rs.getString("LIFE_CIRCLE"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.10
					sb.append(StringFormat.fillLeftPadString(rs.getString("DISPOSITION_CODE"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.11
					sb.append(StringFormat.fillLeftPadString(rs.getString("END_OF_SIPPLY_TIME"), 4, BackInterface.PLACEHOLDER));//停供日期,长度4
					//对应接口文件字段NO.12
					sb.append(StringFormat.fillLeftPadString(rs.getString("SURFACE_TREAT"), 4, BackInterface.PLACEHOLDER));//长度4
					//对应接口文件字段NO.13
					sb.append(StringFormat.fillLeftPadString(rs.getString("PREMIUM_CODE"), 3, BackInterface.PLACEHOLDER));//长度3
					//对应接口文件字段NO.14
					sb.append(StringFormat.fillLeftPadString(rs.getString("FERTIGUNGSORT_S"), 4, BackInterface.PLACEHOLDER));//company code from EPIS，长度4
					//对应接口文件字段NO.15
					sb.append(StringFormat.fillLeftPadString(rs.getString("STATUSSYMBOL_T"), 2, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.16
					sb.append(StringFormat.fillLeftPadString(rs.getString("GESAMT_LAGERNULL"), 8, "0"));
					//对应接口文件字段NO.17
					sb.append(StringFormat.fillLeftPadString(rs.getString("KENNZEICHEN"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.18
					sb.append(StringFormat.fillLeftPadString(rs.getString("MATERIAL_GROUP"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.19
					sb.append(StringFormat.fillLeftPadString(rs.getString("ENTFALL_W"), 10, "0"));
					//对应接口文件字段NO.20
					sb.append(StringFormat.fillLeftPadString(rs.getString("LAGER_BESTAND"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.21,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE"))==true?"0.000":rs.getString("PRICE"), 12, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.22,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_EURO"))==true?"0.000":rs.getString("PRICE_EURO"), 12, BackInterface.PLACEHOLDER));//单价
					//对应接口文件字段NO.23
					sb.append(StringFormat.fillLeftPadString(rs.getString("PRICE_DATE"), 8, "0"));//定价日期
					//对应接口文件字段NO.24
					sb.append(StringFormat.fillLeftPadString(rs.getString("DISCOUNT_GROUP"), 2, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.25,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_V_PARTS"))==true?"0.000":rs.getString("PRICE_V_PARTS"), 12, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.26,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_V_PARTS_EURO"))==true?"0.000":rs.getString("PRICE_V_PARTS_EURO"), 12, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.27
					sb.append(StringFormat.fillLeftPadString(rs.getString("PRICE_V_PARTS_DATE"), 8, "0"));
					//对应接口文件字段NO.28
					sb.append(StringFormat.fillLeftPadString(rs.getString("MATERIAL_GROUP_4"), 2, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.29,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_Y_PARTS"))==true?"0.000":rs.getString("PRICE_Y_PARTS"), 12, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.30,单价字段默认0.000
					sb.append(StringFormat.fillLeftPadString(StringUtils.isEmpty(rs.getString("PRICE_Y_PARTS_EURO"))==true?"0.000":rs.getString("PRICE_Y_PARTS_EURO"), 12, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.31
					sb.append(StringFormat.fillLeftPadString(rs.getString("PRICE_Y_PARTS_DATE"), 8, "0"));
					//对应接口文件字段NO.32
					sb.append(StringFormat.fillLeftPadString(rs.getString("DICOUNT_GROUP"), 2, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.33
					sb.append(StringFormat.fillLeftPadString(rs.getString("ESTIMATED_PRICE"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.34
					sb.append(StringFormat.fillRightPadString(rs.getString("WEIGHT"), 14, BackInterface.PLACEHOLDER));//重量
					//对应接口文件字段NO.35
					sb.append(StringFormat.fillLeftPadString(rs.getString("WEIGHT_UNIT"), 3, BackInterface.PLACEHOLDER));//重量单位
					//对应接口文件字段NO.36
					sb.append(StringFormat.fillLeftPadString(rs.getString("DIMENSION"), 32, BackInterface.PLACEHOLDER));//长宽高
					//对应接口文件字段NO.37
					sb.append(StringFormat.fillLeftPadString(rs.getString("ESTIMATED_WEIGHT"), 1, BackInterface.PLACEHOLDER));//是否预估重量
					//对应接口文件字段NO.38
					sb.append(StringFormat.fillLeftPadString(rs.getString("ESTIMATED_DIMENSION"), 1, BackInterface.PLACEHOLDER));//是否预估长度
					//对应接口文件字段NO.39
					sb.append(StringFormat.fillLeftPadString(rs.getString("MATERIAL_PLANER"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.40
					sb.append(StringFormat.fillLeftPadString(rs.getString("SUBJECT_AREA_PRICING"), 4, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.41
					sb.append(StringFormat.fillLeftPadString(rs.getString("PURCHASER_GROUP"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.42
					sb.append(StringFormat.fillLeftPadString(rs.getString("STOCK_ZERO"), 8, "0"));//零件出零日期
					//对应接口文件字段NO.43
					sb.append(StringFormat.fillLeftPadString(rs.getString("COMPOUND_SUPPLIER"), 10, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.44
					sb.append(StringFormat.fillLeftPadString(rs.getString("DANGEROUS_GOODS_INDICATOR"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.45
					sb.append(StringFormat.fillLeftPadString(rs.getString("ENVIRONMENTALLY_RELEVANT"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.46
					sb.append(StringFormat.fillLeftPadString(rs.getString("HAZARDOUS_MATERIAL_NUMBE"), 18, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.47
					sb.append(StringFormat.fillLeftPadString(rs.getString("MINIMUM_REMAINING_SHELF_LIFE"), 4, "0"));
					//对应接口文件字段NO.48
					sb.append(StringFormat.fillLeftPadString(rs.getString("SHELF_LIFE_EXPIRATION_DATE"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.49
					sb.append(StringFormat.fillLeftPadString(rs.getString("CALCULATION_OF_SLED"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.50
					sb.append(StringFormat.fillLeftPadString(rs.getString("STORAGE_PERCENTAGE"), 3, "0"));
					//对应接口文件字段NO.51
					sb.append(StringFormat.fillLeftPadString(rs.getString("TOTAL_SHELF_LIFE"), 4, "0"));
					//对应接口文件字段NO.52
					sb.append(StringFormat.fillLeftPadString(rs.getString("DANGER_GOOD_KEY"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.53
					sb.append(StringFormat.fillLeftPadString(rs.getString("MAXIMUM_STORAGE_PERIOD"), 5, "0"));
					//对应接口文件字段NO.54
					sb.append(StringFormat.fillLeftPadString(rs.getString("UNIT_MAXIMUM_STORAGE_PERIOD"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.55
					sb.append(StringFormat.fillLeftPadString(rs.getString("POSTPONED_SPARE_STARTING_DATE"), 8, "0"));
					//对应接口文件字段NO.56
					sb.append(StringFormat.fillLeftPadString(rs.getString("LOCK_OF_AVAILABILITY_CHECK"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.57
					sb.append(StringFormat.fillLeftPadString(rs.getString("NUMBER_X_PART"), 18, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.58
					sb.append(StringFormat.fillLeftPadString(rs.getString("ITEM_TYPE"), 4, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.59
					sb.append(StringFormat.fillLeftPadString(rs.getString("COMPONENT_TYPE"), 3, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.60
					sb.append(StringFormat.fillLeftPadString(rs.getString("LIMITED_STORING_TIME"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.61
					sb.append(StringFormat.fillLeftPadString(rs.getString("DANGEROUS_GOODS"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.62
					sb.append(StringFormat.fillLeftPadString(rs.getString("CLASS_OF_FIRE_DANGER"), 2, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.63
					sb.append(StringFormat.fillLeftPadString(rs.getString("BALL_BEARING_FLAG"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.64
					sb.append(StringFormat.fillLeftPadString(rs.getString("PACKAGING_NUMBER"), 10, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.65
					sb.append(StringFormat.fillLeftPadString(rs.getString("MANUFACTURER_PART_NO"), 40, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.66
					sb.append(StringFormat.fillLeftPadString(rs.getString("PRODUCT_HIERARCHIE"), 18, BackInterface.PLACEHOLDER));//格式化的HTG
					//对应接口文件字段NO.67
					sb.append(StringFormat.fillLeftPadString(rs.getString("DISPOCODE"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.68
					sb.append(StringFormat.fillLeftPadString(rs.getString("ID_FOR_PRODUCT"), 1, BackInterface.PLACEHOLDER));
					//对应接口文件字段NO.69
					sb.append(StringFormat.fillLeftPadString(rs.getString("ALTERNATIVE_PARTNO"), 18, BackInterface.PLACEHOLDER));
					list.add(sb.toString());
					sb.delete(0, sb.length());
				}
				FileUtil.generateFile(list, "E://DET.K7UHBE.D1B317SV", "UTF-8");
			} catch (SQLException e) {
				e.printStackTrace();
			} finally{
				JdbcUtil.close(rs, ps, conn);
			}
		}
}
