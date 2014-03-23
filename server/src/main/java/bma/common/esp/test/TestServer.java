package bma.common.esp.test;

import java.io.IOException;
import java.util.Map;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class TestServer implements Test.Iface {
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
	.getLogger(TestServer.class);

	@Override
	public void add(ESNPServerTransport eTransport,
			ERequest eRequest, EResponse eResponse) throws IOException {

		Map<String, Object> dataMap = eRequest.getData();
		
		int c;
		int a = (Integer)dataMap.get("a");
		int b = (Integer)dataMap.get("b");
		c =  a + b;
		
		//设置响应数据结果
		eResponse.setData("c",c);	
		
		//发送
		eTransport.write(eResponse);
		eTransport.flush();
		
		log.info("AAAAAAA");
	}

	@Override
	public int add(int a, int b) throws EspExecption {
		int c;
		return a + b;
	}

}
