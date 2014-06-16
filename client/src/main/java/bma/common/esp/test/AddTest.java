package bma.common.esp.test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.ChannelFuture;

import bma.common.esp.client.core.ESNPClient;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;
import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.transport.ERequest;

public class AddTest {
	
	private ESNPClient client;
		
	public ESNPClient getClient() {
		return client;
	}

	public void setClient(ESNPClient client) {
		this.client = client;
	}

	public void send() throws IOException{
		ERequest request = new ERequest();
		long id = 88888888;
		ESNPMesNoFramer mesNo = new ESNPMesNoFramer();
		mesNo.setMesNo(id);
		
		ESNPMesTypeFramer mesType = new ESNPMesTypeFramer();
		mesType.setMesType(1);
		
		ESNPAddressFramer sAddress = new ESNPAddressFramer();
		sAddress.setAddressType(ESNPAddressFramer.ADDRESS_SERVICE);
		sAddress.setAddress("test");
		
		ESNPAddressFramer oAddress = new ESNPAddressFramer();
		oAddress.setAddressType(ESNPAddressFramer.ADDRESS_OP);
		oAddress.setAddress("add");
		
		List<ESNPAddressFramer> addressList = new ArrayList<ESNPAddressFramer>();
		addressList.add(sAddress);
		addressList.add(oAddress);
		
		ESNPDataFramer aData = new ESNPDataFramer();
		aData.setDataName("a");
		aData.setData(2);
		
		ESNPDataFramer bData = new ESNPDataFramer();
		bData.setDataName("b");
		bData.setData(3);
		
		List<ESNPDataFramer> dataList = new ArrayList<ESNPDataFramer>();
		dataList.add(aData);
		dataList.add(bData);
		
		request.setMesNo(mesNo);
		request.setMesType(mesType);
		request.setAddressList(addressList);
		request.setDataList(dataList);
		
		ByteArrayOutputStream tmpBAOut = new ByteArrayOutputStream();
		//消息编号
		request.getMesNo().mesNoFramerToOutputStream(tmpBAOut);

		//消息类型
		request.getMesType().mesTypeFramerToOutputStream(tmpBAOut);
		
		//地址
		List<ESNPAddressFramer> aList = request.getAddressList();
		if(!aList.isEmpty()){
			for(ESNPAddressFramer af : aList){
				af.addressFramerToOutputStream(tmpBAOut);
			}
		}
		
		//业务数据
		List<ESNPDataFramer> dList = request.getDataList();
		if(!dList.isEmpty()){
			for(ESNPDataFramer df : dList){
				df.dataFramerToOutputStream(tmpBAOut);
			}
		}
		
		client.getClient();
		ChannelFuture c = client.connet();
		c.getChannel().write(tmpBAOut);
		
		byte[] i32buf = new byte[4];
		i32buf[0] = 0;
		ESNPFrameReader.encodeFrameSize(0, i32buf);
		c.getChannel().write(ChannelBuffers.copiedBuffer(i32buf));
		
	}
	

}
