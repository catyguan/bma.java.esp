package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.Map;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

/**
 * 登录处理器
 * @author reason
 *
 */
public class ESNPLoginProcessor extends EProcessor {
	
	private static String LOGIN_SERVICE = "espnLogin";
	
	//登录
	private EHandler loginHandle;

	@Override
	public void processor(ESNPServerScoket eTransport, ERequest eRequest,
			EResponse eResponse) throws IOException {
		Map<Integer,String> adMap = eRequest.getAddressService();
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_SERVICE)){
			log.error("[ESNPLoginProcessor] => do not find service address! ");
			throw new EspExecption("do not find service address!");
		}
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_OP)){
			log.error("[ESNPLoginProcessor] => do not find op address! ");
			throw new EspExecption("do not find op address!");
		}	
		
		try {
			if(!eTransport.isLogin()){
				if(LOGIN_SERVICE.equals(adMap.get(ESNPAddressFramer.ADDRESS_SERVICE))){
					//获取操作
					EFunction function = loginHandle.getFunctionMap().get(adMap.get(ESNPAddressFramer.ADDRESS_OP));
					
					//执行
					function.execute(eTransport, eRequest, eResponse);	
							
				} 
			} 
			
			if(eTransport.isLogin()){
				log.info("[ESNPLoginProcessor] => isLogin !");
			} else {
				log.error("[ESNPLoginProcessor] => client is fail login ! ");
				throw new EspExecption("[ESNPLoginProcessor] => client is fail login ! ");
			}
			
		} catch (Exception e) {
			log.error("[ESNPLoginProcessor] => error : " + e.getMessage());
			throw new EspExecption(e.getMessage());
		}
		
	}

	@Override
	public void exceptionHandle(ESNPServerScoket eTransport,
			EResponse eResponse, Exception e) {
		try {
			eResponse.setError(e.getMessage());
			eTransport.write(eResponse);
			eTransport.flush();
		} catch (IOException e1) {
			log.error("[ESNPLoginProcessor] => inner error " + e1.getMessage());
		}
	}

	public EHandler getLoginHandle() {
		return loginHandle;
	}

	public void setLoginHandle(EHandler loginHandle) {
		this.loginHandle = loginHandle;
	}
	
	

}
