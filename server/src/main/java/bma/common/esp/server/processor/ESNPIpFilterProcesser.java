package bma.common.esp.server.processor;

import java.io.IOException;
import java.util.Map;

import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

/**
 * ip限制处理器
 * @author reason
 *
 */
public class ESNPIpFilterProcesser extends EProcessor {
	
	/**
	 * 服务器ip限制Map
	 */
	private Map<String,String> serverIpMap;

	@Override
	public void processor(ESNPServerTransport eTransport, ERequest eRequest,
			EResponse eResponse) throws IOException {
		// TODO 处理服务ip匹配
		
	}

	public Map<String, String> getServerIpMap() {
		return serverIpMap;
	}

	public void setServerIpMap(Map<String, String> serverIpMap) {
		this.serverIpMap = serverIpMap;
	}
	
	

}
