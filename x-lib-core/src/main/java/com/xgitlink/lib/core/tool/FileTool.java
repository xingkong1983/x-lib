package com.xgitlink.lib.core.tool;

import java.io.File;
import java.io.IOException;
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
	public static String readText(String fileName) throws Exception {
		File file = new File(fileName);
		String content = FileUtils.readFileToString(file, Charset.forName("utf-8"));
		return content;
	}

	/**
	 * 写一个字符串到文件
	 * @param fileName
	 * @param text
	 * @throws IOException
	 */
	public static void writeText(String fileName, String text) throws IOException {
		File file = new File(fileName);
		FileUtils.writeStringToFile(file, text, Charset.forName("utf-8"));
	}
}
