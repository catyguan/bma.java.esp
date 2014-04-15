package bma.common.esp.server.core;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;

import bma.common.esp.framer.ESNPDataFramer;
import bma.common.esp.framer.ESNPErrorFramer;
import bma.common.esp.server.frame.ESNPFrameReader;
import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;
import bma.common.esp.transport.FramerDncoderFactory;
import bma.common.netty.SupportedNettyChannel;

public class ESNPServerScoket implements SupportedNettyChannel{

	protected Channel channel;
	private ChannelBuffer readBuffer;
	
	/**
	 * Buffer for output
	 */
	protected ChannelBuffer writeBuffer = ChannelBuffers.dynamicBuffer(1024);
	
	public ESNPServerScoket(Channel ch, ChannelBuffer rcb){
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
	public void read(ERequest eRequest) throws IOException{
		int size = readBuffer.readableBytes();
		byte[] dataByte = new byte[size];
		readBuffer.readBytes(dataByte);
		
		ByteArrayInputStream in = new ByteArrayInputStream(dataByte);
		int fType;
		while(in.available() > 0){
			fType = in.read();
			FramerDncoderFactory.getERequestFramer(eRequest, in, fType);
		}
		readBuffer.resetReaderIndex();
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
	public void write(ERequest eRequest){
		
	}
	
	/**
	 * @throws IOException 
	 * 
	* @Title: write 
	* @Description: 将响应对象写入流
	* @param @param eResponse    
	* @return void    
	* @throws
	 */
	public void write(EResponse eResponse) throws IOException{
		ByteArrayOutputStream tmpBAOut = new ByteArrayOutputStream();
		//消息编号
		eResponse.getMesNo().mesNoFramerToOutputStream(tmpBAOut);
		//源消息编号
		eResponse.getMesSno().mesSnoFramerToOutputStream(tmpBAOut);
		//消息类型
		eResponse.getMesType().mesTypeFramerToOutputStream(tmpBAOut);
		
		//业务数据
		List<ESNPDataFramer> dList = eResponse.getDataList();
		if(!dList.isEmpty()){
			for(ESNPDataFramer df : dList){
				df.dataFramerToOutputStream(tmpBAOut);
			}
		}
		
		//错误信息
		ESNPErrorFramer error = eResponse.getError();
		if(error != null){
			error.errorFramerToOutputStream(tmpBAOut);
		}
		
		if(writeBuffer.writableBytes() > tmpBAOut.size()){
			writeBuffer.writeBytes(tmpBAOut.toByteArray());
		}
	}

	public void flush(){
		byte[] i32buf = new byte[4];
		i32buf[0] = 0;
		ESNPFrameReader.encodeFrameSize(0, i32buf);
		if (channel.isOpen()) {
			channel.write(writeBuffer);
			channel.write(ChannelBuffers.copiedBuffer(i32buf));
			
		}
	}
	
	public String getRemoteHost(){
		InetSocketAddress s = (InetSocketAddress) channel.getRemoteAddress();
		return s.getHostString();
	}
	
	public int getRemotePort(){
		InetSocketAddress s = (InetSocketAddress) channel.getRemoteAddress();
		return s.getPort();
	}
	
	
}
