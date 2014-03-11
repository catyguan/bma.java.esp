package bma.common.esp.server.core;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.jboss.netty.handler.codec.oneone.OneToOneDecoder;

import bma.common.esp.common.FramerCommon;
import bma.common.esp.framer.ESNPMesNoFramer;
import bma.common.esp.framer.ESNPMesTypeFramer;
import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.server.processor.ESNPServerProcessor;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;
import bma.common.esp.utils.BaseTypeTool;
import bma.common.netty.NettyServer;

public class ESNPServer extends NettyServer{
	
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
	.getLogger(ESNPServer.class);
	
	protected ESNPServerProcessor processor;
	
	public ESNPServerProcessor getProcessor() {
		return processor;
	}

	public void setProcessor(ESNPServerProcessor processor) {
		this.processor = processor;
	}

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
					ESNPServerTransport t = new ESNPServerTransport(
							ctx.getChannel(), cb);
					return t;
				}
				return msg;
			}
		});
		pipeline.addLast("processer", new Handler());
		
		
	}
	
	public class Handler extends SimpleChannelUpstreamHandler {

		@Override
		public void messageReceived(final ChannelHandlerContext nctx,
				MessageEvent e) throws Exception {
			Object obj = e.getMessage();
			if( ! (obj instanceof ESNPServerTransport)){
				return;
			}
			ESNPServerTransport t = (ESNPServerTransport) obj;
			
			ERequest eRequest = new ERequest();
			t.read(eRequest);
			
			EResponse eResponse = new EResponse();
			
			ESNPMesNoFramer mnf = new ESNPMesNoFramer();
			mnf.setFramerType(FramerCommon.FRAMER_TYPE_ID);
			mnf.setMesNo(BaseTypeTool.getRandomID());
			
			ESNPMesTypeFramer mtf = new ESNPMesTypeFramer();
			mtf.setFramerType(FramerCommon.FRAMER_TYPE_TYPE);
			mtf.setMesType(2);
			
			eResponse.setMesNo(mnf);
			eResponse.setMesSno(eRequest.getMesNo().tranToMesSnoFramer());	
			eResponse.setMesType(mtf);
			
			//服务器分发请求
			processor.processor(t, eRequest, eResponse);
			
			log.info("SUCCESS");

		}

		@Override
		public void exceptionCaught(ChannelHandlerContext nctx, ExceptionEvent e)
				throws Exception {

		}

	}
	
}
