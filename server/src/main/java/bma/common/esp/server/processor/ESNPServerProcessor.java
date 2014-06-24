package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public class ESNPServerProcessor extends EProcessor{
	
	/**
	 * 服务器业务处理Map
	 */
	private Map<String,EHandler> handleMap;
	
	//threadlocal
	protected static ThreadLocal<ESNPServerScoket> scoketThreadLocal = new ThreadLocal<ESNPServerScoket>();

	@Override
	public void processor(ESNPServerScoket eTransport,
			ERequest eRequest, EResponse eResponse) throws IOException {
		
		scoketThreadLocal.set(eTransport);
		
		Map<Integer,String> adMap = eRequest.getAddressService();
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_SERVICE)){
			log.error("[ESNPServerProcessor] => do not find service address! ");
			throw new EspExecption("do not find service address!");
		}
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_OP)){
			log.error("[ESNPServerProcessor] => do not find op address! ");
			throw new EspExecption("do not find op address!");
		}	
		
		try {
			//获取handle
			EHandler handle = handleMap.get(adMap.get(ESNPAddressFramer.ADDRESS_SERVICE));

			//获取操作
			EFunction function = handle.getFunctionMap().get(adMap.get(ESNPAddressFramer.ADDRESS_OP));
			
			//执行
			function.execute(eTransport, eRequest, eResponse);	
			
		} catch (Exception e) {
			log.error("[ESNPServerProcessor] => error : " + e.getMessage());
			throw new EspExecption(e.getMessage());
		}

	}

	public Map<String, EHandler> getHandleMap() {
		return handleMap;
	}

	public void setHandleMap(Map<String, EHandler> handleMap) {
		this.handleMap = handleMap;
	}

	@Override
	public void exceptionHandle(ESNPServerScoket eTransport,
			EResponse eResponse, Exception e) {
		try {
			eResponse.setError(e.getMessage());
			eTransport.write(eResponse);
			eTransport.flush();
		} catch (IOException e1) {
			log.error("[ESNPServerProcessor] => inner error " + e1.getMessage());
		}
	}
	
	public static ESNPServerScoket currentScoket() {
		return scoketThreadLocal.get();
	}

	

}
