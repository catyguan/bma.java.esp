package bma.common.esp.test;

import java.io.IOException;
import java.util.Map;

import org.hamcrest.core.IsInstanceOf;

import bma.common.esp.common.FramerCommon;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.server.core.ESNPServerFramedTransport;
import bma.common.esp.transport.ERequestTransport;
import bma.common.esp.transport.EResponseTransport;

public class TestServer implements Test.Iface {

	@Override
	public void add(ESNPServerFramedTransport eTransport,
			ERequestTransport eRequest, EResponseTransport eResponse) throws IOException {

		Map<String, Object> dataMap = eRequest.getData();
		
		int c;
		int a = (Integer)dataMap.get("a");
		int b = (Integer)dataMap.get("b");
		c =  a + b;
		
		//组装数据
		ESNPDataFramer dataF = new ESNPDataFramer();
		dataF.setFramerType(FramerCommon.FRAMER_TYPE_DATA);
		dataF.setDataName("c");
		dataF.setDataType(5);
		dataF.setData(c);	
		eResponse.addDataFramer(dataF);	
		
		//发送
		eTransport.write(eResponse);
		eTransport.flush();
		
		System.out.println("AAAA");
	}

}
