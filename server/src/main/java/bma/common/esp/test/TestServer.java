package bma.common.esp.test;

import bma.common.esp.exception.EspExecption;


public class TestServer implements Test.Iface {
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
	.getLogger(TestServer.class);

	@Override
	public int add(int a, int b) throws EspExecption {
		return a + b;
	}

}
