package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

/**
 * 登录处理器
 * @author reason
 *
 */
public class ESNPLoginProcessor extends EProcessor {

	@Override
	public void processor(ESNPServerScoket eTransport, ERequest eRequest,
			EResponse eResponse) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exceptionHandle(ESNPServerScoket eTransport,
			EResponse eResponse, Exception e) {
		// TODO Auto-generated method stub
		
	}

}
