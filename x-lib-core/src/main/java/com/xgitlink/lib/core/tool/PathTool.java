package com.xgitlink.lib.core.tool;

import java.io.File;

public class PathTool {

	/**
	 * 判断文件或者文件夹是否存在
	 * @param fileName
	 * @return
	 */
	public static boolean isExists( String name ) {
		File file = new File(name);
		return file.exists();
	}

	/**
	 * 返回环境变量
	 * @return
	 */
	public static String getEnv( ) {

		String env = System.getProperty("env");
		if (StringTool.isEmpty(env)) {
			env = "dev";
		}

		return env;
	}

	/**
	 * 返回应用当前路径
	 * @return
	 */
	public static String getCurrentPath( ) {
		String appPath = "";
		try {
			appPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			return appPath;
		} catch (Exception e1) {
			appPath = "./";
		}
		return appPath;
	}
}
