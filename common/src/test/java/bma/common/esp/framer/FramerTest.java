package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


import org.junit.Test;

import bma.common.esp.coder.FixUint64Coder;
import bma.common.langutil.core.StringUtil;

public class FramerTest {

	@Test
	public void testMesNo() throws IOException {
		
		ByteArrayOutputStream bosB = new ByteArrayOutputStream();
		
		long id = 88888888;	
		ESNPMesNoFramer mesNoFramer = new ESNPMesNoFramer();
		FixUint64Coder.fixUint64Encoder(bosB, id);
		ByteArrayInputStream bisB = new ByteArrayInputStream(bosB.toByteArray());
		mesNoFramer.setFramerType(0x11);
		mesNoFramer.setFramerBodyLength((long)bisB.available());
		mesNoFramer.setMesNo(id);
		bosB.close();
		bisB.close();
		
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		mesNoFramer.mesNoFramerToOutputStream(bos32);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		
		
		ByteArrayInputStream bisS = new ByteArrayInputStream(bos32.toByteArray());
		
		ESNPMesNoFramer mesNoFramer1 = new ESNPMesNoFramer(bisS.read(), bisS);
		
	}
	
	@Test
	public void testAddress() throws IOException {
		

		
	}
}
