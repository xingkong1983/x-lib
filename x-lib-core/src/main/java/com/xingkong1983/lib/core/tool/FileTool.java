package com.xingkong1983.lib.core.tool;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import org.apache.commons.io.FileUtils;

public class FileTool {
	/**
	 * 读取一个文件到字符串
	 * 
	 * @param fileName
	 * @return
	 * @throws Exception
	 */
	public static String readText( String fileName ) throws Exception {
		File file = new File(fileName);
		String content = FileUtils.readFileToString(file, Charset.forName("utf-8"));
		return content;
	}

	/**
	 * 写一个字符串到文件
	 * 
	 * @param fileName
	 * @param text
	 * @throws IOException
	 */
	public static void writeText( String fileName, String text ) throws IOException {
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, text, Charset.forName("utf-8"));
	}

	public static byte[] readStream( InputStream inputStream ) throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = -1;
		while ((len = inputStream.read(buffer)) != -1) {
			byteArrayOutputStream.write(buffer, 0, len);
		}
		return byteArrayOutputStream.toByteArray();
	}

	/**
	 * 如果文件存在，则重新创建文件 如果文件不存在，直接创建文件
	 * 
	 * @param fileName
	 * @return
	 */
	public static boolean reCreateFile( String fileName ) {
		boolean result = false;
		File file = null;
		try {
			file = new File(fileName);
			if (!file.getParentFile().exists()) {
				file.getParentFile().mkdirs();
			}
			if (!file.exists()) {
				file.createNewFile();
			} else {
				file.delete();
				file.createNewFile();
			}
			result = true;
		} catch (IOException e) {
			OsTool.print(e);
		} finally {

		}
		return result;
	}

	public static boolean delete( String fileName ) {
		File file = new File(fileName);
		if (file.exists()) {
			return file.delete();
		} else {
			OsTool.print("不存在文件:" + fileName);
		}
		return true;
	}

	/**
	 * 判断文件大小
	 * 
	 * @param len  文件长度
	 * @param size 限制大小
	 * @param unit 限制单位(B,K,M,G)
	 * @return
	 */
	public static boolean checkFileSize( Long len, int size, String unit ) {
		// long len = file.length();
		double fileSize = 0;
		if ("B".equals(unit.toUpperCase())) {
			fileSize = (double) len;
		} else if ("K".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1024;
		} else if ("M".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1048576;
		} else if ("G".equals(unit.toUpperCase())) {
			fileSize = (double) len / 1073741824;
		}
		if (fileSize > size) {
			return false;
		}
		return true;
	}

}
