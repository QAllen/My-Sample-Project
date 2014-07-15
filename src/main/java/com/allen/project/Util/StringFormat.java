/**
 * 
 */
package com.allen.project.Util;

import org.apache.commons.lang3.StringUtils;

/**
 * @author Allen
 *
 */
public class StringFormat {
	/**
	 * 0 代表前面补充0         
	 * 9 代表长度为9        
	 * f 代表参数为浮点数
	 * 保留2位小数四舍五入，长度为9，位数不足则前面补0  
	 * @param s
	 * @return
	 */
	public static String formatDecimals(Double d){
		return String.format("%09.2f", d);
	}
	/**
	 * 0 代表前面补充0         
     * f 代表参数为浮点数
     * 保留2位小数四舍五入，长度为9，位数不足则前面补0  
	 * @param d
	 * @param length 字符串长度
	 * @return
	 */
	public static String formatDecimals(Double d,int length){
		String format = "%0"+length+".2f";
		return String.format(format, d);
	}
	/**
	 * 0 代表前面补充0         
	 * 9 代表长度为9         
	 * f 代表参数为浮点数
	 * 保留2位小数四舍五入，长度为9，位数不足则前面补0  
	 * @param s
	 * @return
	 */
	public static String formatDecimals(String s){
		Double d = Double.parseDouble(s);
		return String.format("%09.2f", d);
	}
	/**
	 * 0 代表前面补充0         
     * f 代表参数为浮点数
     * 保留2位小数四舍五入，长度为9，位数不足则前面补0  
	 * @param d
	 * @param length 字符串长度
	 * @return
	 */
	public static String formatDecimals(String s,int length){
		Double d = Double.parseDouble(s);
		String format = "%0"+length+".2f";
		return String.format(format, d);
	}
	/**
	 *  获取指定长度的字符串，位数不足则右边填充字符串
	 * @param s 源字符串
	 * @param length 字符串总长度
	 * @param str 占位符
	 * @return
	 */
	public static String fillRightPadString(String s,int length, String str){
		if("0".equals(str)){
			return StringUtils.rightPad(StringUtils.defaultString(s),length,str);
		}
		return StringUtils.rightPad("".equals(StringUtils.defaultString(s))==true?"/":s,length,str);
	}
	
	/**
	 *  获取指定长度的字符串，位数不足则左边填充字符串
	 * @param s 源字符串
	 * @param length 字符串总长度
	 * @param str 占位符
	 * @return
	 */
	public static String fillLeftPadString(String s,int length, String str){
		return StringUtils.leftPad(StringUtils.defaultString(s),length,str);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(formatDecimals(123.12,5));
		System.out.println(fillRightPadString("1",9,"/"));
		System.out.println(fillLeftPadString("1",1,"/"));
		
	}

}
