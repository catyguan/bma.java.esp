package bma.common.esp.framer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import bma.common.esp.coder.FixUint64Coder;

/**
 * 
* @ClassName: ESNPMesSnoFramer 
* @Description: 消息源编号帧 
* @author zhongrisheng
* @date 2014-3-7 下午04:23:14 
*
 */
public class ESNPMesSnoFramer extends ESNPBaseFramer {
	
	/**
	 * 源ID编号
	 */
	private long mesSno;
	
	/**
	 * 
	* <p>Title: </p> 
	* <p>Description: 从流中获取数据构建对象</p> 
	* @param framerType
	* @param in
	* @throws IOException
	 */
	public ESNPMesSnoFramer(int framerType,InputStream in) throws IOException{
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesSno(in);
	}

	public ESNPMesSnoFramer() {
		super();
	}

	public long getMesSno() {
		return mesSno;
	}

	public void setMesSno(long mesSno) {
		this.mesSno = mesSno;
	}
	
	/**
	 * 
	* @Title: setMesNo 
	* @Description: 从流中读取消息编号
	* @param @param in
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void setMesSno(InputStream in) throws IOException {
		if(super.getFramerType() == 0 || super.getFramerBodyLength() == 0){
			return ;
		}
		this.mesSno = FixUint64Coder.fixUint64Decoder(in);
	}
	
	/**
	 * 
	* @Title: mesSnoFramerToOutputStream 
	* @Description: 将消息帧写入流
	* @param @param out
	* @param @throws IOException    
	* @return void    
	* @throws
	 */
	public void mesSnoFramerToOutputStream(OutputStream out) throws IOException{	
		ByteArrayOutputStream mesNoOut = new ByteArrayOutputStream();
		FixUint64Coder.fixUint64Encoder(mesNoOut, this.mesSno);
		byte[] dataByte = mesNoOut.toByteArray();
		super.toOutputStream(out,dataByte.length);	
		out.write(dataByte);		
	}

	@Override
	public void readInputStream(int framerType, InputStream in)
			throws IOException {
		super.setFramerType(framerType);
		super.setFramerBodyLength(in);
		this.setMesSno(in);		
	}
}
