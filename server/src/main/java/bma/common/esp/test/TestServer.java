package bma.common.esp.test;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.server.processor.ESNPServerProcessor;


public class TestServer implements Test.Iface {
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
	.getLogger(TestServer.class);

	@Override
	public int add(int a, int b) throws EspExecption {
		ESNPServerScoket s ;
		s = ESNPServerProcessor.currentScoket();
		return a + b;
	}

}
