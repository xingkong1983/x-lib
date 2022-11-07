package com.xgitlink.lib.core.tool;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.concurrent.LinkedBlockingQueue;

public class LogTool extends Thread {

	private final static int MAX_QUEUE_LEN = 200;

	private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>(MAX_QUEUE_LEN);

	private File file;
	private Writer writer;

	public static void push(String message) {

		if (!linkedBlockingQueue.offer(message)) {
			System.out.print("log lost.");
		}
	}

	public String poll() {
		String message = linkedBlockingQueue.poll();
		return message;
	}

	@Override
	public void run() {
		String logFileName = "C:/logs/" + DateTool.getNospaceDateStr() + ".log";
		System.out.println("日志文件是:" + logFileName);
		this.file = new File(logFileName);
		try {
			this.writer = new FileWriter(file,  Charset.forName("utf-8"));
			while (true) {
				StringBuffer outMessage = new StringBuffer();
				for (int i = 0; i < MAX_QUEUE_LEN; i++) {

					String message = poll();

					if (message != null) {
						outMessage.append(message);
						outMessage.append("\n");
						// System.out.print("+");
					} else {
						OsTool.sleepms(10);
					}
				}
				OsTool.sleepms(300);
//				System.out.println("");
				writer.write(outMessage.toString());
				writer.flush();
			}
		} catch (IOException e) {
			System.out.println(OsTool.getErrorText(e));
		} finally {
			OsTool.close(writer);
		}
	}

	public static void begin() {
		LogTool logTool = new LogTool();
		logTool.start();
	}
}
