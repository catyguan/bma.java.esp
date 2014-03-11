package bma.common.esp.framer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.Test;

import bma.common.esp.common.VarTypeCommon;
import bma.common.langutil.core.StringUtil;

public class FramerTest {

	@Test
	public void testMesNo() throws IOException {
		
		ByteArrayOutputStream bosB = new ByteArrayOutputStream();
		
		long id = 88888888;	
		ESNPMesNoFramer mesNoFramer = new ESNPMesNoFramer();
//		FixUint64Coder.fixUint64Encoder(bosB, id);
//		ByteArrayInputStream bisB = new ByteArrayInputStream(bosB.toByteArray());
		mesNoFramer.setFramerType(0x11);
//		mesNoFramer.setFramerBodyLength((long)bisB.available());
		mesNoFramer.setMesNo(id);
		bosB.close();
//		bisB.close();
		
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		mesNoFramer.mesNoFramerToOutputStream(bos32);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		
		
		ByteArrayInputStream bisS = new ByteArrayInputStream(bos32.toByteArray());
		
		ESNPMesNoFramer mesNoFramer1 = new ESNPMesNoFramer(bisS.read(), bisS);
		System.out.println("end");
		
	}
	
	@Test
	public void testMesType() throws IOException {
		
		ByteArrayOutputStream bosB = new ByteArrayOutputStream();
		
		int id = 88888888;	
		ESNPMesTypeFramer mesTypeFramer = new ESNPMesTypeFramer();
//		Uint32Coder.uint32Encoder(bosB, id);
//		ByteArrayInputStream bisB = new ByteArrayInputStream(bosB.toByteArray());
		mesTypeFramer.setFramerType(0x13);
//		mesTypeFramer.setFramerBodyLength((long)bisB.available());
		mesTypeFramer.setMesType(id);
		bosB.close();
//		bisB.close();
		
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		mesTypeFramer.mesTypeFramerToOutputStream(bos32);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		
		
		ByteArrayInputStream bisS = new ByteArrayInputStream(bos32.toByteArray());
		
		ESNPMesTypeFramer mesTypeFramer1 = new ESNPMesTypeFramer(bisS.read(), bisS);
		System.out.println("end");
		
	}
	
	@Test
	public void testAddress() throws IOException {
		
		ESNPAddressFramer af = new ESNPAddressFramer();
		af.setFramerType(0x17);
		af.setAddressType(40);
		af.setAddress("administrator");
		
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		af.addressFramerToOutputStream(bos32);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		
		ByteArrayInputStream bisS = new ByteArrayInputStream(bos32.toByteArray());
		
		ESNPAddressFramer af1 = new ESNPAddressFramer(bisS.read(),bisS);
		System.out.println("end");
		
	}
	
	@Test
	public void testData() throws IOException {
		
		int a = 1;
		int b = 2;
		
		ESNPDataFramer af = new ESNPDataFramer();
		af.setFramerType(0x15);
		af.setDataName("a");
		af.setDataType(VarTypeCommon.TYPE_INT32);
		af.setData(a);
		
		ByteArrayOutputStream bos32 = new ByteArrayOutputStream();
		af.dataFramerToOutputStream(bos32);
		System.out.println(StringUtil.byte2Hex(bos32.toByteArray()));
		
		ByteArrayInputStream bisS = new ByteArrayInputStream(bos32.toByteArray());
		
		ESNPDataFramer af1 = new ESNPDataFramer(bisS.read(),bisS);
		System.out.println("end");
		
	}
}
