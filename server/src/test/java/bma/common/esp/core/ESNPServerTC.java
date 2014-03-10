package bma.common.esp.core;

import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import bma.common.esp.server.core.ESNPServer;
import bma.common.langutil.core.ObjectUtil;
import bma.common.langutil.log.LogbackUtil;


public class ESNPServerTC {
	@Before
	public void setUp() {
		LogbackUtil.setLevel("bma.common.langutil.concurrent.ProcessTimeWheel",
				"INFO");
	}

	public ESNPServer server() {
		ESNPServer s = new ESNPServer();
		s.setPort(1081);
		s.setName("ESNPServer");
		s.setTraceBufferSize(1024);
		return s;
	}

	@Test
	public void server_file() {
		ESNPServer s = server();
		s.start();
		ObjectUtil.waitFor(this, 6000 * 1000);
		s.close();
	}
}
