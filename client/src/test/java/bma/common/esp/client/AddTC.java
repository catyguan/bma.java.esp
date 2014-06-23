package bma.common.esp.client;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import bma.common.esp.client.core.ESNPSocketClient;
import bma.common.esp.test.AddTest;
import bma.common.langutil.testcase.SpringTestcaseUtil;

public class AddTC {
	
	FileSystemXmlApplicationContext context;
	ESNPSocketClient client;
	AddTest add;

	@Before
	public void setUp() throws Exception {
		SpringTestcaseUtil.disableDebug();
		context = new SpringTestcaseUtil.ApplicationContextBuilder().project("src/test/resources/spring_server.xml")
				.build();

		client = context.getBean("client", ESNPSocketClient.class);
		add = context.getBean("add", AddTest.class);
		System.out.println(client.toString());
		System.out.println(add.toString());
	}

	@After
	public void tearDown() throws Exception {
		if (context != null)
			context.close();
	}
	
	@Test
	public void testAdd() throws IOException{
		add.send();
	}

}
