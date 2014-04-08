package bma.common.esp.framer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.Uint32Coder;

/**
 * 
* @ClassName: ESNPMesTypeFramer 
* @Description: 消息类型帧  
* @author zhongrisheng
* @date 2014-3-10 上午10:26:13 
*
 */
public class ESNPMesTypeFramer extends ESNPBaseFramer{
	/*
	 * 消息类型
	 */
	private int mesType;
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: 从流中获取数据构建对象</p> 
	* @param framerType
	* @param in
	* @throws IOException
	 */
	public ESNPMesTypeFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesType(in);
	}

	public ESNPMesTypeFramer() {
		super();
	}

	public int getMesType() {
		return mesType;
	}

	public void setMesType(int mesType) {
		this.mesType = mesType;
	}
	
	/**
	 * 
	* @Title: setMesNo 
	* @Description: 从流中读取消息类型
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setMesType(InputStream in) throws IOException {
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		this.mesType = Uint32Coder.uint32Decoder(in);
	}
	
	/**
	 * 
	* @Title: mesNoFramerToOutputStream 
	* @Description: 将消息类型帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void mesTypeFramerToOutputStream(OutputStream out) throws IOException{
		ByteArrayOutputStream mesTypeOut = new ByteArrayOutputStream();
		Uint32Coder.uint32Encoder(mesTypeOut, this.mesType);
		byte[] dataByte = mesTypeOut.toByteArray();
		super.toOutputStream(out,dataByte.length);	
		out.write(dataByte);
	}

	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesType(in);
	}
	
}
