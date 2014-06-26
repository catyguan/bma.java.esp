package bma.common.esp.test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import bma.common.esp.client.core.ESNPSocketClient;
import bma.common.esp.common.FramerCommon;
import bma.common.esp.common.VarTypeCommon;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;
import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class AddTest {
	
	private ESNPSocketClient client;
		
	public ESNPSocketClient getClient() {
		return client;
	}

	public void setClient(ESNPSocketClient client) {
		this.client = client;
	}

	public void send() throws IOException{
		ERequest request = new ERequest();
		long id = 88888888;
		ESNPMesNoFramer mesNo = new ESNPMesNoFramer();
		mesNo.setFramerType(FramerCommon.FRAMER_TYPE_ID);
		mesNo.setMesNo(id);
		
		ESNPMesTypeFramer mesType = new ESNPMesTypeFramer();
		mesType.setFramerType(FramerCommon.FRAMER_TYPE_TYPE);
		mesType.setMesType(1);
		
		ESNPAddressFramer sAddress = new ESNPAddressFramer();
		sAddress.setFramerType(FramerCommon.FRAMER_TYPE_ADDRESS);
		sAddress.setAddressType(ESNPAddressFramer.ADDRESS_SERVICE);
		sAddress.setAddress("test");
		
		ESNPAddressFramer oAddress = new ESNPAddressFramer();
		oAddress.setFramerType(FramerCommon.FRAMER_TYPE_ADDRESS);
		oAddress.setAddressType(ESNPAddressFramer.ADDRESS_OP);
		oAddress.setAddress("add");
		
		List<ESNPAddressFramer> addressList = new ArrayList<ESNPAddressFramer>();
		addressList.add(sAddress);
		addressList.add(oAddress);
		
		ESNPDataFramer aData = new ESNPDataFramer();
		aData.setFramerType(FramerCommon.FRAMER_TYPE_DATA);
		aData.setDataName("a");
		aData.setDataType(VarTypeCommon.TYPE_INT32);
		aData.setData(2);
		
		ESNPDataFramer bData = new ESNPDataFramer();
		bData.setFramerType(FramerCommon.FRAMER_TYPE_DATA);
		bData.setDataType(VarTypeCommon.TYPE_INT32);
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
		
		client.connet();
		client.write(tmpBAOut.toByteArray());
		
		byte[] i32buf = new byte[4];
		i32buf[0] = 0;
		ESNPFrameReader.encodeFrameSize(0, i32buf);
		client.write(i32buf);
		EResponse eResponse = new EResponse();
		while(true){
			InputStream in = client.read();
			if(in != null){
				client.read(in, eResponse);
				break;
			}
		}
		
		System.out.println("原id："+eResponse.getMesSno().getMesSno());
		System.out.println("id："+eResponse.getMesNo().getMesNo());
		System.out.println("类型："+eResponse.getMesType().getMesType());
		if(eResponse.getDataList() != null && !eResponse.getDataList().isEmpty()){
			for(ESNPDataFramer dd : eResponse.getDataList()){
				System.out.println(dd.getDataName() + ":" + dd.getData());
			}
		}

		
		client.close();
	}
	

}
