package bma.common.esp.core;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bma.common.esp.server.core.ESNPServer;
import bma.common.langutil.core.ObjectUtil;
import bma.common.langutil.log.LogbackUtil;
import bma.common.langutil.testcase.SpringTestcaseUtil;


public class ESNPServerSpringTC {
	
	FileSystemXmlApplicationContext context;

	@Before
	public void setUp() {
		context = new SpringTestcaseUtil.ApplicationContextBuilder().project("src/test/resources/spring_server.xml")
				.build();
	}

	@After
	public void tearDown() throws Exception {
		if (context != null) {
			context.close();
		}
	}

	@Test
	public void server_file() {
		System.out.println(UUID.randomUUID().toString().replaceAll("-", "o"));
		
		ESNPServer s1 = context.getBean("server", ESNPServer.class);
		System.out.println(s1.toString());
		
		s1.start();
		ObjectUtil.waitFor(this, 6000 * 1000);
		s1.close();
	}
}
