package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class ESNPServerProcessor extends EProcessor{
	
	/**
	 * 服务器业务处理Map
	 */
	private Map<String,EHandler> handleMap;

	@Override
	public void processor(ESNPServerTransport eTransport,
			ERequest eRequest, EResponse eResponse) throws IOException {
		
		Map<Integer,String> adMap = getAddressService(eRequest);
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_SERVICE)){
			throw new EspExecption("do not find service address!");
		}
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_OP)){
			throw new EspExecption("do not find op address!");
		}	
		
		//获取handle
		EHandler handle = handleMap.get(adMap.get(ESNPAddressFramer.ADDRESS_SERVICE));

		//获取操作
		EFunction function = handle.getFunctionMap().get(adMap.get(ESNPAddressFramer.ADDRESS_OP));
		
		//执行
		function.execute(eTransport, eRequest, eResponse);	
	}
	
	private Map<Integer,String> getAddressService(ERequest eRequest){
		Map<Integer,String> adMap = new HashMap<Integer, String>();
		List<ESNPAddressFramer> afList = eRequest.getAddressList();
		for(ESNPAddressFramer af : afList){
			adMap.put(af.getAddressType(), af.getAddress());
		}
		return adMap;
	}

	public Map<String, EHandler> getHandleMap() {
		return handleMap;
	}

	public void setHandleMap(Map<String, EHandler> handleMap) {
		this.handleMap = handleMap;
	}

	

}
