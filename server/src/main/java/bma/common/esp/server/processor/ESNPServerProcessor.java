package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class ESNPServerProcessor implements EProcessor{
	
	/**
	 * 服务器业务处理Map
	 */
	private Map<String,EHandler> handleMap;

	@Override
	public void processor(ESNPServerTransport eTransport,
			ERequest eRequest, EResponse eResponse) throws IOException {
		
		Map<Integer,String> adMap = getAddressService(eRequest);
		
		//获取handle
		EHandler handle = handleMap.get(adMap.get(30));

		//获取操作
		EFunction function = handle.getFunctionMap().get(adMap.get(20));
		
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
