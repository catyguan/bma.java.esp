package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerFramedTransport;
import bma.common.esp.transport.ERequestTransport;
import bma.common.esp.transport.EResponseTransport;

public class ESNPServerProcessor implements EProcessor{
	
	/**
	 * 服务器业务处理Map
	 */
	private Map<String,EHandler> handleMap;

	@Override
	public void processor(ESNPServerFramedTransport eTransport,
			ERequestTransport eRequest, EResponseTransport eResponse) throws IOException {
		
		List<ESNPAddressFramer> adList = eRequest.getAddressList();
		
		Map<Integer,String> adMap = getAddressService(eRequest);
		
		//获取handle
		EHandler handle = handleMap.get(adMap.get(30));

		//获取操作
		handle.getFunctionMap();
		EFunction function = handle.getFunctionMap().get(adMap.get(20));
		
		//执行
		function.execute(eTransport, eRequest, eResponse);	
	}
	
	private Map<Integer,String> getAddressService(ERequestTransport eRequest){
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
