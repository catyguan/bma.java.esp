package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public abstract class EProcessor {
	
	protected final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(EProcessor.class);
	
	public boolean execute(ESNPServerScoket eTransport,ERequest eRequest,EResponse eResponse){
		
		try {
			this.processor(eTransport, eRequest, eResponse);
			return true;
		} catch (Exception e) {
			log.error("[EProcessor] => " + e.getMessage());
			this.exceptionHandle(eTransport, eResponse,e);
			return false;
		}
		
	}
	
	/**
	 * 处理器
	 * @param eTransport
	 * @param eRequest
	 * @param eResponse
	 * @throws IOException
	 */
	public abstract void processor(ESNPServerScoket eTransport,ERequest eRequest,EResponse eResponse) throws IOException;
	
	/**
	 * 异常处理
	 * @param eTransport
	 * @param eResponse
	 */
	public abstract void exceptionHandle(ESNPServerScoket eTransport,EResponse eResponse,Exception e) ;

}
