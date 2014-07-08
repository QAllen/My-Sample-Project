/**
 * 
 */
package com.allen.project.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * apache的common-io中有FileUtils不能使用时再用此
 * 
 * @author Allen
 *
 */
public class FileUtil {
	/**
	 * 按行生成文件
	 * 
	 * @param list
	 *            要生成的数据
	 * @param filePath
	 *            文件位置
	 * @throws IOException
	 */
	public static void generateFile(List<String> list, String filePath,
			String encoding) {
			File file = new File(filePath);
			BufferedWriter bw = null;
		try {
			file.createNewFile();
			bw = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(file), encoding));
			for (String s : list) {
				bw.write(s);
				bw.newLine();// 换行
			}
			bw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				if(bw!=null)
				bw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param oldPath
	 *            源文件
	 * @param newPath
	 *            新路径文件
	 */
	public static void copyFile(String oldPath, String newPath) {
		InputStream inStream = null;
		FileOutputStream fs = null;
		try {
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				inStream = new FileInputStream(oldPath); // 读入原文件
				fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					fs.write(buffer, 0, byteread);
				}
			}

		} catch (Exception e) {
			System.out.println("复制单个文件操作出错 ");
			e.printStackTrace();

		} finally {
			try {
				inStream.close();
				fs.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) {
		try {
			String filePath = filePathAndName;
			filePath = filePath.toString();
			java.io.File myDelFile = new java.io.File(filePath);
			System.out.println(filePath);
			myDelFile.delete();

		} catch (Exception e) {
			System.out.println("删除文件操作出错 ");
			e.printStackTrace();

		}

	}

	public static void main(String[] args) throws InterruptedException {

		List<String> list = new ArrayList<String>();
		String s = "123456";
		long startTime = System.currentTimeMillis(); // 获取开始时间
//		for (int i = 0; i < 1000; i++) {
//			list.add("abc中华人民共和国" + i);
//		}
////			File file = new File("E://a.txt");
////			generateFile(list, "E://DET.K7UHBE.D1B317SV", "UTF-8");
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
		System.out.println("1/////ZZZZZZZZZ7810HT000000000012340000000000111100000001111111//////2019/////////14//00000000////0000000000///////////////////10.000//////////////////////////////////00000000//////////////////////////00000000///40000//////////KG///////////////00040X00030X00030JN//////////20130506//////////////////////////////////////0000000///00000///00000000/////////////////////////////////////////////////////////////////////////////////    G   GT     GTA////////////////////".length());
	}

}
