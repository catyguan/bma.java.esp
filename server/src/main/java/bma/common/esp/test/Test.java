package bma.common.esp.test;

import java.io.IOException;

import bma.common.esp.server.core.ESNPServerFramedTransport;
import bma.common.esp.server.processor.EFunction;
import bma.common.esp.server.processor.EHandler;
import bma.common.esp.transport.ERequestTransport;
import bma.common.esp.transport.EResponseTransport;

public class Test extends EHandler{
	
	private Iface iface;

	public interface Iface{
		
		public void add(ESNPServerFramedTransport eTransport,
				ERequestTransport eRequest, EResponseTransport eResponse) throws IOException;
	}
	
	public Test(){
		super.getFunctionMap().put("add", new Add());
	}
	
	public class Add extends EFunction{

		@Override
		public void execute(ESNPServerFramedTransport eTransport,
				ERequestTransport eRequest, EResponseTransport eResponse) throws IOException {
			iface.add(eTransport, eRequest, eResponse);
		}
	}

	public Iface getIface() {
		return iface;
	}

	public void setIface(Iface iface) {
		this.iface = iface;
	}
	
	

}
