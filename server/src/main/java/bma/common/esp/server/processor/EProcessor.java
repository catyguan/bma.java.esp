package bma.common.esp.server.processor;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerFramedTransport;
import bma.common.esp.transport.ERequestTransport;
import bma.common.esp.transport.EResponseTransport;

public interface EProcessor {
	
	public void processor(ESNPServerFramedTransport eTransport,ERequestTransport eRequest,EResponseTransport eResponse) throws IOException;

}
