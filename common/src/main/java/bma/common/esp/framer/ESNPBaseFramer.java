package bma.common.esp.framer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * 
* @ClassName: ENSPBaseFramer 
* @Description: 基本帧 
* @author zhongrisheng
* @date 2014-3-7 下午03:38:04 
*
 */
public class ESNPBaseFramer {
	
	/**
	 * 帧类型
	 */
	private int framerType;
	
	/**
	 * 体大小
	 */
	private long framerBodyLength;
	

	public int getFramerType() {
		return framerType;
	}


	public void setFramerType(int framerType) {
		this.framerType = framerType;
	}


	public long getFramerBodyLength() {
		return framerBodyLength;
	}

	/**
	 * 
	* @Title: toStreamFramerBodyLength 
	* @Description: 将体大小数据写进流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	private void toStreamFramerBodyLength(OutputStream out) throws IOException {
		out.write((byte) (this.framerBodyLength >> 16));
		out.write((byte) (this.framerBodyLength >> 8));
		out.write((byte) (this.framerBodyLength >> 0));
	}
	
	/**
	 * 
	* @Title: toStreamFramerBodyLength 
	* @Description: 将体大小数据写进流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	private void toStreamFramerBodyLength(OutputStream out,long dataLength) throws IOException {
		out.write((byte) (dataLength >> 16));
		out.write((byte) (dataLength >> 8));
		out.write((byte) (dataLength >> 0));
	}

	public void setFramerBodyLength(long framerBodyLength) {
		this.framerBodyLength = framerBodyLength;
	}
	
	/**
	 * 
	* @Title: setFramerBodyLength 
	* @Description: 从流中获取体大小数据
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setFramerBodyLength(InputStream in) throws IOException {
		int s = 0;
		s += (in.read() & 0xff) << 16;
		s += (in.read() & 0xff) << 8;
		s += (in.read() & 0xff) ;	
		this.framerBodyLength = (long) s;
	}	
	
	/**
	 * 
	* @Title: toOutputStream 
	* @Description: 将帧类型和体大小写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void toOutputStream(OutputStream out) throws IOException{
		byte[] typeByte = new byte[1];
		typeByte[0] = (byte) this.framerType;
		out.write(typeByte);
		this.toStreamFramerBodyLength(out);		
	}
	
	/**
	 * 
	* @Title: toOutputStream 
	* @Description: 将帧类型和体大小写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void toOutputStream(OutputStream out,long dataLength) throws IOException{
		byte[] typeByte = new byte[1];
		typeByte[0] = (byte) this.framerType;
		out.write(typeByte);
		this.toStreamFramerBodyLength(out,dataLength);		
	}

}
