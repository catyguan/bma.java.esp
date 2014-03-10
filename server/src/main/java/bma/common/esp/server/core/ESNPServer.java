package bma.common.esp.server.core;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.http.HttpRequest;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.transport.ERequestTransport;
import bma.common.netty.NettyServer;

public class ESNPServer extends NettyServer{
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
	.getLogger(ESNPServer.class);

	@Override
	protected void beforeBuildPipeline(ChannelPipeline pipeline) {
		System.out.println("beforeBuildPipeline");
		super.beforeBuildPipeline(pipeline);
		pipeline.addLast("framed", new ESNPFrameReader());
		pipeline.addLast("transport", new OneToOneDecoder() {
			@Override
			protected Object decode(ChannelHandlerContext ctx, Channel channel,
					Object msg) throws Exception {
				if (msg instanceof ChannelBuffer) {
					ChannelBuffer cb = (ChannelBuffer) msg;
					ESNPServerFramedTransport t = new ESNPServerFramedTransport(
							ctx.getChannel(), cb);
					return t;
				}
				return msg;
			}
		});
		pipeline.addLast("main", new Handler());
		
		
	}
	
	public class Handler extends SimpleChannelUpstreamHandler {

		@Override
		public void messageReceived(final ChannelHandlerContext nctx,
				MessageEvent e) throws Exception {
			Object obj = e.getMessage();
			if( ! (obj instanceof ESNPServerFramedTransport)){
				return;
			}
			ESNPServerFramedTransport t = (ESNPServerFramedTransport) obj;
			
			ERequestTransport eRequest = new ERequestTransport();
			t.read(eRequest);
			
			System.out.println("SUCCESS!");

		}

		@Override
		public void exceptionCaught(ChannelHandlerContext nctx, ExceptionEvent e)
				throws Exception {

		}

	}
	
}
