package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerScoket;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;

public abstract class EProcessor {
	
	
	
	public abstract void processor(ESNPServerScoket eTransport,ERequest eRequest,EResponse eResponse) throws IOException;

}
