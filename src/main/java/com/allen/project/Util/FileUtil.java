/**
 * 
 */
package com.allen.project.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.ArrayUtils;

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
			String encoding) throws IOException {
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

		} finally {
			if (bw != null)
				bw.close();
		}
	}

	/**
	 * 复制文件
	 * 
	 * @param oldPath
	 *            源文件
	 * @param newPath
	 *            新路径文件
	 * @throws IOException
	 */
	public static void copyFile(String oldPath, String newPath)
			throws IOException {
		InputStream inStream = null;
		FileOutputStream fs = null;
		int bytesum = 0;
		int byteread = 0;
		File oldfile = new File(oldPath);
		try {
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

		} finally {
			if (inStream != null) {
				inStream.close();
			}
			if (fs != null) {
				fs.close();
			}
		}

	}

	/**
	 * 删除文件
	 * 
	 * @param filePathAndName
	 */
	public static void delFile(String filePathAndName) {
		String filePath = filePathAndName;
		filePath = filePath.toString();
		File myDelFile = new File(filePath);
		myDelFile.delete();

	}

	public static void main(String[] args) throws InterruptedException, IOException {

		List<String> list = new ArrayList<String>();
		String s = "123456";
		long startTime = System.currentTimeMillis(); // 获取开始时间
		 for (int i = 0; i < 900000; i++) {
		 list.add("abc中华人民共和国" + i);
		 }
		 File file = new File("E://a.txt");
//		  generateFile(list, "E://DET.K7UHBE.D1B317SV", "UTF-8");
//		  FileUtils.writeLines(file, list);
		  for(int i=0;i<list.size();i++){
			  String o = (String)list.get(i);
			  byte[] b = FileUtils.readFileToByteArray(file);
			  byte[] t = ArrayUtils.addAll(b, o.getBytes());
			  FileUtils.writeByteArrayToFile(file,t);
		  }
				  
		long endTime = System.currentTimeMillis(); // 获取结束时间
		System.out.println("程序运行时间： " + (endTime - startTime) + "ms");

	}

}
