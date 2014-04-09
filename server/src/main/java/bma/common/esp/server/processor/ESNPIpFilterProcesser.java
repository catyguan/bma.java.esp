package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.Map;

import bma.common.esp.exception.EspExecption;
import bma.common.esp.framer.ESNPAddressFramer;
import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

/**
 * ip限制处理器
 * @author reason
 *
 */
public class ESNPIpFilterProcesser extends EProcessor {
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ESNPIpFilterProcesser.class);
	
	/**
	 * 服务器ip限制Map
	 */
	private Map<String,String> serverIpMap;

	@Override
	public void processor(ESNPServerScoket eTransport, ERequest eRequest,
			EResponse eResponse) throws IOException {
		// TODO 处理服务ip匹配
		String host = eTransport.getRemoteHost();
		System.out.println(host);
		
		Map<Integer,String> adMap = eRequest.getAddressService();
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_SERVICE)){
			throw new EspExecption("do not find service address!");
		}
		
		if(!adMap.containsKey(ESNPAddressFramer.ADDRESS_OP)){
			throw new EspExecption("do not find op address!");
		}	
		
		if(serverIpMap.containsKey(adMap.get(ESNPAddressFramer.ADDRESS_SERVICE))){
			String whiteList = serverIpMap.get(adMap.get(ESNPAddressFramer.ADDRESS_SERVICE));
			if(whiteList != null && !"".equals(whiteList)){
				if (whiteList.contains(host)) {
					log.info("[ESNPIpFilterProcesser] => you can visit this server (" + adMap.get(ESNPAddressFramer.ADDRESS_SERVICE) + ")");
					return;
				} else {
					log.info("[ESNPIpFilterProcesser] => this host " + host + " can not visit this server (" + adMap.get(ESNPAddressFramer.ADDRESS_SERVICE) + ")");
					throw new EspExecption("[ESNPIpFilterProcesser] => this host " + host + " can not visit this server (" + adMap.get(ESNPAddressFramer.ADDRESS_SERVICE) + ")");
				}
			}
		}		
		
	}

	public Map<String, String> getServerIpMap() {
		return serverIpMap;
	}

	public void setServerIpMap(Map<String, String> serverIpMap) {
		this.serverIpMap = serverIpMap;
	}
	
	

}
