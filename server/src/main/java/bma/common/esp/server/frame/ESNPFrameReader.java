package bma.common.esp.server.frame;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;

import bma.common.netty.framereader.FrameReaderDecoder;

public class ESNPFrameReader extends FrameReaderDecoder{
	
	public ESNPFrameReader(){
		super();
	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, Channel channel,
			ChannelBuffer buffer) throws Exception {
		byte[] i32buf = new byte[4];
		buffer.markReaderIndex();
		int type;
		int length;
		int readIndex;
		while(buffer.readableBytes() >= 4){
			buffer.readBytes(i32buf);
			type = i32buf[0];
			length = decodeFrameSize(i32buf);
			
			if(type == 0 && length == 0){
				readIndex = buffer.readerIndex();
				buffer.resetReaderIndex();
				return buffer.readBytes(readIndex-buffer.readerIndex());			
			}
			
			if(buffer.readableBytes() < length){
				break;
			}
			buffer.skipBytes(length);
		}
		
		buffer.resetReaderIndex();
		return null;
	}

	public static final int decodeFrameSize(final byte[] buf) {
		return (
				((buf[1] & 0xff) << 16)| 
				((buf[2] & 0xff) << 8) | 
				((buf[3] & 0xff)));
	}

	public static final void encodeFrameSize(final int frameSize,
			final byte[] buf) {
		buf[1] = (byte) (0xff & (frameSize >> 16));
		buf[2] = (byte) (0xff & (frameSize >> 8));
		buf[3] = (byte) (0xff & (frameSize));
	}
	
}
