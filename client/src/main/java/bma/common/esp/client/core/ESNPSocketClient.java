package bma.common.esp.client.core;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import bma.common.esp.transport.ERequest;
import bma.common.esp.transport.EResponse;
import bma.common.esp.transport.FramerDncoderFactory;

public class ESNPSocketClient {
	final org.slf4j.Logger log = org.slf4j.LoggerFactory
			.getLogger(ESNPSocketClient.class);

	protected String serverHost;
	protected int serverPost;
	
	protected Socket socket;
	
	/**
	 * 连接
	 */
	public void connet(){
		try {
			socket=new Socket(serverHost,serverPost);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭
	 */
	public void close(){
		if(socket != null && socket.isConnected()){
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
	}
	
	/**
	 * 写请求
	 */
	public void write(byte[] buf){
        //通过printWriter 来向服务器发送消息
		try {
			DataOutputStream writer = new DataOutputStream(socket.getOutputStream());  
		    writer.write(buf);
		    writer.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public InputStream read(){		
		DataInputStream in;
		try {
			in = new DataInputStream(socket.getInputStream());
			return in;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

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
	public void read(InputStream in,EResponse eResponse) throws IOException{
		int size = in.available();
		byte[] dataByte = new byte[size];
		in.read(dataByte);
		
		ByteArrayInputStream bin = new ByteArrayInputStream(dataByte);
		int fType;
		while(bin.available() > 0){
			fType = bin.read();
			FramerDncoderFactory.getEResponseFramer(eResponse, bin, fType);
		}
	}

	public String getServerHost() {
		return serverHost;
	}

	public void setServerHost(String serverHost) {
		this.serverHost = serverHost;
	}

	public int getServerPost() {
		return serverPost;
	}

	public void setServerPost(int serverPost) {
		this.serverPost = serverPost;
	}

	public Socket getSocket() {
		return socket;
	}
	
	
}
