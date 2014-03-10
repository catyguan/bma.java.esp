package bma.common.esp.transport;

import org.jboss.netty.buffer.ChannelBuffer;

public class ETransport {
	
	private ChannelBuffer channelBuf;

	public ChannelBuffer getChannelBuf() {
		return channelBuf;
	}

	public void setChannelBuf(ChannelBuffer channelBuf) {
		this.channelBuf = channelBuf;
	}
	
}
