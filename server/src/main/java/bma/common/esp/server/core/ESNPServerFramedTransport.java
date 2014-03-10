package bma.common.esp.server.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.transport.ERequestTransport;
import bma.common.esp.transport.EResponseTransport;
import bma.common.esp.transport.FramerDncoderFactory;
import bma.common.netty.SupportedNettyChannel;

public class ESNPServerFramedTransport implements SupportedNettyChannel{

	protected Channel channel;
	private ChannelBuffer readBuffer;
	
	/**
	 * Buffer for output
	 */
	protected ChannelBuffer writeBuffer = ChannelBuffers.dynamicBuffer(1024);
	
	public ESNPServerFramedTransport(Channel ch, ChannelBuffer rcb){
		this.channel = ch;
		this.readBuffer = rcb;
	}
	
	@Override
	public Channel getChannel() {
		// TODO Auto-generated method stub
		return channel;
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: read 
	* @Description: 从流中获取请求对象
	* @param @param eRequest    
	* @return void    
	* @throws
	 */
	public void read(ERequestTransport eRequest) throws IOException{
		int size = readBuffer.readableBytes();
		byte[] dataByte = new byte[size];
		readBuffer.readBytes(dataByte);
		
		ByteArrayInputStream in = new ByteArrayInputStream(dataByte);
		int fType;
		while(in.available() > 0){
			fType = in.read();
			FramerDncoderFactory.getFramer(eRequest, in, fType);
		}
		
	}
	
	/**
	 * 
	* @Title: read 
	* @Description: 从流中获取响应对象
	* @param @param eResponse    
	* @return void    
	* @throws
	 */
	public void read(EResponseTransport eResponse){
		
	}
	
	public int read(byte[] buf, int off, int len){
		int got = Math.min(readBuffer.readableBytes(), len);
		readBuffer.readBytes(buf, off, got);
		return got;
	}
	
	public void write(byte[] buf, int off, int len){
		writeBuffer.writeBytes(buf, off, len);
	}
	
	/**
	 * 
	* @Title: write 
	* @Description: 将请求对象写入流
	* @param @param eRequest    
	* @return void    
	* @throws
	 */
	public void write(ERequestTransport eRequest){
		
	}
	
	/**
	 * 
	* @Title: write 
	* @Description: 将响应对象写入流
	* @param @param eResponse    
	* @return void    
	* @throws
	 */
	public void write(EResponseTransport eResponse){
		
	}

	public void flush(){
		byte[] i32buf = new byte[4];
		ESNPFrameReader.encodeFrameSize(0, i32buf);
		if (channel.isOpen()) {
			channel.write(writeBuffer);
			channel.write(ChannelBuffers.copiedBuffer(i32buf));
		}
	}
	
	
}
