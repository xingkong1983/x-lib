package com.xgitlink.lib.core.tool;

import org.junit.jupiter.api.Test;

class OsToolTest {

	@Test
	void testSingleCmd() {

		String cmd = "netstat -ano";
		String result = OsTool.exec(cmd);
		OsTool.print(cmd);
		OsTool.print(result);

	}

	@Test
	void testCmdList() {
		String[] cmdList = new String[] { "netstat -ano" };
		String result;
		for (String cmdText : cmdList) {
			result = OsTool.exec(cmdText);
			OsTool.print(cmdText);
			OsTool.print(result);
		}
	}
}
