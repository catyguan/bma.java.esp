package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerTransport;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public interface EProcessor {
	
	public void processor(ESNPServerTransport eTransport,ERequest eRequest,EResponse eResponse) throws IOException;

}
